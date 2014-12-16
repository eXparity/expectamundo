
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a property is null
 * 
 * @author Stewart Bissett
 */
@SuppressWarnings("rawtypes")
public class IsNull implements PropertyExpectation {

	@Override
	public boolean matches(final Object actual) {
		return actual == null;
	}

	@Override
	public String describe() {
		return "is null";
	}

}
