package gui;

import java.awt.Cursor;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import crypto.Serpent;
import crypto.SerpentData;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class IP {

	private SerpentData data = SerpentData.getInstance();
	public JFrame frmInitalPermutationPanel;
	public JTable table;
	private JTable AfterIPTable;
	private JTable plainTextTable;
	private JTable PlainTextTable;
	private JTable AfterInitalTable;

	public static final int INITAL_PERMUTATION = 1;
	public static final int LINEAR_TRANSFORMATION = 2;
	public static final int KEY_SCHEDULER = 4;

	public int[][] ipMatrix = {
			{ 0, 32, 64, 96, 1, 33, 65, 97, 2, 34, 66, 98, 3, 35, 67, 99, 4, 36, 68, 100, 5, 37, 69, 101, 6, 38, 70,
					102, 7, 39, 71, 103 },
			{ 8, 40, 72, 104, 9, 41, 73, 105, 10, 42, 74, 106, 11, 43, 75, 107, 12, 44, 76, 108, 13, 45, 77, 109, 14,
					46, 78, 110, 15, 47, 79, 111 },
			{ 16, 48, 80, 112, 17, 49, 81, 113, 18, 50, 82, 114, 19, 51, 83, 115, 20, 52, 84, 116, 21, 53, 85, 117, 22,
					54, 86, 118, 23, 55, 87, 119 },
			{ 24, 56, 88, 120, 25, 57, 89, 121, 26, 58, 90, 122, 27, 59, 91, 123, 28, 60, 92, 124, 29, 61, 93, 125, 30,
					62, 94, 126, 31, 63, 95, 127 } };
	public int[][] fpMatrix = {
			{ 0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100, 104,
					108, 112, 116, 120, 124 },
			{ 1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61, 65, 69, 73, 77, 81, 85, 89, 93, 97, 101, 105,
					109, 113, 117, 121, 125 },
			{ 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 66, 70, 74, 78, 82, 86, 90, 94, 98, 102,
					106, 110, 114, 118, 122, 126 },
			{ 3, 7, 11, 15, 19, 23, 27, 31, 35, 39, 43, 47, 51, 55, 59, 63, 67, 71, 75, 79, 83, 87, 91, 95, 99, 103,
					107, 111, 115, 119, 123, 127 } };

	/**
	 * Create the application.
	 */
	public IP(int type) {
		initialize(type);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int type) {
		frmInitalPermutationPanel = new JFrame();
		frmInitalPermutationPanel.getContentPane().setBackground(SystemColor.activeCaptionBorder);
		frmInitalPermutationPanel.setResizable(false);
		frmInitalPermutationPanel.setTitle("Inital permutation table");
		frmInitalPermutationPanel.setType(Type.POPUP);
		frmInitalPermutationPanel.setBounds(100, 100, 809, 439);
		frmInitalPermutationPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInitalPermutationPanel.getContentPane().setLayout(null);

		PlainTextTable = new JTable();
		PlainTextTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = PlainTextTable.rowAtPoint(e.getPoint());
				int column = PlainTextTable.columnAtPoint(e.getPoint());

				PlainTextTable.clearSelection();
				plainTextTable.clearSelection();
				if (row > -1) {

					PlainTextTable.setRowSelectionInterval(row, row);
					PlainTextTable.setColumnSelectionInterval(column, column);

					plainTextTable.setRowSelectionInterval(row, row);
					plainTextTable.setColumnSelectionInterval(column * 4, column * 4 + 3);

					table.setRowSelectionInterval(row, row);
					table.setColumnSelectionInterval((31 - (column * 4 + 3)) % 32, (31 - 4 * column) % 32);

					AfterIPTable.setRowSelectionInterval(fpMatrix[row][31- column] / 32,fpMatrix[row][31-column] / 32);
					AfterIPTable.setColumnSelectionInterval(31-fpMatrix[row][31-column] % 32, 31-fpMatrix[row][31-column] % 32);
					
					AfterIPTable.setRowSelectionInterval(row, row);
					AfterIPTable.setColumnSelectionInterval(column * 4, column * 4 + 3);
					
					
					AfterInitalTable.setRowSelectionInterval(row, row);
					AfterInitalTable.setColumnSelectionInterval(column, column);
					
					// table.clearSelection();
					// table.setRowSelectionInterval(row , row );
					// table.setColumnSelectionInterval((31 - column)% 32, (31 -
					// column)% 32);
					//
					// AfterIPTable.clearSelection();
					// AfterIPTable.setRowSelectionInterval(row, row);
					// AfterIPTable.setColumnSelectionInterval(column, column);

				}

			}
		});
		PlainTextTable.setCellSelectionEnabled(true);
		PlainTextTable.setColumnSelectionAllowed(true);
		PlainTextTable.setBounds(321, 19, 160, 64);
		frmInitalPermutationPanel.getContentPane().add(PlainTextTable);
		PlainTextTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		PlainTextTable.setModel(new DefaultTableModel((type == INITAL_PERMUTATION) ? (SerpentData.intToObject(data.text))
				: (type == LINEAR_TRANSFORMATION)
				? (SerpentData.intToObject(Serpent.FP(data.afterLT[Rounds.round - 1])))
				: (SerpentData.intToObject(data.subKeysBeforIP[Rounds.round
						- ((Rounds.lastSubKeyFlag == false) ? 1 : 0)])),
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
		AfterInitalTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = AfterInitalTable.rowAtPoint(e.getPoint());
				int column = AfterInitalTable.columnAtPoint(e.getPoint());

				PlainTextTable.clearSelection();
				plainTextTable.clearSelection();
				if (row > -1) {

					PlainTextTable.setRowSelectionInterval(row, row);
					PlainTextTable.setColumnSelectionInterval(column, column);

					plainTextTable.setRowSelectionInterval(row, row);
					plainTextTable.setColumnSelectionInterval(column * 4, column * 4 + 3);

					table.setRowSelectionInterval(row, row);
					table.setColumnSelectionInterval((31 - (column * 4 + 3)) % 32, (31 - 4 * column) % 32);

					AfterIPTable.setRowSelectionInterval(fpMatrix[row][31- column] / 32,fpMatrix[row][31-column] / 32);
					AfterIPTable.setColumnSelectionInterval(31-fpMatrix[row][31-column] % 32, 31-fpMatrix[row][31-column] % 32);
					
					AfterIPTable.setRowSelectionInterval(row, row);
					AfterIPTable.setColumnSelectionInterval(column * 4, column * 4 + 3);
					
					
					AfterInitalTable.setRowSelectionInterval(row, row);
					AfterInitalTable.setColumnSelectionInterval(column, column);
					
				

				}

			
			}
		});
		AfterInitalTable.setColumnSelectionAllowed(true);
		AfterInitalTable.setCellSelectionEnabled(true);
		AfterInitalTable.setBounds(319, 335, 165, 64);
		frmInitalPermutationPanel.getContentPane().add(AfterInitalTable);
		AfterInitalTable.setModel(new DefaultTableModel((type == INITAL_PERMUTATION) ? (SerpentData.intToObject(data.afterInitalPermutation))
				: (type == LINEAR_TRANSFORMATION)
				? (SerpentData.intToObject(data.afterLT[Rounds.round - 1]))
				: (SerpentData.intToObject(data.subKeysAfterIP[Rounds.round
						- ((Rounds.lastSubKeyFlag == false) ? 1 : 0)])),
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

		table = new JTable();
		table.setToolTipText("   ");
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(Color.WHITE));
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{ 0, 32, 64, 96, 1, 33, 65, 97, 2, 34, 66, 98, 3, 35, 67, 99, 4, 36, 68, 100, 5, 37, 69, 101, 6,
								38, 70, 102, 7, 39, 71, 103 },
						{ 8, 40, 72, 104, 9, 41, 73, 105, 10, 42, 74, 106, 11, 43, 75, 107, 12, 44, 76, 108, 13, 45, 77,
								109, 14, 46, 78, 110, 15, 47, 79, 111 },
						{ 16, 48, 80, 112, 17, 49, 81, 113, 18, 50, 82, 114, 19, 51, 83, 115, 20, 52, 84, 116, 21, 53,
								85, 117, 22, 54, 86, 118, 23, 55, 87, 119 },
						{ 24, 56, 88, 120, 25, 57, 89, 121, 26, 58, 90, 122, 27, 59, 91, 123, 28, 60, 92, 124, 29, 61,
								93, 125, 30, 62, 94, 126, 31, 63, 95, 127 } },
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31" }));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(0).setMaxWidth(25);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setMinWidth(25);
		table.getColumnModel().getColumn(1).setMaxWidth(25);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setMinWidth(25);
		table.getColumnModel().getColumn(2).setMaxWidth(25);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setMinWidth(25);
		table.getColumnModel().getColumn(3).setMaxWidth(25);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setMinWidth(25);
		table.getColumnModel().getColumn(4).setMaxWidth(25);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(25);
		table.getColumnModel().getColumn(5).setMinWidth(25);
		table.getColumnModel().getColumn(5).setMaxWidth(25);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(25);
		table.getColumnModel().getColumn(6).setMinWidth(25);
		table.getColumnModel().getColumn(6).setMaxWidth(25);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(25);
		table.getColumnModel().getColumn(7).setMinWidth(25);
		table.getColumnModel().getColumn(7).setMaxWidth(25);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(8).setPreferredWidth(25);
		table.getColumnModel().getColumn(8).setMinWidth(25);
		table.getColumnModel().getColumn(8).setMaxWidth(25);
		table.getColumnModel().getColumn(9).setResizable(false);
		table.getColumnModel().getColumn(9).setPreferredWidth(25);
		table.getColumnModel().getColumn(9).setMinWidth(25);
		table.getColumnModel().getColumn(9).setMaxWidth(25);
		table.getColumnModel().getColumn(10).setResizable(false);
		table.getColumnModel().getColumn(10).setPreferredWidth(25);
		table.getColumnModel().getColumn(10).setMinWidth(25);
		table.getColumnModel().getColumn(10).setMaxWidth(25);
		table.getColumnModel().getColumn(11).setResizable(false);
		table.getColumnModel().getColumn(11).setPreferredWidth(25);
		table.getColumnModel().getColumn(11).setMinWidth(25);
		table.getColumnModel().getColumn(11).setMaxWidth(25);
		table.getColumnModel().getColumn(12).setResizable(false);
		table.getColumnModel().getColumn(12).setPreferredWidth(25);
		table.getColumnModel().getColumn(12).setMinWidth(25);
		table.getColumnModel().getColumn(12).setMaxWidth(25);
		table.getColumnModel().getColumn(13).setResizable(false);
		table.getColumnModel().getColumn(13).setPreferredWidth(25);
		table.getColumnModel().getColumn(13).setMinWidth(25);
		table.getColumnModel().getColumn(13).setMaxWidth(25);
		table.getColumnModel().getColumn(14).setResizable(false);
		table.getColumnModel().getColumn(14).setPreferredWidth(25);
		table.getColumnModel().getColumn(14).setMinWidth(25);
		table.getColumnModel().getColumn(14).setMaxWidth(25);
		table.getColumnModel().getColumn(15).setResizable(false);
		table.getColumnModel().getColumn(15).setPreferredWidth(25);
		table.getColumnModel().getColumn(15).setMinWidth(25);
		table.getColumnModel().getColumn(15).setMaxWidth(25);
		table.getColumnModel().getColumn(16).setResizable(false);
		table.getColumnModel().getColumn(16).setPreferredWidth(25);
		table.getColumnModel().getColumn(16).setMinWidth(25);
		table.getColumnModel().getColumn(16).setMaxWidth(25);

		table.getColumnModel().getColumn(17).setPreferredWidth(25);
		table.getColumnModel().getColumn(17).setMinWidth(25);
		table.getColumnModel().getColumn(17).setMaxWidth(25);
		table.getColumnModel().getColumn(17).setResizable(false);
		table.getColumnModel().getColumn(18).setResizable(false);
		table.getColumnModel().getColumn(18).setPreferredWidth(25);
		table.getColumnModel().getColumn(18).setMinWidth(25);
		table.getColumnModel().getColumn(18).setMaxWidth(25);
		table.getColumnModel().getColumn(19).setResizable(false);
		table.getColumnModel().getColumn(19).setPreferredWidth(25);
		table.getColumnModel().getColumn(19).setMinWidth(25);
		table.getColumnModel().getColumn(19).setMaxWidth(25);
		table.getColumnModel().getColumn(25).setResizable(false);
		table.getColumnModel().getColumn(25).setPreferredWidth(25);
		table.getColumnModel().getColumn(25).setMinWidth(25);
		table.getColumnModel().getColumn(25).setMaxWidth(25);
		table.getColumnModel().getColumn(21).setResizable(false);
		table.getColumnModel().getColumn(21).setPreferredWidth(25);
		table.getColumnModel().getColumn(21).setMinWidth(25);
		table.getColumnModel().getColumn(21).setMaxWidth(25);
		table.getColumnModel().getColumn(22).setResizable(false);
		table.getColumnModel().getColumn(22).setPreferredWidth(25);
		table.getColumnModel().getColumn(22).setMinWidth(25);
		table.getColumnModel().getColumn(22).setMaxWidth(25);
		table.getColumnModel().getColumn(23).setResizable(false);
		table.getColumnModel().getColumn(23).setPreferredWidth(25);
		table.getColumnModel().getColumn(23).setMinWidth(25);
		table.getColumnModel().getColumn(23).setMaxWidth(25);
		table.getColumnModel().getColumn(24).setResizable(false);
		table.getColumnModel().getColumn(24).setPreferredWidth(25);
		table.getColumnModel().getColumn(24).setMinWidth(25);
		table.getColumnModel().getColumn(24).setMaxWidth(25);
		table.getColumnModel().getColumn(25).setResizable(false);
		table.getColumnModel().getColumn(25).setPreferredWidth(25);
		table.getColumnModel().getColumn(25).setMinWidth(25);
		table.getColumnModel().getColumn(25).setMaxWidth(25);
		table.getColumnModel().getColumn(26).setResizable(false);
		table.getColumnModel().getColumn(26).setPreferredWidth(25);
		table.getColumnModel().getColumn(26).setMinWidth(25);
		table.getColumnModel().getColumn(26).setMaxWidth(25);
		table.getColumnModel().getColumn(27).setResizable(false);
		table.getColumnModel().getColumn(27).setPreferredWidth(25);
		table.getColumnModel().getColumn(27).setMinWidth(25);
		table.getColumnModel().getColumn(27).setMaxWidth(25);
		table.getColumnModel().getColumn(28).setResizable(false);
		table.getColumnModel().getColumn(28).setPreferredWidth(25);
		table.getColumnModel().getColumn(28).setMinWidth(25);
		table.getColumnModel().getColumn(28).setMaxWidth(25);
		table.getColumnModel().getColumn(29).setResizable(false);
		table.getColumnModel().getColumn(29).setPreferredWidth(25);
		table.getColumnModel().getColumn(29).setMinWidth(25);
		table.getColumnModel().getColumn(29).setMaxWidth(25);
		table.getColumnModel().getColumn(30).setResizable(false);
		table.getColumnModel().getColumn(30).setPreferredWidth(25);
		table.getColumnModel().getColumn(30).setMinWidth(25);
		table.getColumnModel().getColumn(30).setMaxWidth(25);
		table.getColumnModel().getColumn(31).setResizable(false);
		table.getColumnModel().getColumn(31).setPreferredWidth(25);
		table.getColumnModel().getColumn(31).setMinWidth(25);
		table.getColumnModel().getColumn(31).setMaxWidth(25);

		table.setEnabled(false);

		table.setBounds(0, 173, 803, 64);
		frmInitalPermutationPanel.getContentPane().add(table);

		plainTextTable = new JTable();
		plainTextTable.setColumnSelectionAllowed(true);
		plainTextTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = plainTextTable.rowAtPoint(e.getPoint());
				int column = plainTextTable.columnAtPoint(e.getPoint());

				plainTextTable.clearSelection();
				table.clearSelection();
				AfterIPTable.clearSelection();
				if (row > -1 && row < 32) {

					plainTextTable.setRowSelectionInterval(row, row);
					plainTextTable.setColumnSelectionInterval(column, column);

					table.setRowSelectionInterval(row, row);
					table.setColumnSelectionInterval((31 - column) % 32, (31 - column) % 32);
					
					AfterIPTable.setRowSelectionInterval(fpMatrix[row][31- column] / 32,fpMatrix[row][31-column] / 32);
					AfterIPTable.setColumnSelectionInterval(31-fpMatrix[row][31-column] % 32, 31-fpMatrix[row][31-column] % 32);
					

				}

			}
		});
		plainTextTable.setBorder(new LineBorder(Color.WHITE));
		plainTextTable.setForeground(new Color(0, 0, 0));
		plainTextTable.setBackground(Color.WHITE);
		plainTextTable.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		plainTextTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		plainTextTable.setModel(
				new DefaultTableModel(((type == INITAL_PERMUTATION) ? (SerpentData.intArrayToBooleanIntArray(data.text))
						: (type == LINEAR_TRANSFORMATION)
								? (SerpentData.intArrayToBooleanIntArray(Serpent.FP(data.afterLT[Rounds.round - 1])))
								: (SerpentData.intArrayToBooleanIntArray(data.subKeysBeforIP[Rounds.round
										- ((Rounds.lastSubKeyFlag == false) ? 1 : 0)]))),

						new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
								"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
								"29", "30", "31" }));
		plainTextTable.getColumnModel().getColumn(0).setResizable(false);
		plainTextTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(0).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(0).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(1).setResizable(false);
		plainTextTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(1).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(1).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(2).setResizable(false);
		plainTextTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(2).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(2).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(3).setResizable(false);
		plainTextTable.getColumnModel().getColumn(3).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(3).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(3).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(4).setResizable(false);
		plainTextTable.getColumnModel().getColumn(4).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(4).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(4).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(5).setResizable(false);
		plainTextTable.getColumnModel().getColumn(5).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(5).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(5).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(6).setResizable(false);
		plainTextTable.getColumnModel().getColumn(6).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(6).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(6).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(7).setResizable(false);
		plainTextTable.getColumnModel().getColumn(7).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(7).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(7).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(8).setResizable(false);
		plainTextTable.getColumnModel().getColumn(8).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(8).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(8).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(9).setResizable(false);
		plainTextTable.getColumnModel().getColumn(9).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(9).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(9).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(10).setResizable(false);
		plainTextTable.getColumnModel().getColumn(10).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(10).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(10).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(11).setResizable(false);
		plainTextTable.getColumnModel().getColumn(11).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(11).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(11).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(12).setResizable(false);
		plainTextTable.getColumnModel().getColumn(12).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(12).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(12).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(13).setResizable(false);
		plainTextTable.getColumnModel().getColumn(13).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(13).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(13).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(14).setResizable(false);
		plainTextTable.getColumnModel().getColumn(14).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(14).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(14).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(15).setResizable(false);
		plainTextTable.getColumnModel().getColumn(15).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(15).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(15).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(16).setResizable(false);
		plainTextTable.getColumnModel().getColumn(16).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(16).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(16).setMaxWidth(25);

		plainTextTable.getColumnModel().getColumn(17).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(17).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(17).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(17).setResizable(false);
		plainTextTable.getColumnModel().getColumn(18).setResizable(false);
		plainTextTable.getColumnModel().getColumn(18).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(18).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(18).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(19).setResizable(false);
		plainTextTable.getColumnModel().getColumn(19).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(19).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(19).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(25).setResizable(false);
		plainTextTable.getColumnModel().getColumn(25).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(25).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(25).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(21).setResizable(false);
		plainTextTable.getColumnModel().getColumn(21).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(21).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(21).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(22).setResizable(false);
		plainTextTable.getColumnModel().getColumn(22).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(22).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(22).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(23).setResizable(false);
		plainTextTable.getColumnModel().getColumn(23).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(23).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(23).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(24).setResizable(false);
		plainTextTable.getColumnModel().getColumn(24).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(24).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(24).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(25).setResizable(false);
		plainTextTable.getColumnModel().getColumn(25).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(25).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(25).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(26).setResizable(false);
		plainTextTable.getColumnModel().getColumn(26).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(26).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(26).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(27).setResizable(false);
		plainTextTable.getColumnModel().getColumn(27).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(27).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(27).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(28).setResizable(false);
		plainTextTable.getColumnModel().getColumn(28).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(28).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(28).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(29).setResizable(false);
		plainTextTable.getColumnModel().getColumn(29).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(29).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(29).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(30).setResizable(false);
		plainTextTable.getColumnModel().getColumn(30).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(30).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(30).setMaxWidth(25);
		plainTextTable.getColumnModel().getColumn(31).setResizable(false);
		plainTextTable.getColumnModel().getColumn(31).setPreferredWidth(25);
		plainTextTable.getColumnModel().getColumn(31).setMinWidth(25);
		plainTextTable.getColumnModel().getColumn(31).setMaxWidth(25);

		plainTextTable.setBounds(0, 94, 803, 64);
		frmInitalPermutationPanel.getContentPane().add(plainTextTable);

		AfterIPTable = new JTable();
		AfterIPTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int row = AfterIPTable.rowAtPoint(e.getPoint());
				int column = AfterIPTable.columnAtPoint(e.getPoint());

				AfterIPTable.clearSelection();
				plainTextTable.clearSelection();
				if (row > -1) {

					AfterIPTable.setRowSelectionInterval(row, row);
					AfterIPTable.setColumnSelectionInterval(column, column);


					table.setRowSelectionInterval(row ,row);
					table.setColumnSelectionInterval((31 - column) % 32, (31 - column) % 32);

					plainTextTable.setRowSelectionInterval(ipMatrix[row][31- column] / 32,ipMatrix[row][31-column] / 32);
					plainTextTable.setColumnSelectionInterval(31-ipMatrix[row][31-column] % 32, 31-ipMatrix[row][31-column] % 32);

				}

			}
		});
		AfterIPTable.setCellSelectionEnabled(true);
		AfterIPTable.setColumnSelectionAllowed(true);

		AfterIPTable.setBorder(new LineBorder(Color.WHITE));
		AfterIPTable.setForeground(Color.BLACK);
		AfterIPTable.setBackground(Color.WHITE);
		AfterIPTable.setEnabled(false);
		AfterIPTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		AfterIPTable.setModel(new DefaultTableModel(
				((type == INITAL_PERMUTATION) ? (SerpentData.intArrayToBooleanIntArray(data.afterInitalPermutation))
						: (type == LINEAR_TRANSFORMATION)
								? (SerpentData.intArrayToBooleanIntArray(data.afterLT[Rounds.round - 1]))
								: (SerpentData.intArrayToBooleanIntArray(data.subKeysAfterIP[Rounds.round
										- ((Rounds.lastSubKeyFlag == false) ? 1 : 0)]))),

				new String[] { "Name", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
						"15", "New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column", "New column" }));
		AfterIPTable.getColumnModel().getColumn(0).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(0).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(0).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(1).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(1).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(1).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(2).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(2).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(2).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(3).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(3).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(3).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(3).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(4).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(4).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(4).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(4).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(5).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(5).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(5).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(5).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(6).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(6).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(6).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(6).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(7).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(7).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(7).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(7).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(8).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(8).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(8).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(8).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(9).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(9).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(9).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(9).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(10).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(10).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(10).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(10).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(11).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(11).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(11).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(11).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(12).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(12).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(12).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(12).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(13).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(13).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(13).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(13).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(14).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(14).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(14).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(14).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(15).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(15).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(15).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(15).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(16).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(16).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(16).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(16).setMaxWidth(25);

		AfterIPTable.getColumnModel().getColumn(17).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(17).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(17).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(17).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(18).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(18).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(18).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(18).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(19).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(19).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(19).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(19).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(25).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(25).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(25).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(25).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(21).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(21).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(21).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(21).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(22).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(22).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(22).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(22).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(23).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(23).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(23).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(23).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(24).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(24).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(24).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(24).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(25).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(25).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(25).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(25).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(26).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(26).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(26).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(26).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(27).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(27).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(27).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(27).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(28).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(28).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(28).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(28).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(29).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(29).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(29).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(29).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(30).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(30).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(30).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(30).setMaxWidth(25);
		AfterIPTable.getColumnModel().getColumn(31).setResizable(false);
		AfterIPTable.getColumnModel().getColumn(31).setPreferredWidth(25);
		AfterIPTable.getColumnModel().getColumn(31).setMinWidth(25);
		AfterIPTable.getColumnModel().getColumn(31).setMaxWidth(25);

		AfterIPTable.setEnabled(false);

		AfterIPTable.setBounds(0, 252, 803, 64);
		frmInitalPermutationPanel.getContentPane().add(AfterIPTable);

	}
}
