package com.johnbryce.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	/**
	 * initilaize connection to DB
	 * 
	 * @throws SQLException
	 *
	 */

	public static Connection getConnection(String username, String password, String schema) throws SQLException {

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + schema, username, password);
	}

	public static void createDB() throws SQLException {

		/**
		 * initialize COMPANIES TABLE
		 */

		try {
			Connection con = getConnection("root", "123123", "coupons_system");
			Statement stmt = con.createStatement();
			String query_Companies = "create table if not exists COMPANIES(" + "ID int PRIMARY KEY AUTO_INCREMENT,"
					+ "name varchar(255)," + "Email varchar(255)," + "password varchar(255)" + ")";
			stmt.execute(query_Companies);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * initialize CUSTOMERS TABLE
		 */
		try {
			Connection con = getConnection("root", "123123", "coupons_system");
			Statement stmt = con.createStatement();
			String query_Customres = "create table if not exists CUSTOMERS(" + "ID int PRIMARY KEY AUTO_INCREMENT,"
					+ "FirstName varchar(255)," + "LastName varchar(255)," + "Email varchar(255),"
					+ "password varchar(255)" + ")";
			stmt.execute(query_Customres);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * initialize CATEGORIES TABLE
		 */

		try {
			Connection con = getConnection("root", "123123", "coupons_system");
			Statement stmt = con.createStatement();
			String query_Categories = "create table if not exists CATEGORIES(" + "ID int PRIMARY KEY AUTO_INCREMENT,"
					+ "Name varchar(255)" + ")";
			stmt.execute(query_Categories);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * initialize Coupons TABLE
		 */
		try {
			Connection con = getConnection("root", "123123", "coupons_system");
			Statement stmt = con.createStatement();
			String query_Coupons = "create table if not exists Coupons(" + "ID int PRIMARY KEY AUTO_INCREMENT,"
					+ "COMPANY_ID int, FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES(ID),"
					+ "CATEGORY_ID int, FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES(ID)," + "TITLE varchar(255),"
					+ "DESCRIPTION varchar(255)," + "START_DATE DATE," + "END_DATE DATE," + "AMOUNT int,"
					+ "PRICE double," + "IMAGE varchar(255)" + ")";

			stmt.execute(query_Coupons);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * initialize CUSTOMERS_VS_COUPONS
		 */
		try {
			Connection con = getConnection("root", "123123", "coupons_system");
			Statement stmt = con.createStatement();
			String query_CUSTOMERS_VS_COUPONS = "create table if not exists CUSTOMERS_VS_COUPONS("
					+ "CUSTOMER_ID int, FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(ID),"
					+ "COUPON_ID int, FOREIGN KEY (COUPON_ID) REFERENCES Coupons(ID),"
					+ "PRIMARY KEY (CUSTOMER_ID, COUPON_ID))";
			stmt.execute(query_CUSTOMERS_VS_COUPONS);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
