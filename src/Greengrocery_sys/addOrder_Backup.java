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
	public void addOrderBackup(int id, String type, int bucket, int box, int card, double viss, int price, double total, String date) {
		
		int orderId = id;
		String typeString = type;
		int bucketNum = bucket;
		int boxNum = box;
		int cardNum = card;
		double vissNum = viss;
		int vissPrice = price;
		double totalPrice = total;
		String orderdate = date;
		int customerId = 0; 
		int productId =0; 
		System.out.println("retrieved from order type : " + typeString + " , " + orderdate);
		
		Connection connection = new DbConnection().connect();
			
		String sqlString2 = "insert into customer_order (Id_order, Type, Bucket, Box, Card, Viss, Price, Total, Date, Id_customer, Id_product) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
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
			
			pStatement.executeUpdate();
			
			System.out.println("get from backup order type : " + typeString);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
