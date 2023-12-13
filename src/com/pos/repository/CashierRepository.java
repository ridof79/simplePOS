package com.pos.repository;

import java.util.List;

import com.pos.domain.Cashier;
import com.pos.exception.RepositoryException;

public interface CashierRepository {
	void save();
	Cashier findCashierByID(String id) throws RepositoryException;
	List<Cashier> findAll() throws RepositoryException;

}
