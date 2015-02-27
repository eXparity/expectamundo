
package org.exparity.expectamundo.core.string;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to check if a String property is equal to another String property regardless of case
 * 
 * @author Stewart Bissett
 */
public class IsEqualToIgnoreCase implements PropertyExpectation<String> {

	private final String expected;

	public IsEqualToIgnoreCase(final String expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final String actual) {
		return expected == null ? actual == null : expected.equalsIgnoreCase(actual);
	}

	@Override
	public String describe() {
		return "equal to " + expected;
	}

}
