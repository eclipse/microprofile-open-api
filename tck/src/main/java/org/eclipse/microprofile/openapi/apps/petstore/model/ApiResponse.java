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

import jakarta.xml.bind.annotation.XmlTransient;

@jakarta.xml.bind.annotation.XmlRootElement
public class ApiResponse {
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 4;
    public static final int TOO_BUSY = 5;

    private int code;
    private String type;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        switch (code) {
            case ERROR :
                setType("error");
                break;
            case WARNING :
                setType("warning");
                break;
            case INFO :
                setType("info");
                break;
            case OK :
                setType("ok");
                break;
            case TOO_BUSY :
                setType("too busy");
                break;
            default :
                setType("unknown");
                break;
        }
        this.message = message;
    }

    @XmlTransient
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
