package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Customer {

	protected JFrame frame;
	protected JTable tbCustomer;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Customer() {
		initialize();
		updateTable();
	}

	/**
	 * Retrieving database data to JTable
	 */
	
	public void updateTable () {
		Connection conn = new DbConnection().connect();
		String sqlString = "select Id_customer, Name, Address, Phone from customer;";
	
		try {
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbCustomer.setModel(DbUtils.resultSetToTableModel(rSet));
			
			//JOptionPane.showMessageDialog(null, "Updated successfully");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1008, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		//frame.setUndecorated(true);
		//close the Jframe_autoClose button
		//frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText();
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				
				//String id_customer = name.substring(name.length()-1) + address.substring(address.length()-1) + phone.substring(phone.length()-2);
				
				new addCustomer_F().addCustomer(name, address, phone);
				updateTable();
				//new addCustomer_D().frame.setVisible(true);
			}
		});
		
		JButton btnRemoveRow = new JButton("ဖ်က္မည္");
		
		btnRemoveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * Backup the deleted row
				 */
				
				int row = tbCustomer.getSelectedRow();
																						
				if(tbCustomer.getSelectedRow() != -1) {
					
					String idString = tbCustomer.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(idString);
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
					
					if(confirm == 0) {
						new addCustomerBackup().backupCus(id);
						new deleteCustomer().deleteCus(id);
						
						DefaultTableModel model= (DefaultTableModel) tbCustomer.getModel();
						model.removeRow(tbCustomer.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Remove row successfully!");
					}
													
					updateTable();
				}	
				
			}
		});
		btnRemoveRow.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemoveRow.setBackground(SystemColor.info);
		btnRemoveRow.setBounds(780, 340, 119, 40);
		frame.getContentPane().add(btnRemoveRow);
		btnAdd.setBackground(SystemColor.info);
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBounds(620, 340, 125, 40);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 586, 334);
		frame.getContentPane().add(scrollPane);
		
		/*
		 * Unable the table cell editing
		 */
		tbCustomer = new JTable(){
	        //private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		tbCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tbCustomer.getSelectedRow();
				String selectString = tbCustomer.getModel().getValueAt(row, 0).toString();
				System.out.println("mouse selected id : " + selectString);
								
				String sqlString = "select * from customer where id_customer = '" + selectString + "';";
				
				Connection connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					if (rSet.next()) {
						txtName.setText(rSet.getString("Name"));
						txtAddress.setText(rSet.getString("Address"));
						txtPhone.setText(rSet.getString("Phone"));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(tbCustomer);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setBackground(Color.RED);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClose.setBounds(939, 0, 43, 18);
		frame.getContentPane().add(btnClose);
		
		JLabel lblNewLabel = new JLabel("နာမည္    :");
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel.setBounds(620, 87, 98, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("လိပ္စာ    :");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel_1.setBounds(620, 178, 98, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ဖုန္းနံပါတ္ :");
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(620, 278, 111, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		/*
		 * focus cursor into next jtextfile by enter key
		 */
		txtName = new JTextField();
		txtName.requestFocus();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtAddress.requestFocus();
				}
			}
		});
		txtName.setToolTipText("Enter your name");
		txtName.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		txtName.setBounds(728, 86, 172, 39);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPhone.requestFocus();
				}
			}
		});
		txtAddress.setToolTipText("Enter the address");
		txtAddress.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(728, 178, 172, 39);
		frame.getContentPane().add(txtAddress);
		
		txtPhone = new JTextField();
		txtPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAdd.requestFocus();
				}
			}
		});
		txtPhone.setToolTipText("Enter the phone number");
		txtPhone.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		txtPhone.setColumns(10);
		txtPhone.setBounds(728, 272, 172, 39);
		frame.getContentPane().add(txtPhone);
		
		JLabel lblNewLabel_2 = new JLabel("ကုန္သည္စာရင္း");
		lblNewLabel_2.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 332, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nameString = txtName.getText();
				String addressString = txtAddress.getText();
				String phoneString = txtPhone.getText();
				
				int row = tbCustomer.getSelectedRow();
				if(row != -1) {
					String idString = tbCustomer.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(idString);
					new updateCustomer().updateCus(id, nameString, addressString, phoneString);
					updateTable();
				}
			}
		});
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(SystemColor.info);
		btnUpdate.setBounds(620, 396, 119, 40);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtName.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
			}
		});
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(SystemColor.info);
		btnClear.setBounds(780, 396, 119, 40);
		frame.getContentPane().add(btnClear);
	}
}
