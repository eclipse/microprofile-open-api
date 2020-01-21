/*
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

/**
 * An interface to represent a programmable model of an example 
 * of a data type or a media type.
 * <p>
 * The behaviour of methods inherited from java.lang.Object are undefined by the MicroProfile OpenAPI specification.
 * <p>
 * Example usage: 
 * <pre>
 * .components(OASFactory.createObject(Components.class)
 *      .examples(new HashMap&lt;String, Example&gt;())
 *          .addExample("review", OASFactory.createObject(Example.class)
 *              .summary("External review example")
 *              .description("This example exemplifies the content on our site.")
 *              .externalValue("http://foo.bar/examples/review-example.json"))
 *          .addExample("user", OASFactory.createObject(Example.class)
 *              .summary("External user example")
 *              .externalValue("http://foo.bar/examples/user-example.json"))
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.models.examples;