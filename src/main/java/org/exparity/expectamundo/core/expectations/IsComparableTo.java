
package org.exparity.expectamundo.core.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implemenation of a {@link PropertyExpectation} to check if a property is comparable to another
 * 
 * @author Stewart Bissett
 */
public class IsComparableTo<T extends Comparable<T>> implements PropertyExpectation<T> {

	private final T expected;

	public IsComparableTo(final T expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		return expected.compareTo(actual) == 0;
	}

	@Override
	public String describe() {
		return "is comparable to " + expected;
	}

}
