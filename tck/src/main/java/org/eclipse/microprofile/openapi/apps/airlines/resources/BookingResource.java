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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.apps.airlines.model.Booking;

@Path("/bookings")
@Tags(
    value = @Tag(
        name = "Bookings", 
        description = "All the bookings methods"))

public class BookingResource {
    private Map<Integer, Booking> bookings = new ConcurrentHashMap<Integer, Booking>();
    private volatile int currentId = 0;

    @GET
    @Operation(
        method = "get",
        summary="Retrieve all bookings for current user",
        operationId = "getAllBookings",
        tags = {"booking"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Bookings retrieved",
                content=@Content(
                    schema=@Schema(
                        type="array",
                        implementation=Booking.class))
                ),
            @APIResponse(
                responseCode="404",
                description="No bookings found for the user.")
        })
    @Produces("application/json")
    public Response getBookings(){
        return Response.ok().entity(bookings.values()).build();
    }

    @POST
    @Callback(
        name = "get all the bookings",
        callbackUrlExpression = "http://localhost:9080/airlines/bookings",
        operation = @Operation(
            summary="Retrieve all bookings for current user",
            responses={
                @APIResponse(
                    responseCode="200",
                    description="Bookings retrieved",
                    content=@Content(
                        schema=@Schema(
                            type="array",
                            implementation=Booking.class))
                ),
                @APIResponse(
                    responseCode="404",
                    description="No bookings found for the user.")
            }))
    @Operation(
        method = "post",
        summary="Create a booking",
        description = "Create a new booking record with the booking information provided.",
        operationId = "createBooking",
        tags = {"booking"},
        requestBody = @RequestBody(
            description = "Create a new booking with the provided information.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class),
                examples = @ExampleObject(
                    name = "booking",
                    summary = "External booking example",
                    externalValue = "http://foo.bar/examples/booking-example.json"
                )
            )
        ),
        responses={
            @APIResponse(
                responseCode="201",
                description="Booking created",
                content = @Content(
                    schema=@Schema(
                        name= "id",
                        description = "id of the new booking",
                        type="string"
                    )
                )
            )
        }
    )
    @Consumes("application/json")
    @Produces("application/json")
    public Response createBooking(
        @Parameter(
            description = "booking to create",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)))
        Booking task) {
            bookings.put(currentId, task);
            return Response.status(Status.CREATED).entity("{\"id\":" + currentId++ + "}").build();
        }

    @GET
    @Path("{id}")
    @Produces("application/json")
    @Operation(
        method = "get",
        summary="Get a booking with ID",
        operationId = "getBookingById",
        tags = {"booking"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Booking retrieved",
                content=@Content(
                    schema=@Schema(
                        implementation=Booking.class))),
            @APIResponse(
                responseCode="404",
                description="Booking not found")
        })
    public Response getBooking(
        @Parameter(
            name = "id",
            description = "ID of the booking",
            required = true,
            in = ParameterIn.PATH)
        @PathParam("id") int id){
            Booking booking = bookings.get(id);
            if(booking!=null){
                return Response.ok().entity(booking).build();
            }
            else{
                return Response.status(Status.NOT_FOUND).build();
            }
        }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("text/plain")
    @Operation(
        method = "put",
        summary="Update a booking with ID",
        operationId = "updateBookingId",
        tags = {"booking"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Booking updated"
                ),
            @APIResponse(
                responseCode="404",
                description="Booking not found"
                )
        })
    public Response updateBooking(
        @PathParam("id") int id, Booking booking){
            if(bookings.get(id)!=null){
                bookings.put(id, booking);
                return Response.ok().build();
            }
            else{
                return Response.status(Status.NOT_FOUND).build();
            }
        }

    @DELETE
    @Path("{id}")
    @Operation(
        method = "delete",
        summary="Delete a booking with ID",
        operationId = "deleteBookingById",
        tags = {"booking"},
        responses={
            @APIResponse(
                responseCode="200",
                description="Booking deleted successfully."
            ),
            @APIResponse(
                responseCode="404",
                description="Booking not found."
            )
        })
    @Produces("text/plain")
    public Response deleteBooking(
            @PathParam("id") int id){
                if(bookings.get(id)!=null) {
                    bookings.remove(id);
                    return Response.ok().build();
                }
                else {
                    return Response.status(Status.NOT_FOUND).build();
                }
            }
}
