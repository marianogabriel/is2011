package ar.kennedy.is2011.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mlabarinas
 */
public abstract class AbstractController extends HttpServlet {
	
	private static final long serialVersionUID = 7320911254853012236L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}
	
	protected abstract void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}