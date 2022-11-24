package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Greengrocery_sys.Database.DbConnection;
import javafx.scene.input.KeyCode;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class product_D extends JFrame {

	private JPanel contentPane;
	private JTable tbProduct;
	private JTextField txtType;
	private JScrollPane scrollPane;
	private JTextField txtBucket;
	private JTextField txtViss;
	private JTextField txtBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					product_D frame = new product_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateTable() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from product_price;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbProduct.setModel(DbUtils.resultSetToTableModel(rSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public product_D() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 481, 318);
		contentPane.add(scrollPane);
		
		tbProduct = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
        };
		};
		scrollPane.setViewportView(tbProduct);
		tbProduct.getTableHeader().setFont(new Font("Zawgyi-One", Font.BOLD, 12));
		tbProduct.setFont(new Font("Zawgyi-one",Font.PLAIN,12));
		tbProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tbProduct.getSelectedRow();
				String idString = tbProduct.getModel().getValueAt(row, 0).toString();
				int id = Integer.parseInt(idString);
				System.out.println("mouse selected id : " +id);
				
					Connection connection = new DbConnection().connect();
					String sqlString = "select * from product_price where Id_product = "+ id +";";
					try {
						PreparedStatement pStatement = connection.prepareStatement(sqlString);
						ResultSet rSet = pStatement.executeQuery();
						
						if(rSet.next()) {
						txtType.setText(rSet.getString("Type"));
						txtBucket.setText(rSet.getString("Bucket_Price"));
						txtViss.setText(rSet.getString("Viss_Price"));
						txtBox.setText(rSet.getString("Box_Price"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		JLabel lblTitle = new JLabel("အသီးစာရင္း");
		lblTitle.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblTitle.setBounds(371, 11, 118, 44);
		contentPane.add(lblTitle);
		
		txtType = new JTextField();
		txtType.requestFocus();
		txtType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBucket.requestFocus();
				}
			}
		});
		txtType.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		txtType.setBounds(662, 79, 140, 27);
		contentPane.add(txtType);
		txtType.setColumns(10);
		
		JLabel lblTitle_1 = new JLabel("အမ်ိဳးအစား      :");
		lblTitle_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1.setBounds(501, 69, 151, 44);
		contentPane.add(lblTitle_1);
		
		JLabel lblTitle_1_1 = new JLabel("ေတာင္းေစ်း       :");
		lblTitle_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1.setBounds(501, 124, 151, 44);
		contentPane.add(lblTitle_1_1);
		
		JLabel lblTitle_1_1_1 = new JLabel("ပိႆာေစ်း       :");
		lblTitle_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1.setBounds(501, 179, 151, 44);
		contentPane.add(lblTitle_1_1_1);
		
		JLabel lblTitle_1_1_1_1 = new JLabel("ေသတၱာေစ်း     :");
		lblTitle_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_1.setBounds(501, 234, 151, 44);
		contentPane.add(lblTitle_1_1_1_1);
		
		txtBucket = new JTextField();
		txtBucket.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtViss.requestFocus();
				}
			}
		});
		txtBucket.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBucket.setColumns(10);
		txtBucket.setBounds(662, 133, 140, 27);
		contentPane.add(txtBucket);
		
		txtViss = new JTextField();
		txtViss.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBox.requestFocus();
				}
			}
		});
		txtViss.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtViss.setColumns(10);
		txtViss.setBounds(662, 188, 140, 27);
		contentPane.add(txtViss);
		
		txtBox = new JTextField();
		txtBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtType.requestFocus();
				}
			}
		});
		txtBox.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBox.setColumns(10);
		txtBox.setBounds(662, 243, 140, 27);
		contentPane.add(txtBox);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeString = txtType.getText();
				int bucketPrice = Integer.parseInt(txtBucket.getText());
				int vissPrice = Integer.parseInt(txtViss.getText());
				int boxPrice = Integer.parseInt(txtBox.getText());
				
				new addProduct_F().addProduct(typeString, bucketPrice, vissPrice, boxPrice);
				updateTable();
			}
		});
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 14));
		btnAdd.setBounds(511, 306, 129, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 14));
		btnRemove.setBounds(673, 306, 129, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeString = txtType.getText();
				int bucketPrice = Integer.parseInt(txtBucket.getText());
				int vissPrice = Integer.parseInt(txtViss.getText());
				int boxPrice = Integer.parseInt(txtBox.getText());
				
				int r = tbProduct.getSelectedRow();
				if(r != -1) {
					String idString = tbProduct.getModel().getValueAt(r, 0).toString();
					int id = Integer.parseInt(idString);
					
					new updateProduct_F().updateProduct(id, typeString, bucketPrice, vissPrice, boxPrice);
					updateTable();
				}
			}
		});
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 14));
		btnUpdate.setBounds(511, 353, 129, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 14));
		btnClear.setBounds(673, 353, 129, 34);
		contentPane.add(btnClear);
		
		updateTable();
	}
}
