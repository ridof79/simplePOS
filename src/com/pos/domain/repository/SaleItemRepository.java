package com.pos.domain.repository;

import com.pos.domain.SaleItem;

public interface SaleItemRepository {
	SaleItem save(String itemCode, int quantity);
}
