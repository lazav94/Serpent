package exceptions;

public class SBoxException extends SerpentException {


	private static final long serialVersionUID = 2855464292051743638L;

	public static final boolean SBOX = false;
	public static final boolean VALUE = true;
	
	public SBoxException(boolean type, int arg) {
		if(type)
			System.out.println("Value of S-Box can't be " + arg  + " must be beetween 0 - 15(0xFF)");
		else
			System.out.println("Number of S-Box can't be " + arg  + " must be beetween 0 - 7");
			
	}

}
