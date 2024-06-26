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

package org.eclipse.microprofile.openapi.annotations.info;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.extensions.Extension;

/**
 * License information for the exposed API.
 *
 * @see <a href="https://spec.openapis.org/oas/v3.1.0.html#license-object">OpenAPI Specification License Object</a>
 **/
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface License {
    /**
     * The license name used for the API.
     *
     * @return the name of the license
     **/
    String name();

    /**
     * The license identifier used for the API.
     *
     * @return the identifier of the license
     * @since 4.0
     **/
    String identifier() default "";

    /**
     * A URL to the license used for the API. MUST be in the format of a URL.
     *
     * @return the URL of the license
     **/
    String url() default "";

    /**
     * List of extensions to be added to the {@link org.eclipse.microprofile.openapi.models.info.License License} model
     * corresponding to the containing annotation.
     *
     * @return array of extensions
     *
     * @since 3.1
     */
    Extension[] extensions() default {};
}
