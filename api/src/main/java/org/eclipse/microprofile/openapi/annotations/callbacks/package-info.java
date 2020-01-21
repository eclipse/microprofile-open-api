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
 * A set of annotations to represent callback URL 
 * or an array of relevant callback URLs that can be invoked for a particular HTTP operation
 * as well as the HTTP operation that will be invoked with the callback.
 * <p>
 * Example annotation:
 * <pre>
 * {@literal @}POST
    {@literal @}Callbacks(
        {{@literal @}Callback(
            name = "testCallback",
            callbackUrlExpression="http://localhost:9080/oas3-airlines/reviews",
            operations = {@literal @}CallbackOperation(
                summary = "Get all reviews",
                method = "get",
                responses = {@literal @}APIResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {@literal @}Content(
                        mediaType = "application/json",
                        schema = {@literal @}Schema(
                            type = SchemaType.ARRAY,
                            implementation = Review.class
                            )
                        )
                    )
                )
            )
        }
    )
 * </pre>
 */

@org.osgi.annotation.versioning.Version("1.0")
@org.osgi.annotation.versioning.ProviderType
package org.eclipse.microprofile.openapi.annotations.callbacks;