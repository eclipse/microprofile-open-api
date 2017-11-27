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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.callbacks.Callbacks;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.servers.Servers;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.security.OAuthScope;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import org.eclipse.microprofile.openapi.apps.airlines.model.Airline;
import org.eclipse.microprofile.openapi.apps.airlines.model.Review;
import org.eclipse.microprofile.openapi.apps.airlines.model.User;


@Path("/reviews")
@Servers(
    value = {
        @Server(
            description = "endpoint for all the review related methods",
            url = "http://localhost:9080/airlines/reviews/")
    })
@SecurityScheme(
    securitySchemeName = "reviewoauth2",
    type = SecuritySchemeType.OAUTH2,
    description = "authentication needed to create and delete reviews",
    flows = @OAuthFlows(
        implicit = @OAuthFlow(
            authorizationUrl = "https://example.com/api/oauth/dialog",
            scopes = @OAuthScope(
                name = "write:reviews",
                description = "create a review"
            )
        ),
        authorizationCode = @OAuthFlow(
            authorizationUrl = "https://example.com/api/oauth/dialog",
            tokenUrl = "https://example.com/api/oauth/token"
        )
    )
)
@Tags(
    value = @Tag(
        name = "Reviews",
        description = "All the review methods"
    )
)
public class ReviewResource {

    private static Map<Integer, Review> reviews = new ConcurrentHashMap<Integer, Review>();
    private volatile int currentId = 0;

    static {
        reviews.put(1, new Review("1", new User(3456, "userOne", "passwd", "Charlie", 
        "Smith", "male", 46, "charlie@mail.com", "11-11-11", 1), 
        new Airline("Acme Air", "1-888-1234-567"), 8, "great!"));

        reviews.put(2, new Review("2", new User(7896, "userTwo", "Passwd", "Nora", 
        "Parkings", "female", 23, "nora@mail.com", "12-12-12", 1), 
        new Airline("Acme Air", "1-888-1234-567"), 7, "good"));
    }
    @GET
    @Operation(
        operationId = "getAllReviews",
        summary = "get all the reviews",
        method = "get",
        tags = {"review"},
        responses = @APIResponse(
            responseCode = "200",
            description = "successful operation",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    type = "array",
                    implementation = Review.class
                )
            )
        )
    )
    @Produces("application/json")
    public Response getAllReviews(){
        return Response.ok().entity(reviews.values()).build();
    }

    @GET
    @Path("{id}")
    @Operation(
        operationId = "getReviewById",
        summary="Get a review with ID",
        method= "get",
        tags = {"review"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Review retrieved",
                content=@Content(
                    schema=@Schema(
                        implementation=Review.class))),
            @APIResponse(
                responseCode="404",
                description="Review not found")
        }
    )
    @Produces("application/json")
    public Response getReviewById(
            @Parameter(
                name = "id",
                description = "ID of the booking",
                required = true,
                in = ParameterIn.PATH,
                content = @Content(
                    examples = @ExampleObject(
                        value = "1")))
            @PathParam("id") int id){
        Review review = reviews.get(id);
        if(review!=null){
            return Response.ok().entity(review).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{user}")
    @Operation(
        method = "get",
        operationId = "getReviewByUser",
        summary="Get all reviews by user",
        tags = {"review"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Review(s) retrieved",
                content=@Content(
                    schema=@Schema(
                        implementation=Review.class))),
            @APIResponse(
                responseCode="404",
                description="Review(s) not found")
        })
    @Produces("application/json")
    public Response getReviewByUser(
        @Parameter(
            name = "user",
            description = "username of the user for the reviews",
            required = true,
            in = ParameterIn.PATH,
            content = @Content(
                examples = @ExampleObject(
                    value = "bsmith")))
        @PathParam("user") String user){

            List<Review> reviewsByUser = new ArrayList<Review>();
            for (Review review : reviews.values()) {
                User currentUser = review.getUser();
                if (currentUser.getUsername() == user) {
                    reviewsByUser.add(review);
                }
            }
            if(!reviewsByUser.isEmpty()){
                return Response.ok().entity(reviewsByUser).build();
            }
            else{
                return Response.status(Status.NOT_FOUND).build();
            }
    }

    @GET
    @Path("{airline}")
    @Operation(
        method = "get",
        operationId = "getReviewByAirline",
        summary="Get all reviews by airlines",
        tags = {"review"},
        parameters = {
            @Parameter(
                name = "airline",
                description = "name of the airlines for the reviews",
                required = true,
                in = ParameterIn.PATH,
                content = @Content(
                    examples = @ExampleObject(
                        value = "Acme Air")))
        },
        responses={
            @APIResponse(
                responseCode="200",
                description="Review(s) retrieved",
                content=@Content(
                    schema=@Schema(
                        implementation=Review.class))),
            @APIResponse(
                responseCode="404",
                description="Review(s) not found")
        })
    @Produces("application/json")
    public Response getReviewByAirline(
        @Parameter(
            name = "airline",
            description = "name of the airlines for the reviews",
            required = true,
            in = ParameterIn.PATH,
            content = @Content(
                examples = @ExampleObject(
                    value = "Acme Air")))
        @PathParam("airline") String airlines){

            List<Review> reviewsByAirlines = new ArrayList<Review>();
            for (Review review : reviews.values()) {
                Airline currentAirline = review.getAirlines();
                if (currentAirline.getName() == airlines) {
                    reviewsByAirlines.add(review);
                }
            }
            if(!reviewsByAirlines.isEmpty()){
                return Response.ok().entity(reviewsByAirlines).build();
            }
            else{
                return Response.status(Status.NOT_FOUND).build();
            }
    }

    @GET
    @Path("{user}/{airlines}")
    @Operation(
        method = "get",
        operationId = "getReviewByAirlineAndUser",
        summary="Get all reviews for an airline by User",
        tags = {"review"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Review(s) retrieved",
                content=@Content(
                    schema=@Schema(
                    implementation=Review.class))),
            @APIResponse(
                responseCode="404",
                description="Review(s) not found")
        })
    @Produces("application/json")
    public Response getReviewByAirlineAndUser(
        @Parameters(
            value = {
                @Parameter(
                    name = "airlines",
                    description = "name of the airlines for the reviews",
                    required = true,
                    in = ParameterIn.PATH,
                    content = @Content(
                        examples = @ExampleObject(
                            value = "Acme Air"))),
                @Parameter(
                    name = "user",
                    description = "sername of the user for the reviews",
                    required = true,
                    in = ParameterIn.PATH,
                    content = @Content(
                        examples = @ExampleObject(
                            value = "bsmith")))
                })
        @PathParam("user") String user,
        @PathParam("airlines") String airlines){
            List<Review> reviewsByAirlinesUser = new ArrayList<Review>();
            for (Review review : reviews.values()) {
                Airline currentAirline = review.getAirlines();
                User currentUser = review.getUser();

                if (currentAirline.getName() == airlines && currentUser.getUsername() == user) {
                    reviewsByAirlinesUser.add(review);
                }
            }
            if(!reviewsByAirlinesUser.isEmpty()){
                return Response.ok().entity(reviewsByAirlinesUser).build();
            }
            else{
                return Response.status(Status.NOT_FOUND).build();
            }
    }

    @POST
    @Callbacks(
        {@Callback(
            name = "testCallback",
            callbackUrlExpression="http://localhost:9080/oas3-airlines/reviews",
            operation = @Operation(
                operationId = "getAllReviews",
                summary = "Get all reviews",
                method = "get",
                responses = @APIResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                            type = "array",
                            implementation = Review.class
                            )
                        )
                    )
                )
            )
        }
            )
    @Operation(
        method = "post",
        summary="Create a Review",
        operationId = "createReview",
        tags = {"review"},
        servers = {
            @Server(
                url = "localhost:9080/oas3-airlines/reviews/1",
                description = "view of all the reviews",
                variables = {
                    @ServerVariable(
                        name = "id",
                        description = "id of the review",
                        defaultValue = "1")
            })
        },
        security = @SecurityRequirement(
            name = "reviewoauth2",
            scopes = "write:reviews"),
        responses={
            @APIResponse(
                responseCode="201",
                description="review created",
                content = @Content(
                    schema=@Schema(
                        name= "id",
                        description = "id of the new review",
                        type="string")))
            },
        requestBody = @RequestBody(
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = Review.class),
                examples = @ExampleObject(
                    name = "review",
                    summary = "External review example",
                    externalValue = "http://foo.bar/examples/review-example.json"
                    )
                ),
            required = true,
            description = "example review to add"
        )
    )
    @Consumes("application/json")
    @Produces("application/json")
    public Response createReview(
        @Parameter(
            description = "review to create",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Review.class)))
    Review review) {
        reviews.put(currentId, review);
        return Response.status(Status.CREATED).entity("{\"id\":" + currentId++ + "}").build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
        method = "delete",
        summary="Delete a Review with ID",
        operationId = "deleteReview",
        tags = {"review"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Review deleted"
                ),
            @APIResponse(
                responseCode="404",
                description="Review not found"
                )
        })
    @Produces("text/plain")
    public Response deleteReview(
            @PathParam("id") int id){
                if(reviews.get(id)!=null) {
                    reviews.remove(id);
                    return Response.ok().build();
                }
                else {
                    return Response.status(Status.NOT_FOUND).build();
                }
            }
}
