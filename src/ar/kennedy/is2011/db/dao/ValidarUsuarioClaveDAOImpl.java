package ar.kennedy.is2011.db.dao;

import ar.kennedy.is2011.db.entities.Usuario;

public class ValidarUsuarioClaveDAOImpl implements ValidarUsuarioClaveDAO {

	AbstractDao<Usuario> usuarioDao;
	
	public ValidarUsuarioClaveDAOImpl() {
		super();
		
		this.usuarioDao = new AbstractDao<Usuario>();
	}
	
	@Override
	public Usuario validarUsuarioYClave(String nombreUsr, String clave) {
		/*
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Usuario.class);
		query.setFilter("nombreUsr == nombreUsrParam");
	    query.declareParameters("String nombreUsrParam");
		
	    try{
			List usuarios = (List) query.execute(nombreUsr);
			if(usuarios!=null){
				Iterator it = usuarios.iterator();
				while(it.hasNext()){
					Usuario usr = (Usuario)it.next();
					if(usr.getClave().trim().equals(clave.trim())){
					  return usr;	
					}
					
				}
			
			}
		}finally{
			query.closeAll();
			pm.close();
		}
			
	return null;
	*/
		try {
			Usuario usuario = usuarioDao.findById(Usuario.class, nombreUsr);
			
			if(usuario != null && usuario.getClave().trim().equals(clave.trim())){
				  return usuario;	
			
			} else {
				return null;
			}
			
		} catch(Exception e) {
			return null;
		}
	}

}