package com.edu;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import com.util.OracleConnection;

public class Editor {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Statement st = OracleConnection.getConnection().createStatement();
		while (true) {
			System.out.print("SQL> ");
			String query = sc.nextLine();
			if (query.equalsIgnoreCase("exit")) {
				OracleConnection.closeConnection();
				break;
			}
			try {
				boolean status = st.execute(query);
				if (status) {
					ResultSet rs = st.getResultSet();
					ResultSetMetaData md = rs.getMetaData();
					int columncount = md.getColumnCount();
					for (int i = 1; i <= columncount; i++)
						System.out.printf("%-25s", md.getColumnName(i));
					System.out.println(
							"\n--------------------------------------------------------------------------------------------------------------");
					while (rs.next()) {
						for (int i = 1; i <= columncount; i++)
							System.out.printf("%-25s", rs.getString(i));
						System.out.println("");
					}
				} else {
					int c = st.getUpdateCount();
					System.out.println(c + " row updated");
				}
			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
			}
			System.out.println("");
		}
		sc.close();
	}
}
