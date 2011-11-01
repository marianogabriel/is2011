package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.models.LoginModel;
import ar.kennedy.is2011.models.ModelItf;
import ar.kennedy.is2011.session.Session;

/**
 * Servlet implementation class LoginUsuarioController
 */
public class LoginUsuarioServlet extends LoginController {

	private static final long serialVersionUID = 782740363242765383L;

	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception {
		/*
		System.out.println("entro en doPost!!!");
		String idUsr = (String) request.getParameter("nombreUsr");
		String clave = (String) request.getParameter("claveUsr");
				
		ValidarUsuarioClaveDAO validar = new ValidarUsuarioClaveDAOImpl();
		
		Usuario usuario = validar.validarUsuarioYClave(idUsr.trim(), clave.trim()); 
	
		if(usuario != null){
		  HttpSession session = request.getSession(true);
		  session.setMaxInactiveInterval(600);// en segundos
		  session.setAttribute("usuarioLogeado", usuario); 	
		  request.getRequestDispatcher("secure/panelPrincipal.jsp").forward(request, response);
				
		}else{
			request.setAttribute("loginFailure","Nombre de usuario o contrase�a no v�lidos.");
		   	request.getRequestDispatcher("loginUsuario.jsp").forward(request, response); 
		}
		*/
		
		ModelItf model = new LoginModel();
		
		if(((LoginModel) model).validateLogin(request, response)) {
			response.sendRedirect("secure/main.jsp");
		
		} else {
			response.sendRedirect("index.jsp?e=t");
		}
	}
	
	@Override
	public boolean validateLogin(HttpServletRequest request) {
		return false;
	}

}