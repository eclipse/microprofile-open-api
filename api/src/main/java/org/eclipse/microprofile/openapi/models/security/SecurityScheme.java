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
 * SecurityScheme
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#securitySchemeObject"
 */
public interface SecurityScheme extends Constructible {

	/**
	 * Gets or Sets type
	 */
	public enum Type {
	    APIKEY("apiKey"),
	    HTTP("http"),
	    OAUTH2("oauth2"),
	    OPENIDCONNECT("openIdConnect");
	
	    private final String value;
	
	    Type(String value) {
	        this.value = value;
	    }
	
	    @Override
	    public String toString() {
	        return String.valueOf(value);
	    }
	}

	/**
	 * Gets or Sets in
	 */
	public enum In {
	    COOKIE("cookie"),
	    HEADER("header"),
	    QUERY("query");
	
	    private final String value;
	
	    In(String value) {
	        this.value = value;
	    }
	
	    @Override
	    public String toString() {
	        return String.valueOf(value);
	    }
	}

	/**
	 * returns the type property from a SecurityScheme instance.
	 *
	 * @return Type type
	 **/

	SecurityScheme.Type getType();

	/**
	 * Sets the type property of a SecurityScheme instance
	 * to the parameter
	 *
	 * @param type
	 */

	void setType(SecurityScheme.Type type);

	/**
	 * Sets the type property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param type
	 * @return SecurityScheme instance with the set type property
	 */

	SecurityScheme type(SecurityScheme.Type type);

	/**
	 * returns the description property from a SecurityScheme instance.
	 *
	 * @return String description
	 **/

	String getDescription();

	/**
	 * Sets the description property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * Sets the description property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param description
	 * @return SecurityScheme instance with the set description property
	 */

	SecurityScheme description(String description);

	/**
	 * returns the name property from a SecurityScheme instance.
	 *
	 * @return String name
	 **/

	String getName();

	/**
	 * Sets the name property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param name
	 */

	void setName(String name);

	/**
	 * Sets the name property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param name
	 * @return SecurityScheme instance with the set name property
	 */

	SecurityScheme name(String name);

	/**
	 * returns the in property from a SecurityScheme instance.
	 *
	 * @return In in
	 **/

	SecurityScheme.In getIn();

	/**
	 * Sets the in property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param in
	 */

	void setIn(SecurityScheme.In in);

	/**
	 * Sets the in property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param in
	 * @return SecurityScheme instance with the set in property
	 */

	SecurityScheme in(SecurityScheme.In in);

	/**
	 * returns the scheme property from a SecurityScheme instance.
	 *
	 * @return String scheme
	 **/

	String getScheme();

	/**
	 * Sets the scheme property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param scheme
	 */

	void setScheme(String scheme);

	/**
	 * Sets the scheme property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param scheme
	 * @return SecurityScheme instance with the set scheme property
	 */

	SecurityScheme scheme(String scheme);

	/**
	 * returns the bearerFormat property from a SecurityScheme instance.
	 *
	 * @return String bearerFormat
	 **/

	String getBearerFormat();

	/**
	 * Sets the bearerFormat property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param bearerFormat
	 */

	void setBearerFormat(String bearerFormat);

	/**
	 * Sets the bearerFormat property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param bearerFormat
	 * @return SecurityScheme instance with the set bearerFormat property
	 */

	SecurityScheme bearerFormat(String bearerFormat);

	/**
	 * returns the flows property from a SecurityScheme instance.
	 *
	 * @return OAuthFlows flows
	 **/

	OAuthFlows getFlows();

	/**
	 * Sets the flows property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param flows
	 */

	void setFlows(OAuthFlows flows);

	/**
	 * Sets the flows property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param flows
	 * @return SecurityScheme instance with the set flows property
	 */

	SecurityScheme flows(OAuthFlows flows);

	/**
	 * returns the openIdConnectUrl property from a SecurityScheme instance.
	 *
	 * @return String openIdConnectUrl
	 **/

	String getOpenIdConnectUrl();

	/**
	 * Sets the openIdConnectUrl property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param openIdConnectUrl
	 */

	void setOpenIdConnectUrl(String openIdConnectUrl);

	/**
	 * Sets the openIdConnectUrl property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param openIdConnectUrl
	 * @return SecurityScheme instance with the set openIdConnectUrl property
	 */

	SecurityScheme openIdConnectUrl(String openIdConnectUrl);

	/**
	 * Returns extensions property of a SecurityScheme instance.
	 *
	 * @return Map&lt;String, Object&gt; extensions
	 */

	Map<String, Object> getExtensions();

	/**
	 * Adds an object item to extensions map of a SecurityScheme instance
	 * at the specified key.
	 * If extensions is null, then creates a new HashMap and adds the item.
	 *
	 * @param name
	 * @param value
	 */

	void addExtension(String name, Object value);

	/**
	 * Sets extensions property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param extensions
	 */

	void setExtensions(Map<String, Object> extensions);

	/**
	 * returns the $ref property from an SecurityScheme instance.
	 *
	 * @return String $ref
	 **/
	String get$ref();

	/**
	 * Sets the $ref property of a SecurityScheme instance
	 * to the parameter.
	 *
	 * @param $ref
	 */

	void set$ref(String $ref);

	/**
	 * Sets the $ref property of a SecurityScheme instance
	 * to the parameter and returns the instance.
	 *
	 * @param $ref
	 * @return SecurityScheme instance with the set $ref property
	 */

	SecurityScheme $ref(String $ref);

}