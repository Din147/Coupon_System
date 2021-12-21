package com.johnbryce.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.customException.CustomersDBExpetion;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.database.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) throws CustomersDBExpetion  {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT email, password FROM customers where email=? and password=?");
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rset = stmt.executeQuery();
			
			return rset.next();

		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public void addCustomer(Customer customer) throws CustomersDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"Insert into customers (FirstName, LastName, Email,Password) values (?,?,?,?)");
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLasrName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			stmt.execute();
			
		} catch (SQLException e) {
			throw new CustomersDBExpetion();
			} finally {
			cp.restoreConnection(con);
		}
	}

	/**
	 * update customer by email
	 * @throws CustomersDBExpetion 
	 */
	@Override
	public void updatedCustomer(Customer customer) throws CustomersDBExpetion {
		Connection con = cp.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE customers SET FirstName=?, LastName=? where Email = ?");
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLasrName());
			stmt.setString(3, customer.getEmail());
			stmt.execute();

		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public void deleteCustomer(int customerID) throws CustomersDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM customers WHERE ID= ?");
			stmt.setInt(1, customerID);
			stmt.execute();
		
		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws CustomersDBExpetion {
		List<Customer> customers = new ArrayList<Customer>();
		List<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from customers");
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {

				// to get the Coupons of the Costumer:
				// step 1 to get the Coupons ID
				PreparedStatement stmtForCouponsStep1 = con
						.prepareStatement("SELECT COUPON_ID FROM CUSTOMERS_VS_COUPONS WHERE CUSTOMER_ID=? ");
				stmtForCouponsStep1.setInt(1, rset.getInt("ID"));
				ResultSet rsetstep1 = stmtForCouponsStep1.executeQuery();

				// step 2 to get the Coupons to an ArrayList

				while (rsetstep1.next()) {
					PreparedStatement stmtForCouponsStep2 = con.prepareStatement("SELECT * FROM Coupons WHERE ID =? ");
					stmtForCouponsStep2.setInt(1, rsetstep1.getInt("COUPON_ID"));
					ResultSet rsetstep2 = stmtForCouponsStep2.executeQuery();
					coupons.add(new Coupon(rsetstep2.getInt("COMPANY_ID"), rsetstep2.getInt("CATEGORY_ID"),
							rsetstep2.getString("TITLE"), rsetstep2.getString("DESCRIPTION"),
							rsetstep2.getDate("START_DATE"), rsetstep2.getDate("END_DATE"), rsetstep2.getInt("AMOUNT"),
							rsetstep2.getDouble("PRICE"), rsetstep2.getString("IMAGE")));

				}

				customers.add(new Customer(rset.getInt("ID"), rset.getString("FirstName"), rset.getString("LastName"),
						rset.getString("Email"), rset.getString("password"), coupons));
			}

		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

		return (ArrayList<Customer>) customers;

	}

	@Override
	public Customer getOneCustomer(int customerID) throws CustomersDBExpetion {
		List<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from customers WHERE ID=?");
			stmt.setInt(1, customerID);
			ResultSet rset = stmt.executeQuery();

			if (rset.next()) {

				PreparedStatement stmtForCouponsStep1 = con
						.prepareStatement("SELECT COUPON_ID FROM CUSTOMERS_VS_COUPONS WHERE CUSTOMER_ID=? ");
				stmtForCouponsStep1.setInt(1, customerID);
				ResultSet rsetstep1 = stmtForCouponsStep1.executeQuery();

				while (rsetstep1.next()) {
					PreparedStatement stmtForCouponsStep2 = con.prepareStatement("SELECT * FROM Coupons WHERE ID =? ");
					stmtForCouponsStep2.setInt(1, rsetstep1.getInt("COUPON_ID"));
					ResultSet rsetstep2 = stmtForCouponsStep2.executeQuery();
					rsetstep2.next();
					coupons.add(new Coupon(rsetstep2.getInt("COMPANY_ID"), rsetstep2.getInt("CATEGORY_ID"),
							rsetstep2.getString("TITLE"), rsetstep2.getString("DESCRIPTION"),
							rsetstep2.getDate("START_DATE"), rsetstep2.getDate("END_DATE"), rsetstep2.getInt("AMOUNT"),
							rsetstep2.getDouble("PRICE"), rsetstep2.getString("IMAGE")));

				}

				return new Customer(rset.getInt("ID"), rset.getString("FirstName"), rset.getString("LastName"),
						rset.getString("Email"), rset.getString("password"), coupons);

			}

		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

		return null;

	}

	@Override
	public boolean iseEmailExists(String email) throws CustomersDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT email FROM customers where email=?");
			stmt.setString(1, email);
			ResultSet rset = stmt.executeQuery();
			return (rset.next());

		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public int getCustomerID(String email, String password) throws CustomersDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement("select Id from customers WHERE Email=? and password = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rset = stmt.executeQuery();

			if (rset.next()) {
				return rset.getInt(1);
			}

		} catch (SQLException e) {
			throw new CustomersDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

		return -999;
	}
	
	
	}

