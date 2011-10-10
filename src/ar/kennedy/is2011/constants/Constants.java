package ar.kennedy.is2011.constants;

/**
 * @author: mlabarinas
 */
public interface Constants {

	public static final String SPRING_CONFIG_XML_PATH = "ar/kennedy/is2011/spring/spring-config.xml"; 
	
	public static final String SESSION_MODE = "http";
	public static final String SESSION_STATUS_ACTIVE = "active";
	public static final String SESSION_STATUS_EXPIRE = "expire";
	
	public static final String ORDERER_STRING = "abcdefghijklnopqrstuvwxyzABCDEFGHIJKLNOPQRSTUVWXYZ1234567890";
	public static final String NOT_ORDERER_STRING = "bacdegfhijnlkoqprstuwvxyzBACDEHGFIJKLNOPQRSTUVWXYZ1237654809";
	
	public static final Integer MAX_READ_BUFFER_SIZE = 1024 * 1024 * 5;
	public static final Integer FILE_UPLOAD_MAX_SIZE = 5;
	
}