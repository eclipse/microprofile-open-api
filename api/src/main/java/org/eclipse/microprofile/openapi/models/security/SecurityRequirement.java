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

import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

/**
 * SecurityRequirement
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#securityRequirementObject"
 */
public interface SecurityRequirement extends Constructible, Map<String, List<String>> {

	/**
	 * Adds a List item to a SecurityRequirement instance
	 * based on the name and item parameters provided as key-value pair.
	 * <p>
	 * Takes value as a String object.
	 * 
	 * @param name
	 * @param item
	 * @return Updated SecurityRequirement instance
	 */

	SecurityRequirement addList(String name, String item);

	/**
	 * Adds a List item to a SecurityRequirement instance
	 * based on the name and item parameters provided as key-value pair
	 * to the map.
	 * <p>
	 * Takes value as a List of String objects.
	 * 
	 * @param name
	 * @param item
	 * @return Updated SecurityRequirement instance
	 */

	SecurityRequirement addList(String name, List<String> item);

	/**
	 * Adds a new empty List item to a SecurityRequirement instance
	 * based on the name parameter provided as key to the map.
	 * 
	 * @param name
	 * @return Updated SecurityRequirement instance
	 */

	SecurityRequirement addList(String name);

}