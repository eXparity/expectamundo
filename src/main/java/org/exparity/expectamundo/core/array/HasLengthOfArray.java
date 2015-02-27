
package org.exparity.expectamundo.core.array;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given expected
 * 
 * @author Stewart Bissett
 */
public class HasLengthOfArray<T> implements PropertyExpectation<T[]> {

	private final int expectedSize;

	public HasLengthOfArray(final int expectedSize) {
		this.expectedSize = expectedSize;
	}

	@Override
	public boolean matches(final T[] actual) {
		return actual == null ? expectedSize == 0 : actual.length == expectedSize;
	}

	@Override
	public String describe() {
		return "has length " + expectedSize;
	}

}
