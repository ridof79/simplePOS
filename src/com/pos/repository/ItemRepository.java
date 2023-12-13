package com.pos.repository;

import java.sql.SQLException;
import java.util.List;

import com.pos.domain.Item;
import com.pos.exception.RepositoryException;

public interface ItemRepository {
	void save();
	Item findByItemCode(String itemCode) throws RepositoryException;
	List<Item> findAll() throws RepositoryException;

}
