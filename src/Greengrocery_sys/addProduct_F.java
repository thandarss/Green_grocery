package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class addProduct_F {
	
	public void addProduct(String type, int bucketP, int vissP, int boxP) {
	
		String typeString = type;
		int bucketPrice = bucketP;
		int vissPrice = vissP;
		int boxPrice = boxP;
		Connection connection = new DbConnection().connect();
		String sqString = "insert into product_price (Type, Bucket_Price, Viss_Price, Box_Price) values (?, ?, ?, ?);";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqString);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketPrice);
			pStatement.setInt(3, vissPrice);
			pStatement.setInt(4, boxPrice);
			
			pStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "New Product added successfully!");
			
			//if(result>0)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail to Add New Product!");
		}
	}
}
