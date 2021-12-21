package com.johnbryce.dao;

import java.util.ArrayList;

import com.johnbryce.beans.Coupon;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.enums.CATEGORY;

public interface CouponsDAO {

	public void addCoupon(Coupon coupon) throws CouponsDBExpetion;

	public void updateCoupon(Coupon coupon) throws CouponsDBExpetion;

	public void deleteCoupon(int couponID) throws CouponsDBExpetion;

	public ArrayList<Coupon> getAllcoupons() throws CouponsDBExpetion;

	public Coupon getOnecoupons(int couponID) throws CouponsDBExpetion;

	public void addCouponPurchase(int customerID, int couponID) throws CouponsDBExpetion;

	public void deletCouponPurchase(int customerID, int couponID) throws CouponsDBExpetion;

	public boolean isCouponExsist(String couponTitle, int CompanyID) throws CouponsDBExpetion;

	public void deletCouponPurchasedHistory(int couponID) throws CouponsDBExpetion;

	public int getCategoryId(CATEGORY category) throws CouponsDBExpetion;

}
