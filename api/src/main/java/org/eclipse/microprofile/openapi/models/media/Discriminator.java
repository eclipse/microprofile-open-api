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

package org.eclipse.microprofile.openapi.models.media;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

public interface Discriminator extends Constructible {

	/**
	 * sets this Discriminator's propertyName property to the given propertyName and
	 * returns this instance of Discriminator
	 *
	 * @param String propertyName
	 * @return Discriminator
	 */
	Discriminator propertyName(String propertyName);

	/**
	 * returns the propertyName property from a Discriminator instance.
	 *
	 * @return String propertyName
	 **/
	String getPropertyName();

	/**
	 * sets this Discriminator's propertyName property to the given propertyName.
	 *
	 * @param String propertyName
	 */
	void setPropertyName(String propertyName);

	/**
	 * maps the given name to the given value and store it in this Discriminator's mapping property.
	 * 
	 * @param String name
	 * @param String value
	 * @return Discriminator
	 */
	Discriminator mapping(String name, String value);

	/**
	 * sets this Discriminator's mapping property to the given mapping and
	 * returns this instance of Discriminator
	 *
	 * @param Map&lt;String, String&gt; mapping
	 * @return Discriminator
	 */
	Discriminator mapping(Map<String, String> mapping);

	/**
	 * returns the mapping property from a Discriminator instance.
	 *
	 * @return Map&lt;String, String&gt; mapping
	 **/
	Map<String, String> getMapping();

	/**
	 * sets this Discriminator's mapping property to the given mapping.
	 *
	 * @param Map&lt;String, String&gt; mapping
	 */
	void setMapping(Map<String, String> mapping);

}