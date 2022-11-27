package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;

import Greengrocery_sys.Database.DbConnection;

public class addOrder_F {
	public void addOrder(String type, int bucket, int box, double viss, String date, int id_cus, int id_product ) {
		
		String typeString = type;
		int bucketNum = bucket;
		int boxNum = box;
		double vissNum = viss;
		String orderdate = date;
		int customerId = id_cus;
		int productId = id_product;
		
		//int vissPrice;
		//int total;
		
		Connection connection = new DbConnection().connect();
		String sqlString = "insert into customer_order (Type, Bucket, Box, Viss, Price, Total, Date, Id_customer, Id_product) values (?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
	}

}
