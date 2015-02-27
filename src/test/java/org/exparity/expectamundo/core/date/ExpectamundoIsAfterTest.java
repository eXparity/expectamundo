
package org.exparity.expectamundo.core.date;

import java.util.Date;
import org.exparity.expectamundo.testutils.types.DateReturnType;
import org.junit.Test;
import static org.exparity.dates.en.FluentDateTime.DEC;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.object.IsAfter} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsAfterTest {

	@Test
	public void canCheckForIsAfter() {
		Date expectedValue = DEC(19, 2014).at(11, 30);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isAfter(DEC(19, 2014).at(11, 15));
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsNotAfter() {
		Date expectedValue = DEC(19, 2014).at(11, 30);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isAfter(DEC(19, 2014).at(11, 45));
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}
}
