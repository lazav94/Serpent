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
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class InitialPermutation {

	private static SerpentData data = SerpentData.getInstance();

	public JFrame frmInitialPermutation;

	private JTable PlainTextTable;
	private JTable AfterInitalTable;

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
		frmInitialPermutation.setBounds(100, 100, 665, 257);
		frmInitialPermutation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInitialPermutation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Inital permutation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 11, 422, 206);
		frmInitialPermutation.getContentPane().add(panel);
				panel.setLayout(null);
		
				JButton btnNext = new JButton("Next");
				btnNext.setBounds(320, 172, 90, 23);
				panel.add(btnNext);
				
						JButton btnNewButton = new JButton("IP");
						btnNewButton.setBounds(180, 91, 55, 23);
						panel.add(btnNewButton);
						
								JLabel lblPlainText = new JLabel((Rounds.ENCRYPT ? "Plain text" : "Cipher text") + " (big endian)");
								lblPlainText.setBounds(10, 46, 105, 14);
								panel.add(lblPlainText);
								
										JLabel lblPlainTextAfter = new JLabel(
												Rounds.ENCRYPT ? "Plain text after permutation" : "Cipher text after permutation");
										lblPlainTextAfter.setBounds(245, 46, 133, 14);
										panel.add(lblPlainTextAfter);
										
												JButton btnBack = new JButton("Back");
												btnBack.setBounds(232, 172, 78, 23);
												panel.add(btnBack);
												
														PlainTextTable = new JTable();
														PlainTextTable.setBounds(10, 71, 160, 64);
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

																	// table.clearSelection();

																} else {
																	PlainTextTable.setSelectionBackground(Color.GREEN);
																	AfterInitalTable.setSelectionBackground(Color.blue);
																}

															}
														});
														PlainTextTable.setCellSelectionEnabled(true);
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
																PlainTextTable.setBackground(Color.WHITE);
																
																		AfterInitalTable = new JTable();
																		AfterInitalTable.addMouseListener(new MouseAdapter() {
																			@Override
																			public void mouseClicked(MouseEvent e) {
																			}
																		});
																		AfterInitalTable.setBounds(245, 71, 165, 64);
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

																						// table.clearSelection();

																					} else {
																						PlainTextTable.setSelectionBackground(Color.GREEN);
																						AfterInitalTable.setSelectionBackground(Color.blue);
																					}
																			}
																		});
																		AfterInitalTable.setColumnSelectionAllowed(true);
																		AfterInitalTable.setCellSelectionEnabled(true);
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
																		AfterInitalTable.getColumnModel().getColumn(4).setPreferredWidth(25);
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
																		AfterInitalTable.setBackground(Color.WHITE);
																		
																		JPanel navPanel = new JPanel();
																		navPanel.setBorder(new TitledBorder(null, "Navigation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
																		navPanel.setBounds(469, 11, 180, 206);
																		frmInitialPermutation.getContentPane().add(navPanel);
																		navPanel.setLayout(null);
																		
																		JButton firstPermutation = new JButton(Rounds.ENCRYPT? "Inital permutation" : "Final Permutation");
																		firstPermutation.setEnabled(false);
																		firstPermutation.setBounds(10, 22, 160, 23);
																		navPanel.add(firstPermutation);
																		
																		JButton lastPermutation = new JButton(Rounds.ENCRYPT?"Final Permutation" : "Inital permutation" );
																		lastPermutation.addMouseListener(new MouseAdapter() {
																			@Override
																			public void mouseClicked(MouseEvent e) {
																				frmInitialPermutation.setVisible(false);
																				MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(true);
																				
																			}
																		});
																		lastPermutation.setBounds(10, 112, 160, 23);
																		navPanel.add(lastPermutation);
																		
																		JButton round = new JButton("Rounds");
																		round.addMouseListener(new MouseAdapter() {
																			@Override
																			public void mouseClicked(MouseEvent arg0) {
																				frmInitialPermutation.setVisible(false);
																				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(true);
																			}
																		});
																		round.setBounds(10, 67, 160, 23);
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
						frmInitialPermutation.setVisible(false);
						MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(true);
					}
				});
				
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

	}
}
