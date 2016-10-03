package com.happiestminds.projectallocationsystem.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

public interface AbstractDAO<E, Id extends Serializable> {

	@Transactional
	E saveOrUpdate(E entity) throws HibernateException;

	E findById(Id id) throws HibernateException;

	void delete(E entity) throws HibernateException;

	List<E> findByCriteria(Criterion criterion) throws HibernateException;

	List<E> findAll(String entityName) throws HibernateException;
}
