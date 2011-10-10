package ar.kennedy.is2011.models;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.kennedy.is2011.db.dao.AbstractDao;
import ar.kennedy.is2011.db.entities.UserEy;
import ar.kennedy.is2011.exception.UserNotExistException;
import ar.kennedy.is2011.exception.ValidateMandatoryParameterException;
import ar.kennedy.is2011.session.Session;
import ar.kennedy.is2011.session.SessionManager;
import ar.kennedy.is2011.utils.Aleatory;
import ar.kennedy.is2011.utils.WebUtils;

/**
 * @author mlabarinas
 */
public class LoginModel extends AbstractModel {

	AbstractDao<UserEy> userDao = new AbstractDao<UserEy>();
	
	public LoginModel() {
		super();
	}
	
	public Boolean validateLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {	
			WebUtils.validateMandatoryParameters(request, new String[] {"username", "password"});
			String username = WebUtils.getParameter(request, "username");
			String password = WebUtils.getParameter(request, "password");
		
			return validateLogin(request, response, username, password);
			
		} catch(ValidateMandatoryParameterException e) {
			errors.put("login_fail", "Faltan parametros obligatorios");
			return false;
		}
		
	}

	public Boolean validateLogin(HttpServletRequest request, HttpServletResponse response, String userId, String password) throws Exception {
		Session session = null;
		
		try {
			UserEy userEy = userDao.findById(UserEy.class, userId);
			
			if(validate(userEy, password)) {
				session = createLoginSession(request, response);
				session.setElement("user", userEy);
				SessionManager.save(request, session);

				return true;
				
			} else {
				errors.put("login_fail", "Fallo en la autenticacion");
				return false;
			}
			
		} catch(UserNotExistException e) {
			errors.put("login_fail", "El usuario no existe");
			return false;
		
		} catch(ValidateMandatoryParameterException e) {
			errors.put("login_fail", "Faltan parametros obligatorios");
			return false;
		}
	}
	
	private Boolean validate(UserEy userEy, String password) throws Exception {
		if(userEy != null) {
			return WebUtils.decrypt(userEy.getPassword()).equals(password);
			
		} else {
			throw new UserNotExistException("User not exist");
		}
	}
	
	protected Session createLoginSession(HttpServletRequest request, HttpServletResponse response) {
		Session session = null;
		String sessionIdentificator = WebUtils.createSessionIdentificator();
		String sessionValidate = Aleatory.getAleatoryString(10);
		
		WebUtils.setCookie(response, new Cookie("sessionIdentificator", WebUtils.encrypt(sessionIdentificator)));
		WebUtils.setCookie(response, new Cookie("sessionValidate", WebUtils.encrypt(sessionValidate)));
		
		session = SessionManager.create(request, sessionIdentificator);
		session.setElement("sessionValidate", sessionValidate);
		
		return session;
	}
	
}