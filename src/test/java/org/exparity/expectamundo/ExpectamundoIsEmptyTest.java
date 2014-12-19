
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link IsEmpty} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsEmptyTest {

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
