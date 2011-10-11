package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.models.LogoutModel;
import ar.kennedy.is2011.models.ModelItf;
import ar.kennedy.is2011.session.Session;

/**
 * @author mlabarinas
 */
public class LogoutController extends AbstractController {

	private static final long serialVersionUID = 6173267829791229429L;

	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception {
		ModelItf model = new LogoutModel();
		
		((LogoutModel) model).logout(request, response);
		
		response.sendRedirect("index.jsp");
	}

	public boolean validateLogin() {
		return true;
	}
	
}