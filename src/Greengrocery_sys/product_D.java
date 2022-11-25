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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

public class product_D extends JFrame {

	private JPanel contentPane;
	private JTable tbProduct;
	private JScrollPane scrollPane;
	private JTextField txtBucket;
	private JTextField txtViss;
	private JTextField txtBox;
	private JComboBox cBoxSize; 
	private JDateChooser dateChooser;

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
	

	/*
	 * FrameMouseListener
	 */
	public class FrameMouseListener implements MouseListener,MouseMotionListener{

		private Point pressPoint;
		private Rectangle frameBounds;
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			mouseFrame(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			this.pressPoint = e.getPoint();
			this.frameBounds = new product_D().getBounds();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			mouseFrame(e);
		}
		
		public void mouseFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressPoint.x;
			int yDiff = endPoint.y - pressPoint.y;
			frameBounds.x += xDiff;
			frameBounds.y += yDiff;
			setBounds(frameBounds);
		}
	}
	
	/*
	 * refresh product table
	 */	
	public void refreshTable() {
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
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Frame Mouse Listener
		 */
		FrameMouseListener listener = new FrameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				product_D.this.setVisible(false);
			}
		});
		
		JButton btnClose_1 = new JButton("-");
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnClose_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnClose_1.setBackground(new Color(214, 54, 31));
		btnClose_1.setBounds(1119, 0, 44, 34);
		contentPane.add(btnClose_1);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClose.setBackground(new Color(214, 54, 31));
		btnClose.setBounds(1163, 0, 44, 34);
		contentPane.add(btnClose);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 66, 830, 392);
		contentPane.add(scrollPane);
		
		tbProduct = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
        };
		};
		tbProduct.setBackground(new Color(254, 251, 245));
		scrollPane.setViewportView(tbProduct);
		tbProduct.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
		tbProduct.getTableHeader().setBackground(new Color(128, 64, 0));
		tbProduct.getTableHeader().setForeground(Color.white);
		tbProduct.setFont(new Font("Zawgyi-one",Font.PLAIN,13));
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
						cBoxSize.setSelectedItem(rSet.getString("Type"));
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
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblTitle.setBounds(32, 11, 118, 44);
		contentPane.add(lblTitle);
		
		JLabel lblTitle_1_1 = new JLabel("ေတာင္းေစ်း       ");
		lblTitle_1_1.setForeground(SystemColor.info);
		lblTitle_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1.setBounds(894, 133, 151, 44);
		contentPane.add(lblTitle_1_1);
		
		JLabel lblTitle_1_1_1 = new JLabel("ပိႆာေစ်း       ");
		lblTitle_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1.setBounds(894, 206, 129, 44);
		contentPane.add(lblTitle_1_1_1);
		
		JLabel lblTitle_1_1_1_1 = new JLabel("ေသတၱာေစ်း     ");
		lblTitle_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_1.setBounds(894, 278, 129, 44);
		contentPane.add(lblTitle_1_1_1_1);
		
		txtBucket = new JTextField();
		txtBucket.setBackground(new Color(254, 251, 245));
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
		txtBucket.setBounds(1057, 141, 140, 27);
		contentPane.add(txtBucket);
		
		txtViss = new JTextField();
		txtViss.setBackground(new Color(254, 251, 245));
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
		txtViss.setBounds(1057, 214, 140, 27);
		contentPane.add(txtViss);
		
		txtBox = new JTextField();
		txtBox.setBackground(new Color(254, 251, 245));
		txtBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBucket.requestFocus();
				}
			}
		});
		txtBox.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBox.setColumns(10);
		txtBox.setBounds(1057, 286, 140, 27);
		contentPane.add(txtBox);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeString= cBoxSize.getSelectedItem().toString();
				int bucketPrice = Integer.parseInt(txtBucket.getText());
				int vissPrice = Integer.parseInt(txtViss.getText());
				int boxPrice = Integer.parseInt(txtBox.getText());
				
				/*
				 * Get Today Date from DateChooser
				 */
				String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				System.out.print("Date chooser " + dateString);
				new addProduct_F().addProduct(typeString, bucketPrice, vissPrice, boxPrice, dateString);
				refreshTable();
			}
		});
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBounds(894, 356, 129, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.setForeground(new Color(128, 64, 0));
		btnRemove.setBackground(new Color(254, 251, 245));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tbProduct.getSelectedRow();
				if(row != -1) {
					String idString = tbProduct.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(idString);
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?", "Confirm Dialog", JOptionPane.YES_NO_OPTION);
					if(confirm == 0) {
						new deleteProduct_F().deleteProduct(id);
					}
					refreshTable();
				}
			}
		});
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemove.setBounds(1068, 356, 129, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeString = cBoxSize.getSelectedItem().toString();
				int bucketPrice = Integer.parseInt(txtBucket.getText());
				int vissPrice = Integer.parseInt(txtViss.getText());
				int boxPrice = Integer.parseInt(txtBox.getText());
				
				int r = tbProduct.getSelectedRow();
				if(r != -1) {
					String idString = tbProduct.getModel().getValueAt(r, 0).toString();
					int id = Integer.parseInt(idString);
					
					new updateProduct_F().updateProduct(id, typeString, bucketPrice, vissPrice, boxPrice);
					refreshTable();
				}
			}
		});
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBounds(894, 424, 129, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtBucket.setText("");
				txtViss.setText("");
				txtBox.setText("");
			}
		});
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBounds(1068, 424, 129, 34);
		contentPane.add(btnClear);
		
		JLabel lblTitle_1_2 = new JLabel("အမ်ိဳးအစား");
		lblTitle_1_2.setForeground(SystemColor.info);
		lblTitle_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_2.setBounds(896, 66, 129, 44);
		contentPane.add(lblTitle_1_2);
		
		/*
		 * add item on JcomboBox
		 */
		String item[]= {"ထူးရွယ္", "ေအာက္ခံသီး", "အလတ္သီး"};
		cBoxSize = new JComboBox(item);
		cBoxSize.setBackground(new Color(253, 248, 236));
		cBoxSize.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		cBoxSize.setBounds(1057, 75, 140, 27);
		
		
		contentPane.add(cBoxSize);
		
		dateChooser = new JDateChooser();
		dateChooser.setBackground(new Color(254, 251, 245));
		/*
		 * set today date
		 */
		Date date = new Date();
		dateChooser.setDate(date);		
		
		dateChooser.setFont(new Font("Zawgyi-one",Font.BOLD,15));
		dateChooser.setBounds(656, 11, 194, 44);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 28, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1213, 495);
		contentPane.add(lblNewLabel);
		
		refreshTable();
	}
}
