package ar.kennedy.is2011.session;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	
	public static Session create(HttpServletRequest request, String sessionId) {
		log.debug("Create session to id: " + sessionId);
		
		SessionEy sessionEy = null;
		Session session = null;
		
		try {
			session = new Session();
			
			session.setId(sessionId);
			session.setStatus(Constants.SESSION_STATUS_ACTIVE);
			session.setDateCreated(new java.util.Date());
			session.setDateUpdated(session.getDateUpdated());
			
			if(Constants.SESSION_MODE.equals("db")) {
				sessionEy = new SessionEy();
				
				sessionEy.setSessionId(sessionId);
				sessionEy.setDateCreated(session.getDateCreated());
				sessionEy.setDateUpdated(session.getDateUpdated());
				sessionEy.setContent(new Blob(FileUtils.ObjectToByteArray(session)));
				sessionEy.setStatus(session.getStatus());
				
				sessionDao.persist(sessionEy);
			
			} else {
				request.getSession(true).setAttribute(sessionId, session);
			}
			
			log.debug("Session created");
			
		} catch(Exception e) {
			log.error("Can't create session object", e);
			throw new SessionOperationException("Can't create session object", e);
		}
		
		return session;
	}
	
	public static Session get(HttpServletRequest request, String sessionId) {
		SessionEy sessionEy = null;
		Session session = null;
		
		log.debug("Get session for id: " + sessionId);
		
		try {
			if(Constants.SESSION_MODE.equals("db")) {
				sessionEy = sessionDao.findById(SessionEy.class, sessionId);
				
				if(sessionEy != null) {
					session = (Session) FileUtils.byteArrayToObject(sessionEy.getContent().getBytes());
				}
			
			} else {
				session = (Session) request.getSession().getAttribute(sessionId);
			}
		
		} catch(Exception e) {
			log.error("Can't get session object", e);
			throw new SessionOperationException("Can't get session object", e);
		}
		
		return session;
	}
	
	public static void save(HttpServletRequest request, Session session) {
		log.debug("Session save for id: " + session.getId());
		
		try {
			session.setDateUpdated(new Date());
			
			if(Constants.SESSION_MODE.equals("db")) {
				SessionEy sessionEy = new SessionEy();
				
				sessionEy.setSessionId(session.getId());
				sessionEy.setDateCreated(session.getDateCreated());
				sessionEy.setDateUpdated(session.getDateUpdated());
				sessionEy.setContent(new Blob(FileUtils.ObjectToByteArray(session)));
				sessionEy.setStatus(session.getStatus());
			
				sessionDao.persist(sessionEy);
			
			} else {
				request.getSession().setAttribute(session.getId(), session);
			}
			
		} catch(Exception e) {
			log.error("Can't save session object", e);
			throw new SessionOperationException("Can't save session object", e);
		}
	}
	
	public static void delete(HttpServletRequest request, String sessionId) {
		log.debug("Session delete for id: " + sessionId);
		
		try {
			if(Constants.SESSION_MODE.equals("db")) {
				sessionDao.remove(SessionEy.class, sessionId);
			
			} else {
				request.getSession().invalidate();
			}
		
		} catch(Exception e) {
			log.error("Can't delete session object", e);
			throw new SessionOperationException("Can't delete session object", e);
		}
	}
	
}