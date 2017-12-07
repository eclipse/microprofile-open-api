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
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.tck.utils.YamlToJsonConverterServlet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;

@RunWith(Arquillian.class)
public class EndpointTest {
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_YAML = "application/yaml";

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9080;

    private static ValidatableResponse vr;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
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
        return ShrinkWrap.create(WebArchive.class, "proxy.war")
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
    }

    @Deployment(name = "airlines")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "airlines.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addAsManifestResource("openapi.yaml", "openapi/openapi.yaml");
    }

    @Test
    @RunAsClient
    public void testVersion() {
        vr.body("openapi", equalTo("3.0.0"));
    }

    @Test
    @RunAsClient
    public void testInfo() {
        vr.body("info.title", equalTo("AirlinesRatingApp API"));
        vr.body("info.version", equalTo("1.0"));
        vr.body("info.termsOfService", equalTo("http://airlinesratingapp.com/terms"));
    }

    @Test
    @RunAsClient
    public void testContact() {
        vr.body("info.contact.name", equalTo("AirlinesRatingApp API Support"));
        vr.body("info.contact.url", equalTo("http://exampleurl.com/contact"));
        vr.body("info.contact.email", equalTo("techsupport@airlinesratingapp.com"));
    }

    @Test
    @RunAsClient
    public void testLicense() {
        vr.body("info.license.name", equalTo("Apache 2.0"));
        vr.body("info.license.url", equalTo("http://www.apache.org/licenses/LICENSE-2.0.html"));
    }

    @Test
    @RunAsClient
    public void testExternalDocumentation() {
        vr.body("externalDocs.description", equalTo("instructions for how to deploy this app"));
        vr.body("externalDocs.url", containsString("README.md"));
    }

    @Test
    @RunAsClient
    public void testServer() {
        vr.body("servers", hasSize(2));
        vr.body("servers.url", hasSize(2));

        String url = "https://{username}.gigantic-server.com:{port}/{basePath}";
        String serverPath = "servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("The production API server"));
        vr.body(serverPath + ".variables", aMapWithSize(4));
        vr.body(serverPath + ".variables.username.description", equalTo("Reviews of the app by users"));
        vr.body(serverPath + ".variables.username.default", equalTo("user1"));
        vr.body(serverPath + ".variables.username.enum", containsInAnyOrder("user1", "user2"));
        vr.body(serverPath + ".variables.port.description", equalTo("Booking data"));
        vr.body(serverPath + ".variables.port.default", equalTo("8443"));
        vr.body(serverPath + ".variables.user.description", equalTo("User data"));
        vr.body(serverPath + ".variables.user.default", equalTo("user"));
        vr.body(serverPath + ".variables.basePath.default", equalTo("v2"));

        url = "https://test-server.com:80/basePath";
        serverPath = "servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("The test API server"));

        // Testing @Servers and @Server defined on top of a class with @Server
        // defined in an operation
        // org.eclipse.microprofile.openapi.apps.airlines.resources.ReviewResource
        vr.body("paths.'/reviews/{id}'.delete.servers", hasSize(3));
        vr.body("paths.'/reviews/{id}'.delete.servers.url", hasSize(3));

        url = "https://gigantic-server.com:443";
        serverPath = "paths.'/reviews/{id}'.delete.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("Secure server"));

        url = "http://gigantic-server.com:80";
        serverPath = "paths.'/reviews/{id}'.delete.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("Unsecure server"));
        vr.body(serverPath + ".variables", either(not(empty())).or(notNullValue()));

        url = "{protocol}://test-server.com";
        serverPath = "paths.'/reviews/{id}'.delete.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("The production API server"));
        vr.body(serverPath + ".variables", aMapWithSize(1));
        vr.body(serverPath + ".variables.proxyPath.description", equalTo("Base path of the proxy"));
        vr.body(serverPath + ".variables.proxyPath.default", equalTo("http"));
        vr.body(serverPath + ".variables.proxyPath.enum", containsInAnyOrder("http", "https"));

        // Testing two @Server defined in an @operation annotation on a method
        // org.eclipse.microprofile.openapi.apps.airlines.resources.ReviewResource.createReview(Review)
        vr.body("paths.'/reviews'.post.servers", hasSize(2));
        vr.body("paths.'/reviews'.post.servers.url", hasSize(2));
        url = "localhost:9080/{proxyPath}/reviews/id";
        serverPath = "paths.'/reviews'.post.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("view of all the reviews"));
        vr.body(serverPath + ".variables", aMapWithSize(1));
        vr.body(serverPath + ".variables.proxyPath.description", equalTo("Base path of the proxy"));
        vr.body(serverPath + ".variables.proxyPath.default", equalTo("proxy"));
        url = "http://random.url/reviews";
        serverPath = "paths.'/reviews'.post.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("random text"));

        // Testing two @Server defined on top of a class
        // org.eclipse.microprofile.openapi.apps.airlines.resources.BookingResource
        vr.body("paths.'/bookings'.get.servers", hasSize(2));
        vr.body("paths.'/bookings'.get.servers.url", hasSize(2));

        url = "https://gigantic-server.com:80";
        serverPath = "paths.'/bookings'.get.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("Secure server"));
        vr.body(serverPath + ".variables", either(not(empty())).or(notNullValue()));

        url = "https://gigantic-server.com:443";
        serverPath = "paths.'/bookings'.get.servers.find { it.url == '" + url + "' }";
        vr.body(serverPath + ".description", equalTo("Unsecure server"));
        vr.body(serverPath + ".variables", either(not(empty())).or(notNullValue()));
    }

    @Test
    @RunAsClient
    public void testOperationAirlinesResource() {
        vr.body("paths.'/'.get.summary", equalTo("Retrieve all available airlines"));
        vr.body("paths.'/'.get.operationId", equalTo("getAirlines"));
    }

    @Test
    @RunAsClient
    public void testOperationAvailabilityResource() {
        vr.body("paths.'/availability'.get.summary", equalTo("Retrieve all available flights"));
        vr.body("paths.'/availability'.get.operationId", equalTo("getFlights"));
    }

    @Test
    @RunAsClient
    public void testOperationBookingResource() {
        vr.body("paths.'/bookings'.get.summary", equalTo("Retrieve all bookings for current user"));
        vr.body("paths.'/bookings'.get.operationId", equalTo("getAllBookings"));

        vr.body("paths.'/bookings'.post.summary", equalTo("Create a booking"));
        vr.body("paths.'/bookings'.post.description", equalTo("Create a new booking record with the booking information provided."));
        vr.body("paths.'/bookings'.post.operationId", equalTo("createBooking"));

        vr.body("paths.'/bookings/{id}'.get.summary", equalTo("Get a booking with ID"));
        vr.body("paths.'/bookings/{id}'.get.operationId", equalTo("getBookingById"));

        vr.body("paths.'/bookings/{id}'.put.summary", equalTo("Update a booking with ID"));
        vr.body("paths.'/bookings/{id}'.put.operationId", equalTo("updateBookingId"));

        vr.body("paths.'/bookings/{id}'.delete.summary", equalTo("Delete a booking with ID"));
        vr.body("paths.'/bookings/{id}'.delete.operationId", equalTo("deleteBookingById"));
    }

    @Test
    @RunAsClient
    public void testOperationReviewResource() {
        vr.body("paths.'/reviews'.get.summary", equalTo("get all the reviews"));
        vr.body("paths.'/reviews'.get.operationId", equalTo("getAllReviews"));

        vr.body("paths.'/reviews'.post.summary", equalTo("Create a Review"));
        vr.body("paths.'/reviews'.post.operationId", equalTo("createReview"));

        vr.body("paths.'/reviews/{id}'.get.summary", equalTo("Get a review with ID"));
        vr.body("paths.'/reviews/{id}'.get.operationId", equalTo("getReviewById"));

        vr.body("paths.'/reviews/{id}'.delete.summary", equalTo("Delete a Review with ID"));
        vr.body("paths.'/reviews/{id}'.delete.operationId", equalTo("deleteReview"));

        vr.body("paths.'/reviews/{user}'.get.summary", equalTo("Get all reviews by user"));
        vr.body("paths.'/reviews/{user}'.get.operationId", equalTo("getReviewByUser"));

        vr.body("paths.'/reviews/{airline}'.get.summary", equalTo("Get all reviews by airlines"));
        vr.body("paths.'/reviews/{airline}'.get.operationId", equalTo("getReviewByAirline"));

        vr.body("paths.'/reviews/{user}/{airlines}'.get.summary", equalTo("Get all reviews for an airline by User"));
        vr.body("paths.'/reviews/{user}/{airlines}'.get.operationId", equalTo("getReviewByAirlineAndUser"));
    }

    @Test
    @RunAsClient
    public void testOperationUserResource() {
        vr.body("paths.'/user'.post.summary", equalTo("Create user"));
        vr.body("paths.'/user'.post.description", equalTo("This can only be done by the logged in user."));
        vr.body("paths.'/user'.post.operationId", equalTo("createUser"));

        vr.body("paths.'/user/createWithArray'.post.summary", equalTo("Creates list of users with given input array"));
        vr.body("paths.'/user/createWithArray'.post.operationId", equalTo("createUsersFromArray"));

        vr.body("paths.'/user/createWithList'.post.summary", equalTo("Creates list of users with given input list"));
        vr.body("paths.'/user/createWithList'.post.operationId", equalTo("createUsersFromList"));

        vr.body("paths.'/user/{username}'.put.summary", equalTo("Update user"));
        vr.body("paths.'/user/{username}'.put.description", equalTo("This can only be done by the logged in user."));
        vr.body("paths.'/user/{username}'.put.operationId", equalTo("updateUser"));

        vr.body("paths.'/user/{username}'.delete.summary", equalTo("Delete user"));
        vr.body("paths.'/user/{username}'.delete.description", equalTo("This can only be done by the logged in user."));
        vr.body("paths.'/user/{username}'.delete.operationId", equalTo("deleteUser"));

        vr.body("paths.'/user/{username}'.get.summary", equalTo("Get user by user name"));
        vr.body("paths.'/user/{username}'.get.operationId", equalTo("getUserByUserName"));

        vr.body("paths.'/user/{id}'.get.summary", equalTo("Get user by id"));
        vr.body("paths.'/user/{id}'.get.operationId", equalTo("getUserById"));

        vr.body("paths.'/user/login'.get.summary", equalTo("Logs user into the system"));
        vr.body("paths.'/user/login'.get.operationId", equalTo("logInUser"));

        vr.body("paths.'/user/logout'.get.summary", equalTo("Logs out current logged in user session"));
        vr.body("paths.'/user/logout'.get.operationId", equalTo("logOutUser"));
    }

    @Test
    @RunAsClient
    public void testParameter() throws InterruptedException {
        testAvailabilityGetParamater(vr);
        testBookingIdMethods(vr);
        testReviewIdMethods(vr);
        testUserLoginMethods(vr);
    }

    private void testUserLoginMethods(ValidatableResponse vr2) {
        String reviewParameters = "paths.'/user/login'.get.parameters";
        vr.body(reviewParameters, hasSize(2));
        vr.body(reviewParameters + ".findAll { it }.name", hasItems("username", "password"));
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[] { "username", "The user name for login" });
        list.add(new String[] { "password", "The password for login in clear text" });

        for (int i = 0; i < list.size(); i++) {
            String currentParam = list.get(i)[0];
            String query = reviewParameters + ".findAll { it.name == '" + currentParam + "' }";

            vr.body(query + ".in", both(hasSize(1)).and(contains("query")));
            vr.body(query + ".description", both(hasSize(1)).and(contains(list.get(i)[1])));
            vr.body(query + ".required", both(hasSize(1)).and(contains(true)));
            vr.body(query + ".schema.type", both(hasSize(1)).and(contains("string")));
        }
    }

    private void testReviewIdMethods(ValidatableResponse vr2) {
        String reviewParameters = "paths.'/reviews/{id}'.get.parameters";
        vr.body(reviewParameters, hasSize(1));
        vr.body(reviewParameters + ".findAll { it }.name", contains("id"));
        vr.body(reviewParameters + ".findAll { it.name == 'id' }.in", both(hasSize(1)).and(contains("path")));
        // vr.body(reviewParameters + ".findAll { it.name == 'id' }.description", both(hasSize(1)).and(contains("ID of the booking")));
        vr.body(reviewParameters + ".findAll { it.name == 'id' }.required", both(hasSize(1)).and(contains(true)));
        vr.body(reviewParameters + ".findAll { it.name == 'id' }.content.'*/*'.schema.type", both(hasSize(1)).and(contains("integer")));
    }

    private void testBookingIdMethods(ValidatableResponse vr) {
        String bookingParameters = "paths.'/bookings/{id}'.%s.parameters";

        for (String method : new String[] { "get", "put", "delete" }) {
            bookingParameters = String.format(bookingParameters, method);

            vr.body(bookingParameters, hasSize(1));
            vr.body(bookingParameters + ".findAll { it }.name", contains("id"));

            vr.body(bookingParameters + ".findAll { it.name == 'id' }.in", both(hasSize(1)).and(contains("path")));
            // TODO
            vr.body(bookingParameters + ".findAll { it.name == 'id' }.description", both(hasSize(1)).and(contains("ID of the booking")));
            vr.body(bookingParameters + ".findAll { it.name == 'id' }.required", both(hasSize(1)).and(contains(true)));
            vr.body(bookingParameters + ".findAll { it.name == 'id' }.schema.type", both(hasSize(1)).and(contains("integer")));
        }
    }

    private void testAvailabilityGetParamater(ValidatableResponse vr) {
        String availabilityParameters = "paths.'/availability'.get.parameters";

        vr.body(availabilityParameters, hasSize(6));
        vr.body(availabilityParameters + ".findAll { it }.name",
                hasItems("departureDate", "airportFrom", "returningDate", "airportTo", "numberOfAdults", "numberOfChildren"));

        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[] { "departureDate", "Customer departure date" });
        list.add(new String[] { "airportFrom", "Airport the customer departs from" });
        list.add(new String[] { "returningDate", "Customer return date" });
        list.add(new String[] { "airportTo", "Airport the customer returns to" });
        list.add(new String[] { "numberOfAdults", "Number of adults on the flight" });
        list.add(new String[] { "numberOfChildren", "Number of children on the flight" });

        for (int i = 0; i < list.size(); i++) {
            String currentParam = list.get(i)[0];
            String query = availabilityParameters + ".findAll { it.name == '" + currentParam + "' }";

            vr.body(query + ".in", both(hasSize(1)).and(contains("query")));
            vr.body(query + ".description", both(hasSize(1)).and(contains(list.get(i)[1])));
            vr.body(query + ".required", both(hasSize(1)).and(contains(true)));
            vr.body(query + ".schema.type", both(hasSize(1)).and(contains("string")));
        }

        vr.body(availabilityParameters + ".findAll { it.name == 'numberOfAdults' }.schema.minimum", both(hasSize(1)).and(contains(0)));
        vr.body(availabilityParameters + ".findAll { it.name == 'numberOfChildren' }.schema.minimum", both(hasSize(1)).and(contains(0)));
    }

    @Test
    @RunAsClient
    public void testExplode() throws InterruptedException {
        String explode = "paths.'/user/{username}'.put.responses.'200'.content.'application/xml'.encoding.password.explode";
        vr.body(explode, equalTo(true));
        explode = "paths.'/user/{username}'.put.responses.'200'.content.'application/xml'.encoding.password.explode";
        vr.body(explode, equalTo(true));
    }

    @Test
    @RunAsClient
    public void testSecurityRequirement() throws InterruptedException {
        vr.body("security.airlinesRatingApp_auth[0][0]", equalTo(null));

        vr.body("paths.'/reviews'.post.security.reviewoauth2[0][0]", equalTo("write:reviews"));
        vr.body("paths.'/reviews'.post.security.reviewoauth2", hasSize(1));

        vr.body("paths.'/user'.post.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user'.post.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/createWithArray'.post.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user/createWithArray'.post.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/createWithList'.post.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user/createWithList'.post.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/{username}'.get.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user/{username}'.get.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/{username}'.put.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user/{username}'.put.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/{username}'.delete.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user/{username}'.delete.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/{id}'.get.security.httpTestScheme[0][0]", equalTo("write:users"));
        vr.body("paths.'/user/{id}'.get.security.httpTestScheme", hasSize(1));

        vr.body("paths.'/user/{login}'.get.security.httpTestScheme[0][0]", equalTo(null));
        vr.body("paths.'/user/{logout}'.get.security.httpTestScheme[0][0]", equalTo(null));
    }

    @Test
    @RunAsClient
    public void testSecuritySchemesInComponents() throws InterruptedException {
        String s = "components.securitySchemes";
        vr.body(s, hasKey("httpTestScheme"));
        vr.body(s, hasKey("airlinesRatingApp_auth"));
        vr.body(s, hasKey("reviewoauth2"));

        vr.body(s + ".httpTestScheme.type", equalTo("http"));
        vr.body(s + ".httpTestScheme.description", equalTo("user security scheme"));
        vr.body(s + ".httpTestScheme.name", equalTo("httpTestScheme"));
        vr.body(s + ".httpTestScheme.scheme", equalTo("testScheme"));

        vr.body(s + ".airlinesRatingApp_auth.type", equalTo("apiKey"));
        vr.body(s + ".airlinesRatingApp_auth.description", equalTo("authentication needed to access Airlines app"));
        vr.body(s + ".airlinesRatingApp_auth.name", equalTo("airlinesRatingApp_auth"));
        vr.body(s + ".airlinesRatingApp_auth.in", equalTo("header"));

        vr.body(s + ".reviewoauth2.type", equalTo("oauth2"));
        vr.body(s + ".reviewoauth2.description", equalTo("authentication needed to create and delete reviews"));
        vr.body(s + ".reviewoauth2.name", equalTo("reviewoauth2"));

        String t = "components.securitySchemes.reviewoauth2.flows";
        vr.body(t + ".implicit.authorizationUrl", equalTo("https://example.com/api/oauth/dialog"));
        vr.body(t + ".implicit.scopes.'write:reviews'", equalTo("create a review"));
        vr.body(t + ".authorizationCode.authorizationUrl", equalTo("https://example.com/api/oauth/dialog"));
        vr.body(t + ".authorizationCode.tokenUrl", equalTo("https://example.com/api/oauth/token"));
    }
}
