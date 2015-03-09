
package org.exparity.expectamundo.core.date;

import java.text.*;
import java.util.*;
import org.exparity.expectamundo.core.*;

/**
 * @author Stewart Bissett
 */
public class IsSameDay implements PropertyExpectation<Date> {

	private static final String DATE_FORMAT = "dd MMM yyyy";
	private final String date;

	public IsSameDay(final Date expected) {
		this.date = new SimpleDateFormat(DATE_FORMAT).format(expected);
	}

	@Override
	public boolean matches(final Date actual) {
		return date.equals(new SimpleDateFormat(DATE_FORMAT).format(actual));
	}

	@Override
	public String describe() {
		return "the same day as " + date;
	}
}
