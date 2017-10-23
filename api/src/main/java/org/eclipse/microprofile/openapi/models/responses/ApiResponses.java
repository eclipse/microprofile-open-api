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

/**
 * ApiResponses
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#responsesObject"
 */
public interface ApiResponses extends Constructible, Map<String, ApiResponse> {

	public static final String DEFAULT = "default";

	ApiResponses addApiResponse(String name, ApiResponse item);

	/**
	 * returns the default property from a ApiResponses instance.
	 *
	 * @return ApiResponse _default
	 **/

	ApiResponse getDefault();

	/**
	 * Sets _default property of an ApiResponses instance
	 * to the parameter.
	 *
	 * @param _default
	 */

	void setDefault(ApiResponse _default);

	/**
	 * Sets _default property of an ApiResponses instance
	 * to the parameter and returns the instance.
	 *
	 * @param _default
	 * @return ApiResponses instance with the set _default property
	 */

	ApiResponses _default(ApiResponse _default);

}