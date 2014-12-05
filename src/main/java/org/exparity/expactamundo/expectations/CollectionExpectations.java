package org.exparity.expactamundo.expectations;

import java.util.Collection;

/**
 * Expectations which can be set on collection objects
 */
public interface CollectionExpectations<E extends Object, T extends Collection<E>> extends ObjectExpectations<T> {

	public void isEmpty();

	public void contains(final E element);
}