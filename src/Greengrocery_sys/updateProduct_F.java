package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updateProduct_F {

	public void updateProduct(int id, String type, int bucketP, int vissP, int boxP, int cardP) {
		
		int idProduct = id;
		String typeString = type;
		int bucketPrice = bucketP;
		int vissPrice = vissP;
		int boxPrice = boxP;
		int cardPrice = cardP;
		
		Connection connection = new DbConnection().connect();
		String sqString = "update product_price set Type=?, Bucket_Price = ?, Viss_Price = ?, Box_Price = ?, Card_Price = ? where Id_Product = " + idProduct + ";";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqString);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketPrice);
			pStatement.setInt(3, vissPrice);
			pStatement.setInt(4, boxPrice);
			pStatement.setInt(5, cardPrice);
			
			pStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Updated successfully!");
			
			//if(result>0)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail to Add New Product!");
		}
	}
}
