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
 * BinarySchema
 */
public interface BinarySchema extends Schema<byte[]> {

	/**
	   * sets this BinarySchema's type property to the given type and
	   * returns this instance of BinarySchema
	   *
	   * @param String type
	   * @return BinarySchema
	   */
	BinarySchema type(String type);

	/**
	   * sets this BinarySchema's format property to the given format and
	   * returns this instance of BinarySchema
	   *
	   * @param String format
	   * @return BinarySchema
	   */
	BinarySchema format(String format);

	/**
	   * sets the _default property of this BinarySchema to the given _default value.
	   * 
	   * @param byte[] _default
	   * @return BinarySchema
	   */
	BinarySchema _default(byte[] _default);

	/**
	   * sets the _enum property of this BinarySchema to the given _enum value.
	   * 
	   * @param List&lt;byte[]&gt; _enum
	   * @return BinarySchema
	   */
	BinarySchema _enum(List<byte[]> _enum);

	/**
	   * Adds the given _enumItem to this BinarySchema's List of _enumItems.
	   *
	   * @param byte[] _enumItem
	   */
	BinarySchema addEnumItem(byte[] _enumItem);

}