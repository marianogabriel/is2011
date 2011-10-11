package ar.kennedy.is2011.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.kennedy.is2011.db.entities.Usuario;

/**
 * Servlet implementation class LoginUsuarioController
 */
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("entro en Forward!!!");
		  String pagina = request.getParameter("misFotos");   
		   
		  HttpSession session = request.getSession();	
		  Usuario usr = (Usuario)session.getAttribute("usuarioLogeado");
		  
		  if(usr != null){
			if(pagina.equals("misFotos")){
			  request.getRequestDispatcher("secure/panelPrincipal.jsp").forward(request, response);
			}
			  
			  
			  
		  }else{
		  session.invalidate();
		  request.setAttribute("iniciarSesion","Debe iniciar sesion nuevamente");
		  request.getRequestDispatcher("loginUsuario.jsp").forward(request, response);
		  }		
		
				
	}

}
