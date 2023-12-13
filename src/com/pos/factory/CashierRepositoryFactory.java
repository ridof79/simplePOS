package com.pos.factory;

import com.pos.exception.RepositoryException;
import com.pos.repository.CashierRepository;
import com.pos.repository.impl.CashierRepositoryMySQL;

public class CashierRepositoryFactory {
	public static CashierRepository getCashierRepository() throws RepositoryException {
		return new CashierRepositoryMySQL();
	}

}
