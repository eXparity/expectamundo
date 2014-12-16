
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class PolymorphicSubtype1 extends PolymorphicType {

	private final int value;

	public PolymorphicSubtype1(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
