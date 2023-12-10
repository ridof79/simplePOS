package com.pos.domain.repository;

import com.pos.domain.Item;
import com.pos.domain.SaleItem;

public interface SaleItemRepository {
	SaleItem save(Item item, int quantity);
}
