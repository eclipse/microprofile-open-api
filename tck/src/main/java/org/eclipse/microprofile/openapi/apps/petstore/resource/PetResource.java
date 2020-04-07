/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
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

package org.eclipse.microprofile.openapi.apps.petstore.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.extensions.Extensions;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBodySchema;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.callbacks.CallbackOperation;

import org.eclipse.microprofile.openapi.apps.petstore.data.PetData;
import org.eclipse.microprofile.openapi.apps.petstore.model.Pet;
import org.eclipse.microprofile.openapi.apps.petstore.model.ApiResponse;
import org.eclipse.microprofile.openapi.apps.petstore.model.Cat;
import org.eclipse.microprofile.openapi.apps.petstore.model.Dog;
import org.eclipse.microprofile.openapi.apps.petstore.model.Lizard;
import org.eclipse.microprofile.openapi.apps.petstore.exception.NotFoundException;

import java.io.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

@Path("/pet")
@Schema(
        name = "pet",
        description = "Operations on pets resource")
@SecuritySchemes(
    value = {
        @SecurityScheme(
            securitySchemeName = "petsApiKey",
            type = SecuritySchemeType.APIKEY,
            description = "authentication needed to create a new pet profile for the store",
            apiKeyName = "createPetProfile",
            in = SecuritySchemeIn.HEADER
        ),
        @SecurityScheme(
            securitySchemeName = "petsOAuth2",
            type = SecuritySchemeType.OAUTH2,
            description = "authentication needed to delete a pet profile", 
            flows = @OAuthFlows(
                implicit = @OAuthFlow(
                    authorizationUrl = "https://example.com/api/oauth/dialog"
                ),
                authorizationCode = @OAuthFlow(
                    authorizationUrl = "https://example.com/api/oauth/dialog",
                    tokenUrl = "https://example.com/api/oauth/token"
                )
            )
        ),
        @SecurityScheme(
            securitySchemeName = "petsHttp",
            type = SecuritySchemeType.HTTP,
            description = "authentication needed to update an exsiting record of a pet in the store",
            scheme = "bearer",
            bearerFormat = "jwt"
        )    
    }
)
public class PetResource {

    static PetData petData = new PetData();

    @GET
    @Path("/{petId}")
    @APIResponses(value={
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid ID supplied",
                    content = @Content(
                        mediaType = "none")
                ),
                @APIResponse(
                    responseCode = "404",
                    description = "Pet not found",
                    content = @Content(
                        mediaType = "none")
                ),
                @APIResponse(
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(type = SchemaType.OBJECT, implementation = Pet.class, 
                          oneOf = { Cat.class, Dog.class, Lizard.class }, 
                          readOnly = true))
                )
    })
    @Operation(
            summary = "Find pet by ID",
            description = "Returns a pet when ID is less than or equal to 10"
    )
    public Response getPetById(
        @Parameter(
            name = "petId",
            description = "ID of pet that needs to be fetched",
            required = true,
            example = "1",
            schema = @Schema(
                implementation = Long.class,
                maximum = "101",
                exclusiveMaximum = true,
                minimum = "9",
                exclusiveMinimum = true, 
                multipleOf = 10))
        @PathParam("petId") Long petId)
    throws NotFoundException {
        Pet pet = petData.getPetById(petId);
        if (pet != null) {
            return Response.ok().entity(pet).build();
        }
        else {
            throw new NotFoundException(404, "Pet not found");
        }
    }

    @GET
    @Path("/{petId}/download")
    @APIResponses(value={
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid ID supplied",
                    content = @Content(mediaType = "none")
                ),
                @APIResponse(
                    responseCode = "404",
                    description = "Pet not found",
                    content = @Content(mediaType = "none")
                )
    })
    @Operation(
        summary = "Find pet by ID and download it",
        description = "Returns a pet when ID is less than or equal to 10"
    )
    public Response downloadFile(
        @Parameter(
            name = "petId",
            description = "ID of pet that needs to be fetched",
            required = true,
            schema = @Schema(
                implementation = Long.class,
                maximum = "10",
                minimum = "1")
        )
        @PathParam("petId") Long petId)
    throws NotFoundException {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException {
                try {
                    // TODO: write file content to output;
                    output.write("hello, world".getBytes());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return Response.ok(stream, "application/force-download")
        .header("Content-Disposition", "attachment; filename = foo.bar")
        .build();
    }

    @DELETE
    @Path("/{petId}")
    @SecurityRequirement(
            name = "petsOAuth2",
            scopes = "write:pets"
            )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid ID supplied"
                ),
                @APIResponse(
                    responseCode = "404",
                    description = "Pet not found"
                )
    })
    @Operation(
        summary = "Deletes a pet by ID",
        description = "Returns a pet when ID is less than or equal to 10"
    )
    public Response deletePet(
        @Parameter(
            name = "apiKey",
            description = "authentication key to access this method",
            schema = @Schema(type = SchemaType.STRING, implementation = String.class,
              maxLength = 256, minLength = 32))
        @HeaderParam("api_key") String apiKey,
        @Parameter(
            name = "petId",
            description = "ID of pet that needs to be fetched",
            required = true,
            schema = @Schema(
                implementation = Long.class,
                maximum = "10",
                minimum = "1"))
        @PathParam("petId") Long petId) {
            if (petData.deletePet(petId)) {
                return Response.ok().build();
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    @SecurityRequirement(
            name = "petsApiKey"
        )
    @APIResponse(
            responseCode = "400",
            description = "Invalid input",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class))
        )
    @RequestBody(
            name="pet",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Pet.class), 
                examples = @ExampleObject(ref = "http://example.org/petapi-examples/openapi.json#/components/examples/pet-example") ),
            required = true,
            description = "example of a new pet to add"
        )
    @Operation(
        summary = "Add pet to store",
        description = "Add a new pet to the store"
    )
    public Response addPet(Pet pet) {
                Pet updatedPet = petData.addPet(pet);
                return Response.ok().entity(updatedPet).build();
            }

    @PUT
    @Consumes({"application/json", "application/xml"})
    @SecurityRequirement(
            name = "petsHttp"
        )
    @APIResponses(value={
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid ID supplied",
                    content = @Content(mediaType = "application/json")
                ),
                @APIResponse(
                    responseCode = "404",
                    description = "Pet not found",
                    content = @Content(mediaType = "application/json")
                ),
                @APIResponse(
                    responseCode = "405",
                    description = "Validation exception",
                    content = @Content(mediaType = "application/json")
                )
    })
    @Operation(
        summary = "Update an existing pet",
        description = "Update an existing pet with the given new attributes"
    )
    public Response updatePet(
        @Parameter(
            name ="petAttribute",
            description = "Attribute to update existing pet record",
            required = true,
            schema = @Schema(implementation = Pet.class)) Pet pet) {
                Pet updatedPet = petData.addPet(pet);
                return Response.ok().entity(updatedPet).build();
            }

    @GET
    @Path("/findByStatus")
    @APIResponse(
            responseCode = "400",
            description = "Invalid status value",
            content = @Content(mediaType = "none")
        )
    @APIResponse(
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(type = SchemaType.ARRAY, implementation = Pet.class))
            )
    @Operation(
        summary = "Finds Pets by status",
        description = "Find all the Pets with the given status; Multiple status values can be provided with comma seperated strings"
    )
    @Extension(name = "x-mp-method1", value = "true")
    @Extensions( { @Extension(name = "x-mp-method2", value = "true"), @Extension(value = "false", name = "x-mp-method3") } )
    public Response findPetsByStatus(
        @Parameter(
            name = "status",
            description = "Status values that need to be considered for filter",
            required = true,
            schema = @Schema(implementation = String.class),
            content = {
                @Content(
                    examples = {
                        @ExampleObject(
                            name = "Available",
                            value = "available",
                            summary = "Retrieves all the pets that are available"),
                        @ExampleObject(
                            name = "Pending",
                            value = "pending",
                            summary = "Retrieves all the pets that are pending to be sold"),
                        @ExampleObject(
                            name = "Sold",
                            value = "sold",
                            summary = "Retrieves all the pets that are sold")
                    }
                )
            },
            allowEmptyValue = true)
        @Extension(name = "x-mp-parm1", value = "true")
        String status) {
                return Response.ok(petData.findPetByStatus(status)).build();
            }

    @GET
    @Path("/findByTags")
    @Produces("application/json")
    @Callback(
        name = "tagsCallback",
        callbackUrlExpression = "http://petstoreapp.com/pet",
        operations = @CallbackOperation(
            method = "GET",
            summary = "Finds Pets by tags",
            description = "Find Pets by tags; Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.",
            responses = {
                @APIResponse(
                    responseCode = "400",
                    description = "Invalid tag value",
                    content = @Content(mediaType = "none")
                ),
                @APIResponse(
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(type = SchemaType.ARRAY, implementation = Pet.class))
                    )
            }
        )
    )
    @APIResponseSchema(Pet[].class)
    @Deprecated
    public Response findPetsByTags(
        @HeaderParam("apiKey") String apiKey,
        @Parameter(
            name = "tags",
            description = "Tags to filter by",
            required = true,
            deprecated = true,
            schema = @Schema(implementation = String.class, deprecated = true,
              externalDocs = @ExternalDocumentation(description = "Pet Types", url = "http://example.com/pettypes"),
              enumeration = { "Cat", "Dog", "Lizard" }, defaultValue = "Dog" ))
        @QueryParam("tags") String tags) {
            return Response.ok(petData.findPetByTags(tags)).build();
        }

    @POST
    @Path("/{petId}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @APIResponse(
            responseCode = "405",
            description = "Validation exception",
            content = @Content(mediaType = "none")
        )
    @Operation(
        summary = "Updates a pet in the store with form data"
    )
    public Response updatePetWithForm (
        @Parameter(
            name = "petId",
            description = "ID of pet that needs to be updated",
            required = true)
        @PathParam("petId") Long petId,
        @Parameter(
            name = "name",
            description = "Updated name of the pet")
        @FormParam("name") String name,
        @Parameter(
            name = "status",
            description = "Updated status of the pet")
        @FormParam("status") String status) {
            Pet pet = petData.getPetById(petId);
            if(pet != null) {
                if(name != null && !"".equals(name)){
                    pet.setName(name);
                }
                if(status != null && !"".equals(status)){
                    pet.setStatus(status);
                }
                petData.addPet(pet);
                return Response.ok().build();
            }
            else{
                return Response.status(404).build();
            }
        }

    @POST
    @Path("/{petId}")
    @Consumes({ "text/csv" })
    @Produces({ "text/csv" })
    @APIResponseSchema(value = Pet.class, responseCode = "204")
    @Operation(summary = "Updates a pet in the store with CSV data")
    public Response updatePetWithCsv (
        @Parameter(
            name = "petId",
            description = "ID of pet that needs to be updated",
            required = true)
        @PathParam("petId") Long petId,
        @RequestBodySchema(Pet.class) 
            String commaSeparatedValues
        ) {
            Pet pet = petData.getPetById(petId);
            if(pet != null) {
                String[] values = commaSeparatedValues.split(",");
                String name = values[2];
                String status = values[5];

                if(name != null && !"".equals(name)){
                    pet.setName(name);
                }
                if(status != null && !"".equals(status)){
                    pet.setStatus(status);
                }
                petData.addPet(pet);
                return Response.ok().build();
            }
            else{
                return Response.status(404).build();
            }
        }
}
