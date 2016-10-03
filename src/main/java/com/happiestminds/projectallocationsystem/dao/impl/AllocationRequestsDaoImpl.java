package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.AllocationRequestsDAO;
import com.happiestminds.projectallocationsystem.entity.AllocationRequestEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class AllocationRequestsDaoImpl extends AbstractDaoImpl<AllocationRequestEntity, Integer> implements AllocationRequestsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected AllocationRequestsDaoImpl() {
		super(AllocationRequestEntity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.dao.AllocationRequestsDAO#
	 * getAllRequests(int, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationRequestEntity> getAllRequests(String requestorId, boolean isPracticeHead, int startIndex, int pageSize, String sortVar) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AllocationRequestEntity.class, "alloc");
		// criteria.createAlias("a.employee", "e");
		// criteria.createAlias("a.requestedSkillSet", "s");
		// criteria.createAlias("a.project", "p");
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		if (sortVar != null && sortVar != "") {
			String[] sortInfo = sortVar.split(" ");
			String sortOrder = sortInfo[1];
			if (sortOrder.equalsIgnoreCase("ASC")) {
				criteria.addOrder(Order.asc(sortInfo[0]));
			} else {
				criteria.addOrder(Order.desc(sortInfo[0]));
			}
		} else {
			criteria.addOrder(Order.desc("requestDate"));
		}
		if (!isPracticeHead && requestorId != null) {
			criteria.add(Restrictions.eq("alloc.requestor.employeeId", requestorId));
		}
		return (List<AllocationRequestEntity>) criteria.list();
	}

	@Override
	public int getAllocationRequestCount() {
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from AllocationRequestEntity").uniqueResult()).intValue();
		return count;
	}

	@Override
	public AllocationRequestEntity saveAllocationRequest(AllocationRequestEntity request) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.clear();
		currentSession.persist(request);
		currentSession.flush();
		return request;
	}

}
