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
import java.util.UUID;

/**
 * UUIDSchema
 */
public interface UUIDSchema extends Schema<UUID> {

	/**
	 * Sets the type property of a UUIDSchema instance
	 * to the parameter and returns the instance.
	 *
	 * @param type
	 * @return UUIDSchema instance with modified type property.
	 */

	UUIDSchema type(String type);

	/**
	 * Sets format property of a UUIDSchema instance
	 * to the parameter and returns the instance.
	 *
	 * @param format
	 * @return UUIDSchema instance with the modified format
	 */

	UUIDSchema format(String format);

	/**
	 * Sets inherited _default property of a UUIDSchema instance
	 * to the parameter and returns the instance.
	 * _default is inherited from super class Schema
	 *
	 * <p>
	 * Sets _default from UUID argument
	 *
	 * @param _default
	 * @return UUIDSchema instance with the modified _default property
	 * @see SchemaImpl
	 */

	UUIDSchema _default(UUID _default);

	/**
	 * Sets inherited _default property of a UUIDSchema instance
	 * to the parameter and returns the instance.
	 * _default is inherited from super class Schema
	 *
	 * <p>
	 * Sets _default from String argument
	 *
	 * @param _default
	 * @return UUIDSchema instance with the modified _default property
	 * @see SchemaImpl
	 */

	UUIDSchema _default(String _default);

	/**
	 * Sets inherited _enum property of a UUIDSchema instance
	 * to the parameter.
	 * _enum is inherited from super class Schema.
	 *
	 * @param _enum
	 * @return UUIDSchema instance with modified _enum.
	 * @see SchemaImpl
	 */

	UUIDSchema _enum(List<UUID> _enum);

	/**
	 * Adds an item to _enum of a UUIDSchema instance
	 * to the parameter and returns the instance.
	 * If _enum is null, creates a new ArrayList and adds item.
	 *
	 * @param _enumItem
	 * @return UUIDSchema instance with the added _enum item.
	 */

	UUIDSchema addEnumItem(UUID _enumItem);

}