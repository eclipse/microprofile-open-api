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
 * A set of interfaces for programmable models and their helper classes,
 * many derived from Swagger Core library. These interfaces can be used to 
 * define OpenAPI document programmatically.
 * <p>
 * The behaviour of methods inherited from java.lang.Object are undefined by the MicroProfile OpenAPI specification.
 * <p>
 * Example:
 * <pre>
 * public class MyOASModelReaderImpl implements OASModelReader {
 *
 *  {@literal @}Override
 *  public OpenAPI buildModel() {
 *      return OASFactory.createObject(OpenAPI.class)
 *              .info(OASFactory.createObject(Info.class)
 *                  .title("AirlinesRatingApp API")
 *                  .version("1.0")
 *                  .termsOfService("http://airlinesratingapp.com/terms")
 *                  .contact(OASFactory.createObject(Contact.class)
 *                      .name("AirlinesRatingApp API Support")
 *                      .url("http://exampleurl.com/contact")
 *                      .email("techsupport{@literal @}airlinesratingapp.com"))
 *                  .license(OASFactory.createObject(License.class)
 *                      .name("Apache 2.0")
 *                      .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
 *  }
 * </pre>
 */

@org.osgi.annotation.versioning.Version("2.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.models;