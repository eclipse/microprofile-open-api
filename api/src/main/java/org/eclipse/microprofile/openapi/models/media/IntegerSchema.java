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

import java.util.List;

/**
 * IntegerSchema
 */
public interface IntegerSchema extends Schema<Integer> {

	/**
	 * Sets the type property for an IntegerSchema instance to the
	 * parameter and returns the instance.
	 * Type for IntegerSchema instance is "integer"
	 *
	 * @param type
	 * @return IntegerSchema instance with the set type
	 **/

	IntegerSchema type(String type);

	/**
	 * Sets the format property for an IntegerSchema instance to the
	 * parameter and returns the instance.
	 * The format property for IntegerSchema can be: "int32" or "int64"
	 *
	 * @param format
	 * @return IntegerSchema instance with the set format
	 **/

	IntegerSchema format(String format);

	/**
	 * Sets the _default property of an IntegerSchema instance to the
	 * parameter and returns the instance.
	 * _default property is inherited from super class Schema
	 * Method setDefault inherited from Schema super class.
	 *
	 * @param _default
	 * @return IntegerSchema instance with the set _default
	 * @see SchemaImpl.setDefault
	 */

	IntegerSchema _default(Integer _default);

	/**
	 * Sets inherited _enum property of an IntegerSchema instance to the
	 * parameter.
	 * _enum is inherited from Schema.
	 *
	 * @param _enum
	 * @return IntegerSchema instance with the set _enum
	 * @see SchemaImpl
	 */

	IntegerSchema _enum(List<Integer> _enum);

	/**
	 * Adds an item to _enum List.
	 * If _enum is null, will create a new ArrayList and add the item.
	 *
	 * @param _enumItem
	 * @return IntegerSchema instance with modified _enum
	 */

	IntegerSchema addEnumItem(Integer _enumItem);

}