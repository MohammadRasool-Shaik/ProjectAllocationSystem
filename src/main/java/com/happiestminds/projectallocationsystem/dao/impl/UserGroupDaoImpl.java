package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.UserGroupDAO;
import com.happiestminds.projectallocationsystem.entity.GroupRightsEntity;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class UserGroupDaoImpl extends AbstractDaoImpl<UserGroupEntity, String> implements UserGroupDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected UserGroupDaoImpl() {
		super(UserGroupEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Deprecated
	public List<ModuleEntity> fetchOperationsByGroup(String groupId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(GroupRightsEntity.class, "gr");
		criteria.createAlias("gr.operation", "o", JoinFragment.RIGHT_OUTER_JOIN);
		criteria.createAlias("o.module", "m", JoinFragment.INNER_JOIN);
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.dao.UserGroupDAO#
	 * fetchGroupRightsByGroup(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GroupRightsEntity> fetchGroupRightsByGroup(String groupId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria groupRightsCriteria = session.createCriteria(GroupRightsEntity.class, "gr");
		groupRightsCriteria.add(Restrictions.eq("gr.groupRightPk.groupId", groupId));
		return groupRightsCriteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.dao.UserGroupDAO#
	 * getAllUsersWithGroups()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupEntity> fetchAllUsersWithGroups() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserGroupEntity.class, "ug");
		criteria.createAlias("ug.user", "u", CriteriaSpecification.LEFT_JOIN);
		List<UserGroupEntity> userGroups = (List<UserGroupEntity>) criteria.list();
		return userGroups;
	}
}
