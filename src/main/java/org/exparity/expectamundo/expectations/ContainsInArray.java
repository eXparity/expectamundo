
package org.exparity.expectamundo.expectations;

import java.util.Arrays;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given expected
 * 
 * @author Stewart Bissett
 */
public class ContainsInArray<T> implements PropertyExpectation<T[]> {

	private T expected;

	public ContainsInArray(final T expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T[] actual) {
		return actual != null && Arrays.asList(actual).contains(expected);
	}

	@Override
	public String describe() {
		return "contains " + expected;
	}

}
