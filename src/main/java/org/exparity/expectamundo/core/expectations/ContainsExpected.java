
package org.exparity.expectamundo.core.expectations;

import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;
import org.exparity.expectamundo.core.PrototypeListMatchResult;
import org.exparity.expectamundo.core.PrototypeMatcher;
import org.exparity.expectamundo.core.PrototypeMismatchReporter;
import org.exparity.expectamundo.core.Prototyped;

/**
 * Implementation of a {@link PropertyExpectation} to verify if a {@link Collection} contains a given prototype
 * 
 * @author Stewart Bissett
 */
public class ContainsExpected<E, T extends Collection<E>> implements PropertyExpectation<T> {

	private final PrototypeMatcher<E> matcher = new PrototypeMatcher<E>();
	private final PrototypeMismatchReporter reporter = new PrototypeMismatchReporter();
	private E prototype;

	public ContainsExpected(final E prototype) {
		if (!Prototyped.class.isInstance(prototype)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using Expectamundo.prototype");
		} else {
			this.prototype = prototype;
		}
	}

	@Override
	public boolean matches(final T actual) {
		PrototypeListMatchResult<E> result = matcher.contains(actual, prototype);
		if (result.isMismatch()) {
			throw new AssertionError(reporter.describeListMismatch(result));
		}
		return true;
	}

	@Override
	public String describe() {
		return "contains " + prototype;
	}

}
