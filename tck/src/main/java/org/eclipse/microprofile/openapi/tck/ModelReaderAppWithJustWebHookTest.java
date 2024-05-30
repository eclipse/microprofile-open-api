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

package org.eclipse.microprofile.openapi.tck;

import static org.hamcrest.Matchers.equalTo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class ModelReaderAppWithJustWebHookTest extends AppTestBase {
    @Deployment(name = "airlinesModelReader", testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "noPathsAppReader.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.reader")
                .addAsManifestResource("microprofile-reader-just-webhook.properties", "microprofile-config.properties");
    }

    @Test(dataProvider = "formatProvider")
    public void testDocumentCreated(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("webhooks.MarketEvent.get.summary", equalTo("Notifies that a deal has been done"));
    }
}
