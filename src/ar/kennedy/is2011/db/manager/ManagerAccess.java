package ar.kennedy.is2011.db.manager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author mlabarinas
 */
public final class ManagerAccess {
    
	private static final EntityManager em = Persistence.createEntityManagerFactory("transactions-optional").createEntityManager();

    private ManagerAccess() {}

    public static EntityManager get() {
    	return em;
    }
    
}