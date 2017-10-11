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

/**
 * OAuthFlows
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#oauthFlowsObject"
 */
public interface OAuthFlows extends Constructible {

	/**
	 * returns the implicit property from a OAuthFlows instance.
	 *
	 * @return OAuthFlow implicit
	 **/

	OAuthFlow getImplicit();

	/**
	 * Sets the implicit property of an OAuthFlows instance
	 * to parameter.
	 *
	 * @param implicit
	 */

	void setImplicit(OAuthFlow implicit);

	/**
	 * Sets the implicit property of an OAuthFlows instance
	 * to parameter and returns the instance.
	 *
	 * @param implicit
	 * @return OAuthFlows instance with the set implicit property
	 */

	OAuthFlows implicit(OAuthFlow implicit);

	/**
	 * returns the password property from a OAuthFlows instance.
	 *
	 * @return OAuthFlow password
	 **/

	OAuthFlow getPassword();

	/**
	 * Sets the password property of an OAuthFlows instance
	 * to parameter.
	 *
	 * @param password
	 */

	void setPassword(OAuthFlow password);

	/**
	 * Sets the password property of an OAuthFlows instance
	 * to parameter and returns the instance.
	 *
	 * @param password
	 * @return OAuthFlows instance with the set password property
	 */

	OAuthFlows password(OAuthFlow password);

	/**
	 * returns the clientCredentials property from a OAuthFlows instance.
	 *
	 * @return OAuthFlow clientCredentials
	 **/

	OAuthFlow getClientCredentials();

	/**
	 * Sets the clientCredentials property of an OAuthFlows instance
	 * to parameter.
	 *
	 * @param clientCredentials
	 */

	void setClientCredentials(OAuthFlow clientCredentials);

	/**
	 * Sets the clientCredentials property of an OAuthFlows instance
	 * to parameter and returns the instance.
	 *
	 * @param clientCredentials
	 * @return OAuthFlows instance with the set clientCredentials property
	 */

	OAuthFlows clientCredentials(OAuthFlow clientCredentials);

	/**
	 * returns the authorizationCode property from a OAuthFlows instance.
	 *
	 * @return OAuthFlow authorizationCode
	 **/

	OAuthFlow getAuthorizationCode();

	/**
	 * Sets the authorizationCode property of an OAuthFlows instance
	 * to parameter.
	 *
	 * @param authorizationCode
	 */

	void setAuthorizationCode(OAuthFlow authorizationCode);

	/**
	 * Sets the authorizationCode property of an OAuthFlows instance
	 * to parameter and returns the instance.
	 *
	 * @param authorizationCode
	 * @return OAuthFlows instance with the set authorizationCode property
	 */

	OAuthFlows authorizationCode(OAuthFlow authorizationCode);

	/**
	 * Returns extensions property of an OAuthFlows instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map of an OAuthFlows instance
	 * at the specified key.
	 * If extensions is null, then creates a new HashMap and adds the item.
	 *
	 * @param name
	 * @param value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of an OAuthFlows instance
	 * to the parameter.
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

}