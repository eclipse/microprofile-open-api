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

import java.util.Date;
import java.util.List;

/**
 * DateSchema
 */
public interface DateSchema extends Schema<Date> {

	/**
	   * sets this DateSchema's type property to the given type and
	   * returns this instance of DateSchema
	   *
	   * @param String type
	   * @return DateSchema
	   */
	DateSchema type(String type);

	/**
	   * sets this DateSchema's format property to the given format and
	   * returns this instance of DateSchema
	   *
	   * @param String format
	   * @return DateSchema
	   */
	DateSchema format(String format);

	/**
	   * sets the _default property of this DateSchema to the given _default value.
	   * 
	   * @param byte[] _default
	   * @return DateSchema
	   */
	DateSchema _default(Date _default);

	/**
	   * sets the _enum property of this DateSchema to the given _enum value.
	   * 
	   * @param List&lt;byte[]&gt; _enum
	   * @return DateSchema
	   */
	DateSchema _enum(List<Date> _enum);

	/**
	   * Adds the given _enumItem to this DateSchema's List of _enumItems.
	   *
	   * @param byte[] _enumItem
	   */
	DateSchema addEnumItem(Date _enumItem);

}