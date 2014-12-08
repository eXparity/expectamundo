
package org.exparity.expectamundo;

import java.util.Arrays;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link ContainsExpectation} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoContainsTest {

	@Test
	public void canCheckForContains() {
		String expectedValue = aRandomString();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).contains(expectedValue);
		verify(new ListReturnType(Arrays.asList(expectedValue))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContains() {
		String expectedValue = aRandomString(), differentValue = aRandomString();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).contains(expectedValue);
		verify(new ListReturnType(Arrays.asList(differentValue))).matches(expected);
	}
}
