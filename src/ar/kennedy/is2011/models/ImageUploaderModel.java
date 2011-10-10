package ar.kennedy.is2011.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import ar.kennedy.is2011.constants.Constants;
import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.db.entities.UserEy;
import ar.kennedy.is2011.exception.ValidateMandatoryParameterException;
import ar.kennedy.is2011.picture.MultiPartRequest;
import ar.kennedy.is2011.picture.UploadedFile;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.session.SessionManager;
import ar.kennedy.is2011.utils.Aleatory;
import ar.kennedy.is2011.utils.WebUtils;

import com.google.appengine.api.datastore.Blob;

/**
 * @author mlabarinas
 */
public class ImageUploaderModel extends AbstractModel {

	private HttpServletRequest request;
	private Session session;
	private AbstractDao<PictureEy> pictureDao;
	
	public ImageUploaderModel(HttpServletRequest request, Session session) {
		super();
		
		this.request = request;
		this.session =  session;
		this.pictureDao = new AbstractDao<PictureEy>();
	}
	
	public Boolean validate() throws Exception {
		PictureEy picture = new PictureEy();
		Map<String, Object> formErrors = new HashMap<String, Object>();
		MultiPartRequest multiPartRequest = new MultiPartRequest(request, (Constants.FILE_UPLOAD_MAX_SIZE * 1024 * 1024));
		
		try {
			WebUtils.validateMandatoryParameters(multiPartRequest, new String[] {"picture_name", "album_name"});
			
			String pictureName = WebUtils.getParameter(multiPartRequest, "picture_name");
			if(!StringUtils.isAlphanumeric(pictureName)) {
				formErrors.put("picture_name", "El campo debe ser alfanumerico");
			
			} else {
				picture.setPictureName(pictureName);
			}
			
			String albumName = WebUtils.getParameter(multiPartRequest, "album_name");
			if(!StringUtils.isAlphanumeric(albumName)) {
				formErrors.put("album_name", "El campo debe ser alfanumerico");
			
			} else {
				picture.setAlbumName(albumName);
			}
			
			String url = WebUtils.getParameter(multiPartRequest, "url");
			if(StringUtils.isNotBlank(url) && !WebUtils.validateUrl(url)) {
				formErrors.put("url", "Url invalida");
			
			} else {
				picture.setUrl(url);
			}
			
			picture.setContent(new Blob(((UploadedFile) multiPartRequest.getFiles().nextElement()).getContent().toByteArray()));
			picture.setTags(WebUtils.getParameter(multiPartRequest, "tags"));
			
			if(picture.getContent() == null && StringUtils.isBlank(picture.getUrl())) {
				formErrors.put("add_url_or_file", "Debes cargar una imagen o asociar una URL que contenga una");
			}
			
		} catch(ValidateMandatoryParameterException e) {
			formErrors.put("mandatory_parameters", "Faltan parametros obligatorios");
		}
		
		if(!formErrors.isEmpty()) {
			errors.put("form_errors", formErrors);
			
			if(!formErrors.containsKey("mandatory_parameters")) {
				session.setElement("picture", picture);
			}
			
			session.setElement("errors", errors);
			SessionManager.save(request, session);
			
			return false;
		}
		
		session.setElement("picture", picture);
		SessionManager.save(request, session);
		
		return true;
	}
	
	public void save() throws Exception {
		PictureEy picture = (PictureEy) session.getElement("picture");
		picture.setPictureId(Aleatory.getAleatoryString(15));
		picture.setUsername(((UserEy) session.getElement("user")).getUsername());
		picture.setDateCreated(new Date());
		picture.setDateUpdated(picture.getDateCreated());
		
		pictureDao.persist(picture);
	}
	
	/*
	private Blob getImageFromRequest() throws CanNotGetFileFromRequestException {
		BlobstoreService blobstoreService = null;
		FileService fileService = null;
		ImageObject imageObject = null;
		
		try {
			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			fileService = FileServiceFactory.getFileService();
			Map<String, BlobKey> images = blobstoreService.getUploadedBlobs(request);
		
			if(images.get("picture_file") != null) {
				imageObject = new ImageObject();
				BlobKey blobKey = images.get("picture_file");
				AppEngineFile imageFile = fileService.getBlobFile(blobKey);
				FileReadChannel readChannel = fileService.openReadChannel(imageFile, false);
		        ByteBuffer byteBuffer = ByteBuffer.allocate(Constants.MAX_READ_BUFFER_SIZE);
		        
		        Integer numberOfBytes = 0;
	            while ((numberOfBytes = readChannel.read(byteBuffer)) != -1) {
	            	imageObject.appendData(byteBuffer.array(), numberOfBytes);
	                byteBuffer = ByteBuffer.allocate(Constants.MAX_READ_BUFFER_SIZE);
	            }
	
		         readChannel.close();
			}
		         
        } catch(Exception e) {
        	log.error(e);
        	
        	throw new CanNotGetFileFromRequestException("Can't get file from request", e);
        }
	        
	    return new Blob(imageObject.getData());
	}
	*/
	
}