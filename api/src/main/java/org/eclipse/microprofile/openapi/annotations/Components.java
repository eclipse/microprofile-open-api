/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
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

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.callbacks.Callback;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.links.Link;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

/**
 * Describes the Components object that holds various reusable objects for different aspects of the OpenAPI
 * Specification (OAS).
 *
 * @see <a href= "https://spec.openapis.org/oas/v3.1.0.html#components-object"> OpenAPI Specification Components
 *      Object</a>
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Components {

    /**
     * An object to hold reusable Schema Objects.
     *
     * @return the reusable Schema objects.
     */
    Schema[] schemas() default {};

    /**
     * An object to hold reusable Response Objects.
     *
     * @return the reusable ApiResponse objects.
     */
    APIResponse[] responses() default {};

    /**
     * An object to hold reusable Parameter Objects.
     *
     * @return the reusable Parameter objects.
     */
    Parameter[] parameters() default {};

    /**
     * An object to hold reusable Example Objects.
     *
     * @return the reusable Example objects.
     */
    ExampleObject[] examples() default {};

    /**
     * An object to hold reusable Request Body Objects.
     *
     * @return the reusable RequestBody objects.
     */
    RequestBody[] requestBodies() default {};

    /**
     * An object to hold reusable Header Objects.
     *
     * @return the reusable Header objects.
     */
    Header[] headers() default {};

    /**
     * An object to hold reusable Security Scheme Objects.
     *
     * @return the reusable SecurityScheme objects.
     */
    SecurityScheme[] securitySchemes() default {};

    /**
     * An object to hold reusable Link Objects.
     *
     * @return the reusable Link objects.
     */
    Link[] links() default {};

    /**
     * An object to hold reusable Callback Objects.
     *
     * @return the reusable Callback objects.
     */
    Callback[] callbacks() default {};

    /**
     * An object to hold reusable Path Item Objects.
     *
     * @return the reusable PathItem objects.
     *
     * @since 4.0
     */
    PathItem[] pathItems() default {};

    /**
     * List of extensions to be added to the {@link org.eclipse.microprofile.openapi.models.Components Components} model
     * corresponding to the containing annotation.
     *
     * @return array of extensions
     *
     * @since 3.1
     */
    Extension[] extensions() default {};

}
