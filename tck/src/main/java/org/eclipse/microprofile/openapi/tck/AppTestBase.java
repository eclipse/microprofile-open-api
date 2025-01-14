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

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.tck.utils.YamlToJsonFilter;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public abstract class AppTestBase extends Arquillian {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9080;

    protected static final Filter YAML_FILTER = new YamlToJsonFilter();

    @BeforeClass
    public void configureRestAssured() throws MalformedURLException {
        // set base URI and port number to use for all requests
        String serverUrl = System.getProperty("test.url");
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

        String username = System.getProperty("test.user");
        String password = System.getProperty("test.pwd");

        if (username != null && password != null) {
            RestAssured.authentication = RestAssured.basic(username, password);
            RestAssured.useRelaxedHTTPSValidation();
        }
        RestAssured.defaultParser = Parser.JSON;

        if (StringUtils.isBlank(serverUrl)) {
            serverUrl = DEFAULT_PROTOCOL + "://" + DEFAULT_HOST + ":" + DEFAULT_PORT;
        }
    }

    public ValidatableResponse callEndpoint(String type) {
        ValidatableResponse vr;
        if ("JSON".equals(type)) {
            vr = given().accept(ContentType.JSON).when().get("/openapi").then().statusCode(200);
        } else {
            // It seems there is no standard for YAML
            vr = given().filter(YAML_FILTER).accept(ContentType.ANY).when().get("/openapi").then().statusCode(200);
        }
        return vr;
    }

    /**
     * Lookup the object at the provided path in the response and if the object is a reference (contains a $ref
     * property), return the reference path. If the object is not a reference, return the input path.
     *
     * @param vr
     *            the response
     * @param path
     *            a path which may be a reference object (containing a $ref)
     * @return the path the object references if present, else the input path
     */
    public static String dereference(ValidatableResponse vr, String path) {
        ExtractableResponse<Response> response = vr.extract();
        String ref = response.path(path + ".$ref");

        if (ref != null) {
            return ref.replaceFirst("^#/?", "").replace('/', '.');
        } else {
            return path;
        }
    }

    /**
     * Builds an absolute path using the series of provided relative {@code paths} by sequentially searching through the
     * provided {@link ValidatableResponse}. Each time a {@code $ref} is encountered as a key in the path node, it is
     * treated as an absolute (within the response) path that is used as the context for the next {@code paths} entry.
     *
     * <p>
     * Consider the following simple schemas as the response content of the {@link ValidatableResponse} argument:
     *
     * <pre>
     * {
     *   "components": {
     *     "schemas": {
     *       "Person": {
     *         "type": "object",
     *         "properties": {
     *           "firstName": {
     *             "type": "string"
     *           },
     *           "lastName": {
     *             "type": "string"
     *           }
     *         }
     *       },
     *       "Employee": {
     *         "$ref": "#/components/schemas/Person"
     *       }
     *     }
     *   }
     * }
     * </pre>
     *
     * Given the {@code paths} {@code "components.schemas.Employee"} and {@code "properties.firstName"}, the value
     * returned is the absolute path {@code "components.schemas.Person.properties.firstName"}. The path segment
     * {@code "components.schemas.Employee"} contains a {@code .$ref} which itself resolves to the schema
     * {@code "components.schemas.Person"} and the second path segment is appended to the resolved reference.
     *
     * Note that this method does not currently support the conversion of Json Pointer escape sequences ({@code ~0} and
     * {@code ~1}) in {@code $ref} values.
     *
     * @param vr
     *            the response
     * @param paths
     *            paths which may be a reference object (containing a $ref)
     * @return the path the object references if present, else the input path
     */
    public static String dereference(ValidatableResponse vr, String... paths) {
        ExtractableResponse<Response> response = vr.extract();
        String context = "";
        StringBuilder lookup = new StringBuilder();
        StringBuilder absolutePath = new StringBuilder();

        for (String path : paths) {
            lookup.setLength(0);

            if (!context.isEmpty()) {
                lookup.append(context);
                lookup.append('.');
            }

            lookup.append(path);
            lookup.append(".$ref");

            String ref = response.path(lookup.toString());

            if (ref != null) {
                absolutePath.setLength(0);
                absolutePath.append(ref.replaceFirst("^#/?", "").replace('/', '.'));
            } else {
                // No $ref, keep appending
                if (absolutePath.length() > 0) {
                    absolutePath.append('.');
                }
                absolutePath.append(path);
            }

            context = absolutePath.toString();
        }

        return absolutePath.toString();
    }

    @DataProvider(name = "formatProvider")
    public Object[][] provide() {
        return new Object[][]{{"JSON"}, {"YAML"}};
    }
}
