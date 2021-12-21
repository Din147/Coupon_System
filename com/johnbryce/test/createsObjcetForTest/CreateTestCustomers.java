package com.johnbryce.test.createsObjcetForTest;

import com.johnbryce.beans.Customer;

public class CreateTestCustomers {

	public CreateTestCustomers() {}
	



public static Customer getTestCustomer1()  {
return new Customer("A", "aa", "Aaa@gmail.com", "Aaa123");
	
}

public static Customer getTestCustomer2()  {
return new Customer("B", "bb", "Bbb@gmail.com", "Bbb123");
	
}

public static Customer getTestCustomer3()  {
return new Customer("C", "cc", "Ccc@gmail.com", "Ccc123");
	
}

public static Customer getTestCustomer4()  {
return new Customer("D", "dd", "Ddd@gmail.com", "Ddd123");
	
}

// 5 for update 3 
//public static Customer getTestCustomer5()  {               
//return new Customer("C", "ZZZ", "123Ccc@gmail.com", "Ccc123");
//	
//}
}