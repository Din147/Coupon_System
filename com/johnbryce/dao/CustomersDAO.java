package com.johnbryce.dao;

import java.util.ArrayList;

import com.johnbryce.beans.Customer;
import com.johnbryce.customException.CustomersDBExpetion;

public interface CustomersDAO {

	public boolean isCustomerExists(String email, String password) throws CustomersDBExpetion;

	public void addCustomer(Customer customer) throws CustomersDBExpetion;

	public void updatedCustomer(Customer customer) throws CustomersDBExpetion;

	public void deleteCustomer(int customerID) throws CustomersDBExpetion;

	public ArrayList<Customer> getAllCustomers() throws CustomersDBExpetion;

	public Customer getOneCustomer(int customerID) throws CustomersDBExpetion;

	public boolean iseEmailExists(String email) throws CustomersDBExpetion;

	public int getCustomerID(String email, String password) throws CustomersDBExpetion;

}
