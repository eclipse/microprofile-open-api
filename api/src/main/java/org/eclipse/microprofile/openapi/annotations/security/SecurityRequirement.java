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

package org.eclipse.microprofile.openapi.annotations.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a security requirement for an operation.
 * <p>
 * Applying this annotation to a method or class is equivalent to applying a {@link SecurityRequirementsSet} annotation
 * containing only this annotation.
 *
 * @see <a href= "https://spec.openapis.org/oas/v3.1.0.html#security-requirement-object">OpenAPI Specification Security
 *      Requirement Object</a>
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SecurityRequirements.class)
@Inherited
public @interface SecurityRequirement {
    /**
     * Name MUST correspond to a security scheme which is declared in the Security Schemes under the Components Object.
     *
     * @return the name of this Security Requirement instance
     */
    String name();

    /**
     * If the security scheme referred by the name property is of type "oauth2" or "openIdConnect", then the scopes
     * array is a list of scope names required for the execution.
     * <p>
     * For other security scheme types, the array MAY contain a list of required roles.
     * </p>
     *
     * @return a list of scope names required for the execution of this Security Requirement instance.
     */
    String[] scopes() default {};
}
