package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.session.Session;

/**
 * @author: mlabarinas
 */
public interface ControllerItf { 
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session session) throws Exception;
	public boolean validateLogin();
	
}