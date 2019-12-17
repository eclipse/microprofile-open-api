/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.tck.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * This filter is a replacement for the original {@code YamlToJsonConverterServlet}.
 * <p>
 * Its only purpose is to convert YAML to JSON so that tests can use JsonPath to inspect the response body.
 *
 * @author Martin Kouba
 */
public class YamlToJsonFilter implements OrderedFilter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        try {
            Response response = ctx.next(requestSpec, responseSpec);

            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(response.getBody().asString(), Object.class);

            ObjectMapper jsonWriter = new ObjectMapper();
            String json = jsonWriter.writeValueAsString(obj);

            ResponseBuilder builder = new ResponseBuilder();
            builder.clone(response);
            builder.setBody(json);
            builder.setContentType(ContentType.JSON);

            return builder.build();
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to convert the request: " + ExceptionUtils.getMessage(e), e);
        }
    }

    @Override
    public int getOrder() {
        return OrderedFilter.HIGHEST_PRECEDENCE;
    }

}
