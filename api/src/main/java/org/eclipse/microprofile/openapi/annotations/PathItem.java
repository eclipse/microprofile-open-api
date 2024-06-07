/*
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 * Describes a set of operations available on a single path.
 *
 * @since 4.0
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface PathItem {

    /**
     * The name of the Path Item object, used as the map key when the path item is used under
     * {@link Components#pathItems()} or {@link OpenAPIDefinition#webhooks()}
     *
     * @return the path item name
     */
    String name();

    /**
     * Reference value to a PathItem object.
     * <p>
     * This property provides a reference to an object defined elsewhere.
     * <p>
     * Unlike {@code ref} on most MP OpenAPI annotations, this property is <em>not</em> mutually exclusive with other
     * properties.
     *
     * @return reference to a path item object definition
     **/
    String ref() default "";

    /**
     * The summary of the path item.
     *
     * @return the summary
     */
    String summary() default "";

    /**
     * The description of the path item.
     *
     * @return the description
     */
    String description() default "";

    /**
     * The operations available under this Path Item.
     * <p>
     * The {@link PathItemOperation#method() method} MUST be defined for each operation.
     *
     * @return the list of operations
     */
    PathItemOperation[] operations() default {};

    /**
     * A list of servers to be used for this Path Item, overriding those defined for the whole API.
     *
     * @return the list of servers
     */
    Server[] servers() default {};

    /**
     * A list of parameters which apply to all operations under this path item.
     *
     * @return the list of parameters
     */
    Parameter[] parameters() default {};

    /**
     * List of extensions to be added to the {@link org.eclipse.microprofile.openapi.models.PathItem PathItem} model
     * corresponding to the containing annotation.
     *
     * @return the list of extensions
     */
    Extension[] extensions() default {};
}
