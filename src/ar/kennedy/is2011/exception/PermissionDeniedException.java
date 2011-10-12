package ar.kennedy.is2011.exception;

/**
 * @author: mlabarinas
 */
public class PermissionDeniedException extends Exception {
	
	private static final long serialVersionUID = 8830585341862091056L;

	public PermissionDeniedException(String message) {
		super(message);
	}
	
	public PermissionDeniedException(String message, Throwable e) {
		super(message, e);
	}
	
}
