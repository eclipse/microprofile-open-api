package org.eclipse.microprofile.openapi.apps.airlines;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import io.swagger.oas.integration.OpenAPIConfiguration;
import org.eclipse.microprofile.openapi.models.Components;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.MediaType;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.responses.APIResponses;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;

public class OpenAPIConfigurationImpl implements OpenAPIConfiguration {

    @Override
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("AirlinesRatingApp API").version("1.0").termsOfService("http://airlinesratingapp.com/terms")
                    .contact(new Contact().name("AirlinesRatingApp API Support").url("http://exampleurl.com/contact").email("techsupport@airlinesratingapp.com"))
                    .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .security(new SecurityRequirement().addScheme("airlinesRatingApp_auth"))
                .components(new Components().addSchemas("User",
                        new Schema<Object>().type("object").addProperties("name", new Schema().type("string"))
                                .addProperties("id", new Schema().type("integer").format("int32"))
                                .addProperties("email", new Schema().type("string"))
                                .addProperties("age", new Schema<>().type("integer").format("int32"))))
                .path("/sample/users",
                        new PathItem().get(new Operation().description("Resource to get a user")
                                .responses(
                                        new ApiResponses()
                                                .addApiResponse("200",
                                                        new ApiResponse().description("Successful")
                                                                .content(new Content().addMediaType("application/json",
                                                                        new MediaType().schema(new Schema<Object>()
                                                                                .$ref("#/components/schemas/User")))))
                                                .addApiResponse("404",
                                                        new ApiResponse().description("Error: Not found.")))
                                .addParametersItem(new Parameter().name("name").required(true).in("query")
                                        .description("Name of the user").schema(new Schema().type("string")))
                                .addParametersItem(new Parameter().name("id").required(true).in("query")
                                        .description("Id of the user").schema(new Schema().type("integer").format("int32")))
                                .addParametersItem(new Parameter().name("email").required(true).in("query")
                                        .description("Email of the user").schema(new Schema().type("string")))
                                .addParametersItem(new Parameter().name("age").required(true).in("query")
                                        .description("Age of the user").schema(new Schema().type("integer").format("int32")))));
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