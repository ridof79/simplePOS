package com.pos.factory;

import com.pos.repository.SaleRepository;
import com.pos.repository.SaleRepositoryDummy;

public class SaleRepositoryFactory {
	public static SaleRepository getSaleRepository() {
		return new SaleRepositoryDummy();
	}
}
