package tw.org.roadtoadventure.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDAO<T> {

	public T getById(Serializable id);

	public List<T> readAll();
	
	public void merge(T entity);

	public void create(T entity);

	public void delete(T entity);

	public void update(T entity);
	
	public void insertOrUpdate(T entity);

	public void evict(T entity);
	
	public void clear();

	public void flush();

	public int countCriteriaResult(DetachedCriteria detachedCriteria);

}
