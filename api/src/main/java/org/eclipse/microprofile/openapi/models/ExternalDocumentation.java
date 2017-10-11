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

/**
 * ExternalDocumentation
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#externalDocumentationObject"
 */
public interface ExternalDocumentation extends Constructible {

	/**
	   * returns the description property from a ExternalDocumentation instance.
	   *
	   * @return String description
	   **/

	String getDescription();

	/**
	   * sets this ExternalDocumentation's description property to the given description.
	   *
	   * @param String description
	   */
	void setDescription(String description);

	/**
	   * sets this ExternalDocumentation's description property to the given description and
	   * returns this instance of ExternalDocumentation.
	   *
	   * @param String description
	   * @return ExternalDocumentation
	   */
	ExternalDocumentation description(String description);

	/**
	   * returns the url property from a ExternalDocumentation instance.
	   *
	   * @return String url
	   **/

	String getUrl();

	/**
	   * sets this ExternalDocumentation's url property to the given url.
	   *
	   * @param String url
	   */
	void setUrl(String url);

	/**
	   * sets this ExternalDocumentation's url property to the given url and
	   * returns this instance of ExternalDocumentation.
	   *
	   * @param String url
	   * @return ExternalDocumentation
	   */
	ExternalDocumentation url(String url);

	/**
	   * returns the extensions property from a ExternalDocumentation instance.
	   *
	   * @return Map&lt;String, Object&gt;extensions
	   **/
	Map<String, Object> getExtensions();

	/**
	   * Adds the given extension to this ExternalDocumentation's map of extensions, with the given key as its key.
	   *
	   * @param String key
	   * @param Object value
	   * @return Components
	   */
	void addExtension(String name, Object value);

	/**
	   * sets this ExternalDocumentation's extensions property to the given extensions.
	   *
	   * @param Map&lt;String, Object&gt;extensions
	   */
	void setExtensions(Map<String, Object> extensions);

}