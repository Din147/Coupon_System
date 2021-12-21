package com.johnbryce.test.createsObjcetForTest;

import com.johnbryce.beans.Company;

public class CreateTestCompnies {

	public static Company getTestCompany1() {
		return new Company("Samsung", "Samsung@gmail.com", "Samsung123");

	}

	public static Company getTestCompany2() {
		return new Company("apple", "apple@gmail.com", "apple123");

	}

	public static Company getTestCompany3() {
		return new Company("Din", "Din@gmail.com", "Din123");

	}

	public static Company getTestCompany4() {
		return new Company("google", "google@gmail.com", "google123");

	}

	/*
	 * this is for update company 3
	 */
	public static Company getTestCompany5() {
		return new Company(3, "Din", "DinUpdated@gmail.com", "Din123");

	}

}
