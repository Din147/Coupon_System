package com.johnbryce.facade.imp;

import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.customException.CustomersDBExpetion;
import com.johnbryce.customException.GenralException;
import com.johnbryce.enums.CATEGORY;
import com.johnbryce.facade.ClientFacade;

public class CustomerFacade extends ClientFacade {
	private int customerID;

	public CustomerFacade() {
	}

	/**
	 * public boolean login(); 1) purpose: to login by client to the system; 2)
	 * Description: expect to get: email (String) and password(String) check the
	 * conditions before login customer: a. check that the customer is exist. b. get
	 * the customerID - this will be use to all client action.
	 * 
	 * If the conditions are proper return true, else false.
	 * 
	 * 3) use: a. customersDAO.isCustomerExists()- expect to get: email(String) and
	 * password (String). b. customersDAO.getCustomerID() - expect to get:
	 * email(String) and password (String).
	 * 
	 * @throws GenralException
	 */

	@Override
	public boolean login(String email, String password) throws GenralException {
		if (email == null || password == null) {
			throw new GenralException("some information on: email and password are missing");
		} else {

			try {
				if (customersDAO.isCustomerExists(email, password)) {
					customerID = customersDAO.getCustomerID(email, password);
					return true;
				} else {
					return false;
					// add exception
				}
			} catch (CustomersDBExpetion e) {
				e.getMessage("CustomerFacade-login", "isCustomerExists/getCustomerID");
				return false;
			}
		}
	}

	/**
	 * public void purchaseCoupon(); 1) purpose: to purchase Coupon from company to
	 * customer; 2) Description: expect to get Coupon. check the conditions before
	 * purchase a Coupon: a. customer can't purchase Coupon with the same title
	 * twice. b. must be at least 1 amount of coupons and not expired.
	 * 
	 * If the conditions are proper, purchase the Coupon and update the coupons
	 * table.
	 * 
	 * 3) use: a. getCustomerCoupons() b. couponsDAO.getOnecoupons() - expect to get
	 * couponsID (int) c. System.currentTimeMillis() d.
	 * couponsDAO.addCouponPurchase() - expect to get customer's ID (int) and
	 * coupon's ID (int) e. couponsDAO.updateCoupon() - expect to get Coupon.
	 * @throws GenralException 
	 */

	public void purchaseCoupon(Coupon coupon) throws GenralException {

		if (coupon == null) {
			throw new GenralException(coupon);
		} else {
			Coupon OneCoupon = null;
			int checker = 0;

			for (Coupon coup : getCustomerCoupons()) {
				if (coup.getTitle().equals(coupon.getTitle())) {
					checker = 1;
					break;
				}
			}
			try {
				if (checker == 0) {
					OneCoupon = couponsDAO.getOnecoupons(coupon.getID());
					if (OneCoupon.getAmmount() <= 0 || OneCoupon.getEndDate().getTime() < System.currentTimeMillis()) {
						throw new GenralException("eror, you can't purchase coupon - no left cuopons or the end");
					} else {
						couponsDAO.addCouponPurchase(customerID, coupon.getID());
						OneCoupon.setAmmount(OneCoupon.getAmmount() - 1);
						couponsDAO.updateCoupon(OneCoupon);
					}
				} else {
					throw new GenralException("eror, you can't purchase coupon twice");
				}
			} catch (CouponsDBExpetion e) {
				e.getMessage("CustomerFacade-purchaseCoupon", "getOnecoupons/addCouponPurchase/updateCoupon");
			}
		}
	}

	/**
	 * public ArrayList<Coupon> getCustomerCoupons(); 1) purpose: to get Customer's
	 * Coupons from the DB; 2) Description: return ArrayList<Coupon> of the
	 * Customer's Coupons
	 * 
	 * 3) use: customersDAO.getOneCustomer() - expect to get customer ID (int)
	 */

	public ArrayList<Coupon> getCustomerCoupons() {
		try {
			return (ArrayList<Coupon>) customersDAO.getOneCustomer(customerID).getCoupons();
		} catch (CustomersDBExpetion e) {
			e.getMessage("CustomerFacade-getCustomerCoupons", "getOneCustomer");
			return null;
		}
	}

	/**
	 * public ArrayList<Coupon> getCustomerCoupons(); 1) purpose: to get Customer's
	 * Coupons from the DB filters by Category; 2) Description: expect to get
	 * category (category).
	 * 
	 * a. go over all the Customr's coupons. b. if there is match in the Category
	 * ID, it'll add to the returned ArrayList. c. return ArrayList<Coupon>.
	 * 
	 * 3) use: a. getCustomerCoupons() b. couponsDAO.getCategoryId() - expect to get
	 * Category;
	 */

	public ArrayList<Coupon> getCustomerCoupons(CATEGORY category) {
		List<Coupon> CustomerCoupons = new ArrayList<Coupon>();

		try {
			for (Coupon coup : getCustomerCoupons()) {
				if (couponsDAO.getCategoryId(category) == coup.getCategoryID()) {
					CustomerCoupons.add(coup);
				}
			}
		} catch (CouponsDBExpetion e) {
			e.getMessage("CustomerFacade-getCustomerCoupons", "getCategoryId");
		}
		return (ArrayList<Coupon>) CustomerCoupons;
	}

	/**
	 * public ArrayList<Coupon> getCustomerCoupons(); 1) purpose: to get Customer's
	 * Coupons from the DB filters by maxPrice; 2) Description: expect to get
	 * maxPrice (double).
	 * 
	 * a. go over all the Customer's coupons. b. if there is match ( <= maxPrice),
	 * it'll add to the returned ArrayList. c. return ArrayList<Coupon>.
	 * 
	 * 3) use: getCustomerCoupons()
	 */

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> CustomerCoupons = new ArrayList<Coupon>();

		for (Coupon coup : getCustomerCoupons()) {
			if (coup.getPrice() <= maxPrice) {
				CustomerCoupons.add(coup);
			}
		}
		return (ArrayList<Coupon>) CustomerCoupons;
	}

	/**
	 * public Customer getCustomerDetails(); 1) purpose: to get all Customer's
	 * detailed from the DB; 2) Description:
	 * 
	 * a. get from the DB the Customer, details. b. get from the DB the Coupons
	 * ArrayList. c. return Customer.
	 * 
	 * 3) use: a. customersDAO.getOneCustomer() - expect to get customerID (int) b.
	 * etCustomerCoupons().
	 * @throws GenralException 
	 */

	public Customer getCustomerDetails() throws GenralException {
		try {
			Customer customer = customersDAO.getOneCustomer(customerID);
			if (customer == null) {
				throw new GenralException(customer);
			}
			customer.setCoupons(getCustomerCoupons());
			return customer;
		} catch (CustomersDBExpetion e) {
			e.getMessage("CustomerFacade-getCustomerDetails", "getOneCustomer");
			return null;
		}
	}
}
