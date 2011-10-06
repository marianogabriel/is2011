package ar.kennedy.is2011.exception;

/**
 * @author: mlabarinas
 */
public class CharsetConvertException extends RuntimeException {
		
	private static final long serialVersionUID = -2936415196084705105L;

	public CharsetConvertException(String message) {
		super(message);
	}
	
	public CharsetConvertException(String message, Throwable e) {
		super(message, e);
	}
	
}