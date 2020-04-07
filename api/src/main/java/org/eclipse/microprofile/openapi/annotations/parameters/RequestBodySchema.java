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

package org.eclipse.microprofile.openapi.annotations.parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides a reference to a class that (after introspection) describes the schema
 * for a single request body. This annotation provides a short-hand way to specify a
 * simple request body that would otherwise be specified using {@link RequestBody &#64;RequestBody}
 * and that typically could not be determined by scanning the resource method alone.
 *
 * <p>
 * The following annotation usages are equivalent to the OpenAPI annotation scanner runtime.
 *
 * <pre>
 * &#64;RequestBody(content = { &#64;Content(schema = &#64;Schema(implementation = MyRequestObject.class)) })
 *
 * &#64;RequestBodySchema(MyRequestObject.class)
 * </pre>
 *
 * <p>
 * Any media types that apply to the resource method from either a method-level or class-level
 * <code>&#64;Consumes</code> annotation will result in those media types applying to the OpenAPI
 * request body model.
 *
 * <p>
 * This annotation is useful in cases when a single request body schema applies to all media types (as
 * given in <code>&#64;Consumes</code>), where it is not possible for class introspection to
 * determine the schema directly.
 *
 * <pre>
 * &#64;PUT
 * &#64;Path("{id}")
 * &#64;RequestBodySchema(MyRequestObject.class)
 * public Response updateItem(&#64;PathParam("{id}") long id, InputStream rawData) {
 *     MyRequestObject entity = service.deserialize(rawData);
 *     service.persist(entity);
 *
 *     return Response.status(204).build();
 * }
 * </pre>
 *
 * @see RequestBody
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.0-rc2/versions/3.0.md#requestBodyObject">OpenAPI requestBody Object</a>
 **/
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RequestBodySchema {

    /**
     * Provides a Java class as implementation for this schema. The class will
     * undergo introspection to determine any applicable Schema attributes to be
     * applied to the OpenAPI request body model.
     *
     * @return a class that implements this schema
     **/
    Class<?> value();

}
