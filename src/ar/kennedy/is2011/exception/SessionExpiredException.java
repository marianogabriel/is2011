package ar.kennedy.is2011.exception;

/**
 * @author: mlabarinas
 */
public class SessionExpiredException extends RuntimeException {

	private static final long serialVersionUID = 6768505943311247379L;

	public SessionExpiredException(String message) {
		super(message);
	}
	
	public SessionExpiredException(String message, Throwable e) {
		super(message, e);
	}
	
}
