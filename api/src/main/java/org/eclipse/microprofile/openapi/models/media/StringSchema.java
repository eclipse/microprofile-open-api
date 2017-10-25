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
 * StringSchema
 */
public interface StringSchema extends Schema<String> {

	/**
	 * Sets the type property of a StringSchema instance
	 * to the parameter and returns the instance.
	 *
	 * @param type
	 * @return StringSchema instance with modified type property.
	 */

	StringSchema type(String type);

	/**
	 * Sets inherited _default property of a StringSchema instance
	 * to the parameter and returns the instance.
	 * _default is inherited from super class Schema
	 *
	 * @param _default
	 * @return StringSchema instance with the modified _default property
	 * @see SchemaImpl
	 */

	StringSchema _default(String _default);

	/**
	 * Sets inherited _enum property of a StringSchema instance
	 * to the parameter.
	 * <p>
	 * _enum is inherited from super class Schema.
	 * <p>
	 * Uses super class method setEnum() and returns
	 * the instance.
	 *
	 * @param _enum
	 * @return StringSchema instance with modified _enum.
	 * @see SchemaImpl
	 */

	StringSchema _enum(List<String> _enum);

	/**
	 * Adds an item to _enum of a StringSchema instance
	 * and returns the instance.
	 * If _enum is null, creates a new ArrayList and adds item.
	 *
	 * @param _enumItem
	 * @return StringSchema instance with the added _enum item.
	 */

	StringSchema addEnumItem(String _enumItem);

}