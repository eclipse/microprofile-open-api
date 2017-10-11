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

package org.eclipse.microprofile.openapi.models.security;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

/**
 * Scopes
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#scopedObject"
 */
public interface Scopes extends Constructible, Map<String, String> {

	/**
	 * Adds name and item parameters to a Scopes instance
	 * as a key-value
	 *
	 * @param name
	 * @param item
	 * @return Scopes instance with the added key-value pair
	 */

	Scopes addString(String name, String item);

	/**
	 * Returns extensions property of an Scopes instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map of an Scopes instance
	 * at the specified key.
	 * If extensions is null, then creates a new HashMap and adds the item.
	 *
	 * @param name
	 * @param value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of an Scopes instance
	 * to the parameter.
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}