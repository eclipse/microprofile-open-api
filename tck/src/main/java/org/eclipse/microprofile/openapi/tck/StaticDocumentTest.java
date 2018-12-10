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

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

/**
 * This test covers Open API documents that are specified by the
 * META-INF/openapi.yaml file. It verifies that the /openapi
 * endpoint returns the correct content for these static files.
 */
public class StaticDocumentTest extends AppTestBase {
    
    @Deployment(name = "static")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "static.war")
                .addAsManifestResource("simpleapi.yaml", "openapi.yaml");
    }
    
    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testStaticDocument(String type) {
        ValidatableResponse vr = callEndpoint(type);
        
        vr.body("openapi", startsWith("3.0."));
        
        vr.body("servers", hasSize(1));
        vr.body("servers.find{ it.description == 'MySimpleAPI' }.url",
                equalTo("https:///MySimpleAPI/1.0.0"));
        
        vr.body("info.description", equalTo("This is a simple API"));
        vr.body("info.version", equalTo("1.0.0"));
        vr.body("info.title", equalTo("Simple Inventory API"));
        vr.body("info.contact.email", equalTo("you@your-company.com"));
        vr.body("info.license.name", equalTo("Apache 2.0"));
        vr.body("info.license.url", equalTo("http://www.apache.org/licenses/LICENSE-2.0.html"));
        
        vr.body("tags", hasSize(2));
        vr.body("tags.find{ it.name == 'admins' }.description",
                equalTo("Secured Admin-only calls"));
        vr.body("tags.find{ it.name == 'developers' }.description",
                equalTo("Operations available to regular developers"));
        
        final String inventoryPathGet = "paths.'/inventory'.get";
        vr.body(inventoryPathGet + ".tags", both(hasSize(1)).and(contains("developers")));
        vr.body(inventoryPathGet + ".summary", equalTo("searches inventory"));
        vr.body(inventoryPathGet + ".operationId", equalTo("searchInventory"));
        vr.body(inventoryPathGet + ".description", 
                both(containsString("By passing in the appropriate options, you can search for")).
                and(containsString("available inventory in the system")));
        vr.body(inventoryPathGet + ".parameters", hasSize(3));
        vr.body(inventoryPathGet + ".parameters.find{ it.name == 'searchString' }.description", 
                equalTo("pass an optional search string for looking up inventory"));
        vr.body(inventoryPathGet + ".parameters.find{ it.name == 'skip' }.description", 
                equalTo("number of records to skip for pagination"));
        vr.body(inventoryPathGet + ".parameters.find{ it.name == 'limit' }.description", 
                equalTo("maximum number of records to return"));
        vr.body(inventoryPathGet + ".responses", aMapWithSize(2));
        vr.body(inventoryPathGet + ".responses.'200'.description", equalTo("search results matching criteria"));
        vr.body(inventoryPathGet + ".responses.'400'.description", equalTo("bad input parameter"));
        
        final String inventoryPathPost = "paths.'/inventory'.post";
        vr.body(inventoryPathPost + ".tags", both(hasSize(1)).and(contains("admins")));
        vr.body(inventoryPathPost + ".summary", equalTo("adds an inventory item"));
        vr.body(inventoryPathPost + ".operationId", equalTo("addInventory"));
        vr.body(inventoryPathPost + ".description", equalTo("Adds an item to the system"));
        vr.body(inventoryPathPost + ".responses", aMapWithSize(3));
        vr.body(inventoryPathPost + ".responses.'201'.description", equalTo("item created"));
        vr.body(inventoryPathPost + ".responses.'400'.description", equalTo("invalid input, object invalid"));
        vr.body(inventoryPathPost + ".responses.'409'.description", equalTo("an existing item already exists"));

        final String inventoryPathPut = "paths.'/inventory'.put";
        vr.body(inventoryPathPut + ".summary", equalTo("put operation"));
        vr.body(inventoryPathPut + ".operationId", equalTo("putInventory"));
        vr.body(inventoryPathPut + ".description", equalTo("tests the put operation"));

        final String inventoryPathDelete = "paths.'/inventory'.delete";
        vr.body(inventoryPathDelete + ".summary", equalTo("delete operation"));
        vr.body(inventoryPathDelete + ".operationId", equalTo("deleteInventory"));
        vr.body(inventoryPathDelete + ".description", equalTo("tests the delete operation"));

        final String inventoryPathOptions = "paths.'/inventory'.options";
        vr.body(inventoryPathOptions + ".summary", equalTo("options operation"));
        vr.body(inventoryPathOptions + ".operationId", equalTo("optionsInventory"));
        vr.body(inventoryPathOptions + ".description", equalTo("tests the options operation"));

        final String inventoryPathHead = "paths.'/inventory'.head";
        vr.body(inventoryPathHead + ".summary", equalTo("head operation"));
        vr.body(inventoryPathHead + ".operationId", equalTo("headInventory"));
        vr.body(inventoryPathHead + ".description", equalTo("tests the head operation"));

        final String inventoryPathPatch = "paths.'/inventory'.patch";
        vr.body(inventoryPathPatch + ".summary", equalTo("patch operation"));
        vr.body(inventoryPathPatch + ".operationId", equalTo("patchInventory"));
        vr.body(inventoryPathPatch + ".description", equalTo("tests the patch operation"));

        final String inventoryPathTrace = "paths.'/inventory'.trace";
        vr.body(inventoryPathTrace + ".summary", equalTo("trace operation"));
        vr.body(inventoryPathTrace + ".operationId", equalTo("traceInventory"));
        vr.body(inventoryPathTrace + ".description", equalTo("tests the trace operation"));
    }
}
