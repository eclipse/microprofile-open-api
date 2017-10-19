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

package org.eclipse.microprofile.openapi.models.parameters;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.media.Content;

/**
 * RequestBody
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#requestBodyObject"
 */
public interface RequestBody extends Constructible, Extensible {

	/**
	 * returns the description property from a RequestBody instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a RequestBody instance
	 * to the parameter.
	 *
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a RequestBody instance
	 * to the parameter and returns the instance.
	 *
	 * @param description
	 * @return RequestBody instance with the modified description property
	 */

	RequestBody description(String description);

	/**
	 * returns the content property from a RequestBody instance.
	 *
	 * @return Content content
	 **/

	Content getContent();

	/**
	 * Sets the content property of a RequestBody instance
	 * to the parameter.
	 *
	 * @param content
	 */

	void setContent(Content content);

	/**
	 * Sets the content property of a RequestBody instance
	 * to the parameter and returns the instance.
	 *
	 * @param content
	 * @return RequestBody instance with the modified content property
	 */

	RequestBody content(Content content);

	/**
	 * returns the required property from a RequestBody instance.
	 *
	 * @return Boolean required
	 **/

	Boolean getRequired();

	/**
	 * Sets the required property of a RequestBody instance
	 * to the parameter.
	 *
	 * @param required
	 */

	void setRequired(Boolean required);

	/**
	 * Sets the required property of a RequestBody instance
	 * to the parameter and returns the instance.
	 *
	 * @param required
	 * @return RequestBody instance with the modified required property
	 */

	RequestBody required(Boolean required);

	/**
	 * returns the $ref property from a RequestBody instance.
	 *
	 * @return String $ref
	 **/

	String get$ref();

	/**
	 * Sets $ref property of a RequestBody instance
	 * to the parameter.
	 *
	 * @param $ref
	 */

	void set$ref(String $ref);

	/**
	 * Sets $ref property of a RequestBody instance
	 * to the parameter and return the instance.
	 *
	 * @param $ref
	 * @return RequestBody instance with the set $ref property.
	 */

	RequestBody $ref(String $ref);

}