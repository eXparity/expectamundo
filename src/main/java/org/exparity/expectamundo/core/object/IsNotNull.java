
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is not null
 * 
 * @author Stewart Bissett
 */
public class IsNotNull<T> implements PropertyExpectation<T> {

	@Override
	public boolean matches(final T actual) {
		return actual != null;
	}

	@Override
	public String describe() {
		return "is not null";
	}

}
