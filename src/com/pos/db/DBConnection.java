package com.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pos.exception.DBConnectionException;
import com.pos.misc.Constant;

public class DBConnection {
	
	private Connection conn;
	
	public DBConnection() throws DBConnectionException {
		try {
			this.conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage());
		}
	}
	
	public Connection getConn() {
		return conn;
	}

	public static Connection conn() throws DBConnectionException {
		return new DBConnection().getConn();
	}
	
	public static ResultSet executeQuery(Connection connection, String query) throws DBConnectionException {
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage());
		}
		return resultSet;
	}
	
	public void executeUpdate() {
		
	}

}
