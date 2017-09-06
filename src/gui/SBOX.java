package gui;

import java.awt.Color;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class SBOX {


	public JFrame frmSbox;
	public JTable table;

	
	
	private static final boolean KEY_SCHEDULE = false;


	/**
	 * Create the application.
	 */
	public SBOX(boolean type) {
		initialize(type);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean type) {
		frmSbox = new JFrame();
		frmSbox.setResizable(false);
		frmSbox.setTitle(Rounds.ENCRYPT == true ? "S-Box" : "Inv S-Box");
		frmSbox.setType(Type.POPUP);
		frmSbox.setBounds(100, 100, 610, 320);
		frmSbox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSbox.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Verdana", Font.PLAIN, 18));
		table.setForeground(Color.BLACK);
		table.setBackground(SystemColor.menu);
		table.setEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"},
				{"SBox1", "3", "8", "F", "1", "A", "6", "5", "B", "E", "D", "4", "2", "7", "0", "9", "C"},
				{"SBox2", "F", "C", "2", "7", "9", "0", "5", "A", "1", "B", "E", "8", "6", "D", "3", "4"},
				{"SBox3", "8", "6", "7", "9", "3", "C", "A", "F", "D", "1", "E", "4", "0", "B", "5", "2"},
				{"SBox4", "0", "F", "B", "8", "C", "9", "6", "3", "D", "1", "2", "4", "A", "7", "5", "E"},
				{"SBox5", "1", "F", "8", "3", "C", "0", "B", "6", "2", "5", "4", "A", "9", "E", "7", "D"},
				{"SBox6", "F", "5", "2", "B", "4", "A", "9", "C", "0", "3", "E", "8", "D", "6", "7", "1"},
				{"SBox7", "7", "2", "C", "5", "8", "4", "6", "B", "E", "9", "1", "F", "D", "3", "A", "0"},
				{"SBox8", "1", "D", "F", "0", "E", "8", "2", "B", "7", "4", "C", "A", "9", "3", "5", "6"},
			},
			new String[] {
				"Name", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setMinWidth(25);
		table.getColumnModel().getColumn(1).setMaxWidth(30);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setMinWidth(25);
		table.getColumnModel().getColumn(2).setMaxWidth(30);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(25);
		table.getColumnModel().getColumn(3).setMaxWidth(30);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setMinWidth(25);
		table.getColumnModel().getColumn(4).setMaxWidth(30);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setMinWidth(25);
		table.getColumnModel().getColumn(5).setMaxWidth(30);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setMinWidth(25);
		table.getColumnModel().getColumn(6).setMaxWidth(30);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setMinWidth(25);
		table.getColumnModel().getColumn(7).setMaxWidth(30);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(8).setPreferredWidth(30);
		table.getColumnModel().getColumn(8).setMinWidth(25);
		table.getColumnModel().getColumn(8).setMaxWidth(30);
		table.getColumnModel().getColumn(9).setResizable(false);
		table.getColumnModel().getColumn(9).setPreferredWidth(30);
		table.getColumnModel().getColumn(9).setMinWidth(25);
		table.getColumnModel().getColumn(9).setMaxWidth(30);
		table.getColumnModel().getColumn(10).setResizable(false);
		table.getColumnModel().getColumn(10).setPreferredWidth(30);
		table.getColumnModel().getColumn(10).setMinWidth(25);
		table.getColumnModel().getColumn(10).setMaxWidth(30);
		table.getColumnModel().getColumn(11).setResizable(false);
		table.getColumnModel().getColumn(11).setPreferredWidth(30);
		table.getColumnModel().getColumn(11).setMinWidth(25);
		table.getColumnModel().getColumn(11).setMaxWidth(30);
		table.getColumnModel().getColumn(12).setResizable(false);
		table.getColumnModel().getColumn(12).setPreferredWidth(30);
		table.getColumnModel().getColumn(12).setMinWidth(25);
		table.getColumnModel().getColumn(12).setMaxWidth(30);
		table.getColumnModel().getColumn(13).setResizable(false);
		table.getColumnModel().getColumn(13).setPreferredWidth(30);
		table.getColumnModel().getColumn(13).setMinWidth(25);
		table.getColumnModel().getColumn(13).setMaxWidth(30);
		table.getColumnModel().getColumn(14).setResizable(false);
		table.getColumnModel().getColumn(14).setPreferredWidth(30);
		table.getColumnModel().getColumn(14).setMinWidth(25);
		table.getColumnModel().getColumn(14).setMaxWidth(30);
		table.getColumnModel().getColumn(15).setResizable(false);
		table.getColumnModel().getColumn(15).setPreferredWidth(30);
		table.getColumnModel().getColumn(15).setMinWidth(25);
		table.getColumnModel().getColumn(15).setMaxWidth(30);
		table.getColumnModel().getColumn(16).setResizable(false);
		table.getColumnModel().getColumn(16).setPreferredWidth(30);
		table.getColumnModel().getColumn(16).setMinWidth(25);
		table.getColumnModel().getColumn(16).setMaxWidth(30);
		
		MainFrame.tableAlignCenter(table);
		table.setBounds(12, 10, 580, 270);
		frmSbox.getContentPane().add(table);
	}
}
