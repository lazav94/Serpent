package crypto;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Serpent2 {
	// CHECK if its better to do with byte[] or String ???
	// TODO made exception for all invalid states!

	private byte[] key;// = new byte[32];

	private byte[][] W = new byte[140][4];
	public byte[][] subKeys = new byte[33][16]; // 33keys x 128b (16B)

	private byte[] plainText = new byte[16];
	private byte[] chyperText = new byte[16];
	// private byte[] initalPermutation = new byte[16];
	private byte[][] cryptedText = new byte[32][16];

	public static byte S0[] = { 3, 8, 15, 1, 10, 6, 5, 11, 14, 13, 4, 2, 7, 0, 9, 12 };
	public static byte S1[] = { 15, 12, 2, 7, 9, 0, 5, 10, 1, 11, 14, 8, 6, 13, 3, 4 };
	public static byte S2[] = { 8, 6, 7, 9, 3, 12, 10, 15, 13, 1, 14, 4, 0, 11, 5, 2 };
	public static byte S3[] = { 0, 15, 11, 8, 12, 9, 6, 3, 13, 1, 2, 4, 10, 7, 5, 14 };
	public static byte S4[] = { 1, 15, 8, 3, 12, 0, 11, 6, 2, 5, 4, 10, 9, 14, 7, 13 };
	public static byte S5[] = { 15, 5, 2, 11, 4, 10, 9, 12, 0, 3, 14, 8, 13, 6, 7, 1 };
	public static byte S6[] = { 7, 2, 12, 5, 8, 4, 6, 11, 14, 9, 1, 15, 13, 3, 10, 0 };
	public static byte S7[] = { 1, 13, 15, 0, 14, 8, 2, 11, 7, 4, 12, 10, 9, 3, 5, 6 };

	public static byte iS0[] = { 13, 3, 11, 0, 10, 6, 5, 12, 1, 14, 4, 7, 15, 9, 8, 2 };
	public static byte iS1[] = { 5, 8, 2, 14, 15, 6, 12, 3, 11, 4, 7, 9, 1, 13, 10, 0 };
	public static byte iS2[] = { 12, 9, 15, 4, 11, 14, 1, 2, 0, 3, 6, 13, 5, 8, 10, 7 };
	public static byte iS3[] = { 0, 9, 10, 7, 11, 14, 6, 13, 3, 5, 12, 2, 4, 8, 15, 1 };
	public static byte iS4[] = { 5, 0, 8, 3, 10, 9, 7, 14, 2, 12, 11, 6, 4, 15, 13, 1 };
	public static byte iS5[] = { 8, 15, 2, 9, 4, 1, 13, 14, 11, 6, 5, 3, 7, 12, 10, 0 };
	public static byte iS6[] = { 15, 10, 1, 13, 5, 3, 6, 0, 4, 9, 14, 7, 2, 12, 8, 11 };
	public static byte iS7[] = { 3, 0, 6, 13, 9, 14, 15, 8, 5, 12, 11, 7, 10, 1, 4, 2 };

	public static final int IP[] = { 0, 32, 64, 96, 1, 33, 65, 97, 2, 34, 66, 98, 3, 35, 67, 99, 4, 36, 68, 100, 5, 37,
			69, 101, 6, 38, 70, 102, 7, 39, 71, 103, 8, 40, 72, 104, 9, 41, 73, 105, 10, 42, 74, 106, 11, 43, 75, 107,
			12, 44, 76, 108, 13, 45, 77, 109, 14, 46, 78, 110, 15, 47, 79, 111, 16, 48, 80, 112, 17, 49, 81, 113, 18,
			50, 82, 114, 19, 51, 83, 115, 20, 52, 84, 116, 21, 53, 85, 117, 22, 54, 86, 118, 23, 55, 87, 119, 24, 56,
			88, 120, 25, 57, 89, 121, 26, 58, 90, 122, 27, 59, 91, 123, 28, 60, 92, 124, 29, 61, 93, 125, 30, 62, 94,
			126, 31, 63, 95, 127 };
	public static final int FP[] = { 0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80,
			84, 88, 92, 96, 100, 104, 108, 112, 116, 120, 124, 1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57,
			61, 65, 69, 73, 77, 81, 85, 89, 93, 97, 101, 105, 109, 113, 117, 121, 125, 2, 6, 10, 14, 18, 22, 26, 30, 34,
			38, 42, 46, 50, 54, 58, 62, 66, 70, 74, 78, 82, 86, 90, 94, 98, 102, 106, 110, 114, 118, 122, 126, 3, 7, 11,
			15, 19, 23, 27, 31, 35, 39, 43, 47, 51, 55, 59, 63, 67, 71, 75, 79, 83, 87, 91, 95, 99, 103, 107, 111, 115,
			119, 123, 127 };

	public static final int ROUNDS = 32;

	/** The fractional part of the golden ratio, (sqrt(5)+1)/2. */
	private static final int PHI = 0x9e3779b9;

	// TODO From textBox get keys
	// TODO Make button for radnom genereted keys

	private void keyAdjusment() {
		if (this.key.length < 16)
			System.err.println("Key must be atleast 128b");
		// TODO Throw exception
		else if (key.length < 32) {

			byte[] padding = new byte[32 - this.key.length];
			padding[0] = (byte) 0x80;
			for (int i = 1; i < 32 - key.length; i++)
				padding[i] = 0;

			byte[] temp = Arrays.copyOf(padding, 32);
			System.arraycopy(this.key, 0, temp, padding.length, this.key.length);
			this.key = temp;
		}
	}

	public void makePreKeys() {
		for (int i = 0; i < 8; i++)
			W[i] = Arrays.copyOfRange(this.key, i * 4, i * 4 + 4);

		System.out.println("W keys:");
		for (int i = 0; i < 8; i++) {
			System.out.print("W[" + i + "] = ");
			for (int j = 0; j < W[i].length; j++) {
				System.out.print(W[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	private Serpent2() {

		// this.key = ("e04fd020ea3a6910a2d808002b30309d").getBytes();
		/*
		 * this.key = ("e04fd020ea3a6910a2d80809d").getBytes();
		 * System.out.println("Key size: " + 8 * key.length + " bits");
		 * printBlock(key);
		 * 
		 * this.plainText = "3456dA1011c13141".getBytes();
		 * System.out.println("PlainText size: " + 8 * plainText.length +
		 * " bits");
		 */
		// this.cryptedText = "".getBytes();

		this.key = hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000");
		
		System.out.println("Key size: " + 8 * key.length + " bits");
		printBlock(key);

		this.plainText = hexStringToByteArray("00000000000000000000000000000000");

		System.out.println("PlainText size: " + 8 * plainText.length + " bits");

	}

	
	private static Serpent2 serpent = null;

	public static Serpent2 getInstance() {
		if (serpent == null)
			serpent = new Serpent2();
		return serpent;
	}

	private void printBlock(byte[] block) {

		// int i = 0;
		// for (byte b : block) {
		// System.out.print(b + " ");
		// if (i++ % 32 == 31)
		// System.out.println();
		// }

		System.out.println(bytesToHex(block));
	}

	public byte[] initialPermutation(byte[] input) {

		String binaryString = "";

		for (int i = 0; i < 16; i++) {
			String s = (Integer.toBinaryString(input[i]));

			if (s.length() < 8) {
				for (int k = 0; k < 8 - s.length(); k++)
					binaryString += "0";

			} else if (s.length() > 8) {
				s = s.substring(s.length() - 8, s.length());
			}

			// System.out.println(s);
			binaryString += s;

		}
		// System.out.println(binaryString);

		// System.out.println("Plain text: ");
		// System.out.println(binaryString);

		StringBuilder initalPermutation = new StringBuilder(binaryString);

		for (int i = 1; i < 127; i++)
			initalPermutation.setCharAt(i * 32 % 127, binaryString.charAt(i));

		// System.out.println("After Initial permutation(IP): ");
		// System.out.println(initalPermutation);

		byte[] result = new byte[16];
		for (int i = 0; i < 16; i++) {
			String s = initalPermutation.substring(i * 8, i * 8 + 8);
			result[i] = (byte) Integer.parseInt(s, 2);
		}

		return result;

	}

	public byte[] finalPermutation(byte[] input) {

		String binaryString = "";

		for (int i = 0; i < 16; i++) {
			String s = (Integer.toBinaryString(input[i]));

			if (s.length() < 8) {
				for (int k = 0; k < 8 - s.length(); k++)
					binaryString += "0";

			} else if (s.length() > 8) {
				s = s.substring(s.length() - 8, s.length());
			}

			// System.out.println(s);
			binaryString += s;

		}
		// System.out.println(binaryString);

		StringBuilder temp = new StringBuilder(binaryString);

		for (int i = 1; i < 127; i++)
			temp.setCharAt(i * 4 % 127, binaryString.charAt(i));

		// System.out.println("After Final permutation(FP): ");
		// System.out.println(temp);

		byte[] result = new byte[16];
		for (int i = 0; i < 16; i++) {
			String s = temp.substring(i * 8, i * 8 + 8);
			result[i] = (byte) Integer.parseInt(s, 2);
		}

		return result;

	}

	public byte[] SBox(byte[] b, int sbox) {
		byte[] result = new byte[b.length];
		for (int i = 0; i < b.length; i++) {
			switch (sbox) {
			case 0:
				result[i] = S0[(b[i] & 0x0F)];
				result[i] |= S0[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 1:
				result[i] = S1[b[i] & 0x0F];
				result[i] |= S1[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 2:
				result[i] = S2[b[i] & 0x0F];
				result[i] |= S2[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 3:
				result[i] = S3[b[i] & 0x0F];
				result[i] |= S3[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 4:
				result[i] = S4[b[i] & 0x0F];
				result[i] |= S4[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 5:
				result[i] = S5[b[i] & 0x0F];
				result[i] |= S5[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 6:
				result[i] = S6[b[i] & 0x0F];
				result[i] |= S6[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 7:
				result[i] = S7[b[i] & 0x0F];
				result[i] |= S7[(b[i] & 0xF0) >> 4] << 4;
				break;

			default:
				System.err.println("Greska sbox");
				break;
			}

		}
		return result;
	}

	public int SBox(int value, int sbox) {
		int result = 0;
		// System.out.println("Bokx: " + sbox);
		switch (sbox) {
		case 0:
			result = S0[value];
			break;
		case 1:
			result = S1[value];
			break;
		case 2:
			result = S2[value];
			break;
		case 3:
			result = S3[value];
			break;
		case 4:
			result = S4[value];
			break;
		case 5:
			result = S5[value];
			break;
		case 6:
			result = S6[value];
			break;
		case 7:
			result = S7[value];
			break;

		default:
			System.err.println("Greska sbox" + sbox);
			break;
		}

		result &= 0x0F;
		return result;
	}

	public byte[] iSBox(byte[] b, int sbox) {
		byte[] result = new byte[b.length];
		for (int i = 0; i < b.length; i++) {
			switch (sbox) {
			case 0:
				result[i] = iS0[(b[i] & 0x0F)];
				result[i] |= iS0[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 1:
				result[i] = iS1[b[i] & 0x0F];
				result[i] |= iS1[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 2:
				result[i] = iS2[b[i] & 0x0F];
				result[i] |= iS2[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 3:
				result[i] = iS3[b[i] & 0x0F];
				result[i] |= iS3[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 4:
				result[i] = iS4[b[i] & 0x0F];
				result[i] |= iS4[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 5:
				result[i] = iS5[b[i] & 0x0F];
				result[i] |= iS5[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 6:
				result[i] = iS6[b[i] & 0x0F];
				result[i] |= iS6[(b[i] & 0xF0) >> 4] << 4;
				break;
			case 7:
				result[i] = iS7[b[i] & 0x0F];
				result[i] |= iS7[(b[i] & 0xF0) >> 4] << 4;
				break;

			default:
				System.err.println("Greska sbox");
				break;
			}

		}
		return result;
	}

	// TODO test these funciton
	private byte[] XOR(byte[] a, byte[] b) {
		byte[] result = new byte[Math.min(a.length, b.length)];
		for (int i = 0; i < result.length; i++)
			result[i] = (byte) (((int) a[i]) ^ ((int) b[i]));
		return result;
	}

	/*
	 * public static byte[] rotateLeft(BigInteger value, int shift) { // Note:
	 * shift must be positive, if necessary add checks.
	 * 
	 * BigInteger topBits = value.shiftRight(32 - shift); BigInteger mask =
	 * BigInteger.ONE.shiftLeft(32).subtract(BigInteger.ONE); byte[] result =
	 * value.shiftLeft(shift).or(topBits).and(mask).toByteArray(); byte[] array
	 * = new byte[4];
	 * 
	 * if (result.length != 4) { for (int i = 1; i < 5; i++) { array[i-1] =
	 * result[i]; } result = array; } return result;
	 * 
	 * }
	 */

	static BigInteger allOnes(int L) {
		return BigInteger.ZERO.setBit(L).subtract(BigInteger.ONE);
	}

	static byte[] rotateLeft(BigInteger n, int k) {
		int L = 32;

		byte[] result = n.shiftLeft(k).or(n.shiftRight(L - k)).and(allOnes(L)).toByteArray();

		byte[] array = new byte[4];

		if (result.length != 4) {
			for (int i = 1; i < 5; i++) {
				array[i - 1] = result[i];
			}
			result = array;
		}
		return result;
	}

	// TODO test these funciton
	public byte[] LeftRotate(byte[] byteArray, int pos) {
		// Predpostavka : ova fja radi samo za 4B = 32b

		if (pos < 1) // TODO Throws exception
			System.err.println("Pos must be positive");

		BigInteger bigInt = new BigInteger(byteArray);

		BigInteger shiftInt = bigInt.shiftLeft(pos);
		shiftInt = shiftInt.and(new BigInteger("FFFFFFFF", 16));

		long mask = 0;
		for (int i = 0; i < pos; i++)
			mask |= 1 << i;

		BigInteger rotated = bigInt.shiftRight(byteArray.length * 8 - pos);

		rotated = rotated.and(new BigInteger(Long.toString(mask)));
		shiftInt = shiftInt.or(rotated);

		byte[] shifted = shiftInt.toByteArray();
		if (shifted.length > 4) {
			byte[] temp = new byte[4];
			for (int i = 0; i < 4; i++)

				temp[i] = shifted[i + (shifted.length - 4)];
			shifted = temp;

		} else if (shifted.length < 4) {
			byte[] temp = new byte[4];
			for (int i = 0, j = 0; i < 4; i++) {
				if (i < 4 - shifted.length)
					temp[i] = 0x00;
				else
					temp[i] = shifted[j++];
			}
			shifted = temp;

		}
		return shifted;
	}

	public byte[] LeftShift(byte[] byteArray, int pos) {
		/*
		 * for(int i = 0; i < byteArray.length; i++)
		 * System.out.print(byteArray[i] + " "); System.out.println();
		 */
		BigInteger bigInt = new BigInteger(byteArray);
		BigInteger shiftInt = bigInt.shiftLeft(pos);

		shiftInt = shiftInt.and(new BigInteger("FFFFFFFF", 16));
		byte[] shifted = shiftInt.toByteArray();

		return shifted;
	}

	public byte[] RightRotate(byte[] byteArray, int pos) {
		// Predpostavka : ova fja radi samo za 4B = 32b

		if (pos < 1) // TODO Throws exception
			System.err.println("Pos must be positive");

		BigInteger bigInt = new BigInteger(byteArray);

		long mask = 0;
		for (int i = 0; i < pos; i++)
			mask |= 1 << i;

		BigInteger rotated = bigInt.and(new BigInteger(Long.toString(mask)));
		rotated = rotated.shiftLeft(32 - pos);

		BigInteger shiftInt = bigInt.shiftRight(pos);
		shiftInt = shiftInt.and(new BigInteger("FFFFFFFF", 16));

		shiftInt = shiftInt.or(rotated);

		byte[] shifted = shiftInt.toByteArray();

		if (shifted.length > 4) {
			byte[] temp = new byte[4];
			for (int i = 0; i < 4; i++)

				temp[i] = shifted[i + (shifted.length - 4)];
			shifted = temp;

		} else if (shifted.length < 4) {
			byte[] temp = new byte[4];
			for (int i = 0, j = 0; i < 4; i++) {
				if (i < 4 - shifted.length)
					temp[i] = 0x00;
				else
					temp[i] = shifted[j++];
			}
			shifted = temp;

		}

		return shifted;
	}

	public byte[] RightShift(byte[] byteArray, int pos) {
		/*
		 * for(int i = 0; i < byteArray.length; i++)
		 * System.out.print(byteArray[i] + " "); System.out.println();
		 */
		BigInteger bigInt = new BigInteger(byteArray);
		BigInteger shiftInt = bigInt.shiftRight(pos);

		shiftInt = shiftInt.and(new BigInteger("FFFFFFFF", 16));
		byte[] shifted = shiftInt.toByteArray();

		return shifted;
	}

	byte[] intToByteArray(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	int byteArrayToInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	private static int getBit(int x, int i) {
		return (x >>> i) & 0x01;
	}

	public void keySchedule() {

		System.out.println();
		for (int i = 8; i < 140; i++) {
			byte[] temp1 = XOR(XOR(W[i - 8], W[i - 5]), XOR(W[i - 3], W[i - 1]));

			// System.out.print("XOR1: ");
			// printBlock(temp1);

			temp1 = XOR(temp1, ByteBuffer.allocate(4).putInt(PHI ^ (i - 8)).array());

			// System.out.print("PHI:");printBlock(ByteBuffer.allocate(4).putInt(PHI
			// ^ 4).array());
			// System.out.print("PHI1: "); printBlock(temp1);

			W[i] = LeftRotate(temp1, 11);

			// W[i] = rotateLeft(new BigInteger(temp1), 11);

			System.out.print("W[" + i + "] = ");
			printBlock(W[i]);

		}

		int[] k = new int[132];
		int box = 3, a, b, c, d, in, out;
		for (int i = 0; i < 33; i++, box--) {
			if (box == -1)
				box = 7;

			a = byteArrayToInt(W[4 * i + 8]);
			b = byteArrayToInt(W[4 * i + 9]);
			c = byteArrayToInt(W[4 * i + 10]);
			d = byteArrayToInt(W[4 * i + 11]);
			// System.out.printf("%x %x %x %x\n", a, b, c, d);
			for (int j = 0; j < 32; j++) {
				in = getBit(a, j) | getBit(b, j) << 1 | getBit(c, j) << 2 | getBit(d, j) << 3;
				out = SBox((byte) in, box);

				// System.out.printf("%x %x %x\n", in, out, box);
				k[4 * i] |= getBit(out, 0) << j;
				k[4 * i + 1] |= getBit(out, 1) << j;
				k[4 * i + 2] |= getBit(out, 2) << j;
				k[4 * i + 3] |= getBit(out, 3) << j;
				// System.out.printf("%X %X %X %X\n", k[4 * i], k[4 * i + 1],
				// k[4 * i + 2], k[4 * i + 3]);

			}

			// System.out.printf("%d %X %X %X %X\n", i ,k[4 * i], k[4 * i + 1],
			// k[4 * i + 2], k[4 * i + 3]);
		}

		System.out.println("\nSubKeys:\n");

		int subKeysIndex = 0;
		for (int i = 0; i < 33; i++) {
			byte[] w = new byte[16];

			System.arraycopy(intToByteArray(k[4 * i]), 0, w, 0, 4);
			System.arraycopy(intToByteArray(k[i * 4 + 1]), 0, w, 4, 4);
			System.arraycopy(intToByteArray(k[i * 4 + 2]), 0, w, 8, 4);
			System.arraycopy(intToByteArray(k[i * 4 + 3]), 0, w, 12, 4);

			subKeys[subKeysIndex] = w;

			printBlock(subKeys[subKeysIndex++]);
		}
		System.out.println();

	}

	private byte[] linearTransformation(byte[] input) {

		byte[] w0 = Arrays.copyOfRange(input, 0, 4);
		byte[] w1 = Arrays.copyOfRange(input, 4, 8);
		byte[] w2 = Arrays.copyOfRange(input, 8, 12);
		byte[] w3 = Arrays.copyOfRange(input, 12, 16);
		// printBlock(w0); printBlock(w1); printBlock(w2); printBlock(w3);

		w0 = LeftRotate(w0, 13); // RotLeft(w0,13)
		w2 = LeftRotate(w2, 3); // RotLeft(w2, 3)

		w1 = XOR(XOR(w1, w0), w2); // w1 = w1 xor w0 xor w2
		w3 = XOR(XOR(w3, w2), LeftShift(w0, 3)); // w3 = w3 xor w2 xor
													// ShiftLeft(w0,3);

		w1 = LeftRotate(w1, 1); // w1 = RotLeft(w1,1);
		w3 = LeftRotate(w3, 7); // w3 = RotLeft(w3,7);

		w0 = XOR(XOR(w0, w1), w3); // w0 = w0 xor w1 xor w3;
		w2 = XOR(XOR(w2, w3), LeftShift(w1, 7)); // w2 = w2 xor w3 xor
													// ShiftLeft(w1,7);
		w0 = LeftRotate(w0, 5); // w0 = RotLeft(w0,5);
		w2 = LeftRotate(w2, 22); // w2 = RotLeft(w2,22);

		// System.out.println();
		// printBlock(w0); printBlock(w1); printBlock(w2); printBlock(w3);

		byte[] result = new byte[16];
		System.arraycopy(w0, 0, result, 0, 4);
		System.arraycopy(w1, 0, result, 4, 4);
		System.arraycopy(w2, 0, result, 8, 4);
		System.arraycopy(w3, 0, result, 12, 4);

		System.out.print("(" + result.length + ") ");
		// printBlock(result);

		return result;

	}

	private byte[] inversLinearTransformation(byte[] input) {

		byte[] w0 = Arrays.copyOfRange(input, 0, 4);
		byte[] w1 = Arrays.copyOfRange(input, 4, 8);
		byte[] w2 = Arrays.copyOfRange(input, 8, 12);
		byte[] w3 = Arrays.copyOfRange(input, 12, 16);
		// printBlock(w0); printBlock(w1); printBlock(w2); printBlock(w3);

		w0 = RightRotate(w0, 13); // RotLeft(w0,13)
		w2 = RightRotate(w2, 3); // RotLeft(w2, 3)

		w1 = XOR(XOR(w1, w0), w2); // w1 = w1 xor w0 xor w2
		w3 = XOR(XOR(w3, w2), RightShift(w0, 3)); // w3 = w3 xor w2 xor
													// ShiftLeft(w0,3);

		w1 = RightRotate(w1, 1); // w1 = RotLeft(w1,1);
		w3 = RightRotate(w3, 7); // w3 = RotLeft(w3,7);

		w0 = XOR(XOR(w0, w1), w3); // w0 = w0 xor w1 xor w3;
		w2 = XOR(XOR(w2, w3), RightShift(w1, 7)); // w2 = w2 xor w3 xor
													// ShiftLeft(w1,7);
		w0 = RightRotate(w0, 5); // w0 = RotLeft(w0,5);
		w2 = RightRotate(w2, 22); // w2 = RotLeft(w2,22);

		// System.out.println();
		// printBlock(w0); printBlock(w1); printBlock(w2); printBlock(w3);

		byte[] result = new byte[16];
		System.arraycopy(w0, 0, result, 0, 4);
		System.arraycopy(w1, 0, result, 4, 4);
		System.arraycopy(w2, 0, result, 8, 4);
		System.arraycopy(w3, 0, result, 12, 4);

		System.out.print("(" + result.length + ") ");
		// printBlock(result);

		return result;

	}

	public String doCrypto(byte[] input) {
		byte[] C = input; // C = input block
		for (int round = 0; round < 32; round++) {
			// C = C xor SubKey[round]
			// C = SBox[round mod 8](C)
			if (round == 31)
				break;
			// C = linearTransformation(C)
		}
		// C = C xor SubKey[32]
		return "";

	}

	public byte[] encrypt() {

		keyAdjusment();
		System.out.println("New key(size: " + 8 * key.length + "): ");
		printBlock(key);

		makePreKeys();

		keySchedule();

		//byte[] input = initialPermutation(plainText);
		byte[] input = plainText;
		for (int round = 0; round < 32; round++) {
			input = XOR(input, subKeys[round]);
			input = SBox(input, round % 8);
			printBlock(input);
			if (round == 31)
				break;
			input = linearTransformation(input);
			System.out.print(round + "  ");
			printBlock(input);
		}
		input = XOR(input, subKeys[32]);

		System.out.print("\n" + 31 + "  ");
		printBlock(input);
		System.out.println();

		input = finalPermutation(input);
		printBlock(input);
		chyperText = input;

		return chyperText;
	}

	public byte[] decrypt() {
		System.out.println(
				"\n===========================================\n==================DECRYPT==================\n===========================================\n");
		byte[] input = chyperText;
		/*
		 * printBlock(input); input = initialPermutation(input);
		 * printBlock(input); input = XOR(subKeys[32], input);
		 * printBlock(input);
		 * 
		 * int i = 0; for (int round = 31; round >= 0; round--) { input =
		 * iSBox(input, (round % 8)); input = XOR(input, subKeys[round]);
		 * printBlock(input); if (round == 31) continue; input =
		 * inversLinearTransformation(input); System.out.print(round + "  " );
		 * printBlock(input); i++; }
		 * 
		 * System.out.print("\n" + 31 + "  " ); printBlock(input);
		 * System.out.println();
		 * 
		 * input = finalPermutation(input); printBlock(input);
		 */
		return input;
	}
}
