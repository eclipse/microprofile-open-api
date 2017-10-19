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

package org.eclipse.microprofile.openapi.models;

import java.util.Map;

/**
 * Paths
 * <p>
 * Holds the relative paths to the individual endpoints and their operations.
 * The path is appended to the URL from the Server Object in order to construct
 * the full URL. The Paths MAY be empty, due to ACL constraints.
 * <p>
 * Patterned Fields
 * <table border=1>
 * <tr>
 * <td>Field Pattern</td>
 * <td>Type</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>/{path}</td>
 * <td>{@link org.eclipse.microprofile.openapi.models.PathItem "Path Item
 * Object"}</td>
 * <td>A relative path to an individual endpoint. The field name MUST begin with
 * a slash. The path is appended (no relative URL resolution) to the expanded
 * URL from the Server Object's url field in order to construct the full URL.
 * Path templating is allowed. When matching URLs, concrete (non-templated)
 * paths would be matched before their templated counterparts. Templated paths
 * with the same hierarchy but different templated names MUST NOT exist as they
 * are identical. In case of ambiguous matching, it's up to the tooling to
 * decide which one to use.</td>
 * </tr>
 * </table>
 * 
 * @see <a
 *      href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#pathsObject">OpenAPI
 *      Specification Paths Object</a>
 */
public interface Paths extends Constructible, Extensible, Map<String, PathItem> {

	/**
	  * Adds the given path item to this Paths and return this instance of Paths
	  * 
	  * @param name a path name in the format valid for a Paths object
	  * @param item the path item added to the list of paths
	  * @return the current Paths instance
	  */
	Paths addPathItem(String name, PathItem item);

}