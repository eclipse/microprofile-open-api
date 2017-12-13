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

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.responses.APIResponses;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.callbacks.Callback;
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.links.Link;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.tags.Tag;

public class AirlinesOASFilter implements OASFilter {

    @Override
    public PathItem filterPathItem(PathItem pathItem){
        if(pathItem.getGET() != null && "Retrieve all available flights".equals(pathItem.getGET().getSummary())){
            pathItem.PUT(OASFactory.createObject(Operation.class).
                    summary("filterPathItem - added put operation")
                    .responses(OASFactory.createObject(APIResponses.class).addApiResponse("200", 
                            OASFactory.createObject(APIResponse.class).description("successfully put airlines"))));
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
        return operation;
    }
    
    @Override
    public Parameter filterParameter(Parameter parameter) {
        if("The user name for login".equals(parameter.getDescription())){
            parameter.setDescription("filterParameter - The user name for login");
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
        return apiResponse;
    }
    
    @Override
    public Schema filterSchema(Schema schema) {
        if("subscription information".equals(schema.getDescription())){
            schema.setDescription("filterSchema - subscription information");
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
        return server;
    }
    
    @Override
    public Tag filterTag(Tag tag) {
        return tag;
    }
    
    @Override
    public Link filterLink(Link link) {
        return link;
    }

    @Override
    public Callback filterCallback(Callback callback) {
        return callback;
    }

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        //Change to ensure that this method is called last
        //override the operation summary that was previously overridden in filterOperation method
        openAPI.getPaths().get("/bookings/{id}").getPUT().setSummary("filterOpenAPI - Update a booking with ID");
    }
}
