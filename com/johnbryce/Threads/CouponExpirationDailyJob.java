package com.johnbryce.Threads;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Coupon;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.imp.CouponsDBDAO;

public class CouponExpirationDailyJob implements Runnable {
	private CouponsDAO couponsDAO;
	private static boolean quit = true;

	public CouponExpirationDailyJob() {
		couponsDAO = new CouponsDBDAO();
	}

	@Override
	public void run() {
		List<Coupon> listCoupon = new ArrayList<Coupon>();

		while (quit) {
			try {
				listCoupon = couponsDAO.getAllcoupons();
				if (listCoupon.equals(null)) {
					Thread.sleep(1000 * 60 * 60 * 24);

				} else {
					for (Coupon coupon : listCoupon) {
						if (coupon.getEndDate().getTime() < System.currentTimeMillis()) {
							couponsDAO.deletCouponPurchasedHistory(coupon.getID());
							couponsDAO.deleteCoupon(coupon.getID());

						}
					}

					Thread.sleep(1000 * 60 * 60 * 24);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();

			} catch (CouponsDBExpetion e) {
				e.getMessage("CouponExpirationDailyJob", "getAllcoupons/deletCouponPurchasedHistory/deleteCoupon");
			}
		}
	}

	public static void stopChecker() {
		quit = false;
	}

}