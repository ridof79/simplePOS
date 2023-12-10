package com.pos.domain;

import java.util.Arrays;
import java.util.Date;

public class Sale {
	private String saleNumber;
	private Cashier cashier;
	private Date transactionDate = new Date();
	private SaleItem[] saleItems = new SaleItem[3];
	private Payment payment;
	private Taxable tax;
		
	public Sale(String saleNumber, Cashier cashier) {
		this.saleNumber = saleNumber;
		this.cashier = cashier;
		this.tax = new Taxable();
	}
	
	public void addSaleItem(SaleItem saleItem) {
		for(int i=0 ; i < saleItems.length ; i++) {
			if(saleItems[i] == null) {
				saleItems[i] = saleItem;
				break;
			}
		}
	}
	
	public SaleItem[] getSaleItem() {
		return Arrays.copyOf(saleItems, saleItems.length);
	}
	
	public double totalPrice() {
		double totalPrice = 0;
		for(SaleItem saleItem : saleItems) {
			if (saleItem.getItem().isTaxable()) {
				totalPrice = totalPrice + saleItem.totalPrice() + (saleItem.totalPrice() * tax.getTax());
				
			} else {
			totalPrice = totalPrice + saleItem.totalPrice();
		}
	}
		return totalPrice;
	}
	
	public double totalPriceWithoutTax() {
		double totalPriceWithoutTax = 0;
		for(SaleItem saleItem : saleItems) {
			totalPriceWithoutTax = totalPriceWithoutTax + saleItem.totalPrice();
		}
		return totalPriceWithoutTax;
	}
	
	public void finish() {
		System.out.println("Finish Transaction!");
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public SaleItem[] getSaleItems() {
		return saleItems;
	}

	public Payment getPayment() {
		return payment;
	}
	

}
