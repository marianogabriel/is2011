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
public class LoginUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Entro en doGet!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("entro en doPost!!!");
		String idUsr = (String) request.getParameter("nombreUsr");
		String clave = (String) request.getParameter("claveUsr");
				
		ValidarUsuarioClaveDAO validar = new ValidarUsuarioClaveDAOImpl();
		
		Usuario usuario = validar.validarUsuarioYClave(idUsr.trim(), clave.trim()); 
	
		if(usuario != null){
		  HttpSession session = request.getSession(true);
		  session.setMaxInactiveInterval(600);// en segundos
		  session.setAttribute("usuarioLogeado", usuario); 	
		  request.getRequestDispatcher("jsp/panelPrincipal.jsp").forward(request, response);
				
		}else{
			request.setAttribute("loginFailure","Nombre de usuario o contraseña no válidos.");
		   	request.getRequestDispatcher("jsp/loginUsuario.jsp").forward(request, response); 
		}
				
	}

}
