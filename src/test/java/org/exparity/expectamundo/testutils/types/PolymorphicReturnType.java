
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class PolymorphicReturnType {

	private final PolymorphicType value;

	public PolymorphicReturnType(final PolymorphicType value) {
		this.value = value;
	}

	public PolymorphicType getValue() {
		return value;
	}
}
