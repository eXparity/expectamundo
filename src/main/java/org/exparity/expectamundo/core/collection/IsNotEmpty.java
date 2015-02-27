
package org.exparity.expectamundo.core.collection;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsNotEmpty<E, T extends Collection<E>> implements PropertyExpectation<T> {

	@Override
	public boolean matches(final T actual) {
		return actual != null && !actual.isEmpty();
	}

	@Override
	public String describe() {
		return "is not empty";
	}

}
