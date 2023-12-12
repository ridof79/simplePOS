package com.pos.factory;

import com.pos.repository.CashierRepository;
import com.pos.repository.impl.CashierRepositoryDummy;

public class CashierRepositoryFactory {
	public static CashierRepository getCashierRepository() {
		return new CashierRepositoryDummy();
	}

}
