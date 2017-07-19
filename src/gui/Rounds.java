package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import crypto.SerpentData;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Rounds {

	public static int round = 1;
	private static SerpentData data = SerpentData.getInstance();
	public JFrame frame;
	private JTable prevXorResultTable;
	private JTable subKeyTable;
	private JTable XorResultTable;
	private JTable prevSBoxResultTable;
	private JTable SBoxResultTable;
	private JTable prevLTResultTable;
	private JTable resultLTTable;

	private static final int XOR_RESULT = 1;
	private static final int SUBKEYS = 2;
	private static final int SBOX_RESULT = 3;
	private static final int LT_RESULT = 4;
	private static final int IP_RESULT = 5;
	
	private JTable prevXorFResult;
	private JTable SubKeyTable;
	private JTable ResultTable;

	private void refreshTable(JTable table, int type, int round) {
		Object[][] matrix = null;

		switch (type) {
		case XOR_RESULT:
			matrix = data.intToObject(data.afterXOR[round]);
			break;
		case SUBKEYS:
			matrix = data.intToObject(data.subKeys[round]);
			break;
		case SBOX_RESULT:
			matrix = data.intToObject(data.afterSBOX[round]);
			break;
		case LT_RESULT:
			matrix = data.intToObject(data.afterLT[round]);
			break;
		case IP_RESULT:
			matrix = data.intToObject(data.afterInitalPermutation);
			break;

		default:
			break;
		}

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 8; j++)
				table.getModel().setValueAt(matrix[i][j], i, j);

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
		frame.setBounds(100, 100, 661, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 645, 260);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 5, 625, 255);
		panel.add(tabbedPane);

		JPanel XOR = new JPanel();
		tabbedPane.addTab("XOR", null, XOR, null);
		tabbedPane.setEnabledAt(0, true);
		XOR.setLayout(null);

		JLabel lblXor = new JLabel("XOR");
		lblXor.setBounds(187, 105, 46, 14);
		XOR.add(lblXor);

		JLabel label = new JLabel("=");
		label.setBounds(404, 105, 33, 14);
		XOR.add(label);

		JLabel lblSubkeyround = new JLabel("SubKey (round)");
		lblSubkeyround.setBounds(220, 50, 110, 14);
		XOR.add(lblSubkeyround);

		JLabel lblPrivi = new JLabel("Previously result");
		lblPrivi.setBounds(10, 50, 118, 14);
		XOR.add(lblPrivi);

		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(442, 50, 52, 14);
		XOR.add(lblResult);

		JButton btnSubKey = new JButton("Sub Key");
		btnSubKey.setBounds(242, 158, 89, 58);
		XOR.add(btnSubKey);

		JPanel prevXorResultPanel = new JPanel();
		prevXorResultPanel.setBorder(null);
		prevXorResultPanel.setBounds(10, 75, 172, 76);
		XOR.add(prevXorResultPanel);

		prevXorResultTable = new JTable();
		prevXorResultTable.setModel(new DefaultTableModel(
				((round != 0) ? data.intToObject(data.afterLT[round - 1])
						: data.intToObject(data.afterInitalPermutation)),
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
		prevXorResultTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		prevXorResultTable.setRowSelectionAllowed(false);
		prevXorResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevXorResultTable.setBackground(Color.WHITE);
		prevXorResultPanel.add(prevXorResultTable);

		JPanel subKeyPanel = new JPanel();
		subKeyPanel.setBorder(null);
		subKeyPanel.setBounds(220, 75, 172, 76);
		XOR.add(subKeyPanel);

		subKeyTable = new JTable();
		subKeyTable.setModel(new DefaultTableModel(data.intToObject(data.subKeys[round]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

			private static final long serialVersionUID = 6522758475005847455L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

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
		subKeyTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		subKeyTable.setRowSelectionAllowed(false);
		subKeyTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		subKeyTable.setBackground(Color.WHITE);
		subKeyPanel.add(subKeyTable);

		JPanel XorResultPanel = new JPanel();
		XorResultPanel.setBorder(null);
		XorResultPanel.setBounds(438, 75, 172, 76);
		XOR.add(XorResultPanel);

		XorResultTable = new JTable();
		XorResultTable.setModel(new DefaultTableModel(data.intToObject(data.afterXOR[round]),
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
		XorResultTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		XorResultTable.setRowSelectionAllowed(false);
		XorResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		XorResultTable.setBackground(Color.WHITE);
		XorResultPanel.add(XorResultTable);

		JPanel SBOX = new JPanel();
		tabbedPane.addTab("SBox", null, SBOX, null);
		tabbedPane.setEnabledAt(1, true);
		SBOX.setLayout(null);

		JLabel label_7 = new JLabel("Previously result");
		label_7.setBounds(28, 45, 118, 14);
		SBOX.add(label_7);

		JLabel label_8 = new JLabel("Result");
		label_8.setBounds(417, 45, 46, 14);
		SBOX.add(label_8);

		JButton button_2 = new JButton("Next");
		button_2.setBounds(541, 230, 89, 23);
		SBOX.add(button_2);

		JLabel label_11 = new JLabel("=");
		label_11.setBounds(339, 101, 46, 14);
		SBOX.add(label_11);

		JButton btnSbox = new JButton("SBOX");
		btnSbox.setBounds(228, 70, 89, 76);
		SBOX.add(btnSbox);

		JPanel prevSBoxResultPanel = new JPanel();
		prevSBoxResultPanel.setBorder(null);
		prevSBoxResultPanel.setBounds(28, 70, 172, 76);
		SBOX.add(prevSBoxResultPanel);

		prevSBoxResultTable = new JTable();
		prevSBoxResultTable.setModel(new DefaultTableModel(data.intToObject(data.afterXOR[round]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 294862973519694063L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

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
		prevSBoxResultTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		prevSBoxResultTable.setRowSelectionAllowed(false);
		prevSBoxResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevSBoxResultTable.setBackground(Color.WHITE);
		prevSBoxResultPanel.add(prevSBoxResultTable);

		JPanel SBoxResultPanel = new JPanel();
		SBoxResultPanel.setBorder(null);
		SBoxResultPanel.setBounds(417, 70, 172, 76);
		SBOX.add(SBoxResultPanel);

		SBoxResultTable = new JTable();
		SBoxResultTable.setModel(new DefaultTableModel(data.intToObject(data.afterSBOX[round]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

			private static final long serialVersionUID = -710303563721443981L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

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
		SBoxResultTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		SBoxResultTable.setRowSelectionAllowed(false);
		SBoxResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		SBoxResultTable.setBackground(Color.WHITE);
		SBoxResultPanel.add(SBoxResultTable);

		JButton btnPreviousRound = new JButton("Previous round");
		btnPreviousRound.setBounds(381, 230, 125, 23);
		SBOX.add(btnPreviousRound);

		JPanel LT = new JPanel();
		tabbedPane.addTab("LT", null, LT, null);
		tabbedPane.setEnabledAt(2, true);
		LT.setLayout(null);

		JLabel label_6 = new JLabel("Previously result");
		label_6.setBounds(34, 30, 118, 14);
		LT.add(label_6);

		JLabel label_9 = new JLabel("=");
		label_9.setBounds(375, 91, 46, 14);
		LT.add(label_9);

		JLabel label_10 = new JLabel("Result");
		label_10.setBounds(430, 30, 46, 14);
		LT.add(label_10);

		JButton button_1 = new JButton("Next");
		button_1.setBounds(521, 230, 89, 23);
		LT.add(button_1);

		JLabel lblLt = new JLabel("LT");
		lblLt.setBounds(266, 184, 46, 14);
		LT.add(lblLt);

		JLabel lblPictureOfLt = new JLabel("Picture of LT");
		lblPictureOfLt.setBounds(233, 30, 113, 14);
		LT.add(lblPictureOfLt);

		JPanel prevLTResultPanel = new JPanel();
		prevLTResultPanel.setBorder(null);
		prevLTResultPanel.setBounds(34, 67, 172, 76);
		LT.add(prevLTResultPanel);

		prevLTResultTable = new JTable();
		prevLTResultTable.setModel(new DefaultTableModel(data.intToObject(data.afterSBOX[round]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5253752856371066056L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

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
		prevLTResultTable.setRowSelectionAllowed(false);
		prevLTResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevLTResultTable.setBackground(Color.WHITE);
		prevLTResultPanel.add(prevLTResultTable);

		JPanel resultLTPanel = new JPanel();
		resultLTPanel.setBorder(null);
		resultLTPanel.setBounds(423, 67, 172, 76);
		LT.add(resultLTPanel);

		resultLTTable = new JTable();
		resultLTTable.setModel(new DefaultTableModel(data.intToObject(data.afterLT[round]),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {

			private static final long serialVersionUID = -469619480133487274L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

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
		resultLTTable.setRowSelectionAllowed(false);
		resultLTTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultLTTable.setBackground(Color.WHITE);
		resultLTPanel.add(resultLTTable);
		
		JPanel XORFinal = new JPanel();
		
		XORFinal.setLayout(null);
		tabbedPane.addTab("XOR", null, XORFinal, null);
		tabbedPane.setEnabledAt(3, false);
		
		JLabel label_1 = new JLabel("XOR");
		label_1.setBounds(187, 105, 46, 14);
		XORFinal.add(label_1);
		
		JLabel label_2 = new JLabel("=");
		label_2.setBounds(404, 105, 33, 14);
		XORFinal.add(label_2);
		
		JLabel label_3 = new JLabel("SubKey (round)");
		label_3.setBounds(220, 50, 110, 14);
		XORFinal.add(label_3);
		
		JLabel label_4 = new JLabel("Previously result");
		label_4.setBounds(10, 50, 118, 14);
		XORFinal.add(label_4);
		
		JLabel label_5 = new JLabel("Result");
		label_5.setBounds(442, 50, 52, 14);
		XORFinal.add(label_5);
		
		JButton button = new JButton("Sub Key");
		button.setBounds(242, 158, 89, 58);
		XORFinal.add(button);
		
		JPanel prevXorFTable = new JPanel();
		prevXorFTable.setBorder(null);
		prevXorFTable.setBounds(10, 75, 172, 76);
		XORFinal.add(prevXorFTable);
		
		prevXorFResult = new JTable();
		prevXorFResult.setModel(new DefaultTableModel(
			data.intToObject(data.afterSBOX[31]),
			new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5838131697177452106L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		prevXorFResult.getColumnModel().getColumn(0).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(0).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(0).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(1).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(1).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(1).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(2).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(2).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(2).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(3).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(3).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(3).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(4).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(4).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(4).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(5).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(5).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(5).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(6).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(6).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(6).setMaxWidth(20);
		prevXorFResult.getColumnModel().getColumn(7).setPreferredWidth(20);
		prevXorFResult.getColumnModel().getColumn(7).setMinWidth(20);
		prevXorFResult.getColumnModel().getColumn(7).setMaxWidth(20);
		prevXorFResult.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		prevXorFResult.setRowSelectionAllowed(false);
		prevXorFResult.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevXorFResult.setBackground(Color.WHITE);
		prevXorFTable.add(prevXorFResult);
		
		JPanel SubKeyPanel = new JPanel();
		SubKeyPanel.setBorder(null);
		SubKeyPanel.setBounds(220, 75, 172, 76);
		XORFinal.add(SubKeyPanel);
				
		SubKeyTable = new JTable();
		SubKeyTable.setModel(new DefaultTableModel(
			data.intToObject(data.subKeys[32]),
			new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3586282447720910272L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
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
		SubKeyTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		SubKeyTable.setRowSelectionAllowed(false);
		SubKeyTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		SubKeyTable.setBackground(Color.WHITE);
		SubKeyPanel.add(SubKeyTable);
		
		JPanel ResultPanel = new JPanel();
		ResultPanel.setBorder(null);
		ResultPanel.setBounds(438, 75, 172, 76);
		XORFinal.add(ResultPanel);
		
		ResultTable = new JTable();
		ResultTable.setModel(new DefaultTableModel(
			data.intToObject(data.afterXOR[32]),
			new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1221999896256837407L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
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
		ResultTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		ResultTable.setRowSelectionAllowed(false);
		ResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		ResultTable.setBackground(Color.WHITE);
		ResultPanel.add(ResultTable);

		JButton btnNext = new JButton("Next");
		btnNext.setBounds(521, 308, 89, 23);
		frame.getContentPane().add(btnNext);

		JLabel lblRound = new JLabel("Round: ");
		lblRound.setBounds(36, 271, 46, 14);
		frame.getContentPane().add(lblRound);

		JLabel roundNumber = new JLabel(Integer.toString(round + 1));
		roundNumber.setBounds(106, 271, 46, 14);
		frame.getContentPane().add(roundNumber);
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				round = slider.getValue();
				roundNumber.setText(""+round);
				
				if (round == 32) {
					tabbedPane.setEnabledAt(2, false);
					tabbedPane.setEnabledAt(3, true);
					refreshTable(prevXorResultTable, LT_RESULT, round - 2);
					refreshTable(subKeyTable, SUBKEYS, round-1);
					refreshTable(XorResultTable, XOR_RESULT, round-1);
					refreshTable(prevSBoxResultTable, XOR_RESULT, round-1);
					refreshTable(SBoxResultTable, SBOX_RESULT, round-1);
				} else {
					tabbedPane.setEnabledAt(2, true);
					tabbedPane.setEnabledAt(3, false);
					if(round > 1)
						refreshTable(prevXorResultTable, LT_RESULT, round - 1);
					else
						refreshTable(prevXorResultTable , IP_RESULT, round);
					refreshTable(subKeyTable, SUBKEYS, round);
					refreshTable(XorResultTable, XOR_RESULT, round);
					refreshTable(prevSBoxResultTable, XOR_RESULT, round);
					refreshTable(SBoxResultTable, SBOX_RESULT, round);
					refreshTable(prevLTResultTable, SBOX_RESULT, round);
					refreshTable(resultLTTable, LT_RESULT, round);
				}
			}
		});
		slider.setValue(round);
		slider.setMinimum(1);
		slider.setMaximum(32);
		slider.setBounds(36, 308, 200, 26);
		frame.getContentPane().add(slider);

		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				
				if (round++ >= 32){
					frame.setVisible(false);
					MainFrame.mainFrame.finalPermutationFrame.frame.setVisible(true);
					round = 31;
					return;
				}
				
				slider.setValue(round);
				
			/*		roundNumber.setText(Integer.toString(round + 1));
			if (round == 31) {
					tabbedPane.setEnabledAt(2, false);
					tabbedPane.setEnabledAt(3, true);
					refreshTable(prevXorResultTable, LT_RESULT, round - 1);
					refreshTable(subKeyTable, SUBKEYS, round);
					refreshTable(XorResultTable, XOR_RESULT, round);
					refreshTable(prevSBoxResultTable, XOR_RESULT, round);
					refreshTable(SBoxResultTable, SBOX_RESULT, round);
				} else {
					
					refreshTable(prevXorResultTable, LT_RESULT, round - 1);
					refreshTable(subKeyTable, SUBKEYS, round);
					refreshTable(XorResultTable, XOR_RESULT, round);
					refreshTable(prevSBoxResultTable, XOR_RESULT, round);
					refreshTable(SBoxResultTable, SBOX_RESULT, round);
					refreshTable(prevLTResultTable, SBOX_RESULT, round);
					refreshTable(resultLTTable, LT_RESULT, round);
				}*/

			}
		});

		JButton btnPreviuous = new JButton("Previuous");
		btnPreviuous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(--round <= 0){
					frame.setVisible(false);
					MainFrame.mainFrame.initialPermutationFrame.frame.setVisible(true);
					round = 0;
					return;
				}
				slider.setValue(round);
				/*roundNumber.setText(Integer.toString(round + 1));
				
				
				if (round > 31){
					frame.setVisible(false);
					// TODO
					MainFrame.mainFrame.frmSerpant.setVisible(true);
				}
				else if (round == 31) {
					tabbedPane.setEnabledAt(2, false);
					tabbedPane.setEnabledAt(3, true);
					refreshTable(prevXorResultTable, LT_RESULT, round - 1);
					refreshTable(subKeyTable, SUBKEYS, round);
					refreshTable(XorResultTable, XOR_RESULT, round);
					refreshTable(prevSBoxResultTable, XOR_RESULT, round);
					refreshTable(SBoxResultTable, SBOX_RESULT, round);
				} else {
					tabbedPane.setEnabledAt(2, true);
					tabbedPane.setEnabledAt(3, false);
					if(round > 1)
						refreshTable(prevXorResultTable, LT_RESULT, round - 1);
					else
						refreshTable(prevXorResultTable , IP_RESULT, round);
					refreshTable(subKeyTable, SUBKEYS, round);
					refreshTable(XorResultTable, XOR_RESULT, round);
					refreshTable(prevSBoxResultTable, XOR_RESULT, round);
					refreshTable(SBoxResultTable, SBOX_RESULT, round);
					refreshTable(prevLTResultTable, SBOX_RESULT, round);
					refreshTable(resultLTTable, LT_RESULT, round);
				}*/

				
				
			}
		});

		btnPreviuous.setBounds(394, 308, 99, 23);
		frame.getContentPane().add(btnPreviuous);
		
	
	}
}
