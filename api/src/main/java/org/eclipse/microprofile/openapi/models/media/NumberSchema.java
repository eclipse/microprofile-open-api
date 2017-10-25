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

import java.math.BigDecimal;
import java.util.List;

/**
 * NumberSchema
 */
public interface NumberSchema extends Schema<BigDecimal> {

	/**
	 * Sets the type property of a NumberSchema instance
	 * to the parameter.
	 *
	 * @param type
	 * @return NumberSchema instance with the modified type property
	 */

	NumberSchema type(String type);

	/**
	 * Sets the _default property of a NumberSchema instance
	 * to the parameter and returns the instance.
	 * _default property is inherited from super class Schema
	 * Method setDefault inherited from Schema super class.
	 *
	 * @param _default
	 * @return The instance of NumberSchema with the modified _default
	 * @see SchemaImpl.setDefault
	 */

	NumberSchema _default(BigDecimal _default);

	/**
	 * Sets inherited _enum property of a NumberSchema instance
	 * to the parameter.
	 * _enum is inherited from Schema.
	 *
	 * @param _enum A list of BigDecimal values
	 * @return A NumberSchema instance with the set _enum
	 * @see SchemaImpl
	 */

	NumberSchema _enum(List<BigDecimal> _enum);

	/**
	 * Adds an item to _enum List.
	 * If _enum is null, will create a new ArrayList and add the item.
	 *
	 * @param BigDecimal _enumItem
	 * @return NumberSchema instance with the modified _enum item
	 */

	NumberSchema addEnumItem(BigDecimal _enumItem);

}