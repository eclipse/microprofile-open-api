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
 * An interface of a model to represent a design-time link for a response.
 * <p>
 * The behaviour of methods inherited from java.lang.Object are undefined by the MicroProfile OpenAPI specification.
 * <p>
 * Example usage:
 * 
 * <pre>
 *  .components(OASFactory.createObject(Components.class)
 *      .links(new HashMap&lt;String, Link&gt;())
 *          .addLink("UserName", OASFactory.createObject(Link.class)
 *              .description("The username corresponding to provided user id")
 *              .operationId("getUserByName")
 *              .parameters(new HashMap&lt;String, Object&gt;())
 *                  .addParameter("userId", "$request.link-path.userId")))
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.models.links;