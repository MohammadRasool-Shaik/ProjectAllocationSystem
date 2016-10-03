package com.happiestminds.projectallocationsystem.service;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.CustomerDto;
import com.happiestminds.projectallocationsystem.Dto.batch.CustomerListDto;
import com.happiestminds.projectallocationsystem.response.CustomerOptionsResponse;

/**
 * @author rasool.shaik
 * 
 */
public interface CustomerService {
	@Transactional(readOnly = false)
	void addCustomer(CustomerDto customer);

	@Transactional(readOnly = false)
	void updateCustomer(CustomerDto customer);

	@Transactional
	void deleteCustomer(CustomerDto customer);

	@Transactional(readOnly = true)
	CustomerListDto fetchCustomers(int startIndex, int pageSize, String sortVar);

	@Transactional(readOnly = true)
	int getCountAllCustomers();

	@Transactional(readOnly = true)
	CustomerOptionsResponse getAllCustomers();

}
