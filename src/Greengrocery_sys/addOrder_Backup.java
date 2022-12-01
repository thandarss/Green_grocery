package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.omg.CosNaming._BindingIteratorImplBase;

import Greengrocery_sys.Database.DbConnection;

public class addOrder_Backup {
	public void addOrderBackup(int id, String name, String type, int bucket, int box, int card, double viss, int price, double total, String date, int Id_customer, int Id_product) {
		
		int orderId = id;
		String nameString = name;
		String typeString = type;
		int bucketNum = bucket;
		int boxNum = box;
		int cardNum = card;
		double vissNum = viss;
		int vissPrice = price;
		double totalPrice = total;
		String orderdate = date;
		int customerId = Id_customer; 
		int productId = Id_product; 
		
		Connection connection = new DbConnection().connect();
			
		String sqlString2 = "insert into customer_order_backup (Id_order, Type, Bucket, Box, Card, Viss, Price, Total, Date, Id_customer, Id_product, Customer) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString2);
			pStatement.setInt(1, orderId);
			pStatement.setString(2, typeString);
			pStatement.setInt(3, bucketNum);
			pStatement.setDouble(4, boxNum);
			pStatement.setInt(5, cardNum);
			pStatement.setDouble(6, vissNum);
			pStatement.setInt(7, vissPrice);
			pStatement.setDouble(8, totalPrice);
			pStatement.setString(9, orderdate);
			pStatement.setInt(10, customerId);
			pStatement.setInt(11, productId);
			pStatement.setString(12, nameString);
			
			pStatement.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
