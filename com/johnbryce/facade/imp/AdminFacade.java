package com.johnbryce.facade.imp;

import java.util.ArrayList;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.customException.CompaniesDBExpetion;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.customException.CustomersDBExpetion;
import com.johnbryce.customException.GenralException;
import com.johnbryce.facade.ClientFacade;

public class AdminFacade extends ClientFacade {

	public AdminFacade() {
	}

	/**
	 * public boolean login(String email, String password). get password and email
	 * by user (in this case it's hard coded data). This is always return true.
	 */

	@Override
	public boolean login(String email, String password) {
		email = "admin@admin.com";
		password = "admin";
		return true;
	}

	/**
	 * public void addCompany(); 1) purpose: to add Company to the DB; 2)
	 * Description: expect to get Company, check the conditions before add a
	 * company: a. can't add company with the same name that already exist. b. can't
	 * add company with the same email that already exist.
	 * 
	 * If the conditions are proper, add company to the DB.
	 * 
	 * 3) use: a. companiesDAO.getallCompanies(). b. companiesDAO.addCompany() -
	 * expect to get Company.
	 * 
	 * @throws GenralException
	 */

	public void addCompany(Company company) throws GenralException {
		try {
			int checker = 0;

			for (Company com : companiesDAO.getallCompanies()) {
				if (com.getEmail().equals(company.getEmail()) && com.getName().equals(company.getName())) {
					checker = 1;
					break;
				}
			}
			if (checker == 0) {
				companiesDAO.addCompany(company);
			} else {
				throw new GenralException("company: name " + company.getName() + " and email " + company.getEmail()
						+ " are already exsist");
			}
		} catch (CompaniesDBExpetion e) {
			e.getMessage("AdminFacade-addCompany", "getallCompanies/addCompany");
		}
	}

	/**
	 * public void UpdatedCompany(); 1) purpose: to update Company in the DB; 2)
	 * Description:exepct to get Company, check the conditions before updated a
	 * company: a. can't update company name. b. can't update company email.
	 * 
	 * If the conditions are proper, updated the company to the DB.
	 * 
	 * 3) use: a. companiesDAO.getOneCompany() -expect to get CompanyiD (int) b.
	 * companiesDAO.updatedCompany() -expect to get Company.
	 * 
	 * @throws GenralException
	 */

	public void updatedCompany(Company company) throws GenralException {
		try {
			Company com = companiesDAO.getOneCompany(company.getId());
			if (com.getName().equals(company.getName()) && com.getPassword().equals(company.getPassword())) {
				companiesDAO.updatedCompany(company);
			} else {
				throw new GenralException("you can't change company's password or name");
			}
		} catch (CompaniesDBExpetion e) {
			e.getMessage("AdminFacade-updatedCompany", "getOneCompany/updatedCompany");
		}
	}

	/**
	 * public void deleteCompany(); 1) purpose: to delete Company from the DB; 2)
	 * Description:exepct to get CompanyID (int). a. delete all coupons (of company)
	 * from customers. b. delete all coupons (of company) from purchase table. c.
	 * delete company.
	 * 
	 * 3) use: a. getAllCustomer(). b. customer.getCoupons().
	 * c.companiesDAO.deleteCompany() expect to get CompanyId (int)
	 */

	public void deleteCompany(int companyID) {
		try {
			for (Customer customer : getAllCustomer()) {
				for (Coupon coupon : customer.getCoupons()) {
					if (coupon.getCompanyID() == companyID) {
						couponsDAO.deletCouponPurchase(customer.getID(), coupon.getID());
					}
					customer.getCoupons().remove(coupon);
				}
			}
			companiesDAO.deleteCompany(companyID);
		} catch (CouponsDBExpetion e) {
			e.getMessage("AdminFacage-deleteCompany", "deletCouponPurchase");
		} catch (CompaniesDBExpetion e) {
			e.getMessage("AdminFacage-deleteCompany", "deleteCompany");
		}
	}

	/**
	 * public Company getOneCompany(); 1) purpose: to getOneCompany from the DB; 2)
	 * Description:exepct to get CompanyID (int). return Company that selected by
	 * Company ID.
	 * 
	 * 3) use: companiesDAO.getOneCompany() expect to get CompanyId (int)
	 * 
	 * @throws GenralException
	 */

	public Company getOneCompany(int companyID) throws GenralException {

		if (companyID <= 0) {
			throw new GenralException("companyID cant less than 1");
		} else {

			try {
				return companiesDAO.getOneCompany(companyID);
			} catch (CompaniesDBExpetion e) {
				e.getMessage("AdminFacage-getOneCompany", "getOneCompany");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * public ArrayList<Company> getAllCompany(); 1) purpose: to getAllCompany from
	 * the DB; 2) Description" return All Companies from the DB in an ArrayList.
	 * 
	 * 3) use: companiesDAO.getallCompanies().
	 */

	public ArrayList<Company> getAllCompany() {
		try {
			return companiesDAO.getallCompanies();
		} catch (CompaniesDBExpetion e) {
			e.getMessage("AdminFacage-getAllCompany", "getallCompanies");
		}
		return null;

	}

	/**
	 * public void addCustomer(); 1) purpose: to add Customer to the DB; 2)
	 * Description: expect to get Customer, check the conditions before add a
	 * Customer: a. can't add Customer with the Email that already exist.
	 * 
	 * If the condition are proper, add Customer to the DB.
	 * 
	 * 3) use: a. customersDAO.iseEmailExists() - expect to get Customer's email
	 * (String) b. customersDAO.addCustomer() - expect to get customer.
	 * 
	 * @throws GenralException
	 */

	public void addCustomer(Customer customer) throws GenralException {
		if (customer == null) {
			throw new GenralException(customer);
		} else {
			try {
				if (!customersDAO.iseEmailExists(customer.getEmail())) {
					customersDAO.addCustomer(customer);
				} else {
					// exception
				}
			} catch (CustomersDBExpetion e) {
				e.getMessage("AdminFacade-addCustomer", "iseEmailExists/addCustomer");
			}
		}
	}

	/**
	 * public void updatedCustomer(); 1) purpose: to update Customer in the DB; 2)
	 * Description:exepct to get Customer, check the condition before updated a
	 * Customer: can't update Customer password.
	 * 
	 * If the condition are proper, updated the Customer to the DB.
	 * 
	 * 3) use: a. getOneCustomer() -expect to get password (String). b.
	 * customersDAO.updatedCustomer -expect to get Customer.
	 * 
	 * @throws GenralException
	 */

	public void updatedCustomer(Customer customer) throws GenralException {
		if (customer == null) {
			throw new GenralException(customer);
		} else {
			try {
				if (getOneCustomer(customer.getID()).getPassword().equals(customer.getPassword())) {
					customersDAO.updatedCustomer(customer);
				} else {
					// exception
				}
			} catch (CustomersDBExpetion e) {
				e.getMessage("AdminFacade-updatedCustomer", "updatedCustomer");
			}
		}
	}

	/**
	 * public void deleteCustomer(); 1) purpose: to delete Customer from the DB; 2)
	 * Description:exepct to get CustomerID (int). a. check if customer is exist-
	 * not null a. delete all coupons (of Customer) from purchase table. b. delete
	 * customer cash coupons list. c. delete customer.
	 * 
	 * 3) use:
	 * 
	 * a. getOneCustomer() - expect to get CustomerID(int). b.
	 * couponsDAO.deletCouponPurchase() - expect to get CustomerID(int). c.
	 * customersDAO.deleteCustomer() - expect to get CustomerID (int).
	 * 
	 * @throws GenralException
	 */

	public void deleteCustomer(int CustomerID) throws GenralException {
		if (CustomerID <= 0) {
			throw new GenralException("CustomerID can be less than 1");
		} else {

			try {
				Customer customer = getOneCustomer(CustomerID);
				if (customer == null) {
					throw new GenralException(customer);
				} else {
					for (Coupon coupon : customer.getCoupons()) {
						couponsDAO.deletCouponPurchase(CustomerID, coupon.getID());
					}
					customer.getCoupons().clear();
					customersDAO.deleteCustomer(CustomerID);
				}
			} catch (CouponsDBExpetion e) {
				e.getMessage("AdminFacade-deleteCustomer", "deletCouponPurchase");
			} catch (CustomersDBExpetion e) {
				e.getMessage("AdminFacade-deleteCustomer", "deleteCustomer");
			}
		}
	}

	/**
	 * public ArrayList<Company> getAllCustomer(); 1) purpose: to get All Customers
	 * from the DB; 2) Description: return All Customers from the DB in an
	 * ArrayList.
	 * 
	 * 3) use: customersDAO.getAllCustomers().
	 */

	public ArrayList<Customer> getAllCustomer() {
		try {
			return customersDAO.getAllCustomers();
		} catch (CustomersDBExpetion e) {
			e.getMessage("AdminFacade-getAllCustomer", "getAllCustomers");
			return null;
		}
	}

	/**
	 * public Company getOneCustomer(); 1) purpose: to get One Customer from the DB;
	 * 2) Description:exepct to get CustomerID (int). return Customer that selected
	 * by Customer ID.
	 * 
	 * 3) use: customersDAO.getOneCustomer() - expect to get CustomerID (int)
	 * 
	 * @throws GenralException
	 */

	public Customer getOneCustomer(int CustomerID) throws GenralException {
		if (CustomerID <= 0) {
			throw new GenralException("CustomerID can be less than 1");
		} else {
			try {
				return customersDAO.getOneCustomer(CustomerID);
			} catch (CustomersDBExpetion e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
