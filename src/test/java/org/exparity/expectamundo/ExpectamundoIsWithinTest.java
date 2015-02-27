
package org.exparity.expectamundo;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.exparity.expectamundo.testutils.types.DateReturnType;
import org.junit.Test;
import static org.exparity.dates.en.FluentDateTime.DEC;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.object.IsWithin} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsWithinTest {

	@Test
	public void canCheckForIsWithin() {
		Date expectedValue = DEC(19, 2014).at(11, 30), withinValue = DEC(19, 2014).at(11, 45);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isWithin(20, TimeUnit.MINUTES, withinValue);
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotIsWithin() {
		Date expectedValue = DEC(19, 2014).at(11, 30), withinValue = DEC(19, 2014).at(11, 45);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isWithin(10, TimeUnit.MINUTES, withinValue);
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}
}
