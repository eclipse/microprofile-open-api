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
 * A property name and an associated schema.
 * <p>
 * Used with {@link Schema#dependentSchemas()}, if an instance has a property named {@link #name()}, then it must
 * validate against {@link #schema()}.
 *
 * @see Schema#dependentSchemas()
 * @since 4.0
 */
@Target({})
public @interface DependentSchema {

    /**
     * A property name
     *
     * @return property name
     */
    String name();

    /**
     * The schema that an instance must validate against if it has a property named {@link #name()}.
     *
     * @return a class used to generate a schema which is used to validate objects with properties named {@link #name()}
     */
    Class<?> schema();
}
