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
 * Annotations to represent a design-time link for a response and the corresponding
 * parameter to be passed to the linked operation.
 * <p>
 * Example usage:
 * <pre>
 *  {@literal @}APIResponse(
 *      responseCode="201",
 *      description="review created",
 *      links = {
 *          {@literal @}Link(
 *              name="Review",
 *              description="get the review that was added",
 *              operationId="getReviewById"
 *      }
 *  )
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.annotations.links;