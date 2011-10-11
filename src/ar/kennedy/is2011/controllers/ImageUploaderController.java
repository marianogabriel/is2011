package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.models.ImageUploaderModel;
import ar.kennedy.is2011.models.ModelItf;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.session.SessionManager;

/**
 * @author mlabarinas
 */
public class ImageUploaderController extends AbstractController {
	
	private static final long serialVersionUID = 8956304553458377284L;
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception {
		ModelItf model = new ImageUploaderModel(request, userSession);
		
		if(((ImageUploaderModel) model).validate()) {
			((ImageUploaderModel) model).save();
			
			userSession.removeElement("picture");
			SessionManager.save(request, userSession);
			
			response.sendRedirect("secure/main.jsp");
		
		} else {
			response.sendRedirect("secure/imageUpload.jsp");
		}
	}
	
	public boolean validateLogin() {
		return true;
	}
	
}