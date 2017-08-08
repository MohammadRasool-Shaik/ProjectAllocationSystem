package org.rash.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.entity.OperationEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface OperationDAO extends AbstractDAO<OperationEntity, String> {
	public List<OperationEntity> fetchOperationsWithModule() throws HibernateException;

	public List<String> fetchAllOperationIds()throws HibernateException;

}
