package com.johnbryce.customException;

public class CouponsDBExpetion extends Exception {

	public void getMessage(String location, String action) {

		System.out.println(
				"There is a problem with the connection to the Data Base:\n-Class: CouponsDBDAO\n-Location of exception: "
						+ location + "\n-Action faild: " + action + "\n");
	}

}
