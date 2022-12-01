package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updateOrder_F {

	public void updateOrder(int id_order, String name, String type, int bucket, int box, double viss, int card, String date, int id_customer, int id_product) {
		
		int orderId = id_order;
		String nameString = name;
		String typeString =type;
		int bucketNum = bucket;
		int boxNum = box;
		double vissNum = viss;
		int cardNum = card;
		String dateString = date;
		int customerId = id_customer, productId = id_product;
		int vissPrice = 0;
		double totalPrice = 0;
		
		Connection connection;
		
		/*
		 * get customer ID, product ID, vissPrice
		 */
		connection = new DbConnection().connect();
		String sqlString = "select Id_customer from customer where Name = '" + nameString + "';";
		String sqlString1 = "select Id_product, Viss_Price from product_price where Type = '" + typeString + "';";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()) {

				customerId = rSet.getInt("Id_customer");
			
				System.out.println(customerId+ " rset customer ID");
			}
			else {
				String warningString = "စာရင္းသြင္းရန္ ဝယ္သူကိုေ႐ြးခ်ယ္ပါ!!";
				JLabel textJLabel = new JLabel(warningString);
				textJLabel.setFont(new Font("Zawgyi-one", Font.BOLD, 20));
				JOptionPane.showMessageDialog(null,textJLabel,"Warning to choose customer",JOptionPane.PLAIN_MESSAGE);
			}
			
			PreparedStatement pStatement1 = connection.prepareStatement(sqlString1);
			ResultSet rSet1 = pStatement1.executeQuery();
			
			if(rSet1.next()) {
				productId = rSet1.getInt("Id_product");
				vissPrice = rSet1.getInt("Viss_Price");
				System.out.println(productId + " " + vissPrice + " rset1 product ID");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		String sqlString2 = "update customer_order set Type = ?, Bucket =?, Box =?, "
				+ "Card = ?, Viss=?, Price=?, Total=?, Date=?, Id_customer=?, Id_product=?, Customer=? where Id_order = " + orderId +";";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString2);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketNum);
			pStatement.setInt(3, boxNum);
			pStatement.setInt(4, cardNum);
			pStatement.setDouble(5, vissNum);
			pStatement.setInt(6,vissPrice);
			pStatement.setDouble(7, totalPrice);
			pStatement.setString(8, dateString);
			pStatement.setInt(9, customerId);
			pStatement.setInt(10, productId);
			pStatement.setString(11, nameString);
			
			int result = pStatement.executeUpdate();
			
			if (result != -1) {
				String updateString = "Order စာရင္းကို ျပင္ဆင္ၿပီးပါၿပီ!";
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
