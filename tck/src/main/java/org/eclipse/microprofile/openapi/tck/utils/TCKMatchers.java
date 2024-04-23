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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.comparator.ComparatorMatcherBuilder.comparedBy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

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
     * Creates a matcher of {@link Comparable Comparable&lt;Number&gt;} that matches when the examined number is equal
     * to the specified value, as reported by the <code>compareTo</code> method of the {@link BigDecimal}s created by
     * passing both Number values' {@link Number#doubleValue() doubleValue} to {@link BigDecimal#valueOf(double)}.
     * <p>
     * For example:
     *
     * <pre>
     * assertThat(1, comparesEqualToNumber(1))
     * </pre>
     *
     * @param expected
     *            the value which, when passed to the compareTo method of the examined object following conversion to
     *            BigDecimal, should return zero
     * @return a matcher to test the equality of the examined Number
     */
    public static Matcher<Number> comparesEqualToNumber(Number expected) {
        return comparedBy(NUMERIC_COMPARATOR).comparesEqualTo(expected);
    }

    /**
     * Creates a matcher which matches an item or a {@link Collection} containing just that item
     *
     * @param itemMatcher
     *            the matcher for the item
     * @return the matcher
     */
    public static Matcher<Object> itemOrSingleton(Matcher<?> itemMatcher) {
        return new ItemOrSingletonMatcher(itemMatcher);
    }

    /**
     * Creates a matcher which matches an item or a {@link Collection} containing just that item
     *
     * @param item
     *            the item
     * @return the matcher
     */
    public static Matcher<Object> itemOrSingleton(Object item) {
        return itemOrSingleton(equalTo(item));
    }

    /**
     * Creates a matcher which matches numbers based on their numeric value without considering their type.
     * <p>
     * Both the expected and actual value are converted to {@link BigDecimal} and compared using
     * {@link BigDecimal#compareTo(BigDecimal) compareTo}.
     *
     * @param number
     *            the expected number
     * @return the matcher
     */
    public static Matcher<Number> number(Number number) {
        return new NumericEqualityMatcher(number);
    }

    public static class ItemOrSingletonMatcher extends TypeSafeDiagnosingMatcher<Object> {

        private Matcher<?> baseMatcher;

        public ItemOrSingletonMatcher(Matcher<?> baseMatcher) {
            super(Object.class);
            this.baseMatcher = baseMatcher;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("An item or singleton list containing ").appendDescriptionOf(baseMatcher);
        }

        @Override
        protected boolean matchesSafely(Object item, Description mismatchDescription) {
            if (item instanceof Collection<?>) {
                Collection<?> collection = (Collection<?>) item;
                if (collection.size() != 1) {
                    mismatchDescription.appendText("object is a collection of size ").appendValue(collection.size());
                    return false;
                }
                item = collection.iterator().next();
            }

            boolean result = baseMatcher.matches(item);
            if (!result) {
                baseMatcher.describeMismatch(item, mismatchDescription);
            }
            return result;
        }
    }

    public static class NumericEqualityMatcher extends TypeSafeDiagnosingMatcher<Number> {

        private BigDecimal expected;

        public NumericEqualityMatcher(Number expected) {
            this.expected = toBigDecimal(expected);
        }

        @Override
        public void describeTo(Description desc) {
            desc.appendText("A number equal to ").appendValue(expected);
        }

        @Override
        protected boolean matchesSafely(Number item, Description mismatchDescription) {
            BigDecimal actual = toBigDecimal(item);
            mismatchDescription.appendText("was: ").appendValue(item);
            return expected.compareTo(actual) == 0;
        }

        private static BigDecimal toBigDecimal(Number number) {
            if (number instanceof Integer) {
                return new BigDecimal((Integer) number);
            } else if (number instanceof Short) {
                return new BigDecimal((Short) number);
            } else if (number instanceof Long) {
                return new BigDecimal((Long) number);
            } else if (number instanceof Float) {
                return new BigDecimal((Float) number);
            } else if (number instanceof Double) {
                return new BigDecimal((Double) number);
            } else if (number instanceof BigInteger) {
                return new BigDecimal((BigInteger) number);
            } else {
                return new BigDecimal(number.doubleValue());
            }
        }

    }
}
