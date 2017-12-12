package org.eclipse.microprofile.openapi.tck;


import org.eclipse.microprofile.openapi.tck.utils.YamlToJsonConverterServlet;

import static org.hamcrest.Matchers.equalTo;

import org.jboss.arquillian.container.test.api.Deployer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;


import io.restassured.response.ValidatableResponse;

public class OASConfigTest {

    private static String properties = null;
    private static ValidatableResponse vr;

    
    @Deployment(name = "airlines", managed = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "airlines.war")
                .addPackages(true, "org.eclipse.microprofile.openapi.apps.airlines")
                .addAsManifestResource("openapi.yaml", "openapi/openapi.yaml")
                .addAsManifestResource(properties, "microprofile-config.properties");
    }
    
    @ArquillianResource
    private Deployer deployer;
    
    @Test
    public void testScanDisable() {
        // setup
        properties = "mp.openapi.scan.disable=true";
        deployer.deploy("airlines");
        // test
        vr.body("openapi", equalTo("test"));
        deployer.undeploy("airlines");
    }
}
