
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link String} matches a given regular expression
 * 
 * @author Stewart Bissett
 */
public class HasLength implements PropertyExpectation<String> {

	private final int length;

	public HasLength(final int length) {
		this.length = length;
	}

	@Override
	public boolean matches(final String actual) {
		return actual == null ? length == 0 : actual.length() == length;
	}

	@Override
	public String describe() {
		return "has length " + length;
	}

}
