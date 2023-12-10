package com.pos.domain.repository;

import com.pos.domain.Sale;

public interface SaleRepository {
	void save(Sale sale);
	Sale findByNumber(String number);

}
