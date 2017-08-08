package org.rash.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;
import org.rash.projectallocationsystem.dao.UserGroupDAO;
import org.rash.projectallocationsystem.entity.GroupRightsEntity;
import org.rash.projectallocationsystem.entity.ModuleEntity;
import org.rash.projectallocationsystem.entity.UserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	 * @see org.rash.projectallocationsystem.dao.UserGroupDAO#
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
	 * @see org.rash.projectallocationsystem.dao.UserGroupDAO#
	 * getAllUsersWithGroups()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupEntity> fetchAllUsersWithGroups(int startIndex, int pageSize, String sortVar) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserGroupEntity.class, "ug");
		criteria.createAlias("ug.users", "u", CriteriaSpecification.LEFT_JOIN);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		if (sortVar != null) {
			String[] sortInfo = sortVar.split(" ");
			String sortOrder = sortInfo[1];
			if (sortOrder.equalsIgnoreCase("ASC")) {
				criteria.addOrder(Order.asc("u." + sortInfo[0]));
			} else {
				criteria.addOrder(Order.desc("u." + sortInfo[0]));
			}
		}
		List<UserGroupEntity> userGroups = (List<UserGroupEntity>) criteria.list();
		return userGroups;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupEntity> fetchAllUsersGroups(int startIndex, int pageSize, String sortVar) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserGroupEntity.class, "ug");
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
		List<UserGroupEntity> userGroups = (List<UserGroupEntity>) criteria.list();
		return userGroups;
	}

	@Override
	public int getCountAllUserGroups() throws HibernateException {
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from UserGroupEntity").uniqueResult()).intValue();
		return count;
	}
}
