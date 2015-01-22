
package org.exparity.expectamundo.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.exparity.expectamundo.core.PrototypeMatcher.PrototypeMatchResult;

/**
 * @author Stewart Bissett
 */
public class PrototypeListVerifier<E, T extends Collection<E>> extends PrototypeVerifier<T> {

	private final PrototypeMatcher<E> matcher = new PrototypeMatcher<E>();
	private final PrototypeMismatchReporter descriptor = new PrototypeMismatchReporter();
	private final Collection<E> actual;

	public PrototypeListVerifier(final T actual) {
		super(actual);
		this.actual = actual;
	}

	/**
	 * Check the collection contains and object which matches the expectations set on the prototype
	 * @param prototype the prototype object containing the expectations
	 */
	@SuppressWarnings("unchecked")
	public void contains(final E prototype) {
		if (!Prototyped.class.isInstance(prototype)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using Expectamundo.prototype");
		} else {
			List<PrototypeMatchResult<E>> mismatches = new ArrayList<>();
			for (E entry : actual) {
				PrototypeMatchResult<E> result = matcher.compare(entry, prototype);
				if (result.isMismatch()) {
					mismatches.add(result);
				} else {
					return;
				}
			}
			throw new AssertionError(descriptor.describeListMismatch((Prototyped<E>) prototype, mismatches));
		}
	}
}
