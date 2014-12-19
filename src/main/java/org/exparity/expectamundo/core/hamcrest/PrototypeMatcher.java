
package org.exparity.expectamundo.core.hamcrest;

import java.util.List;
import org.exparity.expectamundo.core.PrototypePropertyMatcher;
import org.exparity.expectamundo.core.Prototyped;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * An implementation of a hamcrest {@link Matcher} for matching a predefined {@link Prototyped}
 * 
 * @author Stewart Bissett
 */
public class PrototypeMatcher<T> extends TypeSafeDiagnosingMatcher<T> {

	private final Prototyped<T> expected;

	public PrototypeMatcher(final Prototyped<T> stub) {
		this.expected = stub;
	}

	@Override
	public void describeTo(final Description description) {
		describeTo(description, expected.getRawType(), expected.getExpectations());
	}

	private void describeTo(final Description description, final Class<?> rawType, final List<PrototypePropertyMatcher> expectations) {
		description.appendText("a ").appendText(rawType.getSimpleName());
		if (!expectations.isEmpty()) {
			description.appendText(" containing properties :");
			for (PrototypePropertyMatcher expecation : expectations) {
				description.appendText("\n\t").appendText(expecation.getPropertyPath()).appendText(" ").appendText(expecation.getExpectation());
			}
		}
	}

	@Override
	protected boolean matchesSafely(final T actual, final Description mismatchDescription) {
		mismatchDescription.appendText("a ").appendText(actual.getClass().getSimpleName()).appendText(" containing properties :\n");
		boolean matches = true;
		for (PrototypePropertyMatcher expectation : expected.getExpectations()) {
			Object actualValue = expectation.getPropertyValue(actual);
			if (!expectation.matches(actualValue)) {
				mismatchDescription.appendText("\t").appendText(expectation.getPropertyPath()).appendText(" ").appendValue(actualValue);
				matches = false;
			}
		}
		return matches;
	}
}