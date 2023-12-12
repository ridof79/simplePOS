package com.pos.factory;

import com.pos.repository.ItemRepository;
import com.pos.repository.impl.ItemRepositoryFile;

public class ItemRepositoryFactory {
	public static ItemRepository getItemRepository() {
		return new ItemRepositoryFile();
	
	}
}
