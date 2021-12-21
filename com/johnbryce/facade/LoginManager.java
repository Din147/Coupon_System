package com.johnbryce.facade;

import com.johnbryce.customException.GenralException;
import com.johnbryce.enums.ClientType;
import com.johnbryce.facade.imp.AdminFacade;
import com.johnbryce.facade.imp.CompanyFacade;
import com.johnbryce.facade.imp.CustomerFacade;

public class LoginManager {

	private static LoginManager instance;

	private LoginManager() {
	}

	
	/**
	 * public LoginManager getInstance(); 
	 * 1) purpose: to allow to each client (Admin, customers and company) to sign in.; 
	 * 2) Description: this make this class singleton
	 * 
	 */
	
	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}

		}
		return instance;
	}

	/**
	 * public  ClientFacade login(); 
	 * 1) purpose: to allow to each client (admin, customers and company) to sign in; 
	 * 2) Description: expect to get-  email(String), password (String), client Type (ClientType)
	 *    Check the conditions before return client type (with access to the system): 
	 *    a. check which type is client.
	 *    b. check if client can login successful.
	 *   
	 *   If the conditions are proper return client. 
	 * 
	 * 3) use: 
	 *    a. admin.login(email, password))- expect to get: email(String) and password (String).
	 *    a. company.login(email, password))- expect to get: email(String) and password (String).
	 *    a. customer.login(email, password))- expect to get: email(String) and password (String).
	 * @throws GenralException 
	 */
	
	
	public ClientFacade login(String email, String password, ClientType clientType) throws GenralException {

		
		if (clientType == ClientType.ADMNISTRATOR) {
			AdminFacade admin = new AdminFacade();
			if (admin.login(email, password)) {
				//throw exeption
				return admin;
			}
		}
		if (clientType == ClientType.COMPANY) {
			CompanyFacade company = new CompanyFacade();
			if (company.login(email, password)) {
				return company;
			}
		}
		if (clientType == ClientType.CUSTOMER) {
			CustomerFacade customer = new CustomerFacade();
			if (customer.login(email, password)) {
				return customer;
			}
		}
		//System.out.println("you faild to login"); throw exception
		return null;

	}
}