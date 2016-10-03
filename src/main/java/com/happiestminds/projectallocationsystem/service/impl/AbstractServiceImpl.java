package com.happiestminds.projectallocationsystem.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happiestminds.projectallocationsystem.exception.DataException;


/**
 * @author rasool.shaik
 * 
 */
public abstract class AbstractServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(AbstractServiceImpl.class.getSimpleName());

	/**
	 * 
	 */
	public AbstractServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void throwChildValidatationError(String entityName, String id) {
		logger.error("Requested {} id:{} has some active (not deleted)entities, please delete all child entities first", new Object[] { entityName, id });
		throw new DataException("Requested " + entityName + " id:" + id + " has some active (not deleted)entities, please delete all child entities first");
	}

	public void throwAlredyExistValidationError(String entityName, String id) {
		logger.error("Requested {} id:{} is already exist", new Object[] { entityName, id });
		throw new DataException("Requested " + entityName + " id : " + id + " is alredy exist");
	}

}
