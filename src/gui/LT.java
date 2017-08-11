package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import crypto.SerpentData;
import java.awt.event.MouseMotionAdapter;

public class LT {

	public static SerpentData data = SerpentData.getInstance();
	public JFrame frmLinearTransformation;
	private JTable a1;
	private JTable b1;
	private JTable c1;
	private JTable d1;
	private JTable a2;
	private JTable c2;
	private JTable b2;
	private JTable d2;
	private JTable b3;
	private JTable d3;
	private JTable a3;
	private JTable c3;
	private JTable a4;
	private JTable c4;
	private JTable a;
	private JTable b;
	private JTable c;
	private JTable d;
	private JTable af;
	private JTable bf;
	private JTable cf;
	private JTable df;
	private JLabel ImageLabel;
	private JPanel panel;
	private JLabel label13;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel xor1;
	private JLabel xor2;
	private JLabel xor3;
	private JLabel xor4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LT window = new LT();
					window.frmLinearTransformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void labelToolTip(JLabel argLabel, int pos, int prev, int next) {
		
		
	
		String toolTipString = "<html> <code>";
		toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][prev])).replace(' ',
				'0');
		if(Rounds.ENCRYPT)
			toolTipString += "<br> &lt&lt&lt " + pos + "<br>";
		else
			toolTipString += "<br> &gt&gt&gt " + pos + "<br>";
		toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][next])).replace(' ',
				'0') + "<br></code></html>";
		argLabel.setToolTipText(toolTipString);
	}

	private void xorToolTip(JLabel argLabel, int a, int b, int c, int result, int pos) {
		String toolTipString = "<html> <code>";
		toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][a])).replace(' ',
				'0') + "<br>";
		toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][b])).replace(' ',
				'0') + "<br>";
		if (pos > 0) {
			toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][c] << pos))
					.replace(' ', '0') + "<br>";
		} else
			toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][c]))
					.replace(' ', '0') + "<br>";
		toolTipString += "==================================<br>";
		toolTipString += String.format("%32s", Integer.toBinaryString(data.LTdata[Rounds.round - 1][result]))
				.replace(' ', '0') + "</code></html>";
		argLabel.setToolTipText(toolTipString);
	}

	/**
	 * Create the application.
	 */
	public LT() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLinearTransformation = new JFrame();
		frmLinearTransformation.setTitle("Linear Transformation");
		frmLinearTransformation.setResizable(false);
		frmLinearTransformation.setForeground(Color.WHITE);
		frmLinearTransformation.setBackground(Color.WHITE);
		frmLinearTransformation.getContentPane().setForeground(Color.WHITE);
		frmLinearTransformation.getContentPane().setBackground(Color.BLACK);
		frmLinearTransformation.setBounds(100, 100, 508, 650);
		frmLinearTransformation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLinearTransformation.getContentPane().setLayout(null);

		df = new JTable();
		df.setForeground(Color.BLACK);
		df.setBackground(Color.WHITE);
		df.setShowVerticalLines(false);
		df.setEnabled(false);
		df.setBounds(376, 570, 112, 15);
		df.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.afterLT[Rounds.round - 1][3] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 3858050442291020030L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		df.getColumnModel().getColumn(0).setResizable(false);
		df.getColumnModel().getColumn(0).setPreferredWidth(14);
		df.getColumnModel().getColumn(0).setMinWidth(14);
		df.getColumnModel().getColumn(0).setMaxWidth(14);
		df.getColumnModel().getColumn(1).setPreferredWidth(14);
		df.getColumnModel().getColumn(1).setMinWidth(14);
		df.getColumnModel().getColumn(1).setMaxWidth(14);
		df.getColumnModel().getColumn(2).setResizable(false);
		df.getColumnModel().getColumn(2).setPreferredWidth(14);
		df.getColumnModel().getColumn(2).setMinWidth(14);
		df.getColumnModel().getColumn(2).setMaxWidth(14);
		df.getColumnModel().getColumn(3).setResizable(false);
		df.getColumnModel().getColumn(3).setPreferredWidth(14);
		df.getColumnModel().getColumn(3).setMinWidth(14);
		df.getColumnModel().getColumn(3).setMaxWidth(14);
		df.getColumnModel().getColumn(4).setResizable(false);
		df.getColumnModel().getColumn(4).setPreferredWidth(14);
		df.getColumnModel().getColumn(4).setMinWidth(14);
		df.getColumnModel().getColumn(4).setMaxWidth(14);
		df.getColumnModel().getColumn(5).setResizable(false);
		df.getColumnModel().getColumn(5).setPreferredWidth(14);
		df.getColumnModel().getColumn(5).setMinWidth(14);
		df.getColumnModel().getColumn(5).setMaxWidth(14);
		df.getColumnModel().getColumn(6).setResizable(false);
		df.getColumnModel().getColumn(6).setPreferredWidth(14);
		df.getColumnModel().getColumn(6).setMinWidth(14);
		df.getColumnModel().getColumn(6).setMaxWidth(14);
		df.getColumnModel().getColumn(7).setResizable(false);
		df.getColumnModel().getColumn(7).setPreferredWidth(14);
		df.getColumnModel().getColumn(7).setMinWidth(14);
		df.getColumnModel().getColumn(7).setMaxWidth(14);

		a1 = new JTable();
		a1.setForeground(Color.BLACK);
		a1.setBackground(Color.WHITE);
		a1.setShowVerticalLines(false);
		a1.setEnabled(false);
		a1.setBounds(10, (Rounds.ENCRYPT == true) ? 119 : 142, 112, 14);
		a1.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][0] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1242736564666591334L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		a1.getColumnModel().getColumn(0).setResizable(false);
		a1.getColumnModel().getColumn(0).setPreferredWidth(14);
		a1.getColumnModel().getColumn(0).setMinWidth(14);
		a1.getColumnModel().getColumn(0).setMaxWidth(14);
		a1.getColumnModel().getColumn(1).setPreferredWidth(14);
		a1.getColumnModel().getColumn(1).setMinWidth(14);
		a1.getColumnModel().getColumn(1).setMaxWidth(14);
		a1.getColumnModel().getColumn(2).setResizable(false);
		a1.getColumnModel().getColumn(2).setPreferredWidth(14);
		a1.getColumnModel().getColumn(2).setMinWidth(14);
		a1.getColumnModel().getColumn(2).setMaxWidth(14);
		a1.getColumnModel().getColumn(3).setResizable(false);
		a1.getColumnModel().getColumn(3).setPreferredWidth(14);
		a1.getColumnModel().getColumn(3).setMinWidth(14);
		a1.getColumnModel().getColumn(3).setMaxWidth(14);
		a1.getColumnModel().getColumn(4).setResizable(false);
		a1.getColumnModel().getColumn(4).setPreferredWidth(14);
		a1.getColumnModel().getColumn(4).setMinWidth(14);
		a1.getColumnModel().getColumn(4).setMaxWidth(14);
		a1.getColumnModel().getColumn(5).setResizable(false);
		a1.getColumnModel().getColumn(5).setPreferredWidth(14);
		a1.getColumnModel().getColumn(5).setMinWidth(14);
		a1.getColumnModel().getColumn(5).setMaxWidth(14);
		a1.getColumnModel().getColumn(6).setResizable(false);
		a1.getColumnModel().getColumn(6).setPreferredWidth(14);
		a1.getColumnModel().getColumn(6).setMinWidth(14);
		a1.getColumnModel().getColumn(6).setMaxWidth(14);
		a1.getColumnModel().getColumn(7).setResizable(false);
		a1.getColumnModel().getColumn(7).setPreferredWidth(14);
		a1.getColumnModel().getColumn(7).setMinWidth(14);
		a1.getColumnModel().getColumn(7).setMaxWidth(14);
		a1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(a1);

		b1 = new JTable();
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.WHITE);
		b1.setShowVerticalLines(false);
		b1.setEnabled(false);
		b1.setBounds(132, (Rounds.ENCRYPT == true) ? 119 : 142, 112, 14);
		b1.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][1] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -1894063226396591424L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		b1.getColumnModel().getColumn(0).setResizable(false);
		b1.getColumnModel().getColumn(0).setPreferredWidth(14);
		b1.getColumnModel().getColumn(0).setMinWidth(14);
		b1.getColumnModel().getColumn(0).setMaxWidth(14);
		b1.getColumnModel().getColumn(1).setPreferredWidth(14);
		b1.getColumnModel().getColumn(1).setMinWidth(14);
		b1.getColumnModel().getColumn(1).setMaxWidth(14);
		b1.getColumnModel().getColumn(2).setResizable(false);
		b1.getColumnModel().getColumn(2).setPreferredWidth(14);
		b1.getColumnModel().getColumn(2).setMinWidth(14);
		b1.getColumnModel().getColumn(2).setMaxWidth(14);
		b1.getColumnModel().getColumn(3).setResizable(false);
		b1.getColumnModel().getColumn(3).setPreferredWidth(14);
		b1.getColumnModel().getColumn(3).setMinWidth(14);
		b1.getColumnModel().getColumn(3).setMaxWidth(14);
		b1.getColumnModel().getColumn(4).setResizable(false);
		b1.getColumnModel().getColumn(4).setPreferredWidth(14);
		b1.getColumnModel().getColumn(4).setMinWidth(14);
		b1.getColumnModel().getColumn(4).setMaxWidth(14);
		b1.getColumnModel().getColumn(5).setResizable(false);
		b1.getColumnModel().getColumn(5).setPreferredWidth(14);
		b1.getColumnModel().getColumn(5).setMinWidth(14);
		b1.getColumnModel().getColumn(5).setMaxWidth(14);
		b1.getColumnModel().getColumn(6).setResizable(false);
		b1.getColumnModel().getColumn(6).setPreferredWidth(14);
		b1.getColumnModel().getColumn(6).setMinWidth(14);
		b1.getColumnModel().getColumn(6).setMaxWidth(14);
		b1.getColumnModel().getColumn(7).setResizable(false);
		b1.getColumnModel().getColumn(7).setPreferredWidth(14);
		b1.getColumnModel().getColumn(7).setMinWidth(14);
		b1.getColumnModel().getColumn(7).setMaxWidth(14);
		b1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(b1);

		c1 = new JTable();
		c1.setForeground(Color.BLACK);
		c1.setBackground(Color.WHITE);
		c1.setShowVerticalLines(false);
		c1.setEnabled(false);
		c1.setBounds(254, (Rounds.ENCRYPT == true) ? 119 : 142, 112, 15);
		c1.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][2] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -837996950574090902L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		c1.getColumnModel().getColumn(0).setResizable(false);
		c1.getColumnModel().getColumn(0).setPreferredWidth(14);
		c1.getColumnModel().getColumn(0).setMinWidth(14);
		c1.getColumnModel().getColumn(0).setMaxWidth(14);
		c1.getColumnModel().getColumn(1).setPreferredWidth(14);
		c1.getColumnModel().getColumn(1).setMinWidth(14);
		c1.getColumnModel().getColumn(1).setMaxWidth(14);
		c1.getColumnModel().getColumn(2).setResizable(false);
		c1.getColumnModel().getColumn(2).setPreferredWidth(14);
		c1.getColumnModel().getColumn(2).setMinWidth(14);
		c1.getColumnModel().getColumn(2).setMaxWidth(14);
		c1.getColumnModel().getColumn(3).setResizable(false);
		c1.getColumnModel().getColumn(3).setPreferredWidth(14);
		c1.getColumnModel().getColumn(3).setMinWidth(14);
		c1.getColumnModel().getColumn(3).setMaxWidth(14);
		c1.getColumnModel().getColumn(4).setResizable(false);
		c1.getColumnModel().getColumn(4).setPreferredWidth(14);
		c1.getColumnModel().getColumn(4).setMinWidth(14);
		c1.getColumnModel().getColumn(4).setMaxWidth(14);
		c1.getColumnModel().getColumn(5).setResizable(false);
		c1.getColumnModel().getColumn(5).setPreferredWidth(14);
		c1.getColumnModel().getColumn(5).setMinWidth(14);
		c1.getColumnModel().getColumn(5).setMaxWidth(14);
		c1.getColumnModel().getColumn(6).setResizable(false);
		c1.getColumnModel().getColumn(6).setPreferredWidth(14);
		c1.getColumnModel().getColumn(6).setMinWidth(14);
		c1.getColumnModel().getColumn(6).setMaxWidth(14);
		c1.getColumnModel().getColumn(7).setResizable(false);
		c1.getColumnModel().getColumn(7).setPreferredWidth(14);
		c1.getColumnModel().getColumn(7).setMinWidth(14);
		c1.getColumnModel().getColumn(7).setMaxWidth(14);
		c1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(c1);

		d1 = new JTable();
		d1.setForeground(Color.BLACK);
		d1.setBackground(Color.WHITE);
		d1.setShowVerticalLines(false);
		d1.setEnabled(false);
		d1.setBounds(376, (Rounds.ENCRYPT == true) ? 119 : 142, 112, 15);
		d1.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][3] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -3183692702111178460L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		d1.getColumnModel().getColumn(0).setResizable(false);
		d1.getColumnModel().getColumn(0).setPreferredWidth(14);
		d1.getColumnModel().getColumn(0).setMinWidth(14);
		d1.getColumnModel().getColumn(0).setMaxWidth(14);
		d1.getColumnModel().getColumn(1).setPreferredWidth(14);
		d1.getColumnModel().getColumn(1).setMinWidth(14);
		d1.getColumnModel().getColumn(1).setMaxWidth(14);
		d1.getColumnModel().getColumn(2).setResizable(false);
		d1.getColumnModel().getColumn(2).setPreferredWidth(14);
		d1.getColumnModel().getColumn(2).setMinWidth(14);
		d1.getColumnModel().getColumn(2).setMaxWidth(14);
		d1.getColumnModel().getColumn(3).setResizable(false);
		d1.getColumnModel().getColumn(3).setPreferredWidth(14);
		d1.getColumnModel().getColumn(3).setMinWidth(14);
		d1.getColumnModel().getColumn(3).setMaxWidth(14);
		d1.getColumnModel().getColumn(4).setResizable(false);
		d1.getColumnModel().getColumn(4).setPreferredWidth(14);
		d1.getColumnModel().getColumn(4).setMinWidth(14);
		d1.getColumnModel().getColumn(4).setMaxWidth(14);
		d1.getColumnModel().getColumn(5).setResizable(false);
		d1.getColumnModel().getColumn(5).setPreferredWidth(14);
		d1.getColumnModel().getColumn(5).setMinWidth(14);
		d1.getColumnModel().getColumn(5).setMaxWidth(14);
		d1.getColumnModel().getColumn(6).setResizable(false);
		d1.getColumnModel().getColumn(6).setPreferredWidth(14);
		d1.getColumnModel().getColumn(6).setMinWidth(14);
		d1.getColumnModel().getColumn(6).setMaxWidth(14);
		d1.getColumnModel().getColumn(7).setResizable(false);
		d1.getColumnModel().getColumn(7).setPreferredWidth(14);
		d1.getColumnModel().getColumn(7).setMinWidth(14);
		d1.getColumnModel().getColumn(7).setMaxWidth(14);
		d1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(d1);

		a2 = new JTable();
		a2.setForeground(Color.BLACK);
		a2.setBackground(Color.WHITE);
		a2.setShowVerticalLines(false);
		a2.setEnabled(false);
		a2.setBounds(10, 199, 112, 15);
		a2.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][4] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 3276557886243345448L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		a2.getColumnModel().getColumn(0).setResizable(false);
		a2.getColumnModel().getColumn(0).setPreferredWidth(14);
		a2.getColumnModel().getColumn(0).setMinWidth(14);
		a2.getColumnModel().getColumn(0).setMaxWidth(14);
		a2.getColumnModel().getColumn(1).setPreferredWidth(14);
		a2.getColumnModel().getColumn(1).setMinWidth(14);
		a2.getColumnModel().getColumn(1).setMaxWidth(14);
		a2.getColumnModel().getColumn(2).setResizable(false);
		a2.getColumnModel().getColumn(2).setPreferredWidth(14);
		a2.getColumnModel().getColumn(2).setMinWidth(14);
		a2.getColumnModel().getColumn(2).setMaxWidth(14);
		a2.getColumnModel().getColumn(3).setResizable(false);
		a2.getColumnModel().getColumn(3).setPreferredWidth(14);
		a2.getColumnModel().getColumn(3).setMinWidth(14);
		a2.getColumnModel().getColumn(3).setMaxWidth(14);
		a2.getColumnModel().getColumn(4).setResizable(false);
		a2.getColumnModel().getColumn(4).setPreferredWidth(14);
		a2.getColumnModel().getColumn(4).setMinWidth(14);
		a2.getColumnModel().getColumn(4).setMaxWidth(14);
		a2.getColumnModel().getColumn(5).setResizable(false);
		a2.getColumnModel().getColumn(5).setPreferredWidth(14);
		a2.getColumnModel().getColumn(5).setMinWidth(14);
		a2.getColumnModel().getColumn(5).setMaxWidth(14);
		a2.getColumnModel().getColumn(6).setResizable(false);
		a2.getColumnModel().getColumn(6).setPreferredWidth(14);
		a2.getColumnModel().getColumn(6).setMinWidth(14);
		a2.getColumnModel().getColumn(6).setMaxWidth(14);
		a2.getColumnModel().getColumn(7).setResizable(false);
		a2.getColumnModel().getColumn(7).setPreferredWidth(14);
		a2.getColumnModel().getColumn(7).setMinWidth(14);
		a2.getColumnModel().getColumn(7).setMaxWidth(14);
		a2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(a2);

		c2 = new JTable();
		c2.setForeground(Color.BLACK);
		c2.setBackground(Color.WHITE);
		c2.setShowVerticalLines(false);
		c2.setEnabled(false);
		c2.setBounds(254, 199, 112, 15);
		c2.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][5] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 3970216528030508070L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		c2.getColumnModel().getColumn(0).setResizable(false);
		c2.getColumnModel().getColumn(0).setPreferredWidth(14);
		c2.getColumnModel().getColumn(0).setMinWidth(14);
		c2.getColumnModel().getColumn(0).setMaxWidth(14);
		c2.getColumnModel().getColumn(1).setPreferredWidth(14);
		c2.getColumnModel().getColumn(1).setMinWidth(14);
		c2.getColumnModel().getColumn(1).setMaxWidth(14);
		c2.getColumnModel().getColumn(2).setResizable(false);
		c2.getColumnModel().getColumn(2).setPreferredWidth(14);
		c2.getColumnModel().getColumn(2).setMinWidth(14);
		c2.getColumnModel().getColumn(2).setMaxWidth(14);
		c2.getColumnModel().getColumn(3).setResizable(false);
		c2.getColumnModel().getColumn(3).setPreferredWidth(14);
		c2.getColumnModel().getColumn(3).setMinWidth(14);
		c2.getColumnModel().getColumn(3).setMaxWidth(14);
		c2.getColumnModel().getColumn(4).setResizable(false);
		c2.getColumnModel().getColumn(4).setPreferredWidth(14);
		c2.getColumnModel().getColumn(4).setMinWidth(14);
		c2.getColumnModel().getColumn(4).setMaxWidth(14);
		c2.getColumnModel().getColumn(5).setResizable(false);
		c2.getColumnModel().getColumn(5).setPreferredWidth(14);
		c2.getColumnModel().getColumn(5).setMinWidth(14);
		c2.getColumnModel().getColumn(5).setMaxWidth(14);
		c2.getColumnModel().getColumn(6).setResizable(false);
		c2.getColumnModel().getColumn(6).setPreferredWidth(14);
		c2.getColumnModel().getColumn(6).setMinWidth(14);
		c2.getColumnModel().getColumn(6).setMaxWidth(14);
		c2.getColumnModel().getColumn(7).setResizable(false);
		c2.getColumnModel().getColumn(7).setPreferredWidth(14);
		c2.getColumnModel().getColumn(7).setMinWidth(14);
		c2.getColumnModel().getColumn(7).setMaxWidth(14);
		c2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(c2);

		b2 = new JTable();
		b2.setForeground(Color.BLACK);
		b2.setBackground(Color.WHITE);
		b2.setShowVerticalLines(false);
		b2.setEnabled(false);
		b2.setBounds(132, (Rounds.ENCRYPT == true) ? 256 : 300, 112, 15);
		b2.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][6] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -6424316416302980215L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		b2.getColumnModel().getColumn(0).setResizable(false);
		b2.getColumnModel().getColumn(0).setPreferredWidth(14);
		b2.getColumnModel().getColumn(0).setMinWidth(14);
		b2.getColumnModel().getColumn(0).setMaxWidth(14);
		b2.getColumnModel().getColumn(1).setPreferredWidth(14);
		b2.getColumnModel().getColumn(1).setMinWidth(14);
		b2.getColumnModel().getColumn(1).setMaxWidth(14);
		b2.getColumnModel().getColumn(2).setResizable(false);
		b2.getColumnModel().getColumn(2).setPreferredWidth(14);
		b2.getColumnModel().getColumn(2).setMinWidth(14);
		b2.getColumnModel().getColumn(2).setMaxWidth(14);
		b2.getColumnModel().getColumn(3).setResizable(false);
		b2.getColumnModel().getColumn(3).setPreferredWidth(14);
		b2.getColumnModel().getColumn(3).setMinWidth(14);
		b2.getColumnModel().getColumn(3).setMaxWidth(14);
		b2.getColumnModel().getColumn(4).setResizable(false);
		b2.getColumnModel().getColumn(4).setPreferredWidth(14);
		b2.getColumnModel().getColumn(4).setMinWidth(14);
		b2.getColumnModel().getColumn(4).setMaxWidth(14);
		b2.getColumnModel().getColumn(5).setResizable(false);
		b2.getColumnModel().getColumn(5).setPreferredWidth(14);
		b2.getColumnModel().getColumn(5).setMinWidth(14);
		b2.getColumnModel().getColumn(5).setMaxWidth(14);
		b2.getColumnModel().getColumn(6).setResizable(false);
		b2.getColumnModel().getColumn(6).setPreferredWidth(14);
		b2.getColumnModel().getColumn(6).setMinWidth(14);
		b2.getColumnModel().getColumn(6).setMaxWidth(14);
		b2.getColumnModel().getColumn(7).setResizable(false);
		b2.getColumnModel().getColumn(7).setPreferredWidth(14);
		b2.getColumnModel().getColumn(7).setMinWidth(14);
		b2.getColumnModel().getColumn(7).setMaxWidth(14);
		b2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(b2);

		d2 = new JTable();
		d2.setForeground(Color.BLACK);
		d2.setBackground(Color.WHITE);
		d2.setShowVerticalLines(false);
		d2.setEnabled(false);
		d2.setBounds(376, (Rounds.ENCRYPT == true) ? 317 : 300, 112, 15);
		d2.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][7] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -2740658095502802531L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		d2.getColumnModel().getColumn(0).setResizable(false);
		d2.getColumnModel().getColumn(0).setPreferredWidth(14);
		d2.getColumnModel().getColumn(0).setMinWidth(14);
		d2.getColumnModel().getColumn(0).setMaxWidth(14);
		d2.getColumnModel().getColumn(1).setPreferredWidth(14);
		d2.getColumnModel().getColumn(1).setMinWidth(14);
		d2.getColumnModel().getColumn(1).setMaxWidth(14);
		d2.getColumnModel().getColumn(2).setResizable(false);
		d2.getColumnModel().getColumn(2).setPreferredWidth(14);
		d2.getColumnModel().getColumn(2).setMinWidth(14);
		d2.getColumnModel().getColumn(2).setMaxWidth(14);
		d2.getColumnModel().getColumn(3).setResizable(false);
		d2.getColumnModel().getColumn(3).setPreferredWidth(14);
		d2.getColumnModel().getColumn(3).setMinWidth(14);
		d2.getColumnModel().getColumn(3).setMaxWidth(14);
		d2.getColumnModel().getColumn(4).setResizable(false);
		d2.getColumnModel().getColumn(4).setPreferredWidth(14);
		d2.getColumnModel().getColumn(4).setMinWidth(14);
		d2.getColumnModel().getColumn(4).setMaxWidth(14);
		d2.getColumnModel().getColumn(5).setResizable(false);
		d2.getColumnModel().getColumn(5).setPreferredWidth(14);
		d2.getColumnModel().getColumn(5).setMinWidth(14);
		d2.getColumnModel().getColumn(5).setMaxWidth(14);
		d2.getColumnModel().getColumn(6).setResizable(false);
		d2.getColumnModel().getColumn(6).setPreferredWidth(14);
		d2.getColumnModel().getColumn(6).setMinWidth(14);
		d2.getColumnModel().getColumn(6).setMaxWidth(14);
		d2.getColumnModel().getColumn(7).setResizable(false);
		d2.getColumnModel().getColumn(7).setPreferredWidth(14);
		d2.getColumnModel().getColumn(7).setMinWidth(14);
		d2.getColumnModel().getColumn(7).setMaxWidth(14);
		d2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(d2);

		b3 = new JTable();
		b3.setForeground(Color.BLACK);
		b3.setBackground(Color.WHITE);
		b3.setShowVerticalLines(false);
		b3.setEnabled(false);
		b3.setBounds(132, (Rounds.ENCRYPT == true) ? 482 : 500, 112, 15);
		b3.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][8] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -1641782284299683977L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		b3.getColumnModel().getColumn(0).setResizable(false);
		b3.getColumnModel().getColumn(0).setPreferredWidth(14);
		b3.getColumnModel().getColumn(0).setMinWidth(14);
		b3.getColumnModel().getColumn(0).setMaxWidth(14);
		b3.getColumnModel().getColumn(1).setPreferredWidth(14);
		b3.getColumnModel().getColumn(1).setMinWidth(14);
		b3.getColumnModel().getColumn(1).setMaxWidth(14);
		b3.getColumnModel().getColumn(2).setResizable(false);
		b3.getColumnModel().getColumn(2).setPreferredWidth(14);
		b3.getColumnModel().getColumn(2).setMinWidth(14);
		b3.getColumnModel().getColumn(2).setMaxWidth(14);
		b3.getColumnModel().getColumn(3).setResizable(false);
		b3.getColumnModel().getColumn(3).setPreferredWidth(14);
		b3.getColumnModel().getColumn(3).setMinWidth(14);
		b3.getColumnModel().getColumn(3).setMaxWidth(14);
		b3.getColumnModel().getColumn(4).setResizable(false);
		b3.getColumnModel().getColumn(4).setPreferredWidth(14);
		b3.getColumnModel().getColumn(4).setMinWidth(14);
		b3.getColumnModel().getColumn(4).setMaxWidth(14);
		b3.getColumnModel().getColumn(5).setResizable(false);
		b3.getColumnModel().getColumn(5).setPreferredWidth(14);
		b3.getColumnModel().getColumn(5).setMinWidth(14);
		b3.getColumnModel().getColumn(5).setMaxWidth(14);
		b3.getColumnModel().getColumn(6).setResizable(false);
		b3.getColumnModel().getColumn(6).setPreferredWidth(14);
		b3.getColumnModel().getColumn(6).setMinWidth(14);
		b3.getColumnModel().getColumn(6).setMaxWidth(14);
		b3.getColumnModel().getColumn(7).setResizable(false);
		b3.getColumnModel().getColumn(7).setPreferredWidth(14);
		b3.getColumnModel().getColumn(7).setMinWidth(14);
		b3.getColumnModel().getColumn(7).setMaxWidth(14);
		b3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(b3);

		d3 = new JTable();
		d3.setForeground(Color.BLACK);
		d3.setBackground(Color.WHITE);
		d3.setShowVerticalLines(false);
		d3.setEnabled(false);
		d3.setBounds(376, (Rounds.ENCRYPT == true) ? 482 : 500, 112, 15);
		d3.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][9] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 14009861491232642L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		d3.getColumnModel().getColumn(0).setResizable(false);
		d3.getColumnModel().getColumn(0).setPreferredWidth(14);
		d3.getColumnModel().getColumn(0).setMinWidth(14);
		d3.getColumnModel().getColumn(0).setMaxWidth(14);
		d3.getColumnModel().getColumn(1).setPreferredWidth(14);
		d3.getColumnModel().getColumn(1).setMinWidth(14);
		d3.getColumnModel().getColumn(1).setMaxWidth(14);
		d3.getColumnModel().getColumn(2).setResizable(false);
		d3.getColumnModel().getColumn(2).setPreferredWidth(14);
		d3.getColumnModel().getColumn(2).setMinWidth(14);
		d3.getColumnModel().getColumn(2).setMaxWidth(14);
		d3.getColumnModel().getColumn(3).setResizable(false);
		d3.getColumnModel().getColumn(3).setPreferredWidth(14);
		d3.getColumnModel().getColumn(3).setMinWidth(14);
		d3.getColumnModel().getColumn(3).setMaxWidth(14);
		d3.getColumnModel().getColumn(4).setResizable(false);
		d3.getColumnModel().getColumn(4).setPreferredWidth(14);
		d3.getColumnModel().getColumn(4).setMinWidth(14);
		d3.getColumnModel().getColumn(4).setMaxWidth(14);
		d3.getColumnModel().getColumn(5).setResizable(false);
		d3.getColumnModel().getColumn(5).setPreferredWidth(14);
		d3.getColumnModel().getColumn(5).setMinWidth(14);
		d3.getColumnModel().getColumn(5).setMaxWidth(14);
		d3.getColumnModel().getColumn(6).setResizable(false);
		d3.getColumnModel().getColumn(6).setPreferredWidth(14);
		d3.getColumnModel().getColumn(6).setMinWidth(14);
		d3.getColumnModel().getColumn(6).setMaxWidth(14);
		d3.getColumnModel().getColumn(7).setResizable(false);
		d3.getColumnModel().getColumn(7).setPreferredWidth(14);
		d3.getColumnModel().getColumn(7).setMinWidth(14);
		d3.getColumnModel().getColumn(7).setMaxWidth(14);
		d3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(d3);

		a3 = new JTable();
		a3.setForeground(Color.BLACK);
		a3.setBackground(Color.WHITE);
		a3.setShowHorizontalLines(false);
		a3.setShowVerticalLines(false);
		a3.setEnabled(false);
		a3.setBounds(10, (Rounds.ENCRYPT == true) ? 408 : 300, 112, 15);
		a3.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][10] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -3212253794688437375L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		a3.getColumnModel().getColumn(0).setResizable(false);
		a3.getColumnModel().getColumn(0).setPreferredWidth(14);
		a3.getColumnModel().getColumn(0).setMinWidth(14);
		a3.getColumnModel().getColumn(0).setMaxWidth(14);
		a3.getColumnModel().getColumn(1).setPreferredWidth(14);
		a3.getColumnModel().getColumn(1).setMinWidth(14);
		a3.getColumnModel().getColumn(1).setMaxWidth(14);
		a3.getColumnModel().getColumn(2).setResizable(false);
		a3.getColumnModel().getColumn(2).setPreferredWidth(14);
		a3.getColumnModel().getColumn(2).setMinWidth(14);
		a3.getColumnModel().getColumn(2).setMaxWidth(14);
		a3.getColumnModel().getColumn(3).setResizable(false);
		a3.getColumnModel().getColumn(3).setPreferredWidth(14);
		a3.getColumnModel().getColumn(3).setMinWidth(14);
		a3.getColumnModel().getColumn(3).setMaxWidth(14);
		a3.getColumnModel().getColumn(4).setResizable(false);
		a3.getColumnModel().getColumn(4).setPreferredWidth(14);
		a3.getColumnModel().getColumn(4).setMinWidth(14);
		a3.getColumnModel().getColumn(4).setMaxWidth(14);
		a3.getColumnModel().getColumn(5).setResizable(false);
		a3.getColumnModel().getColumn(5).setPreferredWidth(14);
		a3.getColumnModel().getColumn(5).setMinWidth(14);
		a3.getColumnModel().getColumn(5).setMaxWidth(14);
		a3.getColumnModel().getColumn(6).setResizable(false);
		a3.getColumnModel().getColumn(6).setPreferredWidth(14);
		a3.getColumnModel().getColumn(6).setMinWidth(14);
		a3.getColumnModel().getColumn(6).setMaxWidth(14);
		a3.getColumnModel().getColumn(7).setResizable(false);
		a3.getColumnModel().getColumn(7).setPreferredWidth(14);
		a3.getColumnModel().getColumn(7).setMinWidth(14);
		a3.getColumnModel().getColumn(7).setMaxWidth(14);
		a3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(a3);

		c3 = new JTable();
		c3.setForeground(Color.BLACK);
		c3.setBackground(Color.WHITE);
		c3.setShowVerticalLines(false);
		c3.setEnabled(false);
		c3.setBounds(254, (Rounds.ENCRYPT == true) ? 420 : 300, 112, 15);
		c3.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][11] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 673694055229143392L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		c3.getColumnModel().getColumn(0).setResizable(false);
		c3.getColumnModel().getColumn(0).setPreferredWidth(14);
		c3.getColumnModel().getColumn(0).setMinWidth(14);
		c3.getColumnModel().getColumn(0).setMaxWidth(14);
		c3.getColumnModel().getColumn(1).setPreferredWidth(14);
		c3.getColumnModel().getColumn(1).setMinWidth(14);
		c3.getColumnModel().getColumn(1).setMaxWidth(14);
		c3.getColumnModel().getColumn(2).setResizable(false);
		c3.getColumnModel().getColumn(2).setPreferredWidth(14);
		c3.getColumnModel().getColumn(2).setMinWidth(14);
		c3.getColumnModel().getColumn(2).setMaxWidth(14);
		c3.getColumnModel().getColumn(3).setResizable(false);
		c3.getColumnModel().getColumn(3).setPreferredWidth(14);
		c3.getColumnModel().getColumn(3).setMinWidth(14);
		c3.getColumnModel().getColumn(3).setMaxWidth(14);
		c3.getColumnModel().getColumn(4).setResizable(false);
		c3.getColumnModel().getColumn(4).setPreferredWidth(14);
		c3.getColumnModel().getColumn(4).setMinWidth(14);
		c3.getColumnModel().getColumn(4).setMaxWidth(14);
		c3.getColumnModel().getColumn(5).setResizable(false);
		c3.getColumnModel().getColumn(5).setPreferredWidth(14);
		c3.getColumnModel().getColumn(5).setMinWidth(14);
		c3.getColumnModel().getColumn(5).setMaxWidth(14);
		c3.getColumnModel().getColumn(6).setResizable(false);
		c3.getColumnModel().getColumn(6).setPreferredWidth(14);
		c3.getColumnModel().getColumn(6).setMinWidth(14);
		c3.getColumnModel().getColumn(6).setMaxWidth(14);
		c3.getColumnModel().getColumn(7).setResizable(false);
		c3.getColumnModel().getColumn(7).setPreferredWidth(14);
		c3.getColumnModel().getColumn(7).setMinWidth(14);
		c3.getColumnModel().getColumn(7).setMaxWidth(14);
		c3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(c3);

		a4 = new JTable();
		a4.setForeground(Color.BLACK);
		a4.setBackground(Color.WHITE);
		a4.setShowVerticalLines(false);
		a4.setEnabled(false);
		a4.setBounds(10, (Rounds.ENCRYPT == true) ? 482 : 500, 112, 15);
		a4.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][12] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -8617411291639616253L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		a4.getColumnModel().getColumn(0).setResizable(false);
		a4.getColumnModel().getColumn(0).setPreferredWidth(14);
		a4.getColumnModel().getColumn(0).setMinWidth(14);
		a4.getColumnModel().getColumn(0).setMaxWidth(14);
		a4.getColumnModel().getColumn(1).setPreferredWidth(14);
		a4.getColumnModel().getColumn(1).setMinWidth(14);
		a4.getColumnModel().getColumn(1).setMaxWidth(14);
		a4.getColumnModel().getColumn(2).setResizable(false);
		a4.getColumnModel().getColumn(2).setPreferredWidth(14);
		a4.getColumnModel().getColumn(2).setMinWidth(14);
		a4.getColumnModel().getColumn(2).setMaxWidth(14);
		a4.getColumnModel().getColumn(3).setResizable(false);
		a4.getColumnModel().getColumn(3).setPreferredWidth(14);
		a4.getColumnModel().getColumn(3).setMinWidth(14);
		a4.getColumnModel().getColumn(3).setMaxWidth(14);
		a4.getColumnModel().getColumn(4).setResizable(false);
		a4.getColumnModel().getColumn(4).setPreferredWidth(14);
		a4.getColumnModel().getColumn(4).setMinWidth(14);
		a4.getColumnModel().getColumn(4).setMaxWidth(14);
		a4.getColumnModel().getColumn(5).setResizable(false);
		a4.getColumnModel().getColumn(5).setPreferredWidth(14);
		a4.getColumnModel().getColumn(5).setMinWidth(14);
		a4.getColumnModel().getColumn(5).setMaxWidth(14);
		a4.getColumnModel().getColumn(6).setResizable(false);
		a4.getColumnModel().getColumn(6).setPreferredWidth(14);
		a4.getColumnModel().getColumn(6).setMinWidth(14);
		a4.getColumnModel().getColumn(6).setMaxWidth(14);
		a4.getColumnModel().getColumn(7).setResizable(false);
		a4.getColumnModel().getColumn(7).setPreferredWidth(14);
		a4.getColumnModel().getColumn(7).setMinWidth(14);
		a4.getColumnModel().getColumn(7).setMaxWidth(14);
		a4.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(a4);

		c4 = new JTable();
		c4.setForeground(Color.BLACK);
		c4.setBackground(Color.WHITE);
		c4.setShowVerticalLines(false);
		c4.setEnabled(false);
		c4.setBounds(254, (Rounds.ENCRYPT == true) ? 482 : 500, 112, 15);
		c4.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.LTdata[Rounds.round - 1][13] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -6710874239981304748L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		c4.getColumnModel().getColumn(0).setResizable(false);
		c4.getColumnModel().getColumn(0).setPreferredWidth(14);
		c4.getColumnModel().getColumn(0).setMinWidth(14);
		c4.getColumnModel().getColumn(0).setMaxWidth(14);
		c4.getColumnModel().getColumn(1).setPreferredWidth(14);
		c4.getColumnModel().getColumn(1).setMinWidth(14);
		c4.getColumnModel().getColumn(1).setMaxWidth(14);
		c4.getColumnModel().getColumn(2).setResizable(false);
		c4.getColumnModel().getColumn(2).setPreferredWidth(14);
		c4.getColumnModel().getColumn(2).setMinWidth(14);
		c4.getColumnModel().getColumn(2).setMaxWidth(14);
		c4.getColumnModel().getColumn(3).setResizable(false);
		c4.getColumnModel().getColumn(3).setPreferredWidth(14);
		c4.getColumnModel().getColumn(3).setMinWidth(14);
		c4.getColumnModel().getColumn(3).setMaxWidth(14);
		c4.getColumnModel().getColumn(4).setResizable(false);
		c4.getColumnModel().getColumn(4).setPreferredWidth(14);
		c4.getColumnModel().getColumn(4).setMinWidth(14);
		c4.getColumnModel().getColumn(4).setMaxWidth(14);
		c4.getColumnModel().getColumn(5).setResizable(false);
		c4.getColumnModel().getColumn(5).setPreferredWidth(14);
		c4.getColumnModel().getColumn(5).setMinWidth(14);
		c4.getColumnModel().getColumn(5).setMaxWidth(14);
		c4.getColumnModel().getColumn(6).setResizable(false);
		c4.getColumnModel().getColumn(6).setPreferredWidth(14);
		c4.getColumnModel().getColumn(6).setMinWidth(14);
		c4.getColumnModel().getColumn(6).setMaxWidth(14);
		c4.getColumnModel().getColumn(7).setResizable(false);
		c4.getColumnModel().getColumn(7).setPreferredWidth(14);
		c4.getColumnModel().getColumn(7).setMinWidth(14);
		c4.getColumnModel().getColumn(7).setMaxWidth(14);
		c4.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(c4);

		a = new JTable();
		a.setForeground(Color.BLACK);
		a.setBackground(Color.WHITE);
		a.setShowVerticalLines(false);
		a.setEnabled(false);
		a.setBounds(10, 25, 112, 14);
		a.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] {
				((Rounds.ENCRYPT == true) ? data.afterSBOX[Rounds.round - 1][0] : data.afterXOR[Rounds.round][0]) }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -680341300426334675L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		a.getColumnModel().getColumn(0).setResizable(false);
		a.getColumnModel().getColumn(0).setPreferredWidth(14);
		a.getColumnModel().getColumn(0).setMinWidth(14);
		a.getColumnModel().getColumn(0).setMaxWidth(14);
		a.getColumnModel().getColumn(1).setPreferredWidth(14);
		a.getColumnModel().getColumn(1).setMinWidth(14);
		a.getColumnModel().getColumn(1).setMaxWidth(14);
		a.getColumnModel().getColumn(2).setResizable(false);
		a.getColumnModel().getColumn(2).setPreferredWidth(14);
		a.getColumnModel().getColumn(2).setMinWidth(14);
		a.getColumnModel().getColumn(2).setMaxWidth(14);
		a.getColumnModel().getColumn(3).setResizable(false);
		a.getColumnModel().getColumn(3).setPreferredWidth(14);
		a.getColumnModel().getColumn(3).setMinWidth(14);
		a.getColumnModel().getColumn(3).setMaxWidth(14);
		a.getColumnModel().getColumn(4).setResizable(false);
		a.getColumnModel().getColumn(4).setPreferredWidth(14);
		a.getColumnModel().getColumn(4).setMinWidth(14);
		a.getColumnModel().getColumn(4).setMaxWidth(14);
		a.getColumnModel().getColumn(5).setResizable(false);
		a.getColumnModel().getColumn(5).setPreferredWidth(14);
		a.getColumnModel().getColumn(5).setMinWidth(14);
		a.getColumnModel().getColumn(5).setMaxWidth(14);
		a.getColumnModel().getColumn(6).setResizable(false);
		a.getColumnModel().getColumn(6).setPreferredWidth(14);
		a.getColumnModel().getColumn(6).setMinWidth(14);
		a.getColumnModel().getColumn(6).setMaxWidth(14);
		a.getColumnModel().getColumn(7).setResizable(false);
		a.getColumnModel().getColumn(7).setPreferredWidth(14);
		a.getColumnModel().getColumn(7).setMinWidth(14);
		a.getColumnModel().getColumn(7).setMaxWidth(14);
		a.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(a);

		b = new JTable();
		b.setForeground(Color.BLACK);
		b.setBackground(Color.WHITE);
		b.setShowVerticalLines(false);
		b.setEnabled(false);
		b.setBounds(132, 25, 112, 14);
		b.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] {
				((Rounds.ENCRYPT == true) ? data.afterSBOX[Rounds.round - 1][1] : data.afterXOR[Rounds.round][1]) }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -4554045877095065857L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		b.getColumnModel().getColumn(0).setResizable(false);
		b.getColumnModel().getColumn(0).setPreferredWidth(14);
		b.getColumnModel().getColumn(0).setMinWidth(14);
		b.getColumnModel().getColumn(0).setMaxWidth(14);
		b.getColumnModel().getColumn(1).setPreferredWidth(14);
		b.getColumnModel().getColumn(1).setMinWidth(14);
		b.getColumnModel().getColumn(1).setMaxWidth(14);
		b.getColumnModel().getColumn(2).setResizable(false);
		b.getColumnModel().getColumn(2).setPreferredWidth(14);
		b.getColumnModel().getColumn(2).setMinWidth(14);
		b.getColumnModel().getColumn(2).setMaxWidth(14);
		b.getColumnModel().getColumn(3).setResizable(false);
		b.getColumnModel().getColumn(3).setPreferredWidth(14);
		b.getColumnModel().getColumn(3).setMinWidth(14);
		b.getColumnModel().getColumn(3).setMaxWidth(14);
		b.getColumnModel().getColumn(4).setResizable(false);
		b.getColumnModel().getColumn(4).setPreferredWidth(14);
		b.getColumnModel().getColumn(4).setMinWidth(14);
		b.getColumnModel().getColumn(4).setMaxWidth(14);
		b.getColumnModel().getColumn(5).setResizable(false);
		b.getColumnModel().getColumn(5).setPreferredWidth(14);
		b.getColumnModel().getColumn(5).setMinWidth(14);
		b.getColumnModel().getColumn(5).setMaxWidth(14);
		b.getColumnModel().getColumn(6).setResizable(false);
		b.getColumnModel().getColumn(6).setPreferredWidth(14);
		b.getColumnModel().getColumn(6).setMinWidth(14);
		b.getColumnModel().getColumn(6).setMaxWidth(14);
		b.getColumnModel().getColumn(7).setResizable(false);
		b.getColumnModel().getColumn(7).setPreferredWidth(14);
		b.getColumnModel().getColumn(7).setMinWidth(14);
		b.getColumnModel().getColumn(7).setMaxWidth(14);
		b.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(b);

		c = new JTable();
		c.setForeground(Color.BLACK);
		c.setBackground(Color.WHITE);
		c.setShowVerticalLines(false);
		c.setEnabled(false);
		c.setBounds(254, 25, 112, 15);
		c.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] {
				((Rounds.ENCRYPT == true) ? data.afterSBOX[Rounds.round - 1][2] : data.afterXOR[Rounds.round][2]) }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -5659969924511705678L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		c.getColumnModel().getColumn(0).setResizable(false);
		c.getColumnModel().getColumn(0).setPreferredWidth(14);
		c.getColumnModel().getColumn(0).setMinWidth(14);
		c.getColumnModel().getColumn(0).setMaxWidth(14);
		c.getColumnModel().getColumn(1).setPreferredWidth(14);
		c.getColumnModel().getColumn(1).setMinWidth(14);
		c.getColumnModel().getColumn(1).setMaxWidth(14);
		c.getColumnModel().getColumn(2).setResizable(false);
		c.getColumnModel().getColumn(2).setPreferredWidth(14);
		c.getColumnModel().getColumn(2).setMinWidth(14);
		c.getColumnModel().getColumn(2).setMaxWidth(14);
		c.getColumnModel().getColumn(3).setResizable(false);
		c.getColumnModel().getColumn(3).setPreferredWidth(14);
		c.getColumnModel().getColumn(3).setMinWidth(14);
		c.getColumnModel().getColumn(3).setMaxWidth(14);
		c.getColumnModel().getColumn(4).setResizable(false);
		c.getColumnModel().getColumn(4).setPreferredWidth(14);
		c.getColumnModel().getColumn(4).setMinWidth(14);
		c.getColumnModel().getColumn(4).setMaxWidth(14);
		c.getColumnModel().getColumn(5).setResizable(false);
		c.getColumnModel().getColumn(5).setPreferredWidth(14);
		c.getColumnModel().getColumn(5).setMinWidth(14);
		c.getColumnModel().getColumn(5).setMaxWidth(14);
		c.getColumnModel().getColumn(6).setResizable(false);
		c.getColumnModel().getColumn(6).setPreferredWidth(14);
		c.getColumnModel().getColumn(6).setMinWidth(14);
		c.getColumnModel().getColumn(6).setMaxWidth(14);
		c.getColumnModel().getColumn(7).setResizable(false);
		c.getColumnModel().getColumn(7).setPreferredWidth(14);
		c.getColumnModel().getColumn(7).setMinWidth(14);
		c.getColumnModel().getColumn(7).setMaxWidth(14);
		c.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(c);

		d = new JTable();
		d.setForeground(Color.BLACK);
		d.setBackground(Color.WHITE);
		d.setShowVerticalLines(false);
		d.setEnabled(false);
		d.setBounds(376, 25, 112, 15);
		d.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] {
				((Rounds.ENCRYPT == true) ? data.afterSBOX[Rounds.round - 1][3] : data.afterXOR[Rounds.round][3]) }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -9082952276380342842L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		d.getColumnModel().getColumn(0).setResizable(false);
		d.getColumnModel().getColumn(0).setPreferredWidth(14);
		d.getColumnModel().getColumn(0).setMinWidth(14);
		d.getColumnModel().getColumn(0).setMaxWidth(14);
		d.getColumnModel().getColumn(1).setPreferredWidth(14);
		d.getColumnModel().getColumn(1).setMinWidth(14);
		d.getColumnModel().getColumn(1).setMaxWidth(14);
		d.getColumnModel().getColumn(2).setResizable(false);
		d.getColumnModel().getColumn(2).setPreferredWidth(14);
		d.getColumnModel().getColumn(2).setMinWidth(14);
		d.getColumnModel().getColumn(2).setMaxWidth(14);
		d.getColumnModel().getColumn(3).setResizable(false);
		d.getColumnModel().getColumn(3).setPreferredWidth(14);
		d.getColumnModel().getColumn(3).setMinWidth(14);
		d.getColumnModel().getColumn(3).setMaxWidth(14);
		d.getColumnModel().getColumn(4).setResizable(false);
		d.getColumnModel().getColumn(4).setPreferredWidth(14);
		d.getColumnModel().getColumn(4).setMinWidth(14);
		d.getColumnModel().getColumn(4).setMaxWidth(14);
		d.getColumnModel().getColumn(5).setResizable(false);
		d.getColumnModel().getColumn(5).setPreferredWidth(14);
		d.getColumnModel().getColumn(5).setMinWidth(14);
		d.getColumnModel().getColumn(5).setMaxWidth(14);
		d.getColumnModel().getColumn(6).setResizable(false);
		d.getColumnModel().getColumn(6).setPreferredWidth(14);
		d.getColumnModel().getColumn(6).setMinWidth(14);
		d.getColumnModel().getColumn(6).setMaxWidth(14);
		d.getColumnModel().getColumn(7).setResizable(false);
		d.getColumnModel().getColumn(7).setPreferredWidth(14);
		d.getColumnModel().getColumn(7).setMinWidth(14);
		d.getColumnModel().getColumn(7).setMaxWidth(14);
		d.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(d);

		JButton btnInitialPermutation = new JButton("Initial permutation");
		btnInitialPermutation.setBounds(10, (Rounds.ENCRYPT == true) ? 524 : 530, 478, 23);
		btnInitialPermutation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				IP ip = new IP(2);
				ip.frmInitalPermutationPanel.setVisible(true);
			}
		});
		frmLinearTransformation.getContentPane().add(btnInitialPermutation);

		JButton btnNewButton = new JButton("Final permutation");
		btnNewButton.setBounds(10, 70, 478, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FP fp = new FP((Rounds.ENCRYPT == true) ? 2 : 3);
				fp.frmInitalPermutationTable.setVisible(true);
			}
		});
		frmLinearTransformation.getContentPane().add(btnNewButton);

		af = new JTable();
		af.setForeground(Color.BLACK);
		af.setBackground(Color.WHITE);
		af.setShowVerticalLines(false);
		af.setEnabled(false);
		af.setBounds(10, 570, 112, 15);
		af.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.afterLT[Rounds.round - 1][0] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -8099257524106676157L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		af.getColumnModel().getColumn(0).setResizable(false);
		af.getColumnModel().getColumn(0).setPreferredWidth(14);
		af.getColumnModel().getColumn(0).setMinWidth(14);
		af.getColumnModel().getColumn(0).setMaxWidth(14);
		af.getColumnModel().getColumn(1).setPreferredWidth(14);
		af.getColumnModel().getColumn(1).setMinWidth(14);
		af.getColumnModel().getColumn(1).setMaxWidth(14);
		af.getColumnModel().getColumn(2).setResizable(false);
		af.getColumnModel().getColumn(2).setPreferredWidth(14);
		af.getColumnModel().getColumn(2).setMinWidth(14);
		af.getColumnModel().getColumn(2).setMaxWidth(14);
		af.getColumnModel().getColumn(3).setResizable(false);
		af.getColumnModel().getColumn(3).setPreferredWidth(14);
		af.getColumnModel().getColumn(3).setMinWidth(14);
		af.getColumnModel().getColumn(3).setMaxWidth(14);
		af.getColumnModel().getColumn(4).setResizable(false);
		af.getColumnModel().getColumn(4).setPreferredWidth(14);
		af.getColumnModel().getColumn(4).setMinWidth(14);
		af.getColumnModel().getColumn(4).setMaxWidth(14);
		af.getColumnModel().getColumn(5).setResizable(false);
		af.getColumnModel().getColumn(5).setPreferredWidth(14);
		af.getColumnModel().getColumn(5).setMinWidth(14);
		af.getColumnModel().getColumn(5).setMaxWidth(14);
		af.getColumnModel().getColumn(6).setResizable(false);
		af.getColumnModel().getColumn(6).setPreferredWidth(14);
		af.getColumnModel().getColumn(6).setMinWidth(14);
		af.getColumnModel().getColumn(6).setMaxWidth(14);
		af.getColumnModel().getColumn(7).setResizable(false);
		af.getColumnModel().getColumn(7).setPreferredWidth(14);
		af.getColumnModel().getColumn(7).setMinWidth(14);
		af.getColumnModel().getColumn(7).setMaxWidth(14);
		af.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(af);

		bf = new JTable();
		bf.setForeground(Color.BLACK);
		bf.setBackground(Color.WHITE);
		bf.setShowVerticalLines(false);
		bf.setEnabled(false);
		bf.setBounds(132, 570, 112, 15);
		bf.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.afterLT[Rounds.round - 1][1] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1924679557229565938L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bf.getColumnModel().getColumn(0).setResizable(false);
		bf.getColumnModel().getColumn(0).setPreferredWidth(14);
		bf.getColumnModel().getColumn(0).setMinWidth(14);
		bf.getColumnModel().getColumn(0).setMaxWidth(14);
		bf.getColumnModel().getColumn(1).setPreferredWidth(14);
		bf.getColumnModel().getColumn(1).setMinWidth(14);
		bf.getColumnModel().getColumn(1).setMaxWidth(14);
		bf.getColumnModel().getColumn(2).setResizable(false);
		bf.getColumnModel().getColumn(2).setPreferredWidth(14);
		bf.getColumnModel().getColumn(2).setMinWidth(14);
		bf.getColumnModel().getColumn(2).setMaxWidth(14);
		bf.getColumnModel().getColumn(3).setResizable(false);
		bf.getColumnModel().getColumn(3).setPreferredWidth(14);
		bf.getColumnModel().getColumn(3).setMinWidth(14);
		bf.getColumnModel().getColumn(3).setMaxWidth(14);
		bf.getColumnModel().getColumn(4).setResizable(false);
		bf.getColumnModel().getColumn(4).setPreferredWidth(14);
		bf.getColumnModel().getColumn(4).setMinWidth(14);
		bf.getColumnModel().getColumn(4).setMaxWidth(14);
		bf.getColumnModel().getColumn(5).setResizable(false);
		bf.getColumnModel().getColumn(5).setPreferredWidth(14);
		bf.getColumnModel().getColumn(5).setMinWidth(14);
		bf.getColumnModel().getColumn(5).setMaxWidth(14);
		bf.getColumnModel().getColumn(6).setResizable(false);
		bf.getColumnModel().getColumn(6).setPreferredWidth(14);
		bf.getColumnModel().getColumn(6).setMinWidth(14);
		bf.getColumnModel().getColumn(6).setMaxWidth(14);
		bf.getColumnModel().getColumn(7).setResizable(false);
		bf.getColumnModel().getColumn(7).setPreferredWidth(14);
		bf.getColumnModel().getColumn(7).setMinWidth(14);
		bf.getColumnModel().getColumn(7).setMaxWidth(14);
		bf.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(bf);

		cf = new JTable();
		cf.setForeground(Color.BLACK);
		cf.setBackground(Color.WHITE);
		cf.setShowVerticalLines(false);
		cf.setEnabled(false);
		cf.setBounds(254, 570, 112, 15);
		cf.setModel(new DefaultTableModel(SerpentData.intToObject(new int[] { data.afterLT[Rounds.round - 1][2] }),
				new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 4759743333185947716L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		cf.getColumnModel().getColumn(0).setResizable(false);
		cf.getColumnModel().getColumn(0).setPreferredWidth(14);
		cf.getColumnModel().getColumn(0).setMinWidth(14);
		cf.getColumnModel().getColumn(0).setMaxWidth(14);
		cf.getColumnModel().getColumn(1).setPreferredWidth(14);
		cf.getColumnModel().getColumn(1).setMinWidth(14);
		cf.getColumnModel().getColumn(1).setMaxWidth(14);
		cf.getColumnModel().getColumn(2).setResizable(false);
		cf.getColumnModel().getColumn(2).setPreferredWidth(14);
		cf.getColumnModel().getColumn(2).setMinWidth(14);
		cf.getColumnModel().getColumn(2).setMaxWidth(14);
		cf.getColumnModel().getColumn(3).setResizable(false);
		cf.getColumnModel().getColumn(3).setPreferredWidth(14);
		cf.getColumnModel().getColumn(3).setMinWidth(14);
		cf.getColumnModel().getColumn(3).setMaxWidth(14);
		cf.getColumnModel().getColumn(4).setResizable(false);
		cf.getColumnModel().getColumn(4).setPreferredWidth(14);
		cf.getColumnModel().getColumn(4).setMinWidth(14);
		cf.getColumnModel().getColumn(4).setMaxWidth(14);
		cf.getColumnModel().getColumn(5).setResizable(false);
		cf.getColumnModel().getColumn(5).setPreferredWidth(14);
		cf.getColumnModel().getColumn(5).setMinWidth(14);
		cf.getColumnModel().getColumn(5).setMaxWidth(14);
		cf.getColumnModel().getColumn(6).setResizable(false);
		cf.getColumnModel().getColumn(6).setPreferredWidth(14);
		cf.getColumnModel().getColumn(6).setMinWidth(14);
		cf.getColumnModel().getColumn(6).setMaxWidth(14);
		cf.getColumnModel().getColumn(7).setResizable(false);
		cf.getColumnModel().getColumn(7).setPreferredWidth(14);
		cf.getColumnModel().getColumn(7).setMinWidth(14);
		cf.getColumnModel().getColumn(7).setMaxWidth(14);
		cf.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(cf);
		df.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmLinearTransformation.getContentPane().add(df);

		label13 = new JLabel((Rounds.ENCRYPT == true) ? "<<<13" : ">>>22");
		label13.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				labelToolTip(label13, (Rounds.ENCRYPT == true)?13:22, 0, 4);

			}
		});
		label13.setBounds(43, (Rounds.ENCRYPT == true) ? 159 : 170, 46, 14);
		frmLinearTransformation.getContentPane().add(label13);

		label = new JLabel((Rounds.ENCRYPT == true) ? "<<<3" : ">>>5");
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				labelToolTip(label, (Rounds.ENCRYPT == true)?3:5, 2, 5);
			}
		});

		label.setBounds(290, (Rounds.ENCRYPT == true) ? 159 : 170, 46, 14);
		frmLinearTransformation.getContentPane().add(label);

		label_1 = new JLabel("<<3");
		label_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				String toolTipString = "<html> <code>";
				toolTipString += String
						.format("%32s",
								Integer.toBinaryString(
										data.LTdata[Rounds.round - 1][((Rounds.ENCRYPT == true) ? 4 : 10)]))
						.replace(' ', '0') + "<br> &lt&lt 3 <br>";
				toolTipString += String
						.format("%32s",
								Integer.toBinaryString(
										data.LTdata[Rounds.round - 1][((Rounds.ENCRYPT == true) ? 4 : 10)] << 3))
						.replace(' ', '0') + "<br></code></html>";
				label_1.setToolTipText(toolTipString);
			}
		});
		label_1.setBounds(130, (Rounds.ENCRYPT == true) ? 300 : 330, 46, 14);
		frmLinearTransformation.getContentPane().add(label_1);

		label_3 = new JLabel("<<7");
		label_3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				String toolTipString = "<html> <code>";
				toolTipString += String
						.format("%32s",
								Integer.toBinaryString(data.LTdata[Rounds.round - 1][((Rounds.ENCRYPT == true) ? 8 : 1)]))
						.replace(' ', '0') + "<br> &lt&lt 7 <br>";
				toolTipString += String
						.format("%32s",
								Integer.toBinaryString(
										data.LTdata[Rounds.round - 1][((Rounds.ENCRYPT == true) ? 8 : 1)] << 7))
						.replace(' ', '0') + "<br></code></html>";
				label_3.setToolTipText(toolTipString);
			}
		});
		label_3.setBounds(226, (Rounds.ENCRYPT == true) ? 362 : 225, 46, 14);
		frmLinearTransformation.getContentPane().add(label_3);

		label_4 = new JLabel((Rounds.ENCRYPT == true) ? "<<<7" : ">>>7");
		label_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (Rounds.ENCRYPT)
					labelToolTip(label_4, 7, 7, 9);
				else
					labelToolTip(label_4, 7, 3, 7);
			}
		});
		label_4.setBounds(409, (Rounds.ENCRYPT == true) ? 349 : 275, 46, 14);
		frmLinearTransformation.getContentPane().add(label_4);

		label_6 = new JLabel((Rounds.ENCRYPT == true) ? "<<<22" : ">>>3");
		label_6.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				labelToolTip(label_6, (Rounds.ENCRYPT == true)?22:3, 11, 13);
			}
		});
		label_6.setBounds(289, (Rounds.ENCRYPT == true) ? 458 : 468, 46, 14);
		frmLinearTransformation.getContentPane().add(label_6);

		label_2 = new JLabel((Rounds.ENCRYPT == true) ? "<<<1" : ">>>1");
		label_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				if (Rounds.ENCRYPT)
					labelToolTip(label_2, 1, 6, 8);
				else
					labelToolTip(label_2, 1, 1, 6);

			}
		});
		label_2.setBounds(164, (Rounds.ENCRYPT == true) ? 332 : 273, 46, 14);
		frmLinearTransformation.getContentPane().add(label_2);

		label_5 = new JLabel((Rounds.ENCRYPT == true) ? "<<<5" : ">>>13");
		label_5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				labelToolTip(label_5, (Rounds.ENCRYPT == true)?5:13, 10, 12);
			}
		});
		label_5.setBounds(43, (Rounds.ENCRYPT == true) ? 444 : 468, 46, 14);
		frmLinearTransformation.getContentPane().add(label_5);

		panel = new JPanel();
		panel.setBounds(0, 0, 502, 643);
		frmLinearTransformation.getContentPane().add(panel);
		frmLinearTransformation.getContentPane().setLayout(null);
		panel.setLayout(null);

		xor1 = new JLabel(" ");
		xor1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(Rounds.ENCRYPT)
					xorToolTip(xor1, 4, 1, 5, 6, 0);
				else
					xorToolTip(xor1, 10, 6, 11, 8, 0);
			}
		});
		xor1.setBounds(176, (Rounds.ENCRYPT)?227:393, 22, 20);
		panel.add(xor1);

		xor2 = new JLabel(" ");
		xor2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(Rounds.ENCRYPT)
					xorToolTip(xor2, 3, 5, 4, 7, 3);
				else
					xorToolTip(xor2, 11, 7, 10, 9, 3);
			}
		});

		xor2.setBounds(421, 350, 22, 26);
		panel.add(xor2);

		xor3 = new JLabel(" ");
		xor3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(Rounds.ENCRYPT)
					xorToolTip(xor3, 4, 8, 9, 10, 0);
				else
					xorToolTip(xor3, 4, 1, 3, 10, 0);
			}
		});
		xor3.setBounds(57, (Rounds.ENCRYPT)? 357 : 245, 16, 20);
		panel.add(xor3);

		xor4 = new JLabel(" ");
		xor4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(Rounds.ENCRYPT)
					xorToolTip(xor4, 5, 9, 8, 11, 7);
				else
					xorToolTip(xor4, 5, 3, 1, 11, 7);
			}
		});
		xor4.setBounds(305, (Rounds.ENCRYPT)?392:220, 16, 20);
		panel.add(xor4);

		ImageLabel = new JLabel("");
		ImageLabel.setBounds(-3, 5, 509, 633);
		ImageLabel.setIcon(
		new ImageIcon( ".\\images\\" + ((Rounds.ENCRYPT)?"LT1.JPG":"invLT.JPG")));
//		ImageLabel.setIcon(
//				new ImageIcon(((Rounds.ENCRYPT == true) ? "C:\\Users\\laza\\workspace\\Serpent\\images\\LT1.JPG"
//						: "C:\\Users\\laza\\workspace\\Serpent\\images\\invLT.JPG")));
		panel.add(ImageLabel);
	}
}
