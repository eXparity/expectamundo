
package org.exparity.expectamundo.core.string;

import org.exparity.expectamundo.core.PropertyExpectation;
import java.lang.String;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link String} matches a given regular expression
 * 
 * @author Stewart Bissett
 */
public class HasPattern implements PropertyExpectation<String> {

	private final String pattern;

	public HasPattern(final String pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(final java.lang.String actual) {
		return actual != null && actual.matches(pattern);
	}

	@Override
	public String describe() {
		return "matches regular expression '" + pattern + "'";
	}

}
