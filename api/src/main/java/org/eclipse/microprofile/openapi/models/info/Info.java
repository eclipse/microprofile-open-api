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

package org.eclipse.microprofile.openapi.models.info;

import org.eclipse.microprofile.openapi.models.Constructible;
import org.eclipse.microprofile.openapi.models.Extensible;

/**
*
* @see "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.md#infoObject"
*/
public interface Info extends Constructible, Extensible {

	/**
	   * returns the title property from a Info instance.
	   *
	   * @return String title
	   **/

	String getTitle();

	/**
	   * sets this Info's title property to the given title.
	   *
	   * @param String title
	   */
	void setTitle(String title);

	/**
	   * sets this Info's title property to the given title and
	   * returns this instance of Info
	   *
	   * @param String title
	   * @return Info
	   */
	Info title(String title);

	/**
	   * returns the description property from a Info instance.
	   *
	   * @return String description
	   **/

	String getDescription();

	/**
	   * sets this Info's description property to the given description.
	   *
	   * @param String description
	   */
	void setDescription(String description);

	/**
	   * sets this Info's description property to the given description and
	   * returns this instance of Info
	   *
	   * @param String description
	   * @return Info
	   */
	Info description(String description);

	/**
	   * returns the termsOfService property from a Info instance.
	   *
	   * @return String termsOfService
	   **/

	String getTermsOfService();

	/**
	   * sets this Info's termsOfService property to the given termsOfService.
	   *
	   * @param String termsOfService
	   */
	void setTermsOfService(String termsOfService);

	/**
	   * sets this Info's termsOfService property to the given termsOfService and
	   * returns this instance of Info
	   *
	   * @param String termsOfService
	   * @return Info
	   */
	Info termsOfService(String termsOfService);

	/**
	   * returns the contact property from a Info instance.
	   *
	   * @return Contact contact
	   **/

	Contact getContact();

	/**
	   * sets this Info's contact property to the given contact.
	   *
	   * @param Contact contact
	   */
	void setContact(Contact contact);

	/**
	   * sets this Info's contact property to the given contact and
	   * returns this instance of Info
	   *
	   * @param Contact contact
	   * @return Info
	   */
	Info contact(Contact contact);

	/**
	   * returns the license property from a Info instance.
	   *
	   * @return License license
	   **/

	License getLicense();

	/**
	   * sets this Info's license property to the given license.
	   *
	   * @param License license
	   */
	void setLicense(License license);

	/**
	   * sets this Info's license property to the given license and
	   * returns this instance of Info
	   *
	   * @param License license
	   * @return Info
	   */
	Info license(License license);

	/**
	   * returns the version property from a Info instance.
	   *
	   * @return String version
	   **/

	String getVersion();

	/**
	   * sets this Info's version property to the given version.
	   *
	   * @param String version
	   */
	void setVersion(String version);

	/**
	   * sets this Info's version property to the given version and
	   * returns this instance of Info
	   *
	   * @param String version
	   * @return Info
	   */
	Info version(String version);

}