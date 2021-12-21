package com.johnbryce.test.createsObjcetForTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.johnbryce.database.ConnectionPool;
import com.johnbryce.enums.CATEGORY;
public class CreateTestCategory {

 
	
	public CreateTestCategory() throws SQLException {  /// to do back we need excepion
	
		Connection con = ConnectionPool.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("Insert into categories(Name) value ('"+ CATEGORY.ELECTRICITY.toString() + "')");
		stmt.execute();
		
		stmt = con.prepareStatement("Insert into categories(Name) value ('"+ CATEGORY.FOOD.toString() + "')");
		stmt.execute();
		
		stmt = con.prepareStatement("Insert into categories(Name) value ('"+ CATEGORY.RESTAURANT.toString() + "')");
		stmt.execute();
		
		stmt = con.prepareStatement("Insert into categories(Name) value ('"+ CATEGORY.VACATION.toString() + "')");
		stmt.execute();
	
		stmt = con.prepareStatement("Insert into categories(Name) value ('"+ CATEGORY.ELSE.toString() + "')");
		stmt.execute();
		
		ConnectionPool.getInstance().restoreConnection(con);	
		
}
}
