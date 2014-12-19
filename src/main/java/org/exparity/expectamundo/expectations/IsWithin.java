
package org.exparity.expectamundo.expectations;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsWithin implements PropertyExpectation<Date> {

	private int interval;
	private TimeUnit unit;
	private Date expected;

	public IsWithin(final int interval, final TimeUnit unit, final Date expected) {
		this.interval = interval;
		this.unit = unit;
		this.expected = expected;
	}

	@Override
	public boolean matches(final Date actual) {
		long differenceInMillis = Math.abs(expected.getTime() - actual.getTime());
		if (differenceInMillis > TimeUnit.MILLISECONDS.convert(interval, unit)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String describe() {
		return "a expected within " + interval + " " + unit.name().toLowerCase() + " of " + expected;
	}
}
