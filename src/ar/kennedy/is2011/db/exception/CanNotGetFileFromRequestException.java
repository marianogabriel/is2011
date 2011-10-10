package ar.kennedy.is2011.db.exception;

/**
 * @author mlabarinas
 */
public class CanNotGetFileFromRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public CanNotGetFileFromRequestException(String message) {
		super(message);
	}
	
	public CanNotGetFileFromRequestException(String message, Throwable t) {
		super(message, t);
	}
	
}