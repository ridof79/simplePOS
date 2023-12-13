package com.pos.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pos.domain.Item;
import com.pos.exception.RepositoryException;
import com.pos.repository.ItemRepository;

public class ItemRepositoryMySQL implements ItemRepository{
	private String userName = "root";
	private String password = "";
	private String jdbcUrl = "jdbc:mysql://localhost:3306/simple_pos_db";
	private Connection connection;
	
	private List<Item> items = new ArrayList<Item>();
		
	public ItemRepositoryMySQL() throws RepositoryException {
		try {
			// Load Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Get DB Connection
			connection = DriverManager.getConnection(jdbcUrl, userName, password);
		} catch (ClassNotFoundException e) {
			throw new RepositoryException("Class driver not found!");
		} catch (SQLException e) {
			throw new RepositoryException("Cannot connect to database!");
		}
	}
	
	public Statement query() throws RepositoryException {
		try {
			Statement statement = connection.createStatement();
			return statement;
		} catch (SQLException e) {
			throw new RepositoryException("Query Error");
		}
	}
	
	public ResultSet getItemFromDB(String query) throws RepositoryException {
		try {
			ResultSet resultSet = query().executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			throw new RepositoryException("Cannot get data from database");
		}
	}
	
	public void loadItemFromDB() throws RepositoryException {
		ResultSet resultSet = getItemFromDB("SELECT * FROM item");
		try {
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
		}	
	}
	
	private boolean taxableCheck(int taxable) {
		boolean isTaxable = false;
		if(taxable == 1) {
			isTaxable = true;
		}
		return isTaxable;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item findByItemCode(String itemCode) throws RepositoryException {
		Item item = null;
		ResultSet resultSet = getItemFromDB("SELECT * FROM item WHERE item_code ="+ itemCode);
		try {
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
		}	
		return item;
	}

	@Override
	public List<Item> findAll() throws RepositoryException {
		loadItemFromDB();
		return items;
	}
}

