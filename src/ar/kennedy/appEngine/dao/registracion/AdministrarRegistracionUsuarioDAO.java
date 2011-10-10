package ar.kennedy.appEngine.dao.registracion;

import ar.kennedy.appEngine.beans.Usuario;

public interface AdministrarRegistracionUsuarioDAO {
	
	boolean nuevoUsuario(Usuario usuario);
	
	boolean borrarUsuario(Usuario usuario);
	
	boolean modificarUsuario(Usuario usuario);
	
	Usuario buscarUsuario(String idUsr);
	
	boolean validarExistenciaUsuario(String nombreUsr);
 
}
