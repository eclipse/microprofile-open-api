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

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.models.Components;
import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.ExternalDocumentation;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.Paths;
import org.eclipse.microprofile.openapi.models.Reference;
import org.eclipse.microprofile.openapi.models.callbacks.Callback;
import org.eclipse.microprofile.openapi.models.examples.Example;
import org.eclipse.microprofile.openapi.models.headers.Header;
import org.eclipse.microprofile.openapi.models.info.Contact;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.info.License;
import org.eclipse.microprofile.openapi.models.links.Link;
import org.eclipse.microprofile.openapi.models.media.ArraySchema;
import org.eclipse.microprofile.openapi.models.media.BinarySchema;
import org.eclipse.microprofile.openapi.models.media.BooleanSchema;
import org.eclipse.microprofile.openapi.models.media.ByteArraySchema;
import org.eclipse.microprofile.openapi.models.media.ComposedSchema;
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.DateSchema;
import org.eclipse.microprofile.openapi.models.media.Discriminator;
import org.eclipse.microprofile.openapi.models.media.EmailSchema;
import org.eclipse.microprofile.openapi.models.media.Encoding;
import org.eclipse.microprofile.openapi.models.media.FileSchema;
import org.eclipse.microprofile.openapi.models.media.IntegerSchema;
import org.eclipse.microprofile.openapi.models.media.MapSchema;
import org.eclipse.microprofile.openapi.models.media.MediaType;
import org.eclipse.microprofile.openapi.models.media.NumberSchema;
import org.eclipse.microprofile.openapi.models.media.ObjectSchema;
import org.eclipse.microprofile.openapi.models.media.PasswordSchema;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.media.StringSchema;
import org.eclipse.microprofile.openapi.models.media.UUIDSchema;
import org.eclipse.microprofile.openapi.models.media.XML;
import org.eclipse.microprofile.openapi.models.parameters.CookieParameter;
import org.eclipse.microprofile.openapi.models.parameters.HeaderParameter;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.PathParameter;
import org.eclipse.microprofile.openapi.models.parameters.QueryParameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.ApiResponse;
import org.eclipse.microprofile.openapi.models.responses.ApiResponses;
import org.eclipse.microprofile.openapi.models.security.OAuthFlow;
import org.eclipse.microprofile.openapi.models.security.OAuthFlows;
import org.eclipse.microprofile.openapi.models.security.Scopes;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.servers.ServerVariable;
import org.eclipse.microprofile.openapi.models.servers.ServerVariables;
import org.eclipse.microprofile.openapi.models.tags.Tag;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test covers construction of the OpenAPI model. It verifies that the implementation can
 * create instances of all of the Constructible interfaces and then invokes methods (including
 * getters, setters and builders) on those instances to verify that they behave correctly.
 */
@RunWith(Arquillian.class)
public class ModelConstructionTest {
    
    private static final Map<String,Property> PARAMETER_PROPERTIES = Collections.unmodifiableMap(collectProperties0(Parameter.class));
    private static final Map<String,Property> SCHEMA_PROPERTIES = Collections.unmodifiableMap(collectProperties0(Schema.class));
    
    // Container for matched getter, setter and builder methods
    static final class Property {
        private final String name;
        private final Class<?> type;
        private Method getter;
        private Method setter;
        private Method builder;
        
        public Property(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }
        public void addGetter(Method getter) {
            this.getter = getter;
        }
        public void addSetter(Method setter) {
            this.setter = setter;
        }
        public void addBuilder(Method builder) {
            this.builder = builder;
        }
        public String getName() {
            return name;
        }
        public Class<?> getType() {
            return type;
        }
        public boolean hasBuilder() {
            return builder != null;
        }
        public Object invokeGetter(Object target) {
            try {
                return getter.invoke(target);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Assert.fail("Invocation of getter method \"" + getter.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public void invokeSetter(Object target, Object value) {
            try {
                setter.invoke(target, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Assert.fail("Invocation of setter method \"" + setter.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public Object invokeBuilder(Object target, Object value) {
            try {
                return builder.invoke(target, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Assert.fail("Invocation of builder method \"" + builder.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public boolean isCompatible(Class<?> type) {
            return this.type == type;
        }
        public boolean isPrimitive() {
            return type.isPrimitive();
        }
        public boolean isComplete() {
            return getter != null && setter != null;
        }
    }
    
    @Test
    public void componentsTest() {
        processConstructible(Components.class);
    }
    
    @Test
    public void externalDocumentationTest() {
        processConstructible(ExternalDocumentation.class);
    }
    
    @Test
    public void openAPITest() {
        processConstructible(OpenAPI.class);
    }
    
    @Test
    public void operationTest() {
        processConstructible(Operation.class);
    }
    
    @Test
    public void pathItemTest() {
        processConstructible(PathItem.class);
    }
    
    @Test
    public void pathsTest() {
        processConstructible(Paths.class);
    }
    
    @Test
    public void callbackTest() {
        processConstructible(Callback.class);
    }
    
    @Test
    public void exampleTest() {
        processConstructible(Example.class);
    }
    
    @Test
    public void headerTest() {
        processConstructible(Header.class);
    }
    
    @Test
    public void contactTest() {
        processConstructible(Contact.class);
    }
    
    @Test
    public void infoTest() {
        processConstructible(Info.class);
    }
    
    @Test
    public void licenseTest() {
        processConstructible(License.class);
    }
    
    @Test
    public void linkTest() {
        processConstructible(Link.class);
    }
    
    @Test
    public void arraySchemaTest() {
        processConstructible(ArraySchema.class);
    }
    
    @Test
    public void binarySchemaTest() {
        processConstructible(BinarySchema.class);
    }
    
    @Test
    public void booleanSchemaTest() {
        processConstructible(BooleanSchema.class);
    }
    
    @Test
    public void byteArraySchemaTest() {
        processConstructible(ByteArraySchema.class);
    }
    
    @Test
    public void composedSchemaTest() {
        processConstructible(ComposedSchema.class);
    }
    
    @Test
    public void contentTest() {
        processConstructible(Content.class);
    }
    
    @Test
    public void dateSchemaTest() {
        processConstructible(DateSchema.class);
    }
    
    @Test
    public void discriminatorTest() {
        processConstructible(Discriminator.class);
    }
    
    @Test
    public void emailSchemaTest() {
        processConstructible(EmailSchema.class);
    }
    
    @Test
    public void encodingTest() {
        processConstructible(Encoding.class);
    }
    
    @Test
    public void fileSchemaTest() {
        processConstructible(FileSchema.class);
    }
    
    @Test
    public void integerSchemaTest() {
        processConstructible(IntegerSchema.class);
    }
    
    @Test
    public void mapSchemaTest() {
        processConstructible(MapSchema.class);
    }
    
    @Test
    public void mediaTypeTest() {
        processConstructible(MediaType.class);
    }
    
    @Test
    public void numberSchemaTest() {
        processConstructible(NumberSchema.class);
    }
    
    @Test
    public void objectSchemaTest() {
        processConstructible(ObjectSchema.class);
    }
    
    @Test
    public void passwordSchemaTest() {
        processConstructible(PasswordSchema.class);
    }
    
    @Test
    public void schemaTest() {
        processConstructible(Schema.class);
    }
    
    @Test
    public void stringSchemaTest() {
        processConstructible(StringSchema.class);
    }
    
    @Test
    public void uuidSchemaTest() {
        processConstructible(UUIDSchema.class);
    }
    
    @Test
    public void xmlTest() {
        processConstructible(XML.class);
    }
    
    @Test
    public void cookieParameterTest() {
        processConstructible(CookieParameter.class);
    }
    
    @Test
    public void headerParameterTest() {
        processConstructible(HeaderParameter.class);
    }
    
    @Test
    public void pathParameterTest() {
        processConstructible(PathParameter.class);
    }
    
    @Test
    public void queryParameterTest() {
        processConstructible(QueryParameter.class);
    }
    
    @Test
    public void requestBodyTest() {
        processConstructible(RequestBody.class);
    }
    
    @Test
    public void apiResponseTest() {
        processConstructible(ApiResponse.class);
    }
    
    @Test
    public void apiResponsesTest() {
        processConstructible(ApiResponses.class);
    }
    
    @Test
    public void oAuthFlowTest() {
        processConstructible(OAuthFlow.class);
    }
    
    @Test
    public void oAuthFlowsTest() {
        processConstructible(OAuthFlows.class);
    }
    
    @Test
    public void scopesTest() {
        processConstructible(Scopes.class);
    }
    
    @Test
    public void securityRequirementTest() {
        processConstructible(SecurityRequirement.class);
    }
    
    @Test
    public void securitySchemeTest() {
        processConstructible(SecurityScheme.class);
    }
    
    @Test
    public void serverTest() {
        processConstructible(Server.class);
    }
    
    @Test
    public void serverVariableTest() {
        processConstructible(ServerVariable.class);
    }
    
    @Test
    public void serverVariablesTest() {
        processConstructible(ServerVariables.class);
    }
    
    @Test
    public void tagTest() {
        processConstructible(Tag.class);
    }

    private <T extends Constructible> T processConstructible(Class<T> clazz) {
        final T o = createConstructibleInstance(clazz);
        if (o instanceof Extensible && Extensible.class.isAssignableFrom(clazz)) {
            processExtensible((Extensible) o);
        }
        if (o instanceof Reference && Reference.class.isAssignableFrom(clazz)) {
            processReference((Reference<?>) o);
        }
        final Map<String,Property> properties = collectProperties(clazz);
        properties.values().stream().filter((p) -> p.isComplete()).forEach((p) -> {
            processConstructibleProperty(o, p, clazz);
        });
        return o;
    }
    
    private <T extends Constructible> T createConstructibleInstance(Class<T> clazz) {
        // Check that the OASFactory is able to create an instance of the given Class.
        final T o1 = OASFactory.createObject(clazz);
        Assert.assertNotNull("The return value of OASFactory.createObject(" + clazz.getName() + ") must not be null.", o1);
        Assert.assertTrue("The return value of OASFactory.createObject() is expected to be an instance of: " + clazz.getName(), clazz.isInstance(o1));
        final T o2 = OASFactory.createObject(clazz);
        Assert.assertNotNull("The return value of OASFactory.createObject(" + clazz.getName() + ") must not be null.", o2);
        Assert.assertTrue("The return value of OASFactory.createObject() is expected to be an instance of: " + clazz.getName(), clazz.isInstance(o2));
        Assert.assertNotSame("OASFactory.createObject(" + clazz.getName() + ") is expected to create a new object on each invocation.", o1, o2);
        return o1;
    }
    
    private void processExtensible(Extensible e) {
        final String extensionName1 = "x-" + e.getClass().getName() + "-1";
        final Object obj1 = new Object();
        final String extensionName2 = "x-" + e.getClass().getName() + "-2";
        final Object obj2 = new Object();
        // Check that extensions can be added to and retrieved from the map.
        e.addExtension(extensionName1, obj1);
        e.addExtension(extensionName2, obj2);
        final Map<String, Object> map = e.getExtensions();
        Assert.assertEquals("The extensions map is expected to contain two entries.", 2, map.size());
        Assert.assertTrue("The extensions map is expected to contain the key: " + extensionName1, map.containsKey(extensionName1));
        Assert.assertTrue("The extensions map is expected to contain the key: " + extensionName2, map.containsKey(extensionName2));
        Assert.assertSame("The value associated with the key: " + extensionName1 + " is expected to be the same one that was added.", obj1, map.get(extensionName1));
        Assert.assertSame("The value associated with the key: " + extensionName2 + " is expected to be the same one that was added.", obj2, map.get(extensionName2));
        // Check that the extension map can be replaced with the setter and that it is returned by the getter.
        final Map<String, Object> newMap = new HashMap<>();
        e.setExtensions(newMap);
        final Map<String, Object> map2 = e.getExtensions();
        Assert.assertEquals("The extensions map is expected to contain no entries.", 0, map2.size());
        Assert.assertSame("The return value of getExtensions() is expected to be the same value that was set.", newMap, map2);
    }
    
    private void processReference(Reference<?> r) {
        // Check that the ref value can be set using the setter method and that the getter method returns the same value.
        final String myRef1 = createReference(r, "myRef1");
        r.setRef(myRef1);
        Assert.assertEquals("The return value of getRef() is expected to be equal to the value that was set.", myRef1, r.getRef());
        // Check that the ref value can be set using the builder method and that the getter method returns the same value.
        final String myRef2 = createReference(r, "myRef2");
        final Reference<?> self = r.ref(myRef2);
        Assert.assertSame("The return value of ref() is expected to return the current instance.", r, self);
        Assert.assertEquals("The return value of getRef() is expected to be equal to the value that was set.", myRef2, r.getRef());
    }
    
    private void processConstructibleProperty(Constructible o, Property p, Class<?> enclosingInterface) {
        final Object value1 = getInstanceOf(p.getType(), false);
        p.invokeSetter(o, value1);
        if (!p.isPrimitive()) {
            Assert.assertSame("The return value of the getter method for property \"" + 
                    p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                    "\" is expected to be the same as the value that was set.", value1, p.invokeGetter(o));
        }
        else {
            Assert.assertEquals("The return value of the getter method for property \"" + 
                    p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                    "\" is expected to be equal to the value that was set.", value1, p.invokeGetter(o));
        }
        if (p.hasBuilder()) {
            final Object value2 = getInstanceOf(p.getType(), true);
            final Object self = p.invokeBuilder(o, value2);
            Assert.assertSame("The return value of the builder method for property \"" + 
                    p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                    "\" is expected to be the same as the value that was set.", o, self);
            if (!p.isPrimitive()) {
                Assert.assertSame("The return value of the getter method for property \"" + 
                        p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                        "\" is expected to be the same as the value that was set.", value2, p.invokeGetter(o));
            }
            else {
                Assert.assertEquals("The return value of the getter method for property \"" + 
                        p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                        "\" is expected to be equal to the value that was set.", value2, p.invokeGetter(o));
            }
        }
    }
    
    // Returns instances for testing getter, setter and builder methods.
    @SuppressWarnings("unchecked")
    private Object getInstanceOf(Class<?> clazz, boolean alternateEnumValue) {
        if (Constructible.class.isAssignableFrom(clazz)) {
            return createConstructibleInstance((Class<Constructible>) clazz);
        }
        else if (Enum.class.isAssignableFrom(clazz)) {
            final Object[] enumConstants = clazz.getEnumConstants();
            if (enumConstants != null && enumConstants.length > 0) {
                if (alternateEnumValue && enumConstants.length > 1) {
                    return enumConstants[1];
                }
                return enumConstants[0];
            }  
        }
        else if (clazz == List.class) {
            return new ArrayList<Object>();
        }
        else if (clazz == Map.class) {
            return new HashMap<Object,Object>();
        }
        else if (clazz == String.class) {
            return new String("value");
        }
        else if (clazz == Boolean.class || clazz == Boolean.TYPE) {
            return new Boolean(true);
        }
        else if (clazz == Byte.class || clazz == Byte.TYPE) {
            return new Byte((byte) 1);
        }
        else if (clazz == Short.class || clazz == Short.TYPE) {
            return new Short((short) 1);
        }
        else if (clazz == Integer.class || clazz == Integer.TYPE) {
            return new Integer(1);
        }
        else if (clazz == Long.class || clazz == Long.TYPE) {
            return new Long(1L);
        }
        else if (clazz == Float.class || clazz == Float.TYPE) {
            return new Float(1);
        }
        else if (clazz == Double.class || clazz == Double.TYPE) {
            return new Double(1);
        }
        else if (clazz == Character.class || clazz == Character.TYPE) {
            return new Character('a');
        }
        else if (clazz == BigInteger.class) {
            return new BigInteger("1");
        }
        else if (clazz == BigDecimal.class) {
            return new BigDecimal("1.0");
        }
        else if (clazz == Object.class) {
            return new Object();
        }
        return null;
    }
    
    private String createReference(Reference<?> r, String v) {
        final StringBuilder sb = new StringBuilder();
        if (r instanceof ApiResponse) {
            sb.append("#/components/responses/");
        }
        else if (r instanceof Callback) {
            sb.append("#/components/callbacks/");
        }
        else if (r instanceof Example) {
            sb.append("#/components/examples/");
        }
        else if (r instanceof Header) {
            sb.append("#/components/headers/");
        }
        else if (r instanceof Link) {
            sb.append("#/components/links/");
        }
        else if (r instanceof Parameter) {
            sb.append("#/components/parameters/");
        }
        else if (r instanceof PathItem) {
            sb.append("http://www.abc.def.ghi/");
        }
        else if (r instanceof RequestBody) {
            sb.append("#/components/requestBodies/");
        }
        else if (r instanceof Schema) {
            sb.append("#/components/schemas/");
        }
        else if (r instanceof SecurityScheme) {
            sb.append("#/components/securitySchemes/");
        }
        sb.append(v);
        return sb.toString();
    }
    
    private Map<String,Property> collectProperties(Class<?> clazz) {
        final Map<String,Property> properties;
        if (clazz == Schema.class) {
            properties = SCHEMA_PROPERTIES;
        }
        else {
            properties = collectProperties0(clazz);
            // Schema and Parameter have sub-types.
            // Add the getter/setter/builder methods from the base type.
            if (Schema.class.isAssignableFrom(clazz)) {
                properties.putAll(SCHEMA_PROPERTIES);
            }
            else if (Parameter.class.isAssignableFrom(clazz)) {
                properties.putAll(PARAMETER_PROPERTIES);
            }  
        }
        return properties;
    }
        
    private static Map<String,Property> collectProperties0(Class<?> clazz) {
        final Map<String,Property> properties = new HashMap<>();
        final Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods).forEach(m -> {
            Class<?> returnType = m.getReturnType();
            int parameterCount = m.getParameterCount();
            String name = m.getName();
            Property p;
            Class<?> type;
            // Possible builder method
            if (returnType == clazz) {
                if (parameterCount == 1) {
                    type = m.getParameterTypes()[0];
                    p = properties.get(name);
                    if (p == null) {
                        p = new Property(name, type);
                        properties.put(name, p);
                    }
                    if (p.isCompatible(type)) {
                        p.addBuilder(m);
                    }
                }
            }
            // Possible setter method
            else if (returnType == Void.TYPE) {
                if (name.startsWith("set") && parameterCount == 1) {
                    name = Introspector.decapitalize(name.substring(3));
                    type = m.getParameterTypes()[0];
                    p = properties.get(name);
                    if (p == null) {
                        p = new Property(name, type);
                        properties.put(name, p);
                    }
                    if (p.isCompatible(type)) {
                        p.addSetter(m);
                    }
                }
            }
            // Possible getter method
            else {
                if (name.startsWith("get") && parameterCount == 0) {
                    name = Introspector.decapitalize(name.substring(3));
                    type = returnType;
                    p = properties.get(name);
                    if (p == null) {
                        p = new Property(name, type);
                        properties.put(name, p);
                    }
                    if (p.isCompatible(type)) {
                        p.addGetter(m);
                    }
                }
            }
        });
        return properties;
    }
}
