package com.fredrikpedersen.module3.chapter1;

import java.sql.Connection;
import java.sql.DriverManager;

public class ProductsComponent {

	public boolean tryConnection() throws Exception {	

		
		Connection connection = 
		DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?"
				+ "user=root&password=pluralsight&serverTimezone=UTC");			
		
		
		boolean isValid = connection.isValid(2);
		
		
		connection.close();	
			
		
		return isValid;
    }

}

