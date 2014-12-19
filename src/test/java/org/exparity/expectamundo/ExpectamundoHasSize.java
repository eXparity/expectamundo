
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.List;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.expectations.HasSize} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoHasSize {

	@Test
	public void canCheckForSize() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(1);
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test
	public void canCheckForIsSizeIfNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(0);
		expectThat(new ListReturnType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSizeIfNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(1);
		expectThat(new ListReturnType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSize() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).hasSize(2);
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}
}
