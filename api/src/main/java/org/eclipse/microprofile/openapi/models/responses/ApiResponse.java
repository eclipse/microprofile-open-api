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

package org.eclipse.microprofile.openapi.models.responses;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.links.Link;
import org.eclipse.microprofile.openapi.models.media.Content;

/**
 * ApiResponse
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#responseObject"
 */
public interface ApiResponse extends Constructible, Extensible {

	/**
	 * returns the description property from a ApiResponse instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a ApiResponse instance
	 * to the parameter.
	 *
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a ApiResponse instance
	 * to the parameter and returns the instance.
	 *
	 * @param description
	 * @return ApiResponse instance with the set description property
	 */

	ApiResponse description(String description);

	/**
	 * returns the headers property from a ApiResponse instance.
	 *
	 * @return Map&lt;String, Header&gt; headers
	 **/

	Map<String, Header> getHeaders();

	/**
	 * Sets the headers property of a ApiResponse instance
	 * to the parameter.
	 *
	 * @param headers
	 */

	void setHeaders(Map<String, Header> headers);

	/**
	 * Sets the headers property of a ApiResponse instance
	 * to the parameter and returns the instance.
	 *
	 * @param headers
	 * @return ApiResponse instance with the set headers property
	 */

	ApiResponse headers(Map<String, Header> headers);

	/**
	 * Adds a header item to the headers map of an ApiResponse instance
	 * at the specified key and returns the instance.
	 * If headers is null, creates a new HashMap and adds item to it.
	 *
	 * @param name - map key
	 * @param header - map value
	 * @return ApiResponse instance with the added header item
	 */

	ApiResponse addHeaderObject(String name, Header header);

	/**
	 * returns the content property from a ApiResponse instance.
	 *
	 * @return Content content
	 **/

	Content getContent();

	/**
	 * Sets the content property of an ApiResponse instance
	 * to the parameter.
	 *
	 * @param content
	 */

	void setContent(Content content);

	/**
	 * Sets the content property of an ApiResponse instance
	 * to the parameter and returns the instance.
	 *
	 * @param content
	 * @return ApiResponse instance with the set content property
	 */

	ApiResponse content(Content content);

	/**
	 * returns the links property from a ApiResponse instance.
	 *
	 * @return Link links
	 **/

	Map<String, Link> getLinks();

	/**
	 * Sets the links property of an ApiResponse instance
	 * to the parameter.
	 *
	 * @param links
	 */

	void setLinks(Map<String, Link> links);

	/**
	 * Sets the links property of an ApiResponse instance
	 * using key, value pair and returns the instance.
	 * <p>
	 * If links is null, creates a new HashMap and adds the
	 * key-value pair to it.
	 *
	 * @param link
	 * @param link
	 * @return ApiResponse instance with the set links property
	 */

	ApiResponse link(String name, Link link);

	/**
	 * returns the $ref property from an ApiResponse instance.
	 *
	 * @return String $ref
	 **/
	String get$ref();

	/**
	 * Sets the $ref property of an ApiResponse instance
	 * to the parameter.
	 *
	 * @param String $ref
	 */

	void set$ref(String $ref);

	/**
	 * Sets the $ref property of an ApiResponse instance
	 * to the parameter and returns the instance.
	 *
	 * @param $ref
	 * @return ApiResponse instance with the set $ref property
	 */

	ApiResponse $ref(String $ref);

}