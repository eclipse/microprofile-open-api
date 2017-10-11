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

package org.eclipse.microprofile.openapi.models.servers;

import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

/**
 * ServerVariable
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#serverVariableObject"
 */
public interface ServerVariable extends Constructible {

	/**
	 * returns the _enum property from a ServerVariable instance.
	 *
	 * @return List&lt;String&gt; _enum
	 **/

	List<String> getEnum();

	/**
	 * Sets the _enum property of a ServerVariable instance
	 * to parameter.
	 * 
	 * @param _enum
	 */

	void setEnum(List<String> _enum);

	/**
	 * Sets the _enum property of a ServerVariable instance
	 * to parameter and returns the instance.
	 * 
	 * @param _enum
	 * @return ServerVariable instance with the set _enum property
	 */

	ServerVariable _enum(List<String> _enum);

	/**
	 * Adds a string item to _enum list of a ServerVariable instance
	 * and returns the instance.
	 * <p>
	 * If the _enum list is null, creates a new ArrayList and adds the item.
	 * 
	 * @param _enumItem
	 * @return ServerVariable instance with the added enum item.
	 */

	ServerVariable addEnumItem(String _enumItem);

	/**
	 * returns the _default property from a ServerVariable instance.
	 *
	 * @return String _default
	 **/

	String getDefault();

	/**
	 * Sets the _default property of a ServerVariable instance
	 * to parameter.
	 * 
	 * @param _default
	 */

	void setDefault(String _default);

	/**
	 * Sets the _default property of a ServerVariable instance
	 * to parameter and returns the instance.
	 * 
	 * @param _default
	 * @return ServerVariable instance with the set _default property
	 */

	ServerVariable _default(String _default);

	/**
	 * returns the description property from a ServerVariable instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a ServerVariable instance
	 * to parameter.
	 * 
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a ServerVariable instance
	 * to parameter and returns the instance.
	 * 
	 * @param description
	 * @return ServerVariable instance with the set description property
	 */

	ServerVariable description(String description);

	/**
	 * Returns extensions property of a ServerVariable instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map of a ServerVariable instance
	 * at the specified key.
	 * If extensions is null, then creates a new HashMap and adds the item.
	 *
	 * @param name
	 * @param value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of a ServerVariable instance
	 * to the parameter.
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}