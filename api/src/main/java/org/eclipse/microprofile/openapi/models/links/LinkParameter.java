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

package org.eclipse.microprofile.openapi.models.links;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * LinkParameter
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#linkOParametersbject"
 */
public interface LinkParameter extends Constructible, Extensible {

	/**
	   * returns the value property from a LinkParameter instance.
	   *
	   * @return String value
	   **/
	String getValue();

	/**
	   * sets this LinkParameter's value property to the given value.
	   *
	   * @param String value
	   */
	void setValue(String value);

	/**
	   * sets this LinkParameter's value property to the given value and
	   * returns this instance of LinkParameter
	   *
	   * @param String value
	   * @return LinkParameter
	   */
	LinkParameter value(String value);

}