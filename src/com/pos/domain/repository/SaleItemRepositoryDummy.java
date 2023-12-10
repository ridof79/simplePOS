package com.pos.domain.repository;

import com.pos.domain.SaleItem;
import com.pos.domain.factory.ItemRepositoryFactory;

public class SaleItemRepositoryDummy implements SaleItemRepository{

	private ItemRepository itemRepo = ItemRepositoryFactory.getItemRepository();
	
	@Override
	public SaleItem save(String itemCode, int quantity) {
		return new SaleItem(itemRepo.findByItemCode(itemCode), quantity);
	}
}
