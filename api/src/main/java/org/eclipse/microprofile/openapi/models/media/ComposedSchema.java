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
 * ComposedSchema
 */
public interface ComposedSchema extends Schema {

	/**
	   * returns the allOf property from a ComposedSchema instance.
	   *
	   * @return List&lt;Schema&gt; allOf
	   **/

	List<Schema> getAllOf();

	/**
	   * sets the allOf property of this instance of ComposedSchema
	   * 
	   * @param List&lt;Schema&gt; allOf
	   */
	void setAllOf(List<Schema> allOf);

	/**
	   * sets the allOf property of this instance of ComposedSchema 
	   * and returns this ComposedSchema
	   * 
	   * @param List&lt;Schema&gt; allOf
	   * @return ComposedSchema
	   */
	ComposedSchema allOf(List<Schema> allOf);

	/**
	    * adds the given allOfItem Schema to this ComposedSchema's list of allOfItems
	    * 
	    * @param Schema allOfItem
	    * @return ComposedSchema
	    */
	ComposedSchema addAllOfItem(Schema allOfItem);

	/**
	   * returns the anyOf property from a ComposedSchema instance.
	   *
	   * @return List&lt;Schema&gt; anyOf
	   **/

	List<Schema> getAnyOf();

	/**
	   * sets the anyOf property of this instance of ComposedSchema
	   * 
	   * @param List&lt;Schema&gt; anyOf
	   */
	void setAnyOf(List<Schema> anyOf);

	/**
	   * sets the anyOf property of this instance of ComposedSchema 
	   * and returns this ComposedSchema
	   * 
	   * @param List&lt;Schema&gt; anyOf
	   * @return ComposedSchema
	   */
	ComposedSchema anyOf(List<Schema> anyOf);

	/**
	    * adds the given anyOfItem Schema to this ComposedSchema's list of anyOfItems
	    * 
	    * @param Schema anyOfItem
	    * @return ComposedSchema
	    */
	ComposedSchema addAnyOfItem(Schema anyOfItem);

	/**
	   * returns the oneOf property from a ComposedSchema instance.
	   *
	   * @return List&lt;Schema&gt; oneOf
	   **/

	List<Schema> getOneOf();

	/**
	   * sets the oneOf property of this instance of ComposedSchema
	   * 
	   * @param List&lt;Schema&gt; oneOf
	   */
	void setOneOf(List<Schema> oneOf);

	/**
	   * sets the oneOf property of this instance of ComposedSchema 
	   * and returns this ComposedSchema
	   * 
	   * @param List&lt;Schema&gt; oneOf
	   * @return ComposedSchema
	   */
	ComposedSchema oneOf(List<Schema> oneOf);

	/**
	    * adds the given oneOfItem Schema to this ComposedSchema's list of oneOfItems
	    * 
	    * @param Schema oneOfItem
	    * @return ComposedSchema
	    */
	ComposedSchema addOneOfItem(Schema oneOfItem);

}