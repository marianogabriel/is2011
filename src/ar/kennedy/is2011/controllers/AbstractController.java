package ar.kennedy.is2011.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.HttpJspPage;

import org.apache.log4j.Logger;

import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.session.SessionManager;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public abstract class AbstractController extends HttpServlet implements ControllerItf, HttpJspPage {
	
	private static final long serialVersionUID = 7320911254853012236L;
	
	protected static final Logger log = Logger.getLogger(AbstractController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		internalAction(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		internalAction(request, response);
	}
	
	protected void internalAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session userSession = null;
		
		try {
			setHttpHeaders(response);
			userSession = getSession(request, response);
			
			if(validateLogin(request)) {
				if(validateUserLogin(request, response, userSession)) {
					if(!isJspPage()) {
						action(request, response, userSession);
					
					} else {
						_jspService(request, response);
					}
				
				} else {
					response.sendRedirect("/index.jsp");
				}
			
			} else {
				if(!isJspPage()) {
					action(request, response, userSession);
				
				} else {
					_jspService(request, response);
				}
			}
			
		} catch(Exception e) {
			log.error("Unexpected error", e);
			request.getSession(true).setAttribute("exception", e);
			
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception { }
	
	public abstract boolean validateLogin(HttpServletRequest request);
	
	protected Boolean validateUserLogin(HttpServletRequest request, HttpServletResponse response, Session userSession) {
		return WebUtils.validateUserLogin(request, response, userSession);
	}
	
	protected Session getSession(HttpServletRequest request, HttpServletResponse response) {
		Session userSession = null;
		String sessionIdentificator = getSessionIdentificator(request);
		
		if(sessionIdentificator != null) {
			userSession = SessionManager.get(request, sessionIdentificator);
		}
		
		return userSession;
	}
	
	protected String getSessionIdentificator(HttpServletRequest request) {
		return WebUtils.getSessionIdentificator(request);
	}
	
	protected Boolean isJspPage() {
		return this.getClass().getName().endsWith("_jsp");
	}
	
	protected void setHttpHeaders(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
	}
	
	public void _jspService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	public void jspDestroy() { }

	public void jspInit() { }
	
}