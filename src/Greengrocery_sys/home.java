package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;

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
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton.setBounds(122, 90, 203, 81);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("အဝယ္စာရင္း");
		btnNewButton_1.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setBounds(451, 90, 203, 81);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("ေန႔စဥ္မွတ္တမ္း");
		btnNewButton_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton_1_1.setBackground(SystemColor.info);
		btnNewButton_1_1.setBounds(122, 253, 203, 81);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("အသီးအမ်ိဳးအစား");
		btnNewButton_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		btnNewButton_1_1_1.setBackground(SystemColor.info);
		btnNewButton_1_1_1.setBounds(451, 253, 203, 81);
		frame.getContentPane().add(btnNewButton_1_1_1);
	}
}
