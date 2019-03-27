/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
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

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

/**
 * Base class for config scan classes.
 */
public abstract class OASConfigScanClassBase extends AppTestBase {
    private ValidatableResponse vr;
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testScanClass(String type) throws InterruptedException {
        vr = callEndpoint(type);
        vr.body("openapi", startsWith("3.0."));
        vr.body("paths", aMapWithSize(5));
        vr.body("paths", hasKey("/reviews"));
        vr.body("paths", hasKey("/reviews/{id}"));
        vr.body("paths", hasKey("/reviews/users/{user}"));
        vr.body("paths", hasKey("/reviews/airlines/{airline}"));
        vr.body("paths", hasKey("/reviews/{user}/{airlines}"));
        
        vr.body("paths.'/reviews'", aMapWithSize(2));
        vr.body("paths.'/reviews/{id}'", aMapWithSize(2)); 
        vr.body("paths.'/reviews/users/{user}'", aMapWithSize(1));
        vr.body("paths.'/reviews/airlines/{airline}'", aMapWithSize(1)); 
        vr.body("paths.'/reviews/{user}/{airlines}'", aMapWithSize(1)); 
        
        
    }
}
