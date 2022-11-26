package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;

public class Order_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtBucketPrice;
	private JTextField txtBoxPrice;
	private JTextField txtRentPeople;
	private JTextField txtVissPrice;
	private JTable tbOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order_D frame = new Order_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 *refresh table 
	 */
	
	public void refreshTable() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select Id_Invoice, Type, Bucket, Box, Viss, Price, Total from customer_order;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Order_D() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("နာမည္");
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		lblNewLabel.setBounds(603, 55, 98, 34);
		contentPane.add(lblNewLabel);
		
		JComboBox cboxName = new JComboBox();
		cboxName.setBounds(685, 57, 185, 34);
		contentPane.add(cboxName);
		
		JLabel lblNewLabel_1 = new JLabel("ေတာင္းေစ်း");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1.setBounds(635, 123, 98, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ေသတၱာေစ်း");
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(635, 186, 116, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ပိႆာေစ်း");
		lblNewLabel_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(635, 255, 116, 34);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("လုပ္အားခ");
		lblNewLabel_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(635, 328, 76, 34);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtBucketPrice = new JTextField();
		txtBucketPrice.setBounds(737, 125, 116, 31);
		contentPane.add(txtBucketPrice);
		txtBucketPrice.setColumns(10);
		
		txtBoxPrice = new JTextField();
		txtBoxPrice.setColumns(10);
		txtBoxPrice.setBounds(737, 186, 116, 34);
		contentPane.add(txtBoxPrice);
		
		txtRentPeople = new JTextField();
		txtRentPeople.setColumns(10);
		txtRentPeople.setBounds(737, 325, 116, 40);
		contentPane.add(txtRentPeople);
		
		txtVissPrice = new JTextField();
		txtVissPrice.setColumns(10);
		txtVissPrice.setBounds(737, 252, 116, 40);
		contentPane.add(txtVissPrice);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(424, 11, 149, 40);
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setFont(new Font("Zawgyi-one",Font.BOLD,17));
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2 = new JLabel("ေအာ္ဒါစာရင္း");
		lblNewLabel_2.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		lblNewLabel_2.setBounds(10, 11, 131, 34);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 583, 390);
		contentPane.add(scrollPane);
		
		tbOrder = new JTable();
		scrollPane.setViewportView(tbOrder);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(640, 389, 93, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.setForeground(new Color(128, 64, 0));
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemove.setBackground(new Color(254, 251, 245));
		btnRemove.setBounds(766, 389, 87, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.setBounds(640, 441, 93, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.setBounds(766, 441, 87, 34);
		contentPane.add(btnClear);
		
		refreshTable();
	}
}
