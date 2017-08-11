package gui;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import crypto.SerpentData;
import java.awt.Font;

public class W {

	public JFrame frmWKeys;

	private SerpentData data = SerpentData.getInstance();

	/**
	 * Create the application.
	 */
	public W(int i) {
		initialize(i);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int i) {
		frmWKeys = new JFrame();
		frmWKeys.getContentPane().setForeground(Color.BLACK);
		frmWKeys.setForeground(Color.BLACK);
		frmWKeys.setBackground(Color.BLACK);
		frmWKeys.setResizable(false);
		frmWKeys.setType(Type.POPUP);
		frmWKeys.getContentPane().setBackground(SystemColor.menu);
		frmWKeys.setTitle("W keys");
		frmWKeys.setBounds(100, 100, 583, 410);
		frmWKeys.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		i = i + ((Rounds.lastSubKeyFlag == true) ? 4 : 0);
		frmWKeys.getContentPane().setLayout(null);

		JPanel HexPanel = new JPanel();
		HexPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hex", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		HexPanel.setBounds(126, 10, 135, 230);
		frmWKeys.getContentPane().add(HexPanel);
		HexPanel.setLayout(null);

		JLabel w0h = new JLabel(String.format("%08X", data.W[i - 8]));
		w0h.setFont(new Font("Verdana", Font.PLAIN, 13));
		w0h.setBounds(39, 11, 69, 25);
		HexPanel.add(w0h);

		JLabel w1h = new JLabel(String.format("%08X", data.W[i - 5]));
		w1h.setFont(new Font("Verdana", Font.PLAIN, 13));
		w1h.setBounds(39, 47, 69, 25);
		HexPanel.add(w1h);

		JLabel w2h = new JLabel(String.format("%08X", data.W[i - 3]));
		w2h.setFont(new Font("Verdana", Font.PLAIN, 13));
		w2h.setBounds(39, 83, 69, 25);
		HexPanel.add(w2h);

		JLabel w3h = new JLabel(String.format("%08X", data.W[i - 2]));
		w3h.setFont(new Font("Verdana", Font.PLAIN, 13));
		w3h.setBounds(39, 119, 69, 25);
		HexPanel.add(w3h);

		JLabel Ih = new JLabel(String.format("%08X", i));
		Ih.setFont(new Font("Verdana", Font.PLAIN, 13));
		Ih.setBounds(39, 155, 69, 25);
		HexPanel.add(Ih);

		JLabel PHIh = new JLabel("9E3779B9");
		PHIh.setFont(new Font("Verdana", Font.PLAIN, 13));
		PHIh.setBounds(39, 191, 86, 25);
		HexPanel.add(PHIh);

		JPanel BinaryPanel = new JPanel();
		BinaryPanel.setBorder(new TitledBorder(null, "Binary", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		BinaryPanel.setBounds(271, 10, 297, 230);
		frmWKeys.getContentPane().add(BinaryPanel);
		BinaryPanel.setLayout(null);

		JLabel w0b = new JLabel(String.format("%32s", Integer.toBinaryString(data.W[i - 8])).replace(' ', '0'));
		w0b.setFont(new Font("Verdana", Font.PLAIN, 13));
		w0b.setBounds(12, 11, 291, 25);
		BinaryPanel.add(w0b);

		JLabel w1b = new JLabel(String.format("%32s", Integer.toBinaryString(data.W[i - 5])).replace(' ', '0'));
		w1b.setFont(new Font("Verdana", Font.PLAIN, 13));
		w1b.setBounds(12, 47, 291, 25);
		BinaryPanel.add(w1b);

		JLabel w2b = new JLabel(String.format("%32s", Integer.toBinaryString(data.W[i - 3])).replace(' ', '0'));
		w2b.setFont(new Font("Verdana", Font.PLAIN, 13));
		w2b.setBounds(12, 83, 291, 25);
		BinaryPanel.add(w2b);

		JLabel w3b = new JLabel(String.format("%32s", Integer.toBinaryString(data.W[i - 1])).replace(' ', '0'));
		w3b.setFont(new Font("Verdana", Font.PLAIN, 13));
		w3b.setBounds(12, 119, 291, 25);
		BinaryPanel.add(w3b);

		JLabel Ib = new JLabel(String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0'));
		Ib.setFont(new Font("Verdana", Font.PLAIN, 13));
		Ib.setBounds(12, 155, 291, 25);
		BinaryPanel.add(Ib);

		JLabel PHIb = new JLabel(String.format("%32s", Integer.toBinaryString(0x9E3779B9)).replace(' ', '0'));
		PHIb.setFont(new Font("Verdana", Font.PLAIN, 13));
		PHIb.setBounds(12, 191, 291, 25);
		BinaryPanel.add(PHIb);

		JPanel ValuesPanel = new JPanel();
		ValuesPanel.setBorder(new TitledBorder(null, "Values", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ValuesPanel.setBounds(10, 10, 106, 230);
		frmWKeys.getContentPane().add(ValuesPanel);
		ValuesPanel.setLayout(null);

		JLabel w0 = new JLabel("W[" + (i - 8) + "]");
		w0.setFont(new Font("Verdana", Font.PLAIN, 13));
		w0.setBounds(27, 11, 62, 25);
		ValuesPanel.add(w0);

		JLabel w1 = new JLabel("W[" + (i - 5) + "]");
		w1.setFont(new Font("Verdana", Font.PLAIN, 13));
		w1.setBounds(27, 47, 62, 25);
		ValuesPanel.add(w1);

		JLabel w2 = new JLabel("W[" + (i - 3) + "]");
		w2.setFont(new Font("Verdana", Font.PLAIN, 13));
		w2.setBounds(27, 83, 62, 25);
		ValuesPanel.add(w2);

		JLabel w3 = new JLabel("W[" + (i - 1) + "]");
		w3.setFont(new Font("Verdana", Font.PLAIN, 13));
		w3.setBounds(27, 119, 62, 25);
		ValuesPanel.add(w3);

		JLabel I = new JLabel(" I");
		I.setFont(new Font("Verdana", Font.PLAIN, 13));
		I.setBounds(40, 155, 26, 25);
		ValuesPanel.add(I);

		JLabel PHI = new JLabel("\u03A6");
		PHI.setFont(new Font("Verdana", Font.PLAIN, 13));
		PHI.setBounds(40, 191, 26, 25);
		ValuesPanel.add(PHI);

		JPanel HexPanel2 = new JPanel();
		HexPanel2.setBorder(new TitledBorder(null, "\u03A3 ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		HexPanel2.setBounds(126, 251, 135, 54);
		frmWKeys.getContentPane().add(HexPanel2);
		HexPanel2.setLayout(null);

		JLabel sumHex = new JLabel(String.format("%08X", data.sumW[i]));
		sumHex.setFont(new Font("Verdana", Font.PLAIN, 13));
		sumHex.setBounds(39, 14, 86, 25);
		HexPanel2.add(sumHex);

		JPanel BinaryPanel2 = new JPanel();
		BinaryPanel2.setBorder(new TitledBorder(null, "\u03A3 ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		BinaryPanel2.setBounds(271, 251, 297, 54);
		frmWKeys.getContentPane().add(BinaryPanel2);
		BinaryPanel2.setLayout(null);

		
		JLabel sumBinary = new JLabel(String.format("%32s", Integer.toBinaryString(data.sumW[i])).replace(' ', '0'));
		sumBinary.setFont(new Font("Verdana", Font.PLAIN, 13));
		sumBinary.setBounds(10, 14, 293, 25);
		BinaryPanel2.add(sumBinary);

		JPanel HexPanel3 = new JPanel();
		HexPanel3.setLayout(null);
		HexPanel3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "<<< 11", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		HexPanel3.setBounds(126, 316, 135, 54);
		frmWKeys.getContentPane().add(HexPanel3);

		JLabel WHex = new JLabel(String.format("%08X", data.W[i]));
		WHex.setFont(new Font("Verdana", Font.PLAIN, 13));
		WHex.setBounds(39, 14, 86, 25);
		HexPanel3.add(WHex);

		JPanel BinaryPanel3 = new JPanel();
		BinaryPanel3.setLayout(null);
		BinaryPanel3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "<<< 11", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		BinaryPanel3.setBounds(271, 316, 297, 54);
		frmWKeys.getContentPane().add(BinaryPanel3);

		JLabel WBinary = new JLabel(String.format("%32s", Integer.toBinaryString(data.W[i])).replace(' ', '0'));
		WBinary.setFont(new Font("Verdana", Font.PLAIN, 13));
		WBinary.setBounds(10, 14, 293, 25);
		BinaryPanel3.add(WBinary);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 323, 106, 45);
		frmWKeys.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("W[" + i + "]");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 10, 66, 25);
		panel.add(lblNewLabel);
	}
}
