
package org.exparity.expectamundo.expectations;

import java.util.Date;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsBefore implements PropertyExpectation<Date> {

	private Date expected;

	public IsBefore(final Date expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final Date actual) {
		return actual.compareTo(expected) < 0;
	}

	@Override
	public String describe() {
		return "a date before " + expected;
	}
}
