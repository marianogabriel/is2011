package ar.kennedy.is2011.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.constants.Constants;
import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.AlbumEy;
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
	private AbstractDao<AlbumEy> albumDao;
	private PictureEy picture;
	
	public ImageProxyModel(HttpServletRequest request, HttpServletResponse response, Session userSession) {
		super();
		
		this.request = request;
		this.response = response;
		this.userSession = userSession;
		this.pictureDao = new AbstractDao<PictureEy>();
		this.albumDao = new AbstractDao<AlbumEy>();
	}
	
	public void getPicture() throws Exception {
		WebUtils.validateMandatoryParameters(request, new String[] {"id", "version"});
		
		picture = pictureDao.findById(PictureEy.class, WebUtils.getParameter(request, "id"));
		String version = WebUtils.getParameter(request, "version");
		
		if(picture != null) {
			if(validate(picture)) {
				if(picture.getContent() != null) {
					response.setContentType(picture.getContentType());
					
					if("O".equals(version)) {
						response.getOutputStream().write(picture.getContent().getBytes());
					
					} else {
						response.getOutputStream().write(WebUtils.resize(picture.getContent().getBytes(), getVersionWidth(version), getVersionHeight(version)));
					}
				
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
	
	private Boolean validate(PictureEy picture) throws Exception {
		AlbumEy album = albumDao.findById(AlbumEy.class, picture.getAlbumId());
		
		return Constants.PUBLIC_VISIBILITY.equals(album.getVisibility()) ? true : ((Usuario) userSession.getElement("user")).getNombreUsr().equals(picture.getUsername());
	}
	
	private Integer getVersionWidth(String version) {
		if("N".equals(version)) {
			return 90;
		
		} else if("I".equals(version)) {
			return 150;
		
		} else {
			return 100;
		}
	}
	
	private Integer getVersionHeight(String version) {
		if("N".equals(version)) {
			return 90;
		
		} else if("I".equals(version)) {
			return 150;
		
		} else {
			return 100;
		}
	}
	
}