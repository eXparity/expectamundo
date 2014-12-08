
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class CompareEqualsToExpectaion<T extends Comparable<T>> implements PropertyExpectation {

	private final T expected;

	public CompareEqualsToExpectaion(final T expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final Object actual) {
		return expected.compareTo((T) actual) == 0;
	}

	@Override
	public String describe() {
		return "is comparable to " + expected;
	}

}
