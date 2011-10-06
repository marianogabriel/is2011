package ar.kennedy.is2011.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mlabarinas
 */
public class Session implements Serializable {

	private static final long serialVersionUID = 3625520165016444329L;
	
	protected String id;
	protected Map<String, Object> content = null;
	protected String status;
	
	public Session() {
		super();
		
		this.content = new HashMap<String, Object>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getElement(String key) {
		return this.content.get(key);
	}

	public void setElement(String key, Object value) {
		this.content.put(key, value);
	}

	public void removeElement(String key) {
		this.content.remove(key);
	}
	
	public Map<String, Object> getContent() {
		return this.content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}
	
	public Boolean contains(String key) {
		return content.containsKey(key);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}