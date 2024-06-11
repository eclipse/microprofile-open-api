/**
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package org.eclipse.microprofile.openapi.apps.airlines.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Encoding;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/zepplins")
@SecurityScheme(securitySchemeName = "mutualTLSScheme", type = SecuritySchemeType.MUTUALTLS,
                description = "mutualTLS authentication needed to manage zepplins")
public class ZepplinResource {

    @DELETE
    @APIResponse(responseCode = "200", description = "Review deleted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Deprecate outdated airship technology", operationId = "deprecateZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    public Response deprecateZepplin(String string) {
        return Response.ok().build();
    }

    @HEAD
    @APIResponse(responseCode = "200", description = "Review deleted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Deprecate outdated airship technology", operationId = "deprecateZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    @Parameter(name = "string", description = "something about a string", in = ParameterIn.QUERY,
               required = true,
               schema = @Schema(type = SchemaType.OBJECT), style = ParameterStyle.SPACEDELIMITED)
    public Response headZepplin() {
        return Response.ok().build();
    }

    @GET
    @APIResponse(responseCode = "200", description = "Review deleted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Deprecate outdated airship technology", operationId = "deprecateZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    @Parameter(name = "string", description = "something about a string", in = ParameterIn.QUERY,
               required = true,
               schema = @Schema(type = SchemaType.OBJECT), style = ParameterStyle.PIPEDELIMITED)
    public Response getZepplin() {
        return Response.ok().build();
    }

    @POST
    @APIResponse(responseCode = "200", description = "Review posted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Post by Zepplin", operationId = "postZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    @Consumes("multipart/form-data")
    public Response postZepplin(
            @RequestBody(description = "Record of a new user to be created in the system.", required = true,
                         content = @Content(mediaType = "multipart/form-data",
                                            schema = @Schema(properties = {
                                                    @SchemaProperty(name = "passenger", description = "the passanger",
                                                                    type = SchemaType.STRING),
                                                    @SchemaProperty(name = "allergies",
                                                                    description = "Does the passenger have any allergies",
                                                                    type = SchemaType.ARRAY),
                                                    @SchemaProperty(name = "specialRequests",
                                                                    description = "Does the passenger have any special requests",
                                                                    type = SchemaType.OBJECT)
                                            }),
                                            encoding = {
                                                    @Encoding(name = "allergies",
                                                              allowReserved = true, explode = true,
                                                              style = "pipeDelimited"),
                                                    @Encoding(name = "specialRequests",
                                                              allowReserved = true, explode = true,
                                                              style = "spaceDelimited")
                                            })) List<String> zeeplinUsers) {
        return Response.ok().build();
    }
}
