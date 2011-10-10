package ar.kennedy.appEngine.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.apphosting.utils.remoteapi.RemoteApiPb.Request;

import ar.kennedy.appEngine.beans.Usuario;
import ar.kennedy.appEngine.dao.registracion.AdministrarRegistracionUsuarioDAO;
import ar.kennedy.appEngine.dao.registracion.AdministrarRegistracionUsuarioDAOImpl;

public class EditarCuentaUsuarioServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	public EditarCuentaUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogeado");
		
		if(usuario != null){
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
			
		  AdministrarRegistracionUsuarioDAO serv = new AdministrarRegistracionUsuarioDAOImpl();  	
		  serv.modificarUsuario(usuario);
		  request.getRequestDispatcher("jsp/editarCuentaUsuario.jsp").forward(request, response);	
		}
		else{
			request.getRequestDispatcher("errores/errorLogin.jspp").forward(request, response);  	
		}
			
		
	}

}
