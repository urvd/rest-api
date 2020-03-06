package com.app.restapi.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Query {

	public Query(String query) throws SQLException {
	      DriverManager.registerDriver((Driver) new DriverManagerDataSource());
	      String mysqlUrl = "jdbc:mysql://localhost/mydatabase";
	      Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");	
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate(query);
	}
	
}
