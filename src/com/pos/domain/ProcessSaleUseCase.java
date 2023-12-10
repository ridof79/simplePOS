package com.pos.domain;

import com.pos.domain.factory.CashierRepositoryFactory;
import com.pos.domain.factory.SaleItemRepositoryFactory;
import com.pos.domain.factory.SaleRepositoryFactory;
import com.pos.domain.repository.CashierRepository;
import com.pos.domain.repository.SaleItemRepository;
import com.pos.domain.repository.SaleRepository;

public class ProcessSaleUseCase {
	private static SaleItemRepository saleItemRepo = SaleItemRepositoryFactory.getSaleItemRepository();
	private static CashierRepository cashierRepo = CashierRepositoryFactory.getCashierRepository();
	private static SaleRepository saleRepo = SaleRepositoryFactory.getSaleRepository();
	
	public static Sale createNewSale(String saleNumber, String cashierId) {
		return new Sale(saleNumber, cashierRepo.getCashierById(cashierId));
	}
	
	public static void addSaleItem(Sale sale, String itemCode, int quantity) {
		sale.addSaleItem(saleItemRepo.save(itemCode, quantity));
	}
	
	public static void makePayment(Sale sale, Payment payment) {
		sale.setPayment(payment);
		payment.setSale(sale);
		payment.validate();
	}
	
	public static void getSale(Sale sale) {
		System.out.println("=============================================================" + "\n");
		System.out.println("Sale Number  : " + sale.getSaleNumber());
		System.out.println("Cashier  : " + sale.getCashier().getName());
		System.out.println("Date  : " + sale.getTransactionDate().toString());
		
		System.out.println("Item  : ");		
		for(SaleItem saleItem : sale.getSaleItems()) {
			if (saleItem != null) {
			System.out.println("Item Code : " + saleItem.getItem().getItemCode() + " | Description : " + saleItem.getItem().getDescription() 
					+ " | Type : " + saleItem.getItem().getType() + " | Price : " + saleItem.getPrice() + " | Quantity : " + saleItem.getQuantity()
					+ " | Total Price : " + saleItem.totalPrice());
			}		
		}
		
		System.out.println("Total Price : " + sale.totalPriceWithoutTax() );
		System.out.println("Tax : " + (sale.totalPrice()-sale.totalPriceWithoutTax()));
		System.out.println("Total Price + Tax : " + sale.totalPrice() + "\n");
		System.out.println("=============================================================");
	}

	public static void finishSale(Sale sale) {
		saleRepo.save(sale);
		System.out.println("=============================================================" + "\n");
		System.out.println("Sale Number  : " + sale.getSaleNumber());
		System.out.println("Cashier  : " + sale.getCashier().getName());
		System.out.println("Date  : " + sale.getTransactionDate().toString());
		
		System.out.println("Item  : ");		
		for(SaleItem saleItem : sale.getSaleItems()) {
			if (saleItem != null) {
			System.out.println("Item Code : " + saleItem.getItem().getItemCode() + " | Description : " + saleItem.getItem().getDescription() 
					+ " | Type : " + saleItem.getItem().getType() + " | Price : " + saleItem.getPrice() + " | Quantity : " + saleItem.getQuantity()
					+ " | Total Price : " + saleItem.totalPrice());
			}		
		}
		
		System.out.println("Total Price : " + sale.totalPriceWithoutTax() );
		System.out.println("Tax : " + (sale.totalPrice()-sale.totalPriceWithoutTax()));
		System.out.println("Total Price + Tax : " + sale.totalPrice() + "\n");
		
		sale.getPayment().finishSale();
		System.out.println("=============================================================");
	}
}
