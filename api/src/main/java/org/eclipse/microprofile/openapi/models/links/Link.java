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
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.servers.Server;

/**
 * Link
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#linkObject"
 */
public interface Link extends Constructible, Extensible {

	/**
	 * returns the server property from a Link instance.
	 *
	 * @return Server server
	 **/

	Server getServer();

	/**
	 * sets this Link's server property to the given server.
	 *
	 * @param Server server
	 */
	void setServer(Server server);

	/**
	 * sets this Link's server property to the given server and
	 * returns this instance of Link
	 *
	 * @param Server server
	 * @return Link
	 */
	Link server(Server server);

	/**
	 * returns the operationRef property from a Link instance.
	 *
	 * @return String operationRef
	 **/

	String getOperationRef();

	/**
	 * sets this Link's operationRef property to the given operationRef.
	 *
	 * @param String operationRef
	 */
	void setOperationRef(String operationRef);

	/**
	 * sets this Link's operationRef property to the given operationRef and
	 * returns this instance of Link
	 *
	 * @param String operationRef
	 * @return Link
	 */
	Link operationRef(String operationRef);

	/**
	 * returns the requestBody property from a Link instance.
	 *
	 * @return RequestBody requestBody
	 **/

	RequestBody getRequestBody();

	/**
	 * sets this Link's requestBody property to the given requestBody.
	 *
	 * @param RequestBody requestBody
	 */
	void setRequestBody(RequestBody requestBody);

	/**
	 * sets this Link's requestBody property to the given requestBody and
	 * returns this instance of Link
	 *
	 * @param RequestBody requestBody
	 * @return Link
	 */
	Link requestBody(RequestBody requestBody);

	/**
	 * returns this Link's requestBody property for this instance of Link.
	 *
	 * @param String operationId
	 */
	String getOperationId();

	/**
	 * sets this Link's operationId property to the given operationId.
	 *
	 * @param String operationId
	 */
	void setOperationId(String operationId);

	/**
	 * sets this Link's operationId property to the given operationId and
	 * returns this instance of Link
	 *
	 * @param String operationId
	 * @return Link
	 */
	Link operationId(String operationId);

	/**
	 * returns the parameters property from a Link instance.
	 *
	 * @return LinkParameters parameters
	 **/
	Map<String, String> getParameters();

	/**
	 * sets this Link's parameters property to the given parameters.
	 *
	 * @param LinkParameters parameters
	 */
	void setParameters(Map<String, String> parameters);

	/**
	 * sets this Link's parameter property to the given parameter and
	 * returns this instance of Link
	 *
	 * @param String name
	 * @param String parameter
	 * @return Link
	 */
	Link parameters(String name, String parameter);

	/**
	 * returns the headers property from a Link instance.
	 *
	 * @return Headers headers
	 **/

	Map<String, Header> getHeaders();

	/**
	 * sets this Link's headers property to the given headers.
	 *
	 * @param Map&lt;String, Header&gt; headers
	 */
	void setHeaders(Map<String, Header> headers);

	/**
	 * sets this Link's headers property to the given headers and
	 * returns this instance of Link
	 *
	 * @param Map&lt;String, Header&gt; headers
	 * @return Link
	 */
	Link headers(Map<String, Header> headers);

	/**
	 * Adds the given Header to this Link's map of headers, with the given name as its key.
	 *
	 * @param String name
	 * @param Header header
	 * @return Link
	 */
	Link addHeaderObject(String name, Header header);

	/**
	 * returns the description property from a Link instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * returns the description property from a Link instance.
	 *
	 * @return String description
	 **/
	void setDescription(String description);

	/**
	 * sets this Link's description property to the given description and
	 * returns this instance of Link
	 *
	 * @param String description
	 * @return Link
	 */
	Link description(String description);

	/**
	 * returns the $ref property from a Link instance.
	 *
	 * @return String $ref
	 **/
	String get$ref();

	/**
	 * sets the $ref property for a Link instance.
	 *
	 * @param String $ref
	 **/
	void set$ref(String $ref);

	/**
	 * sets this Link's $ref property to the given description and
	 * returns this instance of Link
	 *
	 * @param String $ref
	 * @return Link
	 */
	Link $ref(String $ref);

}