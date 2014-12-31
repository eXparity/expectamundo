
package org.exparity.expectamundo.core;

import java.util.Arrays;
import java.util.List;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;

/**
 * @author Stewart Bissett
 */
public class PrototypeVerifierTest {

	@Test
	public void canProduceMessageForList() {
		try {
			final List<String> expectedValue = Arrays.asList("AB"), differentValue = Arrays.asList("ABC");
			ListReturnType expected = prototype(ListReturnType.class);
			expect(expected.getValue().get(0)).isEqualTo(expectedValue.get(0));
			new PrototypeVerifier<>(new ListReturnType(differentValue)).matches(expected);
		} catch (AssertionError e) {
			MatcherAssert.assertThat(e.getMessage(),
					Matchers.equalTo("\nExpected a ListReturnType containing properties :\n\tgetValue().get(0) is is equal to AB\nbut actual is a ListReturnType containing properties :\n\tgetValue().get(0) is ABC\n"));
		}
	}

}
