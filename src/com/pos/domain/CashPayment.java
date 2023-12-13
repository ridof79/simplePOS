package com.pos.domain;

public class CashPayment extends Payment{
	private double cashInHand;
	
	public CashPayment(double cashInHand) {
		super();
		this.cashInHand = cashInHand;
		change();
	}

	@Override
	public void validate() {
		this.amount = sale.totalPrice();
		this.isPay = true;
	}

	public double change() {
		return cashInHand - amount;
	}

	@Override
	public void finishSale() {
		System.out.println("Payment (Cash) : " + cashInHand);
		System.out.println("Change : " + change() + "\n");
	}
}
