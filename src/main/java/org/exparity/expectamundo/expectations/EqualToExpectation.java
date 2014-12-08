
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is equal to an expected value
 * 
 * @author Stewart Bissett
 */
public class EqualToExpectation<T> implements PropertyExpectation<T> {

	private final T expected;

	public EqualToExpectation(final T expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		return expected.equals(actual);
	}

	@Override
	public String describe() {
		return "is equal to " + expected;
	}

}
