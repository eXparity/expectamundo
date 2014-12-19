
package org.exparity.expectamundo.core.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsEmptyArray<T> implements PropertyExpectation<T[]> {

	@Override
	public boolean matches(final T[] actual) {
		return actual == null || actual.length == 0;
	}

	@Override
	public String describe() {
		return "is empty";
	}

}
