
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class PolymorphicSubtype2 extends PolymorphicType {

	private final String value;

	public PolymorphicSubtype2(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
