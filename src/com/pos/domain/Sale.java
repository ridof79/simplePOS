package com.pos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
	private String saleNumber;
	private Cashier cashier;
	private Date transactionDate = new Date();
	private List<SaleItem> saleItems = new ArrayList<SaleItem>();
	private Payment payment;
	private Taxable tax;
		
	public Sale(String saleNumber, Cashier cashier) {
		this.saleNumber = saleNumber;
		this.cashier = cashier;
		this.tax = new Taxable();
	}
	
	public Sale(String saleNumber, java.sql.Date date, Cashier cashier) {
		this.saleNumber = saleNumber;
		this.cashier = cashier;
		this.tax = new Taxable();
		this.transactionDate = date;
	}

	public void addSaleItem(SaleItem saleItem) {
		saleItems.add(saleItem);
	}
	
	public List<SaleItem> getSaleItem() {
		return new ArrayList<SaleItem>(saleItems);
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

	public Payment getPayment() {
		return payment;
	}

	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	
	
	
}
