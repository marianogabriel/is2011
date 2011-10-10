package ar.kennedy.is2011.exception;

/**
 * @author: mlabarinas
 */
public class UserNotExistException extends Exception {
	
	private static final long serialVersionUID = 8830585341862091056L;

	public UserNotExistException(String message) {
		super(message);
	}
	
	public UserNotExistException(String message, Throwable e) {
		super(message, e);
	}
	
}
