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

/**
 * Header
 * <p>
 * Describes a single operation header parameter.
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
 * <td>Allows for an external definition of this header. The referenced
 * structure MUST be in the format of a Header Object. This field represents the
 * $ref field in the OAS file. If there are conflicts between the referenced
 * definition and this Header's definition, the behavior is undefined.</td>
 * </tr>
 * <tr>
 * <td>description</td>
 * <td>string</td>
 * <td>A brief description of the parameter. This could contain examples of use.
 * CommonMark syntax MAY be used for rich text representation.</td>
 * </tr>
 * <tr>
 * <td>required</td>
 * <td>boolean</td>
 * <td>Determines whether this parameter is mandatory. The property MAY be
 * included and its default value is false.</td>
 * </tr>
 * <tr>
 * <td>deprecated</td>
 * <td>boolean</td>
 * <td>Specifies that a parameter is deprecated and SHOULD be transitioned out
 * of usage.</td>
 * </tr>
 * <tr>
 * <td>allowEmptyValue</td>
 * <td>boolean</td>
 * <td>Sets the ability to pass empty-valued parameters. This is valid only for
 * query parameters and allows sending a parameter with an empty value. Default
 * value is false. If style is used, and if behavior is n/a (cannot be
 * serialized), the value of allowEmptyValue SHALL be ignored.</td>
 * </tr>
 * </table>
 * The rules for serialization of the header parameter are specified in one of
 * two ways. For simpler scenarios, a schema and style can describe the
 * structure and syntax of the header parameter.
 * <table border=1 cellpadding="8" style="border-collapse: collapse">
 * <tr>
 * <th>Field Name</th>
 * <th>Type</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>style</td>
 * <td>string</td>
 * <td>Describes how the parameter value will be serialized depending on the
 * type of the parameter value. Default value for header is simple.</td>
 * </tr>
 * <tr>
 * <td>explode</td>
 * <td>boolean</td>
 * <td>When this is true, parameter values of type array or object generate
 * separate parameters for each value of the array or key-value pair of the map.
 * For other types of parameters this property has no effect. When style is
 * form, the default value is true. For all other styles, the default value is
 * false.</td>
 * </tr>
 * <tr>
 * <td>allowReserved</td>
 * <td>boolean</td>
 * <td>Determines whether the parameter value SHOULD allow reserved characters,
 * as defined by RFC3986 <code>:/?#[]@!$&'()*+,;=</code> to be included without
 * percent-encoding. This property only applies to parameters with an in value
 * of query. The default value is false.</td>
 * </tr>
 * <tr>
 * <td>schema</td>
 * <td>{@link Schema Schema Object} | {@link Schema Reference Object}</td>
 * <td>The schema defining the type used for the parameter.</td>
 * </tr>
 * <tr>
 * <td>example</td>
 * <td>Any</td>
 * <td>Example of the media type. The example SHOULD match the specified schema
 * and encoding properties if present. The example object is mutually exclusive
 * of the examples object. Furthermore, if referencing a schema which contains
 * an example, the example value SHALL override the example provided by the
 * schema. To represent examples of media types that cannot naturally be
 * represented in JSON or YAML, a string value can contain the example with
 * escaping where necessary.</td>
 * </tr>
 * <tr>
 * <td>examples</td>
 * <td>Map[ string, {@link Example Example Object} | {@link Example Reference Object}]</td>
 * <td>Examples of the media type. Each example SHOULD contain a value in the
 * correct format as specified in the parameter encoding. The examples object is
 * mutually exclusive of the example object. Furthermore, if referencing a
 * schema which contains an example, the examples value SHALL override the
 * example provided by the schema.</td>
 * </tr>
 * </table>
 * For more complex scenarios, the content property can define the media type
 * and schema of the parameter. A parameter MUST contain either a schema
 * property, or a content property, but not both. When example or examples are
 * provided in conjunction with the schema object, the example MUST follow the
 * prescribed serialization strategy for the parameter.
 * <table border=1 cellpadding="8" style="border-collapse: collapse">
 * <tr>
 * <th>Field Name</th>
 * <th>Type</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>content</td>
 * <td>Map[string, {@link MediaType Media Type Object} ]</td>
 * <td>A map containing the representations for the parameter. The key is the
 * media type and the value describes it. The map MUST only contain one
 * entry.</td>
 * </tr>
 * </table>
 * Style Values
 * <p>
 * For headers only one way of serializing the parameters, simple.
 * <table border=1 cellpadding="8">
 * <tr>
 * <th>Style</th>
 * <th>Type</th>
 * <th>Comments</th>
 * </tr>
 * <tr>
 * <td>simple</td>
 * <td>array</td>
 * <td>Simple style parameters defined by RFC6570. This option replaces
 * collectionFormat with a csv value from OpenAPI 2.0.</td>
 * </tr>
 * </table>
 */
public interface Header extends Constructible, Extensible {
	
    /**
     * Controls the style of serialization. Only one style is supported for headers.
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
	 * Returns the description property from a Header instance.
	 *
	 * @return a brief description of the header parameter.
	 **/
	String getDescription();

	/**
	 * Sets this Header's description property to the given string.
	 *
	 * @param description  a brief description of the header parameter
	 */
	void setDescription(String description);

	/**
	 * Sets this Header's description property to the given string.
	 *
	 * @param description  a brief description of the header parameter
	 * @return the current Header instance
	 */
	Header description(String description);

	/**
	 * Returns the required property from a Header instance.
	 *
	 * @return whether this parameter is mandatory
	 **/
	Boolean getRequired();

	/**
	 * Sets this Header's required property to the given value.
	 *
	 * @param required  whether this parameter is mandatory
	 */
	void setRequired(Boolean required);

	/**
	 * Sets this Header's required property to the given value.
	 *
	 * @param required  whether this parameter is mandatory
	 * @return the current Header instance
	 */
	Header required(Boolean required);

	/**
	 * Returns the deprecated property from a Header instance.
	 *
	 * @return  whether a parameter is deprecated
	 **/
	Boolean getDeprecated();

	/**
	 * Sets this Header's deprecated property to the given value.
	 *
	 * @param deprecated  whether a parameter is deprecated
	 */
	void setDeprecated(Boolean deprecated);

	/**
	 * Sets this Header's deprecated property to the given value.
	 *
	 * @param deprecated  whether a parameter is deprecated
	 * @return the current Header instance
	 */
	Header deprecated(Boolean deprecated);

	/**
	 * Returns the allowEmptyValue property from a Header instance.
	 *
	 * @return the ability to pass empty-valued parameters
	 **/
	Boolean getAllowEmptyValue();

	/**
	 * Sets this Header's allowEmptyValue property to the given value.
	 *
	 * @param allowEmptyValue  the ability to pass empty-valued parameters
	 */
	void setAllowEmptyValue(Boolean allowEmptyValue);

	/**
	 * Sets this Header's allowEmptyValue property to the given value.
	 *
	 * @param allowEmptyValue  the ability to pass empty-valued parameters
	 * @return the current Header instance
	 */
	Header allowEmptyValue(Boolean allowEmptyValue);

	/**
	 * Returns the style property from a Header instance.
	 *
	 * @return  how the parameter value will be serialized
	 **/
	StyleEnum getStyle();

	/**
	 * Sets this Header's style property to the given style.
	 *
	 * @param style  how the parameter value will be serialized
	 */
	void setStyle(StyleEnum style);

	/**
	 * Sets this Header's style property to the given style.
	 *
	 * @param style  how the parameter value will be serialized
	 * @return the current Header instance
	 */
	Header style(StyleEnum style);

	/**
	 * Returns the explode property from a Header instance.
	 *
	 * @return whether parameter values of type "array" or "object" generate separate parameters for each value
	 **/
	Boolean getExplode();

	/**
	 * Sets this Header's explode property to the given value.
	 *
	 * @param explode  whether parameter values of type "array" or "object" generate separate parameters for each value
	 */
	void setExplode(Boolean explode);

	/**
	 * Sets this Header's explode property to the given value.
	 *
	 * @param explode  whether parameter values of type "array" or "object" generate separate parameters for each value
	 * @return the current Header instance
	 */
	Header explode(Boolean explode);

	/**
	 * Returns the schema property from a Header instance.
	 *
	 * @return schema defining the type used for the parameter
	 **/
	Schema getSchema();

	/**
	 * Sets this Header's schema property to the given object.
	 *
	 * @param schema  schema defining the type used for the parameter
	 */
	void setSchema(Schema schema);

	/**
	 * Sets this Header's schema property to the given object.
	 *
	 * @param schema  schema defining the type used for the parameter
	 * @return the current Header instance
	 */
	Header schema(Schema schema);

	/**
	 * Returns the examples property from a Header instance.
	 *
	 * @return examples of the media type
	 **/
	Map<String, Example> getExamples();

	/**
	 * Sets the examples property of this Header instance to the given map.
	 *
	 * @param examples  examples of the media type
	 */
	void setExamples(Map<String, Example> examples);

	/**
	 * Sets the examples property of this Header instance to the given map.
	 *
	 * @param examples  examples of the media type
	 * @return the current Header instance 
	 */
	Header examples(Map<String, Example> examples);

	/**
	 * Adds an example of the media type using the specified key.
	 *
	 * @param key string to represent the example
	 * @param examplesItem  example of the media type
	 * @return the current Header instance
	 */
	Header addExample(String key, Example examplesItem);

	/**
	 * Returns the example property from a Header instance.
	 *
	 * @return example of the media type
	 **/
	Object getExample();

	/**
	 * Sets this Header's example property to the given object.
	 *
	 * @param example  example of the media type
	 */
	void setExample(Object example);

	/**
	 * Sets this Header's example property to the given object.
	 *
	 * @param example  example of the media type
	 * @return the current Header instance
	 */
	Header example(Object example);

	/**
	 * Returns the content property from a Header instance.
	 *
	 * @return a map containing the media representations for the parameter
	 **/
	Content getContent();

	/**
	 * Sets this Header's content property to the given object.
	 *
	 * @param content  a map containing the media representations for the parameter
	 */
	void setContent(Content content);

	/**
	 * Sets this Header's content property to the given object.
	 *
	 * @param content  a map containing the media representations for the parameter
	 * @return the current Header instance
	 */
	Header content(Content content);

	/**
	 * Returns the reference property from a Header instance.
	 *
	 * @return a reference to a header object in an OpenAPI document especially
	 *         in the components in this OpenAPI document
	 */
	String getReference();

	/**
	 * Sets this Header's reference property to the given string.
	 *
	 * @param reference  a reference to a header object in an OpenAPI document
	 *            especially in the components in this OpenAPI document
	 */
	void setReference(String reference);

	/**
	 * Sets this Header's reference property to the given string.
	 *
	 * @param reference  a reference to a header object in an OpenAPI document
	 *            especially in the components in this OpenAPI document
	 * @return the current Header instance
	 */
	Header reference(String reference);

}