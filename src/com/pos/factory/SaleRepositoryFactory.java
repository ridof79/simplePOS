package com.pos.factory;

import com.pos.exception.RepositoryException;
import com.pos.repository.SaleRepository;
import com.pos.repository.impl.SaleRepositoryMySQL;

public class SaleRepositoryFactory {
	public static SaleRepository getSaleRepository() throws RepositoryException {
		return new SaleRepositoryMySQL();
	}
}
