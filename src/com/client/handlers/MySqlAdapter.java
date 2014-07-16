package com.client.handlers;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlAdapter{

	public static Connection connection;

	// Opens the MySQL Connection
	public synchronized static void openConnection() {
		try{
			connection = DriverManager.getConnection("jdbc:mysql://sql01.nyc1.do.mgenterprises.org:3306/shorton_genflow", "shorton", "NWjXvNBjxG7FLajE");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	// Closes the MySQL Connection
	public synchronized static void closeConnection() {
		try{
			connection.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
