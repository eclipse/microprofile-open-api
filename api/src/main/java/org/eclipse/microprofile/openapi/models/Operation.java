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
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#operationObject"
 */
public interface Operation extends Constructible {

	/**
	   * returns the tags property from a Operation instance.
	   *
	   * @return List&lt;String&gt; tags
	   **/

	List<String> getTags();

	/**
	   * sets this Operation's tags property to the given Tags.
	   *
	   * @param List&lt;String&gt;tags
	   */
	void setTags(List<String> tags);

	/**
	   * sets this Operation's tags property to the given tags and
	   * returns this instance of Operation
	   *
	   * @param List&lt;String&gt;tags
	   * @return Operation
	   */
	Operation tags(List<String> tags);

	/**
	   * Adds the given tagsItem to this Operation's list of tags, with the given key as its key.
	   *
	   * @param String key
	   * @param String tagsItem
	   * @return Operation
	   */
	Operation addTagsItem(String tagsItem);

	/**
	   * returns the summary property from a Operation instance.
	   *
	   * @return String summary
	   **/

	String getSummary();

	/**
	   * sets this Operation's summary property to the given summary.
	   *
	   * @param String summary
	   */
	void setSummary(String summary);

	/**
	   * sets this Operation's summary property to the given summary and
	   * returns this instance of Operation
	   *
	   * @param String summary
	   * @return Operation
	   */
	Operation summary(String summary);

	/**
	   * returns the description property from a Operation instance.
	   *
	   * @return String description
	   **/
	String getDescription();

	/**
	   * sets this Operation's description property to the given description.
	   *
	   * @param String description
	   */
	void setDescription(String description);

	/**
	   * sets this Operation's description property to the given description and
	   * returns this instance of Operation
	   *
	   * @param String description
	   * @return Operation
	   */
	Operation description(String description);

	/**
	   * returns the externalDocs property from a Operation instance.
	   *
	   * @return ExternalDocumentation externalDocs
	   **/

	ExternalDocumentation getExternalDocs();

	/**
	   * sets this Operation's externalDocs property to the given externalDocs.
	   *
	   * @param ExternalDocumentation externalDocs
	   */
	void setExternalDocs(ExternalDocumentation externalDocs);

	/**
	   * sets this Operation's externalDocs property to the given externalDocs and
	   * returns this instance of Operation
	   *
	   * @param ExternalDocumentation externalDocs
	   * @return Operation
	   */
	Operation externalDocs(ExternalDocumentation externalDocs);

	/**
	   * returns the operationId property from a Operation instance.
	   *
	   * @return String operationId
	   **/

	String getOperationId();

	/**
	   * sets this Operation's operationId property to the given operationId.
	   *
	   * @param String operationId
	   */
	void setOperationId(String operationId);

	/**
	   * sets this Operation's operationId property to the given operationId and
	   * returns this instance of Operation
	   *
	   * @param String operationId
	   * @return Operation
	   */
	Operation operationId(String operationId);

	/**
	   * returns the parameters property from a Operation instance.
	   *
	   * @return List&lt;Parameter&gt; parameters
	   **/

	List<Parameter> getParameters();

	/**
	   * sets this Operation's parameters property to the given parameters.
	   *
	   * @param List&lt;Parameter&gt;parameters
	   */
	void setParameters(List<Parameter> parameters);

	/**
	   * sets this Operation's parameters property to the given parameters and
	   * returns this instance of Operation
	   *
	   * @param List&lt;Parameter&gt;parameters
	   * @return Operation
	   */
	Operation parameters(List<Parameter> parameters);

	/**
	   * Adds the given parametersItem to this Operation's list of parameters, with the given key as its key.
	   *
	   * @param String key
	   * @param Parameter parametersItem
	   * @return Operation
	   */
	Operation addParametersItem(Parameter parametersItem);

	/**
	   * returns the requestBody property from a Operation instance.
	   *
	   * @return RequestBody requestBody
	   **/

	RequestBody getRequestBody();

	/**
	   * sets this Operation's requestBody property to the given requestBody.
	   *
	   * @param RequestBody requestBody
	   */
	void setRequestBody(RequestBody requestBody);

	/**
	   * sets this Operation's requestBody property to the given requestBody and
	   * returns this instance of Operation
	   *
	   * @param RequestBody requestBody
	   * @return Operation
	   */
	Operation requestBody(RequestBody requestBody);

	/**
	   * returns the responses property from a Operation instance.
	   *
	   * @return ApiResponses responses
	   **/

	ApiResponses getResponses();

	/**
	   * sets this Operation's responses property to the given responses.
	   *
	   * @param ApiResponses responses
	   */
	void setResponses(ApiResponses responses);

	/**
	   * sets this Operation's responses property to the given responses and
	   * returns this instance of Operation
	   *
	   * @param ApiResponses responses
	   * @return Operation
	   */
	Operation responses(ApiResponses responses);

	/**
	   * returns the callbacks property from a Operation instance.
	   *
	   * @return Callbacks callbacks
	   **/

	Map<String, Callback> getCallbacks();

	/**
	   * sets this Operation's callbacks property to the given callbacks.
	   *
	   * @param Map&lt;String, Callback&gt; callbacks
	   */
	void setCallbacks(Map<String, Callback> callbacks);

	/**
	   * sets this Operation's callbacks property to the given callbacks and
	   * returns this instance of Operation
	   *
	   * @param Map&lt;String, Callback&gt; callbacks
	   * @return Operation
	   */
	Operation callbacks(Map<String, Callback> callbacks);

	/**
	   * returns the deprecated property from a Operation instance.
	   *
	   * @return Boolean deprecated
	   **/

	Boolean getDeprecated();

	/**
	   * sets this Operation's deprecated property to the given deprecated.
	   *
	   * @param Boolean deprecated
	   */
	void setDeprecated(Boolean deprecated);

	/**
	   * sets this Operation's deprecated property to the given deprecated and
	   * returns this instance of Operation
	   *
	   * @param Boolean deprecated
	   * @return Operation
	   */
	Operation deprecated(Boolean deprecated);

	/**
	   * returns the security property from a Operation instance.
	   *
	   * @return List&lt;SecurityRequirement&gt; security
	   **/

	List<SecurityRequirement> getSecurity();

	/**
	   * sets this Operation's security property to the given security.
	   *
	   * @param List&lt;SecurityRequirement&gt; security
	   */
	void setSecurity(List<SecurityRequirement> security);

	/**
	   * sets this Operation's security property to the given security and
	   * returns this instance of Operation
	   *
	   * @param List&lt;SecurityRequirement&gt;security
	   * @return Operation
	   */
	Operation security(List<SecurityRequirement> security);

	/**
	   * Adds the given securityItem to this Operation's list of securityItems, with the given key as its key.
	   *
	   * @param String key
	   * @param SecurityRequirement securityItem
	   * @return Operation
	   */
	Operation addSecurityItem(SecurityRequirement securityItem);

	/**
	   * returns the servers property from a Operation instance.
	   *
	   * @return List&lt;Server&gt; servers
	   **/

	List<Server> getServers();

	/**
	   * sets this Operation's servers property to the given servers.
	   *
	   * @param List&lt;Server&gt; servers
	   */
	void setServers(List<Server> servers);

	/**
	   * sets this Operation's servers property to the given servers and
	   * returns this instance of Operation
	   *
	   * @param List&lt;Server&gt;servers
	   * @return Operation
	   */
	Operation servers(List<Server> servers);

	/**
	   * Adds the given serversItem to this Operation's list of serversItem, with the given key as its key.
	   *
	   * @param String key
	   * @param Server serversItem
	   * @return Operation
	   */
	Operation addServersItem(Server serversItem);

	/**
	   * returns the extensions property from a Operation instance.
	   *
	   * @return Map&lt;String, Object&gt; extensions
	   **/
	Map<String, Object> getExtensions();

	/**
	   * Adds the given Object to this Operation's map of extensions, with the given key as its key.
	   *
	   * @param String key
	   * @param Object value
	   * @return Operation
	   */
	void addExtension(String name, Object value);

	/**
	   * sets this Operation's extensions property to the given map of extensions.
	   *
	   * @param Map&lt;String, Object&gt;extensions
	   */
	void setExtensions(Map<String, Object> extensions);

}