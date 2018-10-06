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

package org.eclipse.microprofile.openapi.models.servers;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * ServerVariables
 *
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#server-variable-object">ServerVariable Object</a>
 */
public interface ServerVariables extends Constructible, Extensible<ServerVariables>, Map<String, ServerVariable> {

    /**
     * This method adds a key-value item to a ServerVariables instance from the name-item parameter pair and returns the modified instance.
     *
     * @param name the name of ServerVariable instance
     * @param serverVariable the ServerVariable instance
     * @return ServerVariables instance with the added name-item pair.
     */
    ServerVariables addServerVariable(String name, ServerVariable serverVariable);

    /**
     * Removes the given server variables.
     * 
     * @param name the name of ServerVariable instance
     */
    void removeServerVariable(String name);

    /**
     * Returns a copy map (potentially immutable) of the server variables.
     * 
     * @return all items
     */
    Map<String, ServerVariable> getServerVariables();

    /**
     * Set the server variables map to this ServerVariables object.
     * 
     * @param items a map containing key-value item.
     */
    void setServerVariables(Map<String, ServerVariable> items);

    /**
     * Check whether a server variable is present in the map. This is a convenience method for <code>getServerVariables().containsKey(name)</code>
     * 
     * @param name the name of ServerVariable instance
     * @return a boolean to indicate if the server variable is present or not.
     */
    default boolean hasServerVariable(String name) {
        Map<String, ServerVariable> map = getServerVariables();
        if (map == null) {
            return false;
        }
        return map.containsKey(name);
    }

    /**
     * Returns a server variable for a given name. This is a convenience method for <code>getServerVariables().get(name)</code>
     * 
     * @param name the name of ServerVariable instance
     * @return the corresponding server variable or null.
     */
    default ServerVariable getServerVariable(String name) {
        Map<String, ServerVariable> map = getServerVariables();
        if (map == null) {
            return null;
        }
        return map.get(name);
    }

    /**
     * In the next version, {@link ServerVariables} will no longer extends {@link Map}, this method will no longer be present.
     * Use {@link #getServerVariable(String)} instead.
     * @deprecated since 1.1
     */
    @Deprecated
    @Override
    ServerVariable get(Object key);

    /**
     * In the next version, {@link ServerVariables} will no longer extends {@link Map}, this method will no longer be present.
     * Use {@link #hasServerVariable(String)} instead.
     * @deprecated since 1.1
     */
    @Deprecated
    @Override
    boolean containsKey(Object key);
    
    /**
     * In the next version, {@link ServerVariables} will no longer extends {@link Map}, this method will no longer be present.
     * Use {@link #addServerVariable(String, ServerVariable)} instead.
     * @deprecated since 1.1
     */
    @Deprecated
    @Override
    ServerVariable put(String key, ServerVariable value);

    /**
     * In the next version, {@link ServerVariables} will no longer extends {@link Map}, this method will no longer be present.
     * Use {@link #setServerVariables(Map)} instead.
     * @deprecated since 1.1
     */
    @Deprecated
    @Override
    void putAll(Map<? extends String, ? extends ServerVariable> m);

    /**
     * In the next version, {@link ServerVariables} will no longer extends {@link Map}, this method will no longer be present.
     * Use {@link #removeServerVariable(String)} instead.
     * @deprecated since 1.1
     */
    @Deprecated
    @Override
    ServerVariable remove(Object key);

}