package com.happiestminds.projectallocationsystem.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;

public interface AbstractDAO<E, Id extends Serializable> {

	E saveOrUpdate(E entity) throws HibernateException;
	
	E saveOrMerge(E entity) throws HibernateException;
	
	E save(E entity) throws HibernateException;

	E findById(Id id) throws HibernateException;

	void delete(E entity) throws HibernateException;
	
	E update(E entity)throws HibernateException;

	Collection<E> findByCriteria(Criterion criterion) throws HibernateException;

	Collection<E> findAll(String entityName) throws HibernateException;
	
	void deleteById(String entity, Id id) throws HibernateException;

	Collection<E> findAll() throws HibernateException;
	
	
}
