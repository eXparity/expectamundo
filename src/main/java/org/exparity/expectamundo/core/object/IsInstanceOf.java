
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property type is an instance of a given type
 * 
 * @author Stewart Bissett
 */
public class IsInstanceOf<T> implements PropertyExpectation<T> {

	private final Class<? extends T> expected;

	public IsInstanceOf(final Class<? extends T> expected) {
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
