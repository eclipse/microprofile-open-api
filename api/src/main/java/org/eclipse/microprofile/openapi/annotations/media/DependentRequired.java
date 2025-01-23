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
package org.eclipse.microprofile.openapi.annotations.media;

import java.lang.annotation.Target;

/**
 * A property name and an associated list of other property names.
 * <p>
 * Used with {@link Schema#dependentRequired()}, if an object has a property named {@link #name()}, it must also have
 * properties with the names in {@link #requires()}.
 *
 * @see Schema#dependentRequired()
 * @since 4.0
 */
@Target({})
public @interface DependentRequired {

    /**
     * The property name to look for
     *
     * @return a property name
     */
    String name();

    /**
     * The property names that an object is required to have, if it has a property named {@link #name()}
     *
     * @return the required property names
     */
    String[] requires();
}
