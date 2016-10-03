package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.ProjectDAO;
import com.happiestminds.projectallocationsystem.entity.ProjectEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class ProjectDaoImpl extends AbstractDaoImpl<ProjectEntity, String> implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected ProjectDaoImpl() {
		super(ProjectEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchAllProjectIds() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProjectEntity.class);
		criteria.setProjection(Projections.property("projectID"));
		
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.dao.ProjectDAO#
	 * fetchAllProjectWithCustomers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectEntity> fetchAllProjectWithCustomers(int startIndex, int pageSize, String sortVar) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProjectEntity.class, "p");
		criteria.createAlias("p.customer", "c", CriteriaSpecification.INNER_JOIN);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		if (sortVar != null && sortVar!="") {
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
	public int getProjectsCount() throws HibernateException {
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from ProjectEntity").uniqueResult()).intValue();
		return count;
	}
}
