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
	
	@Column(name="BYTES")
	private Blob bytes;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;
	
	public SessionEy() {
		super();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Blob getBytes() {
		return bytes;
	}

	public void setBytes(Blob bytes) {
		this.bytes = bytes;
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