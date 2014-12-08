
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is equal to an expected value
 * 
 * @author Stewart Bissett
 */
public class EqualToExpectation implements PropertyExpectation {

	private final Object expected;

	public EqualToExpectation(final Object expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final Object actual) {
		return expected.equals(actual);
	}

	@Override
	public String describe() {
		return "is equal to " + expected;
	}

}
