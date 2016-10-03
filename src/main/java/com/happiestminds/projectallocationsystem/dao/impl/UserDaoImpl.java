package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.Dto.UserDto;
import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.enumerator.UserStatus;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity, Integer> implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected UserDaoImpl() {
		super(UserEntity.class);
	}

	@Override
	public UserEntity findByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (UserEntity) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findByUserNameOREmail(UserDto user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		Restrictions.or(Restrictions.eq("userName", user.getUserName()), Restrictions.eq("emailId", user.getEmailId()));
		criteria.add(Restrictions.or(Restrictions.eq("userName", user.getUserName()), Restrictions.eq("emailId", user.getEmailId())));
		return (List<UserEntity>) criteria.list();
	}

	@Override
	public UserEntity findByUserByEmail(String emailId) {
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
	public List<UserEntity> getAllUsers(int startIndex, int pageSize, String sortVar) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserEntity.class, "u");
		criteria.createAlias("u.userGroup", "ug", CriteriaSpecification.INNER_JOIN);
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
		}
		return (List<UserEntity>) criteria.list();
	}

	@Override
	public int updateUserbyName(UserDto user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update UserEntity u set  u.accountStatus=:accountStatus, u.userGroup.groupId=:groupId where u.userName=:userName");
		query.setParameter("accountStatus", UserStatus.value(Integer.valueOf(user.getAccountStatus())));
		query.setParameter("groupId", user.getUserGroupId());
		query.setParameter("userName", user.getUserName());
		return query.executeUpdate();
	}

	@Override
	public int updateUserPWD(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update UserEntity u set u.password=:password where u.userName=:userName");
		query.setParameter("password", password);
		query.setParameter("userName", userName);
		return query.executeUpdate();
	}

	@Override
	public int deleteUserByName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete FROM UserEntity u where u.userName=:userName");
		query.setParameter("userName", userName);
		return query.executeUpdate();
	}

	@Override
	public int getAllUsersCount() {
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from UserEntity").uniqueResult()).intValue();
		return count;
	}

}
