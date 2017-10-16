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
 * PasswordSchema
 */
public interface PasswordSchema extends Schema<String> {

	/**
	 * Sets the type property of a PasswordSchema instance
	 * to the parameter and returns the instance.
	 *
	 * @param type
	 * @return PasswordSchema instance with the modified type property
	 */

	PasswordSchema type(String type);

	/**
	 * Sets the format property for a PasswordSchema instance
	 * to the parameter and returns the instance.
	 *
	 * @param format
	 * @return PasswordSchema instance with modified format
	 **/

	PasswordSchema format(String format);

	/**
	 * Sets the inherited _default property of a PasswordSchema instance
	 * to the parameter and returns the instance.
	 * _default is inherited from Schema.
	 *
	 * @param _default
	 * @return The instance of PasswordSchema with the set _default
	 */

	PasswordSchema _default(String _default);

	/**
	 * Sets inherited _enum property of a PasswordSchema instance
	 * to the parameter.
	 * _enum is inherited from Schema.
	 *
	 * @param _enum
	 * @return A PasswordSchema instance with set _enum
	 * @see SchemaImpl
	 */

	PasswordSchema _enum(List<String> _enum);

	/**
	 * Adds an item to _enum List.
	 * If _enum is null, will create a new ArrayList and add the item.
	 *
	 * @param _enumItem to be added to the _enum List
	 * @return PasswordSchema instance with the modified _enum
	 */

	PasswordSchema addEnumItem(String _enumItem);

}