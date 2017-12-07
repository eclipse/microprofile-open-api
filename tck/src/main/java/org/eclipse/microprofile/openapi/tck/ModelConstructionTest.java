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
import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.Discriminator;
import org.eclipse.microprofile.openapi.models.media.Encoding;
import org.eclipse.microprofile.openapi.models.media.MediaType;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.media.XML;
import org.eclipse.microprofile.openapi.models.parameters.CookieParameter;
import org.eclipse.microprofile.openapi.models.parameters.HeaderParameter;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.PathParameter;
import org.eclipse.microprofile.openapi.models.parameters.QueryParameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.responses.APIResponses;
import org.eclipse.microprofile.openapi.models.security.OAuthFlow;
import org.eclipse.microprofile.openapi.models.security.OAuthFlows;
import org.eclipse.microprofile.openapi.models.security.Scopes;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.servers.ServerVariable;
import org.eclipse.microprofile.openapi.models.servers.ServerVariables;
import org.eclipse.microprofile.openapi.models.tags.Tag;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Assert.fail("Invocation of getter method \"" + getter.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public void invokeSetter(Object target, Object value) {
            try {
                setter.invoke(target, value);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Assert.fail("Invocation of setter method \"" + setter.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public Object invokeBuilder(Object target, Object value) {
            try {
                return builder.invoke(target, value);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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
    
    @Deployment
    public static WebArchive createProxy() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        return war;
    }
    
    @Test
    public void componentsTest() {
        final Components c = processConstructible(Components.class);
        
        final String callbackKey = "myCallback";
        final Callback callbackValue = createConstructibleInstance(Callback.class);
        checkSameObject(c, c.addCallback(callbackKey, callbackValue));
        checkMapEntry(c.getCallbacks(), callbackKey, callbackValue);
        
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(c, c.addExample(exampleKey, exampleValue));
        checkMapEntry(c.getExamples(), exampleKey, exampleValue);
        
        final String headerKey = "myHeader";
        final Header headerValue = createConstructibleInstance(Header.class);
        checkSameObject(c, c.addHeader(headerKey, headerValue));
        checkMapEntry(c.getHeaders(), headerKey, headerValue);
        
        final String linkKey = "myLink";
        final Link linkValue = createConstructibleInstance(Link.class);
        checkSameObject(c, c.addLink(linkKey, linkValue));
        checkMapEntry(c.getLinks(), linkKey, linkValue);
        
        final String parameterKey = "myParameter";
        final Parameter<?> parameterValue = createConstructibleInstance(CookieParameter.class);
        checkSameObject(c, c.addParameter(parameterKey, parameterValue));
        checkMapEntry(c.getParameters(), parameterKey, parameterValue);
        
        final String requestBodyKey = "myRequestBody";
        final RequestBody requestBodyValue = createConstructibleInstance(RequestBody.class);
        checkSameObject(c, c.addRequestBody(requestBodyKey, requestBodyValue));
        checkMapEntry(c.getRequestBodies(), requestBodyKey, requestBodyValue);
        
        final String responseKey = "myResponse";
        final APIResponse responseValue = createConstructibleInstance(APIResponse.class);
        checkSameObject(c, c.addResponse(responseKey, responseValue));
        checkMapEntry(c.getResponses(), responseKey, responseValue);
        
        final String schemaKey = "mySchema";
        final Schema schemaValue = createConstructibleInstance(Schema.class);
        checkSameObject(c, c.addSchema(schemaKey, schemaValue));
        checkMapEntry(c.getSchemas(), schemaKey, schemaValue);
        
        final String securitySchemeKey = "mySecurityScheme";
        final SecurityScheme securitySchemeValue = createConstructibleInstance(SecurityScheme.class);
        checkSameObject(c, c.addSecurityScheme(securitySchemeKey, securitySchemeValue));
        checkMapEntry(c.getSecuritySchemes(), securitySchemeKey, securitySchemeValue);
    }
    
    @Test
    public void externalDocumentationTest() {
        processConstructible(ExternalDocumentation.class);
    }
    
    @Test
    public void openAPITest() {
        final OpenAPI o = processConstructible(OpenAPI.class);
        
        final SecurityRequirement sr = createConstructibleInstance(SecurityRequirement.class);
        checkSameObject(o, o.addSecurityRequirement(sr));
        checkListEntry(o.getSecurity(), sr);
        
        final Server s = createConstructibleInstance(Server.class);
        checkSameObject(o, o.addServer(s));
        checkListEntry(o.getServers(), s);
        
        final Tag t = createConstructibleInstance(Tag.class);
        checkSameObject(o, o.addTag(t));
        checkListEntry(o.getTags(), t);
    }
    
    @Test
    public void operationTest() {
        final Operation o = processConstructible(Operation.class);
        
        final Parameter<?> p = createConstructibleInstance(CookieParameter.class);
        checkSameObject(o, o.addParameter(p));
        checkListEntry(o.getParameters(), p);
        
        final SecurityRequirement sr = createConstructibleInstance(SecurityRequirement.class);
        checkSameObject(o, o.addSecurityRequirement(sr));
        checkListEntry(o.getSecurity(), sr);
        
        final Server s = createConstructibleInstance(Server.class);
        checkSameObject(o, o.addServer(s));
        checkListEntry(o.getServers(), s);
        
        final String tag = new String("myTag");
        checkSameObject(o, o.addTag(tag));
        checkListEntry(o.getTags(), tag);
    }
    
    @Test
    public void pathItemTest() {
        final PathItem pi = processConstructible(PathItem.class);
        
        final Parameter<?> p = createConstructibleInstance(CookieParameter.class);
        checkSameObject(pi, pi.addParameter(p));
        checkListEntry(pi.getParameters(), p);
        
        final Server s = createConstructibleInstance(Server.class);
        checkSameObject(pi, pi.addServer(s));
        checkListEntry(pi.getServers(), s);
    }
    
    @Test
    public void pathsTest() {
        final Paths p = processConstructible(Paths.class);
        
        final String pathItemKey = "myPathItem";
        final PathItem pathItemValue = createConstructibleInstance(PathItem.class);
        checkSameObject(p, p.addPathItem(pathItemKey, pathItemValue));
        checkMapEntry(p, pathItemKey, pathItemValue);
        
        final String pathItemKey2 = "myPathItem2";
        final PathItem pathItemValue2 = createConstructibleInstance(PathItem.class);
        Assert.assertNull("No previous mapping expected.", p.put(pathItemKey2, pathItemValue2));
        checkMapEntry(p, pathItemKey2, pathItemValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, p.size());
    }
    
    @Test
    public void callbackTest() {
        final Callback c = processConstructible(Callback.class);
        
        final String pathItemKey = "myPathItem";
        final PathItem pathItemValue = createConstructibleInstance(PathItem.class);
        checkSameObject(c, c.addPathItem(pathItemKey, pathItemValue));
        checkMapEntry(c, pathItemKey, pathItemValue);
        
        final String pathItemKey2 = "myPathItem2";
        final PathItem pathItemValue2 = createConstructibleInstance(PathItem.class);
        Assert.assertNull("No previous mapping expected.", c.put(pathItemKey2, pathItemValue2));
        checkMapEntry(c, pathItemKey2, pathItemValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, c.size());
    }
    
    @Test
    public void exampleTest() {
        processConstructible(Example.class);
    }
    
    @Test
    public void headerTest() {
        final Header h = processConstructible(Header.class);
        
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(h, h.addExample(exampleKey, exampleValue));
        checkMapEntry(h.getExamples(), exampleKey, exampleValue);
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
        final Link l = processConstructible(Link.class);
        
        final String parameterKey = "myParameter";
        final Parameter<?> parameterValue = createConstructibleInstance(CookieParameter.class);
        checkSameObject(l, l.addParameter(parameterKey, parameterValue));
        checkMapEntry(l.getParameters(), parameterKey, parameterValue);
    }
    
    @Test
    public void contentTest() {
        final Content c = processConstructible(Content.class);
        
        final String mediaTypeKey = "myPathItem";
        final MediaType mediaTypeValue = createConstructibleInstance(MediaType.class);
        checkSameObject(c, c.addMediaType(mediaTypeKey, mediaTypeValue));
        checkMapEntry(c, mediaTypeKey, mediaTypeValue);
        
        final String mediaTypeKey2 = "myPathItem2";
        final MediaType mediaTypeValue2 = createConstructibleInstance(MediaType.class);
        Assert.assertNull("No previous mapping expected.", c.put(mediaTypeKey2, mediaTypeValue2));
        checkMapEntry(c, mediaTypeKey2, mediaTypeValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, c.size());
    }
    
    @Test
    public void discriminatorTest() {
        final Discriminator d = processConstructible(Discriminator.class);
        
        final String key = "myKey";
        final String value = new String("myValue");
        checkSameObject(d, d.addMapping(key, value));
        checkMapEntry(d.getMapping(), key, value);
    }
    
    @Test
    public void encodingTest() {
        processConstructible(Encoding.class);
    }
    
    @Test
    public void mediaTypeTest() {
        final MediaType mt = processConstructible(MediaType.class);
        
        final String encodingKey = "myEncoding";
        final Encoding encodingValue = createConstructibleInstance(Encoding.class);
        checkSameObject(mt, mt.addEncoding(encodingKey, encodingValue));
        checkMapEntry(mt.getEncoding(), encodingKey, encodingValue);
        
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(mt, mt.addExample(exampleKey, exampleValue));
        checkMapEntry(mt.getExamples(), exampleKey, exampleValue);
    }
    
    @Test
    public void schemaTest() {
        final Schema s = processConstructible(Schema.class);
        
        final Schema allOf = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addAllOf(allOf));
        checkListEntry(s.getAllOf(), allOf);
        
        final Schema anyOf = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addAnyOf(anyOf));
        checkListEntry(s.getAnyOf(), anyOf);
        
        final String enumeration = new String("enumValue");
        checkSameObject(s, s.addEnumeration(enumeration));
        checkListEntry(s.getEnumeration(), enumeration);
        
        final Schema oneOf = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addOneOf(oneOf));
        checkListEntry(s.getOneOf(), oneOf);
        
        final String propertySchemaKey = "myPropertySchemaKey";
        final Schema propertySchemaValue = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addProperty(propertySchemaKey, propertySchemaValue));
        checkMapEntry(s.getProperties(), propertySchemaKey, propertySchemaValue);
        
        final String required = new String("required");
        checkSameObject(s, s.addRequired(required));
        checkListEntry(s.getRequired(), required);
    }
    
    @Test
    public void xmlTest() {
        processConstructible(XML.class);
    }
    
    @Test
    public void cookieParameterTest() {
        final CookieParameter cp = processConstructible(CookieParameter.class);
        checkParameterCommon(cp, Parameter.In.COOKIE, "cookie");
    }

    @Test
    public void headerParameterTest() {
        final HeaderParameter hp = processConstructible(HeaderParameter.class);
        checkParameterCommon(hp, Parameter.In.HEADER, "header");
    }
    
    @Test
    public void pathParameterTest() {
        final PathParameter pp = processConstructible(PathParameter.class);
        checkParameterCommon(pp, Parameter.In.PATH, "path");
    }
    
    @Test
    public void queryParameterTest() {
        final QueryParameter qp = processConstructible(QueryParameter.class);
        checkParameterCommon(qp, Parameter.In.QUERY, "query");
    }
    
    @Test
    public void requestBodyTest() {
        processConstructible(RequestBody.class);
    }
    
    @Test
    public void apiResponseTest() {
        final APIResponse response = processConstructible(APIResponse.class);
        
        final String headerKey = "myHeaderKey";
        final Header headerValue = createConstructibleInstance(Header.class);
        checkSameObject(response, response.addHeader(headerKey, headerValue));
        checkMapEntry(response.getHeaders(), headerKey, headerValue);
        
        final String linkKey = "myLinkKey";
        final Link linkValue = createConstructibleInstance(Link.class);
        checkSameObject(response, response.addLink(linkKey, linkValue));
        checkMapEntry(response.getLinks(), linkKey, linkValue);
    }
    
    @Test
    public void apiResponsesTest() {
        final APIResponses responses = processConstructible(APIResponses.class);
        
        final String responseKey = "myResponse";
        final APIResponse responseValue = createConstructibleInstance(APIResponse.class);
        checkSameObject(responses, responses.addApiResponse(responseKey, responseValue));
        checkMapEntry(responses, responseKey, responseValue);
        
        final String responseKey2 = "myResponse2";
        final APIResponse responseValue2 = createConstructibleInstance(APIResponse.class);
        Assert.assertNull("No previous mapping expected.", responses.put(responseKey2, responseValue2));
        checkMapEntry(responses, responseKey2, responseValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, responses.size());
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
        final Scopes s = processConstructible(Scopes.class);
        
        final String scopeKey = "myScope";
        final String scopeValue = new String("myDescription");
        checkSameObject(s, s.addScope(scopeKey, scopeValue));
        checkMapEntry(s, scopeKey, scopeValue);
        
        final String scopeKey2 = "myScope2";
        final String scopeValue2 = new String("myDescription2");
        Assert.assertNull("No previous mapping expected.", s.put(scopeKey2, scopeValue2));
        checkMapEntry(s, scopeKey2, scopeValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, s.size());
    }
    
    @Test
    public void securityRequirementTest() {
        final SecurityRequirement sr = processConstructible(SecurityRequirement.class);
        
        final String schemeKey = "myResponse";
        final List<String> schemeValue = new ArrayList<String>();
        checkSameObject(sr, sr.addScheme(schemeKey, schemeValue));
        checkMapEntry(sr, schemeKey, schemeValue);
        
        final String schemeKey2 = "myResponse2";
        final List<String> schemeValue2 = new ArrayList<String>();
        Assert.assertNull("No previous mapping expected.", sr.put(schemeKey2, schemeValue2));
        checkMapEntry(sr, schemeKey2, schemeValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, sr.size());
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
        final ServerVariable sv = processConstructible(ServerVariable.class);
        
        final String enumeration = new String("enumValue");
        checkSameObject(sv, sv.addEnumeration(enumeration));
        checkListEntry(sv.getEnumeration(), enumeration);
    }
    
    @Test
    public void serverVariablesTest() {
        final ServerVariables svs = processConstructible(ServerVariables.class);
        
        final String varKey = "myServerVariable";
        final ServerVariable varValue = createConstructibleInstance(ServerVariable.class);
        checkSameObject(svs, svs.addServerVariable(varKey, varValue));
        checkMapEntry(svs, varKey, varValue);
        
        final String varKey2 = "myServerVariable2";
        final ServerVariable varValue2 = createConstructibleInstance(ServerVariable.class);
        Assert.assertNull("No previous mapping expected.", svs.put(varKey2, varValue2));
        checkMapEntry(svs, varKey2, varValue2);
        
        Assert.assertEquals("The map is expected to contain two entries.", 2, svs.size());
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
        Assert.assertSame("The value associated with the key: " + extensionName1 + " is expected to be the same one that was added.",
                obj1, map.get(extensionName1));
        Assert.assertSame("The value associated with the key: " + extensionName2 + " is expected to be the same one that was added.",
                obj2, map.get(extensionName2));
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
        // Check that the short name ref value can be set using the setter method and that the getter method returns the expanded value.
        if (!(r instanceof PathItem)) {
            final String shortName = "myRef2";
            final String myRef2 = createReference(r, shortName);
            r.setRef(shortName);
            Assert.assertEquals("The return value of getRef() is expected to be a fully expanded name.", myRef2, r.getRef());
        }
        // Check that the ref value can be set using the builder method and that the getter method returns the same value.
        final String myRef3 = createReference(r, "myRef3");
        final Reference<?> self = r.ref(myRef3);
        Assert.assertSame("The return value of ref() is expected to return the current instance.", r, self);
        Assert.assertEquals("The return value of getRef() is expected to be equal to the value that was set.", myRef3, r.getRef());
        // Check that the short name ref value can be set using the builder method and that the getter method returns the expanded value.
        if (!(r instanceof PathItem)) {
            final String shortName = "myRef4";
            final String myRef4 = createReference(r, shortName);
            final Reference<?> self2 = r.ref(shortName);
            Assert.assertSame("The return value of ref() is expected to return the current instance.", r, self2);
            Assert.assertEquals("The return value of getRef() is expected to be a fully expanded name.", myRef4, r.getRef());
        }
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
        if (clazz == Parameter.class) {
            clazz = CookieParameter.class;
        }
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
            return new String("object");
        }
        return null;
    }
    
    private String createReference(Reference<?> r, String v) {
        final StringBuilder sb = new StringBuilder();
        if (r instanceof APIResponse) {
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
        final Map<String,Property> properties = collectProperties0(clazz);
        // Parameter has sub-types.
        // Add the getter/setter/builder methods from the base type.
        if (Parameter.class.isAssignableFrom(clazz)) {
            properties.putAll(PARAMETER_PROPERTIES);
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
    
    private <T extends Parameter<T>> void checkParameterCommon(Parameter<T> p, Parameter.In expectedIn, String name) {
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(p, p.addExample(exampleKey, exampleValue));
        checkMapEntry(p.getExamples(), exampleKey, exampleValue);
        
        // Check value of read-only 'in' property.
        Assert.assertEquals("The value of the 'in' property is expected to be '" + name + "'.",
                expectedIn, p.getIn());
    }
    
    private <T> void checkMapEntry(Map<String,T> map, String key, T value) {
        Assert.assertNotNull("The map must not be null.", map);
        Assert.assertTrue("The map is expected to contain the key: " + key, map.containsKey(key));
        Assert.assertSame("The value associated with the key: " + key + " is expected to be the same one that was added.",
                value, map.get(key));
    }
    
    private <T> void checkListEntry(List<T> list, T value) {
        Assert.assertNotNull("The list must not be null.", list);
        Assert.assertTrue("The list is expected to contain the value: " + value, list.stream().anyMatch((v) -> v == value));
    }
    
    private <T> void checkSameObject(T expected, T actual) {
        Assert.assertSame("Expecting same object.", expected, actual);
    }
}
