package com.pos.domain;

public class SaleItem {
	private Item item;
	private int quantity;
	private double price;
	
	public SaleItem(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.price = item.getPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public double getPrice() {
		return price;
	}
	
	public double totalPrice() {
		return this.quantity * this.price;
	}
}
