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
 * Configuration details for a supportde OAuthFlow
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#oauthFlowsObject"
 */
public interface OAuthFlow extends Constructible, Extensible {

	/**
	 * The authorization URL to be used for this flow. This MUST be in the form of a URL.
	 * This is a REQUIRED property.
	 * <p>
	 * This method returns the authorizationUrl property from OAuthFlow instance.
	 * <p>
	 * @return String authorizationUrl
	 **/

	String getAuthorizationUrl();

	/**
	 * The authorization URL to be used for this flow. This MUST be in the form of a URL.
	 * This is a REQUIRED property.
	 * <p>
	 * Applies to oauth2 ("implicit", "authorizationCode").
	 * <p>
	 * This method sets the authorizationUrl property of an OAuthFlow instance
	 * to the given authorizationUrl argument.
	 * <p>
	 * @param authorizationUrl
	 */

	void setAuthorizationUrl(String authorizationUrl);

	/**
	 * The authorization URL to be used for this flow. This MUST be in the form of a URL.
	 * This is a REQUIRED property.
	 * <p>
	 * Applies to oauth2 ("implicit", "authorizationCode").
	 * <p>
	 * This method sets the authorizationUrl property of OAuthFlow instance
	 * to the given authorizationUrl argument and returns the modified instance.
	 * <p>
	 * @param authorizationUrl
	 * @return OAuthFlow instance with the set authorizationUrl property
	 */

	OAuthFlow authorizationUrl(String authorizationUrl);

	/**
	 * The token URL to be used for this flow. This MUST be in the form of a URL.
	 * This is a REQUIRED property.
	 * <p>
	 * This method returns the tokenUrl property from OAuthFlow instance.
	 * <p>
	 * @return String tokenUrl
	 **/

	String getTokenUrl();

	/**
	 * The token URL to be used for this flow. This MUST be in the form of a URL.
	 * This is a REQUIRED property.
	 * <p>
	 * Applies to oauth2 ("password", "clientCredentials", "authorizationCode").
	 * <p>
	 * This method sets the tokenUrl property of OAuthFlow instance 
	 * to the given tokenUrl argument.
	 * <p>
	 * @param tokenkUrl
	 */

	void setTokenUrl(String tokenUrl);

	/**
	 * The token URL to be used for this flow. This MUST be in the form of a URL.
	 * This is a REQUIRED property.
	 * <p>
	 * Applies to oauth2 ("password", "clientCredentials", "authorizationCode").
	 * <p>
	 * This method sets the tokenUrl property of OAuthFlow instance 
	 * to the given tokenUrl argument and returns the instance.
	 * <p>
	 * @param tokenUrl
	 * @return OAuthFlow instance with the set tokenUrl property
	 */

	OAuthFlow tokenUrl(String tokenUrl);

	/**
	 * The URL to be used for obtaining refresh tokens. 
	 * This MUST be in the form of a URL.
	 * <p>
	 * This method returns the refreshUrl property from OAuthFlow instance.
	 * <p>
	 * @return String refreshUrl
	 **/

	String getRefreshUrl();

	/**
	 * The URL to be used for obtaining refresh tokens. 
	 * This MUST be in the form of a URL.
	 * <p>
	 * Applies to oauth2.
	 * <p>
	 * This method sets the refreshUrl property of OAuthFlow instance
	 * to the given refreshUrl argument.
	 * <p>
	 * @param refreshUrl
	 */

	void setRefreshUrl(String refreshUrl);

	/**
	 * The URL to be used for obtaining refresh tokens. 
	 * This MUST be in the form of a URL.
	 * <p>
	 * Applies to oauth2.
	 * <p>
	 * This method sets the refreshUrl property of OAuthFlow instance
	 * to the given refreshUrl argument and returns the modified instance.
	 * <p>
	 * @param refreshUrl
	 * @return OAuthFlow instance with the set refreshUrl property
	 */

	OAuthFlow refreshUrl(String refreshUrl);

	/**
	 * The available scopes for the OAuth2 security scheme. 
	 * A map between the scope name and a short description for it.
	 * This is a REQUIRED property.
	 * <p>
	 * This method returns the scopes property from OAuthFlow instance.
	 * <p>
	 * @return Scopes scopes
	 **/

	Scopes getScopes();

	/**
	 * The available scopes for the OAuth2 security scheme. 
	 * A map between the scope name and a short description for it.
	 * This is a REQUIRED property.
	 * <p>
	 * Applies to oauth2.
	 * <p>
	 * This method sets the scopes property of OAuthFlow instance
	 * to the given argument.
	 * <p>
	 * @param scopes
	 */

	void setScopes(Scopes scopes);

	/**
	 * The available scopes for the OAuth2 security scheme. 
	 * A map between the scope name and a short description for it.
	 * This is a REQUIRED property.
	 * <p>
	 * Applies to oauth2.
	 * <p>
	 * This method sets the scopes property of OAuthFlow instance
	 * to the given argument and returns the modified instance.
	 * <p>
	 * @param scopes
	 * @return OAuthFlow instance with the set scopes property
	 */

	OAuthFlow scopes(Scopes scopes);

}