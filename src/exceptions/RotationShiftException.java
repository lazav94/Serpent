package exceptions;

public class RotationShiftException extends SerpentException {


	private static final long serialVersionUID = 9095911950862844061L;

	public RotationShiftException(int arg) {
		System.out.println("You can't rotate/shift by negative positions ( " + arg + " )");
			
	}

}
