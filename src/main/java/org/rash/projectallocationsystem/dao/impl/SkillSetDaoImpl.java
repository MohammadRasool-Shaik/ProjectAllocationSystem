package org.rash.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.rash.projectallocationsystem.dao.SkillSetDAO;
import org.rash.projectallocationsystem.entity.SkillSetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	 * org.rash.projectallocationsystem.dao.SkillSetDAO#fetchAllSkillIds
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SkillSetEntity> fetchAllSkillSets(int startIndex, int pageSize, String sortVar) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SkillSetEntity.class);
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
	public int getSkillSetCount() throws HibernateException {
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from SkillSetEntity").uniqueResult()).intValue();
		return count;
	}
}
