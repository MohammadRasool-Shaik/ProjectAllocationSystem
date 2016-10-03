package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.SkillSetDAO;
import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class SkillSetDaoImpl extends AbstractDaoImpl<SkillSetEntity, String> implements SkillSetDAO {

	@Autowired
	SessionFactory sessionFactory;

	protected SkillSetDaoImpl() {
		super(SkillSetEntity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.dao.SkillSetDAO#fetchAllSkillIds
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchAllSkillSetIds() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SkillSetEntity.class);
		criteria.setProjection(Projections.property("skillID"));
		return criteria.list();
	}
}
