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
import java.awt.Point;
import java.awt.Rectangle;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

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
	 * add frame MouseListener
	 */
	
	public class frameMouseListener implements MouseListener, MouseMotionListener {

		private Point pressedPoint;
		private Rectangle framebounds;
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouseFrame(arg0);
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			this.pressedPoint = arg0.getPoint();
			this.framebounds = report_D.this.getBounds();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouseFrame(arg0);
		}
		
		public void mouseFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressedPoint.x;
			int yDiff = endPoint.y - pressedPoint.y;
			framebounds.x += xDiff;
			framebounds.y += yDiff;
			setBounds(framebounds);}
		
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
	
	/*
	 * Fill the Type Box
	 */
	public void fillTypeBox() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from sale_price;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			cboxType.addItem("All");
			
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
	public report_D() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1157, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		frameMouseListener  listener = new frameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1157, 666);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnMinimize = new JButton("-");
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnMinimize.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnMinimize.setBackground(Color.RED);
		btnMinimize.setBounds(1072, 0, 43, 31);
		panel.add(btnMinimize);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				report_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnClose.setBackground(Color.RED);
		btnClose.setBounds(1114, 0, 43, 31);
		panel.add(btnClose);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Zawgyi-One", Font.PLAIN, 19));
		tabbedPane.setBounds(10, 86, 1132, 515);
		panel.add(tabbedPane);
		
		JPanel showOrder = new JPanel();
		showOrder.setBackground(new Color(255, 222, 173));
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
		tbOrderDetail.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
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
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Type : ");
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2_1.setBounds(357, 11, 76, 32);
		showOrder.add(lblNewLabel_1_2_1);
		
		cboxType = new JComboBox();
		cboxType.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String typeString = cboxType.getSelectedItem().toString();
				String sqlString = "select * from customer_order where Type = '" + typeString + "';";
				
				Connection connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					if(typeString.equals("All")){
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
		cboxType.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		cboxType.setBounds(429, 8, 157, 36);
		showOrder.add(cboxType);
		
		JButton btnSearch = new JButton("စစ္ေဆးမည္");
		btnSearch.setBackground(new Color(255, 248, 220));
		btnSearch.setFont(new Font("Zawgyi-One", Font.PLAIN, 19));
		btnSearch.setBounds(923, 43, 149, 32);
		panel.add(btnSearch);
		
		JLabel lblNewLabel_1_1 = new JLabel("End Date :");
		lblNewLabel_1_1.setForeground(SystemColor.info);
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(487, 43, 116, 32);
		panel.add(lblNewLabel_1_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(613, 43, 188, 32);
		panel.add(dateChooser_1);
		
		JLabel lblNewLabel_1 = new JLabel("Start Date : ");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1.setBounds(143, 43, 121, 32);
		panel.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(264, 43, 188, 32);
		panel.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0,24,0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1157, 655);
		panel.add(lblNewLabel);
		
		refreshOrder();
		fillNameBox();
		fillTypeBox();
	}
}
