
package org.exparity.expectamundo.core;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * @author Stewart Bissett
 */
public class PrototypePropertyMatcher {

	private final PrototypeProperty property;
	private final Matcher<?> matcher;

	public PrototypePropertyMatcher(final PrototypeProperty property, final Matcher<?> matcher) {
		this.property = property;
		this.matcher = matcher;
	}

	public boolean matches(final Object actual) {
		return matcher.matches(actual);
	}

	public String getPropertyPath() {
		return property.getPath();
	}

	public Object getPropertyValue(final Object actual) {
		return property.getPropertyValue(actual);
	}

	public String getExpectation() {
		StringDescription desc = new StringDescription();
		matcher.describeTo(desc);
		return desc.toString();
	}

	@Override
	public String toString() {
		return "Expectation [" + getPropertyPath() + "=" + getExpectation() + "]";
	}
}
