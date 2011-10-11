package ar.kennedy.is2011.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.kennedy.is2011.db.dao.AdministrarRegistracionUsuarioDAO;
import ar.kennedy.is2011.db.dao.AdministrarRegistracionUsuarioDAOImpl;
import ar.kennedy.is2011.db.entities.Usuario;
import ar.kennedy.is2011.utils.WebUtils;

public class RegistracionUsuarioServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	public RegistracionUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsr = request.getParameter("nombreUsuario");
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		String idPreg = request.getParameter("idPreguntaSecreta");
		String resp = request.getParameter("respuestaSecreta");
		
		Usuario usuario = new Usuario(nombreUsr,WebUtils.encrypt(clave),email,idPreg,resp);
		AdministrarRegistracionUsuarioDAO servicio = new AdministrarRegistracionUsuarioDAOImpl();
		
		if(servicio.validarExistenciaUsuario(usuario.getNombreUsr())){
     		System.out.println("El nombre de usuario existe!!");
     		request.setAttribute("errors", "El nombre de usuario ya existe");
     		request.setAttribute("usuarioNoRegistrado", usuario);
			request.getRequestDispatcher("registracionRapida.jsp").forward(request, response);
		}else{
			
			if(servicio.nuevoUsuario(usuario)){
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuarioLogeado", usuario); 	
				request.getRequestDispatcher("loginUsuario.jsp").forward(request, response);
			
			}else{	  
			System.out.println(" error en en alta ususario");
			request.getRequestDispatcher("registracionRapida.jsp").forward(request, response);
			}
		}
	}

}
