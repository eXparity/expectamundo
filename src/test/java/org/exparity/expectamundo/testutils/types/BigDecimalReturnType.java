package org.exparity.expectamundo.testutils.types;

import java.math.BigDecimal;

public class BigDecimalReturnType {
	
	private BigDecimal value;

	public BigDecimalReturnType(final BigDecimal value) {
		this.value = value;
	}

	public BigDecimalReturnType() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(final BigDecimal value) {
		this.value = value;
	}
}