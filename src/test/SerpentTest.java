package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import crypto.Serpent;
import exceptions.KeyException;
import exceptions.RotationShiftException;
import exceptions.SBoxException;
import exceptions.TextException;

public class SerpentTest {

	
	private static boolean areEquals(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i])
				return false;
		return true;

	}
	

	private static int[] HexStringToIntArray(String hexString) {
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



	@Test
	public void testEncrypt() throws KeyException, SBoxException, RotationShiftException, TextException {
		Serpent s = Serpent.getInstance();

		int[] key = HexStringToIntArray("80000000000000000000000000000000");
		int[] plainText = HexStringToIntArray("00000000000000000000000000000000");
		s.encrypt(plainText, key);
		assertEquals("Encryption failed!",
				areEquals(s.data.cipherText, HexStringToIntArray("264E5481EFF42A4606ABDA06C0BFDA3D")), true);

	}

	@Test
	public void testDecrypt() throws KeyException, SBoxException, RotationShiftException, TextException {
		Serpent s = Serpent.getInstance();
		int[] key = HexStringToIntArray("80000000000000000000000000000000");
		int[] cipherText = HexStringToIntArray("264E5481EFF42A4606ABDA06C0BFDA3D");
		s.decrypt(cipherText, key);
		assertEquals("Encryption failed!",
				areEquals(s.data.cipherText, HexStringToIntArray("00000000000000000000000000000000")), true);

	}

	public static void main(String[] argv) {

		try {
			Serpent s = Serpent.getInstance();
			
			int[] key = HexStringToIntArray("00000000000000000000000000000000");
			int[] plainText = HexStringToIntArray("00000000000000000000000000000000");
			s.encrypt(plainText, key);
			
			/*Serpent s = Serpent.getInstance();
			int[] key = byteToInt(hexStringToByteArray("Ovo je kljucwifi"));
			int[] plainText = byteToInt(hexStringToByteArray("Plain Text Test!"));
			printIntAsString(plainText);
			printIntAsString(key);

			s.encrypt(plainText, key);

			printIntAsString(s.data.cipherText);

			key = byteToInt(hexStringToByteArray("Ovo je kljucwifi"));
			s.decrypt(s.data.cipherText, key);

			printIntAsString(s.data.cipherText);*/
		} catch (KeyException | SBoxException | RotationShiftException | TextException e) {
			e.printStackTrace();
		}

	}
}
