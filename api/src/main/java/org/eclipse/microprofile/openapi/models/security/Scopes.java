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

package org.eclipse.microprofile.openapi.models.security;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * Scopes is a property of OAuth Flow Object.
 * 
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#oauthFlowObject">OAuthFlow Object</a>
 **/

public interface Scopes extends Constructible, Extensible<Scopes> {

    /**
     * Adds name of an existing scope object and item parameters to a Scopes instance as a key-value pair in a map.
     *
     * @param scope the name of a scope
     * @param description description of the scope
     * @return Scopes instance with the added key-value pair
     */
    Scopes addScope(String scope, String description);

    /**
     * Removes the given scope item to this Scopes.
     * 
     * @param scope the name of a scope
     */
    void removeScope(String scope);

    /**
     * Returns a copy map (potentially immutable) of scopes.
     * 
     * @return all items
     */
    Map<String, String> getScopes();

    /**
     * Set the scope items map to this Scopes
     * 
     * @param items key-value pair in a map.
     */
    void setScopes(Map<String, String> items);

    /**
     * Check whether a scope item is present in the map. This is a convenience method for <code>getScopes().containsKey(name)</code>
     * 
     * @param scope the name of a scope.
     * @return a boolean to indicate if the scope item is present or not.
     */
    default boolean hasScope(String scope) {
        Map<String, String> map = getScopes();
        if (map == null) {
            return false;
        }
        return map.containsKey(scope);
    }

    /**
     * Returns a scope description for a given scope name. This is a convenience method for <code>getScopes().get(name)</code>
     * 
     * @param scope the name of a scope.
     * @return the corresponding description or null.
     */
    default String getScope(String scope) {
        Map<String, String> map = getScopes();
        if (map == null) {
            return null;
        }
        return map.get(scope);
    }

}