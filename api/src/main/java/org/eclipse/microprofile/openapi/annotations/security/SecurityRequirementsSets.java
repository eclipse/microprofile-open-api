/**
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.annotations.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Represents an array of security requirement sets that apply to an operation. Only one of requirement sets needs be
 * satisfied to access the operation.
 * <p>
 * If this annotation is applied to a method which corresponds to an operation, then the requirements will be added to
 * that operation.
 * <p>
 * If this annotation is applied to a class which contains methods which correspond to operations, then the requirements
 * will be added to all operations corresponding to methods within that class which don't specify any other
 * requirements.
 * <p>
 * This annotation may be used with {@code value} set to an empty array. When applied like this to a method or class, it
 * indicates that no security requirements apply to the corresponding operations. This can be used to override security
 * requirements which are specified for the whole API.
 * <p>
 * A {@code SecurityRequirementSets} annotation corresponds to an array of maps of security requirements in an OpenAPI
 * document.
 *
 * <pre>
 * <b>Example:</b>
 * security:
 *   - oauth_implicit: []
 *     http_basic: []
 *   - api_secret: []
 * </pre>
 *
 * @see <a href= "https://spec.openapis.org/oas/v3.1.0.html#security-requirement-object">OpenAPI Specification Security
 *      Requirement Object</a>
 **/
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Inherited
public @interface SecurityRequirementsSets {

    /**
     * An array of SecurityRequirementSet annotations
     *
     * @return the array of the SecurityRequirementSet annotations
     **/
    SecurityRequirementsSet[] value() default {};
}
