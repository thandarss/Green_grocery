package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updatePurchase_F {

	public void updatePurchase(int id, String type, int bucket, int box, int card, double viss, int price, java.util.Date date) {
		
		int idPurchase = id;
		String typeString = type;
		int bucketNum = bucket;
		double vissNum = viss;
		int boxNum = box;
		int cardNum = card;
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
		
		Connection connection = new DbConnection().connect();
		String sqlString = "update purchase_order set Type =?, Bucket=?, Box=?, Card=?, Viss=?, Price=?, Total=?, Date=? where Id_purchase = " + idPurchase + ";";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1,typeString);
			pStatement.setInt(2, bucketNum);
			pStatement.setInt(3, boxNum);
			pStatement.setDouble(4, cardNum);
			pStatement.setDouble(5, vissNum);
			pStatement.setDouble(6, vissPrice);
			pStatement.setDouble(7, totalPrice);
			pStatement.setDate(8, purchasedate);
			pStatement.executeUpdate();
			int result = pStatement.executeUpdate();
			
			if(result != -1) {
				String updateString = "လက္ရွိစာရင္းကို ျပင္ၿပီးပါၿပီ";
				JLabel textJLabel = new JLabel(updateString);
				textJLabel.setFont(new Font("Zawgyi-One",Font.BOLD,20));
				JOptionPane.showMessageDialog(null, textJLabel, "Update Information", JOptionPane.PLAIN_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
