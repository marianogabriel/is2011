package ar.kennedy.is2011.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import ar.kennedy.is2011.constants.Constants;
import ar.kennedy.is2011.exception.CharsetConvertException;
import ar.kennedy.is2011.exception.ValidateMandatoryParameterException;
import ar.kennedy.is2011.picture.MultiPartRequest;
import ar.kennedy.is2011.session.Session;

/**
 * @author mlabarinas
 */
public class WebUtils {

	private WebUtils() { }
	
	public static Boolean validateMandatoryParameters(HttpServletRequest request, String[] parameters) throws ValidateMandatoryParameterException {
		List<String> parametersNotFounds = new ArrayList<String>();
		
		for(String parameter : parameters) {
			if(StringUtils.isBlank(request.getParameter(parameter))) {
				parametersNotFounds.add(parameter);
			}
		}
		 
		if(!parametersNotFounds.isEmpty()) {
			ValidateMandatoryParameterException validateMandatoryParameterException = new ValidateMandatoryParameterException("Can't find mandatory parameters");
			validateMandatoryParameterException.setParameters(parametersNotFounds.toArray(new String[parametersNotFounds.size()]));
			throw validateMandatoryParameterException;
		}
		
		return true;
	}
	
	public static Boolean validateMandatoryParameters(MultiPartRequest request, String[] parameters) throws ValidateMandatoryParameterException {
		List<String> parametersNotFounds = new ArrayList<String>();
		
		for(String parameter : parameters) {
			if(StringUtils.isBlank(request.getParameter(parameter))) {
				parametersNotFounds.add(parameter);
			}
		}
		 
		if(!parametersNotFounds.isEmpty()) {
			ValidateMandatoryParameterException validateMandatoryParameterException = new ValidateMandatoryParameterException("Can't find mandatory parameters");
			validateMandatoryParameterException.setParameters(parametersNotFounds.toArray(new String[parametersNotFounds.size()]));
			throw validateMandatoryParameterException;
		}
		
		return true;
	}
	
	public static Boolean validateUrl(String url) {
		try {
			new URL(url);
		
			return true;
			
		} catch(MalformedURLException e) {
			return false;
		}
	}
	
	public static String getParameter(HttpServletRequest request, String key) {
		try {
			return StringUtils.isNotBlank(request.getParameter(key)) ? new String(request.getParameter(key).trim().getBytes("ISO-8859-1"), "UTF-8") : null;
		
		} catch (UnsupportedEncodingException e) {
			throw new CharsetConvertException("Can't convert value to UTF-8");
		}
	}
	
	public static String getParameter(MultiPartRequest request, String key) {
		try {
			return StringUtils.isNotBlank(request.getParameter(key)) ? new String(request.getParameter(key).trim().getBytes("ISO-8859-1"), "UTF-8") : null;
		
		} catch (UnsupportedEncodingException e) {
			throw new CharsetConvertException("Can't convert value to UTF-8");
		}
	}
	
	public static Boolean validateUserLogin(HttpServletRequest request, HttpServletResponse response, Session userSession) {
		String sessionValidate = getSessionValidate(request);
		
		if(StringUtils.isNotBlank(sessionValidate) && userSession != null) {
			if(sessionValidate.equals((String) userSession.getElement("sessionValidate"))) {
				return true;
			} else {
				return false;
			}
		
		} else {
			return false;
		}
	}
	
	public static String createSessionIdentificator() {
		return (new StringBuilder()).append(String.valueOf(System.currentTimeMillis())).append("_").append(Aleatory.getAleatoryString(10)).toString();
	}
	
	public static String getSessionIdentificator(HttpServletRequest request) {
		Cookie sessionIdentificator = getCookie(request, "sessionIdentificator");
		
		return sessionIdentificator != null ? decrypt(sessionIdentificator.getValue()) : null;
	}
	
	public static String getSessionValidate(HttpServletRequest request) {
		Cookie sessionIdentificator = getCookie(request, "sessionValidate");
		
		return sessionIdentificator != null ? decrypt(sessionIdentificator.getValue()) : null;
	}
	
	public static Cookie getCookie(HttpServletRequest request, String cookieName){
	    Cookie cookies[] = request.getCookies();
	    
	    if (cookies != null){
	        for (Cookie cookie : cookies){
	            if (cookie.getName().equals(cookieName)){
	                return cookie;
	            }
	        }
	    }
	    
	    return null;
	}

	public static void setCookie(HttpServletResponse response, String key, String value, Integer maxAge) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(maxAge);
		
		response.addCookie(cookie);
	}
	
	public static void setCookie(HttpServletResponse response, Cookie cookie) {
		response.addCookie(cookie);
	}
	
	public static Cookie clearCookie(Cookie cookie) {
		cookie.setValue(null);
		cookie.setMaxAge(0);
		
		return cookie;
	}
	
	public static String encrypt(String value) {
		return StringUtils.replaceChars(value, Constants.ORDERER_STRING, Constants.NOT_ORDERER_STRING);
	}
	
	public static String decrypt(String value) {
		return StringUtils.replaceChars(value, Constants.NOT_ORDERER_STRING, Constants.ORDERER_STRING);
	}
	
    public static String getStackStrace(Throwable e) throws Exception {
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	PrintStream printStream = null;
    	
    	try {
	    	byteArrayOutputStream = new ByteArrayOutputStream();
	        printStream = new PrintStream(byteArrayOutputStream);
	        e.printStackTrace(printStream);
	        byteArrayOutputStream.flush();
	        
	        return WebUtils.stripXss(byteArrayOutputStream.toString());
    	
    	} finally {
	        byteArrayOutputStream.close();
    	}
    }
    
    private static String stripXss(String input) {
    	return input.replace("&", "&amp;").replace("#", "&#35;").replace("<", "&lt;").replace(">", "&gt;").replace("(", "&#40;").replace(")", "&#41;");
    }
	
}