package tw.org.roadtoadventure.dao.imp;

import java.util.List;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAOImpl<T> extends HibernateDaoSupport implements tw.org.roadtoadventure.dao.BaseDAO<T> {

	protected Class<T> entityClass;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public BaseDAOImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T getById(Serializable id) {
		T t = (T) this.getHibernateTemplate().get(getEntityClass(), id);
		return t;
	}

	@Override
	public List<T> readAll() {
		String hsql = "FROM " + entityClass.getSimpleName();
		return this.getHibernateTemplate().find(hsql);
	}
	
	@Override
	public void merge(T entity) {
		this.getHibernateTemplate().merge(entity);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void create(T entity) {
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(final T entity) {
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void insertOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void evict(T entity) {
		this.getHibernateTemplate().evict(entity);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void clear() {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().flush();
	}

	@Override
	public void flush() {
		this.getHibernateTemplate().flush();
	}

	public int countCriteriaResult(DetachedCriteria detachedCriteria) {		
		detachedCriteria.setProjection(Projections.rowCount());		
		return  DataAccessUtils.intResult(this.getHibernateTemplate().findByCriteria(detachedCriteria));
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public int createReturnPKIntType(T entity) {
		int pk = (int) this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		return  pk;
	}
	@Override
	public String createReturnPKStringType(T entity) {
		String pk = (String) this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		return  pk;
	}
	@Override
	public T createReturnPK(T entity) {
		T t = (T) this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		return  t;
	}

}
