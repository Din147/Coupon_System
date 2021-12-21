package com.johnbryce.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Coupon;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.database.ConnectionPool;
import com.johnbryce.enums.CATEGORY;

public class CouponsDBDAO implements CouponsDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws CouponsDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement(
					"Insert into coupons(COMPANY_ID,CATEGORY_ID,title,description,START_DATE,END_DATE,AMOUNT,price,image) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, coupon.getCompanyID());
			stmt.setInt(2, coupon.getCategoryID());
			stmt.setString(3, coupon.getTitle());
			stmt.setString(4, coupon.getDescription());
			stmt.setDate(5, (Date) coupon.getStartDate());
			stmt.setDate(6, (Date) coupon.getEndDate());
			stmt.setInt(7, coupon.getAmmount());
			stmt.setDouble(8, coupon.getPrice());
			stmt.setString(9, coupon.getImage());
			stmt.execute();

		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	/**
	 * update coupon by title. title is the "ID" of coupon.
	 * 
	 * @throws CouponsDBExpetion
	 */

	@Override
	public void updateCoupon(Coupon coupon) throws CouponsDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE coupons SET COMPANY_ID =? ,CATEGORY_ID =? ,DESCRIPTION =? ,START_DATE =? ,END_DATE =? ,AMOUNT =? ,PRICE =? ,IMAGE =? where title = ?");
			stmt.setInt(1, coupon.getCompanyID());
			stmt.setInt(2, coupon.getCategoryID());
			stmt.setString(3, coupon.getDescription());
			stmt.setDate(4, (Date) coupon.getStartDate());
			stmt.setDate(5, (Date) coupon.getEndDate());
			stmt.setInt(6, coupon.getAmmount());
			stmt.setDouble(7, coupon.getPrice());
			stmt.setString(8, coupon.getImage());
			stmt.setString(9, coupon.getTitle());
			stmt.execute();

		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

	}

	/**
	 * delete coupon from DB and from internal List
	 * 
	 * @throws CouponsDBExpetion
	 */
	@Override
	public void deleteCoupon(int couponID) throws CouponsDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM coupons WHERE ID= ?");
			stmt.setInt(1, couponID);
			stmt.execute();

		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllcoupons() throws CouponsDBExpetion {
		List<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from coupons");
			ResultSet rset = stmt.executeQuery();

			while (rset.next()) {
				coupons.add(new Coupon(rset.getInt("ID"), rset.getInt("COMPANY_ID"), rset.getInt("CATEGORY_ID"),
						rset.getString("TITLE"), rset.getString("DESCRIPTION"), rset.getDate("START_DATE"),
						rset.getDate("END_DATE"), rset.getInt("AMOUNT"), rset.getDouble("PRICE"),
						rset.getString("IMAGE")));

			}
		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}

		return (ArrayList<Coupon>) coupons;

	}

	@Override
	public Coupon getOnecoupons(int couponID) throws CouponsDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from coupons WHERE ID=" + couponID + "");
			ResultSet rset = stmt.executeQuery();
			rset.next();
			return new Coupon(rset.getInt("ID"), rset.getInt("COMPANY_ID"), rset.getInt("CATEGORY_ID"),
					rset.getNString("TITLE"), rset.getNString("DESCRIPTION"), rset.getDate("START_DATE"),
					rset.getDate("END_DATE"), rset.getInt("AMOUNT"), rset.getDouble("PRICE"), rset.getString("IMAGE"));

		} catch (SQLException e) {
			throw new CouponsDBExpetion();

		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) throws CouponsDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con
					.prepareStatement("Insert into customers_vs_coupons(CUSTOMER_ID,COUPON_ID) values(?,?)");
			stmt.setInt(1, customerID);
			stmt.setInt(2, couponID);
			stmt.execute();
		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public void deletCouponPurchase(int customerID, int couponID) throws CouponsDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con
					.prepareStatement("DELETE * FROM couponscustomers_vs_coupons  WHERE CUSTOMER_ID= " + customerID
							+ "AND COUPON_ID = " + couponID + "");
			stmt.execute();

		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	/**
	 * i add this
	 * 
	 * @throws CouponsDBExpetion
	 */
	@Override
	public boolean isCouponExsist(String couponTitle, int CompanyID) throws CouponsDBExpetion {
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con
					.prepareStatement("select TITLE, COMPANY_ID from coupons  where TITLE = ? AND COMPANY_ID=?");
			stmt.setString(1, couponTitle);
			stmt.setInt(2, CompanyID);
			stmt.execute();
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				return true;
			}

		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
		return false;
	}

	@Override
	public void deletCouponPurchasedHistory(int couponID) throws CouponsDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM customers_vs_coupons WHERE COUPON_ID= ? ");
			stmt.setInt(1, couponID);
			stmt.execute();
		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}

	/**
	 * i add this, this will help me
	 * 
	 * @throws CouponsDBExpetion
	 */

	@Override
	public int getCategoryId(CATEGORY category) throws CouponsDBExpetion {
		Connection con = cp.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from categories where Name =?");
			stmt.setString(1, category.toString());
			ResultSet rset = stmt.executeQuery();
			rset.next();
			return rset.getInt("ID");
		} catch (SQLException e) {
			throw new CouponsDBExpetion();
		} finally {
			cp.restoreConnection(con);
		}
	}
}
