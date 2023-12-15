package com.pos.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pos.db.DBConnection;
import com.pos.domain.Item;
import com.pos.exception.DBConnectionException;
import com.pos.exception.RepositoryException;
import com.pos.repository.ItemRepository;

public class ItemRepositoryMySQL implements ItemRepository{
	
	private List<Item> items = new ArrayList<Item>();
	
	private boolean taxableCheck(int taxable) {
		boolean isTaxable = false;
		if(taxable == 1) {
			isTaxable = true;
		}
		return isTaxable;
	}

	@Override
	public Item findByItemCode(String itemCode) throws RepositoryException {
		Item item = null;
		try (Connection connection = DBConnection.conn()) {
			ResultSet resultSet = DBConnection.executeQuery(connection ,"SELECT * FROM item WHERE item_code ="+ itemCode);
			while(resultSet.next()) {
				item = new Item(
						resultSet.getString("item_code"), 
						resultSet.getDouble("price"), 
						resultSet.getString("description"), 
						resultSet.getString("type"), 
						taxableCheck(resultSet.getInt("taxable")));
			}
		} catch (SQLException e) {
			throw new RepositoryException("Cannot get data from database");
		} catch (DBConnectionException e) {
			throw new RepositoryException("Cannot get data from database");
		}	
		return item;
	}



	@Override
	public List<Item> findAll() throws RepositoryException {
		ResultSet resultSet;
		try (Connection connection = DBConnection.conn()){
			resultSet = DBConnection.executeQuery(connection, "SELECT * FROM item");
			while(resultSet.next()) {
				items.add(new Item(
						resultSet.getString("item_code"), 
						resultSet.getDouble("price"), 
						resultSet.getString("description"), 
						resultSet.getString("type"), 
						taxableCheck(resultSet.getInt("taxable"))));
				}
		} catch (SQLException e) {
			throw new RepositoryException("Cannot get data from database");
		} catch (DBConnectionException e) {
			throw new RepositoryException("Cannot get data from database");
		}
		return items;
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}

