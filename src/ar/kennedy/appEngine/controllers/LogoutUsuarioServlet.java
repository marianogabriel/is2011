package ar.kennedy.appEngine.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.kennedy.appEngine.beans.Usuario;
import ar.kennedy.appEngine.dao.login.ValidarUsuarioClaveDAO;
import ar.kennedy.appEngine.dao.login.ValidarUsuarioClaveDAOImpl;

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
		
		  HttpSession session = request.getSession();	
		  session.invalidate();
		  request.getRequestDispatcher("jsp/loginUsuario.jsp").forward(request, response);
				
		
				
	}

}
