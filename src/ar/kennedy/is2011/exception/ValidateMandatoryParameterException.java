package ar.kennedy.is2011.exception;

/**
 * @author: mlabarinas
 */
public class ValidateMandatoryParameterException extends RuntimeException {
	
	private static final long serialVersionUID = 8830585341862091056L;

	private String[] parameters = null;
	
	public ValidateMandatoryParameterException(String message) {
		super(message);
	}
	
	public ValidateMandatoryParameterException(String message, Throwable e) {
		super(message, e);
	}
	
	public String[] getParameters() {
		return parameters;
	}
	
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	
}