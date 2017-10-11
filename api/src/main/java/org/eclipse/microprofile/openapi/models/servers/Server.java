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

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

/**
 * Server
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#serverObject"
 */
public interface Server extends Constructible  {

	/**
	 * returns the url property from a Server instance.
	 *
	 * @return String url
	 **/

	String getUrl();

	/**
	 * Sets the url property of a Server instance
	 * to the parameter.
	 * 
	 * @param url
	 */

	void setUrl(String url);

	/**
	 * Sets the url property of a Server instance
	 * to the parameter and returns the instance.
	 * 
	 * @param url
	 * @return Server instance with the set url property.
	 */

	Server url(String url);

	/**
	 * returns the description property from a Server instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a Server instance
	 * to the parameter.
	 * 
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a Server instance
	 * to the parameter and returns the instance.
	 * 
	 * @param description
	 * @return Server instance with the set description property.
	 */

	Server description(String description);

	/**
	 * returns the variables property from a Server instance.
	 *
	 * @return ServerVariables variables
	 **/

	ServerVariables getVariables();

	/**
	 * Sets the variables property of a Server instance
	 * to the parameter.
	 * 
	 * @param variables
	 */

	void setVariables(ServerVariables variables);

	/**
	 * Sets the variables property of a Server instance
	 * to the parameter and returns the instance.
	 * 
	 * @param variables
	 * @return Server instance with the set variables property.
	 */

	Server variables(ServerVariables variables);

	/**
	 * Returns extensions property of a Server instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map of a Server instance
	 * at the specified key.
	 * If extensions is null, then creates a new HashMap and adds the item.
	 *
	 * @param name
	 * @param value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of a Server instance
	 * to the parameter.
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}