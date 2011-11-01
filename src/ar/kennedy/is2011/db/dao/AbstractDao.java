package ar.kennedy.is2011.db.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import ar.kennedy.is2011.db.exception.DeleteEntityException;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;
import ar.kennedy.is2011.db.exception.PersistException;
import ar.kennedy.is2011.db.manager.ManagerAccess;

import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author mlabarinas
 */
public class AbstractDao<T> implements InterfaceDao<T> {
	
	private static final Logger log = Logger.getLogger(AbstractDao.class);
	
	private EntityManager em;
	
	public AbstractDao() {
		super();
		
		em = ManagerAccess.get();
	}
	
	public <D> T findById(Class<T> clazz, D str) throws EntityNotFoundException {
		T generic = null;
		
		log.debug("Find by PK over entity: " + clazz.getClass().getSimpleName());
			
		generic = em.find(clazz, str);
			
		if(generic == null) { 
			throw new EntityNotFoundException("The entity not exist");
		}
			
		log.debug("Entity found");
				
		return generic;
	}
	
	public void persist(T obj) throws PersistException {
		EntityTransaction et = em.getTransaction();
		
		try {
			log.debug("Persist an entity: " + obj.getClass().getSimpleName());
			
			et.begin();
			em.persist(obj);
			et.commit();
			
			log.debug("Entity save");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PersistException("Fail to persist entity in database");
		}
	}
	
	public <D> void remove(Class<T> clazz, D str) throws DeleteEntityException {
		EntityTransaction et = em.getTransaction();
		
		try {
			log.debug("Delete an entity: " + clazz.getClass().getSimpleName());
			
			et.begin();
			em.remove(findById(clazz, str));
			et.commit();
		
			log.debug("Entity deleted");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DeleteEntityException("Fail to delete entity in database");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> select(Class<T> clazz) throws EntityNotFoundException {
		List<T> result = null;
		
		log.debug("Get all object over entity: " + clazz.getClass().getSimpleName());
		
		result = em.createQuery((new StringBuilder()).append("SELECT e FROM ").append(clazz.getName()).append(" e").toString()).getResultList();
		
		if(result == null || result.size() == 0) {
			throw new EntityNotFoundException("The entities not exist");
		}
		
		log.debug("Entities recovered");
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public T createSingleQuery(String ejbQuery, Vector<Object> parameters) throws EntityNotFoundException {
		T result = null;
		
		log.debug("Executing generated query");
		log.debug("Query: " + ejbQuery);
		
		Query query = em.createQuery(ejbQuery);
		
		if(parameters != null) { 
			setParameters(query, parameters);
		}
		
		result = (T) query.getSingleResult();
		
		if(result == null) {
			throw new EntityNotFoundException("The entity not exist");
		}
		
		log.debug("Query executed");
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> createCollectionQuery(String ejbQuery, Vector<Object> parameters) throws EntityNotFoundException {
		List<T> result = null;
		
		log.debug("Executing generated query");
		log.debug("Query: " + ejbQuery);
		
		Query query = em.createQuery(ejbQuery);
		
		if(parameters != null) { 
			setParameters(query, parameters);
		}
		
		result = query.getResultList();
		
		if(result == null) {
			throw new EntityNotFoundException("The entity not exist");
		}
		
		log.debug("Query executed");
		
		return result;
	}
	
	private void setParameters(Query query, Vector<Object> parameters) {
		int i = 0;
		
		for(Object obj : parameters) {
			
			query.setParameter(i+1,obj);
			log.debug("Parameter " + (i+1) + ": " + obj.toString());
			
			i++;
		}
	}
	
}