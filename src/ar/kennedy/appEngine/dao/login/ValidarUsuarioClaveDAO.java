package ar.kennedy.appEngine.dao.login;

import ar.kennedy.appEngine.beans.Usuario;

public interface ValidarUsuarioClaveDAO {
	
	Usuario validarUsuarioYClave(String nombreUsr,String clave);

}
