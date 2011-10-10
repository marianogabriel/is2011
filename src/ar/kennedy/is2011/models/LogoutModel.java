package ar.kennedy.is2011.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.session.SessionManager;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class LogoutModel extends AbstractModel {

	public LogoutModel() {
		super();
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SessionManager.delete(request, WebUtils.getSessionIdentificator(request));
		
		WebUtils.setCookie(response, WebUtils.clearCookie(WebUtils.getCookie(request, "sessionIdentificator")));
		WebUtils.setCookie(response, WebUtils.clearCookie(WebUtils.getCookie(request, "sessionValidate")));
	}
	
}