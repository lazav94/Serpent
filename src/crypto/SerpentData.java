package crypto;

public class SerpentData {

	private static SerpentData seprentData = null;

	public static SerpentData getInstance() {
		if (seprentData == null)
			seprentData = new SerpentData();
		return seprentData;
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();


	
	public static Object[][] intArrayToBooleanIntArray(int[] intArray){
		Object[][] result = new Object[4][32];
		
		for(int i = 0; i < 4; i++)
			for(int j = 0 ; j < 32; j++)
				result[i][j] = 0;
		
		for (int i = 0; i < 4; i++) {
			
			String s = (Integer.toBinaryString(intArray[i]));			
			for(int j = 0 ; j < s.length() ; j++){
				
				if(s.charAt(j) == '1')
					result[i][(32 - s.length()) + j] = 1;
			}

		}
			
		return result;
	}

	private static String byteToUnsignedHex(int i) {
		String hex = Integer.toHexString(i);
		while (hex.length() < 8) {
			hex = "0" + hex;
		}
		return hex;
	}

	public String intArrayToHexString(int[] arr) {
		StringBuilder builder = new StringBuilder(arr.length * 8);
		for (int b : arr) {
			builder.append(byteToUnsignedHex(b));
		}
		return builder.toString();
	}

	public static Object[][] intToObject(int[] intArray) {
		Object[][] result = new Object[intArray.length][8];
		int i = 0;
		for (int integer : intArray) {
			result[i][7] = hexArray[(integer & 0xF)];
			result[i][6] = hexArray[((integer >> 4) & 0xF)];
			result[i][5] = hexArray[((integer >> 8) & 0xF)];
			result[i][4] = hexArray[((integer >> 12) & 0xF)];
			result[i][3] = hexArray[((integer >> 16) & 0xF)];
			result[i][2] = hexArray[((integer >> 20) & 0xF)];
			result[i][1] = hexArray[((integer >> 24) & 0xF)];
			result[i][0] = hexArray[((integer >> 28) & 0xF)];
			i++;

		}
		return result;
	}

	/**
	 * Data for Serpent
	 */
	
	public int[] afterTestInitalPermutaion = new int[4];

	public int[] text = new int[4];
	public int[] key = new int[8];;
	public int[] afterInitalPermutation = new int[4];
	public int[][] roundResult = new int[32][];
	public int[] afterFinalPermutation = new int[4];
	public int[] cipherText = new int[4];

	public int[] sumW = new int[140];
	public int[] W = new int[140];
	
	public int[][] subKeys = new int[33][4];
	public int[][] subKeysAfterIP = new int[33][4];
	public int[][] subKeysBeforIP = new int[33][4];

	public int[][] afterXOR = new int[33][4];
	public int[][] afterSBOX = new int[32][4];
	public int[][] afterLT = new int[32][4];
	
	public int[][] LTdata = new int[31][18];

	public void reset() {
		text = new int[4];
		key = new int[8];
		
		afterInitalPermutation = new int[4];
		roundResult = new int[32][];
		afterFinalPermutation = new int[4];
		cipherText = new int[4];

		W = new int[140];
		subKeys = new int[33][4];

		afterXOR = new int[33][4];
		afterSBOX = new int[32][4];
		afterLT = new int[32][4];

	}

}
