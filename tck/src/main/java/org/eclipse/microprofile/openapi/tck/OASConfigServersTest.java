/**
 * Copyright (c) 2017, 2018 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package org.eclipse.microprofile.openapi.tck;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class OASConfigServersTest extends AppTestBase {
    private ValidatableResponse vr;
    
    @Deployment(name = "airlines")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "airlines.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addAsManifestResource("server-microprofile-config.properties", "microprofile-config.properties");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testServer(String type) throws InterruptedException {
        vr = callEndpoint(type);
        
        vr.body("servers.findAll { it }.url", hasSize(2));
        vr.body("servers.findAll { it }.url", containsInAnyOrder("https://xyz.com/v1", "https://abc.com/v1"));
        
        vr.body("paths.'/bookings/{id}'.servers.findAll { it }.url", hasSize(2));
        vr.body("paths.'/bookings/{id}'.servers.findAll { it }.url", containsInAnyOrder("https://xyz.io/v1", "https://xyz.io/v2"));
        
        vr.body("paths.'/user/createWithArray'.servers.findAll { it }.url", hasSize(1));
        vr.body("paths.'/user/createWithArray'.servers.findAll { it }.url", contains("https://xyz.io/v3"));
        
        vr.body("paths.'/bookings/{id}'.get.servers.findAll { it }.url", hasSize(1));
        vr.body("paths.'/bookings/{id}'.get.servers.findAll { it }.url", contains("https://abc.io/v1"));
        
        vr.body("paths.'/reviews'.post.servers.findAll { it }.url", hasSize(2));
        vr.body("paths.'/reviews'.post.servers.findAll { it }.url", 
                containsInAnyOrder("https://newreviewserver.io/v1","https://newreviewserver.io/v2"));
        
    }
}
