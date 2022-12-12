package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class home {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
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
	public home() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(350, 100, 817, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JButton btnNewButton = new JButton("ကုန္သည္");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Customer().frame.setVisible(true);
			}
		});
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton.setBounds(122, 90, 203, 81);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("အေရာင္းေစ်း စာရင္း");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new saleProduct_D().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setBounds(451, 90, 203, 81);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Order စာရင္း");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Order_D().setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton_1_1.setBackground(SystemColor.info);
		btnNewButton_1_1.setBounds(122, 253, 203, 81);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("အဝယ္ စာရင္း");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton_1_1_1.setBackground(SystemColor.info);
		btnNewButton_1_1_1.setBounds(451, 253, 203, 81);
		frame.getContentPane().add(btnNewButton_1_1_1);
	}
}
