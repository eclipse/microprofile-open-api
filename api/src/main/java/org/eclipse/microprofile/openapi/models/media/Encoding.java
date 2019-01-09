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
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#encodingObject">Encoding Object</a>
 */
public interface Encoding extends Constructible, Extensible<Encoding> {

    enum Style {
        FORM("form"), SPACE_DELIMITED("spaceDelimited"), PIPE_DELIMITED("pipeDelimited"), DEEP_OBJECT("deepObject");

        private String value;

        Style(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * The Content-Type for encoding a specific property. Default value depends on the property type: i.e. for binary string - contentType is
     * application/octet-stream, for primitive types - text/plain, for object - application/json.
     * <p>
     * This method sets contentType property for the Encoding instance to the passed parameter and returns the modified instance
     * </p> 
     * @param contentType a string that describes the type of content of the encoding
     * @return Encoding
     */
    default Encoding contentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    /**
     * The Content-Type for encoding a specific property. Default value depends on the property type: i.e. for binary string - contentType is
     * application/octet-stream, for primitive types - text/plain, for object - application/json.
     * <p>
     * This method returns the contentType property from an Encoding instance.
     * </p>
     * @return String contentType
     **/
    String getContentType();

    /**
     * The Content-Type for encoding a specific property. Default value depends on the property type: i.e. for binary string - contentType is
     * application/octet-stream, for primitive types - text/plain, for object - application/json.
     * <p>
     * This method sets thecontentType property of an Encoding instance to the passed contentType parameter.
     * </p>
     * @param contentType a string that describes the type of content of the encoding
     */
    void setContentType(String contentType);

    /**
     * Headers property of an Encoding is a map that allows additional information to be provided as headers
     * <p>
     * This method sets the headers property of Encoding instance to the passed headers argument and returns the modified instance.
     * </p>
     * @param headers a map of name to corresponding header object
     * @return Encoding
     */
    default Encoding headers(Map<String, Header> headers) {
        setHeaders(headers);
        return this;
    }

    /**
     * Headers property of an Encoding is a map that allows additional information to be provided as headers
     * <p>
     * This method returns the headers property from a Encoding instance.
     * </p>
     * @return a copy Map (potentially immutable) containing headers
     **/
    Map<String, Header> getHeaders();

    /**
     * Headers property of an Encoding is a map that allows additional information to be provided as headers
     * <p>
     * This method sets the headers property of Encoding instance to the passed headers argument.
     * </p>
     * @param headers a map of name to corresponding header object
     */
    void setHeaders(Map<String, Header> headers);

    /**
     * Adds the given header to this Encoding' list of headers with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param header the header object. null values will be rejected (implementation will throw an exception) or ignored.
     * @return the current Encoding object
     */
    Encoding addHeader(String key, Header header);

    /**
     * Removes the given header to this Encoding' list of headers with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeHeader(String key);

    /**
     * Style describes how the encoding value will be serialized depending on the type of the parameter value.
     * <p>
     * This method sets the style property of Encoding instance to the passed style argument and returns the modified instance
     * </p>
     * @param style a string that descibes how encoding value will be serialized
     * @return Encoding
     */
    default Encoding style(Style style) {
        setStyle(style);
        return this;
    }

    /**
     * Style describes how the encoding value will be serialized depending on the type of the parameter value.
     * <p>
     * This method returns the style property from a Encoding instance.
     * </p>
     * @return String style
     **/
    Style getStyle();

    /**
     * Style describes how the encoding value will be serialized depending on the type of the parameter value.
     * <p>
     * This method sets the style property of Encoding instance to the given style argument.
     * </p>
     * @param style a string that descibes how encoding value will be serialized
     */
    void setStyle(Style style);

    /**
     * When this is true, property values of type array or object generate separate parameters for each value of the array, or key-value-pair of the
     * map. For other types of properties this property has no effect. When style is form, the default value is true. For all other styles, the
     * default value is false.
     * <p>
     * This method sets the explode property of Encoding instance to the given explode argument and returns the instance.
     * </p>
     * @param explode a boolean that indicates whether the property values of array or object will generate separate parameters
     * @return Encoding
     */
    default Encoding explode(Boolean explode) {
        setExplode(explode);
        return this;
    }

    /**
     * When this is true, property values of type array or object generate separate parameters for each value of the array, or key-value-pair of the
     * map. For other types of properties this property has no effect. When style is form, the default value is true. For all other styles, the
     * default value is false.
     * <p>
     * This method returns the explode property from a Encoding instance.
     * </p>
     * @return Boolean explode
     **/
    Boolean getExplode();

    /**
     * When this is true, property values of type array or object generate separate parameters for each value of the array, or key-value-pair of the
     * map. For other types of properties this property has no effect. When style is form, the default value is true. For all other styles, the
     * default value is false.
     * <p>
     * This method sets the explode property of Encoding instance to the given explode argument.
     * </p> 
     * @param explode a boolean that indicates whether the property values of array or object will generate separate parameters
     */
    void setExplode(Boolean explode);

    /**
     * AllowReserved determines whether the parameter value SHOULD allow reserved characters to be encoded without percent-encoding.
     * <p>
     * This method sets the allowReserved property of Encoding instance to the given allowReserved argument and returns the instance.
     * </p>
     * @param allowReserved a boolean that determines whether the parameter value SHOULD allow reserved characters
     * @return Encoding
     */
    default Encoding allowReserved(Boolean allowReserved) {
        setAllowReserved(allowReserved);
        return this;
    }

    /**
     * AllowReserved determines whether the parameter value SHOULD allow reserved characters to be encoded without percent-encoding.
     * <p>
     * This method returns the allowReserved property from a Encoding instance.
     * </p>
     * @return Boolean allowReserved
     **/
    Boolean getAllowReserved();

    /**
     * AllowReserved determines whether the parameter value SHOULD allow reserved characters to be encoded without percent-encoding.
     * <p>
     * This method sets the allowReserved property to the given allowReserved argument.
     * </p>
     * @param allowReserved a boolean that determines whether the parameter value SHOULD allow reserved characters
     */
    void setAllowReserved(Boolean allowReserved);

}