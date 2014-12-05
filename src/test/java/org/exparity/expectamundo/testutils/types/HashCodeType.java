
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class HashCodeType {

	private final int value;

	public HashCodeType(final int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
