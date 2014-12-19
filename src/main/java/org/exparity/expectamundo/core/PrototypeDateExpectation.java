
package org.exparity.expectamundo.core;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.exparity.expectamundo.expectations.IsAfter;
import org.exparity.expectamundo.expectations.IsBefore;
import org.exparity.expectamundo.expectations.IsWithin;

/**
 * @author Stewart Bissett
 */
public class PrototypeDateExpectation extends PrototypeComparableExpectation<Date> {

	public PrototypeDateExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void isWithin(final int interval, final TimeUnit unit, final Date date) {
		hasExpectation(new IsWithin(interval, unit, date));
	}

	public void isAfter(final Date date) {
		hasExpectation(new IsAfter(date));
	}

	public void isBefore(final Date date) {
		hasExpectation(new IsBefore(date));
	}
}
