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
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session session) throws Exception {
		ModelItf model = new ImageUploaderModel(request, session);
		
		if(((ImageUploaderModel) model).validate()) {
			((ImageUploaderModel) model).save();
			
			session.removeElement("picture");
			SessionManager.save(request, session);
			
			response.sendRedirect("main.jsp");
		
		} else {
			response.sendRedirect("imageUpload.jsp");
		}
	}
	
	public boolean validateLogin() {
		return true;
	}
	
}