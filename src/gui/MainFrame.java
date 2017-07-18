package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import crypto.Serpent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class MainFrame {

	private JFrame frame;
	
	private Serpent serpent;
	private JTextField keyTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
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
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(39, 58, 39, 14);
		frame.getContentPane().add(lblKey);
		
		keyTextField = new JTextField();
		keyTextField.setBounds(132, 55, 155, 20);
		frame.getContentPane().add(keyTextField);
		keyTextField.setColumns(10);
		
		JButton radnom = new JButton("Random");
		radnom.setBounds(124, 86, 89, 23);
		frame.getContentPane().add(radnom);
		
		JRadioButton rdbtnCrypto = new JRadioButton("crypto");
		rdbtnCrypto.setBounds(39, 142, 109, 23);
		frame.getContentPane().add(rdbtnCrypto);
		
		JRadioButton rdbtnDecrypto = new JRadioButton("decrypto");
		rdbtnDecrypto.setBounds(39, 168, 109, 23);
		frame.getContentPane().add(rdbtnDecrypto);
	}
}
