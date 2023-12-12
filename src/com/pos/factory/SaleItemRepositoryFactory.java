package com.pos.factory;

import com.pos.repository.SaleItemRepository;
import com.pos.repository.impl.SaleItemRepositoryDummy;

public class SaleItemRepositoryFactory {
	public static SaleItemRepository getSaleItemRepository(){
		return new SaleItemRepositoryDummy();
	}

}
