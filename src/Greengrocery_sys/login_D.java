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
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class login_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new login_D().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * MouseListener
	 */
	
	public class FrameMouseListener implements MouseListener, MouseMotionListener{

		private Point pressPoint;
		private Rectangle framebound;
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			moveJFrame(e);
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
			this.framebound = new login_D().getBounds();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			moveJFrame(e);
			
		}
				
		private void moveJFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressPoint.x;
			int yDiff = endPoint.y - pressPoint.y;
			framebound.x += xDiff;
			framebound.y += yDiff;
			setBounds(framebound);
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public login_D() {
				
		setUndecorated(true);
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 398, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		/*
		 * FrameMouseListener
		 */
		
		FrameMouseListener listener = new FrameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("အေကာင့္ဝင္မည္");
		lblNewLabel.setForeground(new Color(245, 255, 250));
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 26, 229, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("နာမည္    : ");
		lblNewLabel_1.setForeground(new Color(245, 255, 250));
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblNewLabel_1.setBounds(70, 109, 86, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("စကားဝွက္ : ");
		lblNewLabel_1_1.setForeground(new Color(245, 255, 250));
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(70, 185, 86, 41);
		contentPane.add(lblNewLabel_1_1);
		
		txtName = new JTextField();
		txtName.setBounds(195, 114, 141, 31);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(195, 190, 141, 31);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameString = txtName.getText();
				String passString = txtPassword.getText();
				
				new login_F().checkLogin(nameString, passString);
				new home().frame.setVisible(true);
				login_D.this.setVisible(false);
				
			}
		});
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnNewButton.setBounds(159, 254, 86, 38);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("X");
		btnClose.setForeground(Color.BLACK);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClose.setBackground(new Color(176, 0, 0));
		btnClose.setBounds(339, 0, 59, 31);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel_2.setBounds(0, 0, 398, 352);
		contentPane.add(lblNewLabel_2);
	}
}
