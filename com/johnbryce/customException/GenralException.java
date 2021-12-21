package com.johnbryce.customException;

import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;

public class GenralException extends Exception {
	String str = null;
	Object ob = null;

	public GenralException(String str) {
		this.str = str;
	}

	public GenralException(Coupon coupon) {
		ob = coupon;
	}

	public GenralException(Customer customer) {
		ob = customer;
	}

	public void getGenralException() {
		if ((str != null)) {
			System.out.println(str);
		}
		if (ob instanceof Coupon) {
			System.out.println("the coupon is null- this is not valid");
		}
		if (ob instanceof Customer) {
			System.out.println("the customer is null- this is not valid");
		}

	}

}
