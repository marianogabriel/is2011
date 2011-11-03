package ar.kennedy.is2011.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.AlbumEy;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;

/**
 * @author mlabarinas
 */
public class SearchAlbumModel extends AbstractModel {

	AbstractDao<AlbumEy> albumDao;
	
	private static final String ALBUM_BY_NAME = "SELECT e FROM AlbumEy e WHERE e.albumName = :1";
	private static final String ALL_ALBUMS = "SELECT e FROM AlbumEy e ";	
	
	public SearchAlbumModel() {
		super();
		
		this.albumDao = new AbstractDao<AlbumEy>();
	}
	
	public List<AlbumEy> getAlbumByName(String name) {
		List<AlbumEy> albums = null;
		
		try {
			albums = albumDao.createCollectionQuery(ALBUM_BY_NAME, new Vector<Object>(Arrays.asList(new String[] {name})));
		
			return albums;
			
		} catch(EntityNotFoundException e) {
			return new ArrayList<AlbumEy>();
		}
	}
	
	
	public List<AlbumEy> getAllAlbums() {
		List<AlbumEy> albums = null;
		
		try {
			albums = albumDao.createCollectionQuery(ALL_ALBUMS, null);

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<AlbumEy>();
		}
		
		return albums;
	}
	
}