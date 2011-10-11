package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.Usuario;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class TestController extends AbstractController {

	private static final long serialVersionUID = 3707424606466635639L;
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception {
		AbstractDao<Usuario> userDao = new AbstractDao<Usuario>();
		Usuario user = new Usuario();
		
		user.setNombreUsr("jdropes");
		user.setClave(WebUtils.encrypt("12344321"));
		
		userDao.persist(user);
	}
	
	public boolean validateLogin() {
		return false;
	}
	
}