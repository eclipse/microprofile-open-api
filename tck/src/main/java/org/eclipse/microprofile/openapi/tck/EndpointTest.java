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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.microprofile.openapi.tck.utils.YamlToJsonConverterServlet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

@RunWith(Arquillian.class)
public class EndpointTest {
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_YAML = "application/yaml";

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9080;

    @BeforeClass
    static public void setup() throws MalformedURLException {
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

        String userName = System.getProperty("test.user");
        String password = System.getProperty("test.pwd");

        if (userName != null && password != null) {
            RestAssured.authentication = RestAssured.basic(userName, password);
            RestAssured.useRelaxedHTTPSValidation();
        }
    }

    @Deployment(name = "proxy")
    public static WebArchive createProxy() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "proxy.war")
                .addClass(YamlToJsonConverterServlet.class)
                .addAsLibraries(new File("./lib/httpclient-4.5.2.jar"))
                .addAsLibraries(new File("./lib/httpcore-4.4.4.jar"))
                .addAsLibraries(new File("./lib/jackson-core-2.9.2.jar"))
                .addAsLibraries(new File("./lib/jackson-dataformat-yaml-2.9.2.jar"))
                .addAsLibraries(new File("./lib/jackson-databind-2.9.2.jar"))
                .addAsLibraries(new File("./lib/jackson-annotations-2.9.1.jar"))
                .addAsLibraries(new File("./lib/snakeyaml-1.18.jar"))
                .addAsLibraries(new File("./lib/commons-logging-1.2.jar"));
        System.out.println(war.toString(true));
        return war;
    }

    @Deployment(name = "airlines")
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "airlines.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addAsManifestResource(EmptyAsset.INSTANCE, "/openapi/openapi.yaml");
        System.out.println(war.toString(true));
        return war;
    }

    @Test
    @RunAsClient
    public void testVersion() throws InterruptedException {
        Response response = given().when().get("/proxy");
        response.then().parser("", Parser.JSON).statusCode(200).body("openapi", equalTo("3.0.0")).body("info.description", containsString("Liberty"));
    }
}
