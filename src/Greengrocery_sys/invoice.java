package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

public class invoice extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					invoice frame = new invoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Mouse Listener
	 */
	
	public class FrameMouseListener implements MouseListener,MouseMotionListener{

		private Point pressedPoint;
		private Rectangle frameBound;
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			moveFrame(e);
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
			this.frameBound = new invoice().getBounds();
			this.pressedPoint = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			moveFrame(e);
		}
		
		public void moveFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressedPoint.x;
			int yDiff = endPoint.y - pressedPoint.y;
			frameBound.x += xDiff;
			frameBound.y += yDiff;
			setBounds(frameBound);
		}
		
	}

	/**
	 * Create the frame.
	 */
	public invoice() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 80, 570, 636);
		contentPane = new JPanel();
		//contentPane.setPreferredSize(new Dimension(mainPanel.getWidth(),mainPanel.getHeight()));
		
		FrameMouseListener listener = new FrameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 51, 0));
		mainPanel.setBounds(0, 0, 570, 636);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				invoice.this.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(199, 5, 58));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(521, 0, 49, 23);
		mainPanel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("<html><strong>09 52111 54<br>\r\n09 78 52111 54<br>09 560 1148</strong></html>",JLabel.LEFT);
		lblNewLabel_2.setFont(new Font("Sesame", Font.PLAIN, 9));
		lblNewLabel_2.setBackground(new Color(0, 255, 0));
		lblNewLabel_2.setForeground(SystemColor.info);
		lblNewLabel_2.setBounds(403, 30, 141, 56);
		mainPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("ရာဇာၿဖိဳး");
		lblNewLabel.setForeground(new Color(255, 255, 119));
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(146, 0, 252, 66);
		mainPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ကုန္စိမ္းေရာင္းဝယ္ေရး");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(107, 58, 341, 28);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblAddress = new JLabel();
		lblAddress.setText("ေဖာင္ေတာ္ျပန္လမ္း၊နန္းပန္ရပ္(၂)၊ကမ္းနားလမ္း၊ေညာင္ေ႐ႊၿမိဳ႕။");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setForeground(UIManager.getColor("info"));
		lblAddress.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblAddress.setBounds(50, 123, 456, 41);
		mainPanel.add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel();
		lblAddress_1.setText("ျမနႏၵာရေထ​ြက္ဦးလမ္း၊ေညာင္ပင္ေထာင္ရပ္က​ြက္၊ျမနႏၵာ(၃)လမ္း၊ေအာင္ပန္းၿမိဳ႕၊");
		lblAddress_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress_1.setForeground(UIManager.getColor("info"));
		lblAddress_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblAddress_1.setBounds(40, 91, 504, 41);
		mainPanel.add(lblAddress_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(199, 236, 183));
		panel_1.setBounds(10, 163, 550, 443);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("အမည္ :");
		lblNewLabel_3.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(52, 11, 46, 33);
		panel_1.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(108, 15, 140, 22);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_3_1 = new JLabel("ေန႔စ​ြဲ :");
		lblNewLabel_3_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(295, 11, 46, 33);
		panel_1.add(lblNewLabel_3_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(336, 11, 149, 29);
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel_3_2 = new JLabel("လိပ္စာ :");
		lblNewLabel_3_2.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_2.setBounds(52, 48, 46, 33);
		panel_1.add(lblNewLabel_3_2);
		
		textField = new JTextField();
		textField.setBounds(108, 53, 377, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 530, 236);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3_3 = new JLabel("စုစုေပါင္း :");
		lblNewLabel_3_3.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3.setBounds(342, 337, 56, 33);
		panel_1.add(lblNewLabel_3_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(408, 342, 132, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("စရံေင​ြ :");
		lblNewLabel_3_3_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3_1.setBounds(352, 364, 46, 33);
		panel_1.add(lblNewLabel_3_3_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(408, 373, 132, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(408, 404, 132, 20);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_3_3_1_1 = new JLabel("က်န္ေင​ြ :");
		lblNewLabel_3_3_1_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3_1_1.setBounds(342, 399, 46, 33);
		panel_1.add(lblNewLabel_3_3_1_1);
		
		JLabel lblAddress_2 = new JLabel();
		lblAddress_2.setText("ဝယ္ယူအားေပးမႈကိုေက်းဇူးတင္ပါသည္");
		lblAddress_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress_2.setForeground(SystemColor.activeCaptionText);
		lblAddress_2.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblAddress_2.setBounds(10, 333, 193, 41);
		panel_1.add(lblAddress_2);
		
		JLabel lblNewLabel_3_3_2 = new JLabel("လက္မွတ္ :");
		lblNewLabel_3_3_2.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3_2.setBounds(10, 399, 56, 33);
		panel_1.add(lblNewLabel_3_3_2);
		
		JLabel lblImage = new JLabel("");
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\Tomato Green Grocery\\Images\\tomato.jpg"));
		lblImage.setBounds(0, 0, 570, 625);
		mainPanel.add(lblImage);
	}
}
