
package org.exparity.expectamundo.core;

/**
 * Static global context for holding the {@link Prototyped} which is being defined
 * 
 * @author Stewart Bissett
 */
public class PrototypeMatcherContext {

	private static ThreadLocal<Prototype<?>> CURRENT_STUB = new ThreadLocal<Prototype<?>>();

	public static Prototype<?> currentPrototype() {
		return CURRENT_STUB.get();
	}

	public static void setCurrentPrototype(final Prototype<?> prototype) {
		CURRENT_STUB.set(prototype);
	}

}
