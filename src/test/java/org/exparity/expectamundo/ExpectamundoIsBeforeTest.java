
package org.exparity.expectamundo;

import java.util.Date;
import org.exparity.expectamundo.testutils.types.DateReturnType;
import org.junit.Test;
import static org.exparity.dates.en.FluentDateTime.DEC;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.object.IsBefore} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsBeforeTest {

	@Test
	public void canCheckForIsBefore() {
		Date expectedValue = DEC(19, 2014).at(11, 30);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isBefore(DEC(19, 2014).at(11, 45));
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsNotBefore() {
		Date expectedValue = DEC(19, 2014).at(11, 30);
		DateReturnType expected = prototype(DateReturnType.class);
		expect(expected.getValue()).isBefore(DEC(19, 2014).at(11, 15));
		expectThat(new DateReturnType(expectedValue)).matches(expected);
	}
}
