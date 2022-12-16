package Greengrocery_sys;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class report_D extends JFrame {

	private JPanel contentPane;
	private JTable tbOrderDetail;
	private JComboBox cboxName, cboxType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					report_D frame = new report_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * refresh the order table
	 */
	public void refreshOrder() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from customer_order;";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			tbOrderDetail.setModel(DbUtils.resultSetToTableModel(rSet));
			TableColumnModel columnModel = tbOrderDetail.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(11));
			columnModel.removeColumn(columnModel.getColumn(10));
			columnModel.removeColumn(columnModel.getColumn(0));
							
			DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
			renderer.setBackground(new Color(210,251,250));
			TableColumn priceColumn = tbOrderDetail.getColumnModel().getColumn(6);
			priceColumn.setCellRenderer(renderer);
			
			DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
			renderer2.setBackground(new Color(196,186,252));
			tbOrderDetail.getColumnModel().getColumn(7).setCellRenderer(renderer2);
			
			DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
			renderer3.setBackground(new Color(251,237,159));
			tbOrderDetail.getColumnModel().getColumn(5).setCellRenderer(renderer3);
			
			columnModel.setColumnMargin(11);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Fill the Name box
	 */
	public void fillNameBox() {
		Connection connection = new DbConnection().connect();
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

	/**
	 * Create the frame.
	 */
	public report_D() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1156, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1140, 576);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Zawgyi-One", Font.PLAIN, 19));
		tabbedPane.setBounds(10, 50, 1132, 515);
		panel.add(tabbedPane);
		
		JPanel showOrder = new JPanel();
		tabbedPane.addTab("ေအာ္ဒါစာရင္း", null, showOrder, null);
		showOrder.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 1107, 375);
		showOrder.add(scrollPane);
		
		tbOrderDetail = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbOrderDetail.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		tbOrderDetail.setRowHeight(30);
		scrollPane.setViewportView(tbOrderDetail);
		
		JLabel lblNewLabel_1_2 = new JLabel("Customer : ");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2.setBounds(10, 11, 123, 32);
		showOrder.add(lblNewLabel_1_2);
		
		cboxName = new JComboBox();
		cboxName.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String nameString = cboxName.getSelectedItem().toString();
				String sql = "select * from customer_order where Customer = '" + nameString + "';";
				
				Connection connection= new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sql);
					ResultSet rSet = pStatement.executeQuery();
					
					if(nameString.equals("All")) {
						refreshOrder();
					}
					
					else {
					
						tbOrderDetail.setModel(DbUtils.resultSetToTableModel(rSet));
						TableColumnModel columnModel = tbOrderDetail.getColumnModel();
						columnModel.removeColumn(columnModel.getColumn(11));
						columnModel.removeColumn(columnModel.getColumn(10));
						columnModel.removeColumn(columnModel.getColumn(0));
										
						DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
						renderer.setBackground(new Color(210,251,250));
						TableColumn priceColumn = tbOrderDetail.getColumnModel().getColumn(6);
						priceColumn.setCellRenderer(renderer);
						
						DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
						renderer2.setBackground(new Color(196,186,252));
						tbOrderDetail.getColumnModel().getColumn(7).setCellRenderer(renderer2);
						
						DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
						renderer3.setBackground(new Color(251,237,159));
						tbOrderDetail.getColumnModel().getColumn(5).setCellRenderer(renderer3);
						
						columnModel.setColumnMargin(11);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cboxName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		cboxName.setBounds(123, 11, 157, 36);
		showOrder.add(cboxName);
		
		JButton btnSearch = new JButton("စစ္ေဆးမည္");
		btnSearch.setBackground(new Color(255, 248, 220));
		btnSearch.setFont(new Font("Zawgyi-One", Font.PLAIN, 19));
		btnSearch.setBounds(927, 11, 149, 32);
		panel.add(btnSearch);
		
		JLabel lblNewLabel_1_1 = new JLabel("End Date :");
		lblNewLabel_1_1.setForeground(SystemColor.info);
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(491, 11, 116, 32);
		panel.add(lblNewLabel_1_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(617, 11, 188, 32);
		panel.add(dateChooser_1);
		
		JLabel lblNewLabel_1 = new JLabel("Start Date : ");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1.setBounds(147, 11, 121, 32);
		panel.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(268, 11, 188, 32);
		panel.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0,24,0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1142, 565);
		panel.add(lblNewLabel);
		
		refreshOrder();
		fillNameBox();
	}
}