/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
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

package org.eclipse.microprofile.openapi.annotations.media;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;

/**
 * The SchemaProperty Object allows the definition of input and output data types nested within the
 * <code>properties</code> attribute of a {@link Schema} annotation. These types can be objects, but also primitives and
 * arrays.
 *
 * This object is an extended subset of the JSON Schema draft specification 2020-12.
 *
 * @see <a href= "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#schemaObject">OpenAPI
 *      Specification Schema Object</a>
 *
 * @since 2.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SchemaProperty {
    /**
     * Provides a java class as implementation for this schema. When provided, additional information in the Schema
     * annotation (except for type information) will augment the java class after introspection.
     *
     * @return a class that implements this schema
     **/
    Class<?> implementation() default Void.class;

    /**
     * Provides a java class to be used to disallow matching properties. Inline or referenced schema MUST be of a Schema
     * Object and not a standard JSON Schema.
     *
     * @return a class with disallowed properties
     **/
    Class<?> not() default Void.class;

    /**
     * Provides an array of java class implementations which can be used to describe multiple acceptable schemas. If
     * more than one match the derived schemas, a validation error will occur.
     *
     * @return the list of possible classes for a single match
     **/
    Class<?>[] oneOf() default {};

    /**
     * Provides an array of java class implementations which can be used to describe multiple acceptable schemas. If any
     * match, the schema will be considered valid.
     *
     * @return the list of possible class matches
     **/
    Class<?>[] anyOf() default {};

    /**
     * Provides an array of java class implementations which can be used to describe multiple acceptable schemas. If all
     * match, the schema will be considered valid.
     *
     * @return the list of classes to match
     **/
    Class<?>[] allOf() default {};

    /**
     * The name of the property.
     * <p>
     * The name will be used as the key to add this schema to the parent schema's 'properties' map.
     * </p>
     *
     * @return the name of the schema
     **/
    String name();

    /**
     * A title to explain the purpose of the schema.
     *
     * @return the title of the schema
     **/
    String title() default "";

    /**
     * Constrains a value such that when divided by the multipleOf, the result must be an integer. Ignored if the value
     * is {@code 0}.
     *
     * @return the multiplier constraint of the schema
     **/
    double multipleOf() default 0;

    /**
     * Sets the maximum numeric value for a property. Value must be a valid number. Ignored if the value is an empty
     * string or not a number.
     *
     * @return the maximum value for this schema
     **/
    String maximum() default "";

    /**
     * If true, makes the maximum value exclusive, or a less-than criteria.
     *
     * @return the exclusive maximum value for this schema
     **/
    boolean exclusiveMaximum() default false;

    /**
     * Sets the minimum numeric value for a property. Value must be a valid number. Ignored if the value is an empty
     * string or not a number.
     *
     * @return the minimum value for this schema
     **/
    String minimum() default "";

    /**
     * If true, makes the minimum value exclusive, or a greater-than criteria.
     *
     * @return the exclusive minimum value for this schema
     **/
    boolean exclusiveMinimum() default false;

    /**
     * Sets the maximum length of a string value. Ignored if the value is negative.
     *
     * @return the maximum length of this schema
     **/
    int maxLength() default Integer.MAX_VALUE;

    /**
     * Sets the minimum length of a string value. Ignored if the value is negative.
     *
     * @return the minimum length of this schema
     **/
    int minLength() default 0;

    /**
     * A regular expression that the value must satisfy. Ignored if the value is an empty string.
     * <p>
     * If the instance is a string, the regular expression must match the instance.
     *
     * @return the ECMA-262 regular expression to match against
     **/
    String pattern() default "";

    /**
     * Constrains the number of arbitrary properties when additionalProperties is defined. Ignored if value is 0.
     *
     * @return the maximum number of properties for this schema
     **/
    int maxProperties() default 0;

    /**
     * Constrains the number of arbitrary properties when additionalProperties is defined. Ignored if value is 0.
     *
     * @return the minimum number of properties for this schema
     **/
    int minProperties() default 0;

    /**
     * Allows multiple properties in an object to be marked as required.
     *
     * @return the list of required schema properties
     **/
    String[] requiredProperties() default {};

    /**
     * A description of the schema.
     *
     * @return this schema's description
     **/
    String description() default "";

    /**
     * Provides an optional override for the format.
     * <p>
     * If a consumer is unaware of the meaning of the format, they shall fall back to using the basic type without
     * format. For example, if \&quot;type: integer, format: int128\&quot; were used to designate a very large integer,
     * most consumers will not understand how to handle it, and fall back to simply \&quot;type: integer\&quot;
     * </p>
     *
     * @return this schema's format
     **/
    String format() default "";

    /**
     * Reference value to a Schema definition.
     * <p>
     * This property provides a reference to an object defined elsewhere.
     * <p>
     * Unlike {@code ref} on most MP OpenAPI annotations, this property is <em>not</em> mutually exclusive with other
     * properties.
     *
     * @return a reference to a schema definition
     **/
    String ref() default "";

    /**
     * Allows sending a null value for the defined schema.
     *
     * @return whether or not this schema is nullable
     **/
    boolean nullable() default false;

    /**
     * Declares the property as "read only". This means that it MAY be sent as part of a response but SHOULD NOT be sent
     * as part of the request.
     * <p>
     * If the property is marked as readOnly being true and is in the required list, the required will take effect on
     * the response only. A property MUST NOT be marked as both readOnly and writeOnly being true.
     * </p>
     *
     * @return whether or not this schema is read only
     **/
    boolean readOnly() default false;

    /**
     * Declares the property as "write only". Therefore, it MAY be sent as part of a request but SHOULD NOT be sent as
     * part of the response.
     * <p>
     * If the property is marked as writeOnly being true and is in the required list, the required will take effect on
     * the request only. A property MUST NOT be marked as both readOnly and writeOnly being true.
     * </p>
     *
     * @return whether or not this schema is write only
     **/
    boolean writeOnly() default false;

    /**
     * A free-form property to include an example of an instance for this schema.
     *
     * @return an example of this schema
     * @deprecated use {@link #examples()}
     **/
    @Deprecated(since = "4.0")
    String example() default "";

    /**
     * A free-form property to include examples of an instance for this schema.
     * <p>
     * Each example SHOULD validate against this schema.
     * <p>
     * If the schema {@link #type()} is STRING, the value will be interpreted as a literal string, otherwise it will be
     * parsed as JSON.
     *
     * @return an array of examples of this schema
     * @since 4.0
     **/
    String[] examples() default {};

    /**
     * Additional external documentation for this schema.
     *
     * @return additional schema documentation
     **/
    ExternalDocumentation externalDocs() default @ExternalDocumentation();

    /**
     * Specifies that a schema is deprecated and SHOULD be transitioned out of usage.
     *
     * @return whether or not this schema is deprecated
     **/
    boolean deprecated() default false;

    /**
     * Provides an override for the basic type of the schema.
     * <p>
     * Value MUST be a string. Multiple types via an array are not supported.
     * </p>
     * MUST be a valid type per the OpenAPI Specification.
     *
     * @return the type of this schema
     **/
    SchemaType type() default SchemaType.DEFAULT;

    /**
     * Provides a list of enum values. Corresponds to the enum property in the OAS schema and the enumeration property
     * in the schema model.
     *
     * @return a list of allowed schema values
     */
    String[] enumeration() default {};

    /**
     * Provides a default value. The default value represents what would be assumed by the consumer of the input as the
     * value of the schema if one is not provided.
     * <p>
     * Unlike JSON Schema, the value MUST conform to the defined type for the Schema Object defined at the same level.
     * </p>
     * For example, if type is string, then default can be "foo" but cannot be 1.
     *
     * @return the default value of this schema
     */
    String defaultValue() default "";

    /**
     * Provides a discriminator property value. Adds support for polymorphism.
     * <p>
     * The discriminator is an object name that is used to differentiate between other schemas which may satisfy the
     * payload description.
     * </p>
     *
     * @return the discriminator property
     */
    String discriminatorProperty() default "";

    /**
     * An array of discriminator mappings.
     *
     * @return the discriminator mappings for this schema
     */
    DiscriminatorMapping[] discriminatorMapping() default {};

    /**
     * Allows schema to be marked as hidden.
     *
     * @return whether or not this schema is hidden
     */
    boolean hidden() default false;

    /**
     * Only applicable if type=array. Sets the maximum number of items in an array. This integer MUST be greater than,
     * or equal to, 0.
     * <p>
     * An array instance is valid against "maxItems" if its size is less than, or equal to, the value of this keyword.
     * </p>
     * Ignored if value is Integer.MIN_VALUE.
     *
     * @return the maximum number of items in this array
     **/
    int maxItems() default Integer.MIN_VALUE;

    /**
     * Only applicable if type=array. Sets the minimum number of items in an array. This integer MUST be greater than,
     * or equal to, 0.
     * <p>
     * An array instance is valid against "minItems" if its size is greater than, or equal to, the value of this
     * keyword.
     * </p>
     * Ignored if value is Integer.MAX_VALUE.
     *
     * @return the minimum number of items in this array
     **/
    int minItems() default Integer.MAX_VALUE;

    /**
     * Only applicable if type=array. Determines if the items in the array SHOULD be unique.
     * <p>
     * If false, the instance validates successfully. If true, the instance validates successfully if all of its
     * elements are unique.
     * </p>
     *
     * @return whether the items in this array are unique
     **/
    boolean uniqueItems() default false;

    /**
     * List of extensions to be added to the {@link org.eclipse.microprofile.openapi.models.media.Schema Schema} model
     * corresponding to the containing annotation.
     *
     * @return array of extensions
     *
     * @since 3.1
     */
    Extension[] extensions() default {};

    /**
     * A comment to be included in the schema
     * <p>
     * This value is set in the {@code $comment} property of the schema object
     *
     * @return the comment
     * @since 4.0
     */
    String comment() default "";

    /**
     * Requires that the instance must be a specific value. No other values are permitted.
     * <p>
     * The value is parsed as JSON if the schema type is anything other than STRING.
     *
     * @return the value which the instance must be equal to, expressed according to the type of the schema
     * @since 4.0
     */
    String constValue() default "";

    /**
     * A class used to create a schema used to control conditional evaluation. If an instance validates against the
     * {@code if} schema then it must also validate against the {@code then} schema. Otherwise it must validate against
     * the {@code else} schema.
     *
     * @return a class used to create the {@code if} schema
     * @see #thenSchema()
     * @see #elseSchema()
     * @since 4.0
     */
    Class<?> ifSchema() default Void.class;

    /**
     * A class used to create a schema that an instance must validate against if it validates against the {@code if}
     * schema.
     *
     * @return a class used to create the {@code then} schema
     * @see #ifSchema()
     * @see #elseSchema()
     * @since 4.0
     */
    Class<?> thenSchema() default Void.class;

    /**
     * A class used to create a schema that an instance must validate against if it does not validate against the
     * {@code if} schema.
     *
     * @return a class used to create the {@code else} schema
     * @see #ifSchema()
     * @see #thenSchema()
     * @since 4.0
     */
    Class<?> elseSchema() default Void.class;

    /**
     * Schemas which an instance must validate against if the instance has certain properties.
     * <p>
     * For each {@link DependentSchema} listed, if the instance is an object which has a property named
     * {@link DependentSchema#name() name()} then the instance must validate against the schema created from
     * {@link DependentSchema#schema() schema()}.
     *
     * @return an array of DependentSchema entries
     * @since 4.0
     */
    DependentSchema[] dependentSchemas() default {};

    /**
     * A schema which at least one element of an array instance must validate against.
     * <p>
     * The class is used to create a schema. If the instance is an array, then at least one element of the array must
     * validate against the schema.
     *
     * @return a class used to create a schema which at least one element of an array instance must validate against
     * @since 4.0
     */
    Class<?> contains() default Void.class;

    /**
     * Specifies the maximum number of elements which may validate against the {@link #contains()} schema.
     * <p>
     * If more than this number of elements of an array instance match the {@code contains} schema, the instance does
     * not validate against this schema.
     *
     * @return the maximum number of elements which may validate against the {@link #contains()} schema
     * @since 4.0
     */
    int maxContains() default Integer.MAX_VALUE;

    /**
     * Specifies the minimum number of elements which must validate against the {@link #contains()} schema.
     * <p>
     * If fewer than this number of elements of an array instance match the {@code contains} schema, the instance does
     * not validate against this schema.
     *
     * @return the minimum number of elements which must validate against the {@link #contains()} schema
     * @since 4.0
     */
    int minContains() default 0;

    /**
     * Schemas which the leading elements of an array instance must validate against.
     * <p>
     * The array of classes is used to create an array of schemas. If an instance is an array, the first element of the
     * array must validate against the first schema, the second element must validate against the second schema and so
     * on.
     *
     * @return an array of classes used to create an array of schemas used to validate the leading elements of an array
     *         instance
     * @since 4.0
     */
    Class<?>[] prefixItems() default {};

    /**
     * Applies subschemas against properties matched by regular expressions.
     * <p>
     * For each {@link PatternProperty} listed, for each property whose name matches {@link PatternProperty#regex() |
     * regex()}, its value must validate against the schema created from {@link PatternProperty#schema() schema()}.
     *
     * @return a mapping from regular expressions to schemas
     * @since 4.0
     */
    PatternProperty[] patternProperties() default {};

    /**
     * Specifies that certain properties must be present if other properties are present.
     * <p>
     * For each {@link DependentRequired} entry in the list, if the instance is an object and has a property named
     * {@link DependentRequired#name()} then it must also have property named for each entry of
     * {@link DependentRequired#requires()} to validate against this schema.
     *
     * @return the properties required if certain other properties are present
     * @since 4.0
     */
    DependentRequired[] dependentRequired() default {};

    /**
     * A schema which the names of properties of an object instance must validate against.
     * <p>
     * The class is used to create a schema. If the instance is an object, then the name of each property in the
     * instance must validate against the schema.
     *
     * @return a schema that property names must validate against
     * @since 4.0
     */
    Class<?> propertyNames() default Void.class;

    /**
     * The encoding used to allow binary data to be stored in a string.
     * <p>
     * If the instance is a string, this property specifies that it contains binary data encoded as text using the
     * specified encoding (e.g. base64).
     *
     * @return the encoding
     * @since 4.0
     */
    String contentEncoding() default "";

    /**
     * The media type of the data in a string.
     * <p>
     * If the instance is a string, this property specifies the media type of the data it contains. If
     * {@link #contentEncoding()} is also set, it specifies the media type of the decoded string.
     *
     * @return the media type of the data in a string
     * @since 4.0
     */
    String contentMediaType() default "";

    /**
     * The schema that data in a string must validate against.
     * <p>
     * The class is used to create a schema. If the instance is a string and {@link #contentMediaType()} is set, the
     * data must validate against the schema when interpreted as the given media type.
     *
     * @return a class used to create a schema used to validate the data in a string
     * @since 4.0
     */
    Class<?> contentSchema() default Void.class;
}
