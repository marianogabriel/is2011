package ar.kennedy.is2011.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import ar.kennedy.is2011.constants.Constants;
import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.AlbumEy;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.db.entities.Usuario;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;
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
	private Session userSession;
	private AbstractDao<PictureEy> pictureDao;
	private AbstractDao<AlbumEy> albumDao;
	private String action;
	
	
	
	public ImageUploaderModel(HttpServletRequest request, Session userSession, String action) {
		super();
		
		this.request = request;
		this.userSession =  userSession;
		this.pictureDao = new AbstractDao<PictureEy>();
		this.albumDao = new AbstractDao<AlbumEy>();
		this.action = action;
	}
	
	public Boolean validate() throws Exception {
		PictureEy picture = "add".equals(action) ? new PictureEy() : pictureDao.findById(PictureEy.class, WebUtils.getParameter(request, "id"));
		Map<String, Object> formErrors = new HashMap<String, Object>();
		MultiPartRequest multiPartRequest = new MultiPartRequest(request, (Constants.FILE_UPLOAD_MAX_SIZE * 1024 * 1024));
		
		try {
			WebUtils.validateMandatoryParameters(multiPartRequest, new String[] {"picture_name", "album_id"});
			
			String pictureName = WebUtils.getParameter(multiPartRequest, "picture_name");
			if(!StringUtils.isAlphanumericSpace(pictureName)) {
				formErrors.put("picture_name", "El campo debe ser alfanumerico");
			
			} else {
				picture.setPictureName(pictureName);
			}
			
			String albumId = WebUtils.getParameter(multiPartRequest, "album_id");
			if("Elegir".equals(albumId)) {
				formErrors.put("album_id", "Debe asociar seleccionar un album");
			
			} else {
				try {
					if(isNewAlbum(albumId)) {
						AlbumEy album = albumDao.findById(AlbumEy.class, albumId.substring(0, albumId.indexOf(";")));
						
						if("public".equals(album.getAlbumId())) {
							picture.setAlbumId(album.getAlbumId());
						
						} else {
							formErrors.put("album_id", "El album ya existe");
						}
					
					} else {
						picture.setAlbumId(albumId);
					}
						
				} catch (EntityNotFoundException e) {
					AlbumEy nuevoAlbum = new AlbumEy();
					nuevoAlbum.setAlbumId(albumId.substring(0, albumId.indexOf(";")));
					nuevoAlbum.setVisibility(albumId.substring(albumId.indexOf(";") + 1, albumId.length()));
					nuevoAlbum.setOwner(((Usuario) userSession.getElement("user")).getNombreUsr());
					albumDao.persist(nuevoAlbum);
					
					picture.setAlbumId(albumId.substring(0, albumId.indexOf(";")));					
				}
			}
			
			String url = WebUtils.getParameter(multiPartRequest, "url");
			if(StringUtils.isNotBlank(url) && !WebUtils.validateUrl(url)) {
				formErrors.put("url", "Url invalida");
			
			} else {
				picture.setUrl(url);
			}
			
			if(multiPartRequest.getFiles().hasMoreElements()) {
				UploadedFile uploadPicture = (UploadedFile) multiPartRequest.getFiles().nextElement();
				
				if(uploadPicture.getContent().size() < Constants.ENTITY_WEIGHT) {
					picture.setContentType(uploadPicture.getContentType());
					picture.setContent(new Blob(((UploadedFile) multiPartRequest.getFiles().nextElement()).getContent().toByteArray()));
					
				} else {
					picture.setContentType(uploadPicture.getContentType());
					picture.setContent(new Blob(WebUtils.resize(((UploadedFile) multiPartRequest.getFiles().nextElement()).getContent().toByteArray(), 800, 600)));
				}
			}
			
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
				userSession.setElement("picture", picture);
			}
			
			userSession.setElement("errors", errors);
			SessionManager.save(request, userSession);
			
			return false;
		}
		
		userSession.setElement("picture", picture);
		SessionManager.save(request, userSession);
		
		return true;
	}
	
	public void save() throws Exception {
		PictureEy picture = (PictureEy) userSession.getElement("picture");
		picture.setPictureId(Aleatory.getAleatoryString(15));
		picture.setUsername(((Usuario) userSession.getElement("user")).getNombreUsr());
		picture.setDateCreated(new Date());
		picture.setDateUpdated(picture.getDateCreated());
		
		pictureDao.persist(picture);
	}
	
	public void update() throws Exception {
		PictureEy picture = (PictureEy) userSession.getElement("picture");
		picture.setDateUpdated(new Date());
		
		pictureDao.persist(picture);
	}
	
	public void delete() throws Exception {
		WebUtils.validateMandatoryParameters(request, new String[] {"id"});
		
		pictureDao.remove(PictureEy.class, WebUtils.getParameter(request, "id"));
	}
	
	private Boolean isNewAlbum(String value) {
		return value.indexOf(";") > 0;
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