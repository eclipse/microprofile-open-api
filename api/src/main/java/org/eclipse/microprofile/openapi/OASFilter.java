/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.openapi;

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

/**
 * This interface allows application developers to filter different parts of the OpenAPI model tree.
 * 
 * A common scenario is to dynamically augment (update or remove) OpenAPI elements based on the environment
 * that the application is currently in.  
 * 
 * The registration of this filter is controlled by setting the key <b>mp.openapi.filter</b> using
 * one of the configuration sources specified in <a href="https://github.com/eclipse/microprofile-config">MicroProfile Config</a>.
 * The value is the fully qualified name of the filter implementation, which needs to be visible to the application's classloader.
 *
 */
public interface OASFilter {    
	
    /**
     * Allows filtering of a particular PathItem.  Implementers of this method can choose to update the given PathItem,
     * pass it back as-is, or return null if removing this PathItem.
     * 
     * @return the PathItem to be used or null 
     */
    PathItem filterPathItem(PathItem pathItem);
    
    /**
     * Allows filtering of a particular Operation.  Implementers of this method can choose to update the given Operation,
     * pass it back as-is, or return null if removing this Operation.
     * 
     * @return the Operation to be used or null 
     */
    Operation filterOperation(Operation operation);
    
    /**
     * Allows filtering of a particular Parameter.  Implementers of this method can choose to update the given Parameter,
     * pass it back as-is, or return null if removing this Parameter.
     * 
     * @return the Parameter to be used or null 
     */
    Parameter filterParameter(Parameter parameter);
    
    /**
     * Allows filtering of a particular Header.  Implementers of this method can choose to update the given Header,
     * pass it back as-is, or return null if removing this Header.
     * 
     * @return the Header to be used or null 
     */
    Header filterHeader(Header header);
    
    /**
     * Allows filtering of a particular RequestBody.  Implementers of this method can choose to update the given RequestBody,
     * pass it back as-is, or return null if removing this RequestBody.
     * 
     * @return the RequestBody to be used or null 
     */
    RequestBody filterRequestBody(RequestBody requestBody);
    
    /**
     * Allows filtering of a particular APIResponse.  Implementers of this method can choose to update the given APIResponse,
     * pass it back as-is, or return null if removing this APIResponse.
     * 
     * @return the APIResponse to be used or null 
     */
    APIResponse filterAPIResponse(APIResponse apiResponse);
    
    /**
     * Allows filtering of a particular Schema.  Implementers of this method can choose to update the given Schema,
     * pass it back as-is, or return null if removing this Schema.
     * 
     * @return the Schema to be used or null 
     */
    Schema<?> filterSchema(Schema<?> schema);
    
    /**
     * Allows filtering of a particular SecurityScheme.  Implementers of this method can choose to update the given SecurityScheme,
     * pass it back as-is, or return null if removing this SecurityScheme.
     * 
     * @return the SecurityScheme to be used or null 
     */
    SecurityScheme filterSecurityScheme(SecurityScheme securityScheme);
    
    /**
     * Allows filtering of a particular Server.  Implementers of this method can choose to update the given Server,
     * pass it back as-is, or return null if removing this Server.
     * 
     * @return the Server to be used or null 
     */
    Server filterServer(Server server);
    
    /**
     * Allows filtering of a particular Tag.  Implementers of this method can choose to update the given Tag,
     * pass it back as-is, or return null if removing this Tag.
     * 
     * @return the Tag to be used or null 
     */
    Tag filterTag(Tag tag);
    
    /**
     * Allows filtering of a particular Link.  Implementers of this method can choose to update the given Link,
     * pass it back as-is, or return null if removing this Link.
     * 
     * @return the Link to be used or null 
     */
    Link filterLink(Link link);
    
    /**
     * Allows filtering of a particular Callback.  Implementers of this method can choose to update the given Callback,
     * pass it back as-is, or return null if removing this Callback.
     * 
     * @return the Callback to be used or null 
     */
    Callback filterCallback(Callback callback);
    
    /**
     * Allows filtering of the singleton OpenAPI element.  Implementers of this method can choose to update this element, or
     * do nothing if no change is required.  Note that one cannot remove this element from the model tree, hence the return type
     * of void.  
     * 
     * @return the OpenAPI object to be used
     */
    void filterOpenAPI(OpenAPI openAPI);
}
