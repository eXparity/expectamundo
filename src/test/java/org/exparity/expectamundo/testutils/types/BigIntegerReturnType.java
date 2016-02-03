package org.exparity.expectamundo.testutils.types;

import java.math.BigInteger;

public class BigIntegerReturnType {

	private BigInteger value;

	public BigIntegerReturnType(final BigInteger value) {
		this.value = value;
	}

	public BigIntegerReturnType() {
		// TODO Auto-generated constructor stub
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(final BigInteger value) {
		this.value = value;
	}
}