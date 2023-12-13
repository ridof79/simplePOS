package com.pos.factory;

import com.pos.exception.RepositoryException;
import com.pos.repository.ItemRepository;
import com.pos.repository.impl.ItemRepositoryMySQL;

public class ItemRepositoryFactory {
	public static ItemRepository getItemRepository() throws RepositoryException {
		return new ItemRepositoryMySQL();
	
	}
}
