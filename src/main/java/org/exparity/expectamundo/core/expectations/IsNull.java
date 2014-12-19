
package org.exparity.expectamundo.core.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is null
 * 
 * @author Stewart Bissett
 */
public class IsNull<T> implements PropertyExpectation<T> {

	@Override
	public boolean matches(final T actual) {
		return actual == null;
	}

	@Override
	public String describe() {
		return "is null";
	}

}
