package com.pos.repository.impl;

import com.pos.domain.Sale;
import com.pos.repository.SaleRepository;

public class SaleRepositoryDummy implements SaleRepository{
	
	@Override
	public void save(Sale sale) {
		System.out.println("Succesfully Transaction!");
		
	}

	@Override
	public Sale findByNumber(String number) {
		return null;
	}

}
