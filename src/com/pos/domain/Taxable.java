package com.pos.domain;

import com.pos.misc.Constant;

public class Taxable {
	private double tax = Constant.TAX_PRECENTAGE;

	public double getTax() {
		return (tax / 100.0);
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
}
