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
package org.eclipse.microprofile.openapi.tck;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasKey;

import org.eclipse.microprofile.openapi.OASConfig;
import org.eclipse.microprofile.openapi.apps.scanconfig.ScanConfigApplication;
import org.eclipse.microprofile.openapi.apps.scanconfig.a.AResource;
import org.eclipse.microprofile.openapi.apps.scanconfig.a.b.BResource;
import org.eclipse.microprofile.openapi.apps.scanconfig.a.b.c.CResource;
import org.eclipse.microprofile.openapi.apps.scanconfig.x.y.YResource;
import org.eclipse.microprofile.openapi.tck.utils.ConfigAsset;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class OASScanConfigTests {

    public static class ScanConfigIncludeOnly extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_CLASSES, BResource.class.getName());
            return scanConfigApp("scanConfigIncludeOnly.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testIncludeOnly(String type) {
            assertPaths(callEndpoint(type), "/b");
        }
    }

    public static class ScanConfigIncludeMultiple extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_CLASSES, BResource.class.getName(), YResource.class.getName());
            return scanConfigApp("scanConfigIncludeMultiple.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testIncludeMultiple(String type) {
            assertPaths(callEndpoint(type), "/b", "/y");
        }
    }

    public static class ScanConfigPackageIncludeOnly extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_PACKAGES, BResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageIncludeOnly.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageIncludeOnly(String type) {
            assertPaths(callEndpoint(type), "/b", "/c");
        }
    }

    public static class ScanConfigPackageIncludeMultiple extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_PACKAGES, BResource.class.getPackage().getName(),
                            YResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageIncludeMultiple.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageIncludeMultiple(String type) {
            assertPaths(callEndpoint(type), "/b", "/c", "/y");
        }
    }

    public static class ScanConfigExcludeOnly extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_EXCLUDE_CLASSES, BResource.class.getName());
            return scanConfigApp("scanConfigExcludeOnly.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testExcludeOnly(String type) {
            assertPaths(callEndpoint(type), "/a", "/c", "/x", "/y");
        }
    }

    public static class ScanConfigExcludeMultiple extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_EXCLUDE_CLASSES, BResource.class.getName(), YResource.class.getName());
            return scanConfigApp("scanConfigExcludeMultiple.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testExcludeMultiple(String type) {
            assertPaths(callEndpoint(type), "/a", "/c", "/x");
        }
    }

    public static class ScanConfigPackageExcludeOnly extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_EXCLUDE_PACKAGES, BResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageExcludeOnly.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageExcludeOnly(String type) {
            assertPaths(callEndpoint(type), "/a", "/x", "/y");
        }
    }

    public static class ScanConfigPackageExcludeMultiple extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_EXCLUDE_PACKAGES, BResource.class.getPackage().getName(),
                            YResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageExcludeMultiple.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageExcludeMultiple(String type) {
            assertPaths(callEndpoint(type), "/a", "/x");
        }
    }

    public static class ScanConfigPackageIncludesAroundExcludes extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_PACKAGES, AResource.class.getPackage().getName(),
                            CResource.class.getPackage().getName())
                    .put(OASConfig.SCAN_EXCLUDE_PACKAGES, BResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageIncludesAroundExcludes.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageIncludesAroundExcludes(String type) {
            assertPaths(callEndpoint(type), "/a", "/c");
        }
    }

    public static class ScanConfigPackageExcludesAroundInclude extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_PACKAGES, BResource.class.getPackage().getName())
                    .put(OASConfig.SCAN_EXCLUDE_PACKAGES, AResource.class.getPackage().getName(),
                            CResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageExcludesAroundInclude.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageExcludesAroundInclude(String type) {
            assertPaths(callEndpoint(type), "/b");
        }
    }

    public static class ScanConfigPackageExcludeClassInclude extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_CLASSES, AResource.class.getName())
                    .put(OASConfig.SCAN_EXCLUDE_PACKAGES, AResource.class.getPackage().getName());
            return scanConfigApp("scanConfigPackageExcludeClassInclude.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageExcludeClassInclude(String type) {
            assertPaths(callEndpoint(type), "/a");
        }
    }

    public static class ScanConfigPackageIncludeClassExclude extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_PACKAGES, AResource.class.getPackage().getName())
                    .put(OASConfig.SCAN_EXCLUDE_CLASSES, AResource.class.getName());
            return scanConfigApp("scanConfigPackageIncludeClassExclude.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPackageIncludeClassExclude(String type) {
            assertPaths(callEndpoint(type), "/b", "/c");
        }
    }

    public static class ScanConfigPartialPackage extends AppTestBase {

        @Deployment(testable = false)
        public static WebArchive deployment() {
            ConfigAsset config = new ConfigAsset()
                    .put(OASConfig.SCAN_PACKAGES, "a.b.c");
            // Note: should not match org.eclipse.microprofile.openapi.apps.scanconfig.a.b.c
            return scanConfigApp("scanConfigPartialPackage.war", config);
        }

        @Test(dataProvider = "formatProvider")
        public void testPartialPackage(String type) {
            assertPaths(callEndpoint(type)); // assert no paths
        }
    }

    private static WebArchive scanConfigApp(String name, ConfigAsset config) {
        return ShrinkWrap.create(WebArchive.class, name)
                .addPackages(true, ScanConfigApplication.class.getPackage())
                .addAsResource(config, "META-INF/microprofile-config.properties");
    }

    private static void assertPaths(ValidatableResponse vr, String... paths) {
        for (String path : paths) {
            vr.body("paths", hasKey(path));
        }
        vr.body("paths", aMapWithSize(paths.length));
    }
}
