package com.pos.domain.factory;

import com.pos.domain.repository.ItemRepository;
import com.pos.domain.repository.ItemRepositoryDummy;

public class ItemRepositoryFactory {
	public static ItemRepository getItemRepository() {
		return new ItemRepositoryDummy();
	
	}
}
