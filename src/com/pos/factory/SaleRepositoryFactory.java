package com.pos.factory;

import com.pos.repository.SaleRepository;
import com.pos.repository.impl.SaleRepositoryDummy;

public class SaleRepositoryFactory {
	public static SaleRepository getSaleRepository() {
		return new SaleRepositoryDummy();
	}
}
