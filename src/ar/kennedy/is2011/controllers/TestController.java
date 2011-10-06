package ar.kennedy.is2011.controllers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.session.Session;

import com.google.appengine.api.datastore.Blob;

/**
 * @author mlabarinas
 */
public class TestController extends AbstractController {
	
	private static final long serialVersionUID = 7320911254853012236L;

	public void action(HttpServletRequest request, HttpServletResponse response, Session session) throws Exception {
		AbstractDao<PictureEy> dao = new AbstractDao<PictureEy>();
		PictureEy picture = new PictureEy();
		
		try {
			URL resource = getServletContext().getResource("/images/Nenufares.jpg");
			
			picture.setPictureId("32249_2323.jpg");
			picture.setCreateDate(new Date());
			picture.setLastUpdated(new Date());
			picture.setContent(new Blob(inputStramToByteArray(resource)));
			
			dao.persist(picture);
			
			PictureEy result = dao.findById(PictureEy.class, "32249_2323.jpg");
			
			response.setContentType("image/jpg");
			response.getOutputStream().write(result.getContent().getBytes());
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private byte[] inputStramToByteArray(URL resource) throws Exception {
		 InputStream inputStream = resource.openStream();
		 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		 int c;
		 
		 while ((c = inputStream.read()) != -1) {
			 byteArrayOutputStream.write((char) c);
		 }
		 
		 return byteArrayOutputStream.toByteArray();
	}

	public boolean validateLogin() {
		return false;
	}
	
}