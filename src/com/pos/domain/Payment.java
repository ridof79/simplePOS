package com.pos.domain;

public abstract class Payment {
	protected Sale sale;
	protected double amount;
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public abstract boolean validate();
	
	public abstract void finishSale();
}
