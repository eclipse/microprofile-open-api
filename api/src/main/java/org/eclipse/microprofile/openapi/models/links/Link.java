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

package org.eclipse.microprofile.openapi.models.links;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.servers.Server;

/**
 * Link
 * <p>
 * The Link object represents a possible design-time link for a response. The
 * presence of a link does not guarantee the caller's ability to successfully
 * invoke it, rather it provides a known relationship and traversal mechanism
 * between responses and other operations.
 * <p>
 * Unlike dynamic links (i.e. links provided in the response payload), the OAS
 * linking mechanism does not require link information in the runtime response.
 * <p>
 * For computing links, and providing instructions to execute them, a runtime
 * expression is used for accessing values in an operation and using them as
 * parameters while invoking the linked operation.
 * <p>
 * Fixed Fields
 * <table border=1 cellpadding="8" style="border-collapse: collapse">
 * <tr>
 * <th>Field Name</th>
 * <th>Type</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>reference</td>
 * <td>string</td>
 * <td>Allows for an external definition of this link. The referenced structure
 * MUST be in the format of a Link Object. This field represents the $ref field
 * in the OAS file. If there are conflicts between the referenced definition and
 * this Link's definition, the behavior is undefined.</td>
 * </tr>
 * <tr>
 * <td>operationRef</td>
 * <td>string</td>
 * <td>A relative or absolute reference to an OAS operation. This field is
 * mutually exclusive of the operationId field, and MUST point to an Operation
 * Object. Relative operationRef values MAY be used to locate an existing
 * Operation Object in the OpenAPI definition.</td>
 * </tr>
 * <tr>
 * <td>operationId</td>
 * <td>string</td>
 * <td>The name of an existing, resolvable OAS operation, as defined with a
 * unique operationId. This field is mutually exclusive of the operationRef
 * field.</td>
 * </tr>
 * <tr>
 * <td>parameters</td>
 * <td>Map[string, Any | {expression}]</td>
 * <td>A map representing parameters to pass to an operation as specified with
 * operationId or identified via operationRef. The key is the parameter name to
 * be used, whereas the value can be a constant or an expression to be evaluated
 * and passed to the linked operation. The parameter name can be qualified using
 * the parameter location [{in}.]{name} for operations that use the same
 * parameter name in different locations (e.g. path.id).</td>
 * </tr>
 * <tr>
 * <td>requestBody</td>
 * <td>{@link RequestBody Request Body Object} | runtime expression</td>
 * <td>A literal value or runtime expression to use as a request body when
 * calling the target operation.</td>
 * </tr>
 * <tr>
 * <td>description</td>
 * <td>string</td>
 * <td>A description of the link. CommonMark syntax MAY be used for rich text
 * representation.</td>
 * </tr>
 * <tr>
 * <td>server</td>
 * <td>{@link Server Server Object}</td>
 * <td>A server object to be used by the target operation.</td>
 * </tr>
 * </table>
 * A linked operation MUST be identified using either an operationRef or
 * operationId. In the case of an operationId, it MUST be unique and resolved in
 * the scope of the OAS document. Because of the potential for name clashes, the
 * operationRef syntax is preferred for specifications with external references.
 * <p>
 * 
 * @see <a href=
 *      "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#linkObject">
 *      OpenAPI Specification Link Object</a>
 */
public interface Link extends Constructible, Extensible {

	/**
	 * Returns the server property from a Link instance.
	 *
	 * @return a server object to be used by the target operation
	 **/
	Server getServer();

	/**
	 * Sets this Link's server property to the given object.
	 *
	 * @param server  a server object to be used by the target operation
	 */
	void setServer(Server server);

	/**
	 * Sets this Link's server property to the given object.
	 *
	 * @param server  a server object to be used by the target operation
	 * @return the current instance of Link
	 */
	Link server(Server server);

	/**
	 * Returns the operationRef property from a Link instance.
	 *
	 * @return a relative or absolute reference to an OAS operation
	 **/
	String getOperationRef();

	/**
	 * Sets this Link's operationRef property to the given string.
	 *
	 * @param operationRef  a relative or absolute reference to an OAS operation
	 */
	void setOperationRef(String operationRef);

	/**
	 * Sets this Link's operationRef property to the given string.
	 *
	 * @param operationRef  a relative or absolute reference to an OAS operation
	 * @return the current instance of Link
	 */
	Link operationRef(String operationRef);

	/**
	 * Returns the requestBody property from a Link instance.
	 *
	 * @return a literal value or runtime expression to use as a request body when calling the target operation
	 **/
	RequestBody getRequestBody();

	/**
	 * Sets this Link's requestBody property to the given object.
	 *
	 * @param requestBody  a literal value or runtime expression to use as a request body when calling the target operation
	 */
	void setRequestBody(RequestBody requestBody);

	/**
	 * Sets this Link's requestBody property to the given object.
	 *
	 * @param requestBody  a literal value or runtime expression to use as a request body when calling the target operation
	 * @return the current instance of Link
	 */
	Link requestBody(RequestBody requestBody);

	/**
	 * Returns the operationId property for this instance of Link.
	 *
	 * @param operationId  the name of an existing, resolvable OAS operation
	 */
	String getOperationId();

	/**
	 * Sets this Link's operationId property to the given string.
	 *
	 * @param operationId  the name of an existing, resolvable OAS operation
	 */
	void setOperationId(String operationId);

	/**
	 * Sets this Link's operationId property to the given string.
	 *
	 * @param operationId  the name of an existing, resolvable OAS operation
	 * @return the current instance of Link
	 */
	Link operationId(String operationId);

	/**
	 * Returns the parameters property from this instance of Link.
	 *
	 * @return a map representing parameters to pass to this link's operation
	 **/
	Map<String, String> getParameters();

	/**
	 * Sets this Link's parameters property to the given map.
	 *
	 * @param parameters  a map representing parameters to pass to this link's operation
	 */
	void setParameters(Map<String, String> parameters);

	/**
	 * Add a new parameter to the parameters property of this instance of Link. 
	 *
	 * @param name  The name of the parameter. Can be qualified using the parameter location [{in}.]{name} for operations that use the same parameter name in different locations (e.g. path.id).
	 * @param parameter  a constant or an expression to be evaluated at runtime and passed to the linked operation
	 * @return the current instance of Link
	 */
	Link parameters(String name, String parameter);

	/**
	 * Returns the description property from a Link instance.
	 *
	 * @return a description of the link
	 **/
	String getDescription();

	/**
	 * Sets this Link's description property to the given string.
	 *
	 * @param description  a description of the link
	 **/
	void setDescription(String description);

	/**
	 * Sets this Link's description property to the given string.
	 *
	 * @param description  a description of the link
	 * @return the current instance of Link
	 */
	Link description(String description);

	/**
	 * Returns the reference property from a Link instance.
	 *
	 * @return a reference to one of the components in this OpenAPI document
	 **/
	String getReference();

	/**
	 * Sets this Link's reference property to the given string.
	 *
	 * @param reference  a reference to a link object in the components in this OpenAPI document
	 **/
	void setReference(String reference);

	/**
	 * Sets this Link's reference property to the given string.
	 *
	 * @param reference  a reference to a link object in the components in this OpenAPI document
	 * @return the current instance of Link
	 */
	Link reference(String reference);

}