
package org.exparity.expectamundo.core.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is not null
 * 
 * @author Stewart Bissett
 */
@SuppressWarnings("rawtypes")
public class IsNotNull implements PropertyExpectation {

	@Override
	public boolean matches(final Object actual) {
		return actual != null;
	}

	@Override
	public String describe() {
		return "is not null";
	}

}