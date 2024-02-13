/**
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
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
package org.eclipse.microprofile.openapi.tck.beanvalidation;

import static org.eclipse.microprofile.openapi.tck.Groups.BEAN_VALIDATION;
import static org.eclipse.microprofile.openapi.tck.utils.TCKMatchers.itemOrSingleton;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.eclipse.microprofile.openapi.apps.beanvalidation.BeanValidationApp;
import org.eclipse.microprofile.openapi.tck.AppTestBase;
import org.hamcrest.Matcher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class BeanValidationTest extends AppTestBase {

    @Deployment(testable = false)
    public static WebArchive buildApp() {
        return ShrinkWrap.create(WebArchive.class, "beanValidation.war")
                .addPackage(BeanValidationApp.class.getPackage());
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void notEmptyStringTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "notEmptyString", hasEntry("minLength", 1));
        assertProperty(vr, "notEmptyString", hasEntry(is("type"), itemOrSingleton("string")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void notEmptyListTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "notEmptyList", hasEntry("minItems", 1));
        assertProperty(vr, "notEmptyList", hasEntry(is("type"), itemOrSingleton("array")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void notEmptyMapTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "notEmptyMap", hasEntry("minProperties", 1));
        assertProperty(vr, "notEmptyMap", hasEntry(is("type"), itemOrSingleton("object")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void notBlankStringTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "notBlankString", hasEntry("pattern", "\\S"));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void sizedStringTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "sizedString", hasEntry("minLength", 2));
        assertProperty(vr, "sizedString", hasEntry("maxLength", 7));
        assertProperty(vr, "sizedString", hasEntry(is("type"), itemOrSingleton("string")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void sizedListTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "sizedList", hasEntry("minItems", 1));
        assertProperty(vr, "sizedList", hasEntry("maxItems", 10));
        assertProperty(vr, "sizedList", hasEntry(is("type"), itemOrSingleton("array")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void sizedMapTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "sizedMap", hasEntry("minProperties", 3));
        assertProperty(vr, "sizedMap", hasEntry("maxProperties", 5));
        assertProperty(vr, "sizedMap", hasEntry(is("type"), itemOrSingleton("object")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void maxDecimalInclusiveTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "maxDecimalInclusive", hasEntry("maximum", 1.5f));
        assertProperty(vr, "maxDecimalInclusive", hasEntry(is("type"), itemOrSingleton("number")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void maxDecimalExclusiveTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "maxDecimalExclusive", hasEntry("maximum", 1.5f));
        assertProperty(vr, "maxDecimalExclusive", hasEntry("exclusiveMaximum", true));
        assertProperty(vr, "maxDecimalExclusive", hasEntry(is("type"), itemOrSingleton("number")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void minDecimalInclusiveTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "minDecimalInclusive", hasEntry("minimum", 3.25f));
        assertProperty(vr, "minDecimalInclusive", hasEntry(is("type"), itemOrSingleton("number")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void minDecimalExclusiveTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "minDecimalExclusive", hasEntry("minimum", 3.25f));
        assertProperty(vr, "minDecimalExclusive", hasEntry("exclusiveMinimum", true));
        assertProperty(vr, "minDecimalExclusive", hasEntry(is("type"), itemOrSingleton("number")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void maxIntTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "maxInt", hasEntry("maximum", 5));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void minIntTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "minInt", hasEntry("minimum", 7));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void negativeIntTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "negativeInt", hasEntry("maximum", 0));
        assertProperty(vr, "negativeInt", hasEntry("exclusiveMaximum", true));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void negativeOrZeroIntTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "negativeOrZeroInt", hasEntry("maximum", 0));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void positiveIntTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "positiveInt", hasEntry("minimum", 0));
        assertProperty(vr, "positiveInt", hasEntry("exclusiveMinimum", true));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void positiveOrZeroIntTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "positiveOrZeroInt", hasEntry("minimum", 0));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void overridenBySchemaAnnotationTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "overridenBySchemaAnnotation", hasEntry("minLength", 6));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void nonDefaultGroupTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "nonDefaultGroup", not(hasKey("minLength")));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void defaultAndOtherGroupsTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        assertProperty(vr, "defaultAndOtherGroups", hasEntry("minLength", 1));
    }

    @Test(dataProvider = "formatProvider", groups = BEAN_VALIDATION)
    public void parameterTest(String format) {
        ValidatableResponse vr = callEndpoint(format);
        String schemaPath = dereference(vr, "paths.'/parameter/{test}'.post.parameters[0]", "schema");
        vr.body(schemaPath, hasEntry("maxLength", 6));
        vr.body(schemaPath, hasEntry(is("type"), itemOrSingleton("string")));
    }

    /**
     * Asserts that a property from the test schema matches the given matcher
     *
     * @param vr
     *            the response
     * @param propertyName
     *            the property to test
     * @param matcher
     *            the matcher to assert
     */
    public static void assertProperty(ValidatableResponse vr, String propertyName, Matcher<?> matcher) {
        String schemaPath = dereference(vr, "paths.'/'.post.requestBody", "content.'application/json'.schema");
        String propertyPath = schemaPath + ".properties." + propertyName;
        vr.body(propertyPath, matcher);
    }

}
