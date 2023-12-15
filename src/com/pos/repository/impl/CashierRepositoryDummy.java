package com.pos.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pos.domain.Cashier;
import com.pos.exception.RepositoryException;
import com.pos.repository.CashierRepository;

public class CashierRepositoryDummy implements CashierRepository{

	@Override
	public void save(Cashier cashier) {
		System.out.println("Succesfully add Cashier data!");
		
	}

	@Override
	public Cashier findCashierByID(String id) {
		Cashier cashier1 = new Cashier("01", "Andre");
		Cashier cashier2 = new Cashier("02", "Dimas");
		
		List<Cashier> cashiers = new ArrayList<Cashier>(Arrays.asList(cashier1, cashier2));
		
		for(Cashier cashier : cashiers) {
			if (cashier.getId().equals(id)) {
				return cashier;
			}
		}
		return null;
	}

	@Override
	public List<Cashier> findAll() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
