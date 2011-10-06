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
@Table(name="SESSION")
public class SessionEy implements Serializable {

	private static final long serialVersionUID = 5536987801884493557L;

	@Id
	@Column(name="SESSION_ID")
	private String sessionId;
	
	@Column(name="CONTENT")
	private Blob content;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DATE_CREATED")
	private Date dateCreated;
	
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;
	
	public SessionEy() {
		super();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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