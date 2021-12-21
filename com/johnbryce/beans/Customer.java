package com.johnbryce.beans;

import java.util.List;

public class Customer {
	private int ID;
	private String firstName;
	private String lasrName;
	private String email;
	private String password;
	private List<Coupon> coupons;

	public Customer(int ID, String firstName, String lasrName, String email, String password, List<Coupon> coupons) {
		this.ID = ID;
		this.firstName = firstName;
		this.lasrName = lasrName;
		this.email = email;
		this.password = password;
		this.coupons = coupons;

	}

	public Customer(String firstName, String lasrName, String email, String password, List<Coupon> coupons) {

	}

	public Customer(int ID, String firstName, String lasrName, String email, String password) {
		this(ID, firstName, lasrName, email, password, null);
	}

	public Customer(String firstName, String lasrName, String email, String password) {
		this(0, firstName, lasrName, email, password, null);
	}

	public Customer() {
	}

	public int getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasrName() {
		return lasrName;
	}

	public void setLasrName(String lasrName) {
		this.lasrName = lasrName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", firstName=" + firstName + ", lasrName=" + lasrName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons + "]";
	}

}
