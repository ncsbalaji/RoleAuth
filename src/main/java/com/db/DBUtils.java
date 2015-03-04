package com.db;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtils {

	private static DataSource mysqlDataSource = null; 
	private static Context context = null; 
	
	private static DataSource mysqlDataSource() throws Exception {
		
		
		if (mysqlDataSource != null) {
			return mysqlDataSource;
		}
		
		try {
			
			
			if (context == null) {
				context = new InitialContext();
			}
			//Context envContext  = (Context)context.lookup("java:/comp/env");
			//DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			DataSource ds = (DataSource)context.lookup("java:/MySqlDS");
			mysqlDataSource = (DataSource) ds;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return mysqlDataSource;
		
	}
	
	protected static Connection mysqlConnection() {
		Connection conn = null;
		try {
			conn = mysqlDataSource().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
