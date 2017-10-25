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

package org.eclipse.microprofile.openapi.models.headers;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.examples.Example;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.Schema;

public interface Header extends Constructible, Extensible {
	
    /**
     * Gets or Sets style
     */
    public enum StyleEnum {
        SIMPLE("simple");

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
	 * returns the description property from a Header instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * sets this Header's description property to the given description.
	 *
	 * @param String description
	 */
	void setDescription(String description);

	/**
	 * sets this Header's description property to the given description and
	 * returns this instance of Header
	 *
	 * @param String description
	 * @return Header
	 */
	Header description(String description);

	/**
	 * returns the required property from a Header instance.
	 *
	 * @return Boolean required
	 **/

	Boolean getRequired();

	/**
	 * sets this Header's required property to the given required.
	 *
	 * @param Boolean required
	 */
	void setRequired(Boolean required);

	/**
	 * sets this Header's required property to the given required and
	 * returns this instance of Header
	 *
	 * @param Boolean required
	 * @return Header
	 */
	Header required(Boolean required);

	/**
	 * returns the deprecated property from a Header instance.
	 *
	 * @return Boolean deprecated
	 **/

	Boolean getDeprecated();

	/**
	 * sets this Header's deprecated property to the given deprecated.
	 *
	 * @param Boolean deprecated
	 */
	void setDeprecated(Boolean deprecated);

	/**
	 * sets this Header's deprecated property to the given deprecated and
	 * returns this instance of Header
	 *
	 * @param Boolean deprecated
	 * @return Header
	 */
	Header deprecated(Boolean deprecated);

	/**
	 * returns the allowEmptyValue property from a Header instance.
	 *
	 * @return Boolean allowEmptyValue
	 **/

	Boolean getAllowEmptyValue();

	/**
	 * sets this Header's allowEmptyValue property to the given allowEmptyValue.
	 *
	 * @param Boolean allowEmptyValue
	 */
	void setAllowEmptyValue(Boolean allowEmptyValue);

	/**
	 * sets this Header's allowEmptyValue property to the given allowEmptyValue and
	 * returns this instance of Header
	 *
	 * @param Boolean allowEmptyValue
	 * @return Header
	 */
	Header allowEmptyValue(Boolean allowEmptyValue);

	/**
	 * returns the style property from a Header instance.
	 *
	 * @return StyleEnum style
	 **/

	StyleEnum getStyle();

	/**
	 * sets this Header's style property to the given style.
	 *
	 * @param StyleEnum style
	 */
	void setStyle(StyleEnum style);

	/**
	 * sets this Header's style property to the given style and
	 * returns this instance of Header
	 *
	 * @param StyleEnum style
	 * @return Header
	 */
	Header style(StyleEnum style);

	/**
	 * returns the explode property from a Header instance.
	 *
	 * @return Boolean explode
	 **/

	Boolean getExplode();

	/**
	 * sets this Header's explode property to the given explode.
	 *
	 * @param Boolean allowEmptyValue
	 */
	void setExplode(Boolean explode);

	/**
	 * sets this Header's explode property to the given explode and
	 * returns this instance of Header
	 *
	 * @param Boolean explode
	 * @return Header
	 */
	Header explode(Boolean explode);

	/**
	 * returns the schema property from a Header instance.
	 *
	 * @return Schema schema
	 **/

	Schema getSchema();

	/**
	 * sets this Header's schema property to the given schema.
	 *
	 * @param Schema schema
	 */
	void setSchema(Schema schema);

	/**
	 * sets this Header's schema property to the given schema and
	 * returns this instance of Header
	 *
	 * @param Schema schema
	 * @return Header
	 */
	Header schema(Schema schema);

	/**
	 * returns the examples property from a Header instance.
	 *
	 * @return Map&lt;String, Example&gt; examples
	 **/

	Map<String, Example> getExamples();

	/**
	 * Sets the examples map of header instance to parameter.
	 *
	 * @param examples
	 */

	void setExamples(Map<String, Example> examples);

	/**
	 * Sets the examples map of header instance
	 * to parameter and returns the instance.
	 *
	 * @param examples
	 * @return Header instance with set examples map.
	 */

	Header examples(Map<String, Example> examples);

	/**
	 * Adds a key-value item to examples map
	 * of header instance and returns the instance.
	 *
	 * @param key
	 * @param examplesItem
	 * @return Header instance with a key-value pair added to examples map
	 */

	Header addExample(String key, Example examplesItem);

	/**
	 * returns the example property from a Header instance.
	 *
	 * @return String example
	 **/

	String getExample();

	/**
	 * sets this Header's example property to the given example.
	 *
	 * @param String example
	 */
	void setExample(String example);

	/**
	 * sets this Header's example property to the given example and
	 * returns this instance of Header
	 *
	 * @param String example
	 * @return Header
	 */
	Header example(String example);

	/**
	 * returns the content property from a Header instance.
	 *
	 * @return Content content
	 **/

	Content getContent();

	/**
	 * sets this Header's content property to the given content.
	 *
	 * @param Content content
	 */
	void setContent(Content content);

	/**
	 * sets this Header's content property to the given content and
	 * returns this instance of Header
	 *
	 * @param Content content
	 * @return Header
	 */
	Header content(Content content);

	/**
	 * returns the $ref property from a Header instance.
	 *
	 * @return String $ref
	 */
	String get$ref();

	/**
	 * sets this Header's $ref property to the given $ref.
	 *
	 * @param String $ref
	 */
	void set$ref(String $ref);

	/**
	 * sets this Header's $ref property to the given $ref and
	 * returns this instance of Header
	 *
	 * @param String $ref
	 * @return Header
	 */
	Header $ref(String $ref);

}