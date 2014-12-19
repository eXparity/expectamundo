
package org.exparity.expectamundo.core.expectations;

import java.util.Date;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsAfter implements PropertyExpectation<Date> {

	private Date expected;

	public IsAfter(final Date expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final Date actual) {
		return actual.compareTo(expected) > 0;
	}

	@Override
	public String describe() {
		return "a date after " + expected;
	}
}
