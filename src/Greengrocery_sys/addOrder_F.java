package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.CosNaming._BindingIteratorImplBase;

import Greengrocery_sys.Database.DbConnection;

public class addOrder_F {
	public void addOrder(String name, String type, int bucket, int box, double viss, int card, String date) {
		
		String nameString = name;
		String typeString = type;
		int bucketNum = bucket;
		int boxNum = box;
		double vissNum = viss;
		String orderdate = date;
		int customerId = 0, productId =0, vissPrice = 0;
		double totalPrice = 0;

		/*
		 * get customer ID, product ID, vissPrice
		 */
		Connection connection = new DbConnection().connect();
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
			
		String sqlString2 = "insert into customer_order (Type, Bucket, Box, Viss, Price, Total, Date, Id_customer, Id_product, Customer) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString2);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketNum);
			pStatement.setInt(3, boxNum);
			pStatement.setDouble(4, vissNum);
			pStatement.setInt(5, vissPrice);
			pStatement.setDouble(6, totalPrice);
			pStatement.setString(7, orderdate);
			pStatement.setInt(8, customerId);
			pStatement.setInt(9, productId);
			pStatement.setString(10, nameString);
			
			pStatement.executeUpdate();
			
			String showString = nameString + "အတြက္ Order အသစ္ထည့္ၿပီးပါၿပီ!!";
			JLabel textJLabel = new JLabel(showString);
			textJLabel.setFont(new Font("Zawgyi-One", Font.PLAIN,22));
			JOptionPane.showMessageDialog(null,textJLabel,"Adding Order", JOptionPane.PLAIN_MESSAGE);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
