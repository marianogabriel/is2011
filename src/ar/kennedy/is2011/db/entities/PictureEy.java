package ar.kennedy.is2011.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.appengine.api.datastore.Blob;

/**
 * @author mlabarinas
 */
@Entity
@Table(name = "PICTURE")
public class PictureEy implements Serializable {

	private static final long serialVersionUID = 7033982200449402813L;

	@Id
	@Column(name = "PICTURE_ID")
	private String pictureId;

	@Column(name = "PICTURE_NAME")
	private String pictureName;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "ALBUM_ID")
	private String albumId;

	@Column(name = "TAGS")
	private String tags;

	@Column(name = "URL")
	private String url;
	
	@Column(name = "CONTENT")
	private Blob content;
	
	@Column(name = "CONTENT_TYPE")
	private String contentType;
	
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Column(name = "DATE_UPDATED")
	private Date dateUpdated;

	public PictureEy() {
		super();
	}
	
	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
}