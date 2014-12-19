
package org.exparity.expectamundo.core.expectations;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given expected
 * 
 * @author Stewart Bissett
 */
public class HasSize<T extends Collection<?>> implements PropertyExpectation<T> {

	private final int expectedSize;

	public HasSize(final int expectedSize) {
		this.expectedSize = expectedSize;
	}

	@Override
	public boolean matches(final T actual) {
		return actual == null ? expectedSize == 0 : actual.size() == expectedSize;
	}

	@Override
	public String describe() {
		return "has size " + expectedSize;
	}

}
