package com.pos.domain;

public class Taxable {
	private double tax = 15.0;

	public double getTax() {
		return (tax / 100.0);
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
}
