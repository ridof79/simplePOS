package com.pos.domain;

public class QrisPayment extends Payment{

	@Override
	public void validate() {
		this.amount = sale.totalPrice();
		this.isPay = true;
	}
	
	public void generateQR() {
		System.out.println("Please Scan This QR . . .");
	}

	@Override
	public void finishSale() {
		System.out.println("Payment (QRIS) : " + amount + "\n");
	}
	

}
