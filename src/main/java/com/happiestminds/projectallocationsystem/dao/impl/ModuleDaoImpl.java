package com.happiestminds.projectallocationsystem.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.dao.ModuleDao;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class ModuleDaoImpl extends AbstractDaoImpl<ModuleEntity, String> implements ModuleDao {

	private static Logger logger = LoggerFactory.getLogger(ModuleDaoImpl.class.getSimpleName());

	@Autowired
	private SessionFactory sessionFactory;

	protected ModuleDaoImpl() {
		super(ModuleEntity.class);
	}

	@Override
	@Transactional
	public void saveInitialData(ModuleEntity module)throws HibernateException {
		try {
			
			
			
			
		} catch (HibernateException hex) {
			logger.error(hex.getClass() + "EXCEPTION OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + hex.getStackTrace());
			throw hex;
		}

	}

}
