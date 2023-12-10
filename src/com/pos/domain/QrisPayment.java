package com.pos.domain;

public class QrisPayment extends Payment{

	@Override
	public boolean validate() {
		this.amount = sale.totalPrice();
		return true;
	}
	
	public void generateQR() {
		System.out.println("Please Scan This QR . . .");
	}

	@Override
	public void finishSale() {
		System.out.println("Payment (QRIS) : " + amount);
	}

}
