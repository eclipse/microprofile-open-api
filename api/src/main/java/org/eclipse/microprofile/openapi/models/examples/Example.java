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
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * Example
 * <p>
 * An object containing sample data for the related object.
 * <p>
 * Fixed Fields
 * <table border=1 cellpadding="8" style="border-collapse: collapse">
 * <tr>
 * <th>Field Name</th>
 * <th>Type</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>reference</td>
 * <td>string</td>
 * <td>Allows for an external definition of this example. The referenced
 * structure MUST be in the format of an Example Object. This field represents
 * the $ref field in the OAS file. If there are conflicts between the referenced
 * definition and this Example's definition, the behavior is undefined.</td>
 * </tr>
 * <tr>
 * <td>summary</td>
 * <td>string</td>
 * <td>Short description for the example.</td>
 * </tr>
 * <tr>
 * <td>description</td>
 * <td>string</td>
 * <td>Long description for the example. CommonMark syntax MAY be used for rich
 * text representation.</td>
 * </tr>
 * <tr>
 * <td>value</td>
 * <td>Any</td>
 * <td>Embedded literal example. The value field and externalValue field are
 * mutually exclusive. To represent examples of media types that cannot
 * naturally be represented in JSON or YAML, use a string value to contain the
 * example, escaping where necessary.</td>
 * </tr>
 * <tr>
 * <td>externalValue</td>
 * <td>string</td>
 * <td>A URL that points to the literal example. This provides the capability to
 * reference examples that cannot easily be included in JSON or YAML documents.
 * The value field and externalValue field are mutually exclusive.</td>
 * </tr>
 * </table>
 * <p>
 * In all cases, the example value is expected to be compatible with the type
 * schema of its associated value. Tooling implementations MAY choose to
 * validate compatibility automatically, and reject the example value(s) if
 * incompatible.
 * 
 * @see <a href=
 *      "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#exampleObject">OpenAPI
 *      Specification Example Object</a>
 */
public interface Example extends Constructible, Extensible {

	/**
	 * Returns the summary property from an Example instance.
	 *
	 * @return short description of the example
	 **/
	String getSummary();

	/**
	 * Sets this Example's summary property to the given string.
	 *
	 * @param summary short description of the example
	 */
	void setSummary(String summary);

	/**
	 * Sets this Example's summary property to the given string.
	 *
	 * @param summary short description of the example
	 * @return the current Example object
	 */
	Example summary(String summary);

	/**
	 * Returns the description property from an Example instance.
	 *
	 * @return long description of the example
	 **/
	String getDescription();

	/**
	 * Sets this Example's description property to the given string.
	 *
	 * @param description long description of the example
	 */
	void setDescription(String description);

	/**
	 * Sets this Example's description property to the given string.
	 *
	 * @param description long description of the example
	 * @return the current Example object
	 */
	Example description(String description);

	/**
	 * Returns the value property from an Example instance.
	 *
	 * @return embedded literal example object
	 **/
	Object getValue();

	/**
	 * Sets this Example's value property to the given value.
	 *
	 * @param value a literal example object
	 */
	void setValue(Object value);

	/**
	 * Sets this Example's value property to the given value.
	 *
	 * @param value a literal example object
	 * @return the current Example object
	 */
	Example value(Object value);

	/**
	 * Returns the externalValue property from an Example instance.
	 *
	 * @return URL that points to the literal example
	 **/
	String getExternalValue();

	/**
	 * Sets this Example's externalValue property to the given string.
	 *
	 * @param externalValue URL that points to the literal example
	 */
	void setExternalValue(String externalValue);

	/**
	 * Sets this Example's externalValue property to the given string.
	 *
	 * @param externalValue URL that points to the literal example
	 * @return the current Example object
	 */
	Example externalValue(String externalValue);

	/**
	 * Returns the reference property from an Example instance.
	 *
	 * @return a reference to an example object in the components in this OpenAPI document
	 */
	String getReference();

	/**
	 * Sets this Example's reference property to the given string.
	 *
	 * @param reference  a reference to an example object in the components in this OpenAPI document
	 */
	void setReference(String reference);

	/**
	 * Sets this Example's reference property to the given string.
	 *
	 * @param reference  a reference to an example object in the components in this OpenAPI document
	 * @return the current Example object
	 */
	Example reference(String reference);

}