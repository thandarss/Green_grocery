package Greengrocery_sys;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class purchaseProduct_D extends JFrame {

	private JPanel contentPane;
	private JTable tbPurchase;
	private JTextField txtBacketPrice;
	private JTextField txtCardPrice;
	private JTextField txtBoxPrice;
	private JTextField txtVissPrice;
	private JComboBox cBoxSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchaseProduct_D frame = new purchaseProduct_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * refresh the table
	 */
	private void refreshTable() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from purchase_price";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			tbPurchase.setModel(DbUtils.resultSetToTableModel(rSet));
			
			TableColumnModel columnModel = tbPurchase.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public purchaseProduct_D() {
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1117, 476);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnMinimize = new JButton("-");
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchaseProduct_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnMinimize.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnMinimize.setBackground(new Color(214, 54, 31));
		btnMinimize.setBounds(1022, 0, 44, 34);
		panel.add(btnMinimize);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchaseProduct_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClose.setBackground(new Color(214, 54, 31));
		btnClose.setBounds(1063, 0, 44, 34);
		panel.add(btnClose);
		
		JButton btnDelete = new JButton("ဖ်က္မည္");
		btnDelete.setForeground(new Color(128, 64, 0));
		btnDelete.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnDelete.setBackground(new Color(254, 251, 245));
		btnDelete.setBounds(948, 330, 118, 34);
		panel.add(btnDelete);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeString = cBoxSize.getSelectedItem().toString();
				int bucketP = Integer.parseInt(txtBacketPrice.getText());
				int boxP = Integer.parseInt(txtBoxPrice.getText());
				int cardP = Integer.parseInt(txtCardPrice.getText());
				int vissP = Integer.parseInt(txtVissPrice.getText());
				
				new addPurchase_F().addPurchase(typeString, bucketP, boxP, cardP, vissP);
				refreshTable();
			}
		});
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(782, 330, 118, 34);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.setBounds(948, 398, 118, 34);
		panel.add(btnClear);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.setBounds(782, 398, 118, 34);
		panel.add(btnUpdate);
		
		txtVissPrice = new JTextField();
		txtVissPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBacketPrice.requestFocus();
				}
			}
		});
		txtVissPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtVissPrice.setText("");
			}
		});
		txtVissPrice.setText("0");
		txtVissPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtVissPrice.setColumns(10);
		txtVissPrice.setBackground(new Color(254, 251, 245));
		txtVissPrice.setBounds(960, 265, 106, 27);
		panel.add(txtVissPrice);
		
		txtBoxPrice = new JTextField();
		txtBoxPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtCardPrice.requestFocus();
				}
			}
		});
		txtBoxPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBoxPrice.setText("");
			}
		});
		txtBoxPrice.setText("0");
		txtBoxPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBoxPrice.setColumns(10);
		txtBoxPrice.setBackground(new Color(254, 251, 245));
		txtBoxPrice.setBounds(794, 265, 106, 27);
		panel.add(txtBoxPrice);
		
		txtCardPrice = new JTextField();
		txtCardPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtVissPrice.requestFocus();
				}
			}
		});
		txtCardPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCardPrice.setText("");
			}
		});
		txtCardPrice.setText("0");
		txtCardPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtCardPrice.setColumns(10);
		txtCardPrice.setBackground(new Color(254, 251, 245));
		txtCardPrice.setBounds(960, 165, 106, 27);
		panel.add(txtCardPrice);
		
		txtBacketPrice = new JTextField();
		txtBacketPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBoxPrice.requestFocus();
				}
			}
		});
		txtBacketPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBacketPrice.setText("");
			}
		});
		txtBacketPrice.setText("0");
		txtBacketPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBacketPrice.setColumns(10);
		txtBacketPrice.setBackground(new Color(254, 251, 245));
		txtBacketPrice.setBounds(794, 165, 106, 27);
		panel.add(txtBacketPrice);
		
		JLabel lblTitle_1_1_1_1_1 = new JLabel("ကဒ္ေစ်း");
		lblTitle_1_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_1_1.setBounds(976, 122, 90, 44);
		panel.add(lblTitle_1_1_1_1_1);
		
		JLabel lblTitle_1_1_1 = new JLabel("ပိႆာေစ်း       ");
		lblTitle_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1.setBounds(971, 225, 90, 44);
		panel.add(lblTitle_1_1_1);
		
		JLabel lblTitle_1_1 = new JLabel("ေတာင္း/ျခင္း‌ေဈး");
		lblTitle_1_1.setForeground(SystemColor.info);
		lblTitle_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1.setBounds(794, 122, 111, 44);
		panel.add(lblTitle_1_1);
		
		JLabel lblTitle_1_1_1_1 = new JLabel("ေသတၱာေစ်း     ");
		lblTitle_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_1.setBounds(804, 225, 129, 44);
		panel.add(lblTitle_1_1_1_1);
		
		JLabel lblTitle_1_2 = new JLabel("အမ်ိဳးအစား");
		lblTitle_1_2.setForeground(SystemColor.info);
		lblTitle_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_2.setBounds(794, 53, 129, 44);
		panel.add(lblTitle_1_2);
		
		/*
		 * add item
		 */
		
		String item[]= {"ထူးရွယ္", "ေအာက္ခံသီး", "အလတ္သီး", "အလုပ္သမားခ"};
		cBoxSize = new JComboBox(item);
		cBoxSize.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		cBoxSize.setBackground(new Color(253, 248, 236));
		cBoxSize.setBounds(921, 62, 145, 27);
		panel.add(cBoxSize);
		
		JLabel lblNewLabel_1 = new JLabel("အဝယ္ စာရင္း");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 23));
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setBounds(603, 11, 166, 37);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 66, 749, 366);
		panel.add(scrollPane);
		
		tbPurchase = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbPurchase.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		tbPurchase.setRowHeight(30);
		tbPurchase.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
		tbPurchase.getTableHeader().setBackground(new Color(128, 64, 0));
		tbPurchase.getTableHeader().setForeground(Color.white);
		scrollPane.setViewportView(tbPurchase);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1107, 476);
		lblNewLabel.setBackground(new Color(0,28,0));
		panel.add(lblNewLabel);
		
		refreshTable();
	}
}
