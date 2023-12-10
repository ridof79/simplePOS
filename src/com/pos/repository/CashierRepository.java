package com.pos.repository;

import com.pos.domain.Cashier;

public interface CashierRepository {
	void save();
	Cashier getCashierById(String id);

}
