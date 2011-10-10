package ar.kennedy.is2011.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.UserEy;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class TestController extends AbstractController {

	private static final long serialVersionUID = 3707424606466635639L;
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session session) throws Exception {
		AbstractDao<UserEy> userDao = new AbstractDao<UserEy>();
		UserEy user = new UserEy();
		
		user.setUsername("jdropes");
		user.setPassword(WebUtils.encrypt("12344321"));
		user.setStatus("active");
		user.setDateCreated(new Date());
		user.setDateUpdated(user.getDateCreated());
	
		userDao.persist(user);
	}
	
	public boolean validateLogin() {
		return false;
	}
	
}