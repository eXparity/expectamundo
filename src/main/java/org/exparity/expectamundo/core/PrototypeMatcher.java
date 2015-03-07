
package org.exparity.expectamundo.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Stewart Bissett
 */
public class PrototypeMatcher<T> {

	@SuppressWarnings("unchecked")
	public PrototypeMatchResult<T> compare(final T actual, final T expectation) {
		if (!Prototyped.class.isInstance(expectation)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using Expectamundo.prototype");
		} else {
			List<PrototypeValueDifference> differences = new ArrayList<>();
			for (PrototypeValueMatcher expectedProperty : ((Prototyped<T>) expectation).getExpectations()) {
				Object actualValue = expectedProperty.getPropertyValue(actual);
				if (!expectedProperty.matches(actualValue)) {
					differences.add(new PrototypeValueDifference(expectedProperty.getPropertyPath(), actualValue));
				}
			}
			return new PrototypeMatchResult<T>(actual, expectation, differences);
		}
	}

	public PrototypeListMatchResult<T> contains(final Collection<T> collection, final T expectation) {
		List<PrototypeMatchResult<T>> mismatches = new ArrayList<>();
		for (T entry : collection) {
			PrototypeMatchResult<T> result = compare(entry, expectation);
			if (result.isMismatch()) {
				mismatches.add(result);
			} else {
				return new PrototypeListMatchResult<>(expectation, mismatches);
			}
		}
		return new PrototypeListMatchResult<T>(expectation, mismatches);
	}
}
