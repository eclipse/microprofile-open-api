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

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.examples.Example;

/**
 * MediaType
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#mediaTypeObject"
 */
public interface MediaType extends Constructible {

	/**
	 * returns the schema property from a MediaType instance.
	 *
	 * @return Schema schema
	 **/

	Schema getSchema();

	/**
	 * Sets schema field of a MediaType instance to the
	 * parameter.
	 *
	 * @param schema
	 */

	void setSchema(Schema schema);

	/**
	 * Sets schema property of a MediaType instance to the
	 * parameter and returns the instance.
	 *
	 * @param schema
	 * @return MediaType instance with the set schema property
	 */

	MediaType schema(Schema schema);

	/**
	 * returns the examples map of String to Example from a MediaType instance.
	 *
	 * @return Map&lt;String, Example&gt; examples
	 **/

	Map<String, Example> getExamples();

	/**
	 * Sets examples field of a MediaType instance to the
	 * parameter.
	 *
	 * @param examples
	 */

	void setExamples(Map<String, Example> examples);

	/**
	 * Sets examples field of a MediaType instance to the
	 * parameter and returns the instance.
	 *
	 * @param examples
	 * @return MediaType instance with the set examples property
	 */

	MediaType examples(Map<String, Example> examples);

	/**
	 * Adds an example item to examples map of a MediaType instance
	 * and returns the instance.
	 * <p>
	 * If the examples property is null, creates a new HashMap
	 * and adds the item to it.
	 *
	 * @param key
	 * @param examplesItem
	 * @return MediaType instance with the set example item
	 */

	MediaType addExamples(String key, Example examplesItem);

	/**
	 * returns the example property from a MediaType instance.
	 *
	 * @return String example
	 **/

	String getExample();

	/**
	 * Sets example property of a MediaType instance to the
	 * parameter.
	 *
	 * @param example
	 */

	void setExample(String example);

	/**
	 * Sets example property of a MediaType instance to the
	 * parameter and returns the instance.
	 *
	 * @param example
	 * @return MediaType instance with the set example property
	 */

	MediaType example(String example);

	/**
	 * returns the encoding property from a MediaType instance.
	 *
	 * @return Encoding encoding
	 **/

	Map<String, Encoding> getEncoding();

	/**
	 * Sets encoding property of a MediaType instance to the
	 * parameter.
	 *
	 * @param encoding
	 */

	void setEncoding(Map<String, Encoding> encoding);

	/**
	 * Sets encoding property of a MediaType instance to the
	 * parameter and returns the instance.
	 *
	 * @param encoding
	 * @return MediaType instance with the set encoding property
	 */

	MediaType encoding(Map<String, Encoding> encoding);

	/**
	 * Adds an Encoding item to encoding map of a MediaType instance
	 * and returns the instance.
	 * <p>
	 * If the encoding property is null, creates a new HashMap
	 * and adds the item to it.
	 *
	 * @param String key
	 * @param Encoding encodingItem
	 * @return MediaType instance with the added encoding item
	 */

	MediaType addEncoding(String key, Encoding encodingItem);

	/**
	 * Returns extensions property of a MediaType instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map for
	 * the specified name key.
	 * <p>
	 * If the extensions property is null, creates a new HashMap
	 * and adds the item to it.
	 *
	 * @param name - map key
	 * @param value - map value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of a MediaType instance
	 * to the parameter.
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}