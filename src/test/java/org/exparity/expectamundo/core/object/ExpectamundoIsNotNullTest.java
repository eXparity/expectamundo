
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsNotNull} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsNotNullTest {

	@Test
	public void canCheckForNotNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNotNull();
		expectThat(new SimpleType(aRandomString())).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNotNull();
		expectThat(new SimpleType(null)).matches(expected);
	}
}
