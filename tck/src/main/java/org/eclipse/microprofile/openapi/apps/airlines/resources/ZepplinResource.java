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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/zepplins")
@SecurityScheme(securitySchemeName = "mutualTLSScheme", type = SecuritySchemeType.MUTUALTLS,
                description = "mutualTLS authentication needed to manage zepplins")
public class ZepplinResource {

    @DELETE
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "Review deleted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Deprecate outdated airship technology", operationId = "deprecateZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    public Response deprecateZepplin(@RequestBody(description = "Something about a zepplin.",
                                                  content = @Content(mediaType = "application/json")) String string) {
        return Response.ok().build();
    }

    @HEAD
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "Review deleted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Deprecate outdated airship technology", operationId = "deprecateZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    public Response headZepplin(@RequestBody(ref = "#/paths/~1zepplins~1{id}/delete/requestBody") String string) {
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "Review deleted")
    @APIResponse(responseCode = "404", description = "Review not found")
    @Operation(summary = "Deprecate outdated airship technology", operationId = "deprecateZepplin")
    @Produces("text/plain")
    @SecurityRequirement(name = "mutualTLSScheme", scopes = "zepplinScope")
    public Response getZepplin(@RequestBody(ref = "#/paths/~1zepplins~1{id}/delete/requestBody") String string) {
        return Response.ok().build();
    }
}
