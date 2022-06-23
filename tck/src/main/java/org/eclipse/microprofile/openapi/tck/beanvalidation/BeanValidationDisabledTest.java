/**
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.tck.beanvalidation;

import static org.eclipse.microprofile.openapi.tck.Groups.BEAN_VALIDATION;
import static org.eclipse.microprofile.openapi.tck.beanvalidation.BeanValidationTest.assertProperty;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

import org.eclipse.microprofile.openapi.OASConfig;
import org.eclipse.microprofile.openapi.apps.beanvalidation.BeanValidationApp;
import org.eclipse.microprofile.openapi.tck.AppTestBase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class BeanValidationDisabledTest extends AppTestBase {

    @Deployment
    public static WebArchive buildApp() {
        Asset config = new StringAsset(OASConfig.SCAN_BEANVALIDATION + "=false");

        return ShrinkWrap.create(WebArchive.class, "beanValidation.war")
                .addPackage(BeanValidationApp.class.getPackage())
                .addAsManifestResource(config, "microprofile-config.properties");
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    @RunAsClient
    public void beanValidationScanningDisabledTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "notEmptyString", not(hasKey("minLength")));
    }

}
