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

import java.util.Map;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.info.License;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class OASFactoryErrorTest {
    
    public interface MyConstructible extends Constructible {}
    public interface MyLicense extends License {}
    public abstract class MyAbstractLicenseImpl implements License {}
    public final class MyLicenseImpl implements License {
        @Override
        public Map<String, Object> getExtensions() {
            return null;
        }
        @Override
        public void addExtension(String name, Object value) {}
        @Override
        public void setExtensions(Map<String, Object> extensions) {}
        @Override
        public String getName() {
            return null;
        }
        @Override
        public void setName(String name) {}
        @Override
        public License name(String name) {
            return null;
        }
        @Override
        public String getUrl() {
            return null;
        }
        @Override
        public void setUrl(String url) {}
        @Override
        public License url(String url) {
            return null;
        }
    }
    
    @Test(expected = NullPointerException.class)
    public void nullValueTest() {
        @SuppressWarnings("unused")
        final Object o = OASFactory.createObject(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void baseInterfaceTest() {
        @SuppressWarnings("unused")
        final Constructible c = OASFactory.createObject(Constructible.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void extendedBaseInterfaceTest() {
        @SuppressWarnings("unused")
        final MyConstructible m = OASFactory.createObject(MyConstructible.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void extendedInterfaceTest() {
        @SuppressWarnings("unused")
        final MyLicense m = OASFactory.createObject(MyLicense.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void customAbstractClassTest() {
        @SuppressWarnings("unused")
        final MyAbstractLicenseImpl m = OASFactory.createObject(MyAbstractLicenseImpl.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void customClassTest() {
        @SuppressWarnings("unused")
        final MyLicenseImpl m = OASFactory.createObject(MyLicenseImpl.class);
    }
}
