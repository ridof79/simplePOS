package com.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pos.db.DBConnection;
import com.pos.domain.Cashier;
import com.pos.exception.DBConnectionException;
import com.pos.exception.RepositoryException;
import com.pos.repository.CashierRepository;

public class CashierRepositoryMySQL implements CashierRepository{
	
	@Override
	public void save(Cashier cashier) throws RepositoryException {
		try (Connection connection = DBConnection.conn()) {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO cashier (id, name) values (?,?)");
			statement.setString(1, cashier.getId());
			statement.setString(2, cashier.getName());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	@Override
	public Cashier findCashierByID(String id) throws RepositoryException {
		Cashier cashier = null;
		try (Connection connection = DBConnection.conn()){
			ResultSet resultSet = DBConnection.executeQuery(connection, "SELECT * FROM cashier WHERE ID="+id+";");
			while(resultSet.next()) {
				cashier = new Cashier(
						resultSet.getString("id"),
						resultSet.getString("name"));
			}
		} catch (DBConnectionException e) {
			throw new RepositoryException(e.getMessage());
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
		return cashier;
	}

	@Override
	public List<Cashier> findAll() throws RepositoryException {
		List<Cashier> cashiers = new ArrayList<Cashier>();
		try (Connection connection = DBConnection.conn()){
			ResultSet resultSet = DBConnection.executeQuery(connection, "SELECT * FROM cashier");
			while(resultSet.next()) {
				cashiers.add(new Cashier(
						resultSet.getString("id"),
						resultSet.getString("name")));
			}
		} catch (DBConnectionException e) {
			throw new RepositoryException("Cannot get data from database");
		} catch (SQLException e) {
			throw new RepositoryException("Cannot get data from database");
		}
		return cashiers;
	}

}
