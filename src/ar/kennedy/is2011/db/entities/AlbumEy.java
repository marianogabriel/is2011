package ar.kennedy.is2011.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mlabarinas
 */
@Entity
@Table(name = "ALBUM")
public class AlbumEy implements Serializable {

	private static final long serialVersionUID = 7596454954378729377L;

	@Id
	@Column(name = "ALBUM_ID")
	private String albumId;
	
	@Column(name = "VISIBILITY")
	private String visibility;
	
	@Column(name = "OWNER")
	private String owner;
	
	public AlbumEy() {
		super();
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}