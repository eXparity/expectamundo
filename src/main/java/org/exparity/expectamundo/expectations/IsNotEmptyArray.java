
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsNotEmptyArray<T> implements PropertyExpectation<T[]> {

	@Override
	public boolean matches(final T[] actual) {
		return actual != null && actual.length > 0;
	}

	@Override
	public String describe() {
		return "is not empty";
	}

}
