package com.pos.domain.factory;

import com.pos.domain.repository.CashierRepository;
import com.pos.domain.repository.CashierRepositoryDummy;

public class CashierRepositoryFactory {
	public static CashierRepository getCashierRepository() {
		return new CashierRepositoryDummy();
	}

}
