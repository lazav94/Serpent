package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import crypto.SerpentData;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.SystemColor;

public class Rounds {

	public static int round = 1;
	public static boolean ENCRYPT = true;
	public static int box = 3;
	public static boolean lastSubKeyFlag = false;

	private static SerpentData data = SerpentData.getInstance();
	public JFrame frmSerpentRounds;
	private JTable prevXorResultTable;
	private JTable subKeyTable;
	private JTable subKeyTable_1;
	private JTable XorResultTable;
	private JTable prevSBoxResultTable;
	private JTable SBoxResultTable;
	private JTable prevLTResultTable;
	private JTable resultLTTable;

	// public JTable subKeyTable;
	private JTable w2Table;
	private JTable w1Table;
	private JTable w3Table;
	private JTable w4Table;
	private JTable SubKeyAfterIPTable;

	private static final int XOR_RESULT = 1;
	private static final int SUBKEYS = 2;
	private static final int SBOX_RESULT = 3;
	private static final int LT_RESULT = 4;
	private static final int IP_RESULT = 5;
	private static final int KEYS_SCHEDULER = 6;

	private static final int KEYS_SCHEDULER_IP = 7;
	private static final int W1 = 8;
	private static final int W2 = 9;
	private static final int W3 = 10;
	private static final int W4 = 11;

	private JTable prevXorFTable;
	private JTable SubKeyTable;
	private JTable ResultTable;

	private void refreshTable(JTable table, int type, int round) {
		Object[][] matrix = null;

		switch (type) {
		case XOR_RESULT:
			matrix = SerpentData.intToObject(data.afterXOR[round]);
			break;
		case SUBKEYS:
			matrix = SerpentData.intToObject(data.subKeys[round]);
			break;
		case SBOX_RESULT:
			matrix = SerpentData.intToObject(data.afterSBOX[round]);
			break;
		case LT_RESULT:
			matrix = SerpentData.intToObject(data.afterLT[round]);
			break;
		case IP_RESULT:
			matrix = SerpentData.intToObject(data.afterInitalPermutation);
			break;
		case KEYS_SCHEDULER_IP:
			matrix = SerpentData.intToObject(data.subKeysAfterIP[round]);
			break;
		case KEYS_SCHEDULER:
			matrix = SerpentData.intToObject(data.subKeys[round]);
			break;
		case W1:
			matrix = SerpentData.intToObject(new int[] { data.W[(int) (round * 4) + 8] });
			break;
		case W2:
			matrix = SerpentData.intToObject(new int[] { data.W[(int) (round * 4) + 9] });
			break;
		case W3:
			matrix = SerpentData.intToObject(new int[] { data.W[(int) (round * 4) + 10] });
			break;
		case W4:
			matrix = SerpentData.intToObject(new int[] { data.W[(int) (round * 4) + 11] });
			break;

		default:
			break;
		}

		if (type < W1) {
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 8; j++)
					table.getModel().setValueAt(matrix[i][j], i, j);
		} else {
			for (int j = 0; j < 8; j++)
				table.getModel().setValueAt(matrix[0][j], 0, j);

		}

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
		frmSerpentRounds = new JFrame();
		frmSerpentRounds.setTitle("Serpent rounds");
		frmSerpentRounds.setResizable(false);
		frmSerpentRounds.setBounds(100, 100, 919, 380);
		frmSerpentRounds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSerpentRounds.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 718, 278);
		frmSerpentRounds.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 13));
		tabbedPane.setBorder(new TitledBorder(null, "Rounds", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.setBounds(0, 21, 708, 257);
		panel.add(tabbedPane);

		JPanel keyScheduler = new JPanel();
		keyScheduler.setBorder(null);
		tabbedPane.addTab("Key Scheduler", null, keyScheduler, null);
		tabbedPane.setEnabledAt(0, true);
		keyScheduler.setLayout(null);

		JButton btnNewButton = new JButton("IP");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				IP ip = new IP(IP.KEY_SCHEDULER);
				ip.frmInitalPermutationPanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(182, 79, 50, 64);
		keyScheduler.add(btnNewButton);

		JButton btnSbox_1 = new JButton("S-Box: " + ((40 - (round - ((lastSubKeyFlag == true) ? 1 : 0)) - 4) % 8 + 1));
		btnSbox_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnSbox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSbox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SBOX sboxFrame = new SBOX(false);
				sboxFrame.frmSbox.setVisible(true);

				sboxFrame.table.setRowSelectionInterval((40 - (round - ((lastSubKeyFlag == true) ? 1 : 0)) - 4) % 8 + 1,
						(40 - (round + ((lastSubKeyFlag == true) ? 1 : 0)) - 4) % 8 + 1);

				//sboxFrame.table.setSelectionBackground(Color.GREEN);

			}
		});
		btnSbox_1.setBounds(414, 79, 93, 64);
		keyScheduler.add(btnSbox_1);

		w1Table = new JTable();
		w1Table.setBackground(SystemColor.menu);
		w1Table.setFont(new Font("Verdana", Font.PLAIN, 14));
		w1Table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		w1Table.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = w1Table.rowAtPoint(e.getPoint());
				int column = w1Table.columnAtPoint(e.getPoint());
				if (row > -1) {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();
					w1Table.setRowSelectionInterval(0, 0);
					w1Table.setColumnSelectionInterval(column, column);

					SubKeyAfterIPTable.clearSelection();
					SubKeyAfterIPTable.setRowSelectionInterval(0, 0);
					SubKeyAfterIPTable.setColumnSelectionInterval(column, column);

					subKeyTable_1.clearSelection();
					subKeyTable_1.setRowSelectionInterval(row, row);
					subKeyTable_1.setColumnSelectionInterval(column, column);

					// table.clearSelection();

				} else {
					w1Table.clearSelection();
					w1Table.setSelectionBackground(Color.blue);
					SubKeyAfterIPTable.setSelectionBackground(Color.GREEN);
					subKeyTable_1.setSelectionBackground(Color.blue);
				}
			}
		});
		w1Table.setCellSelectionEnabled(true);
		w1Table.setColumnSelectionAllowed(true);
		w1Table.setBounds(11, 33, 160, 16);
		keyScheduler.add(w1Table);
		w1Table.setBorder(new LineBorder(new Color(0, 0, 153)));
		w1Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				W w = new W((round - 1) * 4 + 8);
				w.frmWKeys.setVisible(true);
			}
		});
		w1Table.setModel(
				new DefaultTableModel(SerpentData.intToObject(new int[] { data.W[(int) (Rounds.round / 4) + 8] }),
						new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		w1Table.getColumnModel().getColumn(0).setResizable(false);
		w1Table.getColumnModel().getColumn(0).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(0).setMinWidth(20);
		w1Table.getColumnModel().getColumn(0).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(1).setResizable(false);
		w1Table.getColumnModel().getColumn(1).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(1).setMinWidth(20);
		w1Table.getColumnModel().getColumn(1).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(2).setResizable(false);
		w1Table.getColumnModel().getColumn(2).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(2).setMinWidth(20);
		w1Table.getColumnModel().getColumn(2).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(3).setResizable(false);
		w1Table.getColumnModel().getColumn(3).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(3).setMinWidth(20);
		w1Table.getColumnModel().getColumn(3).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(4).setResizable(false);
		w1Table.getColumnModel().getColumn(4).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(4).setMinWidth(20);
		w1Table.getColumnModel().getColumn(4).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(5).setResizable(false);
		w1Table.getColumnModel().getColumn(5).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(5).setMinWidth(20);
		w1Table.getColumnModel().getColumn(5).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(6).setResizable(false);
		w1Table.getColumnModel().getColumn(6).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(6).setMinWidth(20);
		w1Table.getColumnModel().getColumn(6).setMaxWidth(20);
		w1Table.getColumnModel().getColumn(7).setResizable(false);
		w1Table.getColumnModel().getColumn(7).setPreferredWidth(20);
		w1Table.getColumnModel().getColumn(7).setMinWidth(20);
		w1Table.getColumnModel().getColumn(7).setMaxWidth(20);

		MainFrame.tableAlignCenter(w1Table);
		
		w2Table = new JTable();
		w2Table.setBackground(SystemColor.menu);
		w2Table.setFont(new Font("Verdana", Font.PLAIN, 14));
		w2Table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		w2Table.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = w2Table.rowAtPoint(e.getPoint());
				int column = w2Table.columnAtPoint(e.getPoint());
				if (row > -1) {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();
					w2Table.setRowSelectionInterval(0, 0);
					w2Table.setColumnSelectionInterval(column, column);

					SubKeyAfterIPTable.clearSelection();
					SubKeyAfterIPTable.setRowSelectionInterval(1, 1);
					SubKeyAfterIPTable.setColumnSelectionInterval(column, column);

					subKeyTable_1.clearSelection();
					subKeyTable_1.setRowSelectionInterval(1, 1);
					subKeyTable_1.setColumnSelectionInterval(column, column);

					// table.clearSelection();

				} else {
					w2Table.clearSelection();
					w2Table.setSelectionBackground(Color.blue);
					SubKeyAfterIPTable.setSelectionBackground(Color.GREEN);
					subKeyTable_1.setSelectionBackground(Color.blue);
				}

			}
		});
		w2Table.setCellSelectionEnabled(true);
		w2Table.setColumnSelectionAllowed(true);
		w2Table.setBounds(11, 82, 160, 16);
		keyScheduler.add(w2Table);
		w2Table.setBorder(new LineBorder(new Color(0, 0, 153)));
		w2Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				W w = new W((round - 1) * 4 + 9);
				w.frmWKeys.setVisible(true);
			}
		});
		w2Table.setModel(new DefaultTableModel(

				SerpentData.intToObject(new int[] { data.W[(int) (Rounds.round / 4) + 9] }),

				new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7618022620548498955L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		w2Table.getColumnModel().getColumn(0).setResizable(false);
		w2Table.getColumnModel().getColumn(0).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(0).setMinWidth(20);
		w2Table.getColumnModel().getColumn(0).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(1).setResizable(false);
		w2Table.getColumnModel().getColumn(1).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(1).setMinWidth(20);
		w2Table.getColumnModel().getColumn(1).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(2).setResizable(false);
		w2Table.getColumnModel().getColumn(2).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(2).setMinWidth(20);
		w2Table.getColumnModel().getColumn(2).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(3).setResizable(false);
		w2Table.getColumnModel().getColumn(3).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(3).setMinWidth(20);
		w2Table.getColumnModel().getColumn(3).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(4).setResizable(false);
		w2Table.getColumnModel().getColumn(4).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(4).setMinWidth(20);
		w2Table.getColumnModel().getColumn(4).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(5).setResizable(false);
		w2Table.getColumnModel().getColumn(5).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(5).setMinWidth(20);
		w2Table.getColumnModel().getColumn(5).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(6).setResizable(false);
		w2Table.getColumnModel().getColumn(6).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(6).setMinWidth(20);
		w2Table.getColumnModel().getColumn(6).setMaxWidth(20);
		w2Table.getColumnModel().getColumn(7).setResizable(false);
		w2Table.getColumnModel().getColumn(7).setPreferredWidth(20);
		w2Table.getColumnModel().getColumn(7).setMinWidth(20);
		w2Table.getColumnModel().getColumn(7).setMaxWidth(20);
		
		MainFrame.tableAlignCenter(w2Table);

		w3Table = new JTable();
		w3Table.setBackground(SystemColor.menu);
		w3Table.setFont(new Font("Verdana", Font.PLAIN, 14));
		w3Table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		w3Table.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = w3Table.rowAtPoint(e.getPoint());
				int column = w3Table.columnAtPoint(e.getPoint());
				if (row > -1) {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();

					w3Table.setRowSelectionInterval(0, 0);
					w3Table.setColumnSelectionInterval(column, column);

					SubKeyAfterIPTable.clearSelection();
					SubKeyAfterIPTable.setRowSelectionInterval(2, 2);
					SubKeyAfterIPTable.setColumnSelectionInterval(column, column);

					subKeyTable_1.clearSelection();
					subKeyTable_1.setRowSelectionInterval(2, 2);
					subKeyTable_1.setColumnSelectionInterval(column, column);

					// table.clearSelection();

				} else {
					w3Table.clearSelection();
					w3Table.setSelectionBackground(Color.blue);
					SubKeyAfterIPTable.setSelectionBackground(Color.GREEN);
					subKeyTable_1.setSelectionBackground(Color.blue);
				}

			}
		});
		w3Table.setCellSelectionEnabled(true);
		w3Table.setColumnSelectionAllowed(true);
		w3Table.setBounds(11, 131, 160, 16);
		keyScheduler.add(w3Table);
		w3Table.setBorder(new LineBorder(new Color(0, 0, 153)));
		w3Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				W w = new W((round - 1) * 4 + 10);
				w.frmWKeys.setVisible(true);
			}
		});
		w3Table.setModel(new DefaultTableModel(

				SerpentData.intToObject(new int[] { data.W[(int) (Rounds.round / 4) + 10] }),

				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4081304010228956510L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		w3Table.getColumnModel().getColumn(0).setResizable(false);
		w3Table.getColumnModel().getColumn(0).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(0).setMinWidth(20);
		w3Table.getColumnModel().getColumn(0).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(1).setResizable(false);
		w3Table.getColumnModel().getColumn(1).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(1).setMinWidth(20);
		w3Table.getColumnModel().getColumn(1).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(2).setResizable(false);
		w3Table.getColumnModel().getColumn(2).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(2).setMinWidth(20);
		w3Table.getColumnModel().getColumn(2).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(3).setResizable(false);
		w3Table.getColumnModel().getColumn(3).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(3).setMinWidth(20);
		w3Table.getColumnModel().getColumn(3).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(4).setResizable(false);
		w3Table.getColumnModel().getColumn(4).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(4).setMinWidth(20);
		w3Table.getColumnModel().getColumn(4).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(5).setResizable(false);
		w3Table.getColumnModel().getColumn(5).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(5).setMinWidth(20);
		w3Table.getColumnModel().getColumn(5).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(6).setResizable(false);
		w3Table.getColumnModel().getColumn(6).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(6).setMinWidth(20);
		w3Table.getColumnModel().getColumn(6).setMaxWidth(20);
		w3Table.getColumnModel().getColumn(7).setResizable(false);
		w3Table.getColumnModel().getColumn(7).setPreferredWidth(20);
		w3Table.getColumnModel().getColumn(7).setMinWidth(20);
		w3Table.getColumnModel().getColumn(7).setMaxWidth(20);
		
		MainFrame.tableAlignCenter(w3Table);

		w4Table = new JTable();
		w4Table.setBackground(SystemColor.menu);
		w4Table.setFont(new Font("Verdana", Font.PLAIN, 14));
		w4Table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		w4Table.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = w4Table.rowAtPoint(e.getPoint());
				int column = w4Table.columnAtPoint(e.getPoint());
				if (row > -1) {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();

					w4Table.setRowSelectionInterval(0, 0);
					w4Table.setColumnSelectionInterval(column, column);

					SubKeyAfterIPTable.clearSelection();
					SubKeyAfterIPTable.setRowSelectionInterval(3, 3);
					SubKeyAfterIPTable.setColumnSelectionInterval(column, column);

					subKeyTable_1.clearSelection();
					subKeyTable_1.setRowSelectionInterval(3, 3);
					subKeyTable_1.setColumnSelectionInterval(column, column);

					// table.clearSelection();

				} else {
					w4Table.clearSelection();
					w4Table.setSelectionBackground(Color.blue);
					SubKeyAfterIPTable.setSelectionBackground(Color.GREEN);
					subKeyTable_1.setSelectionBackground(Color.blue);
				}

			}
		});
		w4Table.setCellSelectionEnabled(true);
		w4Table.setColumnSelectionAllowed(true);
		w4Table.setBounds(11, 180, 160, 16);
		keyScheduler.add(w4Table);
		w4Table.setBorder(new LineBorder(new Color(0, 0, 153)));
		w4Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				W w = new W((round - 1) * 4 + 11);
				w.frmWKeys.setVisible(true);
			}
		});
		w4Table.setModel(new DefaultTableModel(

				SerpentData.intToObject(new int[] { data.W[(int) (Rounds.round / 4) + 11] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3951407988042306186L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		w4Table.getColumnModel().getColumn(0).setResizable(false);
		w4Table.getColumnModel().getColumn(0).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(0).setMinWidth(20);
		w4Table.getColumnModel().getColumn(0).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(1).setResizable(false);
		w4Table.getColumnModel().getColumn(1).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(1).setMinWidth(20);
		w4Table.getColumnModel().getColumn(1).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(2).setResizable(false);
		w4Table.getColumnModel().getColumn(2).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(2).setMinWidth(20);
		w4Table.getColumnModel().getColumn(2).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(3).setResizable(false);
		w4Table.getColumnModel().getColumn(3).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(3).setMinWidth(20);
		w4Table.getColumnModel().getColumn(3).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(4).setResizable(false);
		w4Table.getColumnModel().getColumn(4).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(4).setMinWidth(20);
		w4Table.getColumnModel().getColumn(4).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(5).setResizable(false);
		w4Table.getColumnModel().getColumn(5).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(5).setMinWidth(20);
		w4Table.getColumnModel().getColumn(5).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(6).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(6).setMinWidth(20);
		w4Table.getColumnModel().getColumn(6).setMaxWidth(20);
		w4Table.getColumnModel().getColumn(7).setResizable(false);
		w4Table.getColumnModel().getColumn(7).setPreferredWidth(20);
		w4Table.getColumnModel().getColumn(7).setMinWidth(20);
		w4Table.getColumnModel().getColumn(7).setMaxWidth(20);
		
		MainFrame.tableAlignCenter(w4Table);

		subKeyTable_1 = new JTable();
		subKeyTable_1.setBackground(SystemColor.menu);
		subKeyTable_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		subKeyTable_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = subKeyTable_1.rowAtPoint(e.getPoint());
				int column = subKeyTable_1.columnAtPoint(e.getPoint());
				if (row > -1) {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();
					SubKeyAfterIPTable.clearSelection();

					subKeyTable_1.clearSelection();
					switch (row) {
					case 0:
						w1Table.setRowSelectionInterval(0, 0);
						w1Table.setColumnSelectionInterval(column, column);
						break;
					case 1:
						w2Table.setRowSelectionInterval(0, 0);
						w2Table.setColumnSelectionInterval(column, column);
						break;
					case 2:
						w3Table.setRowSelectionInterval(0, 0);
						w3Table.setColumnSelectionInterval(column, column);
						break;
					case 3:
						w4Table.setRowSelectionInterval(0, 0);
						w4Table.setColumnSelectionInterval(column, column);
						break;
					}

					SubKeyAfterIPTable.setRowSelectionInterval(row, row);
					SubKeyAfterIPTable.setColumnSelectionInterval(column, column);

					subKeyTable_1.setRowSelectionInterval(0, 0);
					subKeyTable_1.setColumnSelectionInterval(column, column);

					// table.clearSelection();

				} else {
					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();
					subKeyTable_1.clearSelection();
					SubKeyAfterIPTable.clearSelection();

					w1Table.setSelectionBackground(Color.blue);
					w2Table.setSelectionBackground(Color.blue);
					w3Table.setSelectionBackground(Color.blue);
					w4Table.setSelectionBackground(Color.blue);
					SubKeyAfterIPTable.setSelectionBackground(Color.GREEN);
					subKeyTable_1.setSelectionBackground(Color.blue);
				}

			}
		});
		subKeyTable_1.setColumnSelectionAllowed(true);
		subKeyTable_1.setCellSelectionEnabled(true);
		subKeyTable_1.setBounds(518, 79, 160, 64);
		keyScheduler.add(subKeyTable_1);
		subKeyTable_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		subKeyTable_1.setModel(new DefaultTableModel(SerpentData.intToObject(data.subKeys[Rounds.round - 1]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -310372770275391738L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		subKeyTable_1.getColumnModel().getColumn(0).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(0).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(0).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(0).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(1).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(1).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(1).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(1).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(2).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(2).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(2).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(2).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(3).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(3).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(3).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(3).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(4).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(4).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(4).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(4).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(5).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(5).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(5).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(5).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(6).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(6).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(6).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(6).setMaxWidth(20);
		subKeyTable_1.getColumnModel().getColumn(7).setResizable(false);
		subKeyTable_1.getColumnModel().getColumn(7).setPreferredWidth(20);
		subKeyTable_1.getColumnModel().getColumn(7).setMinWidth(20);
		subKeyTable_1.getColumnModel().getColumn(7).setMaxWidth(20);
		
		MainFrame.tableAlignCenter(subKeyTable_1);

		SubKeyAfterIPTable = new JTable();
		SubKeyAfterIPTable.setBackground(SystemColor.menu);
		SubKeyAfterIPTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		SubKeyAfterIPTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = SubKeyAfterIPTable.rowAtPoint(e.getPoint());
				int column = SubKeyAfterIPTable.columnAtPoint(e.getPoint());
				if (row > -1) {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();
					SubKeyAfterIPTable.clearSelection();

					subKeyTable_1.clearSelection();

					switch (row) {
					case 0:
						w1Table.setRowSelectionInterval(0, 0);
						w1Table.setColumnSelectionInterval(column, column);
						break;
					case 1:
						w2Table.setRowSelectionInterval(0, 0);
						w2Table.setColumnSelectionInterval(column, column);
						break;
					case 2:
						w3Table.setRowSelectionInterval(0, 0);
						w3Table.setColumnSelectionInterval(column, column);
						break;
					case 3:
						w4Table.setRowSelectionInterval(0, 0);
						w4Table.setColumnSelectionInterval(column, column);
						break;
					}

					SubKeyAfterIPTable.setRowSelectionInterval(row, row);
					SubKeyAfterIPTable.setColumnSelectionInterval(column, column);

					subKeyTable_1.setRowSelectionInterval(row, row);
					subKeyTable_1.setColumnSelectionInterval(column, column);

					// table.clearSelection();

				} else {

					w1Table.clearSelection();
					w2Table.clearSelection();
					w3Table.clearSelection();
					w4Table.clearSelection();
					subKeyTable_1.clearSelection();
					SubKeyAfterIPTable.clearSelection();

					SubKeyAfterIPTable.setSelectionBackground(Color.GREEN);
					subKeyTable_1.setSelectionBackground(Color.blue);
				}

			}
		});
		SubKeyAfterIPTable.setColumnSelectionAllowed(true);
		SubKeyAfterIPTable.setCellSelectionEnabled(true);
		SubKeyAfterIPTable.setBounds(243, 79, 160, 64);
		keyScheduler.add(SubKeyAfterIPTable);
		SubKeyAfterIPTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		SubKeyAfterIPTable
				.setModel(new DefaultTableModel(SerpentData.intToObject(data.subKeysAfterIP[Rounds.round - 1]),
						new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = -5611028252976059336L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		SubKeyAfterIPTable.getColumnModel().getColumn(0).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(0).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(0).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(1).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(1).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(1).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(2).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(2).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(2).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(3).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(3).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(3).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(4).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(4).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(4).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(5).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(5).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(5).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(6).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(6).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(6).setMaxWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(7).setResizable(false);
		SubKeyAfterIPTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(7).setMinWidth(20);
		SubKeyAfterIPTable.getColumnModel().getColumn(7).setMaxWidth(20);
		
		MainFrame.tableAlignCenter(SubKeyAfterIPTable);

		JLabel w1 = new JLabel("W[" + (round - 1) * 4 + 8 + ((lastSubKeyFlag == true) ? 4 : 0) + "]");
		w1.setFont(new Font("Verdana", Font.PLAIN, 13));
		w1.setBounds(11, 4, 121, 25);
		keyScheduler.add(w1);

		JLabel w2 = new JLabel("W[" + (round - 1) * 4 + 9 + ((lastSubKeyFlag == true) ? 4 : 0) + "]");
		w2.setFont(new Font("Verdana", Font.PLAIN, 13));
		w2.setBounds(11, 53, 121, 25);
		keyScheduler.add(w2);

		JLabel w3 = new JLabel("W[" + (round - 1) * 4 + 10 + ((lastSubKeyFlag == true) ? 4 : 0) + "]");
		w3.setFont(new Font("Verdana", Font.PLAIN, 13));
		w3.setBounds(11, 102, 121, 25);
		keyScheduler.add(w3);

		JLabel w4 = new JLabel("W[" + (round - 1) * 4 + 11 + ((lastSubKeyFlag == true) ? 4 : 0) + "]");
		w4.setFont(new Font("Verdana", Font.PLAIN, 13));
		w4.setBounds(11, 151, 121, 25);
		keyScheduler.add(w4);

		JLabel lblSubKeyBefore = new JLabel("Sub Key after IP");
		lblSubKeyBefore.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblSubKeyBefore.setBounds(249, 56, 131, 25);
		keyScheduler.add(lblSubKeyBefore);

		JLabel lblSubKey = new JLabel("Sub Key[ " + (round + ((lastSubKeyFlag == true) ? 1 : 0)) + "]");
		lblSubKey.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblSubKey.setBounds(528, 54, 120, 25);
		keyScheduler.add(lblSubKey);

		JPanel XOR = new JPanel();
		XOR.setBorder(null);
		tabbedPane.addTab("XOR", null, XOR, null);
		tabbedPane.setEnabledAt(1, true);
		XOR.setLayout(null);

		JLabel lblXor = new JLabel("XOR");
		lblXor.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblXor.setBounds(217, 89, 46, 25);
		XOR.add(lblXor);

		JLabel label = new JLabel("=");
		label.setFont(new Font("Verdana", Font.PLAIN, 13));
		label.setBounds(473, 89, 33, 25);
		XOR.add(label);

		JLabel lblSubkeyround = new JLabel("Sub key [" + round + "]");
		lblSubkeyround.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblSubkeyround.setBounds(278, 42, 110, 25);
		XOR.add(lblSubkeyround);

		JLabel lblPrivi = new JLabel("Previously result");
		lblPrivi.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPrivi.setBounds(24, 42, 118, 25);
		XOR.add(lblPrivi);

		JLabel lblResult = new JLabel("Result");
		lblResult.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblResult.setBounds(518, 42, 52, 25);
		XOR.add(lblResult);

		prevXorResultTable = new JTable();
		prevXorResultTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		prevXorResultTable.setColumnSelectionAllowed(true);
		prevXorResultTable.setCellSelectionEnabled(true);
		prevXorResultTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = prevXorResultTable.rowAtPoint(e.getPoint());
				int column = prevXorResultTable.columnAtPoint(e.getPoint());

				prevXorResultTable.clearSelection();
				subKeyTable.clearSelection();
				XorResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevXorResultTable.setRowSelectionInterval(row, row);
					prevXorResultTable.setColumnSelectionInterval(column, column);

					subKeyTable.setRowSelectionInterval(row, row);
					subKeyTable.setColumnSelectionInterval(column, column);

					XorResultTable.setRowSelectionInterval(row, row);
					XorResultTable.setColumnSelectionInterval(column, column);

				}

			}
		});
		prevXorResultTable.setBounds(24, 69, 160, 64);
		XOR.add(prevXorResultTable);
		prevXorResultTable.setModel(new DefaultTableModel(
				(ENCRYPT == true)
						? ((round != 0) ? SerpentData.intToObject(data.afterLT[round - 1])
								: SerpentData.intToObject(data.afterInitalPermutation))
						: (SerpentData.intToObject(data.afterSBOX[round - 1])),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

			private static final long serialVersionUID = 8471904586556849201L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		prevXorResultTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(0).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(0).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(1).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(1).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(2).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(2).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(3).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(3).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(4).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(4).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(5).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(5).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(6).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(6).setMaxWidth(50);
		prevXorResultTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		prevXorResultTable.getColumnModel().getColumn(7).setMinWidth(20);
		prevXorResultTable.getColumnModel().getColumn(7).setMaxWidth(50);
		prevXorResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prevXorResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevXorResultTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(prevXorResultTable);

		subKeyTable = new JTable();
		subKeyTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		subKeyTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		subKeyTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = subKeyTable.rowAtPoint(e.getPoint());
				int column = subKeyTable.columnAtPoint(e.getPoint());

				prevXorResultTable.clearSelection();
				subKeyTable.clearSelection();
				XorResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevXorResultTable.setRowSelectionInterval(row, row);
					prevXorResultTable.setColumnSelectionInterval(column, column);

					subKeyTable.setRowSelectionInterval(row, row);
					subKeyTable.setColumnSelectionInterval(column, column);

					XorResultTable.setRowSelectionInterval(row, row);
					XorResultTable.setColumnSelectionInterval(column, column);

				}
			}
		});
		subKeyTable.setColumnSelectionAllowed(true);
		subKeyTable.setCellSelectionEnabled(true);
		subKeyTable.setBounds(278, 69, 160, 64);
		XOR.add(subKeyTable);
		subKeyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		subKeyTable.setModel(
				new DefaultTableModel(SerpentData.intToObject(data.subKeys[(ENCRYPT == true) ? round - 1 : 31]),
						new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

					private static final long serialVersionUID = 6522758475005847455L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		subKeyTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(0).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(0).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(1).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(1).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(2).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(2).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(3).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(3).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(4).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(4).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(5).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(5).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(6).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(6).setMaxWidth(50);
		subKeyTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		subKeyTable.getColumnModel().getColumn(7).setMinWidth(20);
		subKeyTable.getColumnModel().getColumn(7).setMaxWidth(50);
		subKeyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		subKeyTable.setBorder(new LineBorder(new Color(0, 0, 204)));
		subKeyTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(subKeyTable);

		XorResultTable = new JTable();
		XorResultTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		XorResultTable.setToolTipText("  ");
		XorResultTable.setColumnSelectionAllowed(true);
		XorResultTable.setCellSelectionEnabled(true);
		XorResultTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = XorResultTable.rowAtPoint(e.getPoint());
				int column = XorResultTable.columnAtPoint(e.getPoint());

				prevXorResultTable.clearSelection();
				subKeyTable.clearSelection();
				XorResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevXorResultTable.setRowSelectionInterval(row, row);
					prevXorResultTable.setColumnSelectionInterval(column, column);

					subKeyTable.setRowSelectionInterval(row, row);
					subKeyTable.setColumnSelectionInterval(column, column);

					XorResultTable.setRowSelectionInterval(row, row);
					XorResultTable.setColumnSelectionInterval(column, column);
					
			
					String xorResult = "<html><font size='5' face='verdana, times, serif' > ";
					xorResult += "&nbsp;&nbsp;" + String.format("%4s", Integer.toBinaryString(Integer.parseInt(prevXorResultTable.getValueAt(row, column).toString(),16))).replace(' ', '0') + "<br>+";
					xorResult += String.format("%4s", Integer.toBinaryString(Integer.parseInt(subKeyTable.getValueAt(row, column).toString(),16))).replace(' ', '0') + "<br>";
					xorResult += "====<br>";
					xorResult += "&nbsp;&nbsp;" + String.format("%4s", Integer.toBinaryString(Integer.parseInt(XorResultTable.getValueAt(row, column).toString(),16))).replace(' ', '0') + "<br>";
					xorResult += "</font></html>";
					XorResultTable.setToolTipText(xorResult);
		

				}

			}
		});
		
		// Tooltip time = 10s
		ToolTipManager.sharedInstance().setDismissDelay(10000);
		
		XorResultTable.setBounds(519, 69, 160, 64);
		XOR.add(XorResultTable);
		XorResultTable.setModel(new DefaultTableModel(SerpentData.intToObject(data.afterXOR[round]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

			private static final long serialVersionUID = 294862973519694063L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		XorResultTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(0).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(0).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(1).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(1).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(2).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(2).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(3).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(3).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(4).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(4).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(5).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(5).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(6).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(6).setMaxWidth(50);
		XorResultTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		XorResultTable.getColumnModel().getColumn(7).setMinWidth(20);
		XorResultTable.getColumnModel().getColumn(7).setMaxWidth(50);
		XorResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		XorResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		XorResultTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(XorResultTable);

		JPanel SBOX = new JPanel();
		SBOX.setBorder(null);

		tabbedPane.addTab("SBox", null, SBOX, null);
		tabbedPane.setEnabledAt(2, true);
		SBOX.setLayout(null);

		JLabel label_7 = new JLabel("Previously result");
		label_7.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_7.setBounds(62, 46, 118, 25);
		SBOX.add(label_7);

		JLabel label_8 = new JLabel("Result");
		label_8.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_8.setBounds(481, 46, 46, 25);
		SBOX.add(label_8);

		JButton button_2 = new JButton("Next");
		button_2.setBounds(541, 230, 89, 23);
		SBOX.add(button_2);

		JButton btnSbox = new JButton((ENCRYPT == true) ? "S-Box: " : "inv S-Box: " + round % 8);
		btnSbox.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnSbox.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SBOX sboxFrame = new SBOX(true);
				sboxFrame.frmSbox.setVisible(true);

				sboxFrame.table.setRowSelectionInterval((round - 1) % 8 + 1, (round - 1) % 8 + 1);
			//	sboxFrame.table.setSelectionBackground(Color.GREEN);

			}
		});
		btnSbox.setBounds(284, 65, 135, 72);
		SBOX.add(btnSbox);

		prevSBoxResultTable = new JTable();
		prevSBoxResultTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		prevSBoxResultTable.setColumnSelectionAllowed(true);
		prevSBoxResultTable.setCellSelectionEnabled(true);
		prevSBoxResultTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = prevSBoxResultTable.rowAtPoint(e.getPoint());
				int column = prevSBoxResultTable.columnAtPoint(e.getPoint());

				prevSBoxResultTable.clearSelection();
				SBoxResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevSBoxResultTable.setRowSelectionInterval(row, row);
					prevSBoxResultTable.setColumnSelectionInterval(column, column);

					SBoxResultTable.setRowSelectionInterval(row, row);
					SBoxResultTable.setColumnSelectionInterval(column, column);

				}

			}
		});
		prevSBoxResultTable.setBounds(62, 69, 160, 64);
		SBOX.add(prevSBoxResultTable);
		prevSBoxResultTable
				.setModel(
						new DefaultTableModel(
								(ENCRYPT == true) ? SerpentData.intToObject(data.afterXOR[round])
										: SerpentData
												.intToObject((round == 32) ? data.afterXOR[32] : data.afterLT[round]),
								new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
							/**
							 * 
							 */
							private static final long serialVersionUID = 294862973519694063L;
							boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
									false };

							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
		prevSBoxResultTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(0).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(0).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(1).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(1).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(2).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(2).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(3).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(3).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(4).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(4).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(5).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(5).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(6).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(6).setMaxWidth(50);
		prevSBoxResultTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(7).setMinWidth(20);
		prevSBoxResultTable.getColumnModel().getColumn(7).setMaxWidth(50);
		prevSBoxResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prevSBoxResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevSBoxResultTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(prevSBoxResultTable);

		SBoxResultTable = new JTable();
		SBoxResultTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		SBoxResultTable.setColumnSelectionAllowed(true);
		SBoxResultTable.setCellSelectionEnabled(true);
		SBoxResultTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = SBoxResultTable.rowAtPoint(e.getPoint());
				int column = SBoxResultTable.columnAtPoint(e.getPoint());

				prevSBoxResultTable.clearSelection();
				SBoxResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevSBoxResultTable.setRowSelectionInterval(row, row);
					prevSBoxResultTable.setColumnSelectionInterval(column, column);

					SBoxResultTable.setRowSelectionInterval(row, row);
					SBoxResultTable.setColumnSelectionInterval(column, column);

				}

			}
		});
		SBoxResultTable.setBounds(481, 69, 160, 64);
		SBOX.add(SBoxResultTable);
		SBoxResultTable.setModel(
				new DefaultTableModel(SerpentData.intToObject(data.afterSBOX[ENCRYPT ? round - 1 : round - 2]),
						new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

					private static final long serialVersionUID = -710303563721443981L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		SBoxResultTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(0).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(0).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(1).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(1).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(2).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(2).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(3).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(3).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(4).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(4).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(5).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(5).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(6).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(6).setMaxWidth(50);
		SBoxResultTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		SBoxResultTable.getColumnModel().getColumn(7).setMinWidth(20);
		SBoxResultTable.getColumnModel().getColumn(7).setMaxWidth(50);
		SBoxResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SBoxResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		SBoxResultTable.setBackground(SystemColor.menu);

		MainFrame.tableAlignCenter(SBoxResultTable);
		
		JButton btnPreviousRound = new JButton("Previous round");
		btnPreviousRound.setBounds(381, 230, 125, 23);
		SBOX.add(btnPreviousRound);

		JPanel LT = new JPanel();
		LT.setBorder(null);
		tabbedPane.addTab("Linear transforamtion", null, LT, null);
		tabbedPane.setEnabledAt(3, true);
		LT.setLayout(null);

		JLabel label_6 = new JLabel("Previously result");
		label_6.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_6.setBounds(45, 58, 118, 25);
		LT.add(label_6);

		JLabel label_10 = new JLabel("Result");
		label_10.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_10.setBounds(483, 58, 46, 25);
		LT.add(label_10);

		prevLTResultTable = new JTable();
		prevLTResultTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		prevLTResultTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = prevLTResultTable.rowAtPoint(e.getPoint());
				int column = prevLTResultTable.columnAtPoint(e.getPoint());

				prevLTResultTable.clearSelection();
				resultLTTable.clearSelection();
				if (row > -1 && row < 4) {

					prevLTResultTable.setRowSelectionInterval(row, row);
					prevLTResultTable.setColumnSelectionInterval(column, column);

					resultLTTable.setRowSelectionInterval(row, row);
					resultLTTable.setColumnSelectionInterval(column, column);

				}
			}
		});
		prevLTResultTable.setCellSelectionEnabled(true);
		prevLTResultTable.setColumnSelectionAllowed(true);
		prevLTResultTable.setBounds(45, 81, 160, 64);
		LT.add(prevLTResultTable);
		prevLTResultTable
				.setModel(new DefaultTableModel(
						(ENCRYPT == true) ? SerpentData.intToObject(data.afterSBOX[round])
								: SerpentData.intToObject(
										(round == 32) ? data.afterInitalPermutation : data.afterXOR[round + 1]),
						new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = -5253752856371066056L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		prevLTResultTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(0).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(0).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(1).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(1).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(2).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(2).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(3).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(3).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(4).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(4).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(5).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(5).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(6).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(6).setMaxWidth(50);
		prevLTResultTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		prevLTResultTable.getColumnModel().getColumn(7).setMinWidth(20);
		prevLTResultTable.getColumnModel().getColumn(7).setMaxWidth(50);
		prevLTResultTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		prevLTResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevLTResultTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(prevLTResultTable);

		resultLTTable = new JTable();
		resultLTTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		resultLTTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = resultLTTable.rowAtPoint(e.getPoint());
				int column = resultLTTable.columnAtPoint(e.getPoint());

				prevLTResultTable.clearSelection();
				resultLTTable.clearSelection();
				if (row > -1 && row < 4) {

					prevLTResultTable.setRowSelectionInterval(row, row);
					prevLTResultTable.setColumnSelectionInterval(column, column);

					resultLTTable.setRowSelectionInterval(row, row);
					resultLTTable.setColumnSelectionInterval(column, column);

				}
			}
			
		});
		resultLTTable.setCellSelectionEnabled(true);
		resultLTTable.setColumnSelectionAllowed(true);
		resultLTTable.setBounds(483, 81, 160, 64);
		LT.add(resultLTTable);
		resultLTTable
				.setModel(new DefaultTableModel(SerpentData.intToObject(data.afterLT[ENCRYPT ? round - 1 : round - 1]),
						new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

					private static final long serialVersionUID = -469619480133487274L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		resultLTTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(0).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(0).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(1).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(1).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(2).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(2).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(3).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(3).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(4).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(4).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(5).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(5).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(6).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(6).setMaxWidth(50);
		resultLTTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		resultLTTable.getColumnModel().getColumn(7).setMinWidth(20);
		resultLTTable.getColumnModel().getColumn(7).setMaxWidth(50);
		resultLTTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		resultLTTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultLTTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(resultLTTable);

		JButton btnLinearTransformation = new JButton("Linear Transformation");
		btnLinearTransformation.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnLinearTransformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				LT lt = new LT();
				lt.frmLinearTransformation.setVisible(true);
			}
		});
		btnLinearTransformation.setHorizontalAlignment(SwingConstants.LEADING);
		btnLinearTransformation.setBounds(250, 81, 188, 70);
		LT.add(btnLinearTransformation);

		JPanel XORFinal = new JPanel();
		XORFinal.setBackground(SystemColor.menu);
		XORFinal.setBorder(null);

		XORFinal.setLayout(null);
		tabbedPane.addTab("XOR final", null, XORFinal, null);
		tabbedPane.setEnabledAt(4, false);

		JLabel label_1 = new JLabel("XOR");
		label_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_1.setBackground(SystemColor.menu);
		label_1.setBounds(216, 106, 46, 25);
		XORFinal.add(label_1);

		JLabel label_2 = new JLabel("=");
		label_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_2.setBackground(SystemColor.menu);
		label_2.setBounds(461, 106, 33, 25);
		XORFinal.add(label_2);

		JLabel label_3 = new JLabel("SubKey[33]");
		label_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_3.setBackground(SystemColor.menu);
		label_3.setBounds(272, 50, 110, 25);
		XORFinal.add(label_3);

		JLabel label_4 = new JLabel("Previously result");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_4.setBackground(SystemColor.menu);
		label_4.setBounds(23, 50, 118, 25);
		XORFinal.add(label_4);

		JLabel label_5 = new JLabel("Result");
		label_5.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_5.setBackground(SystemColor.menu);
		label_5.setBounds(509, 50, 52, 25);
		XORFinal.add(label_5);

		prevXorFTable = new JTable();
		prevXorFTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		prevXorFTable.setColumnSelectionAllowed(true);
		prevXorFTable.setCellSelectionEnabled(true);
		prevXorFTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {


				int row = prevXorFTable.rowAtPoint(e.getPoint());
				int column = prevXorFTable.columnAtPoint(e.getPoint());

				prevXorFTable.clearSelection();
				SubKeyTable.clearSelection();
				ResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevXorFTable.setRowSelectionInterval(row, row);
					prevXorFTable.setColumnSelectionInterval(column, column);

					SubKeyTable.setRowSelectionInterval(row, row);
					SubKeyTable.setColumnSelectionInterval(column, column);

					ResultTable.setRowSelectionInterval(row, row);
					ResultTable.setColumnSelectionInterval(column, column);

				}

			
			}
		});
		prevXorFTable.setBounds(22, 81, 160, 64);
		XORFinal.add(prevXorFTable);
		prevXorFTable.setModel(new DefaultTableModel(SerpentData.intToObject(data.afterSBOX[31]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5838131697177452106L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		prevXorFTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(0).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(0).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(1).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(1).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(2).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(2).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(3).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(3).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(4).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(4).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(5).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(5).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(6).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(6).setMaxWidth(20);
		prevXorFTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		prevXorFTable.getColumnModel().getColumn(7).setMinWidth(20);
		prevXorFTable.getColumnModel().getColumn(7).setMaxWidth(20);
		prevXorFTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prevXorFTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevXorFTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(prevXorFTable);

		SubKeyTable = new JTable();
		SubKeyTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		SubKeyTable.setColumnSelectionAllowed(true);
		SubKeyTable.setCellSelectionEnabled(true);
		SubKeyTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = SubKeyTable.rowAtPoint(e.getPoint());
				int column = SubKeyTable.columnAtPoint(e.getPoint());

				prevXorFTable.clearSelection();
				SubKeyTable.clearSelection();
				ResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevXorFTable.setRowSelectionInterval(row, row);
					prevXorFTable.setColumnSelectionInterval(column, column);

					SubKeyTable.setRowSelectionInterval(row, row);
					SubKeyTable.setColumnSelectionInterval(column, column);

					ResultTable.setRowSelectionInterval(row, row);
					ResultTable.setColumnSelectionInterval(column, column);

				}
			}
		});
		SubKeyTable.setBounds(272, 81, 160, 64);
		XORFinal.add(SubKeyTable);
		SubKeyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				lastSubKeyFlag = true;
				refreshTable(w1Table, W1, 32);
				refreshTable(w2Table, W2, 32);
				refreshTable(w3Table, W3, 32);
				refreshTable(w4Table, W4, 32);

				lblSubKey.setText("Sub Key[ " + (round + ((lastSubKeyFlag == true) ? 1 : 0)) + "]");

				btnSbox_1.setText("S-Box: " + ((40 - (round - ((lastSubKeyFlag == true) ? 1 : 0)) - 4) % 8 + 1));
				
				w1.setText("W[" + ((round - 1) * 4 + 8 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");
				w2.setText("W[" + ((round - 1) * 4 + 9 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");
				w3.setText("W[" + ((round - 1) * 4 + 10 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");
				w4.setText("W[" + ((round - 1) * 4 + 11 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");

				refreshTable(SubKeyAfterIPTable, KEYS_SCHEDULER_IP, 32);
				refreshTable(subKeyTable_1, KEYS_SCHEDULER, 32);
				tabbedPane.setSelectedIndex(0);

			}

		});
		SubKeyTable.setModel(new DefaultTableModel(SerpentData.intToObject(data.subKeys[32]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3586282447720910272L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		SubKeyTable.getColumnModel().getColumn(0).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(0).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(0).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(1).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(1).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(1).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(2).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(2).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(2).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(3).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(3).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(3).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(4).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(4).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(4).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(5).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(5).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(5).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(6).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(6).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(6).setMaxWidth(20);
		SubKeyTable.getColumnModel().getColumn(7).setResizable(false);
		SubKeyTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		SubKeyTable.getColumnModel().getColumn(7).setMinWidth(20);
		SubKeyTable.getColumnModel().getColumn(7).setMaxWidth(20);
		SubKeyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SubKeyTable.setBorder(new LineBorder(new Color(0, 0, 204)));
		SubKeyTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(SubKeyTable);

		ResultTable = new JTable();
		ResultTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		ResultTable.setColumnSelectionAllowed(true);
		ResultTable.setCellSelectionEnabled(true);
		ResultTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = ResultTable.rowAtPoint(e.getPoint());
				int column = ResultTable.columnAtPoint(e.getPoint());

				prevXorFTable.clearSelection();
				SubKeyTable.clearSelection();
				ResultTable.clearSelection();
				if (row > -1 && row < 4) {

					prevXorFTable.setRowSelectionInterval(row, row);
					prevXorFTable.setColumnSelectionInterval(column, column);

					SubKeyTable.setRowSelectionInterval(row, row);
					SubKeyTable.setColumnSelectionInterval(column, column);

					ResultTable.setRowSelectionInterval(row, row);
					ResultTable.setColumnSelectionInterval(column, column);
					
					String xorResult = "<html><font size='5' face='verdana, times, serif' > ";
					xorResult += "&nbsp;&nbsp;" + String.format("%4s", Integer.toBinaryString(Integer.parseInt(prevXorFTable.getValueAt(row, column).toString(),16))).replace(' ', '0') + "<br>+";
					xorResult += String.format("%4s", Integer.toBinaryString(Integer.parseInt(SubKeyTable.getValueAt(row, column).toString(),16))).replace(' ', '0') + "<br>";
					xorResult += "====<br>";
					xorResult += "&nbsp;&nbsp;" + String.format("%4s", Integer.toBinaryString(Integer.parseInt(ResultTable.getValueAt(row, column).toString(),16))).replace(' ', '0') + "<br>";
					xorResult += "</font></html>";
					ResultTable.setToolTipText(xorResult);

				}
			}
		});
		ResultTable.setBounds(509, 81, 160, 64);
		XORFinal.add(ResultTable);
		ResultTable.setModel(new DefaultTableModel(SerpentData.intToObject(data.afterXOR[32]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1221999896256837407L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ResultTable.getColumnModel().getColumn(0).setResizable(false);
		ResultTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(0).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(0).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(1).setResizable(false);
		ResultTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(1).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(1).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(2).setResizable(false);
		ResultTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(2).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(2).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(3).setResizable(false);
		ResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(3).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(3).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(4).setResizable(false);
		ResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(4).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(4).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(5).setResizable(false);
		ResultTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(5).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(5).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(6).setResizable(false);
		ResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(6).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(6).setMaxWidth(20);
		ResultTable.getColumnModel().getColumn(7).setResizable(false);
		ResultTable.getColumnModel().getColumn(7).setPreferredWidth(20);
		ResultTable.getColumnModel().getColumn(7).setMinWidth(20);
		ResultTable.getColumnModel().getColumn(7).setMaxWidth(20);
		ResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		ResultTable.setBackground(SystemColor.menu);
		
		MainFrame.tableAlignCenter(ResultTable);

		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnNext.setBounds(629, 303, 89, 25);
		frmSerpentRounds.getContentPane().add(btnNext);

		JLabel lblRound = new JLabel("Round: ");
		lblRound.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblRound.setBounds(16, 303, 67, 25);
		frmSerpentRounds.getContentPane().add(lblRound);

		JLabel roundNumber = new JLabel(Integer.toString(round + 1));
		roundNumber.setFont(new Font("Verdana", Font.PLAIN, 13));
		roundNumber.setBounds(93, 303, 46, 25);
		frmSerpentRounds.getContentPane().add(roundNumber);

		if (ENCRYPT == true) {
			tabbedPane.addTab("XOR", null, XOR, null);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.addTab("SBox", null, SBOX, null);
			tabbedPane.setEnabledAt(2, true);
			tabbedPane.addTab("LT", null, LT, null);
			tabbedPane.setEnabledAt(3, true);
			tabbedPane.addTab("XOR final", null, XORFinal, null);
			tabbedPane.setEnabledAt(4, false);
		} else {
			tabbedPane.addTab("XOR final", null, XORFinal, null);
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.addTab("SBox", null, SBOX, null);
			tabbedPane.setEnabledAt(2, true);
			tabbedPane.addTab("XOR", null, XOR, null);
			tabbedPane.setEnabledAt(3, true);
			tabbedPane.addTab("LT", null, LT, null);
			tabbedPane.setEnabledAt(4, true);

		}

		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				round = slider.getValue();
				
				btnSbox.setText(((ENCRYPT == true) ? "S-Box: " : "inv S-Box: ") + ((round - 1) % 8 + 1));
				lblSubkeyround.setText("Sub Key[" + round + "]");
				btnSbox_1.setText("S-Box: " + ((40 - (round - ((lastSubKeyFlag == true) ? 1 : 0)) - 4) % 8 + 1));
				lblSubKey.setText("Sub Key[ " + (round + ((lastSubKeyFlag == true) ? 1 : 0)) + "]");
				roundNumber.setText("" + round);

				w1.setText("W[" + ((round - 1) * 4 + 8 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");
				w2.setText("W[" + ((round - 1) * 4 + 9 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");
				w3.setText("W[" + ((round - 1) * 4 + 10 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");
				w4.setText("W[" + ((round - 1) * 4 + 11 + ((lastSubKeyFlag == true) ? 4 : 0)) + "]");

				refreshTable(subKeyTable_1, KEYS_SCHEDULER, round - 1);
				refreshTable(SubKeyAfterIPTable, KEYS_SCHEDULER_IP, round - 1);
				refreshTable(w1Table, W1, round - 1);
				refreshTable(w2Table, W2, round - 1);
				refreshTable(w3Table, W3, round - 1);
				refreshTable(w4Table, W4, round - 1);

				lastSubKeyFlag = false;

				@SuppressWarnings("unused")
				IP ip = new IP(2);

				if (ENCRYPT) {
					if (round == 32) {
						btnLinearTransformation.setEnabled(false);
						tabbedPane.setEnabledAt(3, false);
						tabbedPane.setEnabledAt(4, true);
						refreshTable(prevXorResultTable, LT_RESULT, round - 2);
						refreshTable(subKeyTable, SUBKEYS, round - 1);
						refreshTable(XorResultTable, XOR_RESULT, round - 1);
						refreshTable(prevSBoxResultTable, XOR_RESULT, round - 1);
						refreshTable(SBoxResultTable, SBOX_RESULT, round - 1);
					} else {
						btnLinearTransformation.setEnabled(true);
						tabbedPane.setEnabledAt(3, true);
						tabbedPane.setEnabledAt(4, false);
						if (round > 1)
							refreshTable(prevXorResultTable, LT_RESULT, round - 2);
						else
							refreshTable(prevXorResultTable, IP_RESULT, 0);
						refreshTable(subKeyTable, SUBKEYS, round - 1);
						refreshTable(XorResultTable, XOR_RESULT, round - 1);
						refreshTable(prevSBoxResultTable, XOR_RESULT, round - 1);
						refreshTable(SBoxResultTable, SBOX_RESULT, round - 1);
						refreshTable(prevLTResultTable, SBOX_RESULT, round - 1);
						refreshTable(resultLTTable, LT_RESULT, round - 1);
					}
				} else {
					refreshTable(subKeyTable, SUBKEYS, round);

					if (round == 32) {
						btnLinearTransformation.setEnabled(false);
						tabbedPane.setEnabledAt(4, false);
						tabbedPane.setEnabledAt(1, true);

						refreshTable(prevXorFTable, IP_RESULT, round);
						refreshTable(subKeyTable, SUBKEYS, round - 1);

						refreshTable(prevSBoxResultTable, XOR_RESULT, round);
						refreshTable(SBoxResultTable, SBOX_RESULT, round - 1);

						refreshTable(prevXorResultTable, SBOX_RESULT, round - 1);
						refreshTable(XorResultTable, XOR_RESULT, round - 1);

					}
					if (round < 32) {
						btnLinearTransformation.setEnabled(true);
						tabbedPane.setEnabledAt(4, true);
						tabbedPane.setEnabledAt(1, false);

						refreshTable(prevLTResultTable, XOR_RESULT, round);
						refreshTable(resultLTTable, LT_RESULT, round - 1);

						refreshTable(subKeyTable, SUBKEYS, round - 1);

						refreshTable(prevSBoxResultTable, LT_RESULT, round - 1);
						refreshTable(SBoxResultTable, SBOX_RESULT, round - 1);

						refreshTable(prevXorResultTable, SBOX_RESULT, round - 1);
						refreshTable(XorResultTable, XOR_RESULT, round - 1);
					}

				}

			}
		});
		slider.setValue(round);
		slider.setMinimum(1);
		slider.setMaximum(32);
		slider.setBounds(149, 303, 200, 25);
		frmSerpentRounds.getContentPane().add(slider);

		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (ENCRYPT) {

					if (round++ >= 32) {
						frmSerpentRounds.setVisible(false);
						MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(true);
						round = 31;
						return;
					}

				} else {
					if (--round <= 0) {

						frmSerpentRounds.setVisible(false);
						MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(true);
						round = 0;
						return;
					}
				}
				slider.setValue(round);

			}
		});

		JButton btnPreviuous = new JButton("Previous");
		btnPreviuous.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnPreviuous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ENCRYPT) {
					if (--round <= 0) {
						frmSerpentRounds.setVisible(false);
						MainFrame.mainFrame.initialPermutationFrame.frmInitialPermutation.setVisible(true);
						round = 0;
						return;
					}
				} else {
					if (round++ >= 32) {
						frmSerpentRounds.setVisible(false);
						MainFrame.mainFrame.initialPermutationFrame.frmInitialPermutation.setVisible(true);
						round = 31;
						return;
					}
				}
				slider.setValue(round);

			}
		});

		btnPreviuous.setBounds(520, 303, 99, 25);
		frmSerpentRounds.getContentPane().add(btnPreviuous);
		
		JPanel navPanel = new JPanel();
		navPanel.setBorder(new TitledBorder(null, "Navigation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		navPanel.setLayout(null);
		navPanel.setBounds(728, 40, 180, 224);
		frmSerpentRounds.getContentPane().add(navPanel);
		
		JButton firstPermutation = new JButton("Inital permutation");
		firstPermutation.setFont(new Font("Verdana", Font.PLAIN, 13));
		firstPermutation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MainFrame.mainFrame.initialPermutationFrame.frmInitialPermutation.setVisible(true);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(false);
	
			}
		});
		firstPermutation.setBounds(10, 25, 160, 25);
		navPanel.add(firstPermutation);
		
		JButton lastPermutation = new JButton("Final Permutation");
		lastPermutation.setFont(new Font("Verdana", Font.PLAIN, 13));
		lastPermutation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MainFrame.mainFrame.finalPermutationFrame.frmFinalPermutation.setVisible(true);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(false);
			}
		});
		lastPermutation.setBounds(10, 121, 160, 25);
		navPanel.add(lastPermutation);
		
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Verdana", Font.PLAIN, 13));
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.frmSerpant.setVisible(true);
				MainFrame.mainFrame.roundFrame.frmSerpentRounds.setVisible(false);
			}
		});
		reset.setBounds(10, 169, 160, 25);
		navPanel.add(reset);
		
		JButton button_4 = new JButton("Rounds");
		button_4.setFont(new Font("Verdana", Font.PLAIN, 13));
		button_4.setEnabled(false);
		button_4.setBounds(10, 73, 160, 25);
		navPanel.add(button_4);

	}
}
