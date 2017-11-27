/**
* (C) Copyright IBM Corporation 2016.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.eclipse.microprofile.openapi.apps.airlines;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;

import org.eclipse.microprofile.openapi.apps.airlines.resources.AirlinesResource;
import org.eclipse.microprofile.openapi.apps.airlines.resources.AvailabilityResource;
import org.eclipse.microprofile.openapi.apps.airlines.resources.BookingResource;
import org.eclipse.microprofile.openapi.apps.airlines.resources.ReviewResource;

@ApplicationPath("/")
@OpenAPIDefinition(
    tags = @Tag(name = "Airlines", description = "airlines app"),
    externalDocs = @ExternalDocumentation(
        description = "instructions for how to deploy this app",
        url = "https://github.com/microservices-api/oas3-airlines/blob/master/README.md"),
    info = @Info(
        title="AirlinesRatingApp API", 
        version = "1.0",
        termsOfService = "http://airlinesratingapp.com/terms", 
        contact = @Contact(
            name = "AirlinesRatingApp API Support",
            url = "https://github.com/microservices-api/oas3-airlines",
            email = "techsupport@airlinesratingapp.com"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
    security = @SecurityRequirement(
        name = "airlinesRatingApp_auth"),
    servers = {
        @Server(
            url = "localhost:9080/oas3-airlines/",
            description = "Home page of airlines app",
            variables = {
                @ServerVariable(
                    name = "reviews",
                    description = "Reviews of the app by users",
                    defaultValue = "reviews"),
                @ServerVariable(
                    name = "bookings",
                    description = "Booking data",
                    defaultValue = "bookings"),
                @ServerVariable(
                    name = "user",
                    description = "User data",
                    defaultValue = "user"),
                @ServerVariable(
                    name = "availability",
                    description = "Flight availabilities",
                    defaultValue = "availability")
            })
        })
@SecurityScheme(
    securitySchemeName = "airlinesRatingApp_auth",
    description = "authentication needed to access Airlines app",
    type = SecuritySchemeType.APIKEY,
    apiKeyName = "api_key",
    in = SecuritySchemeIn.HEADER
)    
@Schema(
    name = "AirlinesRatingApp API",
    description = "APIs for booking and managing air flights",
    externalDocs = @ExternalDocumentation(
        description = "For more information, see the link.",
        url = "https://github.com/janamanoharan/airlinesratingapp")
    )
public class JAXRSApp extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();
        singletons.add(new AirlinesResource());
        singletons.add(new AvailabilityResource());
        singletons.add(new BookingResource());
        singletons.add(new ReviewResource());
        return singletons;
    }
    
    public static int getRandomNumber(int max, int min) {
        return (new Random()).nextInt(max - min + 1) + min;
    }
}
