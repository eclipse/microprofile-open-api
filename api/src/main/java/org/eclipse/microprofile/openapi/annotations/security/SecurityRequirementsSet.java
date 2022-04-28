/**
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

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;

/**
 * This annotation represents a set of security requirements which permit access to an operation if all of them are
 * satisfied.
 * <p>
 * If this annotation is applied to a method which corresponds to an operation, then the requirements will be added to
 * that operation.
 * <p>
 * If this annotation is applied to a class which contains methods which correspond to operations, then the requirements
 * will be added to all operations corresponding to methods within that class which don't specify any other
 * requirements.
 * <p>
 * Security requirements can be specified for the whole API using {@link OpenAPIDefinition#securitySets()}. Security
 * requirements specified for individual operations override those specified for the whole API.
 * <p>
 * If multiple security requirement sets are specified for an operation, then a user must satisfy all of the
 * requirements within any one of the sets to access the operation.
 * <p>
 * An empty security requirement set indicates that authentication is not required.
 * <p>
 * A {@code SecurityRequirementSet} annotation corresponds to a map of security requirements in an OpenAPI document.
 * 
 * <pre>
 * <b>Example:</b> 
 * security: 
 *  - api_secret: []
 *    oauth_implicit: []
 * </pre>
 * 
 * @see <a href=
 *      "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#security-requirement-object">SecurityRequirement
 *      Object</a>
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SecurityRequirementsSets.class)
@Inherited
public @interface SecurityRequirementsSet {
    /**
     * The security requirements which make up the set
     *
     * @return the array of the SecurityRequirement annotations, may be empty
     **/
    SecurityRequirement[] value() default {};

}