package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.GroupRightsDAO;
import com.happiestminds.projectallocationsystem.entity.GroupRightsEntity;
import com.happiestminds.projectallocationsystem.entity.GroupRightsPK;

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
	 * @see com.happiestminds.projectallocationsystem.dao.GroupRightsDAO#
	 * revokeGroupRightsFromGroup()
	 */
	@Override
	public void revokeGroupRightsFromGroup(String groupId, String operationId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Query createQuery = session.createQuery("delete GroupRightsEntity where groupRightPk.groupId=:groupId and groupRightPk.operationId=:operationId");
		createQuery.setParameter("groupId", groupId);
		createQuery.setParameter("operationId", operationId);
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
