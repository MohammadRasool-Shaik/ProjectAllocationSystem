package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.ModuleDao;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class ModuleDaoImpl extends AbstractDaoImpl<ModuleEntity, String> implements ModuleDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected ModuleDaoImpl() {
		super(ModuleEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchAllModuleIds() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ModuleEntity.class);
		criteria.setProjection(Projections.property("moduleId"));
		return criteria.list();
	}

}
