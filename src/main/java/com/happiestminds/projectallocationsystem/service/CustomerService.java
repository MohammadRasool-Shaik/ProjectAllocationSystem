package com.happiestminds.projectallocationsystem.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.entity.CustomerEntity;

/**
 * @author rasool.shaik
 *
 */
public interface CustomerService {
	@Transactional(readOnly=false)
	boolean addCustomer(CustomerEntity customer);

	@Transactional(readOnly=false)
	boolean updateCustomer(CustomerEntity customer);

	@Transactional
	boolean deleteCustomer(CustomerEntity customer);

	@Transactional(readOnly=true)
	List<CustomerEntity> fetchCustomers();
}
