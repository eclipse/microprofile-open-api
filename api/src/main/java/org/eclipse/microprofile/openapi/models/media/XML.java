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

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * XML
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#xmlObject"
 */
public interface XML extends Constructible, Extensible {

	/**
	 * returns the name property from a XML instance.
	 *
	 * @return String name
	 **/

	String getName();

	/**
	 * Sets the name property of an XML instance
	 * to the parameter.
	 *
	 * @param name
	 */

	void setName(String name);

	/**
	 * Sets the name property of an XML instance
	 * to the parameter and returns the instance.
	 *
	 * @param name
	 * @return XML instance with the set name property
	 */

	XML name(String name);

	/**
	 * returns the namespace property from a XML instance.
	 *
	 * @return String namespace
	 **/

	String getNamespace();

	/**
	 * Sets the namespace property of an XML instance
	 * to the parameter.
	 *
	 * @param namespace
	 */

	void setNamespace(String namespace);

	/**
	 * Sets the namespace property of an XML instance
	 * to the parameter and returns the instance.
	 *
	 * @param namespace
	 * @return XML instance with the set namespace property
	 */

	XML namespace(String namespace);

	/**
	 * returns the prefix property from a XML instance.
	 *
	 * @return String prefix
	 **/

	String getPrefix();

	/**
	 * Sets the prefix property of an XML instance
	 * to the parameter.
	 *
	 * @param prefix
	 */

	void setPrefix(String prefix);

	/**
	 * Sets the prefix property of an XML instance
	 * to the parameter and returns the instance.
	 *
	 * @param prefix
	 * @return XML instance with the set prefix property
	 */

	XML prefix(String prefix);

	/**
	 * returns the attribute property from a XML instance.
	 *
	 * @return Boolean attribute
	 **/

	Boolean getAttribute();

	/**
	 * Sets the attribute property of an XML instance
	 * to the parameter.
	 *
	 * @param attribute
	 */

	void setAttribute(Boolean attribute);

	/**
	 * Sets the attribute property of an XML instance
	 * to the parameter and returns the instance.
	 *
	 * @param attribute
	 * @return XML instance with the set attribute property
	 */

	XML attribute(Boolean attribute);

	/**
	 * returns the wrapped property from a XML instance.
	 *
	 * @return Boolean wrapped
	 **/

	Boolean getWrapped();

	/**
	 * Sets the wrapped property of an XML instance
	 * to the parameter.
	 *
	 * @param wrapped
	 */

	void setWrapped(Boolean wrapped);

	/**
	 * Sets the wrapped property of an XML instance
	 * to the parameter and returns the instance.
	 *
	 * @param wrapped
	 * @return XML instance with the set wrapped property
	 */

	XML wrapped(Boolean wrapped);

}