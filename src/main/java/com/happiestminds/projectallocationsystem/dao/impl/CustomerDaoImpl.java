package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.CustomerDAO;
import com.happiestminds.projectallocationsystem.entity.CustomerEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<CustomerEntity, String> implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected CustomerDaoImpl() {
		super(CustomerEntity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.dao.CustomerDAO#fetchAllCustomerIds
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchAllCustomerIds() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CustomerEntity.class);
		criteria.setProjection(Projections.property("customerID"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> fetchAllCustomers(int startIndex, int pageSize, String sortVar) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CustomerEntity.class);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		if (sortVar != null) {
			String[] sortInfo = sortVar.split(" ");
			String sortOrder = sortInfo[1];
			if (sortOrder.equalsIgnoreCase("ASC")) {
				criteria.addOrder(Order.asc(sortInfo[0]));
			} else {
				criteria.addOrder(Order.desc(sortInfo[0]));
			}
		}
		return criteria.list();
	}
	
	@Override
	public int getCountAllCustomers(){
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from CustomerEntity").uniqueResult()).intValue();
		return count;
		
	}
}
