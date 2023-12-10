package com.pos.test;


import com.pos.domain.CashPayment;
import com.pos.domain.ProcessSaleUseCase;
import com.pos.domain.QrisPayment;
import com.pos.domain.Sale;

public class SaleTest {

	public static void main(String[] args) {
		Sale sale1 = ProcessSaleUseCase.createNewSale("#1", "01");
		ProcessSaleUseCase.addSaleItem(sale1, "001", 3);
		ProcessSaleUseCase.addSaleItem(sale1, "002", 3);
		ProcessSaleUseCase.addSaleItem(sale1, "003", 2);
		
		ProcessSaleUseCase.makePayment(sale1, new QrisPayment());
		
		ProcessSaleUseCase.getSale(sale1);
		ProcessSaleUseCase.finishSale(sale1);
		
		Sale sale2 = ProcessSaleUseCase.createNewSale("#2", "02");
		ProcessSaleUseCase.addSaleItem(sale2, "003", 3);
		ProcessSaleUseCase.addSaleItem(sale2, "002", 3);
		ProcessSaleUseCase.addSaleItem(sale2, "005", 2);
		
		ProcessSaleUseCase.makePayment(sale2, new CashPayment(200000.0));
		
		ProcessSaleUseCase.getSale(sale2);
		ProcessSaleUseCase.finishSale(sale2);
	}

}
