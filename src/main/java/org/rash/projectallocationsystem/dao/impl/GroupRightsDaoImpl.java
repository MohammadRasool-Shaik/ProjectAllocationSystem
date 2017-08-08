package org.rash.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.rash.projectallocationsystem.dao.GroupRightsDAO;
import org.rash.projectallocationsystem.entity.GroupRightsEntity;
import org.rash.projectallocationsystem.entity.GroupRightsPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class GroupRightsDaoImpl extends AbstractDaoImpl<GroupRightsEntity, GroupRightsPK> implements GroupRightsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public GroupRightsDaoImpl() {
		super(GroupRightsEntity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rash.projectallocationsystem.dao.GroupRightsDAO#
	 * revokeGroupRightsFromGroup()
	 */
	@Override
	public void revokeGroupRightsFromGroup(String groupId, String operationId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Query createQuery = null;
		//TODO : u can implement a filter for this
		if (operationId != null) {
			createQuery = session.createQuery("delete GroupRightsEntity where groupRightPk.groupId=:groupId and groupRightPk.operationId=:operationId");
			createQuery.setParameter("operationId", operationId);
		} else {
			createQuery = session.createQuery("delete GroupRightsEntity where groupRightPk.groupId=:groupId");
		}
		createQuery.setParameter("groupId", groupId);
		createQuery.executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchGroupRightsByGroup(String groupId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(GroupRightsEntity.class);
		criteria.add(Restrictions.eq("groupRightPk.groupId", groupId));
		criteria.setProjection(Projections.property("groupRightPk.operationId"));
		return (List<String>) criteria.list();
	}
}
