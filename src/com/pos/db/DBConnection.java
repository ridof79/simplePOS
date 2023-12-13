package com.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pos.exception.DBConnectionException;
import com.pos.misc.Constant;

public class DBConnection {
	private static String userName = Constant.DB_USERNAME;
	private static String password = Constant.DB_PASSWORD;
	private static String jdbcUrl = Constant.JDBC_URL;
	private static Connection connection;
	
	public static Connection connect() throws DBConnectionException {
		try {
			// Load Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Get DB Connection
			connection = DriverManager.getConnection(jdbcUrl, userName, password);
		} catch (ClassNotFoundException e) {
			throw new DBConnectionException("Class driver not found!");
		} catch (SQLException e) {
			throw new DBConnectionException("Cannot connect to database!");
		}
		return connection;
	}
	
	public static Statement statement() throws DBConnectionException {
		try {
			Statement statement = connection.createStatement();
			return statement;
		} catch (SQLException e) {
			throw new DBConnectionException("Query Error");
		}
	}
	
	public static ResultSet executeQuery(String query) throws DBConnectionException {
		ResultSet resultSet = null;
		try {
			resultSet = statement().executeQuery(query);
		} catch (SQLException e) {
			throw new DBConnectionException("Cannot get data from database");
		}
		return resultSet;
	}
	
	public static void executeUpdate() {
		
	}

}
