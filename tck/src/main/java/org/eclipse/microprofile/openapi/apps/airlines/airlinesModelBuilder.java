package org.eclipse.microprofile.openapi.apps.airlines;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.OASFactory;

import org.eclipse.microprofile.openapi.apps.airlines.integration.OpenAPIConfiguration;
import org.eclipse.microprofile.openapi.models.callbacks.Callback;
import org.eclipse.microprofile.openapi.models.examples.Example;
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.info.Contact;
import org.eclipse.microprofile.openapi.models.info.License;
import org.eclipse.microprofile.openapi.models.links.Link;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.tags.Tag;
import org.eclipse.microprofile.openapi.models.ExternalDocumentation;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.Components;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Paths;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.MediaType;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.servers.ServerVariables;
import org.eclipse.microprofile.openapi.models.servers.ServerVariable;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.responses.APIResponses;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;

public class AirlinesModelBuilder implements OpenAPIConfiguration {

    @Override
    public OpenAPI getOpenAPI() {
        return OASFactory.createObject(OpenAPI.class)
                .info(OASFactory.createObject(Info.class)
                    .title("AirlinesRatingApp API")
                    .version("1.0")
                    .termsOfService("http://airlinesratingapp.com/terms")
                    .contact(OASFactory.createObject(Contact.class)
                        .name("AirlinesRatingApp API Support")
                        .url("http://exampleurl.com/contact")
                        .email("techsupport@airlinesratingapp.com"))
                    .license(OASFactory.createObject(License.class)
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                //.security(new ArrayList<SecurityRequirements>())
                .addSecurityRequirement(OASFactory.createObject(SecurityRequirement.class)
                    .addScheme("airlinesRatingApp_auth"))
                //.servers(new ArrayList<Server>())
                .addServer(OASFactory.createObject(Server.class)
                    .url("https://{username}.gigantic-server.com:{port}/{basePath}")
                    .description("The production API server")
                    .variables(OASFactory.createObject(ServerVariables.class)
                        .addServerVariable("username", OASFactory.createObject(ServerVariable.class)
                            .defaultValue("user1")
                            .description("Reviews of the app by users")
                            .enumeration(new ArrayList<String>())
                                .addEnumeration("user1")
                                .addEnumeration("user2"))
                        .addServerVariable("port", OASFactory.createObject(ServerVariable.class)
                            .defaultValue("8443")
                            .description("Booking data"))
                        .addServerVariable("user", OASFactory.createObject(ServerVariable.class)
                            .defaultValue("user")
                            .description("User data"))
                        .addServerVariable("basePath", OASFactory.createObject(ServerVariable.class)
                            .defaultValue("v2")))
                .addServer(OASFactory.createObject(Server.class)
                    .url("https://test-server.com:80/basePath")
                    .description("The test API server"))
                .components(OASFactory.createObject(Components.class)
                    .schemas(new HashMap<String, Schema>())
                        .addSchema("id", OASFactory.createObject(Schema.class)
                            .type(SchemaType.INTEGER)
                            .title("int32"))
                    .responses(new HashMap<String, APIResponse>())
                        .addResponse("200", OASFactory.createObject(APIResponse.class)
                            .description("successfully found airlines")
                            .content(OASFactory.createObject(Content.class)
                                .addMediaType("application/json", OASFactory.createObject(MediaType.class)
                                    .schema(OASFactory.createObject(Schema.class)
                                        .type(SchemaType.ARRAY)))))
                        .addResponse("200", OASFactory.createObject(APIResponse.class)
                            .description("successfully found airlines")
                            .content(OASFactory.createObject(Content.class)
                                .addMediaType("application/json", OASFactory.createObject(MediaType.class)
                                    .schema(OASFactory.createObject(Schema.class)
                                        .type(SchemaType.ARRAY)))))
                    .parameters(new HashMap<String, Parameter>())
                        .addParameter("departureDate", OASFactory.createObject(Parameter.class)
                            .required(true)
                            .description("Customer departure date")
                            .schema(OASFactory.createObject(Schema.class)))
                        .addParameter("username", OASFactory.createObject(Parameter.class)
                            .required(true)
                            .description("The name that needs to be deleted")
                            .schema(OASFactory.createObject(Schema.class)))
                    .examples(new HashMap<String, Example>())
                        .addExample("review", OASFactory.createObject(Example.class)
                            .summary("External review example")
                            .externalValue("http://foo.bar/examples/review-example.json"))
                        .addExample("user", OASFactory.createObject(Example.class)
                            .summary("External user example")
                            .externalValue("http://foo.bar/examples/user-example.json"))
                    .requestBodies(new HashMap<String, RequestBody>())
                        .addRequestBody("review", OASFactory.createObject(RequestBody.class)
                            .required(true)
                            .description("example review to add")
                            .content(OASFactory.createObject(Content.class)
                                .addMediaType("application/json", OASFactory.createObject(MediaType.class)
                                    .schema(OASFactory.createObject(Schema.class)))))
                    .headers(new HashMap<String, Header>())
                        .addHeader("Max-Rate", OASFactory.createObject(Header.class)
                            .description("Maximum rate")
                            .schema(OASFactory.createObject(Schema.class)
                                .type(SchemaType.INTEGER))
                            .required(true)
                            .allowEmptyValue(true)
                            .deprecated(true))
                        .addHeader("Request-Limit", OASFactory.createObject(Header.class)
                            .description("The number of allowed requests in the current period")
                            .schema(OASFactory.createObject(Schema.class)
                                .type(SchemaType.INTEGER)))
                    .securitySchemes(new HashMap<String, SecurityScheme>())
                        .addSecurityScheme("httpTestScheme", OASFactory.createObject(SecurityScheme.class)
                            .description("user security scheme")
                            .type(SecuritySchemeType.HTTP)
                            .scheme("testScheme"))
                    .links(new HashMap<String, Link>())
                        .addLink("UserName", OASFactory.createObject(Link.class)
                            .description()
                            .operationId()
                            .parameters(new HashMap<String, Parameter>())
                                .addParameter("userId", OASFactory.createObject(Parameter.class)))
                    .callbacks(new HashMap<String, Callback>())
                        .addCallbacks("GetBookings", OASFactory.createObject(Callback.class)
                            .addPathItem("http://localhost:9080/airlines/bookings", OASFactory.createObject(PathItem.class)
                                .summary("Retrieve all bookings for current user")))
                .tags(new ArrayList<Tag>())
                    .addTag(OASFactory.createObject(Tag.class)
                        .name("Get Airlines")
                        .description("method to get all airlines"))
                    .addTag(OASFactory.createObject(Tag.class)
                        .name("Retrieve Airlines")
                        .description("method to retrieve all airlines"))
                .paths(OASFactory.createObject(Paths.class)
                    .addPathItem("/airlines", OASFactory.createObject(PathItem.class)
                        .GET(OASFactory.createObject(Operation.class)
                            .summary("Retrieve all available airlines")
                            .operationId("getAirlines")
                            .responses(OASFactory.createObject(APIResponses.class)
                                .addApiResponse("404", OASFactory.createObject(APIResponse.class)
                                    .description("No airlines found")
                                    .content(OASFactory.createObject(Content.class)
                                        .addMediaType("n/a", OASFactory.createObject(MediaType.class)))))))
                    .addPathItem("/availability", OASFactory.createObject(PathItem.class)
                        .GET(OASFactory.createObject(Operation.class)
                            .tags(new ArrayList<Tag>())
                                .addTag(OASFactory.createObject(Tag.class)
                                    .name("Get Flights")
                                    .description("method to retrieve all flights available")
                                    .externalDocs(OASFactory.createObject(ExternalDocumentation.class)
                                        .description( "A list of all the flights offered by the app")
                                        .url("http://airlinesratingapp.com/ourflights")))
                            .summary("Retrieve all available flights")
                            .operationId("getFlights")
                            .responses(OASFactory.createObject(APIResponses.class)
                                .addApiResponse("200", OASFactory.createObject(APIResponse.class)
                                    .description("successful operation")
                                    .content(OASFactory.createObject(Content.class)
                                        .addMediaType("applictaion/json", OASFactory.createObject(MediaType.class)
                                            .schema(OASFactory.createObject(Schema.class)
                                                .type(SchemaType.ARRAY)))))
                                .addApiResponse("404", OASFactory.createObject(APIResponse.class)
                                    .description("No available flights found")
                                    .content(OASFactory.createObject(Content.class)
                                        .addMediaType("n/a", OASFactory.createObject(MediaType.class)))))
                            .parameters(new ArrayList<Parameter>())
                                .addParameter(OASFactory.createObject(Parameter.class)
                                    .name("airportFrom")
                                    .required(true)
                                    .in("query")
                                    .allowEmptyValue(true)
                                    .description("Airport the customer departs from")
                                    .schema(OASFactory.createObject(Schema.class)))
                                .addParameter(OASFactory.createObject(Parameter.class)
                                    .name("returningDate")
                                    .required(true)
                                    .in("query")
                                    .allowReserved(true)
                                    .description("Customer return date")
                                    .schema(OASFactory.createObject(Schema.class)))
                                .addParameter(OASFactory.createObject(Parameter.class)
                                    .name("airportTo")
                                    .required(true)
                                    .in("query")
                                    .description("Airport the customer returns to")
                                    .schema(OASFactory.createObject(Schema.class)))
                                .addParameter(OASFactory.createObject(Parameter.class)
                                    .name("numberOfAdults")
                                    .required(true)
                                    .in("query")
                                    .description("Number of adults on the flight")
                                    .schema(OASFactory.createObject(Schema.class)
                                        .minimum(new BigDecimal(0))))
                                .addParameter(OASFactory.createObject(Parameter.class)
                                    .name("numberOfChildren")
                                    .required(true)
                                    .deprecated(true)
                                    .in("query")
                                    .description("Number of children on the flight")
                                    .schema(OASFactory.createObject(Schema.class)
                                        .minimum(new BigDecimal(0))))))
                    .addPathItem("/bookings", OASFactory.createObject(PathItem.class)
                        .GET(OASFactory.createObject(Operation.class)
                            .tags(new ArrayList<Tag>())
                                .addTag(OASFactory.createObject(Tag.class))
                            .summary("Retrieve all bookings for current user")
                            .operationId("getAllBookings")
                            .responses(OASFactory.createObject(APIResponses.class)
                                .addApiResponse("200", OASFactory.createObject(APIResponse.class)
                                    .description("Bookings retrieved")
                                    .content(OASFactory.createObject(Content.class)
                                        .addMediaType("applictaion/json", OASFactory.createObject(MediaType.class)
                                            .schema(OASFactory.createObject(Schema.class)
                                                .type(SchemaType.ARRAY)))))
                                .addApiResponse("404", OASFactory.createObject(APIResponse.class)
                                    .description("No bookings found for the user"))))
                        .POST(OASFactory.createObject(Operation.class)
                            .security(new ArrayList<SecurityRequirement>())
                                .addSecurityRequirement(OASFactory.createObject(SecurityRequirement.class)
                                    .addScheme("bookingSecurityScheme", new ArrayList<String>(Arrays.asList("write:bookings", "read:bookings"))))
                            .callbacks(new HashMap<String, Callback>()
                                .add("get all the bookings", OASFactory.createObject(Callback.class)
                                    .addPathItem("/bookings", OASFactory.createObject(PathItem.class)
                                        .GET(OASFactory.createObject(Operation.class)
                                            .summary("Retrieve all bookings for current user")
                                            .operationId("getAllBookings")
                                            .responses(OASFactory.createObject(APIResponses.class)
                                                .addApiResponse("200", OASFactory.createObject(APIResponse.class)
                                                    .description("Bookings retrieved")
                                                    .content(OASFactory.createObject(Content.class)
                                                        .addMediaType("applictaion/json", OASFactory.createObject(MediaType.class)
                                                            .schema(OASFactory.createObject(Schema.class)
                                                                .type(SchemaType.ARRAY)))))
                                                .addApiResponse("404", OASFactory.createObject(APIResponse.class)
                                                    .description("No bookings found for the user")))))))
                            .summary("Create a booking")
                            .description("Create a new booking record with the booking information provided.")
                            .operationId("createBooking")
                            .responses(OASFactory.createObject(APIResponses.class)
                                .addApiResponse("201", OASFactory.createObject(APIResponse.class)
                                    .description("Bookings created")
                                    .content(OASFactory.createObject(Content.class)
                                        .addMediaType("text/plain", OASFactory.createObject(MediaType.class)
                                            .schema(OASFactory.createObject(Schema.class)
                                                .title("id")
                                                .description("id of the new booking")
                                                .type(SchemaType.STRING)))))))));                            
    }

    @Override
    public String getReaderClass() {
        // This method could be used for providing a custom OpenAPIReader implementation 
        // as an alternative to specifying the fully qualifies class name in 
        // META-INF/services/io.swagger.oas.integration.OpenAPIReader.
        // return "io.swagger.sample.config.OpenAPIReaderImpl";
        return null;
    }

    @Override
    public Set<String> getResourceClasses() {
        return null;
    }

    @Override
    public Set<String> getResourcePackages() {
        return null;
    }

    @Override
    public String getScannerClass() {
        // This method could be used for providing a custom OpenAPIScanner implementation 
        // as an alternative to specifying the fully qualifies class name in 
        // META-INF/services/io.swagger.oas.integration.OpenAPIScanner.
        // return "io.swagger.sample.config.OpenAPIScannerImpl";
        return null;
    }

    @Override
    public Collection<String> getIgnoredRoutes() {
        return null;
    }

    @Override
    public Map<String, Object> getUserDefinedOptions() {
        return null;
    }

    @Override
    public Boolean isReadAllResources() {
        return null;
    }

    @Override
    public Boolean isScanningDisabled() {
        return Boolean.FALSE;
    }

}