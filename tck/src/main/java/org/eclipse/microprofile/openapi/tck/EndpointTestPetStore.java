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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;

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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@RunWith(Arquillian.class)
public class EndpointTestPetStore {
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_YAML = "application/yaml";

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9080;

    private static ValidatableResponse vr;

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
        RestAssured.defaultParser = Parser.JSON;
    }

    @Before
    public void setUpTest() {
        vr = given().when().get("/proxy").then().statusCode(200);
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
                .addAsLibraries(new File("./lib/commons-logging-1.2.jar"))
                .addAsLibraries(new File("./lib/commons-lang3-3.4.jar"));
        return war;
    }

    @Deployment(name = "petstore")
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "petstore.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.petstore")
                .addAsManifestResource(EmptyAsset.INSTANCE, "/openapi/openapi.yaml");
        return war;
    }

    @Test
    @RunAsClient
    public void testVersion() throws InterruptedException {
        vr.body("openapi", equalTo("3.0.0"));
    }

    @Test
    @RunAsClient
    public void testSchema() throws InterruptedException {
        // Schema annotation tests
        Response response = given().when().get("/proxy");
        System.out.println("response=" + response.asString());
        ValidatableResponse vr = response.then().parser("", Parser.JSON).statusCode(200);
        
        // Basic properties
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.default", equalTo("Dog"));  // default val
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.enum", hasItems("Cat", "Dog", "Lizard")); // enumeration 
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.externalDocs.description", equalTo("Pet Types")); // ext doc
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.deprecated", equalTo(true)); // deprecated
        vr.body("paths.'/store/order/{orderId}'.get.responses.'900'.schema", nullValue()); // hidden
        
        // Numerical properties
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.maximum", equalTo(101)); // maximum
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.exclusiveMaximum", equalTo(true)); // exclusive max
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.minimum", equalTo(9)); // minimum
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.exclusiveMinimum", equalTo(true)); // exclusive min
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.multipleOf", equalTo(10)); // multiple of
        
        //  String properties
        vr.body("paths.'/pet/{petId}'.delete.parameters.find{ it.name == 'apiKey' }.schema.maxLength", equalTo(256)); // max length
        vr.body("paths.'/pet/{petId}'.delete.parameters.find{ it.name == 'apiKey' }.schema.minLength", equalTo(32)); // min length

        // Other properties
        String storeOrderResponses = "paths.'/store/order/{orderId}'.get.responses";
        vr.body("paths.'/pet/{petId}'.get.responses.'200'.content.'application/json'.schema.readonly", equalTo(true)); //  readonly
        vr.body(storeOrderResponses + ".'404'.content.'application/json'.schema.anyOf", hasItems("Order.class", "BadOrder.class")); //    any of
        vr.body(storeOrderResponses + ".'200'.content.'application/json'.schema.allOf", hasItems("Order.class", "Pet.class")); //    all of
        vr.body("paths.'/pet/{petId}'.get.responses.'200'.content.'application/json'.schema.oneOf", 
                hasItems("Cat.class", "Dog.class", "Lizard.class")); //    one of
        vr.body(storeOrderResponses + ".'200'.content.'application/json'.schema.not", equalTo("BadOrder.class")); //    not
        vr.body(storeOrderResponses + ".'404'.content.'application/json'.schema.discriminatorProperty", equalTo("id")); //    discrimination prop
        vr.body(storeOrderResponses + ".'404'.content.'application/json'.schema.discriminatorMapping", hasEntry("0", "BadOrder.class")); // mapping
    }
}
