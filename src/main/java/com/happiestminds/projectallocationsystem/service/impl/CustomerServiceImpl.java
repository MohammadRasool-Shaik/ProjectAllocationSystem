package com.happiestminds.projectallocationsystem.service.impl;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.Dto.CustomerDto;
import com.happiestminds.projectallocationsystem.Dto.CustomerOptionDto;
import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.Dto.batch.CustomerListDto;
import com.happiestminds.projectallocationsystem.dao.CustomerDAO;
import com.happiestminds.projectallocationsystem.entity.CustomerEntity;
import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;
import com.happiestminds.projectallocationsystem.response.CustomerOptionsResponse;
import com.happiestminds.projectallocationsystem.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	public CustomerServiceImpl() {
		super();
	}

	@Override
	public void addCustomer(CustomerDto customer) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		CustomerEntity customerEntity = customerDAO.findById(customer.getCustomerID());
		if (customerEntity == null) {
			customer.setCustomerID(customer.getCustomerID().toUpperCase());
			customerEntity = new CustomerEntity();
			BeanUtils.copyProperties(customer, customerEntity);
			customerDAO.save(customerEntity);
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Customer Added Successfully");
		} else {
			statusDto.setStatusMessage("Customer with same ID Already Exit, Try Another?");
			// throwAlredyExistValidationError(SkillSetDto.class.getSimpleName(),
			// skillSetDto.getSkillID());
		}
		customer.setStatusDto(statusDto);
	}

	@Override
	public void updateCustomer(CustomerDto customer) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customer, customerEntity);
		customerEntity = customerDAO.update(customerEntity);
		if (customerEntity != null) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully updated Customer");
		}
		customer.setStatusDto(statusDto);

	}

	@Override
	public void deleteCustomer(CustomerDto customer) {
		int noOfEntiesDeleted = 0;
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		noOfEntiesDeleted = customerDAO.deleteById("CustomerEntity", customer.getCustomerID());
		CustomerDto customerTemp = new CustomerDto();
		BeanUtils.copyProperties(customerTemp, customer);
		if (noOfEntiesDeleted > 0) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully Deleted Customer");
		}
		customer.setStatusDto(statusDto);
	}

	@Override
	public CustomerListDto fetchCustomers(int startIndex, int pageSize, String sortVar) {
		CustomerListDto customers = new CustomerListDto();
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		Collection<CustomerEntity> customerList = customerDAO.fetchAllCustomers(startIndex, pageSize, sortVar);
		if (!customerList.isEmpty()) {
			for (CustomerEntity customerEntiy : customerList) {
				CustomerDto customer = new CustomerDto();
				BeanUtils.copyProperties(customerEntiy, customer);
				customers.getCustomers().add(customer);
			}
			statusDto.setStatusCode(StatusCodeEnum.OK);
		} else {
			statusDto.setStatusMessage("customers are not available, Contact Administrator");
		}
		customers.setStatusDto(statusDto);

		return customers;
	}

	@Override
	public int getCountAllCustomers() {
		return customerDAO.getCountAllCustomers();
	}

	@Override
	public CustomerOptionsResponse getAllCustomers() {
		CustomerOptionsResponse cuResponse = new CustomerOptionsResponse();
		Collection<CustomerEntity> customerList = customerDAO.findAll();
		cuResponse.setResult("ERROR");
		if (!customerList.isEmpty()) {
			for (CustomerEntity customerEntiy : customerList) {
				CustomerOptionDto customerOptionDto = new CustomerOptionDto();
				customerOptionDto.setDisplayText(customerEntiy.getCustomerName());
				customerOptionDto.setValue(customerEntiy.getCustomerID());
				cuResponse.getOptions().add(customerOptionDto);
			}
			cuResponse.setResult("OK");
		} else {
			cuResponse.setMessage("customers are not available, Contact Administrator");
		}
		return cuResponse;
	}
}
