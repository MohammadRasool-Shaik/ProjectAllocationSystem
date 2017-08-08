package org.rash.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.entity.CustomerEntity;

/**
 * @author rasool.shaik
 *
 */
public interface CustomerDAO extends AbstractDAO<CustomerEntity, String> {
	List<String> fetchAllCustomerIds()throws HibernateException;

	List<CustomerEntity> fetchAllCustomers(int startIndex, int pageSize, String sortVar) throws HibernateException;

	int getCountAllCustomers();
}
