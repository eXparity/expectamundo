
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property type is an instance of a given type
 * 
 * @author Stewart Bissett
 */
public class InstanceOfExpectation<T> implements PropertyExpectation<T> {

	private final Class<T> expected;

	public InstanceOfExpectation(final Class<T> expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		return expected.isInstance(actual);
	}

	@Override
	public String describe() {
		return "is instance of " + expected;
	}

}
