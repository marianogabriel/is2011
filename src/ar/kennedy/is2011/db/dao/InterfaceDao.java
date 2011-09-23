package ar.kennedy.is2011.db.dao;

import java.util.List;
import java.util.Vector;

import ar.kennedy.is2011.db.exception.DeleteEntityException;
import ar.kennedy.is2011.db.exception.EntityNotFoundException;
import ar.kennedy.is2011.db.exception.PersistException;

/**
 * @author mlabarinas
 */
public interface InterfaceDao<T> {

	public <D> T findById(Class<T> clase, D str) throws EntityNotFoundException;
	public void persist(T obj) throws PersistException;
	public <D> void remove(Class<T> clase, D str) throws DeleteEntityException;
	public List<T> select(Class<T> clase) throws EntityNotFoundException;
	public T createSingleQuery(String ejbQuery, Vector<Object> parameters) throws EntityNotFoundException;
	public List<T> createCollectionQuery(String ejbQuery, Vector<Object> parameters) throws EntityNotFoundException;
	public void close();
	
}
