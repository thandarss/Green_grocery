package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Greengrocery_sys.Database.DbConnection;

public class deleteProduct_F {
	
	public void deleteProduct(int id) {
		Connection connection = new DbConnection().connect();
		String sqlString = "delete from product_price where Id_product = " + id + ";";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
