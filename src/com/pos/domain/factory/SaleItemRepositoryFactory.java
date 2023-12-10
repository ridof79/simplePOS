package com.pos.domain.factory;

import com.pos.domain.repository.SaleItemRepository;
import com.pos.domain.repository.SaleItemRepositoryDummy;

public class SaleItemRepositoryFactory {
	public static SaleItemRepository getSaleItemRepository(){
		return new SaleItemRepositoryDummy();
	}

}
