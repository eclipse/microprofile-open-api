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

import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.callbacks.Callback;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.ApiResponses;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.models.servers.Server;

/**
 * Operation
 * <p>
 * Describes a single API operation on a path.
 * <p>
 * Fixed Fields
 * <table border=1 cellpadding="8" style="border-collapse: collapse">
 * <tr>
 * <th>Field Name</th>
 * <th>Type</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>tags</td>
 * <td>[string]</td>
 * <td>A list of tags for API documentation control. Tags can be used for
 * logical grouping of operations by resources or any other qualifier.</td>
 * </tr>
 * <tr>
 * <td>summary</td>
 * <td>string</td>
 * <td>A short summary of what the operation does.</td>
 * </tr>
 * <tr>
 * <td>description</td>
 * <td>string</td>
 * <td>A verbose explanation of the operation behavior. CommonMark syntax MAY be
 * used for rich text representation.</td>
 * </tr>
 * <tr>
 * <td>externalDocs</td>
 * <td>{@link ExternalDocumentation External Documentation Object }</td>
 * <td>Additional external documentation for this operation.</td>
 * </tr>
 * <tr>
 * <td>operationId</td>
 * <td>string</td>
 * <td>Unique string used to identify the operation. The id MUST be unique among
 * all operations described in the API. Tools and libraries MAY use the
 * operationId to uniquely identify an operation, therefore, it is RECOMMENDED
 * to follow common programming naming conventions.</td>
 * </tr>
 * <tr>
 * <td>parameters</td>
 * <td>[{@link Parameter Parameter Object } | {@link Parameter Reference Object}]</td>
 * <td>A list of parameters that are applicable for this operation. If a
 * parameter is already defined at the Path Item, the new definition will
 * override it but can never remove it. The list MUST NOT include duplicated
 * parameters. A unique parameter is defined by a combination of a name and
 * location. The list can use the Reference Object to link to parameters that
 * are defined at the OpenAPI Object's components/parameters.</td>
 * </tr>
 * <tr>
 * <td>requestBody</td>
 * <td>{@link RequestBody Request Body Object } | {@link RequestBody Reference
 * Object}</td>
 * <td>The request body applicable for this operation. The requestBody is only
 * supported in HTTP methods where the HTTP 1.1 specification RFC7231 has
 * explicitly defined semantics for request bodies. In other cases where the
 * HTTP spec is vague, requestBody SHALL be ignored by consumers.</td>
 * </tr>
 * <tr>
 * <td>responses</td>
 * <td>{@link ApiResponses Responses Object }</td>
 * <td>REQUIRED. The list of possible responses as they are returned from
 * executing this operation.</td>
 * </tr>
 * <tr>
 * <td>callbacks</td>
 * <td>Map[string, {@link Callback Callback Object } | {@link Callback Reference
 * Object}]</td>
 * <td>A map of possible out-of band callbacks related to the parent operation.
 * The key is a unique identifier for the Callback Object. Each value in the map
 * is a Callback Object that describes a request that may be initiated by the
 * API provider and the expected responses. The key value used to identify the
 * callback object is an expression, evaluated at runtime, that identifies a URL
 * to use for the callback operation.</td>
 * </tr>
 * <tr>
 * <td>deprecated</td>
 * <td>boolean</td>
 * <td>Declares this operation to be deprecated. Consumers SHOULD refrain from
 * usage of the declared operation. Default value is false.</td>
 * </tr>
 * <tr>
 * <td>security</td>
 * <td>[{@link SecurityRequirement Security Requirement Object }]</td>
 * <td>A declaration of which security mechanisms can be used for this
 * operation. The list of values includes alternative security requirement
 * objects that can be used. Only one of the security requirement objects need
 * to be satisfied to authorize a request. This definition overrides any
 * declared top-level security. To remove a top-level security declaration, an
 * empty array can be used.</td>
 * </tr>
 * <tr>
 * <td>servers</td>
 * <td>[{@link Server Server Object }]</td>
 * <td>An alternative server array to service this operation. If an alternative
 * server object is specified at the Path Item Object or Root level, it will be
 * overridden by this value.</td>
 * </tr>
 * </table>
 * 
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#operationObject">OpenAPI Specification Operation Object</a>
 */
public interface Operation extends Constructible, Extensible {

	/**
	 * Returns the tags property from an Operation instance.
	 *
	 * @return a list of the operation's tags
	 **/
	List<String> getTags();

	/**
	 * Sets this Operation's tags property to the given tags.
	 *
	 * @param tags a list of tags for API documentation control 
	 **/
	void setTags(List<String> tags);

	/**
	 * Sets this Operation's tags property to the given tags.
	 *
	 * @param tags a list of tags for API documentation control 
	 * @return the current Operation object
	 **/
	Operation tags(List<String> tags);

	/**
	 * Adds the given tag to this Operation's list of tags.
	 *
	 * @param tagsItem a tag for API documentation control
	 * @return the current Operation object
	 **/
	Operation addTagsItem(String tagsItem);

	/**
	 * Returns the summary property from an Operation instance.
	 *
	 * @return a short summary of what the operation does
	 **/
	String getSummary();

	/**
	 * Sets this Operation's summary property to the given string.
	 *
	 * @param summary a short summary of what the operation does
	 **/
	void setSummary(String summary);

	/**
	 * Sets this Operation's summary property to the given string.
	 *
	 * @param summary a short summary of what the operation does
	 * @return the current Operation object
	 **/
	Operation summary(String summary);

	/**
	 * Returns the description property from an Operation instance.
	 *
	 * @return a verbose explanation of the operation behavior
	 **/
	String getDescription();

	/**
	 * Sets this Operation's description property to the given string.
	 *
	 * @param description a verbose explanation of the operation behavior
	 **/
	void setDescription(String description);

	/**
	 * Sets this Operation's description property to the given string.
	 *
	 * @param description a verbose explanation of the operation behavior
	 * @return the current Operation object
	 **/
	Operation description(String description);

	/**
	 * Returns the externalDocs property from an Operation instance.
	 *
	 * @return additional external documentation for this operation
	 **/
	ExternalDocumentation getExternalDocs();

	/**
	 * Sets this Operation's externalDocs property to the given object.
	 *
	 * @param externalDocs additional external documentation for this operation
	 **/
	void setExternalDocs(ExternalDocumentation externalDocs);

	/**
	 * Sets this Operation's externalDocs property to the given object.
	 *
	 * @param externalDocs additional external documentation for this operation
	 * @return the current Operation object
	 **/
	Operation externalDocs(ExternalDocumentation externalDocs);

	/**
	 * Returns the operationId property from an Operation instance.
	 *
	 * @return unique string used to identify the operation
	 **/
	String getOperationId();

	/**
	 * Sets this Operation's operationId property to the given string.
	 *
	 * @param operationId unique string used to identify the operation
	 **/
	void setOperationId(String operationId);

	/**
	 * Sets this Operation's operationId property to the given string.
	 *
	 * @param operationId unique string used to identify the operation
	 * @return the current Operation object
	 **/
	Operation operationId(String operationId);

	/**
	 * Returns the parameters property from an Operation instance.
	 *
	 * @return a list of parameters that are applicable for this operation
	 **/
	List<Parameter> getParameters();

	/**
	 * Sets this Operation's parameters property to the given parameter list.
	 *
	 * @param parameters a list of parameters that are applicable for this operation
	 **/
	void setParameters(List<Parameter> parameters);

	/**
	 * Sets this Operation's parameters property to the given parameter list.
	 *
	 * @param parameters a list of parameters that are applicable for this operation
	 * @return the current Operation object
	 **/
	Operation parameters(List<Parameter> parameters);

	/**
	 * Adds the given parameter item to this Operation's list of parameters.
	 *
	 * @param parametersItem a parameter that is applicable for this operation
	 * @return the current Operation object
	 **/
	Operation addParametersItem(Parameter parametersItem);

	/**
	 * Returns the requestBody property from an Operation instance.
	 *
	 * @return the request body applicable for this operation
	 **/
	RequestBody getRequestBody();

	/**
	 * Sets this Operation's requestBody property to the given object.
	 *
	 * @param requestBody the request body applicable for this operation
	 **/
	void setRequestBody(RequestBody requestBody);

	/**
	 * Sets this Operation's requestBody property to the given object.
	 *
	 * @param requestBody the request body applicable for this operation
	 * @return the current Operation object
	 **/
	Operation requestBody(RequestBody requestBody);

	/**
	 * Returns the responses property from an Operation instance.
	 *
	 * @return collection of possible responses from executing this operation
	 **/
	ApiResponses getResponses();

	/**
	 * Sets this Operation's responses property to the given responses.
	 *
	 * @param responses collection of possible responses from executing this operation
	 **/
	void setResponses(ApiResponses responses);

	/**
	 * Sets this Operation's responses property to the given responses.
	 *
	 * @param responses collection of possible responses from executing this operation
	 * @return the current Operation object
	 **/
	Operation responses(ApiResponses responses);

	/**
	 * Returns the callbacks property from an Operation instance.
	 *
	 * @return map of possible out-of-band callbacks related to the operation
	 **/
	Map<String, Callback> getCallbacks();

	/**
	 * Sets this Operation's callbacks property to the given map.
	 *
	 * @param callbacks map of possible out-of-band callbacks related to the operation.
	 * The key value must be the correct format for this field.
	 **/
	void setCallbacks(Map<String, Callback> callbacks);

	/**
	 * Sets this Operation's callbacks property to the given map.
	 *
	 * @param callbacks map of possible out-of-band callbacks related to the operation.
	 * The key value must be the correct format for this field.
	 * @return the current Operation object
	 **/
	Operation callbacks(Map<String, Callback> callbacks);

	/**
	 * Returns the deprecated property from an Operation instance.
	 *
	 * @return declaration whether this operation is deprecated
	 **/
	Boolean getDeprecated();

	/**
	 * Sets this Operation's deprecated property to the given value.
	 *
	 * @param deprecated declaration whether this operation is deprecated
	 **/
	void setDeprecated(Boolean deprecated);

	/**
	 * Sets this Operation's deprecated property to the given value.
	 *
	 * @param deprecated declaration whether this operation is deprecated
	 * @return the current Operation object
	 **/
	Operation deprecated(Boolean deprecated);

	/**
	 * Returns the security property from an Operation instance.
	 *
	 * @return a list of which security mechanisms can be used for this operation
	 **/
	List<SecurityRequirement> getSecurity();

	/**
	 * Sets this Operation's security property to the given list.
	 *
	 * @param security list of which security mechanisms can be used for this operation
	 **/
	void setSecurity(List<SecurityRequirement> security);

	/**
	 * Sets this Operation's security property to the given list.
	 *
	 * @param security list of which security mechanisms can be used for this operation
	 * @return the current Operation object
	 **/
	Operation security(List<SecurityRequirement> security);

	/**
	 * Adds the given security requirement item to this Operation's list of security mechanisms.
	 *
	 * @param securityItem security mechanism which can be used for this operation
	 * @return the current Operation object
	 **/
	Operation addSecurityItem(SecurityRequirement securityItem);

	/**
	 * Returns the servers property from an Operation instance.
	 *
	 * @return a list of servers to service this operation
	 **/
	List<Server> getServers();

	/**
	 * Sets this Operation's servers property to the given list.
	 *
	 * @param servers list of servers to service this operation
	 **/
	void setServers(List<Server> servers);

	/**
	 * Sets this Operation's servers property to the given list.
	 *
	 * @param servers list of servers to service this operation
	 * @return the current Operation object
	 **/
	Operation servers(List<Server> servers);

	/**
	 * Adds the given server to this Operation's list of servers.
	 *
	 * @param serversItem server which can service this operation
	 * @return the current Operation object
	 **/
	Operation addServersItem(Server serversItem);

}