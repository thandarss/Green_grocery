package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Greengrocery_sys.Database.DbConnection;

public class deletePurchase_F {
	public void deletePurchase(int id) {
		String sqlString = "delete from purchase_price where Id_purchase = " + id + ";";
		
		Connection connection = new DbConnection().connect();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
