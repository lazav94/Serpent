package exceptions;

public class TextException extends SerpentException {


	private static final long serialVersionUID = 8053612035302633850L;

	public TextException(int lenght) {
		System.out.println("Plain/Cipher text size must be 128 : " + lenght);
	}

	
	
	
}
