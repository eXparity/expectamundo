
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.List;
import org.exparity.expectamundo.core.TypeReference;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link EqualToExpectation} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsInstanceOfTest {

	@Test
	public void canCheckForInstanceOf() {
		List<Integer> expected = prototype(new TypeReference<List<Integer>>() {});
		expect(expected.get(0)).isInstanceOf(Integer.class);
		verify(Arrays.asList(1, 2, 3)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotInstanceOf() {
		List<Integer> expected = prototype(new TypeReference<List<Integer>>() {});
		expect(expected.get(0)).isInstanceOf(String.class);
		verify(Arrays.asList(1, 2, 3)).matches(expected);
	}
}
