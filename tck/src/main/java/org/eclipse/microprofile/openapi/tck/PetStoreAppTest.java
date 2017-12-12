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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class PetStoreAppTest extends AppTestBase {
    @Deployment(name = "petstore")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "petstore.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.petstore");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testSchema(String type) {
        ValidatableResponse vr = callEndpoint(type);
        // Schema and DiscriminatorMapping annotation tests
        // Basic properties
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.default", equalTo("Dog"));
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.enum", hasItems("Cat", "Dog", "Lizard"));
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.externalDocs.description", equalTo("Pet Types"));
        vr.body("paths.'/pet/findByTags'.get.parameters.find{ it.name == 'tags' }.schema.deprecated", equalTo(true));
        vr.body("paths.'/store/order/{orderId}'.get.responses.'900'.schema", nullValue());

        // Numerical properties
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.maximum", equalTo(101));
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.exclusiveMaximum", equalTo(true));
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.minimum", equalTo(9));
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.exclusiveMinimum", equalTo(true));
        vr.body("paths.'/pet/{petId}'.get.parameters.find{ it.name == 'petId' }.schema.multipleOf", equalTo(10));

        // String properties
        vr.body("paths.'/pet/{petId}'.delete.parameters.find{ it.name == 'apiKey' }.schema.maxLength", equalTo(256));
        vr.body("paths.'/pet/{petId}'.delete.parameters.find{ it.name == 'apiKey' }.schema.minLength", equalTo(32));

        // Other properties
        String storeOrderResponses = "paths.'/store/order/{orderId}'.get.responses";
        vr.body("paths.'/pet/{petId}'.get.responses.'200'.content.'application/json'.schema.readonly", equalTo(true));
        vr.body(storeOrderResponses + ".'404'.content.'application/json'.schema.anyOf",
                hasItems("#/components/schemas/Order", "#/components/schemas/BadOrder"));
        vr.body(storeOrderResponses + ".'200'.content.'application/json'.schema.allOf",
                hasItems("#/components/schemas/Order", "#/components/schemas/Pet"));
        vr.body("paths.'/pet/{petId}'.get.responses.'200'.content.'application/json'.schema.oneOf",
                hasItems("#/components/schemas/Cat", "#/components/schemas/Dog", "#/components/schemas/Lizard"));
        vr.body(storeOrderResponses + ".'200'.content.'application/json'.schema.not", equalTo("#/components/schemas/BadOrder"));
        vr.body(storeOrderResponses + ".'404'.content.'application/json'.schema.discriminatorProperty", equalTo("id"));
        vr.body(storeOrderResponses + ".'404'.content.'application/json'.schema.discriminatorMapping",
                hasEntry("0", "#/components/schemas/BadOrder"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testExtension(String type) {
        // Extension annotation tests
        ValidatableResponse vr = callEndpoint(type);

        // extension on field
        String componentExt = "components.schemas.User.extensions";
        vr.body(componentExt, hasSize(3));
        vr.body(componentExt + ".find{ it.name == 'x-mp-field1'}", equalTo(true));
        vr.body(componentExt + ".find{ it.name == 'x-mp-field2'}", equalTo(true));
        vr.body(componentExt + ".find{ it.name == 'x-mp-field3'}", equalTo(false));

        // extension on method
        String pathExt = "paths.'/pet/findByStatus'.get.extensions";
        vr.body(pathExt, hasSize(3));
        vr.body(pathExt + ".find{ it.name == 'x-mp-method1'}", equalTo(true));
        vr.body(pathExt + ".find{ it.name == 'x-mp-method2'}", equalTo(true));
        vr.body(pathExt + ".find{ it.name == 'x-mp-method3'}", equalTo(false));

        // extension on parameter
        String parmExt = "paths.'/pet/findByStatus'.parameters.find{ it.name == 'status' }.extensions";
        vr.body(parmExt, hasSize(1));
        vr.body(parmExt + ".find{ it.name == 'x-mp-parm1'}", equalTo(true));

        // extension on type: app class
        vr.body("extensions", hasSize(3));
        vr.body("extensions.find{ it.name == 'x-mp-openapi1'}", equalTo(true));
        vr.body("extensions.find{ it.name == 'x-mp-openapi2'}", equalTo(true));
        vr.body("extensions.find{ it.name == 'x-mp-openapi3'}", equalTo(false));

        // extension on type: resource class
        endpointExtension(vr, "'/store/inventory'.get");
        endpointExtension(vr, "'/store/order/{orderId}'.get");
        endpointExtension(vr, "'/store/order'.post");
        endpointExtension(vr, "'/store/order/{orderId}'.delete");
    }

    private void endpointExtension(ValidatableResponse vr, String endpoint) {
        String ext1 = ".find{ it.name == 'x-mp-type1' }";
        String ext2 = ".find{ it.name == 'x-mp-type2' }";
        vr.body("paths." + endpoint + ".extensions", hasSize(2));
        vr.body("paths." + endpoint + ".extensions" + ext1, equalTo(true));
        vr.body("paths." + endpoint + ".extensions" + ext2, equalTo(false));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testSecurityRequirement(String type) {
        ValidatableResponse vr = callEndpoint(type);

        vr.body("paths.'/pet'.put.security.petsHttp[0][0]", equalTo(null));

        vr.body("paths.'/pet'.post.security.petsApiKey[0][0]", equalTo(null));

        vr.body("paths.'/pet/{petId}'.delete.security.petsOAuth2[0][0]", equalTo("write:pets"));

        vr.body("paths.'/store/inventory'.get.security.storeOpenIdConnect[0]", hasSize(2));
        vr.body("paths.'/store/inventory'.get.security.storeOpenIdConnect[0][0]", equalTo("write:store"));
        vr.body("paths.'/store/inventory'.get.security.storeOpenIdConnect[0][1]", equalTo("read:store"));
        vr.body("paths.'/store/inventory'.get.security.storeHttp[0]", equalTo(null));

        vr.body("paths.'/store/order/{orderId}'.get.security.storeOpenIdConnect", hasSize(2));
        vr.body("paths.'/store/order/{orderId}'.get.security.storeOpenIdConnect[0][0]", equalTo("write:store"));
        vr.body("paths.'/store/order/{orderId}'.get.security.storeOpenIdConnect[0][1]", equalTo("read:store"));
        vr.body("paths.'/store/order/{orderId}'.get.security.find { it.storeHttp != null }.storeHttp", empty());
        
        vr.body("paths.'/user'.post.security.find { it.userApiKey != null }.userApiKey", empty());
        vr.body("paths.'/user'.post.security.find { it.userBasicHttp != null }.userBasicHttp", empty());
        vr.body("paths.'/user'.post.security.find { it.userBearerHttp != null }.userBearerHttp", empty());
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testSecuritySchemes(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String s = "components.securitySchemes";
        vr.body(s, hasKey("petsOAuth2"));
        vr.body(s, hasKey("userApiKey"));
        vr.body(s, hasKey("userBearerHttp"));
        vr.body(s, hasKey("petsApiKey"));
        vr.body(s, hasKey("storeOpenIdConnect"));
        vr.body(s, hasKey("userBasicHttp"));
        vr.body(s, hasKey("storeHttp"));
        vr.body(s, hasKey("petsHttp"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testSecurityScheme(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String petsApiKey = "components.securitySchemes.petsApiKey.";
        vr.body(petsApiKey + "type", equalTo("apiKey"));
        vr.body(petsApiKey + "description", equalTo("authentication needed to create a new pet profile for the store"));
        vr.body(petsApiKey + "name", equalTo("createPetProfile"));
        vr.body(petsApiKey + "in", equalTo("header"));

        String userApiKey = "components.securitySchemes.userApiKey.";
        vr.body(userApiKey + "type", equalTo("apiKey"));
        vr.body(userApiKey + "description", equalTo("authentication needed to create a new user profile for the store"));
        vr.body(userApiKey + "name", equalTo("createOrUpdateUserProfile1"));
        vr.body(userApiKey + "in", equalTo("header"));

        String userBearerHttp = "components.securitySchemes.userBearerHttp.";
        vr.body(userBearerHttp + "type", equalTo("http"));
        vr.body(userBearerHttp + "description", equalTo("authentication needed to create a new user profile for the store"));
        vr.body(userBearerHttp + "scheme", equalTo("bearer"));
        vr.body(userBearerHttp + "bearerFormat", equalTo("JWT"));

        String petsOAuth2 = "components.securitySchemes.petsOAuth2.";
        vr.body(petsOAuth2 + "type", equalTo("oauth2"));
        vr.body(petsOAuth2 + "description", equalTo("authentication needed to delete a pet profile"));

        String userBasicHttp = "components.securitySchemes.userBasicHttp.";
        vr.body(userBasicHttp + "type", equalTo("http"));
        vr.body(userBasicHttp + "description", equalTo("authentication needed to create a new user profile for the store"));
        vr.body(userBasicHttp + "scheme", equalTo("basic"));

        String storeHttp = "components.securitySchemes.storeHttp.";
        vr.body(storeHttp + "type", equalTo("http"));
        vr.body(storeHttp + "description", equalTo("Basic http authentication to access the pet store resource"));
        vr.body(storeHttp + "scheme", equalTo("basic"));

        String petsHttp = "components.securitySchemes.petsHttp.";
        vr.body(petsHttp + "type", equalTo("http"));
        vr.body(petsHttp + "description", equalTo("authentication needed to update an exsiting record of a pet in the store"));
        vr.body(petsHttp + "scheme", equalTo("bearer"));
        vr.body(petsHttp + "bearerFormat", equalTo("jwt"));

        String storeOpenIdConnect = "components.securitySchemes.storeOpenIdConnect.";
        vr.body(storeOpenIdConnect + "type", equalTo("openIdConnect"));
        vr.body(storeOpenIdConnect + "description", equalTo("openId Connect authentication to access the pet store resource"));
        vr.body(storeOpenIdConnect + "openIdConnectUrl", equalTo("https://petstoreauth.com:4433/oidc/petstore/oidcprovider/authorize"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testOAuthFlows(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String t = "components.securitySchemes.petsOAuth2.flows";
        vr.body(t, hasKey("implicit"));
        vr.body(t, hasKey("authorizationCode"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testOAuthFlow(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String implicit = "components.securitySchemes.petsOAuth2.flows.implicit.";
        vr.body(implicit + "authorizationUrl", equalTo("https://example.com/api/oauth/dialog"));

        String authCode = "components.securitySchemes.petsOAuth2.flows.authorizationCode.";
        vr.body(authCode + "authorizationUrl", equalTo("https://example.com/api/oauth/dialog"));
        vr.body(authCode + "tokenUrl", equalTo("https://example.com/api/oauth/token"));
    }
}
