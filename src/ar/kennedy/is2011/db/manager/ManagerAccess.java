package ar.kennedy.is2011.db.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author mlabarinas
 */
public final class ManagerAccess {
    
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("transactions-optional");

    private ManagerAccess() {}

    public static EntityManager get() {
    	EntityManager em  = null;
    	
    	try {
    		em = emf.createEntityManager();
        	
        } catch(Exception e) {
        	System.out.println("Fail to get an EntityManager's instance");
        }
        
        return em;
    }
    
}