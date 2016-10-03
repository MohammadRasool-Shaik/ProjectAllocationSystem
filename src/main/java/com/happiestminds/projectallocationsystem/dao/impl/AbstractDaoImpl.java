package com.happiestminds.projectallocationsystem.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.happiestminds.projectallocationsystem.dao.AbstractDAO;

/**
 * @author rasool.shaik
 * 
 */

public abstract class AbstractDaoImpl<E extends Object, Id extends Serializable> implements AbstractDAO<E, Id> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<E> entityClass;

	protected AbstractDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;

	}

	@Override
	public E save(E entity) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.clear();
		currentSession.persist(entity);
		currentSession.flush();
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.dao.AbstractDAO#update(java
	 * .lang.Object)
	 */
	@Override
	public E update(E entity) throws HibernateException {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.clear();
		currentSession.merge(entity);
		currentSession.flush();
		return entity;

	}

	@Override
	public E saveOrUpdate(E entity) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.clear();
		currentSession.saveOrUpdate(entity);
		currentSession.flush();
		return entity;
	}

	@Override
	public E saveOrMerge(E entity) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.clear();
		currentSession.merge(entity);
		currentSession.flush();
		return entity;
	}

	@Override
	public void delete(E entity) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(entity);
	}

	@Override
	public int deleteById(String entity, Id id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete FROM " + entity + " WHERE id=:id");
		query.setParameter("id", id);
		return query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public E findById(Id id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (E) currentSession.get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entityClass);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> findByCriteria(Criterion criterion) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entityClass);
		criteria.add(criterion);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> findAll(String entityName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM " + entityName);
		return query.list();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() throws HibernateException {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((entityClass == null) ? 0 :
	 * entityClass.hashCode()); result = prime * result + ((sessionFactory ==
	 * null) ? 0 : sessionFactory.hashCode()); return result; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; AbstractDaoImpl other = (AbstractDaoImpl) obj; if
	 * (entityClass == null) { if (other.entityClass != null) return false; }
	 * else if (!entityClass.equals(other.entityClass)) return false; if
	 * (sessionFactory == null) { if (other.sessionFactory != null) return
	 * false; } else if (!sessionFactory.equals(other.sessionFactory)) return
	 * false; return true; }
	 */

}
