
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.exparity.expectamundo.testutils.types.ParameterizedListReturnType;
import org.exparity.expectamundo.testutils.types.StringListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsEmpty} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsNotEmptyTest {

	@Test
	public void canCheckForIsNotEmpty() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test
	public void canCheckForIsNotEmptyOnGenericSubclass() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		StringListReturnType expected = prototype(StringListReturnType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new StringListReturnType(expectedValue)).matches(expected);
	}

	@Test
	public void canCheckForIsNotEmptyOnGenericCollection() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ParameterizedListReturnType<String> expected = prototype(new TypeReference<ParameterizedListReturnType<String>>() {});
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ParameterizedListReturnType<String>(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsEmpty() {
		List<String> expectedValue = Collections.emptyList();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsEmptyForNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ListReturnType(null)).matches(expected);
	}
}
