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

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class FilterTest extends AppTestBase {
    @Deployment(name = "airlinesFiltered")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "airlinesFiltered.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addPackages(true, "org.eclipse.microprofile.openapi.filter")
                .addAsManifestResource("openapi.yaml", "openapi.yaml")
                .addAsManifestResource("microprofile-config-filter.properties", "microprofile-config.properties");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterServer(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("servers", hasSize(2));
        vr.body("servers.url", hasSize(2));

        String url = "https://{username}.gigantic-server.com:{port}/{basePath}";
        String serverPath = "servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("filterServer - The production API server"));
        vr.body(serverPath + ".variables", aMapWithSize(4));
        vr.body(serverPath + ".variables.username.description", equalTo("Reviews of the app by users"));
        vr.body(serverPath + ".variables.username.default", equalTo("user1"));
        vr.body(serverPath + ".variables.username.enum", containsInAnyOrder("user1", "user2"));
        vr.body(serverPath + ".variables.port.description", equalTo("Booking data"));
        vr.body(serverPath + ".variables.port.default", equalTo("8443"));
        vr.body(serverPath + ".variables.user.description", equalTo("User data"));
        vr.body(serverPath + ".variables.user.default", equalTo("user"));
        vr.body(serverPath + ".variables.basePath.default", equalTo("v2"));

        url = "{protocol}://test-server.com";
        serverPath = "paths.'/reviews/{id}'.delete.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("filterServer - The production API server"));
        vr.body(serverPath + ".variables", aMapWithSize(1));
        vr.body(serverPath + ".variables.protocol.default", equalTo("https"));
        vr.body(serverPath + ".variables.protocol.enum", containsInAnyOrder("http", "https"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterPathItemEnsureOrder(String type) {
        ValidatableResponse vr = callEndpoint(type);
        //Ensure that the operationId set by filterOperation method was overridden by filterPathItem method, since 
        //specification states that ancestors must be invoked last.
        vr.body("paths.'/availability'.get.summary", equalTo("Retrieve all available flights"));
        vr.body("paths.'/availability'.get.operationId", equalTo("filterPathItemGetFlights"));
        vr.body("paths.'/bookings'.post.callbacks.'bookingCallback'.'http://localhost:9080/airlines/bookings'.get.description",
                equalTo("parent - Retrieve all bookings for current user"));
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterPathItemAddOperation(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/availability'.put.summary", equalTo("filterPathItem - added put operation"));
        vr.body("paths.'/availability'.put.responses.'200'.description", equalTo("filterPathItem - successfully put airlines"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterOperation(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/bookings/{id}'.get.summary", equalTo("filterOperation - Get a booking with ID"));
        vr.body("paths.'/bookings/{id}'.get.operationId", equalTo("getBookingById"));
        
        vr.body("paths.'/bookings/{id}'.get.tags", containsInAnyOrder("Reservations", "parent - Bookings"));
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterOpenAPI(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/bookings/{id}'.put.summary", equalTo("filterOpenAPI - Update a booking with ID"));
        vr.body("paths.'/bookings/{id}'.put.operationId", equalTo("updateBookingId"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterParameter(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String reviewParameters = "paths.'/user/login'.get.parameters";
        
        String username = reviewParameters + ".findAll { it.name == 'username' }";
        vr.body(username + ".in", both(hasSize(1)).and(contains("query")));
        vr.body(username + ".description", both(hasSize(1)).and(contains("filterParameter - The user name for login")));
        vr.body(username + ".required", both(hasSize(1)).and(contains(true)));
        vr.body(username + ".schema.type", both(hasSize(1)).and(contains("string")));
        
        //Parameter named 'password' should have been removed by filter
        vr.body(reviewParameters, hasSize(1));
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterRequestBody(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String endpoint = "paths.'/bookings'.post.requestBody";
        vr.body(endpoint + ".description", equalTo("filterRequestBody - Create a new booking with the provided information."));
        vr.body(endpoint + ".content", notNullValue());
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterSecurityScheme(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String booking = "components.securitySchemes.bookingSecurityScheme.";
        vr.body(booking + "type", equalTo("openIdConnect"));
        vr.body(booking + "description", equalTo("filterSecurityScheme - Security Scheme for booking resource"));
        vr.body(booking + "openIdConnectUrl", equalTo("http://openidconnect.com/testurl"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterLink(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String s = "paths.'/user/{id}'.get.responses.'200'.links.'User name'.";
        vr.body(s + "operationId", equalTo("getUserByName"));
        vr.body(s + "description", equalTo("filterLink - The username corresponding to provided user id"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterTag(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String tagsPath = "tags.find { it.name == '";
        String desc = "' }.description";
        vr.body(tagsPath + "user" + desc, equalTo("filterTag - Operations about user"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterHeader(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String maxRate = "components.headers.Max-Rate";
        vr.body(maxRate + ".description", equalTo("filterHeader - Maximum rate"));
        vr.body(maxRate + ".required", equalTo(true));
        vr.body(maxRate + ".deprecated", equalTo(true));
        vr.body(maxRate + ".allowEmptyValue", equalTo(true));
        vr.body(maxRate + ".style", equalTo("simple"));
        vr.body(maxRate + ".schema.type", equalTo("integer"));
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterAPIResponse(String type) {
        ValidatableResponse vr = callEndpoint(type);
        final String response201Path = "paths.'/streams'.post.responses.'201'";
        vr.body(response201Path + ".description", equalTo("filterAPIResponse - subscription successfully created"));
        String parentChild = "paths.'/reviews'.post.responses.'201'.content.'application/json'.schema.description";
        vr.body(parentChild, equalTo("parent - id of the new review"));
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterSchema(String type) {
        ValidatableResponse vr = callEndpoint(type);
        final String response201Path = "paths.'/streams'.post.responses.'201'";
        vr.body(response201Path + ".content.'application/json'.schema.description", equalTo("filterSchema - subscription information"));
    
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testFilterCallback(String type) {
        ValidatableResponse vr = callEndpoint(type);
        final String callbacksPath = "paths.'/streams'.post.callbacks.onData.'{$request.query.callbackUrl}/data'.post";
        vr.body(callbacksPath + ".description", equalTo("filterCallback - callback post operation"));
    }
}
