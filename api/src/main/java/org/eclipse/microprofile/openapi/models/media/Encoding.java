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

package org.eclipse.microprofile.openapi.models.media;

import java.util.Map;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.headers.Header;

/**
 * Encoding
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#encodingObject"
 */
public interface Encoding extends Constructible, Extensible {

	enum StyleEnum {
	    FORM("form"),
	    SPACE_DELIMITED("spaceDelimited"),
	    PIPE_DELIMITED("pipeDelimited"),
	    DEEP_OBJECT("deepObject");
	
	    private String value;
	
	    StyleEnum(String value) {
	        this.value = value;
	    }
	
	    @Override
	    public String toString() {
	        return String.valueOf(value);
	    }
	}

	/**
	 * sets this Encoding's contentType property to the given contentType and
	 * returns this instance of Encoding
	 *
	 * @param String contentType
	 * @return Encoding
	 */
	Encoding contentType(String contentType);

	/**
	 * returns the contentType property from a Encoding instance.
	 *
	 * @return String contentType
	 **/
	String getContentType();

	/**
	 * sets this Encoding's contentType property to the given contentType.
	 *
	 * @param String contentType
	 */
	void setContentType(String contentType);

	/**
	 * sets this Encoding's headers property to the given headers and
	 * returns this instance of Encoding
	 *
	 * @param Map&lt;String, Header&gt; headers
	 * @return Encoding
	 */
	Encoding headers(Map<String, Header> headers);

	/**
	 * returns the headers property from a Encoding instance.
	 *
	 * @return Map&lt;String, Header&gt; headers
	 **/
	Map<String, Header> getHeaders();

	/**
	 * sets this Encoding's headers property to the given headers.
	 *
	 * @param Map&lt;String, Header&gt; headers
	 */
	void setHeaders(Map<String, Header> headers);

	/**
	 * sets this Encoding's style property to the given style and
	 * returns this instance of Encoding
	 *
	 * @param String style
	 * @return Encoding
	 */
	Encoding style(String style);

	/**
	 * returns the style property from a Encoding instance.
	 *
	 * @return String style
	 **/
	String getStyle();

	/**
	 * sets this Encoding's style property to the given style.
	 *
	 * @param String style
	 */
	void setStyle(String style);

	/**
	 * sets this Encoding's explode property to the given explode and
	 * returns this instance of Encoding
	 *
	 * @param Boolean explode
	 * @return Encoding
	 */
	Encoding explode(Boolean explode);

	/**
	 * returns the explode property from a Encoding instance.
	 *
	 * @return Boolean explode
	 **/
	Boolean getExplode();

	/**
	 * sets this Encoding's explode property to the given explode.
	 *
	 * @param Boolean explode
	 */
	void setExplode(Boolean explode);

	/**
	 * sets this Encoding's allowReserved property to the given allowReserved and
	 * returns this instance of Encoding
	 *
	 * @param Boolean allowReserved
	 * @return Encoding
	 */
	Encoding allowReserved(Boolean allowReserved);

	/**
	 * returns the allowReserved property from a Encoding instance.
	 *
	 * @return Boolean allowReserved
	 **/
	Boolean getAllowReserved();

	/**
	 * sets this Encoding's allowReserved property to the given allowReserved.
	 *
	 * @param Boolean allowReserved
	 */
	void setAllowReserved(Boolean allowReserved);

}