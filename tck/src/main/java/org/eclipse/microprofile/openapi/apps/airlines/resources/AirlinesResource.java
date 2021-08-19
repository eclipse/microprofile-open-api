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

package org.eclipse.microprofile.openapi.apps.airlines.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.callbacks.CallbackOperation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.extensions.Extensions;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.eclipse.microprofile.openapi.apps.airlines.JAXRSApp;
import org.eclipse.microprofile.openapi.apps.airlines.model.Airline;
import org.eclipse.microprofile.openapi.apps.airlines.model.Flight;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("")
@Schema(name = "Airline Booking API")
@Tags(value = @Tag(name = "Airlines", description = "All the airlines methods"))
@Callback(name = "availabilityCallback", callbackUrlExpression = "http://localhost:9080/oas3-airlines/availability", operations = @CallbackOperation(method = "get", summary = "Retrieve available flights.", responses = {
        @APIResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "applictaion/json", schema = @Schema(type = SchemaType.ARRAY, implementation = Flight.class))),
        @APIResponse(responseCode = "404", description = "No available flights found", content = @Content(mediaType = "n/a"))
}))
public class AirlinesResource {
    private static Map<Integer, Airline> airlines = new ConcurrentHashMap<Integer, Airline>();

    static {
        airlines.put(1, new Airline("Acme Air", "1-888-1234-567"));
        airlines.put(2, new Airline("Acme Air Partner", "1-855-1284-563"));
        airlines.put(3, new Airline("PanAm 5000", "1-855-1267-561"));
    }

    public static Airline getRandomAirline() {
        return airlines.get(JAXRSApp.getRandomNumber(2, 1));
    }

    @GET
    @APIResponse(ref = "FoundAirlines")
    @APIResponse(responseCode = "404", description = "No airlines found", content = @Content(mediaType = "n/a"))
    @Operation(summary = "Retrieve all available airlines", operationId = "getAirlines")
    @Produces("application/json")
    @Extensions({
            @Extension(name = "x-string-property", value = "string-value"),
            @Extension(name = "x-boolean-property", value = "true", parseValue = true),
            @Extension(name = "x-number-property", value = "117", parseValue = true),
            @Extension(name = "x-object-property", value = "{ \"property-1\" : \"value-1\", \"property-2\" : \"value-2\", \"property-3\" : { \"prop-3-1\" : 17, \"prop-3-2\" : true } }", parseValue = true),
            @Extension(name = "x-string-array-property", value = "[ \"one\", \"two\", \"three\" ]", parseValue = true),
            @Extension(name = "x-object-array-property", value = "[ { \"name\": \"item-1\" }, { \"name\" : \"item-2\" } ]", parseValue = true)
    })
    public Response getAirlines() {
        return Response.ok().entity(airlines.values()).build();
    }

}
