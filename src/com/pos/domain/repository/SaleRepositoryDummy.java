package com.pos.domain.repository;

import com.pos.domain.Sale;

public class SaleRepositoryDummy implements SaleRepository{
	
	@Override
	public void save(Sale sale) {
		System.out.println("Succesfully Transaction!");
		
	}

	@Override
	public Sale findByNumber(String number) {
		// TODO Auto-generated method stub
		return null;
	}

}
