
package org.exparity.expectamundo.expectations;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given expected
 * 
 * @author Stewart Bissett
 */
public class ContainsExpectation<E> implements PropertyExpectation {

	private E expected;

	public ContainsExpectation(final E expected) {
		this.expected = expected;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean matches(final Object actual) {
		if (actual instanceof Collection) {
			return ((Collection) actual).contains(expected);
		} else if (actual == null) {
			return false;
		} else {
			throw new IllegalArgumentException("Expected an instance of '" + Collection.class.getName() + "', but was '" + actual.getClass() + "'");
		}
	}

	@Override
	public String describe() {
		return "contains " + expected;
	}

}
