package com.avilyne.rest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	 public static Connection  PostgreSqlConn() throws ClassNotFoundException, SQLException 
	 {
		 //connection object
		 Connection conn;
		 
		 //Load the driver
	     System.out.print("STEP 1: Loading the driver...");
	     Class.forName(StatVariables.stClassName);
	     System.out.println("loaded\n");

	     //Make a connection
	     System.out.print("STEP 2: Connecting ");
	      
	     conn = DriverManager.getConnection(StatVariables.stUrl + StatVariables.stDatabase, StatVariables.stUsername, StatVariables.stPassword);
	     
	     System.out.println("connected\n");
	     
	     return(conn);
	  }
	
}
