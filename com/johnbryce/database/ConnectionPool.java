package com.johnbryce.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.johnbryce.customException.GenralException;

public class ConnectionPool {
	private static Set<Connection> connections;
	private static ConnectionPool instance;

	private ConnectionPool() {
	}

	/**
	 * this is for get only one connection and remove it from the hash
	 * set @connection(0);
	 */


	public Connection getConnection() {

		if (connections.isEmpty()) {
				try {
					connections.wait();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (connections) {
			for (Connection con : connections) {
				connections.remove(con);
				return con;
			}
		}
		return null;
	}

	/*
	 * this is for return back, one connection to the pool.
	 */
	public void restoreConnection(Connection connection) {
		if (connection != null) {
		synchronized (connections) {
				// System.out.println("Connection returned");
				connections.add(connection);
				connections.notify();
			}
		}
	}

	public void closeAllConnectios() throws GenralException {
	 List<Connection> temp =new ArrayList<Connection>();
	 if (connections.isEmpty()) {
		 throw new GenralException("connections is empty");
	 }else {
	
			for (Connection con :connections){
				synchronized (connections) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			}
	 }
	}
	

	/**
	 * to make this class to single ton
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
					connections = new HashSet<Connection>();
						try {
							for (int i = 0; i < 10; i++) { 
								connections.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "coupons_system", "root", "123123"));
							}} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		return instance;
}

// i this i miss how to create a connection
}
