package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class OracleConnection {
	private static Connection con;
	static {
		Properties p = new Properties();
		try {
			p.load(OracleConnection.class.getClassLoader().getResourceAsStream("dbinfo.prop"));
			Class.forName(p.getProperty("driver"));
			con = DriverManager.getConnection(p.getProperty("url"), p);
		} catch (Exception e) {
			System.out.println("Error : util.OracleConnection " + e);
		}
	}

	public static Connection getConnection() {
		return con;
	}
	public static void closeConnection() {
		try {
			con.close();
			System.out.println("Terminated............");
		}
		catch(Exception e) {
			System.out.println("Error : util.OracleConnection "+e);
		}
	}
}
