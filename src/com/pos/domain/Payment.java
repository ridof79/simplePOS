package com.pos.domain;

public abstract class Payment {
	protected Sale sale;
	protected double amount;
	protected boolean isPay = false;
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public abstract void validate();
	
	public abstract void finishSale();
}
