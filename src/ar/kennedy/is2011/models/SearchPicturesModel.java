package ar.kennedy.is2011.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.AlbumEy;
import ar.kennedy.is2011.db.entities.PictureEy;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;

/**
 * @author mlabarinas
 */
public class SearchPicturesModel extends AbstractModel {

	private AbstractDao<PictureEy> pictureDao;
	private AbstractDao<AlbumEy> albumDao;
	
	private static final String PICTURE_BY_USER_QUERY = "SELECT e FROM PictureEy e WHERE e.username = :1";
	private static final String LAST_PICTURE_UPLOAD_BY_USER_QUERY = "SELECT e FROM PictureEy e WHERE e.username = :1 ORDER BY e.dateCreated DESC";
	private static final String ALBUMS_TO_BE_DISPAYED_BY_VISIBILITY_QUERY = "SELECT a FROM AlbumEy a WHERE a.visibility = :1";
	private static final String ALBUMS_TO_BE_DISPAYED_BY_OWNER_QUERY = "SELECT a FROM AlbumEy a WHERE a.owner = :1";
	@SuppressWarnings("unused")
	private static final String PICTURES_TO_BE_DISPAYED_BY_USER_QUERY = "SELECT e FROM PictureEy e WHERE e.albumId IN (:1)";
	private static final String PICTURE_BY_ALBUM_QUERY = "SELECT e FROM PictureEy e WHERE e.albumId = :1";
	private static final String ALL_ALBUMS = "SELECT a FROM AlbumEy a ";

	
private static final String PICTURE_BY_TAGS = "SELECT e FROM PictureEy e WHERE e.tags IN (:1)";
	
	public SearchPicturesModel() {
		super();
		
		this.pictureDao = new AbstractDao<PictureEy>();
		this.albumDao = new AbstractDao<AlbumEy>();
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
			pictures = pictureDao.createCollectionQuery(LAST_PICTURE_UPLOAD_BY_USER_QUERY, new Vector<Object>(Arrays.asList(new String[] {username})));
		
			return pictures.size() > 0 ? pictures.get(0) : null;
			
		} catch(EntityNotFoundException e) {
			return null;
		}
	}
	
	public List<PictureEy> getPicturesByTags(Vector<Object> tags) {
		List<PictureEy> pictures = null;

		try {
			pictures = pictureDao.createCollectionQuery(PICTURE_BY_TAGS, tags);

		} catch (EntityNotFoundException e) {
			return new ArrayList<PictureEy>();
		}

		return pictures;
	}
	
	public Set<AlbumEy> getAlbumsToBeDisplayedByUser(String username) {
		List<AlbumEy> albumsByVisibility = null;
		List<AlbumEy> albumsByOwner = null;
		Set<AlbumEy> albums = new HashSet<AlbumEy>();
		
		try {
			albumsByVisibility = albumDao.createCollectionQuery(ALBUMS_TO_BE_DISPAYED_BY_VISIBILITY_QUERY, new Vector<Object>(Arrays.asList(new String[] {"public"})));
			albumsByOwner = albumDao.createCollectionQuery(ALBUMS_TO_BE_DISPAYED_BY_OWNER_QUERY, new Vector<Object>(Arrays.asList(new String[] {username})));
			
			albums.addAll(albumsByVisibility);
			albums.addAll(albumsByOwner);
			
			return albums;
			
		} catch(EntityNotFoundException e) {
			return new HashSet<AlbumEy>();
		}
	}
	
	public List<AlbumEy> getAllAlbums() {
		List<AlbumEy> albums = null;
		
		try {
			albums = albumDao.createCollectionQuery(ALL_ALBUMS, null);
			
			return albums;
			
		} catch(EntityNotFoundException e) {
			return new ArrayList<AlbumEy>();
		}
	}
	
	public List<PictureEy> getPicturesToBeDisplayedByUser(String username) {
		List<PictureEy> pictures = new ArrayList<PictureEy>();
		Set<AlbumEy> albums = getAlbumsToBeDisplayedByUser(username);
		
		if(albums.size() > 0) {
			for(AlbumEy album : albums) {
				pictures.addAll(getPictureByAlbum(album.getAlbumId()));
			}
				
		} else {
			pictures = new ArrayList<PictureEy>();
		}
	
		return pictures;
	}
	
	public List<PictureEy> getPictureByAlbum(String albumId) {
		List<PictureEy> pictures = null;

		try {
			pictures = pictureDao.createCollectionQuery(PICTURE_BY_ALBUM_QUERY, new Vector<Object>(Arrays.asList(new String[] {albumId})));

		} catch (EntityNotFoundException e) {
			return new ArrayList<PictureEy>();
		}

		return pictures;
	}
	
	@SuppressWarnings("unused")
	private String getAlbumsSplit(Set<AlbumEy> albums) {
		StringBuilder splitAlbums = new StringBuilder();
		
		int i = 0;
		for(AlbumEy album : albums) {
			if(i == (albums.size() - 1)) {
				splitAlbums.append(album.getAlbumId());
				
			} else {
				splitAlbums.append(album.getAlbumId()).append(",");
			}
			
			i++;
		}
		
		return splitAlbums.toString();
	}
	
}