/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 * Copyright 2017 SmartBear Software
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

package org.eclipse.microprofile.openapi.models;

import java.util.List;

import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
import org.eclipse.microprofile.openapi.models.servers.Server;
import org.eclipse.microprofile.openapi.models.tags.Tag;

/**
 * OpenAPI
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md"
 */
public interface OpenAPI extends Constructible, Extensible {

	/**
	   * returns the openapi property from a OpenAPI instance.
	   *
	   * @return String openapi
	   **/

	String getOpenapi();

	/**
	   * sets this OpenAPI's openapi property to the given openapi.
	   *
	   * @param String openapi
	   */
	void setOpenapi(String openapi);

	/**
	   * sets this OpenAPI's openapi property to the given openapi and
	   * returns this instance of OpenAPI
	   *
	   * @param String openapi
	   * @return OpenAPI
	   */
	OpenAPI openapi(String openapi);

	/**
	   * returns the info property from a OpenAPI instance.
	   *
	   * @return Info info
	   **/

	Info getInfo();

	/**
	   * sets this OpenAPI's info property to the given info.
	   *
	   * @param Info info
	   */
	void setInfo(Info info);

	/**
	   * sets this OpenAPI's info property to the given info and
	   * returns this instance of OpenAPI
	   *
	   * @param Info info
	   * @return OpenAPI
	   */
	OpenAPI info(Info info);

	/**
	   * returns the externalDocs property from a OpenAPI instance.
	   *
	   * @return ExternalDocumentation externalDocs
	   **/
	ExternalDocumentation getExternalDocs();

	/**
	   * sets this OpenAPI's externalDocs property to the given externalDocs.
	   *
	   * @param ExternalDocumentation externalDocs
	   */
	void setExternalDocs(ExternalDocumentation externalDocs);

	/**
	   * sets this OpenAPI's externalDocs property to the given externalDocs and
	   * returns this instance of OpenAPI
	   *
	   * @param ExternalDocumentation externalDocs
	   * @return OpenAPI
	   */
	OpenAPI externalDocs(ExternalDocumentation externalDocs);

	/**
	   * returns the Servers defined in the API
	   *
	   * @return List&lt;Server&gt; servers
	   **/

	List<Server> getServers();

	/**
	   * sets this OpenAPI's servers property to the given servers.
	   *
	   * @param List&lt;Server&gt;servers
	   */
	void setServers(List<Server> servers);

	/**
	   * sets this OpenAPI's servers property to the given servers and
	   * returns this instance of OpenAPI
	   *
	   * @param List&lt;Server&gt;servers
	   * @return OpenAPI
	   */
	OpenAPI servers(List<Server> servers);

	/**
	   * Adds the given serversItem to this OpenAPI's list of servers, with the given key as its key.
	   *
	   * @param String key
	   * @param Server serversItem
	   * @return OpenAPI
	   */
	OpenAPI addServersItem(Server serversItem);

	/**
	   * returns the security property from a OpenAPI instance.
	   *
	   * @return List&lt;SecurityRequirement&gt; security
	   **/

	List<SecurityRequirement> getSecurity();

	/**
	   * sets this OpenAPI's security property to the given security.
	   *
	   * @param List&lt;SecurityRequirement&gt;security
	   */
	void setSecurity(List<SecurityRequirement> security);

	/**
	   * sets this OpenAPI's security property to the given security and
	   * returns this instance of OpenAPI
	   *
	   * @param List&lt;SecurityRequirement&gt;servers
	   * @return OpenAPI
	   */
	OpenAPI security(List<SecurityRequirement> security);

	/**
	   * Adds the given securityItem to this OpenAPI's list of securitItems, with the given key as its key.
	   *
	   * @param String key
	   * @param SecurityRequirement securityItem
	   * @return OpenAPI
	   */
	OpenAPI addSecurityItem(SecurityRequirement securityItem);

	/**
	   * returns the tags property from a OpenAPI instance.
	   *
	   * @return List&lt;Tag&gt; tags
	   **/

	List<Tag> getTags();

	/**
	   * sets this OpenAPI's tags property to the given Tags.
	   *
	   * @param List&lt;Tag&gt;tags
	   */
	void setTags(List<Tag> tags);

	/**
	   * sets this OpenAPI's tags property to the given tags and
	   * returns this instance of OpenAPI
	   *
	   * @param List&lt;Tag&gt;tags
	   * @return OpenAPI
	   */
	OpenAPI tags(List<Tag> tags);

	/**
	   * Adds the given tagsItem to this OpenAPI's list of tags, with the given key as its key.
	   *
	   * @param String key
	   * @param Tag tagsItem
	   * @return OpenAPI
	   */
	OpenAPI addTagsItem(Tag tagsItem);

	/**
	   * returns the paths property from a OpenAPI instance.
	   *
	   * @return Paths paths
	   **/

	Paths getPaths();

	/**
	   * sets this OpenAPI's paths property to the given paths.
	   *
	   * @param List&lt;Paths&gt;paths
	   */
	void setPaths(Paths paths);

	/**
	   * sets this OpenAPI's paths property to the given paths and
	   * returns this instance of OpenAPI
	   *
	   * @param List&lt;Paths&gt;paths
	   * @return OpenAPI
	   */
	OpenAPI paths(Paths paths);

	/**
	   * returns the components property from a OpenAPI instance.
	   *
	   * @return Components components
	   **/

	Components getComponents();

	/**
	   * sets this OpenAPI's components property to the given components.
	   *
	   * @param List&lt;Components&gt;components
	   */
	void setComponents(Components components);

	/**
	   * sets this OpenAPI's components property to the given components and
	   * returns this instance of OpenAPI
	   *
	   * @param List&lt;Components&gt;components
	   * @return OpenAPI
	   */
	OpenAPI components(Components components);

	/**
	   * Adds the given path item to this OpenAPI's list of paths
	   * 
	   * @param String name
	   * @param PathItem path
	   * @return OpenAPI
	   */
	OpenAPI path(String name, PathItem path);

	/**
	   * Adds the given schema to this OpenAPI's components property.
	   * 
	   * @param String name
	   * @param Schema schema
	   * @return OpenAPI
	   */
	OpenAPI schema(String name, Schema schema);

	/**
	   * Adds the given securityScheme to this OpenAPI's securitySchemes
	   * 
	   * @param String name
	   * @param SecurityScheme securityScheme
	   * @return OpenAPI
	   */
	OpenAPI schemaRequirement(String name, SecurityScheme securityScheme);

}