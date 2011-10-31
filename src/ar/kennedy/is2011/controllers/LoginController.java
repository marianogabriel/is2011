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

	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception {
		ModelItf model = new LoginModel();
		log.error("login action");
		
		if(((LoginModel) model).validateLogin(request, response)) {
			log.error("Login validado");
			response.sendRedirect("/about.html");
			//response.sendRedirect("/secure/main.jsp");
		
		} else {
			request.getRequestDispatcher("/index.jsp?e=t").forward(request, response);
		}
	}
	
	public boolean validateLogin(HttpServletRequest request) {
		return false;
	}
	
}