package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnectionTest {
	
	public static void main(String args[]) throws SQLException {
		 Connection connection = null;
		
	try {
		//Step 1:- Create Object for implemention class
	
		Driver driver=new Driver();
		
		//Step2:- Register the Driver with JDBC
		
		DriverManager.registerDriver(driver);
		
		//Step3:- Estrablish the database connection
		
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

		//Step4:- create Statement
		Statement statement = connection.createStatement();
		
		//Step5:- Execute query
		ResultSet result = statement.executeQuery("select * from emp;");
		
		//Step6:- Validate(based on testCase)
		while(result.next())
		{
			System.out.println(result.getInt("ename")+" "+result.getString("job"));
		}
		
		
	}
	finally {
		System.out.println("BeforeClose");
		//Step8:- Close the connection(mandatory)
		connection.close();
		
		System.out.println("AfterClose");
		
	}
	}
	
	
	
}
