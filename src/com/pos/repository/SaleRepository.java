package com.pos.repository;

import com.pos.domain.Sale;
import com.pos.exception.RepositoryException;

public interface SaleRepository {
	void save(Sale sale) throws RepositoryException;
	Sale findByNumber(String number) throws RepositoryException;

}
