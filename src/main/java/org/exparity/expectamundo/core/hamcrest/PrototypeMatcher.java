
package org.exparity.expectamundo.core.hamcrest;

import java.util.List;
import org.exparity.expectamundo.core.PrototypeValueMatcher;
import org.exparity.expectamundo.core.Prototyped;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * An implementation of a hamcrest {@link Matcher} for matching a predefined {@link Prototyped}
 * 
 * @author Stewart Bissett
 */
public class PrototypeMatcher<T> extends TypeSafeDiagnosingMatcher<T> {

	/**
	 * Creates a matcher that matches a prototype create using Expectamundo#prototype against an expected instance
	 * <p/>
	 * For example:
	 * 
	 * <pre>
	 * MyObject expected = Expectamundo.prototype(MyObject.class);
	 * Expectamundo.expect(expected.getValue()).hasPattern("A*");
	 * assertThat(new MyObject("ATest), matchesPrototype(expected));
	 * </pre>
	 * 
	 * @param prototype the prototype to use for the match
	 */
	@Factory
	public static <M> PrototypeMatcher<M> matchesPrototype(final M prototype) {
		return new PrototypeMatcher<M>(prototype);
	}

	private final Class<T> rawType;
	private final List<PrototypeValueMatcher> expectations;

	@SuppressWarnings("unchecked")
	public PrototypeMatcher(final T stub) {
		if (!Prototyped.class.isInstance(stub)) {
			throw new IllegalArgumentException("You can only match an expectation for a type created with Expectamundo.prototype()");
		}
		this.rawType = ((Prototyped<T>) stub).getRawType();
		this.expectations = ((Prototyped<T>) stub).getExpectations();
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText("a ").appendText(rawType.getSimpleName());
		if (!expectations.isEmpty()) {
			description.appendText(" containing properties :");
			for (PrototypeValueMatcher expecation : expectations) {
				description.appendText("\n\t").appendText(expecation.getPropertyPath()).appendText(" ").appendText(expecation.getExpectation());
			}
		}
	}

	@Override
	protected boolean matchesSafely(final T actual, final Description mismatchDescription) {
		mismatchDescription.appendText("a ").appendText(actual.getClass().getSimpleName()).appendText(" containing properties :\n");
		boolean matches = true;
		for (PrototypeValueMatcher expectation : expectations) {
			Object actualValue = expectation.getPropertyValue(actual);
			if (!expectation.matches(actualValue)) {
				mismatchDescription.appendText("\t").appendText(expectation.getPropertyPath()).appendText(" ").appendValue(actualValue);
				matches = false;
			}
		}
		return matches;
	}
}