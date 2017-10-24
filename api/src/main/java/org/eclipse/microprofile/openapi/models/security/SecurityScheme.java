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
 * SecurityScheme
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#securitySchemeObject"
 */
public interface SecurityScheme extends Constructible, Extensible {

	/**
	 * Type is a REQUIRED property that specifies the type of SecurityScheme instance.
	 * <p>
	 * This method is used in getType and setType to get or set the type of SecurityScheme object 
	 * to one of the valid values. 
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
	 * In is a REQUIRED property that specifies the location of the API key.
	 * <p>
	 * This method is used in getIn and setIn to get or set the in of SecurityScheme object 
	 * to one of the enum constants listed.
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
	 * Type is a REQUIRED property that specifies the type of SecurityScheme instance.
	 * <p>
	 * This method returns the type property from SecurityScheme instance.
	 * <p>
	 * @return Type type
	 **/

	SecurityScheme.Type getType();

	/**
	 * Type is a REQUIRED property that specifies the type of SecurityScheme instance.
	 * <p>
	 * This method sets the type property of SecurityScheme instance
	 * to the given Type argument.
	 * <p>
	 * @param type
	 */

	void setType(SecurityScheme.Type type);

	/**
	 * Type is a REQUIRED property that specifies the type of SecurityScheme instance.
	 * <p>
	 * This method sets the type property of SecurityScheme instance
	 * to the given Type argument and returns the modified instance.
	 * <p>
	 * @param type
	 * @return SecurityScheme instance with the set type property
	 */

	SecurityScheme type(SecurityScheme.Type type);

	/**
	 * A short description for security schema.
	 * <p>
	 * This method returns the description property from SecurityScheme instance.
	 * <p>
	 * @return String description
	 **/

	String getDescription();

	/**
	 * A short description for security schema.
	 * <p>
	 * This method sets the description property of SecurityScheme instance.
	 * <p>
	 * @param description
	 */

	void setDescription(String description);

	/**
	 * A short description for security schema.
	 * <p>
	 * This method sets the description property of SecurityScheme instance
	 * and returns the modified instance.
	 * <p>
	 * @param description
	 * @return SecurityScheme instance with the set description property
	 */

	SecurityScheme description(String description);

	/**
	 * Name is a REQUIRED property - this is the name of the header, query or cookie parameter to be used.
	 * <p>
	 * This method returns the name property from SecurityScheme instance.
	 * <p>
	 * @return String name
	 **/

	String getName();

	/**
	 * Name is a REQUIRED property - this is the name of the header, query or cookie parameter to be used.
	 * <p>
	 * This method sets the name property of SecurityScheme instance
	 * to the parameter.
	 * <p>
	 * @param name
	 */

	void setName(String name);

	/**
	 * Name is a REQUIRED property - this is the name of the header, query or cookie parameter to be used.
	 * <p>
	 * This method sets the name property of SecurityScheme instance
	 * to the given String argument and returns the modified instance.
	 * <p>
	 * @param name
	 * @return SecurityScheme instance with the set name property
	 */

	SecurityScheme name(String name);

	/**
	 * In is a REQUIRED property that indicates the location of the API key. 
	 * Valid values are "query", "header", "cookie".
	 * <p> 
	 * This method returns the in property from SecurityScheme instance.
	 * <p>
	 * @return In in
	 **/

	SecurityScheme.In getIn();

	/**
	 * In is a REQUIRED property that indicates the location of the API key. 
	 * Valid values are "query", "header", "cookie".
	 * <p>
	 * The method sets the in property of SecurityScheme instance
	 * to the given In argument.
	 * <p>
	 * @param in
	 */

	void setIn(SecurityScheme.In in);

	/**
	 * In is a REQUIRED property that indicates the location of the API key. 
	 * Valid values are "query", "header", "cookie".
	 * <p>
	 * This method sets the in property of SecurityScheme instance
	 * to the given In argument and returns the modified instance.
	 * <p>
	 * @param in
	 * @return SecurityScheme instance with the set in property
	 */

	SecurityScheme in(SecurityScheme.In in);

	/**
	 * Schema is a REQUIRED property that is the name of the HTTP Authorization scheme
	 * to be used in the Authorization header as defined in RFC7235.
	 * <p>
	 * This method returns the scheme property from SecurityScheme instance.
	 * <p>
	 * @return String scheme
	 **/

	String getScheme();

	/**
	 * Schema is a REQUIRED property that is the name of the HTTP Authorization scheme
	 * to be used in the Authorization header as defined in RFC7235.
	 * <p>
	 * This method sets the scheme property of SecurityScheme instance
	 * to the given String argument.
	 * <p>
	 * @param scheme
	 */

	void setScheme(String scheme);

	/**
	 * Schema is a REQUIRED property that is the name of the HTTP Authorization scheme
	 * to be used in the Authorization header as defined in RFC7235.
	 * <p>
	 * This method sets the scheme property of SecurityScheme instance
	 * to the given String argument and returns the modified instance.
	 * <p>
	 * @param scheme
	 * @return SecurityScheme instance with the set scheme property
	 */

	SecurityScheme scheme(String scheme);

	/**
	 * bearerFormat is intended as a hint to the client to identify how the bearer token is formatted. 
	 * Bearer tokens are usually generated by an authorization server, so this information is primarily for documentation purposes.
	 * <p>
	 * This method returns the bearerFormat property from SecurityScheme instance.
	 * <p>
	 * @return String bearerFormat
	 **/

	String getBearerFormat();

	/**
	 * bearerFormat is intended as a hint to the client to identify how the bearer token is formatted. 
	 * Bearer tokens are usually generated by an authorization server, so this information is primarily for documentation purposes.
	 * <p>
	 * This method sets the bearerFormat property of SecurityScheme instance
	 * to the given String argument.
	 * <p>
	 * @param bearerFormat
	 */

	void setBearerFormat(String bearerFormat);

	/**
	 * bearerFormat is intended as a hint to the client to identify how the bearer token is formatted. 
	 * Bearer tokens are usually generated by an authorization server, so this information is primarily for documentation purposes.
	 * <p>
	 * This method sets the bearerFormat property of SecurityScheme instance to the given String argument
	 * and returns the modified instance.
	 * <p>
	 * @param bearerFormat
	 * @return SecurityScheme instance with the set bearerFormat property
	 */

	SecurityScheme bearerFormat(String bearerFormat);

	/**
	 * Flows is a REQUIRED property.
	 * <p>
	 * Flows is an object containing configuration information for the flow types supported.
	 * <p>
	 * This method returns the flows property from SecurityScheme instance.
	 *
	 * @return OAuthFlows flows
	 **/

	OAuthFlows getFlows();

	/**
	 * Flows is a REQUIRED property.
	 * <p>
	 * Flows is an object containing configuration information for the flow types supported.
	 * <p>
	 * This method sets the flows property of SecurityScheme instance
	 * to the given OAuthFlows argument.
	 * @param flows
	 */

	void setFlows(OAuthFlows flows);

	/**
	 * Flows is a REQUIRED property.
	 * <p>
	 * Flows is an object containing configuration information for the flow types supported.
	 * <p>
	 * This method sets the flows property of SecurityScheme instance
	 * to the given OAuthFlows argument and returns the modified instance.
	 * <p>
	 * @param flows
	 * @return SecurityScheme instance with the set flows property
	 */

	SecurityScheme flows(OAuthFlows flows);

	/**
	 * openIdConnectUrl is a REQUIRED property.
	 * <p>
	 * This property allows to discover OAuth2 configuration values. 
	 * openIdConnectUrl MUST be in a form of a URL.
	 * <p>
	 * This method returns the openIdConnectUrl property from SecurityScheme instance.
	 *
	 * @return String openIdConnectUrl
	 **/

	String getOpenIdConnectUrl();

	/**
	 * openIdConnectUrl is a REQUIRED property.
	 * <p>
	 * This property allows to discover OAuth2 configuration values. 
	 * openIdConnectUrl MUST be in a form of a URL.
	 * <p>
	 * This method sets the openIdConnectUrl property of a SecurityScheme instance
	 * to the given String argument.
	 *
	 * @param openIdConnectUrl
	 */

	void setOpenIdConnectUrl(String openIdConnectUrl);

	/**
	 * penIdConnectUrl is a REQUIRED property.
	 * <p>
	 * This property allows to discover OAuth2 configuration values. 
	 * openIdConnectUrl MUST be in a form of a URL.
	 * <p>
	 * This method sets the openIdConnectUrl property of SecurityScheme instance
	 * to the given String argument and returns the modified instance.
	 * <p>
	 * @param openIdConnectUrl
	 * @return SecurityScheme instance with the set openIdConnectUrl property
	 */

	SecurityScheme openIdConnectUrl(String openIdConnectUrl);

	/**
	 * ref property is the reference of the model's location.
	 * <p>
	 * This method returns the ref property from SecurityScheme instance.
	 * <p>
	 * @return String ref
	 **/
	String getRef();

	/**
	 * ref property is the reference of the model's location.
	 * <p>
	 * This method sets the ref property of SecurityScheme instance
	 * to the given String argument.
	 *
	 * @param ref
	 */

	void setRef(String ref);

	/**
	 * ref property is the reference of the model's location.
	 * <p>
	 * This method sets the ref property of SecurityScheme instance
	 * to the given String argument and returns the modified instance.
	 *
	 * @param ref
	 * @return SecurityScheme instance with the set ref property
	 */

	SecurityScheme ref(String ref);

}