
package org.exparity.expectamundo.core.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsEmpty} expectation
 * 
 * @author Stewart Bissett
 */
public class IsEmptyTest {

	@Test
	public void canCheckForIsEmpty() {
		List<String> expectedValue = Collections.emptyList();
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).isEmpty();
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test
	public void canCheckForIsEmptyForNull() {
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).isEmpty();
		expectThat(new ListReturnType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsNotEmpty() {
		List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue()).isEmpty();
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}
}
