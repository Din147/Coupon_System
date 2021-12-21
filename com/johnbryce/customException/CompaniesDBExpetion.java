package com.johnbryce.customException;

public class CompaniesDBExpetion extends Exception {

	public void getMessage(String location, String action) {

		System.out.println(
				"There is a problem with the connection to the Data Base:\n-Class: CompaniesDBDAO\n-Location of exception: "
						+ location + "\n-Action faild: " + action + "\n");
	}

}
