package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

public class InitialFinalPermutation {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialFinalPermutation window = new InitialFinalPermutation();
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
	public InitialFinalPermutation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(320, 227, 89, 23);
		frame.getContentPane().add(btnNext);
		
		JButton btnNewButton = new JButton("IP / FP");
		btnNewButton.setBounds(193, 111, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(52, 80, 121, 97);
		frame.getContentPane().add(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(292, 80, 121, 97);
		frame.getContentPane().add(tabbedPane_1);
		
		JLabel lblPlainText = new JLabel("plain text");
		lblPlainText.setBounds(69, 55, 46, 14);
		frame.getContentPane().add(lblPlainText);
		
		JLabel lblPlainTextAfter = new JLabel("plain text after IP/FP");
		lblPlainTextAfter.setBounds(292, 55, 117, 14);
		frame.getContentPane().add(lblPlainTextAfter);
	}
}
