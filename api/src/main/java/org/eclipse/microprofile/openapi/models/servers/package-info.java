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
 * Model interfaces to represent servers used for a single API operation
 * or for all operations in an OpenAPI document, as well as a way to represent
 * variables for server URL template substitution.
 * <p>
 * The behaviour of methods inherited from java.lang.Object are undefined by the MicroProfile OpenAPI specification.
 * <p>
 * Example usage:
 * <pre>
 * {@literal}Override
 *  public OpenAPI buildModel() {
 *      return OASFactory.createObject(OpenAPI.class)
 *          .servers(new ArrayList&lt;Server&gt;())
 *              .addServer(OASFactory.createObject(Server.class)
 *                  .url("https://{username}.gigantic-server.com:{port}/{basePath}")
 *                  .description("The production API server")
 *                  .variables(OASFactory.createObject(ServerVariables.class)
 *                      .addServerVariable("username", OASFactory.createObject(ServerVariable.class)
 *                          .defaultValue("user1")
 *                          .description("Reviews of the app by users")
 *                          .enumeration(new ArrayList&lt;String&gt;())
 *                              .addEnumeration("user1")
 *                              .addEnumeration("user2")))
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.0")
package org.eclipse.microprofile.openapi.models.servers;