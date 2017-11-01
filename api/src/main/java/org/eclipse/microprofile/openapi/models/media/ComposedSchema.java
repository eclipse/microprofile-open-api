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

import java.util.List;

/**
 * Create a schema composed of other schemas. An object with this schema can
 * take a value conforming to one (or more) of the schemas used to build this
 * one.
 */
public interface ComposedSchema extends Schema {

	/**
	 * Returns the schemas used by the allOf property in a ComposedSchema instance.
	 *
	 * @return the list of schemas used by the allOf property
	 **/
	List<Schema> getAllOf();

	/**
	 * Sets the schemas used by the allOf property of this schema.
	 * 
	 * @param allOf  the list of schemas used by the allOf property
	 */
	void setAllOf(List<Schema> allOf);

	/**
	 * Sets the schemas used by the allOf property of this schema.
	 * 
	 * @param allOf  the list of schemas used by the allOf property
	 * @return the current ComposedSchema instance
	 */
	ComposedSchema allOf(List<Schema> allOf);

	/**
	 * Adds the given schema to this ComposedSchema's list of schemas used by the allOf property.
	 * 
	 * @param allOfItem  a schema to use with the allOf property
	 * @return the current ComposedSchema instance
	 */
	ComposedSchema addAllOfItem(Schema allOfItem);

	/**
	 * Returns the schemas used by the anyOf property in a ComposedSchema instance.
	 *
	 * @return the list of schemas used by the anyOf property
	 **/
	List<Schema> getAnyOf();

	/**
	 * Sets the schemas used by the anyOf property of this schema.
	 * 
	 * @param anyOf  the list of schemas used by the anyOf property
	 */
	void setAnyOf(List<Schema> anyOf);

	/**
	 * Sets the schemas used by the anyOf property of this schema.
	 * 
	 * @param anyOf  the list of schemas used by the anyOf property
	 * @return the current ComposedSchema instance
	 */
	ComposedSchema anyOf(List<Schema> anyOf);

	/**
	 * Adds the given schema to this ComposedSchema's list of schemas used by the anyOf property.
	 * 
	 * @param anyOfItem  a schema to use with the anyOf property
	 * @return the current ComposedSchema instance
	 */
	ComposedSchema addAnyOfItem(Schema anyOfItem);

	/**
	 * Returns the schemas used by the oneOf property in a ComposedSchema instance.
	 *
	 * @return the list of schemas used by the oneOf property
	 **/
	List<Schema> getOneOf();

	/**
	 * Sets the schemas used by the oneOf property of this schema.
	 * 
	 * @param oneOf  the list of schemas used by the oneOf property
	 */
	void setOneOf(List<Schema> oneOf);

	/**
	 * Sets the schemas used by the oneOf property of this schema.
	 * 
	 * @param oneOf  the list of schemas used by the oneOf property
	 * @return the current ComposedSchema instance
	 */
	ComposedSchema oneOf(List<Schema> oneOf);

	/**
	 * Adds the given schema to this ComposedSchema's list of schemas used by the oneOf property.
	 * 
	 * @param oneOfItem  a schema to use with the oneOf property
	 * @return the current ComposedSchema instance
	 */
	ComposedSchema addOneOfItem(Schema oneOfItem);

}