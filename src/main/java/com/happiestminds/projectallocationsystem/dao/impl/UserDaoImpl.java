package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity, String> implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected UserDaoImpl() {
		super(UserEntity.class);
	}

	@Override
	public UserEntity findByUserName(String userName) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (UserEntity) criteria.uniqueResult();
	}

	@Override
	public UserEntity findByUserByEmail(String emailId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("emailId", emailId));
		return (UserEntity) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.dao.UserDAO#getAllUsersByGroups
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAllUsers() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserEntity.class, "u");
		criteria.createAlias("u.userGroup", "ug", CriteriaSpecification.INNER_JOIN);
		return (List<UserEntity>) criteria.list();
	}

	@Override
	public void updateUserbyId(UserEntity user, String groupId) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("update UserEntity u set u.emailId=:emailId , u.accountStatus=:accountStatus, u.currentlyBilled=:currentlyBilled, u.userGroup.groupId=:groupId where u.userId=:userId");
		query.setParameter("emailId", user.getEmailId());
		query.setParameter("accountStatus", user.getAccountStatus());
		query.setParameter("currentlyBilled", user.getCurrentlyBilled());
		query.setParameter("groupId", groupId);
		query.setParameter("userId", user.getUserId());
		query.executeUpdate();
	}

	@Override
	public void updateUserPWD(String userName,String password ) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update UserEntity u set u.password=:password where u.userName=:userName");
		query.setParameter("password", password);
		query.setParameter("userName", userName);
		query.executeUpdate();
	}

}
