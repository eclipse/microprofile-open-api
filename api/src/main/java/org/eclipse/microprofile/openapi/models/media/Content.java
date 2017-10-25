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

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;

/**
 * Content
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#contentObject"
 */
public interface Content extends Constructible, Map<String, MediaType> {

	/**
	 * Adds the MediaType for this Content, where name is the name of the MediaType and item is the MediaType itself
	 *
	 * @param String name
	 * @param MediaType item
	 * @return Content
	 */
	Content addMediaType(String name, MediaType item);

}