
package org.exparity.expectamundo.core.collection;

import java.util.Arrays;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.testutils.types.ParameterizedListReturnType;
import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link Contains} expectation
 * 
 * @author Stewart Bissett
 */
public class ContainsExpectedTest {

	@Test
	public void canCheckForContains() {
		String expectedString = aRandomString(), anotherString = expectedString + aRandomString();
		SimpleType expectedValue = prototype(SimpleType.class);
		expect(expectedValue.getValue()).isEqualTo(expectedString);
		ParameterizedListReturnType<SimpleType> expected = prototype(new TypeReference<ParameterizedListReturnType<SimpleType>>() {});
		expect(expected.getValue()).containsExpected(expectedValue);
		expectThat(new ParameterizedListReturnType<SimpleType>(Arrays.asList(new SimpleType(expectedString), new SimpleType(anotherString)))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContains() {
		String expectedString = aRandomString(), anotherString = expectedString + aRandomString();
		SimpleType expectedValue = prototype(SimpleType.class);
		expect(expectedValue.getValue()).isEqualTo(expectedString);
		ParameterizedListReturnType<SimpleType> expected = prototype(new TypeReference<ParameterizedListReturnType<SimpleType>>() {});
		expect(expected.getValue()).containsExpected(expectedValue);
		expectThat(new ParameterizedListReturnType<SimpleType>(Arrays.asList(new SimpleType(anotherString)))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContainsIfNull() {
		String expectedString = aRandomString();
		SimpleType expectedValue = prototype(SimpleType.class);
		expect(expectedValue.getValue()).isEqualTo(expectedString);
		ParameterizedListReturnType<SimpleType> expected = prototype(new TypeReference<ParameterizedListReturnType<SimpleType>>() {});
		expect(expected.getValue()).containsExpected(expectedValue);
		expectThat(new ParameterizedListReturnType<SimpleType>(null)).matches(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailWithExceptionIfNotExpection() {
		String expectedString = aRandomString(), anotherString = expectedString + aRandomString();
		SimpleType expectedValue = new SimpleType(expectedString);
		expect(expectedValue.getValue()).isEqualTo(expectedString);
		ParameterizedListReturnType<SimpleType> expected = prototype(new TypeReference<ParameterizedListReturnType<SimpleType>>() {});
		expect(expected.getValue()).containsExpected(expectedValue);
		expectThat(new ParameterizedListReturnType<SimpleType>(Arrays.asList(new SimpleType(expectedString), new SimpleType(anotherString)))).matches(expected);
	}

}
