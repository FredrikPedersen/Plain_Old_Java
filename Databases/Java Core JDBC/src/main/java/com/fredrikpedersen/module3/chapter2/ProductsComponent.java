package com.fredrikpedersen.module3.chapter2;


import java.sql.DriverManager;
import java.sql.Connection;

public class ProductsComponent {

	public boolean tryConnection() throws Exception {	

		//Deprecated way to load JDBC driver for version pre Java 7.
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?user=root&password=pluralsight&serverTimezone=UTC");			
			
		boolean isValid = connection.isValid(2);
			
		connection.close();
		
		return isValid;
		

    }
}