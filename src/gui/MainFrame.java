package gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import crypto.Serpent;
import exceptions.KeyException;
import exceptions.RotationShiftException;
import exceptions.SBoxException;
import exceptions.TextException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JToolBar;

public class MainFrame {

	public static MainFrame mainFrame = new MainFrame();

	public static JFrame frmSerpant;

	private Serpent serpent = Serpent.getInstance();

	private JTextField keyTextField;
	private JTextField plainTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public InitialPermutation initialPermutationFrame;
	public Rounds roundFrame;
	public FinalPermutation finalPermutationFrame;

	public static void tableAlignCenter(JTable table){
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for(int i = 0; i < table.getColumnModel().getColumnCount(); i++)
		table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainFrame.frmSerpant.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	public static int[] HexStringToIntArray(String hexString) {
		if (hexString.length() % 8 != 0) {
			System.err.println("Must me divisible by 8 ,current size: " + hexString.length());
			System.exit(-1);
		}

		int[] result = new int[hexString.length() / 8];
		for (int i = 0, j = 0; i < hexString.length(); i += 8) {
			result[j++] = (int) (Long.valueOf(hexString.substring(i, i + 8), 16) & 0xFFFFFFFF);
		}
		return result;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmSerpant = new JFrame();
		frmSerpant.setFont(new Font("Verdana", Font.PLAIN, 13));
		frmSerpant.setResizable(false);
		frmSerpant.setTitle("Serpant");
		frmSerpant.setBounds(100, 100, 935, 346);
		frmSerpant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSerpant.getContentPane().setLayout(null);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblKey.setBounds(39, 70, 84, 25);
		frmSerpant.getContentPane().add(lblKey);

		keyTextField = new JTextField();
		keyTextField.setFont(new Font("Verdana", Font.PLAIN, 18));
		keyTextField.setText("12345678912345678912345678912345");
		keyTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = keyTextField.getText().length();
				if (length >= 64)
					e.consume();
				String s = "" + e.getKeyChar();

				boolean isHex = s.matches("^[0-9A-Fa-f]+$");
				if (!isHex)
					e.consume();

			}
		});
		keyTextField.setBounds(148, 66, 750, 40);
		frmSerpant.getContentPane().add(keyTextField);
		keyTextField.setColumns(10);

		JLabel lblPlainText = new JLabel("Plain text:");
		lblPlainText.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPlainText.setBounds(39, 140, 135, 25);
		frmSerpant.getContentPane().add(lblPlainText);

		plainTextField = new JTextField();
		plainTextField.setFont(new Font("Verdana", Font.PLAIN, 18));
		plainTextField.setText("12345678912345678912345678912345");
		plainTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = plainTextField.getText().length();
				if (length >= 32)
					e.consume();
				String s = "" + e.getKeyChar();
				boolean isHex = s.matches("^[0-9A-Fa-f]+$");
				if (!isHex)
					e.consume();
			}
		});
		plainTextField.setColumns(10);
		plainTextField.setBounds(148, 133, 750, 40);
		frmSerpant.getContentPane().add(plainTextField);

		JRadioButton rdbtnEncrypt = new JRadioButton("Encrypt");
		rdbtnEncrypt.setFont(new Font("Verdana", Font.PLAIN, 18));
		rdbtnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPlainText.setText("Plain text:");
			}
		});
		rdbtnEncrypt.setSelected(true);
		buttonGroup.add(rdbtnEncrypt);
		rdbtnEncrypt.setBounds(148, 199, 135, 25);
		frmSerpant.getContentPane().add(rdbtnEncrypt);

		JRadioButton rdbtnDecrypt = new JRadioButton("Decrypt");
		rdbtnDecrypt.setFont(new Font("Verdana", Font.PLAIN, 18));
		rdbtnDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblPlainText.setText("Cipher text:");
			}
		});
		buttonGroup.add(rdbtnDecrypt);
		rdbtnDecrypt.setBounds(148, 237, 135, 25);
		frmSerpant.getContentPane().add(rdbtnDecrypt);

		JButton start = new JButton("Start");
		start.setFont(new Font("Verdana", Font.PLAIN, 18));
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String textString = plainTextField.getText();
				String keyString = keyTextField.getText();

				if (textString.length() != 32) {
					JOptionPane.showMessageDialog(null, "Text area must be 32B lenght", "Error",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (keyString.length() != 32 && keyString.length() != 48 && keyString.length() != 64) {
					JOptionPane.showMessageDialog(null, "Key size must be 32B, 48B or 64B size", "Error",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				int[] text = HexStringToIntArray(textString);
				int[] key = HexStringToIntArray(keyString);

				Rounds.ENCRYPT = rdbtnEncrypt.isSelected();
				try {

					if (Rounds.ENCRYPT) {
						serpent.encrypt(text, key);
						Rounds.round = 1;
					} else {
						serpent.decrypt(text, key);
						Rounds.round = 32;
					}

					initialPermutationFrame = new InitialPermutation();
					roundFrame = new Rounds();
					initialPermutationFrame.frmInitialPermutation.setVisible(true);
					frmSerpant.setVisible(false);
					finalPermutationFrame = new FinalPermutation();

					// Object[][] o =
					// serpent.data.intToObject(serpent.data.text);
					// for(int i = 0; i < o.length; i++){
					// for(int j = 0; j < o[i].length; j++){
					// System.out.print((char)o[i][j] + " ");
					// } System.out.println();
					// }

				} catch (KeyException | SBoxException | RotationShiftException | TextException serpentException) {
					serpentException.printStackTrace();
				}

			}
		});
		start.setBounds(763, 232, 135, 35);
		frmSerpant.getContentPane().add(start);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 301, 994, 16);
		frmSerpant.getContentPane().add(toolBar);
		JLabel label = new JLabel("Lazar Vasic 2013/0298");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		toolBar.add(label);

	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}
}
