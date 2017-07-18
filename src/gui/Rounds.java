package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class Rounds {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rounds window = new Rounds();
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
	public Rounds() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 582, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("XOR", null, panel, null);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(64, 75, 1, 1);
		panel.add(table);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(31, 55, 121, 97);
		panel.add(tabbedPane_1);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(223, 55, 121, 97);
		panel.add(tabbedPane_2);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(430, 55, 121, 97);
		panel.add(tabbedPane_3);
		
		JLabel lblXor = new JLabel("XOR");
		lblXor.setBounds(167, 91, 46, 14);
		panel.add(lblXor);
		
		JLabel label = new JLabel("=");
		label.setBounds(375, 91, 46, 14);
		panel.add(label);
		
		JLabel lblSubkeyround = new JLabel("SubKey (round)");
		lblSubkeyround.setBounds(223, 30, 75, 14);
		panel.add(lblSubkeyround);
		
		JLabel lblPrivi = new JLabel("Previously result");
		lblPrivi.setBounds(34, 30, 118, 14);
		panel.add(lblPrivi);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(430, 30, 46, 14);
		panel.add(lblResult);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(432, 182, 89, 23);
		panel.add(btnNext);
		
		JButton btnSubKey = new JButton("Sub Key");
		btnSubKey.setBounds(243, 182, 89, 23);
		panel.add(btnSubKey);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("SBox", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_10 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_10.setBounds(31, 49, 121, 97);
		panel_1.add(tabbedPane_10);
		
		JLabel label_7 = new JLabel("Previously result");
		label_7.setBounds(34, 24, 118, 14);
		panel_1.add(label_7);
		
		JTabbedPane tabbedPane_11 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_11.setBounds(430, 49, 121, 97);
		panel_1.add(tabbedPane_11);
		
		JLabel label_8 = new JLabel("Result");
		label_8.setBounds(430, 24, 46, 14);
		panel_1.add(label_8);
		
		JButton button_2 = new JButton("Next");
		button_2.setBounds(432, 176, 89, 23);
		panel_1.add(button_2);
		
		JLabel label_11 = new JLabel("=");
		label_11.setBounds(375, 85, 46, 14);
		panel_1.add(label_11);
		
		JButton btnSbox = new JButton("SBOX");
		btnSbox.setBounds(233, 60, 89, 65);
		panel_1.add(btnSbox);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("LT", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_6 = new JLabel("Previously result");
		label_6.setBounds(34, 30, 118, 14);
		panel_2.add(label_6);
		
		JTabbedPane tabbedPane_7 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_7.setBounds(31, 55, 121, 97);
		panel_2.add(tabbedPane_7);
		
		JLabel label_9 = new JLabel("=");
		label_9.setBounds(375, 91, 46, 14);
		panel_2.add(label_9);
		
		JTabbedPane tabbedPane_9 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_9.setBounds(430, 55, 121, 97);
		panel_2.add(tabbedPane_9);
		
		JLabel label_10 = new JLabel("Result");
		label_10.setBounds(430, 30, 46, 14);
		panel_2.add(label_10);
		
		JButton button_1 = new JButton("Next");
		button_1.setBounds(432, 182, 89, 23);
		panel_2.add(button_1);
		
		JLabel lblLt = new JLabel("LT");
		lblLt.setBounds(162, 91, 46, 14);
		panel_2.add(lblLt);
		
		JTabbedPane tabbedPane_8 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_8.setForeground(Color.RED);
		tabbedPane_8.setBounds(225, 55, 121, 97);
		panel_2.add(tabbedPane_8);
		
		JLabel lblPictureOfLt = new JLabel("Picture of LT");
		lblPictureOfLt.setBounds(233, 30, 113, 14);
		panel_2.add(lblPictureOfLt);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("XOR(final round)", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label_1 = new JLabel("Previously result");
		label_1.setBounds(34, 24, 118, 14);
		panel_3.add(label_1);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_4.setBounds(31, 49, 121, 97);
		panel_3.add(tabbedPane_4);
		
		JLabel label_2 = new JLabel("XOR");
		label_2.setBounds(167, 85, 46, 14);
		panel_3.add(label_2);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_5.setBounds(223, 49, 121, 97);
		panel_3.add(tabbedPane_5);
		
		JLabel label_3 = new JLabel("SubKey (round)");
		label_3.setBounds(223, 24, 75, 14);
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("=");
		label_4.setBounds(375, 85, 46, 14);
		panel_3.add(label_4);
		
		JTabbedPane tabbedPane_6 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_6.setBounds(430, 49, 121, 97);
		panel_3.add(tabbedPane_6);
		
		JLabel label_5 = new JLabel("Result");
		label_5.setBounds(430, 24, 46, 14);
		panel_3.add(label_5);
		
		JButton button = new JButton("Next");
		button.setBounds(432, 176, 89, 23);
		panel_3.add(button);
	}
}
