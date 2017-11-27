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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.eclipse.microprofile.openapi.apps.airlines.JAXRSApp;
import org.eclipse.microprofile.openapi.apps.airlines.model.Airline;
import org.eclipse.microprofile.openapi.apps.airlines.model.Flight;

@Path("")
@Schema(name = "Airline Booking API")
@Tags(
    value = @Tag(
        name = "Airlines", 
        description = "All the airlines methods"))
@Callback(
    name = "availabilityCallback",
    callbackUrlExpression="http://localhost:9080/oas3-airlines/availability",
    operation = @Operation(
        method = "get",
        summary = "Retrieve all available flights",
        operationId = "getFlights",
        tags = {"availability"},
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "successful operation",
                content = @Content(
                    mediaType = "applictaion/json",
                    schema = @Schema(
                        type = "array",
                        implementation = Flight.class
                    )
                )
            ),
            @APIResponse(
                responseCode = "404",
                description = "No available flights found",
                content = @Content(
                    mediaType = "n/a")
            )
        }
    )
)
public class AirlinesResource {
    private static Map<Integer, Airline> airlines = new ConcurrentHashMap<Integer, Airline>();
    
    static {
        airlines.put(1, new Airline("Acme Air", "1-888-1234-567"));
        airlines.put(2, new Airline("Acme Air Partner", "1-855-1284-563"));
        airlines.put(3, new Airline("PanAm 5000", "1-855-1267-561"));
    }
    
    @Tag(
        name = "getAirlines",
        description = "method to retrieve all airlines"
    )
    public static Airline getRandomAirline(){
        return airlines.get(JAXRSApp.getRandomNumber(2,1));
    }
    
    @GET
    @Operation(
        method = "get",
        summary = "Retrieve all available airlines",
        operationId = "getAirlines",
        tags = {"airlines"},
        responses = {
            @APIResponse(
                responseCode = "200",
                description = "successful operation",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        type = "array",
                        implementation = Airline.class
                    )
                )
            ),
            @APIResponse(
                responseCode = "404",
                description = "No airlines found",
                content = @Content(
                    mediaType = "n/a"
                )
            )
        })
    @Produces("application/json")
    public Response getAirlines(){
        return Response.ok().entity(airlines.values()).build();
    }
    
}
