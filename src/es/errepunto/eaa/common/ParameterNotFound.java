package es.errepunto.eaa.common;

public class ParameterNotFound extends Exception {

	/**
	 * Exception thrown when there isn't a parameter with such name.
	 */
	private static final long serialVersionUID = -1006720088799489226L;

	public ParameterNotFound(String message) {
		super(message);
	}
	
	public ParameterNotFound(String message, Throwable throwable) {
        super(message, throwable);
    }
}
