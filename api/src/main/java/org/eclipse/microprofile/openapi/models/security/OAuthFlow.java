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

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
 * OAuthFlow
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#oauthFlowsObject"
 */
public interface OAuthFlow extends Constructible, Extensible {

	/**
	 * returns the authorizationUrl property from a OAuthFlow instance.
	 *
	 * @return String authorizationUrl
	 **/

	String getAuthorizationUrl();

	/**
	 * Sets the authorizationUrl property of an OAuthFlow instance
	 * to the parameter.
	 *
	 * @param authorizationUrl
	 */

	void setAuthorizationUrl(String authorizationUrl);

	/**
	 * Sets the authorizationUrl property of an OAuthFlow instance
	 * to the parameter and returns the instance.
	 *
	 * @param authorizationUrl
	 * @return OAuthFlow instance with the set authorizationUrl property
	 */

	OAuthFlow authorizationUrl(String authorizationUrl);

	/**
	 * returns the tokenUrl property from a OAuthFlow instance.
	 *
	 * @return String tokenUrl
	 **/

	String getTokenUrl();

	/**
	 * Sets the tokenUrl property of an OAuthFlow instance.
	 *
	 * @param tokenkUrl
	 */

	void setTokenUrl(String tokenUrl);

	/**
	 * Sets the tokenUrl property of an OAuthFlow instance
	 * to the parameter and returns the instance.
	 *
	 * @param tokenUrl
	 * @return OAuthFlow instance with the set tokenUrl property
	 */

	OAuthFlow tokenUrl(String tokenUrl);

	/**
	 * returns the refreshUrl property from a OAuthFlow instance.
	 *
	 * @return String refreshUrl
	 **/

	String getRefreshUrl();

	/**
	 * Sets the refreshUrl property of an OAuthFlow instance
	 * to the parameter.
	 *
	 * @param refreshUrl
	 */

	void setRefreshUrl(String refreshUrl);

	/**
	 * Sets the refreshUrl property of an OAuthFlow instance
	 * to the parameter and returns the instance.
	 *
	 * @param refreshUrl
	 * @return OAuthFlow instance with the set refreshUrl property
	 */

	OAuthFlow refreshUrl(String refreshUrl);

	/**
	 * returns the scopes property from a OAuthFlow instance.
	 *
	 * @return Scopes scopes
	 **/

	Scopes getScopes();

	/**
	 * Sets the scopes property of an OAuthFlow instance
	 * to the parameter.
	 *
	 * @param scopes
	 */

	void setScopes(Scopes scopes);

	/**
	 * Sets the scopes property of an OAuthFlow instance
	 * to the parameter and returns the instance.
	 *
	 * @param scopes
	 * @return OAuthFlow instance with the set scopes property
	 */

	OAuthFlow scopes(Scopes scopes);

}