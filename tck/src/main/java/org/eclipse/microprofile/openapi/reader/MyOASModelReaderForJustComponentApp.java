/**
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.openapi.reader;

import java.util.HashMap;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASModelReader;
import org.eclipse.microprofile.openapi.models.Components;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Contact;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.media.Schema;

public class MyOASModelReaderForJustComponentApp implements OASModelReader {

    @Override
    public OpenAPI buildModel() {
        return OASFactory.createObject(OpenAPI.class)
                .info(OASFactory.createObject(Info.class)
                        .title("MarketApp API")
                        .version("1.0")
                        .termsOfService("http://example.com/terms")
                        .contact(OASFactory.createObject(Contact.class)
                                .name("market API Support")
                                .url("http://example.com/contact")
                                .email("admin@example.com")))
                .components(OASFactory.createObject(Components.class)
                        .schemas(new HashMap<String, Schema>())
                        .addSchema("id", OASFactory.createObject(Schema.class)
                                .addType(Schema.SchemaType.INTEGER)
                                .format("int32")));
    }
}
