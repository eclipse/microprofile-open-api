/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 * Copyright 2017 SmartBear Software
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

package org.eclipse.microprofile.openapi.models;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.callbacks.Callback;
import org.eclipse.microprofile.openapi.models.examples.Example;
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.links.Link;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.ApiResponse;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;

/**
 * Components
 * <p>
 * Holds a set of reusable objects for different aspects of the API specification. All objects defined within this object will have no effect on the
 * API unless they are explicitly referenced from properties outside the components object.
 * <p>
 * All the fields are indexed by keys that must match the regular expression: <code>^[a-zA-Z0-9\.\-_]+$</code>.
 * <p>
 * Key Examples:
 * <ul>
 * <li>User</li>
 * <li>User_1</li>
 * <li>User_Name</li>
 * <li>user-name</li>
 * <li>my.org.User</li>
 * </ul>
 * 
 * @see <a href= "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#componentsObject"> OpenAPI Specification Components
 *      Object</a>
 */
public interface Components extends Constructible, Extensible {

    /**
     * Returns the schemas property from a Components instance.
     *
     * @return a Map containing the keys and the reusable schemas for this OpenAPI document
     **/
    Map<String, Schema> getSchemas();

    /**
     * Sets this Components' schemas property to the given Map containing keys and reusable schema objects.
     * 
     * @param schemas a Map containing keys and reusable schema objects
     */
    void setSchemas(Map<String, Schema> schemas);

    /**
     * Sets this Components' schemas property to the given Map containing keys and reusable schemas.
     * 
     * @param schemas a Map containing keys and reusable schemas
     * @return the current Components object
     */
    Components schemas(Map<String, Schema> schemas);

    /**
     * Adds the given schema to this Components' list of schemas with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param schemasItem a reusable schema object
     * @return the current Components object
     */
    Components addSchemas(String key, Schema schemasItem);

    /**
     * Returns the responses property from a Components instance.
     *
     * @return a Map containing the keys and the reusable responses from API operations for this OpenAPI document
     **/
    Map<String, ApiResponse> getResponses();

    /**
     * Sets this Components' responses property to the given Map containing keys and reusable response objects.
     *
     * @param responses a Map containing keys and reusable response objects
     */
    void setResponses(Map<String, ApiResponse> responses);

    /**
     * Sets this Components' responses property to the given Map containing keys and reusable response objects.
     *
     * @param responses a Map containing keys and reusable response objects
     * @return the current Components object
     */
    Components responses(Map<String, ApiResponse> responses);

    /**
     * Adds the given response to this Components' map of responses with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param responsesItem a reusable response object
     * @return the current Components object
     */
    Components addResponses(String key, ApiResponse responsesItem);

    /**
     * Returns the parameters property from a Components instance.
     *
     * @return a Map containing the keys and the reusable parameters of API operations for this OpenAPI document
     **/
    Map<String, Parameter> getParameters();

    /**
     * Sets this Components' parameters property to the given Map containing keys and reusable parameter objects.
     *
     * @param parameters a Map containing keys and reusable parameter objects
     */
    void setParameters(Map<String, Parameter> parameters);

    /**
     * Sets this Components' parameters property to the given Map containing keys and reusable parameter objects.
     *
     * @param parameters a Map containing keys and reusable parameter objects
     * @return the current Components object
     */
    Components parameters(Map<String, Parameter> parameters);

    /**
     * Adds the given parameter to this Components' map of parameters with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param parametersItem a reusable parameter object
     * @return the current Components object
     */
    Components addParameters(String key, Parameter parametersItem);

    /**
     * Returns the examples property from a Components instance.
     *
     * @return a Map containing the keys and the reusable examples for this OpenAPI document
     **/
    Map<String, Example> getExamples();

    /**
     * Sets this Components' examples property to the given Map containing keys and reusable example objects.
     *
     * @param examples a Map containing keys and reusable example objects
     */
    void setExamples(Map<String, Example> examples);

    /**
     * Sets this Components' examples property to the given Map containing keys and reusable example objects.
     *
     * @param examples a Map containing keys and reusable example objects
     * @return the current Components object
     */
    Components examples(Map<String, Example> examples);

    /**
     * Adds the given example to this Components' map of examples with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param examplesItem a reusable example object
     * @return the current Components object
     */
    Components addExamples(String key, Example examplesItem);

    /**
     * Returns the requestBodies property from a Components instance.
     *
     * @return a Map containing the keys and the reusable request bodies for this OpenAPI document
     **/
    Map<String, RequestBody> getRequestBodies();

    /**
     * Sets this Components' requestBodies property to the given Map containing keys and reusable request body objects.
     *
     * @param requestBodies a Map containing the keys and reusable request body objects
     **/
    void setRequestBodies(Map<String, RequestBody> requestBodies);

    /**
     * Sets this Components' requestBodies property to the given Map containing keys and reusable request body objects.
     *
     * @param requestBodies a Map containing the keys and reusable request body objects
     * @return the current Components object
     */
    Components requestBodies(Map<String, RequestBody> requestBodies);

    /**
     * Adds the given request body to this Components' map of request bodies with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param requestBodiesItem a reusable request body object
     * @return the current Components object
     */
    Components addRequestBodies(String key, RequestBody requestBodiesItem);

    /**
     * Returns the headers property from a Components instance.
     *
     * @return a Map containing the keys and the reusable headers for this OpenAPI document
     **/
    Map<String, Header> getHeaders();

    /**
     * Sets this Components' headers property to the given Map containing keys and reusable header objects.
     *
     * @param headers a Map containing the keys and reusable header objects
     */
    void setHeaders(Map<String, Header> headers);

    /**
     * Sets this Components' headers property to the given Map containing keys and reusable header objects.
     *
     * @param headers a Map containing the keys and reusable header objects
     * @return the current Components object
     */
    Components headers(Map<String, Header> headers);

    /**
     * Adds the given header to this Components' map of headers with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param headersItem a reusable header object
     * @return the current Components object
     */
    Components addHeaders(String key, Header headersItem);

    /**
     * Returns the securitySchemes property from a Components instance.
     *
     * @return a Map containing the keys and the reusable security schemes for this OpenAPI document
     **/
    Map<String, SecurityScheme> getSecuritySchemes();

    /**
     * Sets this Components' securitySchemes property to the given Map containing keys and reusable security scheme objects.
     *
     * @param securitySchemes a Map containing the keys and reusable security scheme objects
     */
    void setSecuritySchemes(Map<String, SecurityScheme> securitySchemes);

    /**
     * Sets this Components' securitySchemes property to the given Map containing keys and reusable security scheme objects.
     *
     * @param securitySchemes a Map containing the keys and reusable security scheme objects
     * @return the current Components object
     */
    Components securitySchemes(Map<String, SecurityScheme> securitySchemes);

    /**
     * Adds the given security scheme to this Components' map of security schemes with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param securitySchemesItem a reusable security scheme object
     * @return the current Components object
     */
    Components addSecuritySchemes(String key, SecurityScheme securitySchemesItem);

    /**
     * Returns the links property from a Components instance.
     *
     * @return a Map containing the keys and the reusable links for this OpenAPI document
     **/
    Map<String, Link> getLinks();

    /**
     * Sets this Components' links property to the given Map containing keys and reusable link objects.
     *
     * @param links a Map containing the keys and reusable link objects
     */
    void setLinks(Map<String, Link> links);

    /**
     * Sets this Components' links property to the given Map containing keys and reusable link objects.
     *
     * @param links a Map containing the keys and reusable link objects
     * @return the current Components object
     */
    Components links(Map<String, Link> links);

    /**
     * Adds the given link to this Components' map of links with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param linksItem a reusable link object
     * @return the current Components object
     */
    Components addLinks(String key, Link linksItem);

    /**
     * Returns the callbacks property from a Components instance.
     *
     * @return a Map containing the keys and the reusable callbacks for this OpenAPI document
     **/
    Map<String, Callback> getCallbacks();

    /**
     * Sets this Components' callbacks property to the given Map containing keys and reusable callback objects.
     *
     * @param callbacks a Map containing the keys and reusable callback objects
     */
    void setCallbacks(Map<String, Callback> callbacks);

    /**
     * Sets this Components' callbacks property to the given Map containing keys and reusable callback objects.
     *
     * @param callbacks a Map containing the keys and reusable callback objects
     * @return the current Components object
     */
    Components callbacks(Map<String, Callback> callbacks);

    /**
     * Adds the given callback to this Components' map of callbacks with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param callbacksItem a reusable callback object
     * @return the current Components object
     */
    Components addCallbacks(String key, Callback callbacksItem);

}