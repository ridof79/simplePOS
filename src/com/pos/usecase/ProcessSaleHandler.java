package com.pos.usecase;

import java.util.ArrayList;
import java.util.List;

import com.pos.domain.CashPayment;
import com.pos.domain.Payment;
import com.pos.domain.QrisPayment;
import com.pos.domain.Sale;
import com.pos.domain.SaleItem;
import com.pos.exception.HandlerException;
import com.pos.exception.RepositoryException;
import com.pos.factory.CashierRepositoryFactory;
import com.pos.factory.ItemRepositoryFactory;
import com.pos.factory.SaleItemRepositoryFactory;
import com.pos.factory.SaleRepositoryFactory;
import com.pos.misc.TableGenerator;
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
	
	public ProcessSaleHandler() throws HandlerException {
		 try {
			saleItemRepo = SaleItemRepositoryFactory.getSaleItemRepository();
			saleRepo = SaleRepositoryFactory.getSaleRepository();
			cashierRepo = CashierRepositoryFactory.getCashierRepository();
			itemRepo = ItemRepositoryFactory.getItemRepository();
		} catch (RepositoryException e) {
			throw new HandlerException(e.getMessage());
		}
	}

	public ProcessSaleHandler createNewSale(String saleNumber, String cashierId) throws HandlerException {
		try {
			sale = new Sale(saleNumber, cashierRepo.findCashierByID(cashierId));
		} catch (RepositoryException e) {
			throw new HandlerException("Failed get cashier!");
		}
		return this;
	}
	
	public ProcessSaleHandler addSaleItem(String itemCode, int quantity) throws HandlerException {
		try {
			sale.addSaleItem(saleItemRepo.save(itemRepo.findByItemCode(itemCode), quantity));
		} catch (RepositoryException e) {
			throw new HandlerException("Failed add sale item");
		}
		return this;
	}
	
	public ProcessSaleHandler makePayment(Payment payment) {
		sale.setPayment(payment);
		payment.setSale(sale);
		return this;
	}
	
	public ProcessSaleHandler getSale() {
		System.out.println("Sale Number  : " + sale.getSaleNumber());
		System.out.println("Cashier  : " + sale.getCashier().getName());
		System.out.println("Date  : " + sale.getTransactionDate().toString());
		
		System.out.println("Item  : ");		
		
        TableGenerator tableGenerator = new TableGenerator();
        List<String> headersList = new ArrayList<>(); 
        headersList.add("Item Code");
        headersList.add("Description");
        headersList.add("Type");
        headersList.add("Price");
        headersList.add("Quantity");
        headersList.add("Total Price");
        
        List<List<String>> rowsList = new ArrayList<>();
        
        for(SaleItem saleItem : sale.getSaleItem()) {
            List<String> row = new ArrayList<>();
            row.add(saleItem.getItem().getItemCode());
            row.add(saleItem.getItem().getDescription());
            row.add(saleItem.getItem().getType());
            row.add("" + saleItem.getPrice());
            row.add("" + saleItem.getQuantity());
            row.add("" + saleItem.totalPrice());
            rowsList.add(row);
        }
        System.out.println(tableGenerator.generateTable(headersList, rowsList));
    	
		System.out.println("Total Price : " + sale.totalPriceWithoutTax() );
		System.out.println("Tax : " + (sale.totalPrice()-sale.totalPriceWithoutTax()));
		System.out.println("Total Price + Tax : " + sale.totalPrice());
		return this;
	}

	public ProcessSaleHandler finishSale() {
		System.out.println("\n" + "=============================================================" + "\n");
		sale.getPayment().validate();
		saleRepo.save(sale);
		getSale();
		sale.getPayment().finishSale();
		System.out.println("=============================================================" + "\n");
		return this;
	}
	
	public Payment qris() {
		return new QrisPayment();
	}
	
	public Payment cash(int amount) {
		return new CashPayment(amount);
	}
}
