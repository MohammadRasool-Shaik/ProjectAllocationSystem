package org.rash.projectallocationsystem.service;

import org.rash.projectallocationsystem.Dto.CustomerDto;
import org.rash.projectallocationsystem.Dto.batch.CustomerListDto;
import org.rash.projectallocationsystem.response.CustomerOptionsResponse;
import org.springframework.transaction.annotation.Transactional;

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
