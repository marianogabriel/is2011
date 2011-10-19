package ar.kennedy.is2011.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;

/**
 * @author mlabarinas
 */
public class SearchPicturesModel extends AbstractModel {

	AbstractDao<PictureEy> pictureDao;
	
	private static final String PICTURE_BY_USER_QUERY = "SELECT e FROM PictureEy e WHERE e.username = :1";
	private static final String LAST_PICTURE_UPLOAD_BY_USER_QUERY = "SELECT e FROM PictureEy e ORDER BY e.dateCreated DESC";
	private static final String PICTURE_BY_TAGS = "SELECT e FROM PictureEy e WHERE e.tags IN (:1)";
	
	public SearchPicturesModel() {
		super();
		
		this.pictureDao = new AbstractDao<PictureEy>();
	}
	
	public List<PictureEy> getPicturesByUsername(String username) {
		List<PictureEy> pictures = null;
		
		try {
			pictures = pictureDao.createCollectionQuery(PICTURE_BY_USER_QUERY, new Vector<Object>(Arrays.asList(new String[] {username})));
		
			return pictures;
			
		} catch(EntityNotFoundException e) {
			return new ArrayList<PictureEy>();
		}
	}
	
	public PictureEy getLastPictureUploadByUser(String username) {
		List<PictureEy> pictures = null;
		
		try {
			pictures = pictureDao.createCollectionQuery(LAST_PICTURE_UPLOAD_BY_USER_QUERY, null);
		
			return pictures.get(0);
			
		} catch(EntityNotFoundException e) {
			return null;
		}
	}
	
	public List<PictureEy> getPicturesByTags(Vector<Object> tags) {
		List<PictureEy> pictures = null;

		try {
			pictures = pictureDao.createCollectionQuery(PICTURE_BY_TAGS, tags);

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return pictures;
	}
	
}