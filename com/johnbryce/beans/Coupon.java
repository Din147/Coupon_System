package com.johnbryce.beans;

import java.util.Date;

public class Coupon {
	private int ID;
	private int companyID;
	private int categoryID;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int ammount;
	private double price;
	private String image;

	public Coupon(int ID, int companyID, int categoryID, String title, String description, Date startDate, Date endDate,
			int ammount, double price, String image) {
		this.ID = ID;
		this.companyID = companyID;
		this.categoryID = categoryID;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ammount = ammount;
		this.price = price;
		this.image = image;
	}

	public Coupon(int companyID, int categoryID, String title, String description, Date startDate, Date endDate,
			int ammount, double price, String image) {
		this(0, companyID, categoryID, title, description, startDate, endDate, ammount, price, image);
	}

	public Coupon(int ID, int companyID, int categoryID, String title, String description, Date startDate, Date endDate,
			int ammount, double price) {
		this(ID, companyID, categoryID, title, description, startDate, endDate, ammount, price, null);
	}

	public Coupon(int companyID, int categoryID, String title, String description, Date startDate, Date endDate,
			int ammount, double price) {
		this(0, companyID, categoryID, title, description, startDate, endDate, ammount, price, null);
	}

	public Coupon(int companyID, int categoryID, String title, Date startDate, Date endDate, int ammount,
			double price) {
		this(0, companyID, categoryID, title, null, startDate, endDate, ammount, price, null);
	}

	public Coupon() {
	};

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {

		return new java.sql.Date(startDate.getTime());
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return new java.sql.Date(endDate.getTime());
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [ID=" + ID + ", companyID=" + companyID + ", categoryID=" + categoryID + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", ammount="
				+ ammount + ", price=" + price + ", image=" + image + "]";
	}
}
