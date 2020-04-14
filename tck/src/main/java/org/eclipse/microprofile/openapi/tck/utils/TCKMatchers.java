/**
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eclipse.microprofile.openapi.tck.utils;

import static org.hamcrest.comparator.ComparatorMatcherBuilder.comparedBy;

import java.math.BigDecimal;
import java.util.Comparator;

import org.hamcrest.Matcher;

public final class TCKMatchers {

    private TCKMatchers() {
    }

    /**
     * Compares two numbers as BigDecimals
     */
    private static final Comparator<Number> NUMERIC_COMPARATOR = (value1, value2) -> {
        final BigDecimal decimal1 = BigDecimal.valueOf(value1.doubleValue());
        final BigDecimal decimal2 = BigDecimal.valueOf(value2.doubleValue());
        return decimal1.compareTo(decimal2);
    };

    /**
     * Creates a matcher of {@link Comparable Comparable&lt;Number&gt;} that
     * matches when the examined number is equal to the specified value, as
     * reported by the <code>compareTo</code> method of the {@link BigDecimal}s
     * created by passing both Number values' {@link Number#doubleValue()
     * doubleValue} to {@link BigDecimal#valueOf(double)}.
     * <p>
     * For example:
     *
     * <pre>
     * assertThat(1, comparesEqualToNumber(1))
     * </pre>
     *
     * @param expected
     *            the value which, when passed to the compareTo method of the
     *            examined object following conversion to BigDecimal, should
     *            return zero
     * @return a matcher to test the equality of the examined Number
     */
    public static Matcher<Number> comparesEqualToNumber(Number expected) {
        return comparedBy(NUMERIC_COMPARATOR).comparesEqualTo(expected);
    }
}
