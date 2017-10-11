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

package org.eclipse.microprofile.openapi.models.parameters;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.examples.Example;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.Schema;

/**
 * Parameter
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#parameterObject"
 */
public interface Parameter {

	/**
	 * Gets or Sets style
	 */
	enum StyleEnum {
	    MATRIX("matrix"),
	    LABEL("label"),
	    FORM("form"),
	    SIMPLE("simple"),
	    SPACEDELIMITED("spaceDelimited"),
	    PIPEDELIMITED("pipeDelimited"),
	    DEEPOBJECT("deepObject");
	
	    private final String value;
	
	    StyleEnum(String value) {
	        this.value = value;
	    }
	
	    @Override
	    public String toString() {
	        return String.valueOf(value);
	    }
	}

	/**
	 * returns the name property from a Parameter instance.
	 *
	 * @return String name
	 **/

	String getName();

	/**
	 * Sets the name property of a Parameter instance
	 * to the parameter.
	 *
	 * @param name
	 */

	void setName(String name);

	/**
	 * Sets the name property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param name
	 * @return Parameter instance with the modified name property
	 */

	Parameter name(String name);

	/**
	 * returns the in property from a Parameter instance.
	 *
	 * @return String in
	 **/

	String getIn();

	/**
	 * Sets the in property of a Parameter instance
	 * to the parameter.
	 * If in property is set to path then also sets
	 * required property to true.
	 *
	 * @param in
	 */

	void setIn(String in);

	/**
	 * Sets the in property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param in
	 * @return Parameter instance with the modified in property
	 */

	Parameter in(String in);

	/**
	 * returns the description property from a Parameter instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a Parameter instance
	 * to the parameter.
	 *
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param description
	 * @return Parameter instance with the modified description property
	 */

	Parameter description(String description);

	/**
	 * returns the required property from a Parameter instance.
	 *
	 * @return Boolean required
	 **/

	Boolean getRequired();

	/**
	 * Sets the required property of a Parameter instance
	 * to the parameter.
	 *
	 * @param required
	 */

	void setRequired(Boolean required);

	/**
	 * Sets the required property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param required
	 * @return Parameter instance with the modified required property
	 */

	Parameter required(Boolean required);

	/**
	 * returns the deprecated property from a Parameter instance.
	 *
	 * @return Boolean deprecated
	 **/

	Boolean getDeprecated();

	/**
	 * Sets the deprecated property of a Parameter instance
	 * to the parameter.
	 *
	 * @param deprecated
	 */

	void setDeprecated(Boolean deprecated);

	/**
	 * Sets the deprecated property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param deprecated
	 * @return Parameter instance with the modified deprecated property
	 */

	Parameter deprecated(Boolean deprecated);

	/**
	 * returns the allowEmptyValue property from a Parameter instance.
	 *
	 * @return Boolean allowEmptyValue
	 **/

	Boolean getAllowEmptyValue();

	/**
	 * Sets the allowEmptyValue property of a Parameter instance
	 * to the parameter.
	 *
	 * @param allowEmptyValue
	 */

	void setAllowEmptyValue(Boolean allowEmptyValue);

	/**
	 * Sets the allowEmptyValue property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param allowEmptyValue
	 * @return Parameter instance with the modified allowEmptyValue property
	 */

	Parameter allowEmptyValue(Boolean allowEmptyValue);

	/**
	 * returns the style property from a Parameter instance.
	 *
	 * @return StyleEnum style
	 **/

	Parameter.StyleEnum getStyle();

	/**
	 * Sets the style property of a Parameter instance
	 * to the parameter.
	 *
	 * @param style
	 */

	void setStyle(Parameter.StyleEnum style);

	/**
	 * Sets the style property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param style
	 * @return Parameter instance with the modified style property
	 */

	Parameter style(Parameter.StyleEnum style);

	/**
	 * returns the explode property from a Parameter instance.
	 *
	 * @return Boolean explode
	 **/

	Boolean getExplode();

	/**
	 * Sets the explode property of a Parameter instance
	 * to the parameter.
	 *
	 * @param explode
	 */

	void setExplode(Boolean explode);

	/**
	 * Sets the explode property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param explode
	 * @return Parameter instance with the modified explode property
	 */

	Parameter explode(Boolean explode);

	/**
	 * returns the allowReserved property from a Parameter instance.
	 *
	 * @return Boolean allowReserved
	 **/

	Boolean getAllowReserved();

	/**
	 * Sets the allowReserved property of a Parameter instance
	 * to the parameter.
	 *
	 * @param allowReserved
	 */

	void setAllowReserved(Boolean allowReserved);

	/**
	 * Sets the allowReserved property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param allowReserved
	 * @return Parameter instance with the modified allowReserved property
	 */

	Parameter allowReserved(Boolean allowReserved);

	/**
	 * returns the schema property from a Parameter instance.
	 *
	 * @return Schema schema
	 **/

	Schema getSchema();

	/**
	 * Sets the schema property of a Parameter instance
	 * to the parameter.
	 *
	 * @param schema
	 */

	void setSchema(Schema schema);

	/**
	 * Sets the schema property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param schema
	 * @return Parameter instance with the modified schema property
	 */

	Parameter schema(Schema schema);

	/**
	 * returns the examples property from a Parameter instance.
	 *
	 * @return Map&lt;String, Example&gt; examples
	 **/

	Map<String, Example> getExamples();

	void setExamples(Map<String, Example> examples);

	/**
	 * Sets the examples property of a Parameter instance
	 * to the parameter.
	 *
	 * @param examples
	 */

	Parameter examples(Map<String, Example> examples);

	/**
	 * Adds an example item to the examples property of a Parameter instance
	 * at the specified key and returns the instance.
	 * If examples is null, creates a new HashMap and adds item
	 *
	 * @param key
	 * @param examplesItem
	 * @return Parameter instance with the added example item
	 */

	Parameter addExamples(String key, Example examplesItem);

	/**
	 * returns the example property from a Parameter instance.
	 *
	 * @return String example
	 **/

	String getExample();

	/**
	 * Sets the example property of a Parameter instance
	 * to the parameter.
	 *
	 * @param example
	 */

	void setExample(String example);

	/**
	 * Sets the example property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param example
	 * @return Parameter instance with the modified example property
	 */

	Parameter example(String example);

	/**
	 * returns the content property from a Parameter instance.
	 *
	 * @return Content content
	 **/

	Content getContent();

	/**
	 * Sets the content property of a Parameter instance
	 * to the parameter.
	 *
	 * @param content
	 */

	void setContent(Content content);

	/**
	 * Sets the content property of a Parameter instance
	 * to the parameter and returns the instance.
	 *
	 * @param content
	 * @return Parameter instance with the modified content property
	 */

	Parameter content(Content content);

	/**
	 * returns the $ref property from a Parameter instance.
	 *
	 * @return String $ref
	 **/

	String get$ref();

	/**
	 * Sets $ref property of a Parameter instance
	 * to the parameter.
	 *
	 * @param $ref
	 */

	void set$ref(String $ref);

	/**
	 * Sets $ref property of a Parameter instance
	 * to the parameter and return the instance.
	 *
	 * @param $ref
	 * @return Parameter instance with the set $ref property.
	 */

	Parameter $ref(String $ref);

	/**
	 * Returns extensions property of a Parameter instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map at
	 * the specified key.
	 * <p>
	 * If extensions is null, creates a new HashMap
	 * and adds item to it
	 *
	 * @param String name - map key
	 * @param Object value - map value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of a Parameter instance
	 *
	 * @param Map&lt;String, Object&gt; extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}