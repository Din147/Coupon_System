package com.johnbryce.beans;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private int id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Coupon> coupons;

	public void setId(int id) {
		this.id = id;
	}

	public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Company(int id, String name, String email, String password) {
		this(id, name, email, password, null);
	}

	public Company(String name, String email, String password) {
		this(0, name, email, password, null);
	}

	public Company() {
	};

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Coupon> getCoupons() {
		return coupons = new ArrayList<Coupon>();
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", email=" + email + ", password=" + password + ", coupons=" + coupons + "]";
	}

}
