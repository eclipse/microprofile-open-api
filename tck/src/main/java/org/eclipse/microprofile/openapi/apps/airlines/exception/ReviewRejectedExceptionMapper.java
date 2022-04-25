/**
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.apps.airlines.exception;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.apps.airlines.exception.ReviewRejectedExceptionMapper.RejectionResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;

@APIResponse(responseCode = "400", description = "The review was rejected", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RejectionResponse.class)))
public class ReviewRejectedExceptionMapper implements ExceptionMapper<ReviewRejectedException> {

    @Override
    public Response toResponse(ReviewRejectedException exception) {
        RejectionResponse response = new RejectionResponse();
        response.setReason(exception.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(response).build();
    }

    public static class RejectionResponse {
        @Schema(description = "The reason the review was rejected")
        private String reason;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

}
