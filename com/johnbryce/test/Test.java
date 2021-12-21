package com.johnbryce.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.johnbryce.Threads.CouponExpirationDailyJob;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.customException.CouponsDBExpetion;
import com.johnbryce.customException.GenralException;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.imp.CouponsDBDAO;
import com.johnbryce.database.ConnectionPool;
import com.johnbryce.database.Database;
import com.johnbryce.enums.CATEGORY;
import com.johnbryce.enums.ClientType;
import com.johnbryce.facade.LoginManager;
import com.johnbryce.facade.imp.AdminFacade;
import com.johnbryce.facade.imp.CompanyFacade;
import com.johnbryce.facade.imp.CustomerFacade;
import com.johnbryce.test.createsObjcetForTest.CreateTestCategory;
import com.johnbryce.test.createsObjcetForTest.CreateTestCompnies;
import com.johnbryce.test.createsObjcetForTest.CreateTestCoupons;
import com.johnbryce.test.createsObjcetForTest.CreateTestCustomers;

public class Test {

	/*
	 * Instruction: 
	 * start to check from test 1 to 6. 
	 * do not " // " Previous tests: for example- if you check test 3, it means that test 1 and 2 must be uncomment.
	 */
	
	public static void testAll() {

		try {
		//	clearDB();                     use only after you have DB
			Database.createDB();

			CouponExpirationDailyJob cedj = new CouponExpirationDailyJob();
			Thread job = new Thread(cedj);
			job.start();

			SimpleDateFormat testDate = new SimpleDateFormat("dd-MM-yyyy");
			CreateTestCategory Test = new CreateTestCategory();

			test1(); // Create Category
			test2(); // Admin - Company
			test3(); // Admin - Customers
			test4(); // companyFacade
			test5(); // customerFacade
			test6(); // check the DailyJob   -- must go to CouponExpirationDailyJob and change the time from:
			         //                          1000*60*60/24 to 1000*10.

			Thread.sleep(1000 * 40);
			cedj.stopChecker();
			
			Thread.sleep(1000 * 40);
			ConnectionPool.getInstance().closeAllConnectios();
	
		} catch (GenralException e) {
			e.getGenralException();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void test1() { // change the throw later
		System.out.println("-------11111111111111111111111111111111111111111111111111111111111111111111111------");

		try {
			CouponsDAO couponsDAO = new CouponsDBDAO();

			System.out.println(couponsDAO.getCategoryId(CATEGORY.ELECTRICITY));
			System.out.println(couponsDAO.getCategoryId(CATEGORY.FOOD));
			System.out.println(couponsDAO.getCategoryId(CATEGORY.RESTAURANT));
			System.out.println(couponsDAO.getCategoryId(CATEGORY.ELSE));
			System.out.println(couponsDAO.getCategoryId(CATEGORY.VACATION));

		} catch (CouponsDBExpetion e) {

		}

	}

	public static void test2() {
		System.out.println("-------22222222222222222222222222222222222222222222222222222222222222222222222------");
		try {

			AdminFacade admin = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.ADMNISTRATOR);

			admin.addCompany(CreateTestCompnies.getTestCompany1());
			admin.addCompany(CreateTestCompnies.getTestCompany2());
			admin.addCompany(CreateTestCompnies.getTestCompany3());
			admin.addCompany(CreateTestCompnies.getTestCompany4());

			System.out.println(admin.getAllCompany());

			admin.updatedCompany(CreateTestCompnies.getTestCompany5()); // this is for update company Din

			System.out.println(admin.getAllCompany());

			admin.deleteCompany(1); // Samsung

			System.out.println(admin.getAllCompany());
			System.out.println(admin.getOneCompany(2));
			System.out.println("");

		} catch (GenralException e) {
			e.getGenralException();
		}
	}

	public static void test3()   {
		System.out.println("-------33333333333333333333333333333333333333333333333333333333333333333333333------");
		
try {
		AdminFacade admin = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
				ClientType.ADMNISTRATOR);

		admin.addCustomer(CreateTestCustomers.getTestCustomer1());
		admin.addCustomer(CreateTestCustomers.getTestCustomer2());
		admin.addCustomer(CreateTestCustomers.getTestCustomer3());
		admin.addCustomer(CreateTestCustomers.getTestCustomer4());

		System.out.println(admin.getAllCustomer());
		System.out.println(admin.getOneCustomer(3));

		Customer customer = admin.getOneCustomer(3);
		customer.setLasrName("ZZZ");
		admin.updatedCustomer(customer);

		System.out.println(admin.getOneCustomer(3));
		System.out.println(admin.getAllCustomer());

		admin.deleteCustomer(2);

		System.out.println(admin.getAllCustomer());
		System.out.println("");

} catch (GenralException e) {
	e.getGenralException();
}
	}

	public static void test4() {
		System.out.println("-------44444444444444444444444444444444444444444444444444444444444444444444444------");

		try {
			CompanyFacade company = (CompanyFacade) LoginManager.getInstance().login("google@gmail.com", "google123",
					ClientType.COMPANY);

			company.addCoupon(CreateTestCoupons.getTestCoupon4());
			company.addCoupon(CreateTestCoupons.getTestCoupon7());
			company.addCoupon(CreateTestCoupons.getTestCoupon8());

			System.out.println(company.getCompanyCoupons());
			System.out.println(company.getCompanyCoupons(CATEGORY.VACATION));
			System.out.println(company.getCompanyCoupons(51.5));

			company.UpdatedCoupon(CreateTestCoupons.getTestCoupon9());

			System.out.println(company.getCompanyCoupons());

			company.DeleteCoupon(1);

			System.out.println(company.getCompanyCoupons());
			System.out.println(company.getCompanyDetails());
			System.out.println("");

		} catch (GenralException e) {
			e.getGenralException();
		}
	}

	public static void test5() throws ParseException {
		System.out.println("-------55555555555555555555555555555555555555555555555555555555555555555555555------");

		try {
			CustomerFacade customer = (CustomerFacade) LoginManager.getInstance().login("Aaa@gmail.com", "Aaa123",
					ClientType.CUSTOMER);
			CompanyFacade company = (CompanyFacade) LoginManager.getInstance().login("google@gmail.com", "google123",
					ClientType.COMPANY);

			Coupon coupo = company.getCompanyCoupons().get(0);
			coupo.setID(2);
			company.UpdatedCoupon(coupo);

			System.out.println(coupo.getAmmount());

			customer.purchaseCoupon(coupo);
//			customer.purchaseCoupon(coupo);  //this is to check Exception 
//    	
			System.out.println(company.getCompanyCoupons().get(0).getAmmount());

			coupo.setAmmount(0);
			company.UpdatedCoupon(coupo);

			System.out.println(company.getCompanyCoupons().get(0).getAmmount());

//			customer.purchaseCoupon(coupo);   //this is to check Exception (need to cancel the upper one)

			coupo = company.getCompanyCoupons().get(1);
			System.out.println(coupo);

			coupo.setID(3);
			coupo.setAmmount(3);
			coupo.setPrice(60);

			company.UpdatedCoupon(coupo);
			customer.purchaseCoupon(coupo);

			System.out.println(customer.getCustomerCoupons());
			System.out.println(customer.getCustomerCoupons(CATEGORY.RESTAURANT));
			System.out.println(customer.getCustomerCoupons(75));
			System.out.println(customer.getCustomerDetails());

		} catch (GenralException e) {
			e.getGenralException();
		}
	}

	public static void test6() {
		System.out.println("-------66666666666666666666666666666666666666666666666666666666666666666666666------");

		try {
			CompanyFacade company = (CompanyFacade) LoginManager.getInstance().login("google@gmail.com", "google123",
					ClientType.COMPANY);

			Coupon coupo = company.getCompanyCoupons().get(0);
			SimpleDateFormat endDate = new SimpleDateFormat("dd-MM-yyyy");

			coupo.setAmmount(50);
			coupo.setEndDate(endDate.parse("01-10-2021"));
			company.UpdatedCoupon(coupo);

			System.out.println("coupons has been update");

			Thread.sleep(1000 * 5);

			System.out.println(company.getCompanyCoupons());

		} catch (GenralException e) {
			e.getGenralException();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void clearDB() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection con = cp.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement(
					"DROP TABLE `coupons_system`.`categories`, `coupons_system`.`companies`, `coupons_system`.`coupons`, `coupons_system`.`customers`, `coupons_system`.`customers_vs_coupons`");
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}

	}

}
