package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class Order_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtBucketNum;
	private JTextField txtBoxNum;
	private JTextField txtVissNum;
	private JTable tbOrder;
	private JComboBox cboxName, cboxType;
	protected int customerId, productId;
	protected String nameString,typeString;
	Connection connection;

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
	 * get Customer ID from customer table
	 */
	
	public void getCustomerID() {
		//get Customer Id
		
		nameString = cboxName.getSelectedItem().toString();
		connection = new DbConnection().connect();
		String sqlString = "select Id_customer from customer where Name = '" + nameString + "';";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			customerId = rSet.getInt("Id_customer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 *refresh table 
	 */
	
	public void refreshTable() {
		
		connection = new DbConnection().connect();
									
		String sqlString2 = "select Id_Invoice, Type, Bucket, Box, Viss, Price, Total, Date from customer_order;";
		try {
			PreparedStatement pStatement1 = connection.prepareStatement(sqlString2);
			ResultSet rSet = pStatement1.executeQuery();
			tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Show Selected Customer Data
	 */
	
	public void selectedCusOrder() {
		connection = new DbConnection().connect();
		
		String sqlString = "select Id_Invoice, Type, Bucket, Box, Viss, Price, Total, Date from customer_order where Id_customer = '" + customerId + "';";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * Fill ComboBox Name
	 */
	
	public void fillNameBox() {
		connection = new DbConnection().connect();
		String sqlString = "select * from customer;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			cboxName.addItem("All");
			
			while(rSet.next()) {
				cboxName.addItem(rSet.getString("Name"));				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillTypeBox() {

		connection = new DbConnection().connect();
		String sqString = "select * from product_price";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqString);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				cboxType.addItem(rSet.getString("Type"));
			}
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
		setBounds(100, 100, 1155, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ဝယ္သူ");
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 18));
		lblNewLabel.setBounds(861, 14, 98, 34);
		contentPane.add(lblNewLabel);
		
		cboxName = new JComboBox();
		cboxName.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				selectedCusOrder();
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cboxName.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		cboxName.setBounds(964, 16, 165, 34);
		contentPane.add(cboxName);
		
		JLabel lblNewLabel_1 = new JLabel("ေတာင္း");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1.setBounds(883, 150, 76, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ေသတၱာ");
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(1022, 150, 67, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ပိႆာ");
		lblNewLabel_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(883, 246, 67, 34);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtBucketNum = new JTextField();
		txtBucketNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				char c = arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtBucketNum.setText("");
				}
				else {
					txtBucketNum.setEditable(true);
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						txtBoxNum.requestFocus();
					}
				}
			}
		});
		txtBucketNum.setBounds(883, 195, 58, 31);
		contentPane.add(txtBucketNum);
		txtBucketNum.setColumns(10);
		
		txtBoxNum = new JTextField();
		txtBoxNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtBoxNum.setText("");
				}
				else {
					txtBoxNum.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtVissNum.requestFocus();
					}
				}
			}
		});
		txtBoxNum.setColumns(10);
		txtBoxNum.setBounds(1022, 195, 58, 31);
		contentPane.add(txtBoxNum);
		
		txtVissNum = new JTextField();
		txtVissNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtVissNum.setText("");
				}
				else {
					txtVissNum.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtBucketNum.requestFocus();
					}
				}
			}
		});
		txtVissNum.setColumns(10);
		txtVissNum.setBounds(883, 291, 58, 31);
		contentPane.add(txtVissNum);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(685, 16, 149, 40);
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setFont(new Font("Zawgyi-one",Font.BOLD,17));
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2 = new JLabel("ေအာ္ဒါစာရင္း");
		lblNewLabel_2.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		lblNewLabel_2.setBounds(10, 11, 131, 34);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 824, 390);
		contentPane.add(scrollPane);
		
		tbOrder = new JTable();
		scrollPane.setViewportView(tbOrder);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameString = cboxName.getSelectedItem().toString();
				typeString = cboxType.getSelectedItem().toString();
				int bucketNum = Integer.parseInt(txtBucketNum.getText());
				int boxNum = Integer.parseInt(txtBoxNum.getText());
				double vissNum = Double.parseDouble(txtVissNum.getText());
				//int labor = Integer.parseInt(txtlabor.getText());
				
				//new addOrder_F().addOrder(typeString, bucketNum, boxNum, vissNum, labor);
				
				
				
				//String sqlString2 = "select Id_product from product_price where "
			}
		});
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(861, 346, 98, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.setForeground(new Color(128, 64, 0));
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemove.setBackground(new Color(254, 251, 245));
		btnRemove.setBounds(1005, 346, 92, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.setBounds(861, 401, 98, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.setBounds(1005, 401, 92, 34);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_3 = new JLabel("အမ်ိဳးအစား");
		lblNewLabel_3.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_3.setBounds(861, 78, 98, 34);
		contentPane.add(lblNewLabel_3);
		
		cboxType = new JComboBox();
		cboxType.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		cboxType.setBounds(964, 78, 165, 34);
		contentPane.add(cboxType);
		
		refreshTable();
		fillNameBox();
		fillTypeBox();
	}
}
