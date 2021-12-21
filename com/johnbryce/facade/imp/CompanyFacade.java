package com.johnbryce.facade.imp;

import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.customException.CompaniesDBExpetion;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.customException.CustomersDBExpetion;
import com.johnbryce.customException.GenralException;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.imp.CompaniesDBDAO;
import com.johnbryce.dao.imp.CouponsDBDAO;
import com.johnbryce.dao.imp.CustomersDBDAO;
import com.johnbryce.enums.CATEGORY;
import com.johnbryce.facade.ClientFacade;

public class CompanyFacade extends ClientFacade {
	private int companyId;

	public CompanyFacade() {
	}

	/**
	 * public boolean login(); 
	 * 1) purpose: to login by client to the system; 
	 * 2) Description: expect to get: email (String) and password(String) check the conditions before login company: 
	 *    a. check that the Company is exist. 
	 *    b. get the CompanyID of the system- this will be use to all client action.
	 * 
	 * If the conditions are proper return true, else false.
	 * 
	 * 3) use: 
	 * a. companiesDAO.isCompanyExists()- expect to get: email(String) and password (String). 
	 * b. companiesDAO.getCompanyID() - expect to get: email(String) and password (String).
	 * 
	 * @throws GenralException
	 */

	@Override
	public boolean login(String email, String password) throws GenralException {

		if (email == null || password == null) {
			throw new GenralException("some information on: email and password are missing");
		} else {
			try {
				if (companiesDAO.isCompanyExists(email, password)) {
					companyId = companiesDAO.getCompanyID(email, password);
					return true;
				} else {
					System.out.println("login faild"); 
					return false;
				}
			} catch (CompaniesDBExpetion e) {
				e.getMessage("CompanyFacade-login", "isCompanyExists/getCompanyID");
				return false;
			}
		}
	}

	/**
	 * public void addCoupon(); 
	 * 1) purpose: to add Coupon to the DB; 
	 * 2) Description: expect to get Coupon, check the conditions before add a Coupon:
	 *   a. can't add Coupon with the same title that already exist. 
	 *   b. can't add Coupon with the companyID email that already exist.
	 * 
	 * If the conditions are proper, add Coupon to the DB.
	 * 
	 * 3) use:
	 *  a. couponsDAO.isCouponExsist() - expect to get: Coupon Title (String) and Coupon CompanyID (int) 
	 *  b. couponsDAO.addCoupon() - expect to get Coupon.
	 * 
	 * @throws GenralException
	 */

	public void addCoupon(Coupon coupon) throws GenralException {

		if (coupon == null) {
			throw new GenralException(coupon);
		} else {
			try {
				if (!couponsDAO.isCouponExsist(coupon.getTitle(), coupon.getCompanyID())) {
					couponsDAO.addCoupon(coupon);
				} else {
					System.out.println("can't add cupon when there is the same title and the same companyID");
				}
			} catch (CouponsDBExpetion e) {
				e.getMessage("CompanyFacade-addCoupon", "addCoupon or is coupon Exsist");
			}
		}
	}

	/**
	 * public void UpdatedCoupon(); 
	 * 1) purpose: to update Coupon in the DB; 
	 * 2) Description:expect to get Coupon, check the conditions before updated a Coupon: 
	 * a. can't update Coupon Title. 
	 * b. can't update Coupon CompanyID.
	 * 
	 * If the conditions are proper, updated the Coupon to the DB.
	 * 
	 * 3) use: 
	 * a. couponsDAO.isCouponExsist -expect to get: Coupon Title (String) and Coupon CompanyID (int) 
	 * b. couponsDAO.updateCoupon(coupon) -expect to get Coupon.
	 * 
	 * @throws GenralException
	 */

	public void UpdatedCoupon(Coupon coupon) throws GenralException {

		if (coupon == null) {
			throw new GenralException(coupon);
		} else {
			try {
				if (couponsDAO.isCouponExsist(coupon.getTitle(), coupon.getCompanyID())) {
					couponsDAO.updateCoupon(coupon);

				} else {
				System.out.println("coupon title can't update the title or the same company ID");
				}
			} catch (CouponsDBExpetion e) {
				e.getMessage("CompanyFacade-UpdatedCoupon", "updatedCoupon");

			}
		}
	}

	/**
	 * public void DeleteCoupon(); 
	 * 1) purpose: to delete Coupon from the DB; 
	 * 2) Description:expect to get couponId (int). 
	 *   a. delete all coupons (of company) from customers. 
	 *   b. delete the purchase history of the coupons 
	 *   c. delete the coupon from coupons table
	 * 
	 * 3) use: 
	      a. getAllCustomer() 
	      b. customer.getCoupons() 
	      c. coupon.getCompanyID()
	      d. couponsDAO.deletCouponPurchase- expect to get: customerID (int) and couponID (int) 
	      e. cuponsDAO.deleteCoupon() - expect to get: couponId (int)
	 * 
	 * @throws GenralException
	 */

	public void DeleteCoupon(int couponId) throws GenralException {
		if (couponId <= 0) {
			throw new GenralException("couponId can't less than 1 ");
		} else {
			try {
				for (Customer customer : customersDAO.getAllCustomers()) {
					for (Coupon coupon : customer.getCoupons())
						if (coupon.getID() == couponId) {
							customer.getCoupons().remove(coupon);
						}
				}
				couponsDAO.deletCouponPurchasedHistory(couponId);
				couponsDAO.deleteCoupon(couponId);
			} catch (CouponsDBExpetion e) {
				e.getMessage("CompanyFacade-DeleteCoupon", "deleteCoupon/deletCouponPurchasedHistory");
			} catch (CustomersDBExpetion e) {
				e.getMessage("CompanyFacade-DeleteCoupon", "getAllCustomers");
			}
		}
	}

	/**
	 * public ArrayList<Coupon> getCompanyCoupons(); 
	    1) purpose: to get Company's Coupons from the DB; 
	    2) Description:
	     a. go over all the coupons in the DB. 
	     b. if there is match in the companyID, it'll add to the returned ArrayList. 
	     c. return ArrayList<Coupon>.
	 
	    3) use: couponsDAO.getAllcoupons()
	 */

	public ArrayList<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		try {
			for (Coupon com : couponsDAO.getAllcoupons()) {
				if (com.getCompanyID() == companyId) {
					coupons.add(com);
				}
			}
		} catch (CouponsDBExpetion e) {
			e.getMessage("CompanyFacade-getCompanyCoupons", "getAllcoupons");
		}
		return (ArrayList<Coupon>) coupons;
	}

	/**
	 * public ArrayList<Coupon> getCompanyCoupons(); 
	  1) purpose: to get Company's Coupons from the DB filters by Category; 
	  2) Description: expect to get category (category).
	     a. go over all the company's coupons. 
	     b. if there is match in the Category ID, it'll add to the returned ArrayList. 
	     c. return ArrayList<Coupon>. 
	   3) use: 
	   a. getCompanyCoupons() 
	   b. couponsDAO.getCategoryId() - expect to get Category;
	 */

	public ArrayList<Coupon> getCompanyCoupons(CATEGORY category) {
		List<Coupon> CompanyCouponCategory = new ArrayList<Coupon>();
		try {
			for (Coupon com : getCompanyCoupons()) {
				if (com.getCategoryID() == couponsDAO.getCategoryId(category)) {
					CompanyCouponCategory.add(com);
				}
			}
		} catch (CouponsDBExpetion e) {
			e.getMessage("CompanyFacade-getCompanyCoupons", "getCategoryId");
		}

		return (ArrayList<Coupon>) CompanyCouponCategory;
	}

	/**
	 * public ArrayList<Coupon> getCompanyCoupons(); 
	   1) purpose: to get Company's Coupons from the DB filters by maxPrice; 
	   2) Description: expect to get maxPrice (double).
	      a. go over all the company's coupons. 
	      b. if there is match ( < maxPrice), it'll add to the returned ArrayList. 
	      c. return ArrayList<Coupon>.
	 * 
	 * 3) use: 
	 * getCompanyCoupons()
	 */

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> CompanyCouponbyprice = new ArrayList<Coupon>();
		for (Coupon com : getCompanyCoupons()) {
			if (com.getPrice() <= maxPrice) {
				CompanyCouponbyprice.add(com);
			}
		}
		return (ArrayList<Coupon>) CompanyCouponbyprice;
	}

	/**
	 * public Company getCompanyDetails(); 
	 * 1) purpose: to get all Company's detailed from the DB; 
	 * 2) Description:
	    a. get from the DB the Company's details. 
	    b. get from the DB the Coupons ArrayList. 
	    c. return Company.
	 * 
	 * 3) use: 
	 * a. companiesDAO.getOneCompany- expect to get: companyId (int) 
	 * b. getCompanyCoupons().
	 * @throws GenralException 
	 */

	public Company getCompanyDetails() throws GenralException {
		try {
			Company com = companiesDAO.getOneCompany(companyId);
			if (com == null) {
				throw new GenralException("there is no Companies with this" + companyId);
			}else {
				com.setCoupons(getCompanyCoupons());
				return com;
			}
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CompanyFacade-getCompanyDetails", "getOneCompany");
			return null;
		}
	}
}
