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

import static org.eclipse.microprofile.openapi.tck.utils.TCKMatchers.itemOrSingleton;
import static org.eclipse.microprofile.openapi.tck.utils.TCKMatchers.number;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class ModelReaderAppTest extends AppTestBase {
    @Deployment(name = "airlinesModelReader", testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "airlinesReader.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addPackages(true, "org.eclipse.microprofile.openapi.reader")
                .addAsManifestResource("microprofile-reader.properties", "microprofile-config.properties");
    }

    @Test(dataProvider = "formatProvider")
    public void testVersion(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("openapi", startsWith("3.1."));
    }

    @Test(dataProvider = "formatProvider")
    public void testInfo(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("info.title", equalTo("AirlinesRatingApp API"));
        vr.body("info.version", equalTo("1.0"));
        vr.body("info.summary", equalTo("An API for an Airline application"));
        vr.body("info.termsOfService", equalTo("http://airlinesratingapp.com/terms"));
    }

    @Test(dataProvider = "formatProvider")
    public void testContact(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("info.contact.name", equalTo("AirlinesRatingApp API Support"));
        vr.body("info.contact.url", equalTo("http://exampleurl.com/contact"));
        vr.body("info.contact.email", equalTo("techsupport@airlinesratingapp.com"));
    }

    @Test(dataProvider = "formatProvider")
    public void testLicense(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("info.license.name", equalTo("Apache 2.0"));
        vr.body("info.license.url", equalTo("http://www.apache.org/licenses/LICENSE-2.0.html"));
    }

    @Test(dataProvider = "formatProvider")
    public void testExternalDocumentation(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("externalDocs.description", equalTo("instructions for how to deploy this app"));
        vr.body("externalDocs.url", containsString("README.md"));
    }

    @Test(dataProvider = "formatProvider")
    public void testServer(String type) {
        ValidatableResponse vr = callEndpoint(type);
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

    }

    @Test(dataProvider = "formatProvider")
    public void testOperationAirlinesResource(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/modelReader/airlines'.get.summary", equalTo("Retrieve all available airlines"));
        vr.body("paths.'/modelReader/airlines'.get.operationId", equalTo("getAirlines"));
    }

    @Test(dataProvider = "formatProvider")
    public void testOperationAvailabilityResource(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/availability'.get.summary", equalTo("Retrieve all available flights"));
        vr.body("paths.'/availability'.get.operationId", equalTo("getFlights"));
    }

    @Test(dataProvider = "formatProvider")
    public void testOperationBookingResource(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/modelReader/bookings'.get.summary", equalTo("Retrieve all bookings for current user"));
        vr.body("paths.'/modelReader/bookings'.get.operationId", equalTo("getAllBookings"));

        vr.body("paths.'/modelReader/bookings'.post.summary", equalTo("Create a booking"));
        vr.body("paths.'/modelReader/bookings'.post.description",
                equalTo("Create a new booking record with the booking information provided."));
        vr.body("paths.'/modelReader/bookings'.post.operationId", equalTo("createBooking"));
    }

    @Test(dataProvider = "formatProvider")
    public void testAPIResponse(String type) {
        ValidatableResponse vr = callEndpoint(type);
        // @APIResponse at method level
        vr.body("paths.'/availability'.get.responses", aMapWithSize(2));
        vr.body("paths.'/availability'.get.responses.'200'.description", equalTo("successful operation"));
        vr.body("paths.'/availability'.get.responses.'404'.description", equalTo("No available flights found"));
    }

    @Test(dataProvider = "formatProvider")
    public void testAvailabilityGetParameter(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String availabilityParameters = "paths.'/availabilityModel'.get.parameters";

        vr.body(availabilityParameters, hasSize(6));
        vr.body(availabilityParameters + ".findAll { it }.name",
                hasItems("airportFrom", "returningDate", "airportTo", "numberOfAdults", "numberOfChildren"));

        vr.body(availabilityParameters + ".findAll { it.$ref == '#/components/parameters/departureDate'}",
                notNullValue());

        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[]{"airportFrom", "Airport the customer departs from"});
        list.add(new String[]{"returningDate", "Customer return date"});
        list.add(new String[]{"airportTo", "Airport the customer returns to"});
        list.add(new String[]{"numberOfAdults", "Number of adults on the flight"});
        list.add(new String[]{"numberOfChildren", "Number of children on the flight"});

        for (int i = 0; i < list.size(); i++) {
            String currentParam = list.get(i)[0];
            String query = availabilityParameters + ".findAll { it.name == '" + currentParam + "' }";

            vr.body(query + ".in", both(hasSize(1)).and(contains("query")));
            vr.body(query + ".description", both(hasSize(1)).and(contains(list.get(i)[1])));
            vr.body(query + ".required", both(hasSize(1)).and(contains(true)));
            vr.body(query + ".schema.type", both(hasSize(1)).and(contains(itemOrSingleton("string"))));
        }

        vr.body(availabilityParameters + ".findAll { it.name == 'numberOfAdults' }.schema.minimum",
                both(hasSize(1)).and(contains(0)));
        vr.body(availabilityParameters + ".findAll { it.name == 'numberOfChildren' }.schema.minimum",
                both(hasSize(1)).and(contains(0)));
    }

    @Test(dataProvider = "formatProvider")
    public void testSecurityRequirement(String type) {
        ValidatableResponse vr = callEndpoint(type);

        vr.body("paths.'/bookings'.post.security.bookingSecurityScheme[0][0]", equalTo("write:bookings"));
        vr.body("paths.'/bookings'.post.security.bookingSecurityScheme[0][1]", equalTo("read:bookings"));
        vr.body("paths.'/reviews'.post.security.bookingSecurityScheme", hasSize(1));
        vr.body("paths.'/bookings'.post.security.bookingSecurityScheme[0]", hasSize(2));
    }

    @Test(dataProvider = "formatProvider")
    public void testSecuritySchemes(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String s = "components.securitySchemes";
        vr.body(s, hasKey("httpTestScheme"));
    }

    @Test(dataProvider = "formatProvider")
    public void testSecurityScheme(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String http = "components.securitySchemes.httpTestScheme.";
        vr.body(http + "type", equalTo("http"));
        vr.body(http + "description", equalTo("user security scheme"));
        vr.body(http + "scheme", equalTo("testScheme"));
    }

    @Test(dataProvider = "formatProvider")
    public void testSchema(String type) {
        ValidatableResponse vr = callEndpoint(type);
        // Basic properties
        vr.body("components.schemas.AirlinesRef.$ref", equalTo("#/components/schemas/Airlines"));
        vr.body("components.schemas.Airlines.title", equalTo("Airlines"));
        vr.body("paths.'/modelReader/bookings'.post.responses.'201'.content.'text/plain'.schema.type",
                itemOrSingleton("string"));
        vr.body("components.schemas.id.format", equalTo("int32"));
        vr.body("paths.'/modelReader/bookings'.post.responses.'201'.content.'text/plain'.schema.description",
                equalTo("id of the new booking"));
    }

    @Test(dataProvider = "formatProvider")
    public void testSchemaCustomProperties(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("components.schemas.custom.$schema", equalTo("http://example.com/myCustomSchema"));
        vr.body("components.schemas.custom.shortKey", number(1));
        vr.body("components.schemas.custom.intKey", number(2));
        vr.body("components.schemas.custom.longKey", number(3));
        vr.body("components.schemas.custom.booleanKey", equalTo(true));
        vr.body("components.schemas.custom.charKey", equalTo("a"));
        vr.body("components.schemas.custom.stringKey", equalTo("string"));
        vr.body("components.schemas.custom.floatKey", number(3.5));
        vr.body("components.schemas.custom.doubleKey", number(3.5));
        vr.body("components.schemas.custom.bigDecimalKey", number(3.5));
        vr.body("components.schemas.custom.bigIntegerKey", number(7));
        vr.body("components.schemas.custom.extDocKey.description", equalTo("test"));
        vr.body("components.schemas.custom.operationKey.description", equalTo("test"));
        vr.body("components.schemas.custom.pathItemKey.description", equalTo("test"));
        vr.body("components.schemas.custom.pathsKey.test.description", equalTo("test"));
        vr.body("components.schemas.custom.callbackKey.test.description", equalTo("test"));
        vr.body("components.schemas.custom.exampleKey.value", equalTo("test"));
        vr.body("components.schemas.custom.headerKey.description", equalTo("test"));
        vr.body("components.schemas.custom.contactKey.name", equalTo("test"));
        vr.body("components.schemas.custom.infoKey.title", equalTo("test"));
        vr.body("components.schemas.custom.licenseKey.name", equalTo("test"));
        vr.body("components.schemas.custom.linkKey.operationId", equalTo("getTestFlights"));
        vr.body("components.schemas.custom.contentKey.test.example", equalTo("test"));
        vr.body("components.schemas.custom.discriminatorKey.propertyName", equalTo("test"));
        vr.body("components.schemas.custom.schemaKey.title", equalTo("test"));
        vr.body("components.schemas.custom.xmlKey.name", equalTo("test"));
        vr.body("components.schemas.custom.parameterKey.name", equalTo("test"));
        vr.body("components.schemas.custom.requestBodyKey.content.test.example", equalTo("test"));
        vr.body("components.schemas.custom.apiResponseKey.description", equalTo("test"));
        vr.body("components.schemas.custom.apiResponsesKey.200.description", equalTo("test"));
        vr.body("components.schemas.custom.oAuthFlowKey.authorizationUrl", equalTo("http://example.com"));
        vr.body("components.schemas.custom.oAuthFlowsKey.implicit.authorizationUrl", equalTo("http://example.com"));
        vr.body("components.schemas.custom.securityReqKey.test", empty());
        vr.body("components.schemas.custom.securitySchemeKey.type", equalTo("http"));
        vr.body("components.schemas.custom.serverKey.url", equalTo("http://example.com"));
        vr.body("components.schemas.custom.serverVarKey.default", equalTo("test"));
        vr.body("components.schemas.custom.tagKey.name", equalTo("test"));
        vr.body("components.schemas.custom.enumKey", equalToIgnoringCase("MONDAY"));
        vr.body("components.schemas.custom.listKey", hasItem("test"));
        vr.body("components.schemas.custom.listKey[1].name", equalTo("test"));
        vr.body("components.schemas.custom.mapKey.test", equalToIgnoringCase("THURSDAY"));
    }

    @Test(dataProvider = "formatProvider")
    public void testExampleObject(String type) {
        ValidatableResponse vr = callEndpoint(type);
        // Example in Components
        vr.body("components.examples.review.summary", equalTo("External review example"));
        vr.body("components.examples.review.description", equalTo("This example exemplifies the content on our site."));
        vr.body("components.examples.review.externalValue", equalTo("http://foo.bar/examples/review-example.json"));
    }

    @Test(dataProvider = "formatProvider")
    public void testTagDeclarations(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String tagsPath = "tags.find { it.name == '";
        String desc = "' }.description";
        vr.body(tagsPath + "user" + desc, equalTo("Operations about user"));
        vr.body(tagsPath + "create" + desc, equalTo("Operations about create"));
        vr.body(tagsPath + "Airlines" + desc, equalTo("All the airlines methods"));
        vr.body(tagsPath + "Availability" + desc, equalTo("All the availability methods"));
        vr.body(tagsPath + "Get Flights" + desc, equalTo("method to retrieve all flights available"));
        vr.body(tagsPath + "Get Flights" + "' }.externalDocs.description",
                equalTo("A list of all the flights offered by the app"));
        vr.body(tagsPath + "Get Flights" + "' }.externalDocs.url", equalTo("http://airlinesratingapp.com/ourflights"));
        vr.body(tagsPath + "Bookings" + desc, equalTo("All the bookings methods"));
        vr.body(tagsPath + "Get Airlines" + desc, equalTo("method to get all airlines"));
        vr.body(tagsPath + "Retrieve Airlines" + desc, equalTo("method to retrieve all airlines"));
    }

    @Test(dataProvider = "formatProvider")
    public void testTagsInOperations(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("paths.'/availability'.get.tags", containsInAnyOrder("Get Flights", "Availability"));
        vr.body("paths.'/modelReader/bookings'.get.tags", containsInAnyOrder("bookings"));
    }

    @Test(dataProvider = "formatProvider")
    public void testComponents(String type) {
        ValidatableResponse vr = callEndpoint(type);

        // Tests to ensure that the reusable items declared using the
        // @Components annotation (within @OpenAPIDefinition) exist.
        // Each of these item types are tested elsewhere, so no need to test the
        // content of them here.
        vr.body("components.schemas.Bookings", notNullValue());
        vr.body("components.schemas.Airlines", notNullValue());
        vr.body("components.schemas.AirlinesRef", notNullValue());
        vr.body("components.responses.FoundAirlines", notNullValue());
        vr.body("components.responses.FoundBookings", notNullValue());
        vr.body("components.parameters.departureDate", notNullValue());
        vr.body("components.parameters.username", notNullValue());
        vr.body("components.examples.review", notNullValue());
        vr.body("components.examples.user", notNullValue());
        vr.body("components.requestBodies.review", notNullValue());
        vr.body("components.headers.Max-Rate", notNullValue());
        vr.body("components.headers.Request-Limit", notNullValue());
        vr.body("components.securitySchemes.httpTestScheme", notNullValue());
        vr.body("components.links.UserName", notNullValue());
    }

    @Test(dataProvider = "formatProvider")
    public void testHeaderInComponents(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String maxRate = "components.headers.Max-Rate";
        vr.body(maxRate + ".description", equalTo("Maximum rate"));
        vr.body(maxRate + ".required", equalTo(true));
        vr.body(maxRate + ".deprecated", equalTo(true));
        vr.body(maxRate + ".allowEmptyValue", equalTo(true));
    }

    @Test(dataProvider = "formatProvider")
    public void testContentInAPIResponse(String type) {
        ValidatableResponse vr = callEndpoint(type);

        String content1 = "paths.'/availability'.get.responses.'200'.content.'application/json'";
        vr.body(content1, notNullValue());
        vr.body(content1 + ".schema.type", itemOrSingleton("array"));
        vr.body(content1 + ".schema.items", notNullValue());
    }
}
