package com.pos.test;

import com.pos.usecase.ProcessSaleHandler;

public class SaleTest {

	public static void main(String[] args) {

	ProcessSaleHandler saleHandler = new ProcessSaleHandler();
	
	saleHandler.createNewSale("#1", "01")
	.addSaleItem("001", 3).addSaleItem("002", 3)
	.getSale()
	.makePayment(saleHandler.qris())
	.finishSale();
	
	saleHandler.createNewSale("#2", "02")
	.addSaleItem("004", 3).addSaleItem("005", 3).addSaleItem("006", 2).addSaleItem("002", 1)
	.getSale()
	.makePayment(saleHandler.cash(150000))
	.finishSale();
	
	saleHandler.createNewSale("#2", "02")
	.addSaleItem("001", 3).addSaleItem("002", 3).addSaleItem("003", 2).addSaleItem("004", 3).addSaleItem("005", 3).addSaleItem("006", 2)
	.getSale()
	.makePayment(saleHandler.cash(500000))
	.finishSale();
	
	saleHandler.createNewSale("#1", "01")
	.addSaleItem("001", 10)
	.getSale()
	.makePayment(saleHandler.qris())
	.finishSale();
	
	}
	

	
	
	
}
