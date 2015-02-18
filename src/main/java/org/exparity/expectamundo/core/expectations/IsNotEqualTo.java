
package org.exparity.expectamundo.core.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is equal to an expected value
 * 
 * @author Stewart Bissett
 */
public class IsNotEqualTo<T> implements PropertyExpectation<T> {

	private final T expected;

	public IsNotEqualTo(final T expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		return expected == null ? actual != null : !expected.equals(actual);
	}

	@Override
	public String describe() {
		return "not equal to " + expected;
	}

}
