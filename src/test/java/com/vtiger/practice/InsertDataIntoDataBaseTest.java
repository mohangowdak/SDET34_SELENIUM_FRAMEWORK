package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertDataIntoDataBaseTest {
	
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
		int result = statement.executeUpdate( "insert into project values('TY_PROJ_124', 'SANJAY2', '27/04/2022', 'DATABASE2', 'On Going', 2);");
		
		//Step6:- Validate(based on testCase)
		if(result==1)
		{
			System.out.println("Data inserted in the database successfully");
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
