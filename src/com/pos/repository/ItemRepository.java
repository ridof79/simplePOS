package com.pos.repository;

import com.pos.domain.Item;

public interface ItemRepository {
	void save();
	Item findByItemCode(String itemCode);

}
