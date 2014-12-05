
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class ToStringType {

	private final String value;

	public ToStringType(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
