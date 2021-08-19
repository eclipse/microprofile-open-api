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
 * A set of annotations to describe and encapsulate operation parameters and operation's request body.
 * <p>
 * Example usage:
 * 
 * <pre>
 * {@literal @}Produces("application/json")
 *  public Response getReviewById(
 *          {@literal @}Parameter(
 *              name = "id",
 *              description = "ID of the booking",
 *              required = true,
 *              in = ParameterIn.PATH,
 *              content = {@literal @}Content(
 *                  examples = {@literal @}ExampleObject(
 *                      name = "example",
 *                      value = "1")))
 *          {@literal @}PathParam("id") int id)
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.1")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.annotations.parameters;