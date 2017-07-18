package exceptions;

public class KeyException extends SerpentException {


	private static final long serialVersionUID = 8053612035302633850L;

	public KeyException(int lenght) {
		System.out.println("Key size must be 128, 192 or 256 bits : " + lenght);
	}

	
	
	
}
