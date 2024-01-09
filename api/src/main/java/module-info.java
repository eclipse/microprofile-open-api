/*
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
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
 * Eclipse MicroProfile OpenAPI
 * <p>
 * A set of Java interfaces, annotations, and programming models which allow Java developers to natively produce OpenAPI
 * documents from Jakarta RESTful Web Services applications.
 */
module org.eclipse.microprofile.openapi {

    exports org.eclipse.microprofile.openapi;
    exports org.eclipse.microprofile.openapi.annotations;
    exports org.eclipse.microprofile.openapi.annotations.callbacks;
    exports org.eclipse.microprofile.openapi.annotations.enums;
    exports org.eclipse.microprofile.openapi.annotations.extensions;
    exports org.eclipse.microprofile.openapi.annotations.headers;
    exports org.eclipse.microprofile.openapi.annotations.info;
    exports org.eclipse.microprofile.openapi.annotations.links;
    exports org.eclipse.microprofile.openapi.annotations.media;
    exports org.eclipse.microprofile.openapi.annotations.parameters;
    exports org.eclipse.microprofile.openapi.annotations.responses;
    exports org.eclipse.microprofile.openapi.annotations.security;
    exports org.eclipse.microprofile.openapi.annotations.servers;
    exports org.eclipse.microprofile.openapi.annotations.tags;
    exports org.eclipse.microprofile.openapi.models;
    exports org.eclipse.microprofile.openapi.models.callbacks;
    exports org.eclipse.microprofile.openapi.models.examples;
    exports org.eclipse.microprofile.openapi.models.headers;
    exports org.eclipse.microprofile.openapi.models.info;
    exports org.eclipse.microprofile.openapi.models.links;
    exports org.eclipse.microprofile.openapi.models.media;
    exports org.eclipse.microprofile.openapi.models.parameters;
    exports org.eclipse.microprofile.openapi.models.responses;
    exports org.eclipse.microprofile.openapi.models.security;
    exports org.eclipse.microprofile.openapi.models.servers;
    exports org.eclipse.microprofile.openapi.models.tags;
    exports org.eclipse.microprofile.openapi.spi;

    // Required for compilation, not used at runtime
    requires static osgi.annotation;

}
