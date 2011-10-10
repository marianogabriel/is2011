package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.models.LoginModel;
import ar.kennedy.is2011.models.ModelItf;
import ar.kennedy.is2011.session.Session;

/**
 * @author mlabarinas
 */
public class LoginController extends AbstractController {

	private static final long serialVersionUID = 7995740723219513508L;

	public void action(HttpServletRequest request, HttpServletResponse response, Session session) throws Exception {
		ModelItf model = new LoginModel();
		
		if(((LoginModel) model).validateLogin(request, response)) {
			response.sendRedirect("main.jsp");
		
		} else {
			response.sendRedirect("index.jsp?e=t");
		}
	}
	
	public boolean validateLogin() {
		return false;
	}
	
}