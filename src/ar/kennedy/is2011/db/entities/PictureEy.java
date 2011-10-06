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
@Table(name="PICTURE")
public class PictureEy implements Serializable {

	private static final long serialVersionUID = 7033982200449402813L;

	@Id
	@Column(name="PICTURE_ID")
	private String pictureId;
	
	@Column(name="CONTENT")
	private Blob content;
	
	@Column(name="LINK")
	private String link;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;
	
	public PictureEy() {
		super();
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	
	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}