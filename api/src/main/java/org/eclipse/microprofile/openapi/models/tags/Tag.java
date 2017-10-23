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

package org.eclipse.microprofile.openapi.models.tags;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.ExternalDocumentation;

/**
 * Tag
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#tagObject"
 */
public interface Tag extends Constructible, Extensible {

	/**
	 * returns the name property from a Tag instance.
	 *
	 * @return String name
	 **/

	String getName();

	/**
	 * Sets the name property of a Tag instance to the
	 * parameter.
	 * 
	 * @param name
	 */

	void setName(String name);

	/**
	 * Sets the name property of a Tag instance to the
	 * parameter and returns the instance.
	 * 
	 * @param name
	 * @return Tag instance with the set name property
	 */

	Tag name(String name);

	/**
	 * returns the description property from a Tag instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a Tag instance to the
	 * parameter.
	 * 
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a Tag instance to the
	 * parameter and returns the instance.
	 * 
	 * @param description
	 * @return Tag instance with the set description property
	 */

	Tag description(String description);

	/**
	 * returns the externalDocs property from a Tag instance.
	 *
	 * @return ExternalDocumentation externalDocs
	 **/

	ExternalDocumentation getExternalDocs();

	/**
	 * Sets the externalDocs property of a Tag instance to the
	 * parameter.
	 * 
	 * @param externalDocs
	 */

	void setExternalDocs(ExternalDocumentation externalDocs);

	/**
	 * Sets the externalDocs property of a Tag instance to the
	 * parameter and returns the instance.
	 * 
	 * @param externalDocs
	 * @return Tag instance with the set externalDocs property
	 */

	Tag externalDocs(ExternalDocumentation externalDocs);

}