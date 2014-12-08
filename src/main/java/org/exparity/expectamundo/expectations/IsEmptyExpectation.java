package org.exparity.expectamundo.expectations;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;


/**
 * @author Stewart Bissett
 */
public class IsEmptyExpectation implements PropertyExpectation {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean matches(final Object actual) {
		if (actual instanceof Collection) {
			return ((Collection) actual).isEmpty();
		} else if (actual == null) {
			return true;
		} else {
			throw new IllegalArgumentException("Expected an instance of '" + Collection.class.getName() + "', but was '" + actual.getClass() + "'");
		}
	}

	@Override
	public String describe() {
		return "is empty";
	}

}
