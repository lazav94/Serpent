package crypto;

import exceptions.KeyException;
import exceptions.RotationShiftException;
import exceptions.SBoxException;
import exceptions.TextException;

/**
 * @author Lazar Vasic
 *
 */
// TODO Use logger instead System.out
public class Serpent {

	private static boolean DEBUG = true;
	private static Serpent serpent = null;

	public SerpentData data = SerpentData.getInstance();

	public static Serpent getInstance() {
		if (serpent == null)
			serpent = new Serpent();
		return serpent;
	}

	private static final String encryptString = "===========================================\n==================ENCRYPT==================\n===========================================\n";
	private static final String decryptString = "\n===========================================\n==================DECRYPT==================\n===========================================\n";

	public static int little2Big(int value) {
		int b1 = (value >> 0) & 0xff;
		int b2 = (value >> 8) & 0xff;
		int b3 = (value >> 16) & 0xff;
		int b4 = (value >> 24) & 0xff;

		return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
	}

	public static int[] little2Big(int[] array) {
		for (int i = 0; i < array.length; i++)
			array[i] = little2Big(array[i]);
		return array;
	}

	public static final byte SBox[][] = { { 3, 8, 15, 1, 10, 6, 5, 11, 14, 13, 4, 2, 7, 0, 9, 12 },
			{ 15, 12, 2, 7, 9, 0, 5, 10, 1, 11, 14, 8, 6, 13, 3, 4 },
			{ 8, 6, 7, 9, 3, 12, 10, 15, 13, 1, 14, 4, 0, 11, 5, 2 },
			{ 0, 15, 11, 8, 12, 9, 6, 3, 13, 1, 2, 4, 10, 7, 5, 14 },
			{ 1, 15, 8, 3, 12, 0, 11, 6, 2, 5, 4, 10, 9, 14, 7, 13 },
			{ 15, 5, 2, 11, 4, 10, 9, 12, 0, 3, 14, 8, 13, 6, 7, 1 },
			{ 7, 2, 12, 5, 8, 4, 6, 11, 14, 9, 1, 15, 13, 3, 10, 0 },
			{ 1, 13, 15, 0, 14, 8, 2, 11, 7, 4, 12, 10, 9, 3, 5, 6 } };

	public static final byte iSBox[][] = { { 13, 3, 11, 0, 10, 6, 5, 12, 1, 14, 4, 7, 15, 9, 8, 2 },
			{ 5, 8, 2, 14, 15, 6, 12, 3, 11, 4, 7, 9, 1, 13, 10, 0 },
			{ 12, 9, 15, 4, 11, 14, 1, 2, 0, 3, 6, 13, 5, 8, 10, 7 },
			{ 0, 9, 10, 7, 11, 14, 6, 13, 3, 5, 12, 2, 4, 8, 15, 1 },
			{ 5, 0, 8, 3, 10, 9, 7, 14, 2, 12, 11, 6, 4, 15, 13, 1 },
			{ 8, 15, 2, 9, 4, 1, 13, 14, 11, 6, 5, 3, 7, 12, 10, 0 },
			{ 15, 10, 1, 13, 5, 3, 6, 0, 4, 9, 14, 7, 2, 12, 8, 11 },
			{ 3, 0, 6, 13, 9, 14, 15, 8, 5, 12, 11, 7, 10, 1, 4, 2 } };

	public static final byte IP[] = { 0, 32, 64, 96, 1, 33, 65, 97, 2, 34, 66, 98, 3, 35, 67, 99, 4, 36, 68, 100, 5, 37,
			69, 101, 6, 38, 70, 102, 7, 39, 71, 103, 8, 40, 72, 104, 9, 41, 73, 105, 10, 42, 74, 106, 11, 43, 75, 107,
			12, 44, 76, 108, 13, 45, 77, 109, 14, 46, 78, 110, 15, 47, 79, 111, 16, 48, 80, 112, 17, 49, 81, 113, 18,
			50, 82, 114, 19, 51, 83, 115, 20, 52, 84, 116, 21, 53, 85, 117, 22, 54, 86, 118, 23, 55, 87, 119, 24, 56,
			88, 120, 25, 57, 89, 121, 26, 58, 90, 122, 27, 59, 91, 123, 28, 60, 92, 124, 29, 61, 93, 125, 30, 62, 94,
			126, 31, 63, 95, 127 };
	public static final byte FP[] = { 0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80,
			84, 88, 92, 96, 100, 104, 108, 112, 116, 120, 124, 1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57,
			61, 65, 69, 73, 77, 81, 85, 89, 93, 97, 101, 105, 109, 113, 117, 121, 125, 2, 6, 10, 14, 18, 22, 26, 30, 34,
			38, 42, 46, 50, 54, 58, 62, 66, 70, 74, 78, 82, 86, 90, 94, 98, 102, 106, 110, 114, 118, 122, 126, 3, 7, 11,
			15, 19, 23, 27, 31, 35, 39, 43, 47, 51, 55, 59, 63, 67, 71, 75, 79, 83, 87, 91, 95, 99, 103, 107, 111, 115,
			119, 123, 127 };

	public static final int ROUNDS = 32;

	/** The fractional part of the golden ratio, <code>(sqrt(5)+1)/2.</code> */
	private static final int PHI = 0x9e3779b9;

	private static int getBit(int[] x, int i) {
		return (x[i / 32] >>> (i % 32)) & 0x01;
	}

	private static void setBit(int[] x, int i, int v) {
		if ((v & 0x01) == 1)
			x[i / 32] |= 1 << (i % 32); // set it
		else
			x[i / 32] &= ~(1 << (i % 32)); // clear it
	}

	private static int[] permutate(byte[] T, int[] x) {
		int[] result = new int[4];
		for (int i = 0; i < 128; i++)
			setBit(result, i, getBit(x, T[i] & 0x7F));
		return result;
	}
	
	
	

	public static int[] IP(int[] x) {
		return permutate(IP, x);
	}

	public static int[] FP(int[] x) {
		return permutate(FP, x);
	}

	private void makePreKeys() {

		int tempKey[] = new int[8];

		for (int i = 0; i < data.key.length + 1; i++) {
			if (i == data.key.length) {
				if (data.key.length == 8)
					break;
				tempKey[i] = 1;
			} else
				tempKey[i] = data.key[i];
			data.W[i] = tempKey[i];
		}

		if (data.key.length < 8)
			data.W[data.key.length] = 1;

		data.key = tempKey;

		if (DEBUG) {
			System.out.println("W keys:");
			for (int i = 0; i < 8; i++)
				System.out.printf("W[%d] = %08X\n", i, data.W[i]);
		}

	}

	public void toString(int[] block) {
		int i = 0;
		for (int b : block) {
			System.out.printf("%08X ", b);
			if (i++ % 8 == 7)
				System.out.println();
		}
	}

	public void toString(String string, int[] block) {
		System.out.print(string);
		int i = 0;
		for (int b : block) {
			System.out.printf("%08X ", b);
			if (i++ % 8 == 7)
				System.out.println();
		}
	}

	private int SBox(int value, int sbox) throws SBoxException {
		if (value < 0 || value > 15)
			throw new SBoxException(SBoxException.VALUE, value);
		if (sbox < 0 || sbox > 7)
			throw new SBoxException(SBoxException.SBOX, sbox);

		return SBox[sbox][value] & 0x0F;

	}

	private int[] SBox(int[] value, int sbox) throws SBoxException {
		int[] result = new int[value.length];
		for (int i = 0; i < value.length; i++) {
			result[i] = SBox((value[i] & 0x0000000F), sbox);
			result[i] |= SBox((value[i] & 0x000000F0) >>> (4 * 1), sbox) << 4;
			result[i] |= SBox((value[i] & 0x00000F00) >>> (4 * 2), sbox) << 8;
			result[i] |= SBox((value[i] & 0x0000F000) >>> (4 * 3), sbox) << 12;
			result[i] |= SBox((value[i] & 0x000F0000) >>> (4 * 4), sbox) << 16;
			result[i] |= SBox((value[i] & 0x00F00000) >>> (4 * 5), sbox) << 20;
			result[i] |= SBox((value[i] & 0x0F000000) >>> (4 * 6), sbox) << 24;
			result[i] |= SBox((value[i] & 0xF0000000) >>> (4 * 7), sbox) << 28;

		}

		return result;

	}

	private int iSBox(int value, int sbox) throws SBoxException {
		if (sbox < 0 || sbox > 7)
			throw new SBoxException(SBoxException.SBOX, sbox);
		if (value < 0 || value > 15)
			throw new SBoxException(SBoxException.VALUE, value);
		return iSBox[sbox][value] & 0x0F;

	}

	private int[] iSBox(int[] value, int sbox) throws SBoxException {
		int[] result = new int[value.length];
		for (int i = 0; i < value.length; i++) {
			result[i] = iSBox((value[i] & 0x0000000F), sbox);
			result[i] |= iSBox((value[i] & 0x000000F0) >>> (4 * 1), sbox) << 4;
			result[i] |= iSBox((value[i] & 0x00000F00) >>> (4 * 2), sbox) << 8;
			result[i] |= iSBox((value[i] & 0x0000F000) >>> (4 * 3), sbox) << 12;
			result[i] |= iSBox((value[i] & 0x000F0000) >>> (4 * 4), sbox) << 16;
			result[i] |= iSBox((value[i] & 0x00F00000) >>> (4 * 5), sbox) << 20;
			result[i] |= iSBox((value[i] & 0x0F000000) >>> (4 * 6), sbox) << 24;
			result[i] |= iSBox((value[i] & 0xF0000000) >>> (4 * 7), sbox) << 28;

		}

		return result;

	}

	private int[] XOR(int[] w2, int[] w3) {
		int[] result = new int[Math.min(w2.length, w3.length)];
		for (int i = 0; i < result.length; i++)
			result[i] = w2[i] ^ w3[i];
		return result;
	}

	private static int XOR(int w2, int w3) {
		return w2 ^ w3;
	}

	private static int LeftRotate(int value, int pos) throws RotationShiftException {
		if (pos < 1)
			throw new RotationShiftException(pos);
		return Integer.rotateLeft(value, pos);

	}

	private static int RightRotate(int value, int pos) throws RotationShiftException {
		if (pos < 1)
			throw new RotationShiftException(pos);
		return Integer.rotateRight(value, pos);

	}

	private static int LeftShift(int value, int pos) throws RotationShiftException {
		if (pos < 1)
			throw new RotationShiftException(pos);
		return value << pos;

	}

	@SuppressWarnings("unused")
	private static int RightShift(int value, int pos) throws RotationShiftException {
		if (pos < 1)
			throw new RotationShiftException(pos);
		return value >> pos;
	}


	private void keySchedule() throws SBoxException, RotationShiftException {

		if (DEBUG)
			System.out.println();
		for (int i = 8; i < 140; i++) {
			int temp1 = XOR(XOR(data.W[i - 8], data.W[i - 5]), XOR(data.W[i - 3], data.W[i - 1]));
			temp1 = XOR(temp1, PHI ^ (i - 8));

			data.W[i] = LeftRotate(temp1, 11);

			if (DEBUG) {
				System.out.print("W[" + i + "] = ");
				System.out.printf("%X\n", data.W[i]);
			}
		}

	
		
		if (DEBUG)
			System.out.println("\nSubKeys:");
		for (int i = 0,subKeysIndex = 0, box = 3; i < 33; i++, box--, subKeysIndex++) {
			if (box == -1)
				box = 7;


			data.subKeysBeforIP[subKeysIndex] = new int[]{ data.W[4 * i + 8], data.W[4 * i + 9], data.W[4 * i + 10] , data.W[4 * i + 11]};
		
			
			data.subKeysAfterIP[subKeysIndex] = IP(data.subKeysBeforIP[subKeysIndex]);
			
			
			data.subKeys[subKeysIndex] = SBox(data.subKeysAfterIP[subKeysIndex], box);
			
			
			if (DEBUG){
				toString("\nWKEYS: " , data.subKeysBeforIP[subKeysIndex]);
				toString("\nBIP Key:", data.subKeysAfterIP[subKeysIndex]);
				toString("\nSub Key:", data.subKeys[subKeysIndex]);
				System.out.println();
			}
		
		}
		
		if (DEBUG)
			System.out.println();

	}

	
	private int[] inverseLinearTransformation(int[] input, int round ) throws RotationShiftException {

		input = FP(input);
		int a = input[0];
		int b = input[1];
		int c = input[2];
		int d = input[3];
		

		data.LTdata[round][0] = a;
		data.LTdata[round][1] = b;
		data.LTdata[round][2] = c;
		data.LTdata[round][3] = d;
		
		if(DEBUG)
			System.out.printf("\n%d:  a %X  b %X  c %X  d %X", round, data.LTdata[round][0],data.LTdata[round][1],data.LTdata[round][2],data.LTdata[round][3]);
		
		

		c = RightRotate(c, 22);
		a = RightRotate(a, 5);
		
		data.LTdata[round][4] = a;
		data.LTdata[round][5] = c;
		
		if(DEBUG)
			System.out.printf("\n%d:  a %X  c %X ", round, data.LTdata[round][4],data.LTdata[round][5]);
		
		
		
		
		c = XOR(XOR(c, d), LeftShift(b, 7));
		a = XOR(XOR(a, b), d);
		d = RightRotate(d, 7);
		b = RightRotate(b, 1);
		
		
		data.LTdata[round][6] = b;
		data.LTdata[round][7] = d;
		data.LTdata[round][10] = a;
		data.LTdata[round][11] = c;
		
		if(DEBUG)
			System.out.printf("\n%d:  b %X  d %X  a %X  c %X", round, data.LTdata[round][6],data.LTdata[round][7],data.LTdata[round][10],data.LTdata[round][11]);
		
		
		
		
		d = XOR(XOR(d, c), LeftShift(a, 3));
		b = XOR(XOR(b, a), c);
		c = RightRotate(c, 3);
		a = RightRotate(a, 13);
		
		data.LTdata[round][8] = b;
		data.LTdata[round][9] = d;
		data.LTdata[round][13] = c;
		data.LTdata[round][12] = a;
		
		if(DEBUG)
			System.out.printf("\n%d:  b %X  d %X  c %X a %X", round, data.LTdata[round][8],data.LTdata[round][9],data.LTdata[round][13],data.LTdata[round][12]);
		

		input[0] = a;
		input[1] = b;
		input[2] = c;
		input[3] = d;

		input = IP(input);
		return input;

	}

	private int[] linearTransformation(int[] input, int round) throws RotationShiftException {

		input = FP(input);

		int a = input[0];
		int b = input[1];
		int c = input[2];
		int d = input[3];
		
		data.LTdata[round][0] = a;
		data.LTdata[round][1] = b;
		data.LTdata[round][2] = c;
		data.LTdata[round][3] = d;
		if(DEBUG)
			System.out.printf("\n%d:  %X %X %X %X", round, data.LTdata[round][0],data.LTdata[round][1],data.LTdata[round][2],data.LTdata[round][3]);
		
		
		a = LeftRotate(a, 13);
		c = LeftRotate(c, 3);
		b = XOR(XOR(b, a), c);
		d = XOR(XOR(d, c), LeftShift(a, 3));
		
		data.LTdata[round][4] = a;
		data.LTdata[round][5] = c;
		data.LTdata[round][6] = b;
		data.LTdata[round][7] = d;
		
		if(DEBUG)
			System.out.printf("\n%d:  %X %X %X %X", round, data.LTdata[round][4],data.LTdata[round][5],data.LTdata[round][6],data.LTdata[round][7]);
		
		
		
		b = LeftRotate(b, 1);
		d = LeftRotate(d, 7);
		a = XOR(XOR(a, b), d);
		c = XOR(XOR(c, d), LeftShift(b, 7));
		
		data.LTdata[round][8] = b;
		data.LTdata[round][9] = d;
		data.LTdata[round][10] = a;
		data.LTdata[round][11] = c;
		
		if(DEBUG)
			System.out.printf("\n%d:  %X %X %X %X", round, data.LTdata[round][8],data.LTdata[round][9],data.LTdata[round][10],data.LTdata[round][11]);
		
		
		a = LeftRotate(a, 5);
		c = LeftRotate(c, 22);
		
		data.LTdata[round][12] = a;
		data.LTdata[round][13] = c;
		
		if(DEBUG)
			System.out.printf("\n%d: %X %X", round, data.LTdata[round][12],data.LTdata[round][13]);
		
		

		input[0] = a;
		input[1] = b;
		input[2] = c;
		input[3] = d;

		input = IP(input);
		
		
		return input;

	}

	public int[] encrypt(int[] plainText, int[] key)
			throws KeyException, SBoxException, RotationShiftException, TextException {
		if (key.length != 8 && key.length != 6 && key.length != 4)
			throw new KeyException(key.length * 4);

		if (plainText.length != 4)
			throw new TextException(plainText.length);

		System.out.println(encryptString);
		toString("Plain text: ", plainText);
		toString("\nKey:        ", key);

		data.text = little2Big(plainText);
		data.key = little2Big(key);

		makePreKeys();
		keySchedule();

		data.afterInitalPermutation = IP(data.text);
		

		if (DEBUG) {
			System.out.println("After Intial permutation:");
			toString(data.afterInitalPermutation);
			System.out.println();
		}
		for (int round = 0; round < 32; round++) {

			if (round == 0)
				data.afterXOR[round] = XOR(data.afterInitalPermutation, data.subKeys[round]);
			else
				data.afterXOR[round] = XOR(data.afterLT[round - 1], data.subKeys[round]);

			if (DEBUG)
				toString("\nXOR " + round + ": ", data.afterXOR[round]);

			data.afterSBOX[round] = SBox(data.afterXOR[round], round % 8);
			if (DEBUG)
				toString("\nSBox  " + round + ": ", data.afterSBOX[round]);
			if (round == 31) {
				break;
			}
			data.afterLT[round] = linearTransformation(data.afterSBOX[round], round);

			if (DEBUG) {
				toString("\nRound " + round + ": ", data.afterLT[round]);
				System.out.println();
			}

		}
		data.afterXOR[32] = XOR(data.afterSBOX[31], data.subKeys[32]);

		if (DEBUG) {
			System.out.print("\nRound 32: ");
			toString(data.afterXOR[32]);
			System.out.print("\t");
			toString(data.subKeys[32]);

		}

		data.afterFinalPermutation = FP(data.afterXOR[32]);

		if (DEBUG) {
			toString("\nAfter Final permutation CP: \n", data.afterFinalPermutation);
			System.out.println();
		}

		data.cipherText = little2Big(data.afterFinalPermutation);

		toString("\nCiphertext: ", data.cipherText);
		System.out.println();

		return data.cipherText;
	}

	public int[] decrypt(int[] plainText, int[] key)
			throws KeyException, SBoxException, RotationShiftException, TextException {
		if (plainText.length != 4)
			throw new TextException(plainText.length);
		if (key.length != 8 && key.length != 6 && key.length != 4)
			throw new KeyException(key.length * 4);
		System.out.println(decryptString);

		toString("Ciphertext: ", plainText);
		toString("\nKey:        ", key);

		data.text = little2Big(plainText);
		data.key = little2Big(key);

		makePreKeys();
		keySchedule();

		data.afterInitalPermutation = IP(data.text);

		if (DEBUG) {
			toString("After Inital permutation:\n", data.afterInitalPermutation);
			System.out.println();
		}

		for (int round = 31; round >= 0; round--) {
			if (round == 31)
				data.afterXOR[32] = XOR(data.afterInitalPermutation, data.subKeys[32]);
			else
				data.afterLT[round] = inverseLinearTransformation(data.afterXOR[round + 1], round);

			if (DEBUG) {
				if (round == 31)
					toString("\nXOR " + 32 + ": ", data.afterXOR[32]);
				else
					toString("\nLT " + round + ": ", data.afterLT[round]);
			}

			if (round == 31)
				data.afterSBOX[round] = iSBox(data.afterXOR[32], round % 8);
			else
				data.afterSBOX[round] = iSBox(data.afterLT[round], round % 8);

			if (DEBUG)
				toString("\nSBOX " + round + ": ", data.afterSBOX[round]);

			data.afterXOR[round] = XOR(data.afterSBOX[round], data.subKeys[round]);

			if (DEBUG){
				toString("\nXOR " + round + ": ", data.afterXOR[round]);
				System.out.println();
			}

		}

		data.afterFinalPermutation = FP(data.afterXOR[0]);

		data.cipherText = little2Big(data.afterFinalPermutation);

		toString("\nPlain text: ", data.cipherText);
		System.out.println();
		return data.cipherText;
	}
}
