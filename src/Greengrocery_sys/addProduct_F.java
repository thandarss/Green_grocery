package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class addProduct_F {
	
	public void addProduct(String type, int bucketP, int vissP, int boxP, int cardP) {
	
		String typeString = type;
		int bucketPrice = bucketP;
		int vissPrice = vissP;
		int boxPrice = boxP;
		int cardPrice = cardP;
		Connection connection = new DbConnection().connect();
		String sqString = "insert into product_price (Type, Bucket_Price, Viss_Price, Box_Price, Card_Price) values (?, ?, ?, ?, ?);";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqString);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketPrice);
			pStatement.setInt(3, vissPrice);
			pStatement.setInt(4, boxPrice);
			pStatement.setInt(5, cardPrice);
			//pStatement.setString(6, dateString);
			pStatement.executeUpdate();
			
			String textString = "အေရာင္းေစ်းအသစ္ ထည့္သြင္းၿပီးပါၿပီ။";
			JLabel textJLabel = new JLabel(textString);
			textJLabel.setFont(new Font("Zawgyi-One",Font.PLAIN, 22));
			
			JOptionPane.showMessageDialog(null, textJLabel, "Adding Product",JOptionPane.PLAIN_MESSAGE);
			
			//if(result>0)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail to Add New Product!");
		}
	}
}
