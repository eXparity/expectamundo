
package org.exparity.expectamundo.expectations;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given expected
 * 
 * @author Stewart Bissett
 */
public class ContainsExpectation<E, T extends Collection<E>> implements PropertyExpectation<T> {

	private E expected;

	public ContainsExpectation(final E expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		if (actual == null) {
			return false;
		} else {
			return actual.contains(expected);
		}
	}

	@Override
	public String describe() {
		return "contains " + expected;
	}

}
