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
package org.eclipse.microprofile.openapi.apps.beanvalidation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

public class BeanValidationData {

    @NotEmpty
    private String notEmptyString;

    @NotEmpty
    private List<String> notEmptyList;

    @NotEmpty
    private Map<String, String> notEmptyMap;

    @NotBlank
    private String notBlankString;

    @Size(min = 2, max = 7)
    private String sizedString;

    @Size(min = 1, max = 10)
    private List<String> sizedList;

    @Size(min = 3, max = 5)
    private Map<String, String> sizedMap;

    @DecimalMax("1.5")
    private BigDecimal maxDecimalInclusive;

    @DecimalMax(value = "1.5", inclusive = false)
    private BigDecimal maxDecimalExclusive;

    @DecimalMin("3.25")
    private BigDecimal minDecimalInclusive;

    @DecimalMin(value = "3.25", inclusive = false)
    private BigDecimal minDecimalExclusive;

    @Max(5)
    private int maxInt;

    @Min(7)
    private int minInt;

    @Negative
    private int negativeInt;

    @NegativeOrZero
    private int negativeOrZeroInt;

    @Positive
    private int positiveInt;

    @PositiveOrZero
    private int positiveOrZeroInt;

    @Schema(minLength = 6)
    @NotEmpty
    private String overridenBySchemaAnnotation;

    @NotEmpty(groups = TestGroup.class)
    private String nonDefaultGroup;

    @NotEmpty(groups = {Default.class, TestGroup.class})
    private String defaultAndOtherGroups;

    public String getNotEmptyString() {
        return notEmptyString;
    }

    public void setNotEmptyString(String notEmptyString) {
        this.notEmptyString = notEmptyString;
    }

    public List<String> getNotEmptyList() {
        return notEmptyList;
    }

    public void setNotEmptyList(List<String> notEmptyList) {
        this.notEmptyList = notEmptyList;
    }

    public Map<String, String> getNotEmptyMap() {
        return notEmptyMap;
    }

    public void setNotEmptyMap(Map<String, String> notEmptyMap) {
        this.notEmptyMap = notEmptyMap;
    }

    public String getNotBlankString() {
        return notBlankString;
    }

    public void setNotBlankString(String notBlankString) {
        this.notBlankString = notBlankString;
    }

    public String getSizedString() {
        return sizedString;
    }

    public void setSizedString(String sizedString) {
        this.sizedString = sizedString;
    }

    public List<String> getSizedList() {
        return sizedList;
    }

    public void setSizedList(List<String> sizedList) {
        this.sizedList = sizedList;
    }

    public Map<String, String> getSizedMap() {
        return sizedMap;
    }

    public void setSizedMap(Map<String, String> sizedMap) {
        this.sizedMap = sizedMap;
    }

    public BigDecimal getMaxDecimalInclusive() {
        return maxDecimalInclusive;
    }

    public void setMaxDecimalInclusive(BigDecimal maxDecimalInclusive) {
        this.maxDecimalInclusive = maxDecimalInclusive;
    }

    public BigDecimal getMaxDecimalExclusive() {
        return maxDecimalExclusive;
    }

    public void setMaxDecimalExclusive(BigDecimal maxDecimalExclusive) {
        this.maxDecimalExclusive = maxDecimalExclusive;
    }

    public BigDecimal getMinDecimalInclusive() {
        return minDecimalInclusive;
    }

    public void setMinDecimalInclusive(BigDecimal minDecimalInclusive) {
        this.minDecimalInclusive = minDecimalInclusive;
    }

    public BigDecimal getMinDecimalExclusive() {
        return minDecimalExclusive;
    }

    public void setMinDecimalExclusive(BigDecimal minDecimalExclusive) {
        this.minDecimalExclusive = minDecimalExclusive;
    }

    public int getMaxInt() {
        return maxInt;
    }

    public void setMaxInt(int maxInt) {
        this.maxInt = maxInt;
    }

    public int getMinInt() {
        return minInt;
    }

    public void setMinInt(int minInt) {
        this.minInt = minInt;
    }

    public int getNegativeInt() {
        return negativeInt;
    }

    public void setNegativeInt(int negativeInt) {
        this.negativeInt = negativeInt;
    }

    public int getNegativeOrZeroInt() {
        return negativeOrZeroInt;
    }

    public void setNegativeOrZeroInt(int negativeOrZeroInt) {
        this.negativeOrZeroInt = negativeOrZeroInt;
    }

    public int getPositiveInt() {
        return positiveInt;
    }

    public void setPositiveInt(int positiveInt) {
        this.positiveInt = positiveInt;
    }

    public int getPositiveOrZeroInt() {
        return positiveOrZeroInt;
    }

    public void setPositiveOrZeroInt(int positiveOrZeroInt) {
        this.positiveOrZeroInt = positiveOrZeroInt;
    }

    public String getOverridenBySchemaAnnotation() {
        return overridenBySchemaAnnotation;
    }

    public void setOverridenBySchemaAnnotation(String overridenBySchemaAnnotation) {
        this.overridenBySchemaAnnotation = overridenBySchemaAnnotation;
    }

    public String getNonDefaultGroup() {
        return nonDefaultGroup;
    }

    public void setNonDefaultGroup(String nonDefaultGroup) {
        this.nonDefaultGroup = nonDefaultGroup;
    }

    public String getDefaultAndOtherGroups() {
        return defaultAndOtherGroups;
    }

    public void setDefaultAndOtherGroups(String defaultAndOtherGroups) {
        this.defaultAndOtherGroups = defaultAndOtherGroups;
    }
}
