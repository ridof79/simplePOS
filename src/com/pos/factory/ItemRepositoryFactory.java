package com.pos.factory;

import com.pos.repository.ItemRepository;
import com.pos.repository.ItemRepositoryDummy;

public class ItemRepositoryFactory {
	public static ItemRepository getItemRepository() {
		return new ItemRepositoryDummy();
	
	}
}
