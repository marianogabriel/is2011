package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.constants.Constants;
import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.AlbumEy;
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
		AbstractDao<AlbumEy> albumDao = new AbstractDao<AlbumEy>();
		Usuario user = new Usuario();
		AlbumEy album = new AlbumEy();
		
		user.setNombreUsr("jdropes");
		user.setClave(WebUtils.encrypt("12344321"));
		
		album.setAlbumId("otros");
		album.setVisibility(Constants.PUBLIC_VISIBILITY);
		album.setOwner("jdropes");
		
		userDao.persist(user);
		albumDao.persist(album);
		
		response.sendRedirect("index.jsp");
	}
	
	public boolean validateLogin(HttpServletRequest request) {
		return false;
	}
	
}