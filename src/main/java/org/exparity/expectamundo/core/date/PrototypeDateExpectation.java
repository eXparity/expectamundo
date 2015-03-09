
package org.exparity.expectamundo.core.date;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.exparity.expectamundo.core.Prototype;
import org.exparity.expectamundo.core.PrototypeValue;
import org.exparity.expectamundo.core.comparable.PrototypeComparableExpectation;

/**
 * @author Stewart Bissett
 */
public class PrototypeDateExpectation extends PrototypeComparableExpectation<Date> {

	public PrototypeDateExpectation(final Prototype<?> prototype, final PrototypeValue property) {
		super(prototype, property);
	}

	/**
	 * Set an expectation that the property value is within a given interval of the given date. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.aDate()).isWithin(1, TimeUnit.MINUTES, new Date());
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param interval the interval gap between the property value and the date
	 * @param unit the time unit of the interval
	 * @param date the date to test the value against
	 */
	public void isWithin(final int interval, final TimeUnit unit, final Date date) {
		hasExpectation(new IsWithin(interval, unit, date));
	}

	/**
	 * Set an expectation that the property value is on the same day. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.aDate()).isSameDay(new Date());
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param date the date to test the value against
	 */
	public void isSameDay(final Date date) {
		hasExpectation(new IsSameDay(date));
	}

	/**
	 * Set an expectation that the property value is after a given date. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.aDate()).isAfter(new Date());
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param date the date to test the value against
	 */
	public void isAfter(final Date date) {
		hasExpectation(new IsAfter(date));
	}

	/**
	 * Set an expectation that the property value is before a given date. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.aDate()).isBefore(new Date());
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param date the date to test the value against
	 */
	public void isBefore(final Date date) {
		hasExpectation(new IsBefore(date));
	}
}
