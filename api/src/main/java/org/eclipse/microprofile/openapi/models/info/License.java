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

package org.eclipse.microprofile.openapi.models.info;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * This interface represents the License information for the exposed API.
 *
 * @see <a href="https://spec.openapis.org/oas/v3.1.0.html#license-object">OpenAPI Specification License Object</a>
 */
public interface License extends Constructible, Extensible<License> {

    /**
     * Returns the license name for this License instance that is used for the API.
     *
     * @return the license name used for the API
     **/
    String getName();

    /**
     * Sets the license name for this License instance that is used for the API.
     *
     * @param name
     *            the license name used for the API
     */
    void setName(String name);

    /**
     * Sets this License instance's name used for the API and returns this instance of License.
     *
     * @param name
     *            the license name used for the API
     * @return this License instance
     */
    default License name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the license identifier for this License instance that is used for the API.
     *
     * @return the license identifier used for the API
     * @since 4.0
     */
    String getIdentifier();

    /**
     * Sets the license identifier for this License instance that is used for the API.
     *
     * @param identifier
     *            the license identifier used for the API
     * @since 4.0
     */
    void setIdentifier(String identifier);

    /**
     * Sets this License instance's identifier used for the API and returns this instance of License.
     *
     * @param identifier
     *            the license identifier used for the API
     * @return this License instance
     * @since 4.0
     */
    default License identifier(String identifier) {
        setIdentifier(identifier);
        return this;
    }

    /**
     * Returns the URL for this License instance that is used for the API.
     *
     * @return the URL to the license used for the API
     **/

    String getUrl();

    /**
     * Sets this URL for this License instance that is used for the API.
     *
     * @param url
     *            the URL to the license used for the API
     */
    void setUrl(String url);

    /**
     * Sets this License instance's URL used for the API and returns this instance of License.
     *
     * @param url
     *            the URL to the license used for the API
     * @return this License instance
     */
    default License url(String url) {
        setUrl(url);
        return this;
    }

}