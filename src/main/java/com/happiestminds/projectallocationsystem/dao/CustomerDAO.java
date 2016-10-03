package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.entity.CustomerEntity;

/**
 * @author rasool.shaik
 *
 */
public interface CustomerDAO extends AbstractDAO<CustomerEntity, String> {
	List<String> fetchAllCustomerIds()throws HibernateException;
}
