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
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.DiscriminatorMapping;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import org.eclipse.microprofile.openapi.apps.petstore.data.StoreData;
import org.eclipse.microprofile.openapi.apps.petstore.model.BadOrder;
import org.eclipse.microprofile.openapi.apps.petstore.model.Order;
import org.eclipse.microprofile.openapi.apps.petstore.model.Pet;
import org.eclipse.microprofile.openapi.apps.petstore.exception.NotFoundException;
import org.eclipse.microprofile.openapi.apps.petstore.data.PetData;

import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/store")
@Schema(name="/store")
@Produces({"application/json", "application/xml"})
@SecuritySchemes(
    value = {
        @SecurityScheme(
            securitySchemeName = "storeOpenIdConnect",
            type = SecuritySchemeType.OPENIDCONNECT,
            description = "openId Connect authentication to access the pet store resource",
            openIdConnectUrl = "https://petstoreauth.com:4433/oidc/petstore/oidcprovider/authorize"
        ),
        @SecurityScheme(
            securitySchemeName = "storeHttp",
            type = SecuritySchemeType.HTTP,
            description = "Basic http authentication to access the pet store resource",
            scheme = "basic"
        )
    }
)
@SecurityRequirements(
    value = {
        @SecurityRequirement(
            name  = "storeOpenIdConnect",
            scopes = {"write:store", "read:store"}
        ),
        @SecurityRequirement(
            name = "storeHttp"
        )
    }
)
@Extension(name = "x-mp-type1", value = "true")
@Extension(name = "x-mp-type2", value = "false")
public class PetStoreResource {
    static StoreData storeData = new StoreData();
    static PetData petData = new PetData();

    @GET
    @Path("/inventory")
    @Produces({"application/json", "application/xml"})
    @APIResponse(
            responseCode = "200", 
            description = "successful operation"
        )
    @Operation(
        summary = "Returns pet inventories by status", 
        description = "Returns a map of status codes to quantities"
    )
    public java.util.Map<String, Integer> getInventory() {
        return petData.getInventoryByStatus();
    }

    @GET
    @Path("/order/{orderId}")
    @Operation(
        summary = "Find purchase order by ID",
        description = "For valid response try integer IDs with value between the integers of 1 and 10. Other values will generated exceptions"
    )
    @APIResponse(
            responseCode = "200", 
            description = "successful operation",
            content = @Content(
                schema = @Schema(implementation = Order.class, 
                  allOf = { Order.class, Pet.class },
                  not = BadOrder.class )
            )
        )
    @APIResponse(
            responseCode = "400", 
            description = "Invalid ID supplied",
            content = @Content(
                schema = @Schema(implementation = Order.class)
            )
        )
    @APIResponse(
            responseCode = "404", 
            description = "Order not found",
            content = @Content(
                schema = @Schema(implementation = Order.class, 
                  anyOf = { Order.class, BadOrder.class },
                  discriminatorProperty = "id", 
                  discriminatorMapping = @DiscriminatorMapping(value = "0", schema = BadOrder.class) )
            )
        )
    @APIResponse(
            responseCode = "900", 
            description = "Invalid",
            content = @Content(
                schema = @Schema(implementation = Order.class, hidden = true)
            )
        )
    public Response getOrderById(
        @Parameter(
            name = "orderId",
            description = "ID of pet that needs to be fetched", 
            schema = @Schema(
                type = SchemaType.INTEGER, 
                minimum = "1", 
                maximum = "10"), 
            required = true
        )
        @PathParam("orderId") Long orderId)
    throws NotFoundException {
        Order order = storeData.findOrderById(orderId);
        if (null != order) {
            return Response.ok().entity(order).build();
        } 
        else {
            throw new NotFoundException(404, "Order not found");
        }
    }

    @POST
    @Path("/order")
    @Operation(
        summary = "Place an order for a pet"
    )
    @APIResponse(
            responseCode = "200", 
            description = "successful operation"
        )
    @APIResponse(
            responseCode = "400", 
            description = "Invalid Order"
            ) 
    public Order placeOrder(
        @Parameter(
            description = "order placed for purchasing the pet",
            required = true
        ) 
        Order order) {
            storeData.placeOrder(order);
            return storeData.placeOrder(order);
        }

    @DELETE
    @Path("/order/{orderId}")
    @APIResponse(
            responseCode = "400", 
            description = "Invalid ID supplied"
            )
    @APIResponse(
            responseCode = "404", 
            description = "Order not found"
            ) 
    @Operation(
        summary = "Delete purchase order by ID",
        description = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors"
    )
    public Response deleteOrder(
        @Parameter(
            name = "orderId",
            description = "ID of the order that needs to be deleted",  
            schema = @Schema(
                type = SchemaType.INTEGER, 
                minimum = "1"
            ), 
            required = true
        )
        @PathParam("orderId") Long orderId) {
            if (storeData.deleteOrder(orderId)) {
                return Response.ok().entity("").build();
            } 
            else {
                return Response.status(Response.Status.NOT_FOUND).entity("Order not found").build();
            }
        }
}
