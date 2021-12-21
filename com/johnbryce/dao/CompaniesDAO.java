package com.johnbryce.dao;

import java.util.ArrayList;

import com.johnbryce.beans.Company;
import com.johnbryce.customException.CompaniesDBExpetion;

public interface CompaniesDAO {

	public  boolean isCompanyExists(String email, String password) throws CompaniesDBExpetion;

	public void addCompany(Company company) throws CompaniesDBExpetion;
	
	public void updatedCompany(Company company) throws CompaniesDBExpetion;
	
	public void deleteCompany(int companyID) throws CompaniesDBExpetion;
	
	public ArrayList<Company> getallCompanies() throws CompaniesDBExpetion;

	public Company getOneCompany(int CompanyID) throws CompaniesDBExpetion;
	
	public int getCompanyID (String email, String password) throws CompaniesDBExpetion;

}
