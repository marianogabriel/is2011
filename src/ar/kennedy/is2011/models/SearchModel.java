package ar.kennedy.is2011.models;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;

/**
 * @author mlabarinas
 */
public class SearchModel extends AbstractModel {

	AbstractDao<PictureEy> pictureDao;
	
	private static final String PICTURE_BY_USER_QUERY = "SELECT e FROM PictureEy e WHERE e.username = :1";
	
	public SearchModel() {
		super();
		
		this.pictureDao = new AbstractDao<PictureEy>();
	}
	
	public List<PictureEy> getPicturesByUsername(String username) {
		List<PictureEy> pictures = null;
		
		try {
			pictures = pictureDao.createCollectionQuery(PICTURE_BY_USER_QUERY, new Vector<Object>(Arrays.asList(new String[] {username})));
		
			return pictures;
			
		} catch(EntityNotFoundException e) {
			return pictures;
		}
	}
	
}