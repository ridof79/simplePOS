package com.pos.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pos.db.DBConnection;
import com.pos.domain.Cashier;
import com.pos.exception.DBConnectionException;
import com.pos.exception.RepositoryException;
import com.pos.repository.CashierRepository;

public class CashierRepositoryMySQL implements CashierRepository{
	
	public CashierRepositoryMySQL() throws RepositoryException {
		try {
			DBConnection.connect();
		} catch (DBConnectionException e) {
			throw new RepositoryException("Cannot connect to database");
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cashier findCashierByID(String id) throws RepositoryException {
		Cashier cashier = null;
		try {
			ResultSet resultSet = DBConnection.executeQuery("SELECT * FROM cashier WHERE ID="+id);
			while(resultSet.next()) {
				cashier = new Cashier(
						resultSet.getString("id"),
						resultSet.getString("name"));
			}
		} catch (DBConnectionException e) {
			throw new RepositoryException("Cannot get data from database");
		} catch (SQLException e) {
			throw new RepositoryException("Cannot get data from database");
		}
		return cashier;
	}

	@Override
	public List<Cashier> findAll() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
