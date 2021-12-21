package com.johnbryce.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Company;
import com.johnbryce.customException.CompaniesDBExpetion;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.database.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {
	
	private ConnectionPool cp = ConnectionPool.getInstance();

	
	
	@Override
	public boolean isCompanyExists(String email, String password) throws CompaniesDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT email, password FROM companies where email=? and password=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rset = stmt.executeQuery();
			return rset.next(); /// maby to cheak in not null;

		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public void addCompany(Company company) throws CompaniesDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("Insert into companies (name, email, password) values (?, ?, ?)");

			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());
			stmt.execute();
		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

	}

	/**
	 * update Company by email
	 * @throws CompaniesDBExpetion 
	 */
	@Override
	public void updatedCompany(Company company) throws CompaniesDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE companies SET Email=?, password =? where name =?");

			stmt.setString(3, company.getName());
			stmt.setString(2, company.getPassword());
			stmt.setString(1, company.getEmail());
			stmt.execute();

		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	/*
	 * delete companies from DB and from internal List
	 */
	@Override
	public void deleteCompany(int companyID) throws CompaniesDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM companies WHERE ID = ?");
			stmt.setInt(1, companyID);
			stmt.execute();

		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Company> getallCompanies() throws CompaniesDBExpetion {
		List<Company> companies = new ArrayList<Company>();
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from companies");
			ResultSet rest = stmt.executeQuery();
			while (rest.next()) {
				companies.add(new Company(rest.getString("Name"), rest.getString("Email"), rest.getString("password")));
			}
		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

		return (ArrayList<Company>) companies;
	}

	@Override
	public Company getOneCompany(int CompanyID) throws CompaniesDBExpetion {
		Connection con = cp.getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement("select * from companies WHERE ID=?");
			stmt.setInt(1, CompanyID);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				return new Company(rset.getString("name"), rset.getString("Email"), rset.getString("password"));
			}

		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
		return null;
	}

	@Override
	public int getCompanyID(String email, String password) throws CompaniesDBExpetion {
		Connection con = cp.getConnection();

		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("select Id from companies WHERE Email=? and password = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rset = stmt.executeQuery();

			if (rset.next()) {
				return rset.getInt(1);
			}

		} catch (SQLException e) {
			throw new CompaniesDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

		return -999;
	}

}
