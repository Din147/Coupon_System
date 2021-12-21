package com.johnbryce.facade;

import com.johnbryce.customException.GenralException;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.dao.imp.CompaniesDBDAO;
import com.johnbryce.dao.imp.CouponsDBDAO;
import com.johnbryce.dao.imp.CustomersDBDAO;

public abstract class ClientFacade {

	protected CompaniesDAO companiesDAO = new CompaniesDBDAO();
	protected CustomersDAO customersDAO = new CustomersDBDAO();
	protected CouponsDAO couponsDAO = new CouponsDBDAO();

	public abstract boolean login(String email, String password) throws GenralException;

}
