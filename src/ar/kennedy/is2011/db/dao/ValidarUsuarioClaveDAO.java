package ar.kennedy.is2011.db.dao;

import ar.kennedy.is2011.db.entities.Usuario;

public interface ValidarUsuarioClaveDAO {
	
	Usuario validarUsuarioYClave(String nombreUsr,String clave);

}
