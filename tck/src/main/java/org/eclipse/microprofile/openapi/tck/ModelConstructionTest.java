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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

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
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.models.Components;
import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;
import org.eclipse.microprofile.openapi.models.ExternalDocumentation;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.PathItem.HttpMethod;
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
import org.eclipse.microprofile.openapi.models.parameters.Parameter;
import org.eclipse.microprofile.openapi.models.parameters.RequestBody;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.responses.APIResponses;
import org.eclipse.microprofile.openapi.models.security.OAuthFlow;
import org.eclipse.microprofile.openapi.models.security.OAuthFlows;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.servers.ServerVariable;
import org.eclipse.microprofile.openapi.models.tags.Tag;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

/**
 * This test covers construction of the OpenAPI model. It verifies that the implementation can
 * create instances of all of the Constructible interfaces and then invokes methods (including
 * getters, setters and builders) on those instances to verify that they behave correctly.
 */
public class ModelConstructionTest extends Arquillian {
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class);
    }

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
                fail("Invocation of getter method \"" + getter.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public void invokeSetter(Object target, Object value) {
            try {
                setter.invoke(target, value);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                fail("Invocation of setter method \"" + setter.getName() + "\" failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        public Object invokeBuilder(Object target, Object value) {
            try {
                return builder.invoke(target, value);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                fail("Invocation of builder method \"" + builder.getName() + "\" failed: " + e.getMessage());
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
        final Components c = processConstructible(Components.class);
        
        final String callbackKey = "myCallback";
        final Callback callbackValue = createConstructibleInstance(Callback.class);
        checkSameObject(c, c.addCallback(callbackKey, callbackValue));
        checkMapEntry(c.getCallbacks(), callbackKey, callbackValue);
        assertEquals(c.getCallbacks().size(), 1, "The map is expected to contain one entry.");
        c.removeCallback(callbackKey);
        assertEquals(c.getCallbacks().size(), 0, "The map is expected to be empty.");
        
        final String callbackKey2 = "myCallbackKey2";
        final Callback callbackValue2 = createConstructibleInstance(Callback.class);
        c.setCallbacks(Collections.singletonMap(callbackKey2, callbackValue2));
        checkMapEntry(c.getCallbacks(), callbackKey2, callbackValue2);
        assertEquals(c.getCallbacks().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addCallback(callbackKey, callbackValue));
        checkMapEntry(c.getCallbacks(), callbackKey, callbackValue);
        assertEquals(c.getCallbacks().size(), 2, "The map is expected to contain two entries.");
        
        Callback otherCallbackValue  = createConstructibleInstance(Callback.class);
        checkMapImmutable(c, Components::getCallbacks, "otherCallback", otherCallbackValue);
        checkNullValueInAdd(c::getCallbacks, c::addCallback, "someCallback", callbackValue);
        
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(c, c.addExample(exampleKey, exampleValue));
        checkMapEntry(c.getExamples(), exampleKey, exampleValue);
        assertEquals(c.getExamples().size(), 1, "The map is expected to contain one entry.");
        c.removeExample(exampleKey);
        assertEquals(c.getExamples().size(), 0, "The map is expected to be empty.");
        Example otherExampleValue  = createConstructibleInstance(Example.class);
        
        final String exampleKey2 = "myExampleKey2";
        final Example exampleValue2 = createConstructibleInstance(Example.class);
        c.setExamples(Collections.singletonMap(exampleKey2, exampleValue2));
        checkMapEntry(c.getExamples(), exampleKey2, exampleValue2);
        assertEquals(c.getExamples().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addExample(exampleKey, exampleValue));
        checkMapEntry(c.getExamples(), exampleKey, exampleValue);
        assertEquals(c.getExamples().size(), 2, "The map is expected to contain two entries.");
        
        checkMapImmutable(c, Components::getExamples, "otherExample", otherExampleValue);
        checkNullValueInAdd(c::getExamples, c::addExample, "someExample", exampleValue);
        
        final String headerKey = "myHeader";
        final Header headerValue = createConstructibleInstance(Header.class);
        checkSameObject(c, c.addHeader(headerKey, headerValue));
        checkMapEntry(c.getHeaders(), headerKey, headerValue);
        assertEquals(c.getHeaders().size(), 1, "The map is expected to contain one entry.");
        c.removeHeader(headerKey);
        assertEquals(c.getHeaders().size(), 0, "The map is expected to be empty.");
        Header otherHeaderValue  = createConstructibleInstance(Header.class);
        
        final String headerKey2 = "myHeaderKey2";
        final Header headerValue2 = createConstructibleInstance(Header.class);
        c.setHeaders(Collections.singletonMap(headerKey2, headerValue2));
        checkMapEntry(c.getHeaders(), headerKey2, headerValue2);
        assertEquals(c.getHeaders().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addHeader(headerKey, headerValue));
        checkMapEntry(c.getHeaders(), headerKey, headerValue);
        assertEquals(c.getHeaders().size(), 2, "The map is expected to contain two entries.");
        
        checkMapImmutable(c, Components::getHeaders, "otherHeader", otherHeaderValue);
        checkNullValueInAdd(c::getHeaders, c::addHeader, "some-header", headerValue);
        
        final String linkKey = "myLink";
        final Link linkValue = createConstructibleInstance(Link.class);
        checkSameObject(c, c.addLink(linkKey, linkValue));
        checkMapEntry(c.getLinks(), linkKey, linkValue);
        assertEquals(c.getLinks().size(), 1, "The map is expected to contain one entry.");
        c.removeLink(linkKey);
        assertEquals(c.getLinks().size(), 0, "The map is expected to be empty.");
        Link otherLinkValue  = createConstructibleInstance(Link.class);
        
        final String linkKey2 = "myLinkKey2";
        final Link linkValue2 = createConstructibleInstance(Link.class);
        c.setLinks(Collections.singletonMap(linkKey2, linkValue2));
        checkMapEntry(c.getLinks(), linkKey2, linkValue2);
        assertEquals(c.getLinks().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addLink(linkKey, linkValue));
        checkMapEntry(c.getLinks(), linkKey, linkValue);
        assertEquals(c.getLinks().size(), 2, "The map is expected to contain two entries.");
        
        checkMapImmutable(c, Components::getLinks, "otherLink", otherLinkValue);
        checkNullValueInAdd(c::getLinks, c::addLink, "someLink", linkValue);
        
        final String parameterKey = "myParameter";
        final Parameter parameterValue = createConstructibleInstance(Parameter.class);
        checkSameObject(c, c.addParameter(parameterKey, parameterValue));
        checkMapEntry(c.getParameters(), parameterKey, parameterValue);
        assertEquals(c.getParameters().size(), 1, "The list is expected to contain one entry.");
        c.removeParameter(parameterKey);
        assertEquals(c.getParameters().size(), 0, "The list is expected to be empty.");
        checkNullValueInAdd(c::getParameters, c::addParameter, "someParameter", parameterValue);
        
        final String parameterKey2 = "myParameterKey2";
        final Parameter parameterValue2 = createConstructibleInstance(Parameter.class);
        c.setParameters(Collections.singletonMap(parameterKey2, parameterValue2));
        checkMapEntry(c.getParameters(), parameterKey2, parameterValue2);
        assertEquals(c.getParameters().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addParameter(parameterKey, parameterValue));
        checkMapEntry(c.getParameters(), parameterKey, parameterValue);
        assertEquals(c.getParameters().size(), 2, "The map is expected to contain two entries.");
        
        Parameter otherParameterValue  = createConstructibleInstance(Parameter.class);
        checkMapImmutable(c, Components::getParameters, "otherParameter", otherParameterValue);
        
        final String requestBodyKey = "myRequestBody";
        final RequestBody requestBodyValue = createConstructibleInstance(RequestBody.class);
        checkSameObject(c, c.addRequestBody(requestBodyKey, requestBodyValue));
        checkMapEntry(c.getRequestBodies(), requestBodyKey, requestBodyValue);
        assertEquals(c.getRequestBodies().size(), 1, "The map is expected to contain one entry.");
        c.removeRequestBody(requestBodyKey);
        assertEquals(c.getRequestBodies().size(), 0, "The map is expected to be empty.");
        
        final String requestBodyKey2 = "myRequestBodyKey2";
        final RequestBody requestBodyValue2 = createConstructibleInstance(RequestBody.class);
        c.setRequestBodies(Collections.singletonMap(requestBodyKey2, requestBodyValue2));
        checkMapEntry(c.getRequestBodies(), requestBodyKey2, requestBodyValue2);
        assertEquals(c.getRequestBodies().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addRequestBody(requestBodyKey, requestBodyValue));
        checkMapEntry(c.getRequestBodies(), requestBodyKey, requestBodyValue);
        assertEquals(c.getRequestBodies().size(), 2, "The map is expected to contain two entries.");
        
        RequestBody otherRequestBodyValue  = createConstructibleInstance(RequestBody.class);
        checkMapImmutable(c, Components::getRequestBodies, "otherRequestBody", otherRequestBodyValue);
        checkNullValueInAdd(c::getRequestBodies, c::addRequestBody, "someRequestBody", requestBodyValue);
        
        final String responseKey = "myResponse";
        final APIResponse responseValue = createConstructibleInstance(APIResponse.class);
        checkSameObject(c, c.addResponse(responseKey, responseValue));
        checkMapEntry(c.getResponses(), responseKey, responseValue);
        assertEquals(c.getResponses().size(), 1, "The map is expected to contain one entry.");
        c.removeResponse(responseKey);
        assertEquals(c.getResponses().size(), 0, "The map is expected to be empty.");
        
        final String responseKey2 = "myResponseKey2";
        final APIResponse responseValue2 = createConstructibleInstance(APIResponse.class);
        c.setResponses(Collections.singletonMap(responseKey2, responseValue2));
        checkMapEntry(c.getResponses(), responseKey2, responseValue2);
        assertEquals(c.getResponses().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addResponse(responseKey, responseValue));
        checkMapEntry(c.getResponses(), responseKey, responseValue);
        assertEquals(c.getResponses().size(), 2, "The map is expected to contain two entries.");
        
        APIResponse otherAPIResponseValue  = createConstructibleInstance(APIResponse.class);
        checkMapImmutable(c, Components::getResponses, "otherAPIResponse", otherAPIResponseValue);
        checkNullValueInAdd(c::getResponses, c::addResponse, "someResponse", responseValue);
        
        final String schemaKey = "mySchema";
        final Schema schemaValue = createConstructibleInstance(Schema.class);
        checkSameObject(c, c.addSchema(schemaKey, schemaValue));
        checkMapEntry(c.getSchemas(), schemaKey, schemaValue);
        assertEquals(c.getSchemas().size(), 1, "The map is expected to contain one entry.");
        c.removeSchema(schemaKey);
        assertEquals(c.getSchemas().size(), 0, "The map is expected to be empty.");
        
        final String schemaKey2 = "mySchemaKey2";
        final Schema schemaValue2 = createConstructibleInstance(Schema.class);
        c.setSchemas(Collections.singletonMap(schemaKey2, schemaValue2));
        checkMapEntry(c.getSchemas(), schemaKey2, schemaValue2);
        assertEquals(c.getSchemas().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addSchema(schemaKey, schemaValue));
        checkMapEntry(c.getSchemas(), schemaKey, schemaValue);
        assertEquals(c.getSchemas().size(), 2, "The map is expected to contain two entries.");
        
        Schema otherSchemaValue  = createConstructibleInstance(Schema.class);
        checkMapImmutable(c, Components::getSchemas, "otherSchema", otherSchemaValue);
        checkNullValueInAdd(c::getSchemas, c::addSchema, "someSchema", schemaValue);
        
        final String securitySchemeKey = "mySecurityScheme";
        final SecurityScheme securitySchemeValue = createConstructibleInstance(SecurityScheme.class);
        checkSameObject(c, c.addSecurityScheme(securitySchemeKey, securitySchemeValue));
        checkMapEntry(c.getSecuritySchemes(), securitySchemeKey, securitySchemeValue);
        assertEquals(c.getSecuritySchemes().size(), 1, "The map is expected to contain one entry.");
        c.removeSecurityScheme(securitySchemeKey);
        assertEquals(c.getSecuritySchemes().size(), 0, "The map is expected to be empty.");
        
        final String securitySchemeKey2 = "mySecuritySchemeKey2";
        final SecurityScheme securitySchemeValue2 = createConstructibleInstance(SecurityScheme.class);
        c.setSecuritySchemes(Collections.singletonMap(securitySchemeKey2, securitySchemeValue2));
        checkMapEntry(c.getSecuritySchemes(), securitySchemeKey2, securitySchemeValue2);
        assertEquals(c.getSecuritySchemes().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(c, c.addSecurityScheme(securitySchemeKey, securitySchemeValue));
        checkMapEntry(c.getSecuritySchemes(), securitySchemeKey, securitySchemeValue);
        assertEquals(c.getSecuritySchemes().size(), 2, "The map is expected to contain two entries.");
        
        SecurityScheme otherSecuritySchemeValue  = createConstructibleInstance(SecurityScheme.class);
        checkMapImmutable(c, Components::getSecuritySchemes, "otherSecurityScheme", otherSecuritySchemeValue);
        checkNullValueInAdd(c::getSecuritySchemes, c::addSecurityScheme, "someSecurityScheme", securitySchemeValue);
    }
    
    @Test
    public void externalDocumentationTest() {
        processConstructible(ExternalDocumentation.class);
    }
    
    @Test
    public void openAPITest() {
        final OpenAPI o = processConstructible(OpenAPI.class);
        
        final SecurityRequirement sr = createConstructibleInstance(SecurityRequirement.class);
        sr.addScheme("BasicAuth");
        checkSameObject(o, o.addSecurityRequirement(sr));
        checkListEntry(o.getSecurity(), sr);
        assertEquals(o.getSecurity().size(), 1, "The list is expected to contain one entry.");
        o.removeSecurityRequirement(sr);
        assertEquals(o.getSecurity().size(), 0, "The list is expected to be empty.");
        
        final SecurityRequirement sr2 = createConstructibleInstance(SecurityRequirement.class);
        sr2.addScheme("OAuth2", "read");
        o.setSecurity(Collections.singletonList(sr2));
        assertEquals(o.getSecurity().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getSecurity(), sr2);
        checkSameObject(o, o.addSecurityRequirement(sr));
        assertEquals(o.getSecurity().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getSecurity(), sr);
        
        SecurityRequirement otherSecurityRequirementValue  = createConstructibleInstance(SecurityRequirement.class);
        otherSecurityRequirementValue.addScheme("OAuth2", "admin");
        checkListImmutable(o, OpenAPI::getSecurity, otherSecurityRequirementValue);
        
        final Server s = createConstructibleInstance(Server.class);
        checkSameObject(o, o.addServer(s));
        checkListEntry(o.getServers(), s);
        assertEquals(o.getServers().size(), 1, "The list is expected to contain one entry.");
        o.removeServer(s);
        assertEquals(o.getServers().size(), 0, "The list is expected to be empty.");
        
        final Server s2 = createConstructibleInstance(Server.class);
        o.setServers(Collections.singletonList(s2));
        assertEquals(o.getServers().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getServers(), s2);
        checkSameObject(o, o.addServer(s));
        assertEquals(o.getSecurity().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getServers(), s);
        
        Server otherServer  = createConstructibleInstance(Server.class);
        checkListImmutable(o, OpenAPI::getServers, otherServer);
        
        final Tag t = createConstructibleInstance(Tag.class);
        checkSameObject(o, o.addTag(t));
        checkListEntry(o.getTags(), t);
        assertEquals(o.getTags().size(), 1, "The list is expected to contain one entry.");
        o.removeTag(t);
        assertEquals(o.getTags().size(), 0, "The list is expected to be empty.");
        
        final Tag t2 = createConstructibleInstance(Tag.class);
        o.setTags(Collections.singletonList(t2));
        assertEquals(o.getTags().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getTags(), t2);
        checkSameObject(o, o.addTag(t));
        assertEquals(o.getTags().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getTags(), t);
        
        Tag otherTag  = createConstructibleInstance(Tag.class);
        checkListImmutable(o, OpenAPI::getTags, otherTag);
    }
    
    @Test
    public void operationTest() {
        final Operation o = processConstructible(Operation.class);
        
        final Parameter p = createConstructibleInstance(Parameter.class);
        checkSameObject(o, o.addParameter(p));
        checkListEntry(o.getParameters(), p);
        assertEquals(o.getParameters().size(), 1, "The list is expected to contain one entry.");
        o.removeParameter(p);
        assertEquals(o.getParameters().size(), 0, "The list is expected to be empty.");
        
        final Parameter p2 = createConstructibleInstance(Parameter.class);
        o.setParameters(Collections.singletonList(p2));
        assertEquals(o.getParameters().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getParameters(), p2);
        checkSameObject(o, o.addParameter(p));
        assertEquals(o.getParameters().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getParameters(), p);
        
        Parameter otherParameter  = createConstructibleInstance(Parameter.class);
        checkListImmutable(o, Operation::getParameters, otherParameter);
        
        final SecurityRequirement sr = createConstructibleInstance(SecurityRequirement.class);
        sr.addScheme("OAuth2", Arrays.asList("read", "write"));
        checkSameObject(o, o.addSecurityRequirement(sr));
        checkListEntry(o.getSecurity(), sr);
        assertEquals(o.getSecurity().size(), 1, "The list is expected to contain one entry.");
        o.removeSecurityRequirement(sr);
        assertEquals(o.getSecurity().size(), 0, "The list is expected to be empty.");
        
        final SecurityRequirement sr2 = createConstructibleInstance(SecurityRequirement.class);
        sr2.addScheme("ApiKey");
        o.setSecurity(Collections.singletonList(sr2));
        assertEquals(o.getSecurity().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getSecurity(), sr2);
        checkSameObject(o, o.addSecurityRequirement(sr));
        assertEquals(o.getSecurity().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getSecurity(), sr);
        
        SecurityRequirement otherSecurityRequirement  = createConstructibleInstance(SecurityRequirement.class);
        otherSecurityRequirement.addScheme("BasicAuth");
        checkListImmutable(o, Operation::getSecurity, otherSecurityRequirement);
        
        final Server s = createConstructibleInstance(Server.class);
        checkSameObject(o, o.addServer(s));
        checkListEntry(o.getServers(), s);
        assertEquals(o.getServers().size(), 1, "The list is expected to contain one entry.");
        o.removeServer(s);
        assertEquals(o.getServers().size(), 0, "The list is expected to be empty.");
        
        final Server s2 = createConstructibleInstance(Server.class);
        o.setServers(Collections.singletonList(s2));
        assertEquals(o.getServers().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getServers(), s2);
        checkSameObject(o, o.addServer(s));
        assertEquals(o.getServers().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getServers(), s);
        
        Server otherServer  = createConstructibleInstance(Server.class);
        checkListImmutable(o, Operation::getServers, otherServer);
        
        final String tag = new String("myTag");
        checkSameObject(o, o.addTag(tag));
        checkListEntry(o.getTags(), tag);
        assertEquals(o.getTags().size(), 1, "The list is expected to contain one entry.");
        o.removeTag(tag);
        assertEquals(o.getTags().size(), 0, "The list is expected to be empty.");
        
        final String tag2 = new String("myTag2");
        o.setTags(Collections.singletonList(tag2));
        assertEquals(o.getTags().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(o.getTags(), tag2);
        checkSameObject(o, o.addTag(tag));
        assertEquals(o.getTags().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(o.getTags(), tag);
        
        String otherTag  = new String("otherTag");
        checkListImmutable(o, Operation::getTags, otherTag);
        
        final String callbackKey = "myCallback";
        final Callback callbackValue = createConstructibleInstance(Callback.class);
        checkSameObject(o, o.addCallback(callbackKey, callbackValue));
        checkMapEntry(o.getCallbacks(), callbackKey, callbackValue);
        assertEquals(o.getCallbacks().size(), 1, "The map is expected to contain one entry.");
        o.removeCallback(callbackKey);
        assertEquals(o.getCallbacks().size(), 0, "The map is expected to be empty.");
        
        final String callbackKey2 = "myCallbackKey2";
        final Callback callbackValue2 = createConstructibleInstance(Callback.class);
        o.setCallbacks(Collections.singletonMap(callbackKey2, callbackValue2));
        checkMapEntry(o.getCallbacks(), callbackKey2, callbackValue2);
        assertEquals(o.getCallbacks().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(o, o.addCallback(callbackKey, callbackValue));
        checkMapEntry(o.getCallbacks(), callbackKey, callbackValue);
        assertEquals(o.getCallbacks().size(), 2, "The map is expected to contain two entries.");
        
        Callback otherCallback  = createConstructibleInstance(Callback.class);
        checkMapImmutable(o, Operation::getCallbacks, "otherCallback", otherCallback);
        checkNullValueInAdd(o::getCallbacks, o::addCallback, "someCallback", callbackValue);
    }
    
    @Test
    public void pathItemTest() {
        final PathItem pi = processConstructible(PathItem.class);
        
        final Parameter p = createConstructibleInstance(Parameter.class);
        checkSameObject(pi, pi.addParameter(p));
        checkListEntry(pi.getParameters(), p);
        assertEquals(pi.getParameters().size(), 1, "The list is expected to contain one entry.");
        pi.removeParameter(p);
        assertEquals(pi.getParameters().size(), 0, "The list is expected to be empty.");
        
        final Parameter p2 = createConstructibleInstance(Parameter.class);
        pi.setParameters(Collections.singletonList(p2));
        assertEquals(pi.getParameters().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(pi.getParameters(), p2);
        checkSameObject(pi, pi.addParameter(p));
        assertEquals(pi.getParameters().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(pi.getParameters(), p);
        
        Parameter otherParameter  = createConstructibleInstance(Parameter.class);
        checkListImmutable(pi, PathItem::getParameters, otherParameter);
        
        final Server s = createConstructibleInstance(Server.class);
        checkSameObject(pi, pi.addServer(s));
        checkListEntry(pi.getServers(), s);
        assertEquals(pi.getServers().size(), 1, "The list is expected to contain one entry.");
        pi.removeServer(s);
        assertEquals(pi.getServers().size(), 0, "The list is expected to be empty.");
        
        final Server s2 = createConstructibleInstance(Server.class);
        pi.setServers(Collections.singletonList(s2));
        assertEquals(pi.getServers().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(pi.getServers(), s2);
        checkSameObject(pi, pi.addServer(s));
        assertEquals(pi.getServers().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(pi.getServers(), s);
        
        Server otherServer  = createConstructibleInstance(Server.class);
        checkListImmutable(pi, PathItem::getServers, otherServer);
        
        final Operation o1 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.GET(o1));
        checkSameObject(o1, pi.getGET());
        
        final Operation o2 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.PUT(o2));
        checkSameObject(o2, pi.getPUT());
        
        final Operation o3 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.POST(o3));
        checkSameObject(o3, pi.getPOST());
        
        final Operation o4 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.DELETE(o4));
        checkSameObject(o4, pi.getDELETE());
        
        final Operation o5 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.OPTIONS(o5));
        checkSameObject(o5, pi.getOPTIONS());
        
        final Operation o6 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.HEAD(o6));
        checkSameObject(o6, pi.getHEAD());
        
        final Operation o7 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.PATCH(o7));
        checkSameObject(o7, pi.getPATCH());
        
        final Operation o8 = createConstructibleInstance(Operation.class);
        checkSameObject(pi, pi.TRACE(o8));
        checkSameObject(o8, pi.getTRACE());
        
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.GET, o1);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.PUT, o2);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.POST, o3);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.DELETE, o4);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.OPTIONS, o5);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.HEAD, o6);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.PATCH, o7);
        checkMapEntry(pi.getOperations(), PathItem.HttpMethod.TRACE, o8);
        
        // test with GET:
        PathItem pathItemGET = createConstructibleInstance(PathItem.class);
        Operation operationGET = createConstructibleInstance(Operation.class).description("This is some GET op");
        pathItemGET.setOperation(HttpMethod.GET, operationGET);
        checkSameObject(pathItemGET.getGET(), operationGET);
        pathItemGET.setOperation(HttpMethod.GET, null);
        assertNull(pathItemGET.getGET());

        // test with POST:
        PathItem pathItemPOST = createConstructibleInstance(PathItem.class);
        Operation operationPOST = createConstructibleInstance(Operation.class).description("This is some POST op");
        pathItemPOST.setOperation(HttpMethod.POST, operationPOST);
        checkSameObject(pathItemPOST.getPOST(), operationPOST);
        pathItemPOST.setOperation(HttpMethod.POST, null);
        assertNull(pathItemPOST.getPOST());

        // test with PUT:
        PathItem pathItemPUT = createConstructibleInstance(PathItem.class);
        Operation operationPUT = createConstructibleInstance(Operation.class).description("This is some PUT op");
        pathItemPUT.setOperation(HttpMethod.PUT, operationPUT);
        checkSameObject(pathItemPUT.getPUT(), operationPUT);
        pathItemPUT.setOperation(HttpMethod.PUT, null);
        assertNull(pathItemPUT.getPUT());

        // test with PATCH:
        PathItem pathItemPATCH = createConstructibleInstance(PathItem.class);
        Operation operationPATCH = createConstructibleInstance(Operation.class).description("This is some PATCH op");
        pathItemPATCH.setOperation(HttpMethod.PATCH, operationPATCH);
        checkSameObject(pathItemPATCH.getPATCH(), operationPATCH);
        pathItemPATCH.setOperation(HttpMethod.PATCH, null);
        assertNull(pathItemPATCH.getPATCH());

        // test with DELETE:
        PathItem pathItemDELETE = createConstructibleInstance(PathItem.class);
        Operation operationDELETE = createConstructibleInstance(Operation.class).description("This is some DELETE op");
        pathItemDELETE.setOperation(HttpMethod.DELETE, operationDELETE);
        checkSameObject(pathItemDELETE.getDELETE(), operationDELETE);
        pathItemDELETE.setOperation(HttpMethod.DELETE, null);
        assertNull(pathItemDELETE.getDELETE());

        // test with HEAD:
        PathItem pathItemHEAD = createConstructibleInstance(PathItem.class);
        Operation operationHEAD = createConstructibleInstance(Operation.class).description("This is some HEAD op");
        pathItemHEAD.setOperation(HttpMethod.HEAD, operationHEAD);
        checkSameObject(pathItemHEAD.getHEAD(), operationHEAD);
        pathItemHEAD.setOperation(HttpMethod.HEAD, null);
        assertNull(pathItemHEAD.getHEAD());

        // test with OPTIONS:
        PathItem pathItemOPTIONS = createConstructibleInstance(PathItem.class);
        Operation operationOPTIONS = createConstructibleInstance(Operation.class).description("This is some OPTIONS op");
        pathItemOPTIONS.setOperation(HttpMethod.OPTIONS, operationOPTIONS);
        checkSameObject(pathItemOPTIONS.getOPTIONS(), operationOPTIONS);
        pathItemOPTIONS.setOperation(HttpMethod.OPTIONS, null);
        assertNull(pathItemOPTIONS.getOPTIONS());

        // test with TRACE:
        PathItem pathItemTRACE = createConstructibleInstance(PathItem.class);
        Operation operationTRACE = createConstructibleInstance(Operation.class).description("This is some TRACE op");
        pathItemTRACE.setOperation(HttpMethod.TRACE, operationTRACE);
        checkSameObject(pathItemTRACE.getTRACE(), operationTRACE);
        pathItemTRACE.setOperation(HttpMethod.TRACE, null);
        assertNull(pathItemTRACE.getTRACE());
    }
    
    @Test
    public void pathsTest() {
        final Paths p = processConstructible(Paths.class);
        
        final String pathItemKey = "/myPathItem";
        final PathItem pathItemValue = createConstructibleInstance(PathItem.class);
        p.setPathItems(Collections.singletonMap(pathItemKey, pathItemValue));
        assertTrue(p.hasPathItem(pathItemKey), pathItemKey + " is present in the map");
        assertEquals(p.getPathItems().size(), 1, "The map is expected to contain one entry.");
        assertSame(p.getPathItem(pathItemKey), pathItemValue, 
                "The value associated with the key: " + pathItemKey + " is expected to be the same one that was added.");
        checkMapEntry(p.getPathItems(), pathItemKey, pathItemValue);
        
        final String pathItemKey2 = "/myPathItem2";
        assertFalse(p.hasPathItem(pathItemKey2), pathItemKey2 + " is absent in the map");
        final PathItem pathItemValue2 = createConstructibleInstance(PathItem.class);
        checkSameObject(p, p.addPathItem(pathItemKey2, pathItemValue2));
        assertTrue(p.hasPathItem(pathItemKey2), pathItemKey2 + " is present in the map");
        assertEquals(p.getPathItems().size(), 2, "The map is expected to contain two entries.");
        assertSame(p.getPathItem(pathItemKey2), pathItemValue2, 
                "The value associated with the key: " + pathItemKey2 + " is expected to be the same one that was added.");
        checkMapEntry(p.getPathItems(), pathItemKey2, pathItemValue2);
        
        p.removePathItem(pathItemKey);
        assertFalse(p.hasPathItem(pathItemKey), pathItemKey + " is absent in the map");
        assertEquals(p.getPathItems().size(), 1, "The map is expected to contain one entry.");
        
        p.removePathItem(pathItemKey2);
        assertFalse(p.hasPathItem(pathItemKey2), pathItemKey + " is absent in the map");
        assertEquals(p.getPathItems().size(), 0, "The map is expected to contain 0 entries.");
        
        final PathItem otherValue = createConstructibleInstance(PathItem.class);
        checkMapImmutable(p, Paths::getPathItems, "/otherPathItem", otherValue);
        checkNullValueInAdd(p::getPathItems, p::addPathItem, "/other", otherValue);
    }
    
    @Test
    public void callbackTest() {
        final Callback c = processConstructible(Callback.class);
        
        final String pathItemKey = "myPathItem";
        final PathItem pathItemValue = createConstructibleInstance(PathItem.class);
        c.setPathItems(Collections.singletonMap(pathItemKey, pathItemValue));
        assertTrue(c.hasPathItem(pathItemKey), pathItemKey + " is present in the map");
        assertEquals(c.getPathItems().size(), 1, "The map is expected to contain one entry.");
        assertSame(c.getPathItem(pathItemKey), pathItemValue, 
                "The value associated with the key: " + pathItemKey + " is expected to be the same one that was added.");
        checkMapEntry(c.getPathItems(), pathItemKey, pathItemValue);
        
        final String pathItemKey2 = "myPathItem2";
        assertFalse(c.hasPathItem(pathItemKey2), pathItemKey2 + " is absent in the map");
        final PathItem pathItemValue2 = createConstructibleInstance(PathItem.class);
        checkSameObject(c, c.addPathItem(pathItemKey2, pathItemValue2));
        assertTrue(c.hasPathItem(pathItemKey2), pathItemKey2 + " is present in the map");
        assertEquals(c.getPathItems().size(), 2, "The map is expected to contain two entries.");
        assertSame(c.getPathItem(pathItemKey2), pathItemValue2, 
                "The value associated with the key: " + pathItemKey2 + " is expected to be the same one that was added.");
        checkMapEntry(c.getPathItems(), pathItemKey2, pathItemValue2);
        
        c.removePathItem(pathItemKey);
        assertFalse(c.hasPathItem(pathItemKey), pathItemKey + " is absent in the map");
        assertEquals(c.getPathItems().size(), 1, "The map is expected to contain one entry.");
        
        c.removePathItem(pathItemKey2);
        assertFalse(c.hasPathItem(pathItemKey2), pathItemKey + " is absent in the map");
        assertEquals(c.getPathItems().size(), 0, "The map is expected to contain 0 entries.");
        
        final PathItem otherValue = createConstructibleInstance(PathItem.class);
        checkMapImmutable(c, Callback::getPathItems, "otherPathItem", otherValue);
        checkNullValueInAdd(c::getPathItems, c::addPathItem, "other", otherValue);
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
        assertEquals(h.getExamples().size(), 1, "The map is expected to contain one entry.");
        h.removeExample(exampleKey);
        assertEquals(h.getExamples().size(), 0, "The map is expected to be empty.");
        
        final String exampleKey2 = "myExampleKey2";
        final Example exampleValue2 = createConstructibleInstance(Example.class);
        h.setExamples(Collections.singletonMap(exampleKey2, exampleValue2));
        checkMapEntry(h.getExamples(), exampleKey2, exampleValue2);
        assertEquals(h.getExamples().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(h, h.addExample(exampleKey, exampleValue));
        checkMapEntry(h.getExamples(), exampleKey, exampleValue);
        assertEquals(h.getExamples().size(), 2, "The map is expected to contain two entries.");
        
        Example otherExampleValue = createConstructibleInstance(Example.class);
        checkMapImmutable(h, Header::getExamples, "otherExample", otherExampleValue);
        checkNullValueInAdd(h::getExamples, h::addExample, "otherExample", exampleValue);
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
        final String parameterValue = "$request.parameter.id";
        checkSameObject(l, l.addParameter(parameterKey, parameterValue));
        checkMapEntry(l.getParameters(), parameterKey, parameterValue);
        assertEquals(l.getParameters().size(), 1, "The map is expected to contain one entry.");
        l.removeParameter(parameterKey);
        assertEquals(l.getParameters().size(), 0, "The map is expected to be empty.");
        
        final String parameterKey2 = "myParameterKey2";
        final String parameterValue2 = "$request.parameter2.id";
        l.setParameters(Collections.singletonMap(parameterKey2, parameterValue2));
        checkMapEntry(l.getParameters(), parameterKey2, parameterValue2);
        assertEquals(l.getParameters().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(l, l.addParameter(parameterKey, parameterValue));
        checkMapEntry(l.getParameters(), parameterKey, parameterValue);
        assertEquals(l.getParameters().size(), 2, "The map is expected to contain two entries.");
        
        Object otherExampleValue = new Object();
        checkMapImmutable(l, Link::getParameters, "otherParameter", otherExampleValue);
        checkNullValueInAdd(l::getParameters, l::addParameter, "otherParameter", parameterValue);
    }
    
    @Test
    public void contentTest() {
        final Content c = processConstructible(Content.class);
        
        final String mediaTypeKey = "application/json";
        final MediaType mediaTypeValue = createConstructibleInstance(MediaType.class);
        c.setMediaTypes(Collections.singletonMap(mediaTypeKey, mediaTypeValue));
        assertTrue(c.hasMediaType(mediaTypeKey), mediaTypeKey + " is present in the map");
        assertEquals(c.getMediaTypes().size(), 1, "The map is expected to contain one entry.");
        assertSame(c.getMediaType(mediaTypeKey), mediaTypeValue, 
                "The value associated with the key: " + mediaTypeKey + " is expected to be the same one that was added.");
        checkMapEntry(c.getMediaTypes(), mediaTypeKey, mediaTypeValue);
        
        final String mediaTypeKey2 = "*/*";
        assertFalse(c.hasMediaType(mediaTypeKey2), mediaTypeKey2 + " is absent in the map");
        final MediaType mediaTypeValue2 = createConstructibleInstance(MediaType.class);
        checkSameObject(c, c.addMediaType(mediaTypeKey2, mediaTypeValue2));
        assertTrue(c.hasMediaType(mediaTypeKey2), mediaTypeKey2 + " is present in the map");
        assertEquals(c.getMediaTypes().size(), 2, "The map is expected to contain two entries.");
        assertSame(c.getMediaType(mediaTypeKey2), mediaTypeValue2, 
                "The value associated with the key: " + mediaTypeKey2 + " is expected to be the same one that was added.");
        checkMapEntry(c.getMediaTypes(), mediaTypeKey2, mediaTypeValue2);
        
        c.removeMediaType(mediaTypeKey);
        assertFalse(c.hasMediaType(mediaTypeKey), mediaTypeKey + " is absent in the map");
        assertEquals(c.getMediaTypes().size(), 1, "The map is expected to contain one entry.");
        
        c.removeMediaType(mediaTypeKey2);
        assertFalse(c.hasMediaType(mediaTypeKey2), mediaTypeKey + " is absent in the map");
        assertEquals(c.getMediaTypes().size(), 0, "The map is expected to contain 0 entries.");
        
        final MediaType otherValue = createConstructibleInstance(MediaType.class);
        checkMapImmutable(c, Content::getMediaTypes, "application/txt", otherValue);
    }
    
    @Test
    public void discriminatorTest() {
        final Discriminator d = processConstructible(Discriminator.class);
        
        final String key = "myKey";
        final String value = new String("myValue");
        checkSameObject(d, d.addMapping(key, value));
        checkMapEntry(d.getMapping(), key, value);
        assertEquals(d.getMapping().size(), 1, "The map is expected to contain one entry.");
        d.removeMapping(key);
        assertEquals(d.getMapping().size(), 0, "The map is expected to be empty.");
        
        final String key2 = "myCallbackKey2";
        final String value2 = new String("myValue2");
        d.setMapping(Collections.singletonMap(key2, value2));
        checkMapEntry(d.getMapping(), key2, value2);
        assertEquals(d.getMapping().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(d, d.addMapping(key, value));
        checkMapEntry(d.getMapping(), key, value);
        assertEquals(d.getMapping().size(), 2, "The map is expected to contain two entries.");
        
        final String otherValue = new String("otherValue");
        checkMapImmutable(d, Discriminator::getMapping, "otherValue", otherValue);
        checkNullValueInAdd(d::getMapping, d::addMapping, "otherKey", value);
    }
    
    @Test
    public void encodingTest() {
        Encoding e = processConstructible(Encoding.class);
        
        final String headerKey = "myHeaderKey";
        final Header headerValue = createConstructibleInstance(Header.class);
        checkSameObject(e, e.addHeader(headerKey, headerValue));
        checkMapEntry(e.getHeaders(), headerKey, headerValue);
        assertEquals(e.getHeaders().size(), 1, "The map is expected to contain one entry.");
        e.removeHeader(headerKey);
        assertEquals(e.getHeaders().size(), 0, "The map is expected to be empty.");
        
        final String headerKey2 = "myHeaderKey2";
        final Header headerValue2 = createConstructibleInstance(Header.class);
        e.setHeaders(Collections.singletonMap(headerKey2, headerValue2));
        checkMapEntry(e.getHeaders(), headerKey2, headerValue2);
        assertEquals(e.getHeaders().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(e, e.addHeader(headerKey, headerValue));
        checkMapEntry(e.getHeaders(), headerKey, headerValue);
        assertEquals(e.getHeaders().size(), 2, "The map is expected to contain two entries.");
        
        final Header otherHeaderValue = createConstructibleInstance(Header.class);
        checkMapImmutable(e, Encoding::getHeaders, "otherHeader", otherHeaderValue);
        checkNullValueInAdd(e::getHeaders, e::addHeader, "otherHeaderKey", headerValue);
    }
    
    @Test
    public void mediaTypeTest() {
        final MediaType mt = processConstructible(MediaType.class);
        
        final String encodingKey = "myEncoding";
        final Encoding encodingValue = createConstructibleInstance(Encoding.class);
        checkSameObject(mt, mt.addEncoding(encodingKey, encodingValue));
        checkMapEntry(mt.getEncoding(), encodingKey, encodingValue);
        assertEquals(mt.getEncoding().size(), 1, "The map is expected to contain one entry.");
        mt.removeEncoding(encodingKey);
        assertEquals(mt.getEncoding().size(), 0, "The map is expected to be empty.");
        
        final String encodingKey2 = "myEncodingKey2";
        final Encoding encodingValue2 = createConstructibleInstance(Encoding.class);
        mt.setEncoding(Collections.singletonMap(encodingKey2, encodingValue2));
        checkMapEntry(mt.getEncoding(), encodingKey2, encodingValue2);
        assertEquals(mt.getEncoding().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(mt, mt.addEncoding(encodingKey, encodingValue));
        checkMapEntry(mt.getEncoding(), encodingKey, encodingValue);
        assertEquals(mt.getEncoding().size(), 2, "The map is expected to contain two entries.");
        
        Encoding otherEncodingValue = createConstructibleInstance(Encoding.class);
        checkMapImmutable(mt, MediaType::getEncoding, "otherEncoding", otherEncodingValue);
        checkNullValueInAdd(mt::getEncoding, mt::addEncoding, "otherEncoding", encodingValue);
        
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(mt, mt.addExample(exampleKey, exampleValue));
        checkMapEntry(mt.getExamples(), exampleKey, exampleValue);
        assertEquals(mt.getExamples().size(), 1, "The map is expected to contain one entry.");
        mt.removeExample(exampleKey);
        assertEquals(mt.getExamples().size(), 0, "The map is expected to be empty.");
        
        final String exampleKey2 = "myExampleKey2";
        final Example exampleValue2 = createConstructibleInstance(Example.class);
        mt.setExamples(Collections.singletonMap(exampleKey2, exampleValue2));
        checkMapEntry(mt.getExamples(), exampleKey2, exampleValue2);
        assertEquals(mt.getExamples().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(mt, mt.addExample(exampleKey, exampleValue));
        checkMapEntry(mt.getExamples(), exampleKey, exampleValue);
        assertEquals(mt.getExamples().size(), 2, "The map is expected to contain two entries.");
        
        Example otherExampleValue = createConstructibleInstance(Example.class);
        checkMapImmutable(mt, MediaType::getExamples, "otherExample", otherExampleValue);
        checkNullValueInAdd(mt::getExamples, mt::addExample, "otherExample", exampleValue);
    }
    
    @Test
    public void schemaTest() {
        final Schema s = processConstructible(Schema.class);
        
        final Schema ap = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.additionalPropertiesSchema(ap));
        checkSameObject(ap, s.getAdditionalPropertiesSchema());
        assertEquals(s.getAdditionalPropertiesBoolean(), null, "AdditionalProperties (Boolean type) is expected to be null");
        checkSameObject(s, s.additionalPropertiesBoolean(Boolean.TRUE));
        assertEquals(s.getAdditionalPropertiesBoolean(), Boolean.TRUE, "AdditionalProperties (Boolean type) is expected to be true");
        assertEquals(s.getAdditionalPropertiesSchema(), null, "AdditionalProperties (Schema type) is expected to be null");
        s.setAdditionalPropertiesBoolean(Boolean.FALSE);
        assertEquals(s.getAdditionalPropertiesBoolean(), Boolean.FALSE, "AdditionalProperties (Boolean type) is expected to be false");
        assertEquals(s.getAdditionalPropertiesSchema(), null, "AdditionalProperties (Schema type) is expected to be null");
        s.setAdditionalPropertiesSchema(null);
        assertEquals(s.getAdditionalPropertiesBoolean(), null, "AdditionalProperties (Boolean type) is expected to be null");
        assertEquals(s.getAdditionalPropertiesSchema(), null, "AdditionalProperties (Schema type) is expected to be null");
        
        final Schema allOf = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addAllOf(allOf));
        checkListEntry(s.getAllOf(), allOf);
        assertEquals(s.getAllOf().size(), 1, "The list is expected to contain one entry.");
        s.removeAllOf(allOf);
        assertEquals(s.getAllOf().size(), 0, "The list is expected to be empty.");
        
        final Schema allOf2 = createConstructibleInstance(Schema.class);
        s.setAllOf(Collections.singletonList(allOf2));
        assertEquals(s.getAllOf().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(s.getAllOf(), allOf2);
        checkSameObject(s, s.addAllOf(allOf));
        assertEquals(s.getAllOf().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(s.getAllOf(), allOf);
        
        final Schema otherAllOfValue = createConstructibleInstance(Schema.class);
        checkListImmutable(s, Schema::getAllOf, otherAllOfValue);
        
        final Schema anyOf = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addAnyOf(anyOf));
        checkListEntry(s.getAnyOf(), anyOf);
        assertEquals(s.getAnyOf().size(), 1, "The list is expected to contain one entry.");
        s.removeAnyOf(anyOf);
        assertEquals(s.getAnyOf().size(), 0, "The list is expected to be empty.");
        
        final Schema anyOf2 = createConstructibleInstance(Schema.class);
        s.setAnyOf(Collections.singletonList(anyOf2));
        assertEquals(s.getAnyOf().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(s.getAnyOf(), anyOf2);
        checkSameObject(s, s.addAnyOf(anyOf));
        assertEquals(s.getAnyOf().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(s.getAnyOf(), anyOf);
        
        final Schema otherAnyOfValue = createConstructibleInstance(Schema.class);
        checkListImmutable(s, Schema::getAnyOf, otherAnyOfValue);
        
        final String enumeration = new String("enumValue");
        checkSameObject(s, s.addEnumeration(enumeration));
        checkListEntry(s.getEnumeration(), enumeration);
        assertEquals(s.getEnumeration().size(), 1, "The list is expected to contain one entry.");
        s.removeEnumeration(enumeration);
        assertEquals(s.getEnumeration().size(), 0, "The list is expected to be empty.");
        
        final String enumeration2 = new String("enumValue2");
        s.setEnumeration(Collections.singletonList(enumeration2));
        assertEquals(s.getEnumeration().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(s.getEnumeration(), enumeration2);
        checkSameObject(s, s.addEnumeration(enumeration));
        assertEquals(s.getEnumeration().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(s.getEnumeration(), enumeration);
        
        final String otherEnumerationValue = new String("otherValue");
        checkListImmutable(s, Schema::getEnumeration , otherEnumerationValue);
        
        final Schema oneOf = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addOneOf(oneOf));
        checkListEntry(s.getOneOf(), oneOf);
        assertEquals(s.getOneOf().size(), 1, "The list is expected to contain one entry.");
        s.removeOneOf(oneOf);
        assertEquals(s.getOneOf().size(), 0, "The list is expected to be empty.");
        
        final Schema oneOf2 = createConstructibleInstance(Schema.class);
        s.setOneOf(Collections.singletonList(oneOf2));
        assertEquals(s.getOneOf().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(s.getOneOf(), oneOf2);
        checkSameObject(s, s.addOneOf(oneOf));
        assertEquals(s.getOneOf().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(s.getOneOf(), oneOf);
        
        final Schema otherOneOfValue = createConstructibleInstance(Schema.class);
        checkListImmutable(s, Schema::getOneOf, otherOneOfValue);
        
        final String propertySchemaKey = "myPropertySchemaKey";
        final Schema propertySchemaValue = createConstructibleInstance(Schema.class);
        checkSameObject(s, s.addProperty(propertySchemaKey, propertySchemaValue));
        checkMapEntry(s.getProperties(), propertySchemaKey, propertySchemaValue);
        assertEquals(s.getProperties().size(), 1, "The map is expected to contain one entry.");
        s.removeProperty(propertySchemaKey);
        assertEquals(s.getProperties().size(), 0, "The map is expected to be empty.");
        
        final String propertySchemaKey2 = "myPropertySchemaKey2";
        final Schema propertySchemaValue2 = createConstructibleInstance(Schema.class);
        s.setProperties(Collections.singletonMap(propertySchemaKey2, propertySchemaValue2));
        checkMapEntry(s.getProperties(), propertySchemaKey2, propertySchemaValue2);
        assertEquals(s.getProperties().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(s, s.addProperty(propertySchemaKey, propertySchemaValue));
        checkMapEntry(s.getProperties(), propertySchemaKey, propertySchemaValue);
        assertEquals(s.getProperties().size(), 2, "The map is expected to contain two entries.");
        
        final Schema otherPropertyValue = createConstructibleInstance(Schema.class);
        checkMapImmutable(s, Schema::getProperties, "otherPropertyKey", otherPropertyValue);
        checkNullValueInAdd(s::getProperties, s::addProperty, "otherProperty", propertySchemaValue);
        
        final String required = new String("required");
        checkSameObject(s, s.addRequired(required));
        checkListEntry(s.getRequired(), required);
        assertEquals(s.getRequired().size(), 1, "The list is expected to contain one entry.");
        s.removeRequired(required);
        assertEquals(s.getRequired().size(), 0, "The list is expected to be empty.");
        
        final String required2 = new String("required2");
        s.setRequired(Collections.singletonList(required2));
        assertEquals(s.getRequired().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(s.getRequired(), required2);
        checkSameObject(s, s.addRequired(required));
        assertEquals(s.getRequired().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(s.getRequired(), required);
        
        final String otherRequiredValue = new String("otherRequired");
        checkListImmutable(s, Schema::getEnumeration, otherRequiredValue);
    }
    
    @Test
    public void xmlTest() {
        processConstructible(XML.class);
    }
    
    @Test
    public void parameterTest() {
        final Parameter p = processConstructible(Parameter.class);
        
        final String exampleKey = "myExample";
        final Example exampleValue = createConstructibleInstance(Example.class);
        checkSameObject(p, p.addExample(exampleKey, exampleValue));
        checkMapEntry(p.getExamples(), exampleKey, exampleValue);
        assertEquals(p.getExamples().size(), 1, "The map is expected to contain one entry.");
        p.removeExample(exampleKey);
        assertEquals(p.getExamples().size(), 0, "The map is expected to be empty.");
        
        final String exampleKey2 = "myExampleKey2";
        final Example exampleValue2 = createConstructibleInstance(Example.class);
        p.setExamples(Collections.singletonMap(exampleKey2, exampleValue2));
        checkMapEntry(p.getExamples(), exampleKey2, exampleValue2);
        assertEquals(p.getExamples().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(p, p.addExample(exampleKey, exampleValue));
        checkMapEntry(p.getExamples(), exampleKey, exampleValue);
        assertEquals(p.getExamples().size(), 2, "The map is expected to contain two entries.");
        
        Example otherExampleValue = createConstructibleInstance(Example.class);
        checkMapImmutable(p, Parameter::getExamples, "otherExample", otherExampleValue);
        checkNullValueInAdd(p::getExamples, p::addExample, "otherExample", exampleValue);
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
        assertEquals(response.getHeaders().size(), 1, "The map is expected to contain one entry.");
        response.removeHeader(headerKey);
        assertEquals(response.getHeaders().size(), 0, "The map is expected to be empty.");
        
        final String headerKey2 = "myHeaderKey2";
        final Header headerValue2 = createConstructibleInstance(Header.class);
        response.setHeaders(Collections.singletonMap(headerKey2, headerValue2));
        checkMapEntry(response.getHeaders(), headerKey2, headerValue2);
        assertEquals(response.getHeaders().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(response, response.addHeader(headerKey, headerValue));
        checkMapEntry(response.getHeaders(), headerKey, headerValue);
        assertEquals(response.getHeaders().size(), 2, "The map is expected to contain two entries.");
        
        Header otherHeaderValue  = createConstructibleInstance(Header.class);
        checkMapImmutable(response, APIResponse::getHeaders, "otherHeader", otherHeaderValue);
        checkNullValueInAdd(response::getHeaders, response::addHeader, "some-header", headerValue);
        
        final String linkKey = "myLinkKey";
        final Link linkValue = createConstructibleInstance(Link.class);
        checkSameObject(response, response.addLink(linkKey, linkValue));
        checkMapEntry(response.getLinks(), linkKey, linkValue);
        assertEquals(response.getLinks().size(), 1, "The map is expected to contain one entry.");
        response.removeLink(linkKey);
        assertEquals(response.getLinks().size(), 0, "The map is expected to be empty.");
        Link otherLinkValue  = createConstructibleInstance(Link.class);
        
        final String linkKey2 = "myLinkKey2";
        final Link linkValue2 = createConstructibleInstance(Link.class);
        response.setLinks(Collections.singletonMap(linkKey2, linkValue2));
        checkMapEntry(response.getLinks(), linkKey2, linkValue2);
        assertEquals(response.getLinks().size(), 1, "The map is expected to contain one entry.");
        checkSameObject(response, response.addLink(linkKey, linkValue));
        checkMapEntry(response.getLinks(), linkKey, linkValue);
        assertEquals(response.getLinks().size(), 2, "The map is expected to contain two entries.");
        
        checkMapImmutable(response, APIResponse::getLinks, "otherLink", otherLinkValue);
        checkNullValueInAdd(response::getLinks, response::addLink, "someLinkKey", linkValue);
    }
    
    @Test
    public void apiResponsesTest() {
        final APIResponses responses = processConstructible(APIResponses.class);
        
        final String responseKey = "200";
        final APIResponse pathItemValue = createConstructibleInstance(APIResponse.class);
        responses.setAPIResponses(Collections.singletonMap(responseKey, pathItemValue));
        assertTrue(responses.hasAPIResponse(responseKey), responseKey + " is present in the map");
        assertEquals(responses.getAPIResponses().size(), 1, "The map is expected to contain one entry.");
        assertSame(responses.getAPIResponse(responseKey), pathItemValue, 
                "The value associated with the key: " + responseKey + " is expected to be the same one that was added.");
        checkMapEntry(responses.getAPIResponses(), responseKey, pathItemValue);
        
        final String responseKey2 = "4XX";
        assertFalse(responses.hasAPIResponse(responseKey2), responseKey2 + " is absent in the map");
        final APIResponse pathItemValue2 = createConstructibleInstance(APIResponse.class);
        checkSameObject(responses, responses.addAPIResponse(responseKey2, pathItemValue2));
        assertTrue(responses.hasAPIResponse(responseKey2), responseKey2 + " is present in the map");
        assertEquals(responses.getAPIResponses().size(), 2, "The map is expected to contain two entries.");
        assertSame(responses.getAPIResponse(responseKey2), pathItemValue2, 
                "The value associated with the key: " + responseKey2 + " is expected to be the same one that was added.");
        checkMapEntry(responses.getAPIResponses(), responseKey2, pathItemValue2);
        
        responses.removeAPIResponse(responseKey);
        assertFalse(responses.hasAPIResponse(responseKey), responseKey + " is absent in the map");
        assertEquals(responses.getAPIResponses().size(), 1, "The map is expected to contain one entry.");
        
        responses.removeAPIResponse(responseKey2);
        assertFalse(responses.hasAPIResponse(responseKey2), responseKey + " is absent in the map");
        assertEquals(responses.getAPIResponses().size(), 0, "The map is expected to contain 0 entries.");
        
        final APIResponse otherValue = createConstructibleInstance(APIResponse.class);
        checkMapImmutable(responses, APIResponses::getAPIResponses, "500", otherValue);
        
        assertNull(responses.getDefaultValue(), "No default value expected.");
        final String responseKey3 = APIResponses.DEFAULT;
        final APIResponse responseValue3 = createConstructibleInstance(APIResponse.class);
        checkSameObject(responses, responses.addAPIResponse(responseKey3, responseValue3));
        checkMapEntry(responses.getAPIResponses(), responseKey3, responseValue3);
        checkSameObject(responseValue3, responses.getDefaultValue());
        
        assertEquals(responses.getAPIResponses().size(), 1, "The map is expected to contain one entry.");
        
        responses.setDefaultValue(null);
        assertNull(responses.getAPIResponse(APIResponses.DEFAULT), "No default value expected.");
        assertNull(responses.getDefaultValue(), "No default value expected.");
        
        final APIResponse responseValue4 = createConstructibleInstance(APIResponse.class);
        responses.setDefaultValue(responseValue4);
        checkMapEntry(responses.getAPIResponses(), APIResponses.DEFAULT, responseValue4);
        checkSameObject(responseValue4, responses.getDefaultValue());
        
        checkNullValueInAdd(responses::getAPIResponses, responses::addAPIResponse, "4XX", otherValue);
    }
    
    @Test
    public void oAuthFlowTest() {
        final OAuthFlow o = processConstructible(OAuthFlow.class);
        final String key = "myKey";
        final String value = new String("myValue");
        o.setScopes(Collections.singletonMap(key, value));
        Map<String,String> scopes = o.getScopes();
        assertEquals(scopes.size(), 1, "The list is expected to contain one entry.");
        assertTrue(scopes.containsKey("myKey"), "The map is expected to contain a 'myKey' entry.");
        assertEquals(scopes.get(key), value, "The value corresponding to the 'myKey' is wrong.");
        
        o.setScopes((Map<String, String>) null);
        assertNull(o.getScopes(), "The value is expected to be null.");
    }
    
    @Test
    public void oAuthFlowsTest() {
        processConstructible(OAuthFlows.class);
    }
    
    @Test
    public void securityRequirementTest() {
        final SecurityRequirement sr = processConstructible(SecurityRequirement.class);
        
        final String schemeKey = "myScheme";
        final List<String> schemeValue = new ArrayList<String>();
        sr.setSchemes(Collections.singletonMap(schemeKey, schemeValue));
        assertTrue(sr.hasScheme(schemeKey), schemeKey + " is present in the map");
        assertEquals(sr.getSchemes().size(), 1, "The map is expected to contain one entry.");
        assertSame(sr.getScheme(schemeKey), schemeValue, 
                "The value associated with the key: " + schemeKey + " is expected to be the same one that was added.");
        checkMapEntry(sr.getSchemes(), schemeKey, schemeValue);
        
        final String schemeKey2 = "myScheme2";
        assertFalse(sr.hasScheme(schemeKey2), schemeKey2 + " is absent in the map");
        final List<String> schemeValue2 = new ArrayList<String>();
        checkSameObject(sr, sr.addScheme(schemeKey2, schemeValue2));
        assertTrue(sr.hasScheme(schemeKey2), schemeKey2 + " is present in the map");
        assertEquals(sr.getSchemes().size(), 2, "The map is expected to contain two entries.");
        assertSame(sr.getScheme(schemeKey2), schemeValue2, 
                "The value associated with the key: " + schemeKey2 + " is expected to be the same one that was added.");
        checkMapEntry(sr.getSchemes(), schemeKey2, schemeValue2);
        
        sr.removeScheme(schemeKey);
        assertFalse(sr.hasScheme(schemeKey), schemeKey + " is absent in the map");
        assertEquals(sr.getSchemes().size(), 1, "The map is expected to contain one entry.");
        
        sr.removeScheme(schemeKey2);
        assertFalse(sr.hasScheme(schemeKey2), schemeKey + " is absent in the map");
        assertEquals(sr.getSchemes().size(), 0, "The map is expected to contain 0 entries.");
        
        final List<String> otherValue = new ArrayList<String>();
        checkMapImmutable(sr, SecurityRequirement::getSchemes, "otherScheme", otherValue);
        
        final String schemeKey3 = "myScheme3";
        sr.addScheme(schemeKey3, (String) null);
        assertTrue(sr.hasScheme(schemeKey3), "Expected " + schemeKey3 + " to be present");
        final List<String> schemeValue3 = Collections.emptyList();
        assertEquals(sr.getScheme(schemeKey3), schemeValue3, 
                "The value associated with the key: " + schemeKey3 + " is expected to be an empty list.");

        final String schemeKey4 = "myScheme3";
        sr.addScheme(schemeKey4, (List<String>) null);
        assertTrue(sr.hasScheme(schemeKey4), "Expected " + schemeKey4 + " to be present");
        final List<String> schemeValue4 = Collections.emptyList();
        assertEquals(sr.getScheme(schemeKey4), schemeValue4, 
                "The value associated with the key: " + schemeKey4 + " is expected to be an empty list.");
    }
    
    @Test
    public void securitySchemeTest() {
        processConstructible(SecurityScheme.class);
    }
    
    @Test
    public void serverTest() {
        Server server = processConstructible(Server.class);
        
        final ServerVariable sv1 = createConstructibleInstance(ServerVariable.class);
        server.setVariables(Collections.singletonMap("var1", sv1));
        Map<String,ServerVariable> variables = server.getVariables();
        assertEquals(variables.size(), 1, "The map is expected to contain one entry.");
        assertTrue(variables.containsKey("var1"), "The map is expected to contain a 'var1' entry.");
        assertEquals(variables.get("var1"), sv1, "The value corresponding to the 'var1' is wrong.");
        checkMapEntry(server.getVariables(), "var1", sv1);
        
        final ServerVariable sv2 = createConstructibleInstance(ServerVariable.class);
        checkMapImmutable(server, Server::getVariables, "sv2", sv2);
        final ServerVariable sv3 = createConstructibleInstance(ServerVariable.class);
        checkNullValueInAdd(server::getVariables, server::addVariable, "sv3", sv3);
        
        server.setVariables((Map<String, ServerVariable>) null);
        assertNull(server.getVariables(), "The value is expected to be null.");
    }
    
    @Test
    public void serverVariableTest() {
        final ServerVariable sv = processConstructible(ServerVariable.class);
        
        final String enumeration = new String("enumValue");
        checkSameObject(sv, sv.addEnumeration(enumeration));
        checkListEntry(sv.getEnumeration(), enumeration);
        assertEquals(sv.getEnumeration().size(), 1, "The list is expected to contain one entry.");
        sv.removeEnumeration(enumeration);
        assertEquals(sv.getEnumeration().size(), 0, "The list is expected to be empty.");
        
        final String enumeration2 = new String("enumValue2");
        sv.setEnumeration(Collections.singletonList(enumeration2));
        assertEquals(sv.getEnumeration().size(), 1, "The list is expected to contain one entry.");
        checkListEntry(sv.getEnumeration(), enumeration2);
        checkSameObject(sv, sv.addEnumeration(enumeration));
        assertEquals(sv.getEnumeration().size(), 2, "The list is expected to contain two entries.");
        checkListEntry(sv.getEnumeration(), enumeration);
        
        final String otherEnumerationValue = new String("otherValue");
        checkListImmutable(sv, ServerVariable::getEnumeration , otherEnumerationValue);
    }
    
    @Test
    public void tagTest() {
        processConstructible(Tag.class);
    }

    private <T extends Constructible> T processConstructible(Class<T> clazz) {
        final T o = createConstructibleInstance(clazz);
        if (o instanceof Extensible && Extensible.class.isAssignableFrom(clazz)) {
            processExtensible((Extensible<?>) o);
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
        assertNotNull(o1, "The return value of OASFactory.createObject(" + clazz.getName() + ") must not be null.");
        assertTrue(clazz.isInstance(o1), "The return value of OASFactory.createObject() is expected to be an instance of: " + clazz.getName());
        final T o2 = OASFactory.createObject(clazz);
        assertNotNull(o2, "The return value of OASFactory.createObject(" + clazz.getName() + ") must not be null.");
        assertTrue(clazz.isInstance(o2), "The return value of OASFactory.createObject() is expected to be an instance of: " + clazz.getName());
        assertNotSame(o2, o1, "OASFactory.createObject(" + clazz.getName() + ") is expected to create a new object on each invocation.");
        return o1;
    }
    
    private void processExtensible(Extensible<?> e) {
        final String extensionName1 = "x-" + e.getClass().getName() + "-1";
        final Object obj1 = new Object();
        final String extensionName2 = "x-" + e.getClass().getName() + "-2";
        final Object obj2 = new Object();
        // Check that extensions can be added to and retrieved from the map.
        e.addExtension(extensionName1, obj1);
        e.addExtension(extensionName2, obj2);
        final Map<String, Object> map = e.getExtensions();
        assertEquals(map.size(), 2, "The extensions map is expected to contain two entries.");
        assertTrue(map.containsKey(extensionName1), "The extensions map is expected to contain the key: " + extensionName1);
        assertTrue(map.containsKey(extensionName2), "The extensions map is expected to contain the key: " + extensionName2);
        assertSame(map.get(extensionName1), obj1,
                "The value associated with the key: " + extensionName1 + " is expected to be the same one that was added.");
        assertSame(map.get(extensionName2), obj2,
                "The value associated with the key: " + extensionName2 + " is expected to be the same one that was added.");
        e.removeExtension(extensionName1);
        assertEquals(e.getExtensions().size(), 1, "The extensions map is expected to contain one entry.");
        // Check that the extension map can be replaced with the setter and that it is returned by the getter.
        final Map<String, Object> newMap = new HashMap<>();
        e.setExtensions(newMap);
        final Map<String, Object> map2 = e.getExtensions();
        assertEquals(map2.size(), 0, "The extensions map is expected to contain no entries.");
        assertEquals(map2, newMap, "The return value of getExtensions() is expected to be the same value that was set.");
        // Check that the extension map can be replaced with the builder method and that it is returned by the getter.
        final Map<String, Object> newOtherMap = Collections.singletonMap("x-test", 42);
        e.setExtensions(newOtherMap);
        final Map<String, Object> map3 = e.getExtensions();
        assertEquals(map3.size(), 1, "The extensions map is expected to contain one entry.");
        assertEquals(map3, newOtherMap, "The return value of getExtensions() is expected to be the same value that was set.");
        // Check that a value can be added, even if the map was immutable
        e.addExtension(extensionName1, obj1);
        assertEquals(e.getExtensions().size(), 2, "The extensions map is expected to contain two entries.");

        checkMapImmutable(e, Extensible::getExtensions, "x-other", new Object());
    }
    
    private void processReference(Reference<?> r) {
        // Check that the ref value can be set using the setter method and that the getter method returns the same value.
        final String myRef1 = createReference(r, "myRef1");
        r.setRef(myRef1);
        assertEquals(r.getRef(), myRef1, "The return value of getRef() is expected to be equal to the value that was set.");
        // Check that the short name ref value can be set using the setter method and that the getter method returns the expanded value.
        if (!(r instanceof PathItem)) {
            final String shortName = "myRef2";
            final String myRef2 = createReference(r, shortName);
            r.setRef(shortName);
            assertEquals(r.getRef(), myRef2, "The return value of getRef() is expected to be a fully expanded name.");
        }
        // Check that the ref value can be set using the builder method and that the getter method returns the same value.
        final String myRef3 = createReference(r, "myRef3");
        final Reference<?> self = r.ref(myRef3);
        assertSame(self, r, "The return value of ref() is expected to return the current instance.");
        assertEquals(r.getRef(), myRef3, "The return value of getRef() is expected to be equal to the value that was set.");
        // Check that the short name ref value can be set using the builder method and that the getter method returns the expanded value.
        if (!(r instanceof PathItem)) {
            final String shortName = "myRef4";
            final String myRef4 = createReference(r, shortName);
            final Reference<?> self2 = r.ref(shortName);
            assertSame(self2, r, "The return value of ref() is expected to return the current instance.");
            assertEquals(r.getRef(), myRef4, "The return value of getRef() is expected to be a fully expanded name.");
        }
    }
    
    private void processConstructibleProperty(Constructible o, Property p, Class<?> enclosingInterface) {
        final Object value1 = getInstanceOf(p.getType(), false);
        p.invokeSetter(o, value1);
        if (!p.isPrimitive() && !p.isCompatible(Map.class) && !p.isCompatible(List.class)) {
            assertSame(p.invokeGetter(o), value1, "The return value of the getter method for property \"" + 
                    p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                    "\" is expected to be the same as the value that was set.");
        }
        else {
            assertEquals(p.invokeGetter(o), value1, "The return value of the getter method for property \"" + 
                    p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                    "\" is expected to be equal to the value that was set.");
        }
        if (p.hasBuilder()) {
            final Object value2 = getInstanceOf(p.getType(), true);
            final Object self = p.invokeBuilder(o, value2);
            assertSame(self, o, "The return value of the builder method for property \"" + 
                    p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                    "\" is expected to be the same as the value that was set.");
            if (!p.isPrimitive() && !p.isCompatible(Map.class) && !p.isCompatible(List.class)) {
                assertSame(p.invokeGetter(o), value2, "The return value of the getter method for property \"" + 
                        p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                        "\" is expected to be the same as the value that was set.");
            }
            else {
                assertEquals(p.invokeGetter(o), value2, "The return value of the getter method for property \"" + 
                        p.getName() + "\" of interface \"" + enclosingInterface.getName() +
                        "\" is expected to be equal to the value that was set.");
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
    
    private <K, T> void checkMapEntry(Map<K,T> map, K key, T value) {
        assertNotNull(map, "The map must not be null.");
        assertTrue(map.containsKey(key), "The map is expected to contain the key: " + key);
        assertSame(map.get(key), value, "The value associated with the key: " + key + " is expected to be the same one that was added.");
    }
    
    private <O, K, T> void checkMapImmutable(O container, Function<O, Map<K,T>> mapGetter, K key, T otherValue) {
        Map<K,T> map = mapGetter.apply(container);
        assertNotNull(map, "The map must not be null.");
        assertFalse(map.containsKey(key), "The map is expected to not contain the key: " + key);
        int originalSize = map.size();
        try {
            map.put(key, otherValue);
        }
        catch (Exception e) {
            //It is allowed to throw an exception
        }
        Map<K,T> map2 = mapGetter.apply(container);
        assertNotNull(map2, "The map must not be null.");
        assertFalse(map2.containsKey(key), "The map is expected to not contain the key: " + key);
        assertEquals(map2.size(), originalSize, "The map is expected to have a size of " + originalSize);
    }
    
    private <O, T> void checkNullValueInAdd(Supplier<Map<String, T>> mapGetter, BiFunction<String, T, O> mapAdd, String key, T value) {
        // add null as value for 'key'
        try {
            mapAdd.apply(key, null);
        }
        catch (Exception e) {
            //It is allowed to throw an exception
        }
        assertFalse(mapGetter.get().containsKey(key), "The map is expected to not contain the key: " + key);
        
        // add value as value for 'key'
        mapAdd.apply(key, value);
        assertTrue(mapGetter.get().containsKey(key), "The map is expected to contain the key: " + key);
        
        // add null again as value for 'key'
        try {
            mapAdd.apply(key, null);
        }
        catch (Exception e) {
            //It is allowed to throw an exception
        }
        assertTrue(mapGetter.get().containsKey(key), "The map is expected to contain the key: " + key);
    }
    
    private <T> void checkListEntry(List<T> list, T value) {
        assertNotNull(list, "The list must not be null.");
        assertTrue(list.stream().anyMatch((v) -> v == value), "The list is expected to contain the value: " + value);
    }
    
    private <O, V> void checkListImmutable(O container, Function<O, List<V>> listGetter, V otherValue) {
        List<V> list = listGetter.apply(container);
        assertNotNull(list, "The list must not be null.");
        assertFalse(list.contains(otherValue), "The list is expected to not contain the value: " + otherValue);
        int originalSize = list.size();
        try {
            list.add(otherValue);
        }
        catch (Exception e) {
            //It is allowed to throw an exception
        }
        List<V> list2 = listGetter.apply(container);
        assertNotNull(list2, "The list must not be null.");
        assertFalse(list2.contains(otherValue), "The list is expected to not contain the value: " + otherValue);
        assertEquals(list2.size(), originalSize, "The list is expected to have a size of " + originalSize);
    }
    
    private <T> void checkSameObject(T expected, T actual) {
        assertSame(actual, expected ,"Expecting same object.");
    }
}
