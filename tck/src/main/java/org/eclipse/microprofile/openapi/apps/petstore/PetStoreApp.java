/**
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

package org.eclipse.microprofile.openapi.apps.petstore;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.apps.petstore.model.Lizard;
import org.eclipse.microprofile.openapi.apps.petstore.resource.PetResource;
import org.eclipse.microprofile.openapi.apps.petstore.resource.PetStoreResource;
import org.eclipse.microprofile.openapi.apps.petstore.resource.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Pet Store App",
        version = "2.0",
        description = "Pet Store App API",
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
        contact = @Contact(
            name = "PetStore API Support",
            url = "https://github.com/eclipse/microprofile-open-api",
            email = "support@petstore.com")
    ),
    externalDocs = @ExternalDocumentation(
        url = "http://swagger.io", 
        description="Find out more about our store"),
    tags={
        @Tag(name="pet", description="Everything about your Pets"),
        @Tag(name="store", description="Access to PetStore orders"),
        @Tag(name="user", description="Operations about user",
            externalDocs = @ExternalDocumentation(
                url = "http://swagger.io", 
                description="Find out more about our store"))
    },
    components = @Components(
        schemas = { 
            @Schema(name = "Lizard", implementation = Lizard.class)
        })
)
@Schema(
    externalDocs = @ExternalDocumentation(
        url = "http://swagger.io", 
        description="Find out more about our store")
)
public class PetStoreApp extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();
        singletons.add(new PetResource());
        singletons.add(new PetStoreResource());
        singletons.add(new UserResource());
        return singletons;
    }

}
