package ar.kennedy.is2011.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.models.ImageUploaderModel;
import ar.kennedy.is2011.models.ModelItf;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.session.SessionManager;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class ImageUploaderController extends AbstractController {
	
	private static final long serialVersionUID = 8956304553458377284L;
	
	public void action(HttpServletRequest request, HttpServletResponse response, Session userSession) throws Exception {
		WebUtils.validateMandatoryParameters(request, new String[] {"action"});
		
		ModelItf model = new ImageUploaderModel(request, userSession, WebUtils.getParameter(request, "action"));
		
		resolveAction(request, response, (ImageUploaderModel) model, userSession, WebUtils.getParameter(request, "action"));
	}
	
	private void resolveAction(HttpServletRequest request, HttpServletResponse response, ImageUploaderModel model, Session userSession, String action) throws Exception {
		if("add".equals(action)) {
			if(model.validate()) {
				model.save();
				
				userSession.removeElement("picture");
				SessionManager.save(request, userSession);
				
				response.sendRedirect("secure/main.jsp");
			
			} else {
				response.sendRedirect("secure/imageUpload.jsp?e=t");
			}
		
		} else if("update".equals(action)) {
			if(model.validate()) {
				model.update();
				
				userSession.removeElement("picture");
				SessionManager.save(request, userSession);
				
				response.sendRedirect("secure/main.jsp");
			
			} else {
				response.sendRedirect("secure/imageUpload.jsp?e=t");
			}
		
		} else if("delete".equals(action)) {
			model.delete();
			
			response.sendRedirect("secure/main.jsp");
			
		} else {
			throw new Exception("Undefined action");
		}
	}
	
	public boolean validateLogin() {
		return true;
	}
	
}