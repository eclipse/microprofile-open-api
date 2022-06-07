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
package org.eclipse.microprofile.openapi.tck.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.shrinkwrap.api.asset.Asset;

/**
 * Utility to allow the contents of microprofile-config.properties to be defined within the deployment method
 * <p>
 * Example usage:
 * 
 * <pre>
 * <code>
 * ConfigAsset config = new ConfigAsset()
 *         .put(OASConfig.SCAN_PACKAGES, "com.example.myPackage")
 *         .put(OASConfig.SCAN_EXCLUDE_CLASSES, "com.example.myPackage.MyClass");
 * WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
 *         .addPackages(true, "com.example.myPackage")
 *         .addAsManifestResource(config, "microprofile-config.properties");
 * </code>
 * </pre>
 */
public class ConfigAsset implements Asset {

    private Properties properties = new Properties();

    public ConfigAsset put(String key, String... values) {
        properties.put(key, String.join(", ", values));
        return this;
    }

    @Override
    public InputStream openStream() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            properties.store(baos, "Written by ConfigAsset");
        } catch (IOException e) {
            throw new RuntimeException("Error writing microprofile config properties", e);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

}
