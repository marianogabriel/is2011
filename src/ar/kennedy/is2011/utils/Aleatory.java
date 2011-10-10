package ar.kennedy.is2011.utils;

/**
 * @author: mlabarinas
 */
public final class Aleatory {
	 
	private static final String NUMEROS = "0123456789";
	private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	
	private Aleatory() {}
	
	public static Integer getAleatoryNumber(Integer length) {
		return new Integer(aleatory(NUMEROS, length));
	}
 
	public static String getAleatoryString(Integer length) {
		return aleatory((new StringBuilder()).append(NUMEROS).append(MAYUSCULAS).append(MINUSCULAS).toString(), length);
	}
 
	private static String aleatory(String key, int length) {
		StringBuilder aleatory = new StringBuilder();
 
		for (int i = 0; i < length; i++) {
			aleatory.append((key.charAt((int)(Math.random() * key.length()))));
		}
 
		return aleatory.toString();
	}
	
}