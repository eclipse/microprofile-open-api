/**
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.annotations.responses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The APIResponseSchema annotation corresponds to an individual schema in the OpenAPI
 * Response model object which describes a single response from an API Operation. This
 * annotation provides a short-hand way to specify a simple response that would otherwise
 * be specified using {@link APIResponse &#64;APIResponse} and that typically could not be determined by
 * scanning the resource method alone.
 *
 * <p>
 * The following annotation usages are equivalent to the OpenAPI annotation scanner runtime.
 *
 * <pre>
 * &#64;APIResponse(content = { &#64;Content(schema = &#64;Schema(implementation = MyResponseObject.class)) })
 *
 * &#64;APIResponseSchema(MyResponseObject.class)
 * </pre>
 *
 * <p>
 * When this annotation is applied to a method the response is added to the responses
 * defined in the corresponding OpenAPI operation with a default response code and description
 * that correspond to the method's HTTP method annotation and return type. Any media types that
 * apply to the resource method from either a method-level or class-level <code>&#64;Produces</code>
 * annotation will result in those media types applying to the OpenAPI response model.
 *
 * <p>
 * If not specified, default responseCode and responseDescription values shall be determined
 * according to the {@link #responseCode() responseCode} and {@link #responseDescription() responseDescription}
 * documentation.
 *
 * <pre>
 * &#64;GET
 * &#64;Path("{id}")
 * &#64;APIResponseSchema(value = MyResponseObject.class, responseDescription = "Success", responseCode = "200")
 * public Response getById(&#64;PathParam("{id}") long id) {
 *     MyResponseObject entity = service.load(id);
 *     return Response.status(200).entity(entity).build();
 * }
 * </pre>
 *
 * @since 2.0
 * @see APIResponse
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#responseObject"
 *
 **/
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface APIResponseSchema {

    /**
     * Provides a Java class as implementation for this schema. The class will
     * undergo introspection to determine any applicable Schema attributes to be
     * applied to the OpenAPI response model.
     *
     * @return a class that implements this schema
     **/
    Class<?> value();

    /**
     * A short description of the response. It is a REQUIRED property in the OpenAPI document.
     *
     * <p>
     * If no value is specified, the default value will set to the description given by the
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1 documentation</a>
     * for the {@link #responseCode() responseCode} in use.
     * 
     * @return description of the response.
     **/
    String responseDescription() default "";

    /**
     * The HTTP response code, or 'default', for the supplied response. May have only one 'default' entry.
     *
     * <p>
     * If no value is specified, a default shall be determined using REST conventions as follows:
     *
     * <ul>
     * <li>If the method's return type is <code>void</code> and the HTTP method is <code>&#64;POST</code>,
     *     the code will be <code>201</code>.
     * <li>Otherwise, if the method's return type is <code>void</code> the method does not list a JAX-RS
     *     <code>AsyncResponse</code> parameter, the code will be <code>204</code>.
     * <li>Otherwise, the code will be <code>200</code>.
     * </ul>
     *
     * @return HTTP response code for this response instance or default
     **/
    String responseCode() default "";
}
