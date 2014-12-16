
package org.exparity.expectamundo.expectations;

import org.exparity.expectamundo.core.PropertyExpectation;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * Implementation of a {@link PropertyExpectation} which checks the property against and expected hamcrest {@link Matcher}
 * 
 * @author Stewart Bissett
 */
public class Matches<T> implements PropertyExpectation<T> {

	private final Matcher<T> matcher;

	public Matches(final Matcher<T> matcher) {
		this.matcher = matcher;
	}

	@Override
	public boolean matches(final Object actual) {
		return matcher.matches(actual);
	}

	@Override
	public String describe() {
		Description description = new StringDescription();
		matcher.describeTo(description);
		return description.toString();
	}

}
