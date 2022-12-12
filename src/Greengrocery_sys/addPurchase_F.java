package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.CORBA.PUBLIC_MEMBER;

import Greengrocery_sys.Database.DbConnection;

public class addPurchase_F {

	public void addPurchase(String type, int bucketP, int boxP, int cardP, int vissP) {
		String typeString = type;
		int bucketPrice = bucketP;
		int boxPrice = boxP;
		int cardPrice = cardP;
		int vissPrice = vissP;
		
		String sqlString = "insert into purchase_price(Type, Bucket_Price, Box_Price, Card_Price, Viss_Price) values (?,?,?,?,?);";
		Connection connection = new DbConnection().connect();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketPrice);
			pStatement.setInt(3, boxPrice);
			pStatement.setInt(4, cardPrice);
			pStatement.setInt(5, vissPrice);
			pStatement.executeUpdate();
			
			String textString = "အဝယ္စာရင္းျဖည့္သြင္းၿပီးပါၿပီ";
			JLabel textJLabel = new JLabel(textString);
			textJLabel.setFont(new Font("Zawgyi-One",Font.PLAIN, 22));
			
			JOptionPane.showMessageDialog(null, textJLabel, "Adding Product",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
