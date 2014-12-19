
package org.exparity.expectamundo.testutils.types;

import java.util.Date;

/**
 * @author Stewart Bissett
 */
public class DateReturnType {

	private final Date value;

	public DateReturnType(final Date value) {
		this.value = value;
	}

	public Date getValue() {
		return value;
	}
}
