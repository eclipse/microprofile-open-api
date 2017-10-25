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

import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.examples.Example;
import org.eclipse.microprofile.openapi.models.media.MediaType;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.Schema;

/**
 * Parameter
 * <p>
 * Describes a single operation parameter.
 * <p>
 * A unique parameter is defined by a combination of a name and location.
 * <p>
 * Parameter Locations
 * <p>
 * There are four possible parameter locations specified by the in field:
 * <ul>
 * <li>path - Used together with Path Templating, where the parameter value is
 * actually part of the operation's URL. This does not include the host or base
 * path of the API. For example, in /items/{itemId}, the path parameter is
 * itemId.</li>
 * <li>query - Parameters that are appended to the URL. For example, in
 * /items?id=###, the query parameter is id.</li>
 * <li>header - Custom headers that are expected as part of the request. Note
 * that RFC7230 states header names are case insensitive.</li>
 * <li>cookie - Used to pass a specific cookie value to the API.</li>
 * </ul>
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
 * <td>Allows for an external definition of this parameter. The referenced
 * structure MUST be in the format of a Parameter Object. This field represents
 * the $ref field in the OAS file. If there are conflicts between the referenced
 * definition and this Parameter's definition, the behavior is undefined.</td>
 * </tr>
 * <tr>
 * <td>name</td>
 * <td>string</td>
 * <td>REQUIRED. The name of the parameter. Parameter names are case sensitive.
 * <ul>
 * <li>If in is "path", the name field MUST correspond to the associated path
 * segment from the path field in the Paths Object. See Path Templating for
 * further information.</li>
 * <li>If in is "header" and the name field is "Accept", "Content-Type" or
 * "Authorization", the parameter definition SHALL be ignored.</li>
 * <li>For all other cases, the name corresponds to the parameter name used by
 * the in property.</li></ul></td>
 * </tr>
 * <tr>
 * <td>in</td>
 * <td>string</td>
 * <td>REQUIRED. The location of the parameter. Possible values are "query",
 * "header", "path" or "cookie".</td>
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
 * <td>Determines whether this parameter is mandatory. If the parameter location
 * is "path", this property is REQUIRED and its value MUST be true. Otherwise,
 * the property MAY be included and its default value is false.</td>
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
 * The rules for serialization of the parameter are specified in one of two
 * ways. For simpler scenarios, a schema and style can describe the structure
 * and syntax of the parameter.
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
 * type of the parameter value. Default values (based on value of in): for query
 * - form; for path - simple; for header - simple; for cookie - form.</td>
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
 * <td>Map[ string, {@link Example Example Object} | {@link Example Reference
 * Object}]</td>
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
 * In order to support common ways of serializing simple parameters, a set of
 * style values are defined.
 * <table border=1 cellpadding="8" style="border-collapse: collapse">
 * <tr>
 * <th>Style</th>
 * <th>Type</th>
 * <th>In</th>
 * <th>Comments</th>
 * </tr>
 * <tr>
 * <td>matrix</td>
 * <td>primitive, array, object</td>
 * <td>path</td>
 * <td>Path-style parameters defined by RFC6570</td>
 * </tr>
 * <tr>
 * <td>label</td>
 * <td>primitive, array, object</td>
 * <td>path</td>
 * <td>Label style parameters defined by RFC6570</td>
 * </tr>
 * <tr>
 * <td>form</td>
 * <td>primitive, array, object</td>
 * <td>query, cookie</td>
 * <td>Form style parameters defined by RFC6570. This option replaces
 * collectionFormat with a csv (when explode is false) or multi (when explode is
 * true) value from OpenAPI 2.0.</td>
 * </tr>
 * <tr>
 * <td>simple</td>
 * <td>array</td>
 * <td>path, header</td>
 * <td>Simple style parameters defined by RFC6570. This option replaces
 * collectionFormat with a csv value from OpenAPI 2.0.</td>
 * </tr>
 * <tr>
 * <td>spaceDelimited</td>
 * <td>array</td>
 * <td>query</td>
 * <td>Space separated array values. This option replaces collectionFormat equal
 * to ssv from OpenAPI 2.0.</td>
 * </tr>
 * <tr>
 * <td>pipeDelimited</td>
 * <td>array</td>
 * <td>query</td>
 * <td>Pipe separated array values. This option replaces collectionFormat equal
 * to pipes from OpenAPI 2.0.</td>
 * </tr>
 * <tr>
 * <td>deepObject</td>
 * <td>object</td>
 * <td>query</td>
 * <td>Provides a simple way of rendering nested objects using form
 * parameters.</td>
 * </tr>
 * </table>
 * 
 * @see <a href=
 *      "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#parameterObject">OpenAPI
 *      Specification Parameter Object</a>
 */
public interface Parameter extends Extensible {

	/**
	 * The values allowed for the style field.
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
	 * Returns the name property from a Parameter instance.
	 *
	 * @return the name of the parameter
	 **/
	String getName();

	/**
	 * Sets the name property of a Parameter instance to the given string.
	 *
	 * @param name  the name of the parameter
	 */
	void setName(String name);

	/**
	 * Sets the name property of a Parameter instance to the given string.
	 *
	 * @param name  the name of the parameter
	 * @return the current Parameter instance
	 */
	Parameter name(String name);

	/**
	 * Returns the in property from a Parameter instance.
	 *
	 * @return the location of the parameter
	 **/
	String getIn();

	/**
	 * Sets the in property of a Parameter instance to the given string. If the
	 * in property is set to "path" then also sets the required property to
	 * true.
	 *
	 * @param in  the location of the parameter
	 */
	void setIn(String in);

	/**
	 * Sets the in property of a Parameter instance to the given string. If the
	 * in property is set to "path" then also sets the required property to
	 * true.
	 *
	 * @param in  the location of the parameter
	 * @return the current Parameter instance
	 */
	Parameter in(String in);

	/**
	 * Returns the description property from a Parameter instance.
	 *
	 * @return a brief description of the parameter
	 **/
	String getDescription();

	/**
	 * Sets the description property of a Parameter instance
	 * to the given string.
	 *
	 * @param description  a brief description of the parameter
	 */
	void setDescription(String description);

	/**
	 * Sets the description property of a Parameter instance
	 * to the given string.
	 *
	 * @param description  a brief description of the parameter
	 * @return the current Parameter instance
	 */
	Parameter description(String description);

	/**
	 * Returns the required property from a Parameter instance.
	 *
	 * @return indicates whether this parameter is mandatory
	 **/
	Boolean getRequired();

	/**
	 * Sets the required property of a Parameter instance to the given value.
	 *
	 * @param required  indicates whether this parameter is mandatory
	 */
	void setRequired(Boolean required);

	/**
	 * Sets the required property of a Parameter instance to the given value.
	 *
	 * @param required  indicates whether this parameter is mandatory
	 * @return the current Parameter instance
	 */
	Parameter required(Boolean required);

	/**
	 * Returns the deprecated property from a Parameter instance.
	 *
	 * @return specifies that a parameter is deprecated
	 **/
	Boolean getDeprecated();

	/**
	 * Sets the deprecated property of a Parameter instance to the given value.
	 *
	 * @param deprecated  specifies that a parameter is deprecated
	 */
	void setDeprecated(Boolean deprecated);

	/**
	 * Sets the deprecated property of a Parameter instance to the given value.
	 *
	 * @param deprecated  specifies that a parameter is deprecated
	 * @return the current Parameter instance
	 */
	Parameter deprecated(Boolean deprecated);

	/**
	 * Returns the allowEmptyValue property from a Parameter instance.
	 *
	 * @return specifies the ability to pass empty-valued parameters
	 **/
	Boolean getAllowEmptyValue();

	/**
	 * Sets the allowEmptyValue property of a Parameter instance to the given value.
	 *
	 * @param allowEmptyValue  specifies the ability to pass empty-valued parameters
	 */
	void setAllowEmptyValue(Boolean allowEmptyValue);

	/**
	 * Sets the allowEmptyValue property of a Parameter instance to the given value.
	 *
	 * @param allowEmptyValue  specifies the ability to pass empty-valued parameters
	 * @return the current Parameter instance
	 */
	Parameter allowEmptyValue(Boolean allowEmptyValue);

	/**
	 * Returns the style property from a Parameter instance.
	 *
	 * @return describes how the parameter value will be serialized
	 **/
	Parameter.StyleEnum getStyle();

	/**
	 * Sets the style property of a Parameter instance to the given value.
	 *
	 * @param style  describes how the parameter value will be serialized
	 */
	void setStyle(Parameter.StyleEnum style);

	/**
	 * Sets the style property of a Parameter instance to the given value.
	 *
	 * @param style  describes how the parameter value will be serialized
	 * @return the current Parameter instance
	 */
	Parameter style(Parameter.StyleEnum style);

	/**
	 * Returns the explode property from a Parameter instance.
	 *
	 * @return whether parameter values of type "array" or "object" generate separate parameters for each value
	 **/
	Boolean getExplode();

	/**
	 * Sets the explode property of a Parameter instance to the given value.
	 *
	 * @param explode  whether parameter values of type "array" or "object" generate separate parameters for each value
	 */
	void setExplode(Boolean explode);

	/**
	 * Sets the explode property of a Parameter instance to the given value.
	 *
	 * @param explode  whether parameter values of type "array" or "object" generate separate parameters for each value
	 * @return the current Parameter instance
	 */
	Parameter explode(Boolean explode);

	/**
	 * Returns the allowReserved property from a Parameter instance.
	 *
	 * @return specifies whether the parameter value should allow reserved characters
	 **/
	Boolean getAllowReserved();

	/**
	 * Sets the allowReserved property of a Parameter instance to the given value.
	 *
	 * @param allowReserved  specifies whether the parameter value should allow reserved characters
	 */
	void setAllowReserved(Boolean allowReserved);

	/**
	 * Sets the allowReserved property of a Parameter instance to the given value.
	 *
	 * @param allowReserved  specifies whether the parameter value should allow reserved characters
	 * @return the current Parameter instance
	 */
	Parameter allowReserved(Boolean allowReserved);

	/**
	 * Returns the schema property from a Parameter instance.
	 *
	 * @return schema defining the type used for the parameter
	 **/
	Schema getSchema();

	/**
	 * Sets the schema property of a Parameter instance to the given value.
	 *
	 * @param schema  schema defining the type used for the parameter
	 */
	void setSchema(Schema schema);

	/**
	 * Sets the schema property of a Parameter instance to the given value.
	 *
	 * @param schema  schema defining the type used for the parameter
	 * @return the current Parameter instance
	 */
	Parameter schema(Schema schema);

	/**
	 * Returns the examples property from a Parameter instance.
	 *
	 * @return examples of the media type
	 **/
	Map<String, Example> getExamples();

	/**
	 * Sets the examples property of a Parameter instance to the given value.
	 *
	 * @param examples  examples of the media type
	 */
	void setExamples(Map<String, Example> examples);

	/**
	 * Sets the examples property of a Parameter instance to the given value.
	 *
	 * @param examples  examples of the media type
	 * @return the current Parameter instance
	 */
	Parameter examples(Map<String, Example> examples);

	/**
	 * Adds an example of the media type using the specified key.
	 *
	 * @param key string to represent the example
	 * @param examplesItem  example of the media type
	 * @return the current Parameter instance
	 */
	Parameter addExamples(String key, Example examplesItem);

	/**
	 * Returns the example property from a Parameter instance.
	 *
	 * @return example of the media type
	 **/
	Object getExample();

	/**
	 * Sets the example property of a Parameter instance to the given object.
	 *
	 * @param example  example of the media type
	 */
	void setExample(Object example);

	/**
	 * Sets the example property of a Parameter instance to the given object.
	 *
	 * @param example  example of the media type
	 * @return the current Parameter instance
	 */
	Parameter example(Object example);

	/**
	 * Returns the content property from a Parameter instance.
	 *
	 * @return a map containing the media representations for the parameter
	 **/
	Content getContent();

	/**
	 * Sets the content property of a Parameter instance to the given object.
	 *
	 * @param content  a map containing the media representations for the parameter
	 */
	void setContent(Content content);

	/**
	 * Sets the content property of a Parameter instance to the given object.
	 *
	 * @param content  a map containing the media representations for the parameter
	 * @return the current Parameter instance
	 */
	Parameter content(Content content);

	/**
	 * Returns the reference property from a Parameter instance.
	 *
	 * @return  a reference to a parameter object in an OpenAPI document
	 **/
	String getReference();

	/**
	 * Sets reference property of a Parameter instance to the given string.
	 *
	 * @param reference  a reference to a header object in an OpenAPI document
	 *            especially in the components in this OpenAPI document
	 */
	void setReference(String reference);

	/**
	 * Sets reference property of a Parameter instance to the given string.
	 *
	 * @param reference  a reference to a header object in an OpenAPI document
	 *            especially in the components in this OpenAPI document
	 * @return the current Parameter instance
	 */
	Parameter reference(String reference);

}