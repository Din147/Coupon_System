package com.johnbryce.test.createsObjcetForTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.customException.CompaniesDBExpetion;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.dao.imp.CompaniesDBDAO;
import com.johnbryce.dao.imp.CouponsDBDAO;
import com.johnbryce.dao.imp.CustomersDBDAO;
import com.johnbryce.enums.CATEGORY;

public class CreateTestCoupons {

	SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");

	public CreateTestCoupons() {

	}

	public static Coupon getTestCoupon1() { // change the trow to date exeption
		try {
			CouponsDAO couponsDAO = new CouponsDBDAO();
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			return new Coupon(companiesDAO.getCompanyID("apple@gmail.com", "apple123"),
					couponsDAO.getCategoryId(CATEGORY.FOOD), "pizza", "1+1 pizza slice", testDate.parse("01-10-2021"),
					testDate.parse("01-01-2022"), 100, 10.5, "pizza Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon2() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("Samsung@gmail.com", "Samsung123"),
					couponsDAO.getCategoryId(CATEGORY.ELECTRICITY), "Smartphone", "save 10%",
					testDate.parse("01-10-2021"), testDate.parse("01-04-2022"), 50, 1000.5, "Galaxy Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon2", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon3() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("Din@gmail.com", "Din123"),
					couponsDAO.getCategoryId(CATEGORY.VACATION), "Rome", "30% disscount", testDate.parse("01-10-2021"),
					testDate.parse("11-01-2022"), 100, 10.5, "Rome Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon3", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon4() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("google@gmail.com", "google123"),
					couponsDAO.getCategoryId(CATEGORY.ELECTRICITY), "pixel", "save 12%", testDate.parse("01-01-2022"),
					testDate.parse("01-03-2022"), 180, 50.5, "pixel Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon4", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon5() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("apple@gmail.com", "apple123"),
					couponsDAO.getCategoryId(CATEGORY.FOOD), "Resturant", "50% disscount all the menu",
					testDate.parse("01-10-2021"), testDate.parse("01-01-2022"), 100, 99.4, "Restureant Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon5", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon6() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("apple@gmail.com", "apple123"),
					couponsDAO.getCategoryId(CATEGORY.FOOD), "Resturant", "50% disscount all the menu",
					testDate.parse("01-11-2021"), testDate.parse("01-11-2022"), 100, 100, "Restureant Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon6", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon7() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("google@gmail.com", "google123"),
					couponsDAO.getCategoryId(CATEGORY.RESTAURANT), "Google best food", "save 10%",
					testDate.parse("01-01-2022"), testDate.parse("01-11-2022"), 1000, 100.5, "pixelfood Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon7", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon8() {
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("google@gmail.com", "google123"),
					couponsDAO.getCategoryId(CATEGORY.VACATION), "Google best hotel", "save 30%",
					testDate.parse("01-01-2022"), testDate.parse("19-03-2022"), 1000, 100.5, "pixel Image");
		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon8", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}

	public static Coupon getTestCoupon9() { // 9 will update 8
		try {
			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CompaniesDAO companiesDAO = new CompaniesDBDAO();
			CouponsDAO couponsDAO = new CouponsDBDAO();
			return new Coupon(companiesDAO.getCompanyID("google@gmail.com", "google123"),
					couponsDAO.getCategoryId(CATEGORY.VACATION), "Google best hotel", "save 99.99%",
					testDate.parse("01-01-2022"), testDate.parse("19-03-2022"), 0, 100.5, "pixelHotel Image");

		} catch (CouponsDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon8", "getCategoryId");
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (CompaniesDBExpetion e) {
			e.getMessage("CreateTestCoupons-getTestCoupon1", "getCompanyID");
			return null;
		}
	}
}
