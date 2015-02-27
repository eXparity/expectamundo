
package org.exparity.expectamundo.core.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.exparity.expectamundo.core.PropertyExpectation;

/**
 * @author Stewart Bissett
 */
public class IsOneOf<T> implements PropertyExpectation<T> {

	private List<T> possibleValues = new ArrayList<T>();

	public IsOneOf(final Collection<T> possibleValues) {
		this.possibleValues.addAll(possibleValues);
	}

	@Override
	public boolean matches(final T actual) {
		return actual != null && possibleValues.contains(actual);
	}

	@Override
	public String describe() {
		return "is equal to one of " + StringUtils.join(possibleValues, ", ");
	}

}
