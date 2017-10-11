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

package org.eclipse.microprofile.openapi.models.examples;

import org.eclipse.microprofile.openapi.models.Constructible;

/**
 * Example
 */
public interface Example extends Constructible {

	/**
	 * returns the summary property from a Example instance.
	 *
	 * @return String summary
	 **/

	String getSummary();

	/**
	 * sets this Example's summary property to the given summary.
	 *
	 * @param String summary
	 */
	void setSummary(String summary);

	/**
	 * sets this Example's summary property to the given summary and
	 * returns this instance of Example
	 *
	 * @param String summary
	 * @return Example
	 */
	Example summary(String summary);

	/**
	 * returns the description property from a Example instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * sets this Example's description property to the given description.
	 *
	 * @param String description
	 */
	void setDescription(String description);

	/**
	 * sets this Example's description property to the given description and
	 * returns this instance of Example
	 *
	 * @param String description
	 * @return Example
	 */
	Example description(String description);

	/**
	 * returns the value property from a Example instance.
	 *
	 * @return Object value
	 **/

	Object getValue();

	/**
	 * sets this Example's value property to the given value.
	 *
	 * @param Object value
	 */
	void setValue(Object value);

	/**
	 * sets this Example's value property to the given value and
	 * returns this instance of Example
	 *
	 * @param Object value
	 * @return Example
	 */
	Example value(Object value);

	/**
	 * returns the externalValue property from a Example instance.
	 *
	 * @return String externalValue
	 **/

	String getExternalValue();

	/**
	 * sets this Example's externalValue property to the given externalValue.
	 *
	 * @param String externalValue
	 */
	void setExternalValue(String externalValue);

	/**
	 * sets this Example's externalValue property to the given externalValue and
	 * returns this instance of Example
	 *
	 * @param String externalValue
	 * @return Example
	 */
	Example externalValue(String externalValue);

	/**
	 * returns the $ref property from a Example instance.
	 *
	 * @return String $ref
	 */
	String get$ref();

	/**
	 * sets this Example's $ref property to the given $ref.
	 *
	 * @param String $ref
	 */
	void set$ref(String $ref);

	/**
	 * sets this Example's $ref property to the given $ref and
	 * returns this instance of Example
	 *
	 * @param String $ref
	 * @return Example
	 */
	Example $ref(String $ref);

	/**
	 * returns the extensions property from a Example instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 **/
	java.util.Map<String, Object> getExtensions();

	/**
	 * Adds the given Object to this Example's map of extensions, with the given key as its key.
	 *
	 * @param String key
	 * @param Object value
	 */
	void addExtension(String name, Object value);

	/**
	 * sets the extensions property for a Example instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 **/
	void setExtensions(java.util.Map<String, Object> extensions);

}