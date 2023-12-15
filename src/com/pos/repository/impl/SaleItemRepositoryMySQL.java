package com.pos.repository.impl;

import com.pos.domain.Item;
import com.pos.domain.SaleItem;
import com.pos.repository.SaleItemRepository;

public class SaleItemRepositoryMySQL implements SaleItemRepository{
	
	@Override
	public SaleItem save(Item item, int quantity) {
		return new SaleItem(item, quantity);
	}
}
