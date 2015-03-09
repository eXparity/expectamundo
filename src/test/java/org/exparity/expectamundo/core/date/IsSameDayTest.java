
package org.exparity.expectamundo.core.date;

import java.util.*;
import org.exparity.expectamundo.testutils.types.*;
import org.junit.*;
import static org.exparity.dates.en.FluentDate.*;
import static org.exparity.expectamundo.Expectamundo.*;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.date.IsSameDay} expectation
 * 
 * @author Stewart Bissett
 */
public class IsSameDayTest {

	@Test
	public void canCheckForSameDay() {
		Date expectedValue = DEC(19, 2014);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isSameDay(DEC(19, 2014));
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForDifferentDay() {
		Date expectedValue = DEC(19, 2014);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isSameDay(expectedValue);
		expectThat(new DateReturnType(DEC(20, 2014))).matches(expected);
	}
}
