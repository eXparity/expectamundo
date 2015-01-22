
package org.exparity.expectamundo.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stewart Bissett
 */
public class PrototypeMatcher<T> {

	public static class PrototypePropertyDifference {

		private final String path;
		private final Object value;

		public PrototypePropertyDifference(final String path, final Object value) {
			this.path = path;
			this.value = value;
		}

		public Object getValue() {
			return value;
		}

		public String getPath() {
			return path;
		}

	}

	public static class PrototypeMatchResult<T> {

		private final List<PrototypePropertyDifference> differences;
		private final T actual, expected;

		public PrototypeMatchResult(final T actual, final T expected, final List<PrototypePropertyDifference> differences) {
			this.actual = actual;
			this.expected = expected;
			this.differences = differences;
		}

		public boolean isMismatch() {
			return !differences.isEmpty();
		}

		@SuppressWarnings("unchecked")
		public Class<?> getExpectedType() {
			return ((Prototyped<T>) expected).getRawType();
		}

		public Class<?> getActualType() {
			return actual.getClass();
		}

		public List<PrototypePropertyDifference> getDifferences() {
			return differences;
		}

		@SuppressWarnings("unchecked")
		public List<PrototypePropertyMatcher> getExpectations() {
			return ((Prototyped<T>) expected).getExpectations();
		}

	}

	@SuppressWarnings("unchecked")
	public PrototypeMatchResult<T> compare(final T actual, final T expectation) {
		if (!Prototyped.class.isInstance(expectation)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using Expectamundo.prototype");
		} else {
			List<PrototypePropertyDifference> differences = new ArrayList<>();
			for (PrototypePropertyMatcher expectedProperty : ((Prototyped<T>) expectation).getExpectations()) {
				Object actualValue = expectedProperty.getPropertyValue(actual);
				if (!expectedProperty.matches(actualValue)) {
					differences.add(new PrototypePropertyDifference(expectedProperty.getPropertyPath(), actualValue));
				}
			}
			return new PrototypeMatchResult<T>(actual, expectation, differences);
		}
	}
}
