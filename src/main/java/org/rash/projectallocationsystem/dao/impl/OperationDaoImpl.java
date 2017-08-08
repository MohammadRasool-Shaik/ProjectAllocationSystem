package org.rash.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.rash.projectallocationsystem.dao.OperationDAO;
import org.rash.projectallocationsystem.entity.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class OperationDaoImpl extends AbstractDaoImpl<OperationEntity, String> implements OperationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected OperationDaoImpl() {
		super(OperationEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationEntity> fetchOperationsWithModule() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(OperationEntity.class, "o");
		criteria.createAlias("o.module", "m", CriteriaSpecification.INNER_JOIN);
		criteria.addOrder(Order.asc("o.viewOrder"));
		return (List<OperationEntity>) criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rash.projectallocationsystem.dao.OperationDAO#
	 * fetchAllOperationIds()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchAllOperationIds() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(OperationEntity.class);
		criteria.setProjection(Projections.property("operationId"));
		return (List<String>) criteria.list();
	}
}
