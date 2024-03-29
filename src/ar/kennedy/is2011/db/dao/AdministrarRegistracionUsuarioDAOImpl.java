package ar.kennedy.is2011.db.dao;

import ar.kennedy.is2011.db.entities.Usuario;

public class AdministrarRegistracionUsuarioDAOImpl implements AdministrarRegistracionUsuarioDAO {

	AbstractDao<Usuario> usuarioDao;
	
	public AdministrarRegistracionUsuarioDAOImpl() {
		super();
		
		usuarioDao = new AbstractDao<Usuario>();
	}
	
	public Usuario buscarUsuario(String nombreUsr) {
		try {
			return usuarioDao.findById(Usuario.class, nombreUsr);
			
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarUsuario(Usuario usuario) {
		return false;
	}

	

	@Override
	public boolean modificarUsuario(Usuario usuario) {
		/*
		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(usuario);
        } finally {
            pm.close();
        }
        */
		try {
			usuarioDao.persist(usuario);
			
		} catch(Exception e) {
			return false;
		}
			
		return true;
	}

	@Override
	public boolean nuevoUsuario(Usuario usuario) {
		/*
		PersistenceManager pm = PMF.get().getPersistenceManager();
        
        try {
            pm.makePersistent(usuario);
        } finally {
            pm.close();
        }
		*/
		
		try {
			usuarioDao.persist(usuario);
			
		} catch(Exception e) {
			return false;
		}
			
		return true;
	}

	@Override
	public boolean validarExistenciaUsuario(String nombreUsr) {
		/*
		boolean existe = false; 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Usuario.class);
		query.setFilter("nombreUsr == nombreUsrParam");
	    query.declareParameters("String nombreUsrParam");
		
	    try{
			List usuarios = (List) query.execute(nombreUsr);
			if(usuarios!=null && usuarios.size()>=1){
				Iterator it = usuarios.iterator();
				while(it.hasNext()){
					Usuario usa = (Usuario)it.next();
					System.out.println("-- "+usa.getNombreUsr()+" --"+usa.getClave());
				}
				existe = true;
			}
		}finally{
			query.closeAll();
			pm.close();
		}
		*/
	    
		try {
			Usuario usuario = usuarioDao.findById(Usuario.class, nombreUsr);
			
			if(usuario != null) {
				 return true;
			
			} else {
				return false;
			}
			
		} catch(Exception e) {
			return false;
		}
	}

}