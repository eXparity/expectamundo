
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link Matches} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoMatchesTest {

	@Test
	public void canCheckForMatching() {
		String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).matches(Matchers.equalTo(expectedValue));
		expectThat(new SimpleType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotMatching() {
		String expectedValue = aRandomString(), differentValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).matches(Matchers.equalTo(expectedValue));
		expectThat(new SimpleType(differentValue)).matches(expected);
	}

}
