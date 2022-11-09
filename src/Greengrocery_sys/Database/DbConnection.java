package Greengrocery_sys.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	Connection conn;
	public Connection connect() {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/green_grocery","root","root");
			System.out.println("Database Connected.");
			
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection Failed!");
			
		}
		
		return conn;
	}
}
