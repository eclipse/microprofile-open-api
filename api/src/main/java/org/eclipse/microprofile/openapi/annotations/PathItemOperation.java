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

import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirementsSet;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Describes an Operation
 * <p>
 * This annotation is only used for operations defined under a {@link PathItem}. For operations provided by the API
 * itself, it's more common to use the {@link Operation} annotation applied to a Jakarta REST resource method instead.
 *
 * @since 4.0
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface PathItemOperation {

    /**
     * The HTTP method for this operation.
     *
     * @return the HTTP method of this operation
     **/
    String method();

    /**
     * The tags which apply to this operation.
     *
     * @return the list of tags
     */
    Tag[] tags() default {};

    /**
     * Provides a brief description of what this operation does.
     *
     * @return a summary of this operation
     **/
    String summary() default "";

    /**
     * A verbose description of the operation behavior. CommonMark syntax MAY be used for rich text representation.
     *
     * @return a description of this operation
     **/
    String description() default "";

    /**
     * Additional external documentation for this operation.
     *
     * @return external documentation associated with this operation instance
     **/
    ExternalDocumentation externalDocs() default @ExternalDocumentation();

    /**
     * Unique string used to identify the operation. The id MUST be unique among all operations described in the API.
     * <p>
     * Tools and libraries MAY use the operationId to uniquely identify an operation, therefore, it is RECOMMENDED to
     * follow common programming naming conventions.
     * </p>
     *
     * @return the ID of this operation
     **/
    String operationId() default "";

    /**
     * An array of parameters applicable for this operation.
     * <p>
     * The list MUST NOT include duplicated parameters. A unique parameter is defined by a combination of a name and
     * location.
     * </p>
     *
     * @return the list of parameters for this operation
     **/
    Parameter[] parameters() default {};

    /**
     * The request body for this operation.
     *
     * @return the request body of this operation
     **/
    RequestBody requestBody() default @RequestBody();

    /**
     * The list of possible responses that can be returned when executing this operation.
     *
     * @return the list of responses for this operation
     **/
    APIResponse[] responses() default {};

    /**
     * A list of possible out-of-band callbacks related to this operation. Each entry represents a set of requests that
     * may be initiated by the API provided and an expression, evaluated at runtime, that identifies the URL used to
     * make those requests.
     *
     * @return the list of callbacks for this operation
     */
    Callback[] callbacks() default {};

    /**
     * Allows an operation to be marked as deprecated
     * <p>
     * Consumers SHOULD refrain from usage of a deprecated operation.
     * </p>
     *
     * @return whether or not this operation is deprecated
     **/
    boolean deprecated() default false;

    /**
     * A declaration of which security mechanisms can be used for this callback operation. Only one of the security
     * requirement objects need to be satisfied to authorize a request.
     * <p>
     * Adding a {@code SecurityRequirement} to this array is equivalent to adding a {@code SecurityRequirementsSet}
     * containing a single {@code SecurityRequirement} to {@link #securitySets()}.
     * <p>
     * This definition overrides any declared top-level security. To remove a top-level security declaration, an empty
     * array can be used.
     *
     * @return the list of security mechanisms for this operation
     */
    SecurityRequirement[] security() default {};

    /**
     * A declaration of which security mechanisms can be used for this callback operation. All of the security
     * requirements within any one of the sets needs needs to be satisfied to authorize a request.
     * <p>
     * This definition overrides any declared top-level security. To remove a top-level security declaration, an empty
     * array can be used.
     * <p>
     * Including an empty set within this list indicates that the other requirements are optional.
     *
     * @return the list of security mechanisms for this operation
     */
    SecurityRequirementsSet[] securitySets() default {};

    /**
     * A list of servers to be used for this operation, overriding those defined for the parent path item or for the
     * whole API.
     *
     * @return the list of servers
     */
    Server[] servers() default {};

    /**
     * List of extensions to be added to the {@link org.eclipse.microprofile.openapi.models.Operation Operation} model
     * corresponding to the containing annotation.
     *
     * @return array of extensions
     */
    Extension[] extensions() default {};
}
