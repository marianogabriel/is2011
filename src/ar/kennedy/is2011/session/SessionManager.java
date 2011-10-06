package ar.kennedy.is2011.session;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import ar.kennedy.is2011.constants.Constants;
import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.SessionEy;
import ar.kennedy.is2011.exception.SessionOperationException;
import ar.kennedy.is2011.utils.FileUtils;

import com.google.appengine.api.datastore.Blob;

/**
 * @author: mlabarinas
 */
public class SessionManager {
	
	private static final Logger log = Logger.getLogger(SessionManager.class);
	private static final AbstractDao<SessionEy> sessionDao = new AbstractDao<SessionEy>();

	private SessionManager() { }
	
	public static Session create(String sessionId) {
		SessionEy sessionEy = null;
		Session session = null;
		
		try {
			sessionEy = new SessionEy();
			session = new Session();
			
			session.setId(sessionId);
			session.setStatus(Constants.SESSION_STATUS_ACTIVE);
		
			sessionEy.setSessionId(sessionId);
			sessionEy.setDateCreated(new Timestamp(new java.util.Date().getTime()));
			sessionEy.setDateUpdated(sessionEy.getDateUpdated());
			sessionEy.setContent(new Blob(FileUtils.ObjectToByteArray(session)));
			sessionEy.setStatus(session.getStatus());
			
			sessionDao.persist(sessionEy);
			
		} catch(Exception e) {
			log.error("Can't create session object", e);
			throw new SessionOperationException("Can't create session object", e);
		}
		
		return session;
	}
	
	public static Session get(String sessionId) {
		SessionEy sessionEy = null;
		Session session = null;
		
		try {
			sessionEy = sessionDao.findById(SessionEy.class, sessionId);
			
			if(sessionEy != null) {
				session = (Session) FileUtils.byteArrayToObject(sessionEy.getContent().getBytes());
			}
		
		} catch(Exception e) {
			log.error("Can't get session object", e);
			throw new SessionOperationException("Can't get session object", e);
		}
		
		return session;
	}
	
	public void save(Session session) {
		try {
			SessionEy sessionEy = new SessionEy();
			
			sessionEy.setSessionId(session.getId());
			sessionEy.setDateCreated(new java.util.Date());
			sessionEy.setDateUpdated(sessionEy.getDateCreated());
			sessionEy.setContent(new Blob(FileUtils.ObjectToByteArray(session)));
			sessionEy.setStatus(session.getStatus());
		
			sessionDao.persist(sessionEy);
			
		} catch(Exception e) {
			log.error("Can't save session object", e);
			throw new SessionOperationException("Can't save session object", e);
		}
	}
	
	public void delete(String sessionId) {
		try {
			sessionDao.remove(SessionEy.class, sessionId);
		
		} catch(Exception e) {
			log.error("Can't delete session object", e);
			throw new SessionOperationException("Can't delete session object", e);
		}
	}
	
}