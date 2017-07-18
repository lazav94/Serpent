package test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

//import static org.junit.Assert.*;
//
//import org.junit.Test;

import crypto.Serpent;
import exceptions.KeyException;
import exceptions.RotationShiftException;
import exceptions.SBoxException;

public class SerpentTest {

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

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len];

		for (int i = 0; i < len; i++)
			data[i] = (byte) s.charAt(i);

		return data;
	}

	public static int[] byteToInt(byte[] byteArray) {
		IntBuffer intBuf = ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
		int[] array = new int[intBuf.remaining()];
		intBuf.get(array);
		return array;
	}

	
	public static void main(String[] argv) {

		
		try {
			Serpent s = Serpent.getInstance();
			int[] key = byteToInt(hexStringToByteArray("Ovo je kljucwifi"));

			int[] plainText = byteToInt(hexStringToByteArray("Mami kako si ti?"));

			for (int i = 0; i < plainText.length; i++) {
				char c1 = (char) ((plainText[i] >> 24) & 0xFF);
				char c2 = (char) ((plainText[i] >> 16) & 0xFF);
				char c3 = (char) ((plainText[i] >> 8) & 0xFF);
				char c4 = (char) (plainText[i] & 0xFF);

				System.out.print("" +c1 + c2 + c3 + c4);

			}
			System.out.println();

			s.encrypt(plainText, key);

			for (int i = 0; i < s.cipherText.length; i++) {
				char c1 = (char) ((s.cipherText[i] >> 24) & 0xFF);
				char c2 = (char) ((s.cipherText[i] >> 16) & 0xFF);
				char c3 = (char) ((s.cipherText[i] >> 8) & 0xFF);
				char c4 = (char) (s.cipherText[i] & 0xFF);

				System.out.print("" + c1  + c2 +  c3 + c4);

			}
			System.out.println();


			/***/
//			int pt[] =  byteToInt(hexStringToByteArray("×XÁ7UårhÖ.ûi?=J"));
//			pt[3] = 0x69843D4A;
			/***/
			
			key = byteToInt(hexStringToByteArray("Ovo je kljucwifi"));
			s.decrypt(s.cipherText, key);

			for (int i = 0; i < s.cipherText.length; i++) {

				char c1 = (char) ((s.cipherText[i] >> 24) & 0xFF);
				char c2 = (char) ((s.cipherText[i] >> 16) & 0xFF);
				char c3 = (char) ((s.cipherText[i] >> 8) & 0xFF);
				char c4 = (char) (s.cipherText[i] & 0xFF);
				System.out.print(""+ c1 + c2 + c3 + c4);

			}
			System.out.println();
		} catch (KeyException e) {
			e.printStackTrace();
		} catch (SBoxException e) {
			e.printStackTrace();
		} catch (RotationShiftException e) {
			e.printStackTrace();
		}

	}
}
