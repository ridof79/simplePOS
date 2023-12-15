package com.pos.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pos.db.DBConnection;
import com.pos.domain.Cashier;
import com.pos.domain.Sale;
import com.pos.exception.RepositoryException;

public class DBTest {
	public static void main(String[] args) throws RepositoryException {
		Sale sale = new Sale("01", new Cashier("01", "dimas"));
		
		try (Connection connection = DBConnection.conn()) {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO sale_test (sale_number, date, cashier) values (?,?,?)");
			statement.setString(1, sale.getSaleNumber());
			statement.setDate(2, new Date(sale.getTransactionDate().getTime()));;
			statement.setString(3, sale.getCashier().getId());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

}
