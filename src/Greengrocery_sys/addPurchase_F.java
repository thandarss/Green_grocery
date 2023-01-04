package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.CORBA.PUBLIC_MEMBER;

import Greengrocery_sys.Database.DbConnection;

public class addPurchase_F {

	public void addPurchase(String type, int bucket, int box, double viss, int card, int price, java.util.Date date) {
	
		String typeString = type;
		int bucketNum = bucket;
		int boxNum = box;
		int cardNum = card;
		double vissNum = viss;
		Date purchasedate = new java.sql.Date(date.getTime());
		int vissPrice = price;
		double totalPrice = 0;
		
		/*
		 * calculate total price
		 * Total = vissPrice * viss (0.85)
		 */
		
		int vissInt = (int) vissNum;
		DecimalFormat decim = new DecimalFormat("0.00");
		
		double vissDouble = Double.parseDouble(decim.format(vissInt));
		
		//get decimal approximate value
		String vissString = String.format("%.1f", vissNum);
		vissString = vissString.substring(vissString.indexOf(".")+1);
		
		int lastVissNum = Integer.parseInt(vissString);
		
		//compare the decimal to get the approximate value
		
		if(lastVissNum >= 5 && lastVissNum < 9)
			vissDouble += 0.5;
		
		else if(lastVissNum >= 9) {
			vissDouble += 1;
		}
		
		//calculate total
		totalPrice = vissPrice * vissDouble;
		
		String sqlString = "insert into purchase_order(Type, Bucket, Box, Card, Viss, Price, Total, Date) values (?,?,?,?,?,?,?,?);";
		Connection connection = new DbConnection().connect();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketNum);
			pStatement.setInt(3, boxNum);
			pStatement.setDouble(4, cardNum);
			pStatement.setDouble(5, vissNum);
			pStatement.setDouble(6, vissPrice);
			pStatement.setDouble(7, totalPrice);
			pStatement.setDate(8, purchasedate);
			pStatement.executeUpdate();
			
			String textString = typeString + " စာရင္းျဖည့္သြင္းၿပီးပါၿပီ";
			JLabel textJLabel = new JLabel(textString);
			textJLabel.setFont(new Font("Zawgyi-One",Font.PLAIN, 22));
			
			JOptionPane.showMessageDialog(null, textJLabel, "Adding Product",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
