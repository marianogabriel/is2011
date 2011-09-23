package ar.kennedy.is2011.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mlabarinas
 */
@Entity
@Table(name="USERS")
public class UserEy implements Serializable {
	
	private static final long serialVersionUID = -2548441551324306490L;

	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="STATUS")
	private String status;
		
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;
	
	public UserEy() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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