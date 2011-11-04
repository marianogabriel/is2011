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

public class EditarCuentaUsuarioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public EditarCuentaUsuarioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdministrarRegistracionUsuarioDAO serv = new AdministrarRegistracionUsuarioDAOImpl(); 
		
		String nombre = request.getParameter("nombreUsr");
		String apellido = request.getParameter("apellidoUsr");
		String mail = request.getParameter("mailPrimario");
		String mail2 = request.getParameter("mailSecundaro");
		String fechaNac = request.getParameter("fechaNacimiento");
		String tipoSexo = request.getParameter("tipoSexo");
		String idPais = request.getParameter("pais");
		String idProv = request.getParameter("provincia");
		String idPreg = request.getParameter("preguntaSecreta");
		String resp = request.getParameter("respuestaUsr");
		
		HttpSession sesion = request.getSession();
		Usuario usuario = serv.buscarUsuario(((Usuario) sesion.getAttribute("usuarioLogeado")).getNombreUsr());
		
		if(usuario != null) {
		  usuario.setNombre(nombre);
		  usuario.setApellido(apellido);
		  usuario.setMail(mail);
		  usuario.setMailSecundario(mail2);
		  usuario.setFechaNacimiento(fechaNac);
		  usuario.setSexo(tipoSexo);
		  usuario.setPais(idPais);
		  usuario.setIdProvicia(idProv);
		  usuario.setIdPreguntaSecreta(idPreg);
		  usuario.setRespuestaPregunta(resp);
		  	
		  serv.modificarUsuario(usuario);
		  sesion.setAttribute("usuarioLogeado", usuario);
		  request.getRequestDispatcher("secure/editarCuentaUsuario.jsp").forward(request, response);
		
		} else {
			request.getRequestDispatcher("errorLogin.jsp").forward(request, response);  	
		}			
	}

}