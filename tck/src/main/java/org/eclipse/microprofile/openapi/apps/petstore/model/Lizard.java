/**
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
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

package org.eclipse.microprofile.openapi.apps.petstore.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Lizard")
public class Lizard extends Pet {

    String lizardBreed;

    public String getLizardBreed() {
        return lizardBreed;
    }

    public void setLizardBreed(String lizardBreed) {
        this.lizardBreed = lizardBreed;
    }
}
