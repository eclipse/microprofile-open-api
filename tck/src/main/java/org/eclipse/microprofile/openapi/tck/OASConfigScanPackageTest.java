/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
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

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class OASConfigScanPackageTest extends AppTestBase {
    private ValidatableResponse vr;
    
    @Deployment(name = "airlines")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "airlines.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addAsManifestResource("package-microprofile-config.properties", "microprofile-config.properties");
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testScanPackage(String type) throws InterruptedException {
        vr = callEndpoint(type);
        vr.body("openapi", startsWith("3.0."));
        vr.body("paths", aMapWithSize(2));
        vr.body("paths", hasKey("/bookings"));
        vr.body("paths", hasKey("/bookings/{id}"));
        vr.body("paths.'/bookings'", aMapWithSize(2));
        vr.body("paths.'/bookings/{id}'", aMapWithSize(3)); 
    }
}
