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

package org.eclipse.microprofile.openapi.apps.airlines.resources;

import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.links.Link;
import org.eclipse.microprofile.openapi.annotations.links.LinkParameter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.ArraySchema;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Encoding;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.media.Content;

import org.eclipse.microprofile.openapi.apps.airlines.data.UserData;
import org.eclipse.microprofile.openapi.apps.airlines.model.User;
import org.eclipse.microprofile.openapi.apps.airlines.exception.ApiException;
import org.eclipse.microprofile.openapi.apps.airlines.exception.NotFoundException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces({"application/json", "application/xml"})
@OpenAPIDefinition(
    info = @Info(
        title = "User Resource API",
        version = "1.0.0",
        description = "API resource for User model of the AirlinesRatingApp.",
        termsOfService = "http://exampleurl.com/termsofservice",
        contact = @Contact(
            name = "AirlinesRatingApp API Support Team",
            url = "http://exampleurl.com/contact",
            email = "ask@airlinessupport.com"
            ),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"
            )
    )
)
@SecurityScheme(
    description = "user security scheme",
    type = SecuritySchemeType.HTTP,
    securitySchemeName = "httpTestScheme",
    scheme = "testScheme")
@SecurityRequirement(
    name = "users",
    scopes = "write:users")

public class UserResource {

    static UserData userData = new UserData();

    @POST
    @Operation(
        method = "post",
        summary = "Create user",
        description = "This can only be done by the logged in user.",
        operationId = "createUser",
        tags = {"user"},
        requestBody = @RequestBody(
            description = "Record of a new user to be created in the system.",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    name = "testUser",
                    type = "object",
                    implementation = User.class),
                examples = @ExampleObject(
                    name = "user",
                    summary = "External user example",
                    externalValue = "http://foo.bar/examples/user-example.json"
                    ),
                encoding = @Encoding(
                    name = "email",
                    contentType = "text/plain",
                    style = "form",
                    allowReserved = true,
                    explode = true,
                    headers = @Header(
                        name = "testHeader"
                    )
                )
            )
        ),
        parameters = {
            @Parameter(
                name = "id",
                in = ParameterIn.QUERY,
                description = "User id for the new user record to be created",
                required = true,
                allowReserved = true,
                style = ParameterStyle.FORM,
                schema = @Schema(type = "integer", format = "int32")
            ),
            @Parameter(
                name = "userName",
                in = ParameterIn.QUERY,
                description = "Username for the new user record to be created",
                required = true,
                schema = @Schema(
                    type = "string",
                    externalDocs = @ExternalDocumentation(
                        description = "How to create good user names.",
                        url = "http://exampleurl.com/usernames"
                    )
                )
            ),
            @Parameter(
                name = "password",
                in = ParameterIn.QUERY,
                description = "User password for the new user record to be created",
                required = true,
                hidden = true,
                schema = @Schema(
                    type = "string",
                    externalDocs = @ExternalDocumentation(
                        description = "How to create good passwords.",
                        url = "http://exampleurl.com/passwords"
                    )
                )
            ),
            @Parameter(
                name = "firstName",
                in = ParameterIn.QUERY,
                description = "User's first name for the new user record to be created",
                required = true,
                schema = @Schema(type = "string")
            ),
            @Parameter(
                name = "lastName",
                in = ParameterIn.QUERY,
                description = "User's last name for the new user record to be created",
                style = ParameterStyle.FORM,
                required = true,
                schema = @Schema(type = "string")
            ),
            @Parameter(
                name = "sex",
                in = ParameterIn.QUERY,
                description = "User's sex for the new user record to be created",
                required = true,
                style = ParameterStyle.FORM,
                schema = @Schema(type = "string")
            ),
            @Parameter(
                name = "age",
                in = ParameterIn.QUERY,
                description = "User's age for the new user record to be created",
                required = true,
                schema = @Schema(type = "integer", format = "int64")
            ),
            @Parameter(
                name = "phone",
                in = ParameterIn.QUERY,
                description = "User phone number for the new user record to be created",
                required = true,
                schema = @Schema(type = "string")
            ),
            @Parameter(
                name = "status",
                in = ParameterIn.QUERY,
                description = "User status for the new user record to be created",
                required = true,
                schema = @Schema(type = "integer")
            )
        },
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "New user record successfully created."
            ),
            @APIResponse(
                responseCode = "400",
                description = "Unable to create this user record."
            )
        })
    public Response createUser(
        @Parameter(
            description = "Created user object",
            schema = @Schema(implementation = User.class),
            required = true
        ) User user) {
        userData.addUser(user);
        return Response.ok().entity("").build();
        }

    @POST
    @Path("/createWithArray")
    @Operation(
        method = "post",
        summary = "Creates list of users with given input array", //Array of User objects
        operationId = "createUsersFromArray",
        tags = {"user"},
        parameters = {
            @Parameter(
                name = "userArray",
                in = ParameterIn.QUERY,
                description = "An array of User objects to create records.",
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        type = "array",
                        implementation = User.class
                    ),
                    encoding = @Encoding(
                        name = "firstName",
                        contentType = "text/plain",
                        style = "form",
                        allowReserved = true,
                        explode = true
                    )
                ),
                array = @ArraySchema(
                    schema = @Schema(
                        type = "object",
                        implementation = User.class
                    ),
                    minItems = 2
                )
            )
        },
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "Successfully created list of users."
            ),
            @APIResponse(
                responseCode = "400",
                description = "Unable to create list of users."
            )
        })
    public Response createUsersWithArrayInput(
        @Parameter(
            description = "Array of user object",
            required = true
        ) User[] users) {
            for (User user : users) {
                userData.addUser(user);
            }
           return Response.ok().entity("").build();
        }

    @POST
      @Path("/createWithList")
      @Operation(
        method = "post",
        summary = "Creates list of users with given input list", //List of User objects
        operationId = "createUsersFromList",
        tags = {"user"},
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "Successfully created list of users."
                ),
            @APIResponse(
                responseCode = "400",
                description = "Unable to create list of users."
                )
        })
      public Response createUsersWithListInput(
            @Parameter(
                description = "List of user object",
                required = true
            )
            java.util.List<User> users) {
                for (User user : users) {
                    userData.addUser(user);
                }
                return Response.ok().entity("").build();
            }

      @PUT
      @Path("/{username}")
      @Operation(
        method = "put",
        summary = "Update user",
        description = "This can only be done by the logged in user.",
        operationId = "updateUser",
        tags = {"user"},
        requestBody = @RequestBody(
            description = "Record of a new user to be created in the system.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class),
                examples = @ExampleObject(
                    name = "user",
                    summary = "Example user properties to update",
                    value = "Properties to update in JSON format here"
                )
            )
        ),
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "User updated successfully",
                content = @Content(
                    schema = @Schema(
                        name = "upadtedUser",
                        implementation = User.class
                        ),
                    encoding = @Encoding(
                        name = "password",
                        contentType = "text/plain",
                        style = "form",
                        allowReserved = true,
                        explode = true
                        )
                    )
            ),
            @APIResponse(
                responseCode = "400",
                description = "Invalid user supplied"
            ),
            @APIResponse(
                responseCode = "404",
                description = "User not found"
            )
        })
    public Response updateUser(
        @Parameter(
            name = "username",
            description = "User that needs to be updated",
            schema = @Schema(type = "String"),
            required = true
            )
        @PathParam("username") String username,
        @Parameter(
            description = "Updated user object",
            required = true) User user) {
                userData.addUser(user);
                return Response.ok().entity("").build();
            }

      @DELETE
      @Path("/{username}")
      @Operation(
        method = "delete",
        summary = "Delete user",
        description = "This can only be done by the logged in user.",
        operationId = "deleteUser",
        tags = {"user"},
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "User deleted successfully"
                ),
            @APIResponse(
                responseCode = "400",
                description = "Invalid username supplied"
                ),
            @APIResponse(
                responseCode = "404",
                description = "User not found"
                )
        })
    public Response deleteUser(
        @Parameter(
            name = "username",
            description = "The name that needs to be deleted",
            schema = @Schema(type = "String"),
            required = true
            )
        @PathParam("username") String userName) {
            if (userData.removeUser(userName)) {
                return Response.ok().entity("").build();
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

    @GET
    @Path("/{username}")
    @Operation(
        method = "get",
        summary = "Get user by user name",
        operationId = "getUserByUserName",
        tags = {"user"},
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "Successfully retrieved user by user name.",
                content = @Content(
                    schema = @Schema(implementation = User.class)
                )),
            @APIResponse(
                responseCode = "400",
                description = "Invalid username supplied",
                content = @Content(
                        schema = @Schema(implementation = User.class)
                )),
            @APIResponse(
                responseCode = "404",
                description = "User not found",
                        content = @Content(
                                schema = @Schema(implementation = User.class)
                        )
                )
                })
    public Response getUserByName(
        @Parameter(
            name = "username",
            description = "The name that needs to be fetched. Use Bob1 for testing.",
            schema = @Schema(type = "String"),
            required = true
            )
        @PathParam("username") String userName) throws ApiException {
            User user = userData.findUserByName(userName);
            if (null != user) {
                return Response.ok().entity(user).build();
            }
            else {
                throw new NotFoundException(404, "User not found");
            }
        }

    @GET
    @Path("/{id}")
    @Operation(
        method = "get",
        summary = "Get user by id",
        operationId = "getUserById",
        tags = {"user"},
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "Successfully retrieved user by id.",
                content = @Content(
                    schema = @Schema(implementation = User.class)
                ),
                links = @Link(
                        name = "User name",
                        description = "The username corresponding to provided user id",
                        operationId = "getUserByName",
                        parameters = @LinkParameter(
                            name = "userId",
                            expression = "$request.path.id"))
                ),
            @APIResponse(
                responseCode = "400",
                description = "Invalid id supplied",
                content = @Content(
                    schema = @Schema(implementation = User.class)
                )),
            @APIResponse(
                responseCode = "404",
                description = "User not found",
                    content = @Content(
                        schema = @Schema(implementation = User.class)
                    )
                )
            })
    public Response getUserById(
        @Parameter(
            name = "id",
            description = "The name that needs to be fetched. Use 1 for testing.",
            schema = @Schema(type = "integer"),
            required = true
        )
        @PathParam("id") int id) throws ApiException {
            User user = userData.findUserById(id);
            if (null != user) {
            return Response.ok().entity(user).build();
            }
            else {
            throw new NotFoundException(404, "User not found");
            }
        }

    @GET
    @Path("/login")
    @Operation(
        method = "get",
        summary = "Logs user into the system",
        operationId = "logInUser",
        tags = {"user"},
        externalDocs = @ExternalDocumentation(
                description = "Policy on user security.",
                url = "http://exampleurl.com/policy"
        ),
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "Successful user login.",
                content = @Content(
                    schema = @Schema(implementation = User.class)
                )
            ),
            @APIResponse(
                responseCode = "400",
                description = "Invalid username/password supplied"
                )
            }
    )

    public Response loginUser(
        @Parameter(
            name = "username",
            description = "The user name for login",
            schema = @Schema(type = "String"),
            required = true
            )
        @QueryParam("username") String username,
        @Parameter(
            name = "password",
            description = "The password for login in clear text",
            schema = @Schema(type = "String"),
            required = true)
        @QueryParam("password") String password) {
            return Response.ok()
                .entity("logged in user session:" + System.currentTimeMillis())
                .build();
        }

    @GET
    @Path("/logout")
    @Operation(
        method = "get",
        summary = "Logs out current logged in user session",
        operationId = "logOutUser",
        tags = {"user"},
        externalDocs = @ExternalDocumentation(
            description = "Policy on user security.",
            url = "http://exampleurl.com/policy"
            ),
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "Successful user logout."
                )
            })
    public Response logoutUser() {
    return Response.ok().entity("").build();
    }
}