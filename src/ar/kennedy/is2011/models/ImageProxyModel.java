package ar.kennedy.is2011.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class ImageProxyModel extends AbstractModel {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private AbstractDao<PictureEy> pictureDao;
	private PictureEy picture;
	
	public ImageProxyModel(HttpServletRequest request, HttpServletResponse response) {
		super();
		
		this.request = request;
		this.response = response;
		this.pictureDao = new AbstractDao<PictureEy>();
	}
	
	public void getImage() throws Exception {
		WebUtils.validateMandatoryParameters(request, new String[] {"id"});
		
		picture = pictureDao.findById(PictureEy.class, request.getParameter("id"));
		
		if(picture.getContent() != null) {
			response.getOutputStream().write(picture.getContent().getBytes());
		
		} else {
			response.sendRedirect(picture.getUrl());
		}
	}
	
}