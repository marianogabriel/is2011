package ar.kennedy.is2011.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.HttpJspPage;

import org.apache.log4j.Logger;

import ar.kennedy.is2011.session.Session;

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
		Session session = null;
		
		try {
			setHttpHeaders(response);
			
			if(validateLogin()) {
				if(validateUserLogin(request, response, session)) {
					if(!isJspPage()) {
						action(request, response, session);
					
					} else {
						_jspService(request, response);
					}
				
				} else {
					response.sendRedirect("index.jsp");
				}
			
			} else {
				if(!isJspPage()) {
					action(request, response, session);
				
				} else {
					_jspService(request, response);
				}
			}
			
		} catch(Exception e) {
			log.error("Unexpected error", e);
			response.sendRedirect("error.jsp");
		}
		
	}
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session session) throws Exception { }
	
	public abstract boolean validateLogin();
	
	protected Boolean validateUserLogin(HttpServletRequest request, HttpServletResponse response, Session session) {
		/** TODO: implement method  */
		return true;
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