package ar.kennedy.is2011.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.kennedy.is2011.models.LogoutModel;
import ar.kennedy.is2011.models.ModelItf;

/**
 * Servlet implementation class LoginUsuarioController
 */
public class LogoutUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entro en doPost logout!!!");
		
		ModelItf model = new LogoutModel();
		
		try {
			((LogoutModel) model).logout(request, response);
			
		} catch(Exception e) { }
		
		 HttpSession session = request.getSession();	
		 session.invalidate();
		 request.getRequestDispatcher("loginUsuario.jsp").forward(request, response);
				
	}

}