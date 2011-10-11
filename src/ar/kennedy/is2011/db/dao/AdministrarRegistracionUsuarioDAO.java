package ar.kennedy.is2011.db.dao;

import ar.kennedy.is2011.db.entities.Usuario;

public interface AdministrarRegistracionUsuarioDAO {
	
	boolean nuevoUsuario(Usuario usuario);
	
	boolean borrarUsuario(Usuario usuario);
	
	boolean modificarUsuario(Usuario usuario);
	
	Usuario buscarUsuario(String idUsr);
	
	boolean validarExistenciaUsuario(String nombreUsr);
 
}
