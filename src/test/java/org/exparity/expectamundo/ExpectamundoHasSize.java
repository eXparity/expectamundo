
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.List;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link org.exparity.expectamundo.expectations.HasSize} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoHasSize {

	@Test
	public void canCheckForSize() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(1);
		verify(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test
	public void canCheckForIsSizeIfNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(0);
		verify(new ListReturnType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSizeIfNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(1);
		verify(new ListReturnType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSize() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(2);
		verify(new ListReturnType(expectedValue)).matches(expected);
	}
}
