package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import crypto.SerpentData;

public class InitialPermutation {

	private static SerpentData data = SerpentData.getInstance();

	public JFrame frmInitialPermutation;

	private JTable PlainTextTable;
	private JTable AfterInitalTable;
	private JPanel navPanel;

	/**
	 * Create the application.
	 */
	public InitialPermutation() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInitialPermutation = new JFrame();
		frmInitialPermutation.setTitle("Initial Permutation");
		frmInitialPermutation.setResizable(false);
		frmInitialPermutation.setBounds(100, 100, 900, 400);
		frmInitialPermutation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInitialPermutation.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Inital permutation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.setBounds(20, 11, 622, 310);
		frmInitialPermutation.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnNext.setBounds(455, 241, 135, 35);
		panel.add(btnNext);

		JButton btnNewButton = new JButton("IP");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnNewButton.setBounds(282, 121, 55, 35);
		panel.add(btnNewButton);

		JLabel lblPlainText = new JLabel((Rounds.ENCRYPT ? "Plain text" : "Cipher text") + " (big endian)");
		lblPlainText.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPlainText.setBounds(21, 46, 263, 25);
		panel.add(lblPlainText);

		JLabel lblPlainTextAfter = new JLabel("After permutation");
		lblPlainTextAfter.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPlainTextAfter.setBounds(358, 46, 175, 25);
		panel.add(lblPlainTextAfter);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnBack.setBounds(295, 241, 135, 35);
		panel.add(btnBack);

		PlainTextTable = new JTable();
		PlainTextTable.setRowHeight(30);
		
		PlainTextTable.setEnabled(false);
		PlainTextTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				PlainTextTable.clearSelection();
				AfterInitalTable.clearSelection();
				
			}
		});
		PlainTextTable.setFont(new Font("Verdana", Font.PLAIN, 18));
		PlainTextTable.setBounds(21, 78, 240, 120);

		panel.add(PlainTextTable);

		PlainTextTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = PlainTextTable.rowAtPoint(e.getPoint());
				int column = PlainTextTable.columnAtPoint(e.getPoint());
				if (row > -1) {

					PlainTextTable.setRowSelectionInterval(row, row);
					PlainTextTable.setColumnSelectionInterval(column, column);

					AfterInitalTable.setRowSelectionInterval(row, row);
					AfterInitalTable.setColumnSelectionInterval(column, column);

				} 

			}
		});
		PlainTextTable.setColumnSelectionAllowed(true);
		PlainTextTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		PlainTextTable.setModel(new DefaultTableModel(SerpentData.intToObject(data.text),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

			private static final long serialVersionUID = -595476558480084675L;
			Class<?>[] columnTypes = new Class[] { Character.class, Character.class, Character.class, Character.class,
					Character.class, Character.class, Character.class, Character.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		PlainTextTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(0).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(0).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(1).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(1).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(2).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(2).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(3).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(3).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(4).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(4).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(5).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(5).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(6).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(6).setMaxWidth(50);
		PlainTextTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		PlainTextTable.getColumnModel().getColumn(7).setMinWidth(20);
		PlainTextTable.getColumnModel().getColumn(7).setMaxWidth(50);
		PlainTextTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		PlainTextTable.setBackground(SystemColor.menu);

		MainFrame.tableAlignCenter(PlainTextTable);

		AfterInitalTable = new JTable();
		AfterInitalTable.setRowHeight(30);
		AfterInitalTable.setEnabled(false);
		AfterInitalTable.setFont(new Font("Verdana", Font.PLAIN, 18));

		AfterInitalTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				PlainTextTable.clearSelection();
				AfterInitalTable.clearSelection();
			}
		});
		AfterInitalTable.setBounds(358, 78, 240, 120);
		panel.add(AfterInitalTable);
		AfterInitalTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = AfterInitalTable.rowAtPoint(e.getPoint());
				int column = AfterInitalTable.columnAtPoint(e.getPoint());
				if (row > -1) {

					PlainTextTable.setRowSelectionInterval(row, row);
					PlainTextTable.setColumnSelectionInterval(column, column);

					AfterInitalTable.setRowSelectionInterval(row, row);
					AfterInitalTable.setColumnSelectionInterval(column, column);


				} 
			}
		});
		AfterInitalTable.setColumnSelectionAllowed(true);
		AfterInitalTable.setModel(new DefaultTableModel(SerpentData.intToObject(data.afterInitalPermutation),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1631947820306732872L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		AfterInitalTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(0).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(0).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(1).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(1).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(2).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(2).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(3).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(3).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(4).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(4).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(5).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(5).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(6).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(6).setMaxWidth(50);
		AfterInitalTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		AfterInitalTable.getColumnModel().getColumn(7).setMinWidth(20);
		AfterInitalTable.getColumnModel().getColumn(7).setMaxWidth(50);
		AfterInitalTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		AfterInitalTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		AfterInitalTable.setBackground(SystemColor.menu);

		MainFrame.tableAlignCenter(AfterInitalTable);

		navPanel = new JPanel();
		navPanel.setFont(new Font("Verdana", Font.PLAIN, 13));
		navPanel.setBorder(new TitledBorder(null, "Navigation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		navPanel.setBounds(652, 11, 230, 310);
		frmInitialPermutation.getContentPane().add(navPanel);
		navPanel.setLayout(null);

		JButton firstPermutation = new JButton(Rounds.ENCRYPT ? "Inital permutation" : "Final Permutation");
		firstPermutation.setFont(new Font("Verdana", Font.PLAIN, 18));
		firstPermutation.setEnabled(false);
		firstPermutation.setBounds(10, 34, 212, 35);
		navPanel.add(firstPermutation);

		JButton lastPermutation = new JButton(Rounds.ENCRYPT ? "Final Permutation" : "Inital permutation");
		lastPermutation.setFont(new Font("Verdana", Font.PLAIN, 18));
		lastPermutation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rounds.round = (Rounds.ENCRYPT == true) ? 32 : 1;
				frmInitialPermutation.setVisible(false);
				MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(true);

			}
		});
		lastPermutation.setBounds(10, 172, 212, 35);
		navPanel.add(lastPermutation);

		JButton round = new JButton("Rounds");
		round.setFont(new Font("Verdana", Font.PLAIN, 18));
		round.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Rounds.round = (Rounds.ENCRYPT == true) ? 1 : 32;
				
				frmInitialPermutation.setVisible(false);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(true);
			}
		});
		round.setBounds(10, 103, 212, 35);
		navPanel.add(round);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmInitialPermutation.setVisible(false);
				MainFrame.frmSerpant.setVisible(true);

			}

		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				IP ip = new IP(1);
				ip.frmInitalPermutationPanel.setVisible(true);
			}

		});
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Rounds.round = (Rounds.ENCRYPT == true) ? 1 : 32;
				frmInitialPermutation.setVisible(false);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(true);
			}
		});

		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Verdana", Font.PLAIN, 18));
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rounds.round = 1;
				MainFrame.frmSerpant.setVisible(true);
				MainFrame.mainFrame.initialPermutationFrame.frmInitialPermutation.setVisible(false);
			}
		});
		reset.setBounds(10, 241, 212, 35);
		navPanel.add(reset);

	}
}
