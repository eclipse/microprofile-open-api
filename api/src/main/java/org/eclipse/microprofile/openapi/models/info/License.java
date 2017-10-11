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

package org.eclipse.microprofile.openapi.models.info;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

public interface License extends Constructible {

	/**
	   * returns the name property from a License instance.
	   *
	   * @return String name
	   **/

	String getName();

	/**
	   * sets this License's name property to the given name.
	   *
	   * @param String name
	   */
	void setName(String name);

	/**
	   * sets this License's name property to the given name and
	   * returns this instance of License
	   *
	   * @param String name
	   * @return License
	   */
	License name(String name);

	/**
	   * returns the url property from a License instance.
	   *
	   * @return String url
	   **/

	String getUrl();

	/**
	   * sets this License's url property to the given url.
	   *
	   * @param String url
	   */
	void setUrl(String url);

	/**
	   * sets this License's url property to the given url and
	   * returns this instance of License
	   *
	   * @param String url
	   * @return License
	   */
	License url(String url);

	/**
	   * returns the extensions property from a License instance.
	   *
	   * @return Map&lt;String, Object&gt; extensions
	   */
	Map<String, Object> getExtensions();

	/**
	   * Adds the given Object to this License's map of extensions, with the given key as its key.
	   *
	   * @param String key
	   * @param Object value
	   */
	void addExtension(String name, Object value);

	/**
	   * sets the extensions property for a License instance.
	   *
	   * @return Map&lt;String, Object&gt; extensions
	   */
	void setExtensions(Map<String, Object> extensions);

}