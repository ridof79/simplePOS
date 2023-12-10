package com.pos.domain.repository;

import com.pos.domain.Item;
import com.pos.domain.SaleItem;

public class SaleItemRepositoryDummy implements SaleItemRepository{
	
	@Override
	public SaleItem save(Item item, int quantity) {
		return new SaleItem(item, quantity);
	}
}
