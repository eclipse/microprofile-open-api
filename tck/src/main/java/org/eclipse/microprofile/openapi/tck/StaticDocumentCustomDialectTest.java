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
import static org.hamcrest.Matchers.startsWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

/**
 * Test that a static document can contain a schema with a custom dialect
 */
public class StaticDocumentCustomDialectTest extends AppTestBase {

    @Deployment(name = "customdialect", testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "customdialect.war")
                .addAsManifestResource("customDialect.yaml", "openapi.yaml");
    }

    @Test(dataProvider = "formatProvider")
    public void testStaticDocumentCustomDialect(String type) {
        ValidatableResponse vr = callEndpoint(type);

        vr.body("openapi", startsWith("3.1."));

        final String schemaPath = "paths.'/test'.get.responses.'200'.content.'application/json'.schema";
        vr.body(schemaPath + ".$schema", equalTo("http://example.com/custom"));
        vr.body(schemaPath + ".foo", equalTo("bar"));
        vr.body(schemaPath + ".baz", equalTo("qux"));
    }

}
