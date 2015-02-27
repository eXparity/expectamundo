
package org.exparity.expectamundo.core.collection;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given expected
 * 
 * @author Stewart Bissett
 */
public class Contains<E, T extends Collection<E>> implements PropertyExpectation<T> {

	private E expected;

	public Contains(final E expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(final T actual) {
		return actual != null && actual.contains(expected);
	}

	@Override
	public String describe() {
		return "contains " + expected;
	}

}
