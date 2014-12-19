
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.List;
import org.exparity.expectamundo.core.TypeReference;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.expectThat;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link IsEqualTo} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsInstanceOfTest {

	@Test
	public void canCheckForInstanceOf() {
		List<Integer> expected = prototype(new TypeReference<List<Integer>>() {});
		expect(expected.get(0)).isInstanceOf(Integer.class);
		expectThat(Arrays.asList(1, 2, 3)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotInstanceOf() {
		List<Integer> expected = prototype(new TypeReference<List<Integer>>() {});
		expect(expected.get(0)).isInstanceOf(String.class);
		expectThat(Arrays.asList(1, 2, 3)).matches(expected);
	}
}
