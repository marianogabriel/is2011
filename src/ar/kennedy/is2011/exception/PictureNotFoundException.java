package ar.kennedy.is2011.exception;

/**
 * @author: mlabarinas
 */
public class PictureNotFoundException extends Exception {
	
	private static final long serialVersionUID = 8830585341862091056L;

	public PictureNotFoundException(String message) {
		super(message);
	}
	
	public PictureNotFoundException(String message, Throwable e) {
		super(message, e);
	}
	
}
