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

package org.eclipse.microprofile.openapi.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.callbacks.Callback;
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.links.Link;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.responses.APIResponses;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.tags.Tag;

public class AirlinesOASFilter implements OASFilter {

    @Override
    public PathItem filterPathItem(PathItem pathItem){
        if(pathItem.getGET() != null && "Retrieve all available flights".equals(pathItem.getGET().getSummary())){
            //Add new operation
            pathItem.PUT(OASFactory.createObject(Operation.class).
                    summary("filterPathItem - added put operation")
                    .responses(OASFactory.createObject(APIResponses.class).addAPIResponse("200", 
                            OASFactory.createObject(APIResponse.class).description("filterPathItem - successfully put airlines"))));
            
            //Spec states : All filterable descendant elements of a filtered element must be called before its ancestor
            //Override the operatioId value that was previously overridden by the filterOperation method
            pathItem.getGET().setOperationId("filterPathItemGetFlights");
        }
        
        if (pathItem.getPOST() != null && "createBooking".equals(pathItem.getPOST().getOperationId())) {
            Map<String, Callback> callbacks = pathItem.getPOST().getCallbacks();
            if (callbacks.containsKey("bookingCallback")) {
                Callback callback = callbacks.get("bookingCallback");
                if (callback.hasPathItem("http://localhost:9080/airlines/bookings")
                        && callback.getPathItem("http://localhost:9080/airlines/bookings").getGET() != null) {
                    if ("child - Retrieve all bookings for current user".equals(callback.getPathItem("http://localhost:9080/airlines/bookings")
                            .getGET().getDescription())) {   
                        callback.getPathItem("http://localhost:9080/airlines/bookings").getGET()
                        .setDescription("parent - Retrieve all bookings for current user"); 
                    }
                }
            }   
        }
        
        return pathItem;
    }
    
    @Override
    public Operation filterOperation(Operation operation) {
        if("Get a booking with ID".equals(operation.getSummary())){
            operation.setSummary("filterOperation - Get a booking with ID");  
        } 
        else if("Update a booking with ID".equals(operation.getSummary())){
            operation.setSummary("filterOperation - Update a booking with ID");  
        }
        else if("Retrieve all available flights".equals(operation.getSummary())){
            operation.setOperationId("filterOperationGetFlights");
        }
        
        List<String> tags = operation.getTags();
        if (tags != null) {
            if (tags.contains("Bookings")) {
                tags = new ArrayList<>(tags);
                tags.set(tags.indexOf("Bookings"), "parent - Bookings");
                operation.setTags(tags);
            }
        }
        return operation;
    }
    
    @Override
    public Parameter filterParameter(Parameter parameter) {
        if("The user name for login".equals(parameter.getDescription())){
            parameter.setDescription("filterParameter - The user name for login");
        }
        else if("The password for login in clear text".equals(parameter.getDescription())){
            return null; //remove parameter
        }
        return parameter;
    }
    
    @Override
    public Header filterHeader(Header header) {
        if("Maximum rate".equals(header.getDescription())){
            header.setDescription("filterHeader - Maximum rate");
        }
        return header;
    }
    
    @Override
    public RequestBody filterRequestBody(RequestBody requestBody) {
        if("Create a new booking with the provided information.".equals(requestBody.getDescription())){
            requestBody.setDescription("filterRequestBody - Create a new booking with the provided information.");
        }
        return requestBody;
    }
    
    @Override
    public APIResponse filterAPIResponse(APIResponse apiResponse) {
        if("subscription successfully created".equals(apiResponse.getDescription())){
            apiResponse.setDescription("filterAPIResponse - subscription successfully created");
        }
        
        // testing child before parent filtering
        Content content = apiResponse.getContent();
        if (content != null) {
            if (content.hasMediaType("application/json")) {
                Schema schema = content.getMediaType("application/json").getSchema();
                if ("child - id of the new review".equals(schema.getDescription())) {
                    schema.setDescription("parent - id of the new review");
                }
            }
        }
        
        return apiResponse;
    }
    
    @Override
    public Schema filterSchema(Schema schema) {
        if("subscription information".equals(schema.getDescription())){
            schema.setDescription("filterSchema - subscription information");
        }
        
        // testing child before parent filtering
        if ("id of the new review".equals(schema.getDescription())) {
            schema.setDescription("child - id of the new review");
        }
        
        return schema;
    }
    
    @Override
    public SecurityScheme filterSecurityScheme(SecurityScheme securityScheme) {
        if("Security Scheme for booking resource".equals(securityScheme.getDescription())){
            securityScheme.setDescription("filterSecurityScheme - Security Scheme for booking resource");
        }
        return securityScheme;
    }
    
    @Override
    public Server filterServer(Server server) {
        if("The production API server".equals(server.getDescription())){
            server.description("filterServer - The production API server");
        }
        return server;
    }
    
    @Override
    public Tag filterTag(Tag tag) {
        if("Operations about user".equals(tag.getDescription())){
            tag.setDescription("filterTag - Operations about user");
        }
        
        if ("Bookings".equals(tag.getName())) {
            tag.setName("child - Bookings");
        }
        return tag;
    }
    
    @Override
    public Link filterLink(Link link) {
        if("The username corresponding to provided user id".equals(link.getDescription())){
            link.setDescription("filterLink - The username corresponding to provided user id");
        }
        return link;
    }

    @Override
    public Callback filterCallback(Callback callback) {
        if(callback.hasPathItem("{$request.query.callbackUrl}/data") && callback.getPathItem("{$request.query.callbackUrl}/data").getPOST() != null){
            callback.getPathItem("{$request.query.callbackUrl}/data").getPOST().setDescription("filterCallback - callback post operation");
        }
        
        if (callback.hasPathItem("http://localhost:9080/airlines/bookings") 
                && callback.getPathItem("http://localhost:9080/airlines/bookings").getGET() != null) {
            callback.getPathItem("http://localhost:9080/airlines/bookings").getGET().setDescription("child - Retrieve all bookings for current user");
        }
        return callback;
    }

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        //Spec states : The filterOpenAPI method must be the last method called on a filter (which is just a specialization of the first exception).
        //To ensure that this method is called last, override the operation summary that was previously overridden in filterOperation method
        openAPI.getPaths().getPathItem("/bookings/{id}").getPUT().setSummary("filterOpenAPI - Update a booking with ID");
    }
}
