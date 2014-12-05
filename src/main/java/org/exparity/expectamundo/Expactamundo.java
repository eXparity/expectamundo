
package org.exparity.expectamundo;

import org.exparity.expectamundo.core.PrototypeExpectationBuilderImpl;
import org.exparity.expectamundo.core.PrototypeFactory;
import org.exparity.expectamundo.core.PrototypeVerifier;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.expectations.ObjectExpectations;
import static org.exparity.expectamundo.core.PrototypeMatcherContext.currentPrototype;

/**
 * @author Stewart Bissett
 */
public class Expactamundo {

	private static PrototypeFactory factory = new PrototypeFactory();

	/**
	 * Prepare a new prototype
	 */
	public static <T> T prototype(final Class<T> type) {
		return factory.createPrototype(type);
	}

	/**
	 * Prepare a new prototype.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T prototype(final TypeReference<T> typeRef) {
		return (T) factory.createPrototype(typeRef.getType());
	}

	/**
	 * Setup an expecation for a property on a {@link Prototype}
	 */
	public static <T> ObjectExpectations<T> expect(final T property) {
		if (currentPrototype() == null) {
			throw new IllegalArgumentException("You can only set an expectation for a property create with Expactamundo.prototype()");
		} else if (currentPrototype().getActiveProperty() == null) {
			throw new IllegalArgumentException("You can only set an expectation for a property create with Expactamundo.prototype()");
		} else {
			return new PrototypeExpectationBuilderImpl<T>(currentPrototype(), currentPrototype().getActiveProperty());
		}
	}

	public static <T> PrototypeVerifier<T> verify(final T test) {
		return new PrototypeVerifier<T>(test);
	}
}
