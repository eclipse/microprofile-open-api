/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eclipse.microprofile.openapi.tck.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@WebServlet("/")
public class YamlToJsonConverterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9080;

    private static final String OPENAPI_PATH = "/openapi";

    private String username;
    private String password;
    private String serverUrl;

    @Override
    public void init() throws ServletException {
        username = System.getProperty("test.user");
        password = System.getProperty("test.pwd");

        serverUrl = System.getProperty("test.url");
        if (serverUrl == null) {
            serverUrl = DEFAULT_PROTOCOL + "://" + DEFAULT_HOST + ":" + DEFAULT_PORT;
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader rd = null;
        PrintWriter writer = null;

        try {
            HttpClientBuilder builder = HttpClientBuilder.create();
            if (username != null && password != null) {
                CredentialsProvider provider = new BasicCredentialsProvider();
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
                provider.setCredentials(AuthScope.ANY, credentials);
                builder.setDefaultCredentialsProvider(provider);
            }

            HttpClient client = builder.build();
            HttpGet targetRequest = new HttpGet(serverUrl + OPENAPI_PATH);

            // Copy request headers
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                Enumeration<String> headers = request.getHeaders(headerName);
                while (headers.hasMoreElements()) {
                    targetRequest.addHeader(headerName, headers.nextElement());
                }
            }

            // Forward the request
            HttpResponse targetResponse = client.execute(targetRequest);

            // Copy response headers
            response.setStatus(targetResponse.getStatusLine().getStatusCode());
            Arrays.stream(targetResponse.getAllHeaders()).forEach(h -> response.addHeader(h.getName(), h.getValue()));

            // Copy the content
            String yaml = EntityUtils.toString(targetResponse.getEntity());

            // Convert the yaml to json
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(yaml, Object.class);

            ObjectMapper jsonWriter = new ObjectMapper();
            String json = jsonWriter.writeValueAsString(obj);
            writer = response.getWriter();
            writer.print(json);
            writer.close();
        }
        catch (Exception e) {
            response.sendError(500, "Failed to convert the request: " + ExceptionUtils.getMessage(e));
        }
        finally {
            if (rd != null) {
                try {
                    rd.close();
                }
                catch (IOException e) {
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
