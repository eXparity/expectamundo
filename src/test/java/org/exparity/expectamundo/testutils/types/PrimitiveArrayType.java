
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class PrimitiveArrayType {

	private final byte[] value;

	public PrimitiveArrayType(final byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}
}
