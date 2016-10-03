package com.happiestminds.projectallocationsystem.dao.impl;

import java.io.Serializable;
import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rash.sampleapp.dao.AbstractDAO#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public E saveOrUpdate(E entity)throws HibernateException {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(entity);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rash.sampleapp.dao.AbstractDAO#findById(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E findById(Id id)throws HibernateException {
		Session currentSession = sessionFactory.getCurrentSession();
		E entity = (E) currentSession.get(entityClass, id);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rash.sampleapp.dao.AbstractDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(E entity)throws HibernateException {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rash.sampleapp.dao.AbstractDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findByCriteria(Criterion criterion)throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entityClass);
		criteria.add(criterion);
		return criteria.list();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory()throws HibernateException {
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
	 * @see org.rash.sampleapp.dao.AbstractDAO#findAll(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll(String entityName)throws HibernateException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("FROM "+entityName  );
		List<E> list = query.list();
		return list;
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
