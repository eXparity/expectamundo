
package org.exparity.expectamundo.core.collection;

import java.util.Arrays;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.exparity.expectamundo.testutils.types.ParameterizedListReturnType;
import org.exparity.expectamundo.testutils.types.SimpleType;
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
public class ContainsTest {

	@Test
	public void canCheckForContains() {
		String expectedValue = aRandomString();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).contains(expectedValue);
		expectThat(new ListReturnType(Arrays.asList(expectedValue))).matches(expected);
	}

	@Test
	public void canCheckForContainsPrototype() {
		String expectedString = aRandomString(), anotherString = expectedString + aRandomString();
		SimpleType expectedValue = prototype(SimpleType.class);
		expect(expectedValue.getValue()).isEqualTo(expectedString);
		ParameterizedListReturnType<SimpleType> expected = prototype(new TypeReference<ParameterizedListReturnType<SimpleType>>() {});
		expect(expected.getValue()).contains(expectedValue);
		expectThat(new ParameterizedListReturnType<SimpleType>(Arrays.asList(new SimpleType(expectedString), new SimpleType(anotherString)))).matches(expected);
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
