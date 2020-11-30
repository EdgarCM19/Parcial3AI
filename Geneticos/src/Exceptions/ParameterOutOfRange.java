package Exceptions;

public class ParameterOutOfRange extends Exception {

	public ParameterOutOfRange(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ParameterOutOfRange(String message) {
		super(message);
	}
	
}
