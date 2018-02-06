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

package org.eclipse.microprofile.openapi.tck;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.tck.utils.YamlToJsonConverterServlet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem; //maven.MavenDependencyResolver;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;

public abstract class AppTestBase extends Arquillian {
    private static final String APPLICATION_JSON = "application/json";

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9080;

    private static String serverUrl;
    private static String username;
    private static String password;
    private static Map<String, String> headers = new HashMap<>();
        
    @Deployment(name = "proxy")
    public static WebArchive createProxy() {
        
        MavenResolverSystem resolver = Maven.resolver();  
        resolver.loadPomFromFile("pom.xml");  
        File[] files = resolver.resolve("org.apache.httpcomponents:httpcore:4.4.4",
                "com.fasterxml.jackson.core:jackson-core:2.8.6",
                "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.8.6",
                "com.fasterxml.jackson.core:jackson-databind-2.8.6",
                "com.fasterxml.jackson.core:jackson-annotations:2.8.0",
                "org.yaml:snakeyaml:1.17",
                "commons-logging:commons-logging:1.2",
                "org.apache.commonscommons-lang3:3.4").withTransitivity().asFile();
        
        return ShrinkWrap.create(WebArchive.class, "proxy.war")
                .addClass(YamlToJsonConverterServlet.class)
                .addAsLibraries(files);
    }

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        // set base URI and port number to use for all requests
        serverUrl = System.getProperty("test.url");
        String protocol = DEFAULT_PROTOCOL;
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;

        if (serverUrl != null) {
            URL url = new URL(serverUrl);
            protocol = url.getProtocol();
            host = url.getHost();
            port = (url.getPort() == -1) ? DEFAULT_PORT : url.getPort();
        }

        RestAssured.baseURI = protocol + "://" + host;
        RestAssured.port = port;

        username = System.getProperty("test.user");
        password = System.getProperty("test.pwd");

        if (username != null && password != null) {
            RestAssured.authentication = RestAssured.basic(username, password);
            RestAssured.useRelaxedHTTPSValidation();
        }
        RestAssured.defaultParser = Parser.JSON;

        if (StringUtils.isBlank(serverUrl)) {
            serverUrl = DEFAULT_PROTOCOL + "://" + DEFAULT_HOST + ":" + DEFAULT_PORT;
        }
        headers.put(YamlToJsonConverterServlet.TCK_HEADER_SERVERURL, serverUrl);
        if (StringUtils.isNotBlank(username)) {
            headers.put(YamlToJsonConverterServlet.TCK_HEADER_USERNAME, username);
        }
        if (StringUtils.isNotBlank(password)) {
            headers.put(YamlToJsonConverterServlet.TCK_HEADER_PASSWORD, password);
        }
    }

    public ValidatableResponse callEndpoint(String type) {
        ValidatableResponse vr;
        if ("JSON".equals(type)) {
            vr = given().accept(APPLICATION_JSON).when().get("/openapi").then().statusCode(200);
        }
        else {
            vr = given().headers(headers).when().get("/proxy").then().statusCode(200);
        }
        return vr;
    }

    @DataProvider(name = "formatProvider")
    public Object[][] provide() throws Exception {
        return new Object[][] { { "JSON" }, { "YAML" } };
    }
}
