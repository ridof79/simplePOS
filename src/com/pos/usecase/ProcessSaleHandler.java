package com.pos.usecase;

import com.pos.domain.CashPayment;
import com.pos.domain.Payment;
import com.pos.domain.QrisPayment;
import com.pos.domain.Sale;
import com.pos.domain.SaleItem;
import com.pos.factory.CashierRepositoryFactory;
import com.pos.factory.ItemRepositoryFactory;
import com.pos.factory.SaleItemRepositoryFactory;
import com.pos.factory.SaleRepositoryFactory;
import com.pos.repository.CashierRepository;
import com.pos.repository.ItemRepository;
import com.pos.repository.SaleItemRepository;
import com.pos.repository.SaleRepository;

public class ProcessSaleHandler {
	
	private Sale sale;
	private SaleItemRepository saleItemRepo;
	private CashierRepository cashierRepo;
	private SaleRepository saleRepo;
	private ItemRepository itemRepo;
	
	public ProcessSaleHandler() {
		 saleItemRepo = SaleItemRepositoryFactory.getSaleItemRepository();
		 cashierRepo = CashierRepositoryFactory.getCashierRepository();
		 saleRepo = SaleRepositoryFactory.getSaleRepository();
		 itemRepo = ItemRepositoryFactory.getItemRepository();
	}

	public ProcessSaleHandler createNewSale(String saleNumber, String cashierId) {
		sale = new Sale(saleNumber, cashierRepo.getCashierById(cashierId));
		return this;
	}
	
	public ProcessSaleHandler addSaleItem(String itemCode, int quantity) {
		sale.addSaleItem(saleItemRepo.save(itemRepo.findByItemCode(itemCode), quantity));
		return this;
	}
	
	public ProcessSaleHandler makePayment(Payment payment) {
		sale.setPayment(payment);
		payment.setSale(sale);
		return this;
	}
	
	public ProcessSaleHandler getSale() {
		System.out.println("=============================================================" + "\n");
		System.out.println("Sale Number  : " + sale.getSaleNumber());
		System.out.println("Cashier  : " + sale.getCashier().getName());
		System.out.println("Date  : " + sale.getTransactionDate().toString());
		
		System.out.println("Item  : ");		
		for(SaleItem saleItem : sale.getSaleItem()) {
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
		return this;
	}

	public ProcessSaleHandler finishSale() {
		sale.getPayment().validate();
		saleRepo.save(sale);
		System.out.println("=============================================================" + "\n");
		System.out.println("Sale Number  : " + sale.getSaleNumber());
		System.out.println("Cashier  : " + sale.getCashier().getName());
		System.out.println("Date  : " + sale.getTransactionDate().toString());
		
		System.out.println("Item  : ");		
		for(SaleItem saleItem : sale.getSaleItem()) {
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
		return this;
	}
	
	public Payment qris() {
		return new QrisPayment();
	}
	
	public Payment cash(int amount) {
		return new CashPayment(amount);
	}
}
