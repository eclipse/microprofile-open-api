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

/**
 * ArraySchema
 */
public interface ArraySchema extends Schema {

	/**
	   * returns the items property from a ArraySchema instance.
	   *
	   * @return Schema items
	   **/

	Schema getItems();

	/**
	   * sets this ArraySchema's items property to the given items.
	   *
	   * @param SchemaImpl items
	   */
	void setItems(Schema items);

	/**
	   * sets this ArraySchema's items property to the given items and
	   * returns this instance of ArraySchema
	   *
	   * @param SchemaImpl items
	   * @return ArraySchema
	   */
	ArraySchema items(Schema items);

}