package com.pos.test;

import com.pos.domain.Sale;
import com.pos.exception.HandlerException;
import com.pos.usecase.ProcessSaleHandler;

public class SaleTest {

	public static void main(String[] args) {
		
	try {
		
		ProcessSaleHandler saleHandler = new ProcessSaleHandler();
		saleHandler.createNewSale("#7", "01")
		.addSaleItem("001", 3).addSaleItem("002", 3)
		.getSale()
		.makePayment(saleHandler.qris())
		.finishSale();
//		
//		saleHandler.createNewSale("#2", "02")
//		.addSaleItem("004", 3).addSaleItem("005", 3).addSaleItem("006", 2).addSaleItem("002", 1)
//		.getSale()
//		.makePayment(saleHandler.cash(150000))
//		.finishSale();
//	
//		saleHandler.createNewSale("#3", "02")
//		.addSaleItem("001", 3).addSaleItem("002", 3).addSaleItem("003", 2).addSaleItem("004", 3).addSaleItem("005", 3).addSaleItem("006", 2)
//		.getSale()
//		.makePayment(saleHandler.cash(500000))
//		.finishSale();
//	
//		saleHandler.createNewSale("#4", "01")
//		.addSaleItem("001", 10)
//		.getSale()
//		.makePayment(saleHandler.qris())
//		.finishSale();
//		
//		saleHandler.createNewSale("#5", "02")
//		.addSaleItem("006", 10).addSaleItem("001", 10)
//		.getSale()
//		.makePayment(saleHandler.qris())
//		.finishSale();
		
		Sale sale = saleHandler.findSaleByNumber("#2");
		System.out.println(sale.getCashier().getName());
		System.out.println(sale.getSaleNumber());
		System.out.println(sale.getTransactionDate());
		System.out.println(sale.getSaleItem().get(0).getPrice());
		System.out.println(sale.getSaleItem().get(1).getPrice());
				
	} catch (HandlerException e) {
		System.out.println(e.getMessage());
		}
	}
	
}
