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
 * A set of interfaces for programmable models to represent input and output data types
 * and media types.
 * <p>
 * The behaviour of methods inherited from java.lang.Object are undefined by the MicroProfile OpenAPI specification.
 * <p>
 * Example usage:
 * <pre>
 * .responses(OASFactory.createObject(APIResponses.class)
 *      .addApiResponse("200", OASFactory.createObject(APIResponse.class)
 *          .description("Bookings retrieved")
 *          .content(OASFactory.createObject(Content.class)
 *              .addMediaType("applictaion/json", OASFactory.createObject(MediaType.class)
 *                  .schema(OASFactory.createObject(Schema.class)
 *                      .type(Schema.SchemaType.ARRAY)
 *                      .ref("#/components.schemas.Booking")))))
 * </pre>
 */

@org.osgi.annotation.versioning.Version("2.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.models.media;