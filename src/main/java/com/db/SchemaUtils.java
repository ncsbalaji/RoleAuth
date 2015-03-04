package com.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.TPS;

public class SchemaUtils extends DBUtils {

	public ArrayList<TPS> getTPSList() throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		ArrayList<TPS> tpsArray = null;
		
		String url= "jdbc:mysql://localhost:3306/restfuldb";
	    String user="root";
	    String pass="mysql";
	    String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver);
			//conn = mysqlConnection();
			conn = DriverManager.getConnection(url, user, pass);
			query = conn.prepareStatement("SELECT date, tpscount FROM tps");

			tpsArray = new ArrayList<TPS>();
			ResultSet rs = query.executeQuery();
			 while( rs.next() ) {
				 TPS tps = new TPS(rs.getString("date"), rs.getLong("tpscount"));
				 tpsArray.add(tps);
			 }

			query.close(); 
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return tpsArray;
		}
		catch(Exception e) {
			e.printStackTrace();
			return tpsArray;
		}
		finally {
			if (conn != null) conn.close();
		}

		return tpsArray;
	}

}
