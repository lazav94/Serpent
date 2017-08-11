package gui;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import crypto.SerpentData;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseMotionAdapter;

public class FinalPermutation {

	private static SerpentData data = SerpentData.getInstance();
	
	public JFrame frmFinalPermutation;

	
	
	private JTable beforFinalPermutationTable;
	private JTable afterFinalPermutationTable;
	
	


	/**
	 * Create the application.
	 */
	public FinalPermutation() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinalPermutation = new JFrame();
		frmFinalPermutation.setResizable(false);
		frmFinalPermutation.setTitle("Final Permutation");
		frmFinalPermutation.setBounds(100, 100, 652, 258);
		frmFinalPermutation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinalPermutation.getContentPane().setLayout(null);
		frmFinalPermutation.setVisible(false);
		
		JPanel navPanel = new JPanel();
		navPanel.setBorder(new TitledBorder(null, "Navigation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		navPanel.setBounds(456, 11, 180, 206);
		frmFinalPermutation.getContentPane().add(navPanel);
		navPanel.setLayout(null);
		
		JButton firstPermutation = new JButton("Inital permutation");
		firstPermutation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(false);
				MainFrame.mainFrame.initialPermutationFrame.frmInitialPermutation.setVisible(true);
				
				
			}
		});
		firstPermutation.setBounds(10, 22, 160, 23);
		navPanel.add(firstPermutation);
		
		JButton lastPermutation = new JButton("Final Permutation");
		lastPermutation.setEnabled(false);
		lastPermutation.setBounds(10, 112, 160, 23);
		navPanel.add(lastPermutation);
		
		JButton rounds = new JButton("Rounds");
		rounds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(false);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(true);
			}
		});
		rounds.setBounds(10, 67, 160, 23);
		navPanel.add(rounds);
		
		JButton reset = new JButton("Reset");
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.frmSerpant.setVisible(true);
				MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(false);
			}
		});
		reset.setBounds(10, 157, 160, 23);
		navPanel.add(reset);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Final permutation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 435, 207);
		frmFinalPermutation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNext = new JButton("Start over");
		btnNext.setBounds(10, 160, 81, 23);
		panel.add(btnNext);
		
		JButton btnNewButton = new JButton("FP");
		btnNewButton.setBounds(196, 85, 54, 23);
		panel.add(btnNewButton);
		
		JLabel lblPlainText = new JLabel("Result befor final permutation");
		lblPlainText.setBounds(31, 34, 143, 14);
		panel.add(lblPlainText);
		
		JLabel lblPlainTextAfter = new JLabel(Rounds.ENCRYPT ? "Cipher text" : "Plain text");
		lblPlainTextAfter.setBounds(260, 34, 54, 14);
		panel.add(lblPlainTextAfter);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(353, 160, 55, 23);
		panel.add(btnBack);
		
		beforFinalPermutationTable = new JTable();
		beforFinalPermutationTable.setBounds(10, 59, 160, 64);
		panel.add(beforFinalPermutationTable);
		beforFinalPermutationTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {


				int row = beforFinalPermutationTable.rowAtPoint(e.getPoint());
				int column = beforFinalPermutationTable.columnAtPoint(e.getPoint());
				
				beforFinalPermutationTable.clearSelection();
				afterFinalPermutationTable.clearSelection();
				if (row > -1) {

					beforFinalPermutationTable.setRowSelectionInterval(row, row);
					beforFinalPermutationTable.setColumnSelectionInterval(column, column);

					afterFinalPermutationTable.setRowSelectionInterval(row, row);
					afterFinalPermutationTable.setColumnSelectionInterval(column, column);

				} 

			
			}
		});
		beforFinalPermutationTable.setColumnSelectionAllowed(true);
		beforFinalPermutationTable.setCellSelectionEnabled(true);
		beforFinalPermutationTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		beforFinalPermutationTable.setModel(new DefaultTableModel(
			SerpentData.intToObject(data.afterXOR[((Rounds.ENCRYPT == true)? 32 : 0)]),
			new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7"
			}
		) {

			private static final long serialVersionUID = -595476558480084675L;
			Class<?>[] columnTypes = new Class[] {
				Character.class, Character.class, Character.class, Character.class, Character.class, Character.class, Character.class, Character.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		beforFinalPermutationTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(0).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(0).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(1).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(1).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(2).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(2).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(3).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(3).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(4).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(4).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(5).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(5).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(6).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(6).setMaxWidth(50);
		beforFinalPermutationTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(7).setMinWidth(20);
		beforFinalPermutationTable.getColumnModel().getColumn(7).setMaxWidth(50);
		beforFinalPermutationTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		beforFinalPermutationTable.setBackground(Color.WHITE);
		
		afterFinalPermutationTable = new JTable();
		afterFinalPermutationTable.setBounds(260, 59, 165, 64);
		panel.add(afterFinalPermutationTable);
		
		afterFinalPermutationTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {


				int row = afterFinalPermutationTable.rowAtPoint(e.getPoint());
				int column = afterFinalPermutationTable.columnAtPoint(e.getPoint());
				
				beforFinalPermutationTable.clearSelection();
				afterFinalPermutationTable.clearSelection();
				if (row > -1) {

					beforFinalPermutationTable.setRowSelectionInterval(row, row);
					beforFinalPermutationTable.setColumnSelectionInterval(column, column);

					afterFinalPermutationTable.setRowSelectionInterval(row, row);
					afterFinalPermutationTable.setColumnSelectionInterval(column, column);

				} 

			
			}
		});
		
		afterFinalPermutationTable.setColumnSelectionAllowed(true);
		afterFinalPermutationTable.setCellSelectionEnabled(true);
		afterFinalPermutationTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		afterFinalPermutationTable.setModel(new DefaultTableModel(
			SerpentData.intToObject(data.afterFinalPermutation),
			new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2597656625522353001L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		afterFinalPermutationTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(0).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(0).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(1).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(1).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(2).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(2).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(3).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(3).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(4).setPreferredWidth(25);
		afterFinalPermutationTable.getColumnModel().getColumn(4).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(4).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(5).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(5).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(6).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(6).setMaxWidth(50);
		afterFinalPermutationTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(7).setMinWidth(20);
		afterFinalPermutationTable.getColumnModel().getColumn(7).setMaxWidth(50);
	
		afterFinalPermutationTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		afterFinalPermutationTable.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmFinalPermutation.setVisible(false);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(true);
				
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FP fp = new FP(1);
				fp.frmInitalPermutationTable.setVisible(true);
			}
		});
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmFinalPermutation.setVisible(false);
				MainFrame.frmSerpant.setVisible(true);
				Rounds.round = 0;
				data.reset();
			}
		});
		
	}
}
