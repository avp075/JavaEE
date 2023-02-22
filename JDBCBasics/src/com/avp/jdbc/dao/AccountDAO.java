package com.avp.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

	public static void main(String[] args) {

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "test1234");
			System.out.println(connection);
			Statement statement = connection.createStatement();
			//int result = statement.executeUpdate("insert into account values(1,'pandey','aviral',1000)");
			//System.out.println(result+" rows got inserted.");
			//int result = statement.executeUpdate("update account set bal=2000");
			//System.out.println(result+" rows got updated.");
			//int result = statement.executeUpdate("delete from account where accno=1");
			//System.out.println(result+" rows got deleted.");
			
			ResultSet rs = statement.executeQuery("select * from account");
			while(rs.next()) {
				
				System.out.println(rs.getString(3)+" "+rs.getString(2)+" "+rs.getString(4));

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
