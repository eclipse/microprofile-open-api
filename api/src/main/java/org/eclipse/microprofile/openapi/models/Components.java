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
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#componentsObject"
 */
public interface Components extends Constructible {

	/**
	 * returns the schemas property from a Components instance.
	 *
	 * @return Map&lt;String, Schema&gt; schemas
	 **/
	Map<String, Schema> getSchemas();

	/**
	 * sets this Components' schemas property to the given schema.
	 * 
	 * @param Map&lt;String, Schema&gt;schemas
	 */
	void setSchemas(Map<String, Schema> schemas);

	/**
	 * sets this Components' schemas property to the given schema and returns this Components.
	 * 
	 * @param Map&lt;String, Schema&gt;schemas
	 * @return Components
	 */
	Components schemas(Map<String, Schema> schemas);

	/**
	 * Adds the given schema to this Components list of schemas, with the given key as its key.
	 *
	 * @param String key
	 * @param Schema schemasItem
	 * @return Components
	 */
	Components addSchemas(String key, Schema schemasItem);

	/**
	 * returns the responses property from a Components instance.
	 *
	 * @return Map&lt;String, ApiResponse&gt; responses
	 **/

	Map<String, ApiResponse> getResponses();

	/**
	 * sets this Components' responses property to the given map of ApiResponses.
	 *
	 * @param Map&lt;String, ApiResponse&gt;respones
	 */
	void setResponses(Map<String, ApiResponse> responses);

	/**
	 * sets this Components' responses property to the given map of ApiResponses and
	 * return this instance of Components.
	 *
	 * @param Map&lt;String, ApiResponse&gt;responses
	 */
	Components responses(Map<String, ApiResponse> responses);

	/**
	 * Adds the given response to this Components' map of responses, with the given key as its key.
	 *
	 * @param String key
	 * @param ApiResponse responsesItem
	 * @return Components instance
	 */
	Components addResponses(String key, ApiResponse responsesItem);

	/**
	 * returns the parameters property from a Components instance.
	 *
	 * @return Map&lt;String, Parameter&gt; parameters
	 **/

	Map<String, Parameter> getParameters();

	/**
	 * sets this Components' parameters property to the given map of Parameters.
	 *
	 * @param Map&lt;String, Parameter&gt;parameters
	 */
	void setParameters(Map<String, Parameter> parameters);

	/**
	 * sets this Components' parameters property to the given map of Parameters and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, Parameter&gt;parameters
	 * @return Components
	 */
	Components parameters(Map<String, Parameter> parameters);

	/**
	 * Adds the given parameters to this Components' map of parameters, with the given key as its key.
	 *
	 * @param String key
	 * @param Parameter parametersItem
	 * @return Components
	 */
	Components addParameters(String key, Parameter parametersItem);

	/**
	 * returns the examples property from a Components instance.
	 *
	 * @return Map&lt;String, Example&gt; examples
	 **/

	Map<String, Example> getExamples();

	/**
	 * sets this Components' examples property to the given map of Examples.
	 *
	 * @param Map&lt;String, Example&gt;examples
	 */
	void setExamples(Map<String, Example> examples);

	/**
	 * sets this Components' examples property to the given map of Examples and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, Example&gt;examples
	 * @return Components
	 */
	Components examples(Map<String, Example> examples);

	/**
	 * Adds the given Example to this Components' map of Examples, with the given key as its key.
	 *
	 * @param String key
	 * @param Example examplesItem
	 * @return Components
	 */
	Components addExamples(String key, Example examplesItem);

	/**
	 * returns the requestBodies property from a Components instance.
	 *
	 * @return Map&lt;String, RequestBody&gt; requestBodies
	 **/

	Map<String, RequestBody> getRequestBodies();

	/**
	 * sets this Components' requestBodies property to the given map of RequestBodies.
	 *
	 * @param Map&lt;String, RequestBody&gt;requestBodies
	 */
	void setRequestBodies(Map<String, RequestBody> requestBodies);

	/**
	 * sets this Components' requestBodies property to the given map of RequestBodies and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, RequestBody&gt;requestBodies
	 * @return Components
	 */
	Components requestBodies(Map<String, RequestBody> requestBodies);

	/**
	 * Adds the given RequestBody to this Components' map of RequestBodies, with the given key as its key.
	 *
	 * @param String key
	 * @param RequestBody requestBodiesItem
	 * @return Components
	 */
	Components addRequestBodies(String key, RequestBody requestBodiesItem);

	/**
	 * returns the headers property from a Components instance.
	 *
	 * @return Map&lt;String, Header&gt; headers
	 **/

	Map<String, Header> getHeaders();

	/**
	 * sets this Components' headers property to the given map of Headers.
	 *
	 * @param Map&lt;String, Header&gt;headers
	 */
	void setHeaders(Map<String, Header> headers);

	/**
	 * sets this Components' headers property to the given map of Headers and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, Header&gt;headers
	 * @return Components
	 */
	Components headers(Map<String, Header> headers);

	/**
	 * Adds the given Header to this Components' map of Headers, with the given key as its key.
	 *
	 * @param String key
	 * @param Header headersItem
	 * @return Components
	 */
	Components addHeaders(String key, Header headersItem);

	/**
	 * returns the securitySchemes property from a Components instance.
	 *
	 * @return Map&lt;String, SecurityScheme&gt; securitySchemes
	 **/

	Map<String, SecurityScheme> getSecuritySchemes();

	/**
	 * sets this Components' securitySchemes property to the given map of SecuritySchemes.
	 *
	 * @param Map&lt;String, SecurityScheme&gt;securitySchemes
	 */
	void setSecuritySchemes(Map<String, SecurityScheme> securitySchemes);

	/**
	 * sets this Components' securitySchemes property to the given map of SecuritySchemes and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, SecurityScheme&gt;securitySchemes
	 * @return Components
	 */
	Components securitySchemes(Map<String, SecurityScheme> securitySchemes);

	/**
	 * Adds the given SecurityScheme to this Components' map of SecuritySchemes, with the given key as its key.
	 *
	 * @param String key
	 * @param SecurityScheme securitySchemesItem
	 * @return Components
	 */
	Components addSecuritySchemes(String key, SecurityScheme securitySchemesItem);

	/**
	 * returns the links property from a Components instance.
	 *
	 * @return Map&lt;String, Link&gt; links
	 **/

	Map<String, Link> getLinks();

	/**
	 * sets this Components' links property to the given map of Links.
	 *
	 * @param Map&lt;String, Link&gt;links
	 */
	void setLinks(Map<String, Link> links);

	/**
	 * sets this Components' links property to the given map of Links and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, Link&gt;links
	 * @return Components
	 */
	Components links(Map<String, Link> links);

	/**
	 * Adds the given Link to this Components' map of Links, with the given key as its key.
	 *
	 * @param String key
	 * @param Link linksItem
	 * @return Components
	 */
	Components addLinks(String key, Link linksItem);

	/**
	 * returns the callbacks property from a Components instance.
	 *
	 * @return Map&lt;String, Callback&gt; callbacks
	 **/

	Map<String, Callback> getCallbacks();

	/**
	 * sets this Components' callbacks property to the given map of Callbacks.
	 *
	 * @param Map&lt;String, Callback&gt;callbacks
	 */
	void setCallbacks(Map<String, Callback> callbacks);

	/**
	 * sets this Components' callbacks property to the given map of Callbacks and
	 * returns this instance of Components.
	 *
	 * @param Map&lt;String, Callback&gt;callbacks
	 * @return Components
	 */
	Components callbacks(Map<String, Callback> callbacks);

	/**
	 * Adds the given Callback to this Components' map of Callbacks, with the given key as its key.
	 *
	 * @param String key
	 * @param Callback callbacksItem
	 * @return Components
	 */
	Components addCallbacks(String key, Callback callbacksItem);

	/**
	 * returns the extensions property from a Components instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 **/
	Map<String, Object> getExtensions();

	/**
	 * Adds the given Object to this Components' map of extensions, with the given key as its key.
	 *
	 * @param String key
	 * @param Object value
	 * @return Components
	 */
	void addExtension(String name, Object value);

	/**
	 * sets this Components' extensions property to the given map of extensions.
	 *
	 * @param Map&lt;String, Object&gt;extensions
	 */
	void setExtensions(Map<String, Object> extensions);

}