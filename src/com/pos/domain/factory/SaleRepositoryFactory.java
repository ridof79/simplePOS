package com.pos.domain.factory;

import com.pos.domain.repository.SaleRepository;
import com.pos.domain.repository.SaleRepositoryDummy;

public class SaleRepositoryFactory {
	public static SaleRepository getSaleRepository() {
		return new SaleRepositoryDummy();
	}
}
