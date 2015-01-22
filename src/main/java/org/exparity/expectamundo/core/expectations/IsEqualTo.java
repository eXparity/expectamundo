
package org.exparity.expectamundo.core.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is equal to an expected value
 * 
 * @author Stewart Bissett
 */
public class IsEqualTo<T> implements PropertyExpectation<T> {

	private final T expected;

	public IsEqualTo(final T expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		return expected == null ? actual == null : expected.equals(actual);
	}

	@Override
	public String describe() {
		return "equal to " + expected;
	}

}
