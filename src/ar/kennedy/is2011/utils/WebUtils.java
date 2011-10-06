package ar.kennedy.is2011.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import ar.kennedy.is2011.exception.CharsetConvertException;
import ar.kennedy.is2011.exception.ValidateMandatoryParameterException;

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
	
	public static String getParameter(HttpServletRequest request, String key) {
		try {
			return StringUtils.isNotBlank(request.getParameter(key)) ? new String(request.getParameter(key).trim().getBytes("ISO-8859-1"), "UTF-8") : null;
		
		} catch (UnsupportedEncodingException e) {
			throw new CharsetConvertException("Can't convert value to UTF-8");
		}
	}
	
}