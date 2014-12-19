
package org.exparity.expectamundo;

import java.util.Arrays;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link Contains} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoContainsTest {

	@Test
	public void canCheckForContains() {
		String expectedValue = aRandomString();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).contains(expectedValue);
		expectThat(new ListReturnType(Arrays.asList(expectedValue))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContains() {
		String expectedValue = aRandomString(), differentValue = aRandomString();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).contains(expectedValue);
		expectThat(new ListReturnType(Arrays.asList(differentValue))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContainsIfNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).contains(aRandomString());
		expectThat(new ListReturnType(null)).matches(expected);
	}

}
