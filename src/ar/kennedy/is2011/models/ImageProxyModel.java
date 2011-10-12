package ar.kennedy.is2011.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.db.entities.Usuario;
import ar.kennedy.is2011.exception.PermissionDeniedException;
import ar.kennedy.is2011.exception.PictureNotFoundException;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class ImageProxyModel extends AbstractModel {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session userSession;
	private AbstractDao<PictureEy> pictureDao;
	private PictureEy picture;
	
	public ImageProxyModel(HttpServletRequest request, HttpServletResponse response, Session userSession) {
		super();
		
		this.request = request;
		this.response = response;
		this.userSession = userSession;
		this.pictureDao = new AbstractDao<PictureEy>();
	}
	
	public void getPicture() throws Exception {
		WebUtils.validateMandatoryParameters(request, new String[] {"id"});
		
		picture = pictureDao.findById(PictureEy.class, request.getParameter("id"));
		
		if(picture != null) {
			if(validate(picture)) {
				if(picture.getContent() != null) {
					response.getOutputStream().write(picture.getContent().getBytes());
				
				} else {
					response.sendRedirect(picture.getUrl());
				}
			
			} else {
				throw new PermissionDeniedException("User can't access to picture");
			}
		
		} else {
			throw new PictureNotFoundException((new StringBuilder()).append("Picture with id: ").append(request.getParameter("id")).append(" not found").toString());
		}
	}
	
	private Boolean validate(PictureEy picture) {
		return ((Usuario) userSession.getElement("user")).getNombreUsr().equals(picture.getUsername());
	}
	
}