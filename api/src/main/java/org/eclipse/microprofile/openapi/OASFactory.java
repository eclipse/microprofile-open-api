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

package org.eclipse.microprofile.openapi;

import org.eclipse.microprofile.openapi.models.Components;
import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.ExternalDocumentation;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.Paths;
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
import org.eclipse.microprofile.openapi.spi.OASFactoryResolver;

/**
 * This class allows application developers to build new OpenAPI model elements.  
 * 
 * <br><br>For example, to start a new top-level OpenAPI element with an ExternalDocument inside of it an application developer would write:
 * 
 * <pre><code>OASFactory.createObject(OpenAPI.class)
 *          .setExternalDocs(OASFactory.createObject(ExternalDocumentation.class).url("http://myDoc"));</code></pre>
 */
public final class OASFactory {
    
    private OASFactory() {}

    /**
     * This method creates a new instance of a constructible element from the OpenAPI model tree.
     *
     * <br><br>Example:
     * <pre><code>OASFactory.createObject(Info.class).title("Airlines").description("Airlines APIs").version("1.0.0");
     * </code></pre>
     * @param <T> describes the type parameter
     * @param clazz represents a model which extends the {@link org.eclipse.microprofile.openapi.models.Constructible} interface
     *
     * @return a new instance of the requested model class
     * 
     * @throws NullPointerException if the specified class is null
     * @throws IllegalArgumentException if an instance could not be created, most likely, due to an illegal or inappropriate class
     */
    public static <T extends Constructible> T createObject(Class<T> clazz) {
        return OASFactoryResolver.instance().createObject(clazz);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.Components} instance.
     *
     * @return a new Components instance
     */
    public static Components createComponents() {
        return createObject(Components.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.ExternalDocumentation} instance.
     *
     * @return a new ExternalDocumentation instance
     */
    public static ExternalDocumentation createExternalDocumentation() {
        return createObject(ExternalDocumentation.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.OpenAPI} instance.
     *
     * @return a new OpenAPI instance
     */
    public static OpenAPI createOpenAPI() {
        return createObject(OpenAPI.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.Operation} instance.
     *
     * @return a new Operation instance
     */
    public static Operation createOperation() {
        return createObject(Operation.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.PathItem} instance.
     *
     * @return a new PathItem instance
     */
    public static PathItem createPathItem() {
        return createObject(PathItem.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.Paths} instance.
     *
     * @return a new Paths instance
     */
    public static Paths createPaths() {
        return createObject(Paths.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.callbacks.Callback} instance.
     *
     * @return a new Callback instance
     */
    public static Callback createCallback() {
        return createObject(Callback.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.examples.Example} instance.
     *
     * @return a new Example instance
     */
    public static Example createExample() {
        return createObject(Example.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.headers.Header} instance.
     *
     * @return a new Header instance
     */
    public static Header createHeader() {
        return createObject(Header.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.info.Contact} instance.
     *
     * @return a new Contact instance
     */
    public static Contact createContact() {
        return createObject(Contact.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.info.Info} instance.
     *
     * @return a new Info instance
     */
    public static Info createInfo() {
        return createObject(Info.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.info.License} instance.
     *
     * @return a new License instance
     */
    public static License createLicense() {
        return createObject(License.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.links.Link} instance.
     *
     * @return a new Link instance
     */
    public static Link createLink() {
        return createObject(Link.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.media.Content} instance.
     *
     * @return a new Content instance
     */
    public static Content createContent() {
        return createObject(Content.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.media.Discriminator} instance.
     *
     * @return a new Discriminator instance
     */
    public static Discriminator createDiscriminator() {
        return createObject(Discriminator.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.media.Encoding} instance.
     *
     * @return a new Encoding instance
     */
    public static Encoding createEncoding() {
        return createObject(Encoding.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.media.MediaType} instance.
     *
     * @return a new MediaType instance
     */
    public static MediaType createMediaType() {
        return createObject(MediaType.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.media.Schema} instance.
     *
     * @return a new Schema instance
     */
    public static Schema createSchema() {
        return createObject(Schema.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.media.XML} instance.
     *
     * @return a new XML instance
     */
    public static XML createXML() {
        return createObject(XML.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.parameters.Parameter} instance.
     *
     * @return a new Parameter instance
     */
    public static Parameter createParameter() {
        return createObject(Parameter.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.parameters.RequestBody} instance.
     *
     * @return a new RequestBody instance
     */
    public static RequestBody createRequestBody() {
        return createObject(RequestBody.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.responses.APIResponse} instance.
     *
     * @return a new APIResponse instance
     */
    public static APIResponse createAPIResponse() {
        return createObject(APIResponse.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.responses.APIResponses} instance.
     *
     * @return a new APIResponses instance
     */
    public static APIResponses createAPIResponses() {
        return createObject(APIResponses.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.security.OAuthFlow} instance.
     *
     * @return a new OAuthFlow instance
     */
    public static OAuthFlow createOAuthFlow() {
        return createObject(OAuthFlow.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.security.OAuthFlows} instance.
     *
     * @return a new OAuthFlows instance
     */
    public static OAuthFlows createOAuthFlows() {
        return createObject(OAuthFlows.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.security.SecurityRequirement} instance.
     *
     * @return a new SecurityRequirement instance
     */
    public static SecurityRequirement createSecurityRequirement() {
        return createObject(SecurityRequirement.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.security.SecurityScheme} instance.
     *
     * @return a new SecurityScheme instance
     */
    public static SecurityScheme createSecurityScheme() {
        return createObject(SecurityScheme.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.servers.Server} instance.
     *
     * @return a new Server instance
     */
    public static Server createServer() {
        return createObject(Server.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.servers.ServerVariable} instance.
     *
     * @return a new ServerVariable instance
     */
    public static ServerVariable createServerVariable() {
        return createObject(ServerVariable.class);
    }

    /**
     * This method creates a new {@link org.eclipse.microprofile.openapi.models.tags.Tag} instance.
     *
     * @return a new Tag instance
     */
    public static Tag createTag() {
        return createObject(Tag.class);
    }

}