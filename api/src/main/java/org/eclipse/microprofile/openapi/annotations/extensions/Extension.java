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

package org.eclipse.microprofile.openapi.annotations.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A named extension that should be added to the OpenAPI definition. The names of all extensions MUST begin with
 * {@code x-} or else an invalid document will potentially be created.
 * 
 * <p>
 * Although this annotation may currently be placed directly on a Java language element target, application developers
 * should instead utilize the {@code extensions} property of the particular annotation that corresponds to the model
 * being extended. Use of the annotation directly on a Java element is often ambiguous and it may result in the
 * extension being added to an incorrect location in the OpenAPI model. Future releases of MicroProfile OpenAPI may
 * remove the capability of placing this annotation directly on a Java element.
 * 
 * <p>
 * When {@code @Extension} annotations are used both directly on a Java element as well as within another annotation
 * that targets the same Java element, implementations will apply only the nested extensions to the resulting model.
 * 
 * <p>
 * Example of <em>preferred</em> use with {@code @Extension} nested within an {@code @Schema} annotation:
 * 
 * <pre>
 * class MyPojo {
 *
 *     {@literal @}Schema(
 *         type = SchemaType.STRING,
 *         extensions = {@literal @}Extension(
 *             name = "x-custom-property",
 *             value = "custom-value")
 *     String property1;
 * 
 * }
 * </pre>
 * 
 * <p>
 * Example of <em>deprecated</em> use with {@code @Extension} placed directly on a field implied to be a schema
 * property:
 * 
 * <pre>
 * class MyPojo {
 *
 *     {@literal @}Extension(
 *         name = "x-custom-property",
 *         value = "custom-value")
 *     String property1;
 *
 * }
 * </pre>
 * 
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Extensions.class)
public @interface Extension {

    /**
     * A name for the extension. The names of all extensions MUST begin with {@code x-} or else an invalid document will
     * potentially be created.
     *
     * @return an option name for these extensions - must be prefixed with {@code x-}
     */
    String name();

    /**
     * The extension value. If the value should be parsed into an object or array, then the value should be stringified
     * JSON suitable for parsing by a standard JSON parser.
     * 
     * @return the actual extension value
     */
    String value();

    /**
     * Should the value be parsed into an object/array or other simple type (number, boolean, etc) or left as a simple
     * String. If this is true, then the value must be parseable as one of:
     * <ul>
     * <li>JSON object</li>
     * <li>JSON array</li>
     * <li>number</li>
     * <li>boolean</li>
     * </ul>
     * 
     * @return true if the value should be parsed
     */
    boolean parseValue() default false;
}