
package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class GraphType {

	private final String value;
	private final SimpleType child;

	public GraphType(final String value, final SimpleType child) {
		this.value = value;
		this.child = child;
	}

	public String getValue() {
		return value;
	}

	public SimpleType getChild() {
		return child;
	}
}
