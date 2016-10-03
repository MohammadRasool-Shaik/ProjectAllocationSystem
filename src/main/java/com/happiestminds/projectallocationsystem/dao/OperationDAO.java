package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.entity.OperationEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface OperationDAO extends AbstractDAO<OperationEntity, String> {
	public List<OperationEntity> fetchOperationsWithModule() throws HibernateException;

	public List<String> fetchAllOperationIds()throws HibernateException;

}
