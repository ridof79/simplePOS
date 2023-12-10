package com.pos.repository;

import com.pos.domain.Cashier;

public class CashierRepositoryDummy implements CashierRepository{

	@Override
	public void save() {
		System.out.println("Succesfully add Cashier data!");
		
	}

	@Override
	public Cashier getCashierById(String id) {
		Cashier cashier1 = new Cashier("01", "Andre");
		Cashier cashier2 = new Cashier("02", "Dimas");
		
		Cashier[] cashiers = {cashier1, cashier2};
		
		for(Cashier cashier : cashiers) {
			if (cashier.getId().equals(id)) {
				return cashier;
			}
		}
		return null;
	}

}
