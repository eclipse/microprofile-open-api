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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.ExternalDocumentation;

/**
 * Schema
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#schemaObject"
 */
public interface Schema<T> extends Constructible {

	/**
	 * returns the name property from a from a Schema instance. Ignored in serialization.
	 *
	 * @return String name
	 **/
	String getName();

	/**
	 * Sets the name property of a Schema instance
	 * to the parameter.
	 *
	 * @param name
	 */

	void setName(String name);

	/**
	 * Sets the name property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param name
	 * @return Schema instance with the modified name property
	 */

	Schema name(String name);

	/**
	 * returns the discriminator property from a Schema instance.
	 *
	 * @return Discriminator discriminator
	 **/

	Discriminator getDiscriminator();

	/**
	 * Sets discriminator property of a Schema instance
	 * to the parameter.
	 *
	 * @param discriminator
	 */

	void setDiscriminator(Discriminator discriminator);

	/**
	 * Sets discriminator property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param discriminator
	 * @return Schema instance with the modified discriminator.
	 */

	Schema discriminator(Discriminator discriminator);

	/**
	 * returns the title property from a Schema instance.
	 *
	 * @return String title
	 **/

	String getTitle();

	/**
	 * Sets the title property of a Schema instance
	 * to the parameter.
	 *
	 * @param title
	 */

	void setTitle(String title);

	/**
	 * Sets the title property of a Schema instance
	 * to the parameter and returns the modified instance.
	 *
	 * @param title
	 * @return Schema instance with the modified title.
	 */

	Schema title(String title);

	/**
	 * returns the _default property from a StringSchema instance.
	 *
	 * @return String _default
	 **/

	T getDefault();

	/**
	 * Set _default property of a Schema instance
	 * to the parameter.
	 *
	 * @param _default
	 */

	void setDefault(Object _default);

	/**
	 * Returns _enum property for a Schema instance.
	 *
	 * @return List&lt;T&gt; _enum
	 */

	List<T> getEnum();

	/**
	 * Sets _enum property of a Schema instance
	 * to the parameter.
	 *
	 * @param _enum
	 */

	void setEnum(List<T> _enum);

	/**
	 * Adds a generic type T item to _enum of a Schema instance.
	 *
	 * @param _enumItem
	 */

	void addEnumItemObject(T _enumItem);

	/**
	 * returns the multipleOf property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return BigDecimal multipleOf
	 **/

	BigDecimal getMultipleOf();

	/**
	 * Sets multipleOf property of a Schema instance
	 * to the parameter.
	 *
	 * @param multipleOf
	 */

	void setMultipleOf(BigDecimal multipleOf);

	/**
	 * Sets multipleOf property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param multipleOf
	 * @return Schema instance with the modified multipleOf property
	 */

	Schema multipleOf(BigDecimal multipleOf);

	/**
	 * returns the maximum property from a Schema instance.
	 *
	 * @return BigDecimal maximum
	 **/

	BigDecimal getMaximum();

	/**
	 * Sets maximum property of a Schema instance
	 * to the parameter.
	 *
	 * @param maximum
	 */

	void setMaximum(BigDecimal maximum);

	/**
	 * Sets maximum property of a Schema instance to the parameter
	 * and returns the instance.
	 *
	 * @param maximum
	 * @return Schema instance with the modified maximum property
	 */

	Schema maximum(BigDecimal maximum);

	/**
	 * returns the exclusiveMaximum property from a Schema instance.
	 *
	 * @return Boolean exclusiveMaximum
	 **/

	Boolean getExclusiveMaximum();

	/**
	 * Sets exclusiveMaximum property of a Schema instance
	 * to the parameter.
	 *
	 * @param exclusiveMaximum
	 */

	void setExclusiveMaximum(Boolean exclusiveMaximum);

	/**
	 * Sets exclusiveMaximum property of a Schema instance to the parameter
	 * and returns the instance.
	 *
	 * @param exclusiveMaximum
	 * @return Schema instance with modified exclusiveMaximum property.
	 */

	Schema exclusiveMaximum(Boolean exclusiveMaximum);

	/**
	 * returns the minimum property from a Schema instance.
	 *
	 * @return BigDecimal minimum
	 **/

	BigDecimal getMinimum();

	/**
	 * Sets minimum property of a Schema instance
	 * to the parameter.
	 *
	 * @param minimum
	 */

	void setMinimum(BigDecimal minimum);

	/**
	 * Sets minimum property of a Schema instance
	 * to the parameter and returns the instance
	 *
	 * @param minimum
	 * @return Schema instance with the modified minimum property.
	 */

	Schema minimum(BigDecimal minimum);

	/**
	 * returns the exclusiveMinimum property from a Schema instance.
	 *
	 * @return Boolean exclusiveMinimum
	 **/

	Boolean getExclusiveMinimum();

	/**
	 * Sets exclusiveMinimum property of a Schema instance
	 * to the parameter.
	 *
	 * @param exclusiveMinimum
	 */

	void setExclusiveMinimum(Boolean exclusiveMinimum);

	/**
	 * Sets exclusiveMinimum property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param exclusiveMinimum
	 * @return Schema instance with the modified exclusiveMinimum property.
	 */

	Schema exclusiveMinimum(Boolean exclusiveMinimum);

	/**
	 * returns the maxLength property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return Integer maxLength
	 **/

	Integer getMaxLength();

	/**
	 * Sets maxLength property of a Schema instance
	 * to the parameter.
	 *
	 * @param maxLength
	 */

	void setMaxLength(Integer maxLength);

	/**
	 * Sets maxLength property of a Schema instance
	 * to the parameter and returns the instance
	 *
	 * @param maxLength
	 * @return Schema instance with the modified maxLength property.
	 */

	Schema maxLength(Integer maxLength);

	/**
	 * returns the minLength property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return Integer minLength
	 **/

	Integer getMinLength();

	/**
	 * Sets minLength property of a Schema instance
	 * to the parameter.
	 *
	 * @param minLength
	 */

	void setMinLength(Integer minLength);

	/**
	 * Sets minLength property of a Schema instance
	 * to the parameter and returns the instance
	 *
	 * @param minLength
	 * @return Schema instance with the modified minLength property.
	 */

	Schema minLength(Integer minLength);

	/**
	 * returns the pattern property from a Schema instance.
	 *
	 * @return String pattern
	 **/

	String getPattern();

	/**
	 * Sets pattern property of a Schema instance
	 * to the parameter.
	 *
	 * @param pattern
	 */

	void setPattern(String pattern);

	/**
	 * Sets pattern property of a Schema instance
	 * to the parameter and returns the instance
	 *
	 * @param pattern
	 * @return Schema instance with the modified pattern property.
	 */

	Schema pattern(String pattern);

	/**
	 * returns the maxItems property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return Integer maxItems
	 **/

	Integer getMaxItems();

	/**
	 * Sets maxItems property of a Schema instance
	 * to the parameter.
	 *
	 * @param maxItems
	 */

	void setMaxItems(Integer maxItems);

	/**
	 * Sets maxItems property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param maxItems
	 * @return Schema instance with the modified maxItems property.
	 */

	Schema maxItems(Integer maxItems);

	/**
	 * returns the minItems property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return Integer minItems
	 **/

	Integer getMinItems();

	/**
	 * Sets minItems property of Schema instance
	 * to the parameter.
	 *
	 * @param minItems
	 */

	void setMinItems(Integer minItems);

	/**
	 * Sets minItems property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param minItems
	 * @return Schema instance with the modified minItems property.
	 */

	Schema minItems(Integer minItems);

	/**
	 * returns the uniqueItems property from a Schema instance.
	 *
	 * @return Boolean uniqueItems
	 **/

	Boolean getUniqueItems();

	/**
	 * Sets uniqueItems property of a Schema instance
	 * to the parameter.
	 *
	 * @param uniqueItems
	 */

	void setUniqueItems(Boolean uniqueItems);

	/**
	 * Sets uniqueItems property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param uniqueItems
	 * @return Schema instance with the modified uniqueItems property.
	 */

	Schema uniqueItems(Boolean uniqueItems);

	/**
	 * returns the maxProperties property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return Integer maxProperties
	 **/

	Integer getMaxProperties();

	/**
	 * Sets maxProperties property of a Schema instance
	 * to the parameter.
	 *
	 * @param maxProperties
	 */

	void setMaxProperties(Integer maxProperties);

	/**
	 * Sets maxProperties property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param maxProperties
	 * @return Schema instance with the modified maxProperty property.
	 */

	Schema maxProperties(Integer maxProperties);

	/**
	 * returns the minProperties property from a Schema instance.
	 * <p>
	 * minimum: 0
	 *
	 * @return Integer minProperties
	 **/

	Integer getMinProperties();

	/**
	 * Sets minProperties property of a Schema instance
	 * to the parameter.
	 *
	 * @param minProperties
	 */

	void setMinProperties(Integer minProperties);

	/**
	 * Sets minProperties property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param minProperties
	 * @return Schema instance with the modified minProperty property.
	 */

	Schema minProperties(Integer minProperties);

	/**
	 * returns the required property from a Schema instance.
	 *
	 * @return List&lt;String&gt; required
	 **/

	List<String> getRequired();

	/**
	 * Sets required property of a Schema instance if
	 * it is null or does not contain the List items
	 * passed in as method arguments.
	 *
	 * @param required
	 */

	void setRequired(List<String> required);

	/**
	 * Sets required List property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param required
	 * @return Schema instance with the set required property.
	 */

	Schema required(List<String> required);

	/**
	 * Adds an item to required List of a Schema instance.
	 * Creates new ArrayList if instance's required property is null.
	 *
	 * @param requiredItem
	 * @return Schema instance with added required item.
	 */

	Schema addRequiredItem(String requiredItem);

	/**
	 * returns the type property from a Schema instance.
	 *
	 * @return String type
	 **/

	String getType();

	/**
	 * Sets the type property of a Schema instance
	 * to the parameter.
	 *
	 * @param type
	 */

	void setType(String type);

	/**
	 * Sets the type property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param type
	 * @return Schema instance with the modified type property.
	 */

	Schema type(String type);

	/**
	 * returns the not property from a Schema instance.
	 *
	 * @return Schema not
	 **/

	Schema getNot();

	/**
	 * Sets the not property of a Schema instance
	 * to the parameter.
	 *
	 * @param not
	 */

	void setNot(Schema not);

	/**
	 * Sets the not property of a Schema instance
	 * to the parameter and
	 * returns the instance.
	 *
	 * @param not
	 * @return Schema with the modified not property.
	 */

	Schema not(Schema not);

	/**
	 * returns the properties property from a Schema instance.
	 *
	 * @return Map&lt;String, Schema&gt; properties
	 **/

	Map<String, Schema> getProperties();

	/**
	 * Sets properties property of a Schema instance
	 * to the parameter.
	 *
	 * @param properties
	 */

	void setProperties(Map<String, Schema> properties);

	/**
	 * Sets properties property of a Schema instance
	 * to the parameter and returns the modified instance.
	 *
	 * @param properties
	 * @return Schema instance with the set properties property.
	 */

	Schema properties(Map<String, Schema> properties);

	/**
	 * Adds a Schema property item at specified key to properties
	 * property of a Schema instance and returns the instance.
	 *
	 * @param key
	 * @param propertiesItem
	 * @return Schema instance with added property item.
	 */

	Schema addProperties(String key, Schema propertiesItem);

	/**
	 * returns the additionalProperties property from a Schema instance.
	 *
	 * @return Schema additionalProperties
	 **/

	Schema getAdditionalProperties();

	/**
	 * Sets additionalProperties property of a Schema instance
	 * to the parameter.
	 *
	 * @param additionalProperties
	 */

	void setAdditionalProperties(Schema additionalProperties);

	/**
	 * Sets additionalProperties property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param additionalProperties
	 * @return Schema instance with the set additionalProperties property
	 */

	Schema additionalProperties(Schema additionalProperties);

	/**
	 * returns the description property from a Schema instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets description property of a Schema instance
	 * to the parameter.
	 *
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets description property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param description
	 * @return Schema instance with the set description property
	 */

	Schema description(String description);

	/**
	 * returns the format property from a Schema instance.
	 *
	 * @return String format
	 **/

	String getFormat();

	/**
	 * Sets format property of a Schema instance
	 * to the parameter.
	 *
	 * @param format
	 */

	void setFormat(String format);

	/**
	 * Sets format property of a Schema instance
	 * to the parameter and returns the instance.
	 *
	 * @param format
	 * @return Schema instance with the set format property.
	 */

	Schema format(String format);

	/**
	 * returns the $ref property from a Schema instance.
	 *
	 * @return String $ref
	 **/
	String get$ref();

	/**
	 * Set $ref property of a Schema instance
	 * to the parameter.
	 *
	 * @param $ref
	 */

	void set$ref(String $ref);

	/**
	 * Set $ref property of a Schema instance
	 * to the parameter and return the instance.
	 *
	 * @param $ref
	 * @return Schema instance with the set $ref property.
	 */

	Schema $ref(String $ref);

	/**
	 * returns the nullable property from a Schema instance.
	 *
	 * @return Boolean nullable
	 **/

	Boolean getNullable();

	/**
	 * Sets nullable property of a Schema instance
	 * to the parameter.
	 *
	 * @param nullable
	 */

	void setNullable(Boolean nullable);

	/**
	 * Sets nullable property of a Schema instance
	 * to the parameter and return the instance.
	 *
	 * @param nullable
	 * @return Schema instance with the set nullable property.
	 */

	Schema nullable(Boolean nullable);

	/**
	 * returns the readOnly property from a Schema instance.
	 *
	 * @return Boolean readOnly
	 **/

	Boolean getReadOnly();

	/**
	 * Sets readOnly property of a Schema instance
	 * to the parameter.
	 *
	 * @param readOnly
	 */

	void setReadOnly(Boolean readOnly);

	/**
	 * Sets readOnly property of a Schema instance
	 * to the parameter and return the instance.
	 *
	 * @param readOnly
	 * @return Schema instance with the set readOnly property.
	 */

	Schema readOnly(Boolean readOnly);

	/**
	 * returns the writeOnly property from a Schema instance.
	 *
	 * @return Boolean writeOnly
	 **/

	Boolean getWriteOnly();

	/**
	 * Sets writeOnly property of a Schema instance
	 * to the parameter.
	 *
	 * @param writeOnly
	 */

	void setWriteOnly(Boolean writeOnly);

	/**
	 * Sets writeOnly property of a Schema instance
	 * to the parameter and return the instance.
	 *
	 * @param writeOnly
	 * @return Schema instance with the set writeOnly property.
	 */

	Schema writeOnly(Boolean writeOnly);

	/**
	 * returns the example property from a Schema instance.
	 *
	 * @return String example
	 **/

	Object getExample();

	/**
	 * Sets example property of a Schema instance
	 * to the parameter.
	 *
	 * @param example
	 */

	void setExample(Object example);

	/**
	 * Sets example property of a Schema instance
	 * to the parameter and return the instance.
	 *
	 * @param example
	 * @return Schema instance with the set example property.
	 */

	Schema example(Object example);

	/**
	 * returns the externalDocs property from a Schema instance.
	 *
	 * @return ExternalDocumentation externalDocs
	 **/

	ExternalDocumentation getExternalDocs();

	/**
	 * Sets externalDocs property of a Schema instance
	 * to the parameter.
	 *
	 * @param externalDocs
	 */

	void setExternalDocs(ExternalDocumentation externalDocs);

	/**
	 * Sets externalDocs property of a Schema instance
	 * to the parameter and
	 * return the instance.
	 *
	 * @param externalDocs
	 * @return Schema instance with the set externalDocs property
	 */

	Schema externalDocs(ExternalDocumentation externalDocs);

	/**
	 * returns the deprecated property from a Schema instance.
	 *
	 * @return Boolean deprecated
	 **/

	Boolean getDeprecated();

	/**
	 * Sets deprecated property of a Schema instance
	 * to the parameter.
	 *
	 * @param deprecated
	 */

	void setDeprecated(Boolean deprecated);

	/**
	 * Sets deprecated property of a Schema instance
	 * to the parameter and
	 * return the instance.
	 *
	 * @param deprecated
	 * @return Schema instance with the set deprecated property
	 */

	Schema deprecated(Boolean deprecated);

	/**
	 * returns the xml property from a Schema instance.
	 *
	 * @return XML xml
	 **/

	XML getXml();

	/**
	 * Sets xml property of a Schema instance
	 * to the parameter.
	 *
	 * @param xml
	 */

	void setXml(XML xml);

	/**
	 * Sets xml property of a Schema instance
	 * to the parameter and
	 * return the instance.
	 *
	 * @param xml
	 * @return Schema instance with the set xml property
	 */

	Schema xml(XML xml);

	/**
	 * Returns extensions property of a Schema instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map at
	 * the specified key.
	 *
	 * @param name - map key
	 * @param value - map value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of a Schema instance
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}