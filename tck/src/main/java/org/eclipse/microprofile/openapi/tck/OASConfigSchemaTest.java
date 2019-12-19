/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.eclipse.microprofile.openapi.tck;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import java.util.Map;

import org.hamcrest.Matcher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class OASConfigSchemaTest extends AppTestBase {

    @Deployment(name = "petstore")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "petstore.war")
                         .addPackages(true, "org.eclipse.microprofile.openapi.apps.petstore")
                         .addAsWebInfResource("schema-microprofile-config.properties",
                                              "classes/META-INF/microprofile-config.properties");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testSchemaConfigApplied(String type) {
        ValidatableResponse vr = callEndpoint(type);

        vr.body("components.schemas.EpochSeconds", epochSecondsSchema());
        vr.body("components.schemas.Lizard.properties.birthInstant",
                anyOf(epochSecondsSchema(), epochSecondsRef()));
    }

    private Matcher<Map<? extends String, ? extends String>> epochSecondsSchema() {
        return allOf(aMapWithSize(4),
                     hasEntry("title", "Epoch Seconds"),
                     hasEntry("type", "number"),
                     hasEntry("format", "int64"),
                     hasEntry("description", "Number of seconds from the epoch of 1970-01-01T00:00:00Z"));
    }

    private Matcher<Map<? extends String, ? extends String>> epochSecondsRef() {
        return allOf(aMapWithSize(1), hasEntry("$ref", "#/components/schemas/EpochSeconds"));
    }
}
