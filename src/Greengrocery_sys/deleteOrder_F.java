package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Greengrocery_sys.Database.DbConnection;

public class deleteOrder_F {

	public void deleteOrder(int id) {
		Connection connection = new DbConnection().connect();
		String sqlString = "delete from customer_order where Id_order = " + id + ";";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
