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
 * A set of annotations to represent various security components of an OpenAPI app.
 * 
 * <p>
 * Example usage:
 * <pre>
 * {@literal @}Path("/reviews")
 * {@literal @}SecurityScheme(
 *  securitySchemeName = "reviewoauth2",
 *  type = SecuritySchemeType.OAUTH2,
 *  description = "authentication needed to create and delete reviews",
 *  flows = {@literal @}OAuthFlows(
 *      implicit = {@literal @}OAuthFlow(
 *          authorizationUrl = "https://example.com/api/oauth/dialog",
 *          scopes = {@literal @}OAuthScope(
 *              name = "write:reviews",
 *              description = "create a review"
 *          )
 *      )
 *   )
 * )
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.annotations.security;