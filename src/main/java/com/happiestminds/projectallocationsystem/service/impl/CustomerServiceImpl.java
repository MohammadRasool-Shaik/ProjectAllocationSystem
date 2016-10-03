package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.dao.CustomerDAO;
import com.happiestminds.projectallocationsystem.entity.CustomerEntity;
import com.happiestminds.projectallocationsystem.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class.getSimpleName());

	@Autowired
	private CustomerDAO customerDAO;

	public CustomerServiceImpl() {
		super();
	}

	@Override
	public boolean addCustomer(CustomerEntity customer) {
		boolean isSavedCustomer = false;
		CustomerEntity customerEntity = null;
		try {
			customerEntity = customerDAO.findById(customer.getCustomerID());
			if (customerEntity == null) {
				customer.setCustomerID(customer.getCustomerID().toUpperCase());
				customerDAO.save(customer);
				isSavedCustomer = true;
			}
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED  WHILE SAVING CUTOMER INTO CUSTOMER ENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE SAVING CUTOMER INTO CUSTOMER ENTITY" + ex.getMessage(), ex);
		}

		return isSavedCustomer;
	}

	@Override
	public boolean updateCustomer(CustomerEntity customer) {
		boolean isUpdatedCustomer = false;
		try {
			customerDAO.update(customer);
			isUpdatedCustomer = true;
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED  WHILE UPDATING CUTOMER INTO CUSTOMER ENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE UPDATING CUTOMER INTO CUSTOMER ENTITY" + ex.getMessage(), ex);
		}

		return isUpdatedCustomer;
	}

	@Override
	public boolean deleteCustomer(CustomerEntity customer) {
		boolean isCustomerDeleted = false;
		try {
			customerDAO.deleteById("CustomerEntity", customer.getCustomerID());
			isCustomerDeleted = true;
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE DELTEING CUTOMER FROM CUSTOMER ENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE DELTEING CUTOMER FROM CUSTOMER ENTITY" + ex.getMessage(), ex);
		}
		return isCustomerDeleted;
	}

	@Override
	public List<CustomerEntity> fetchCustomers() {
		List<CustomerEntity> customers = null;
		try {
			customers = (List<CustomerEntity>) customerDAO.findAll();

		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE FETCHING CUTOMER FROM CUSTOMER ENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE FETCHING CUTOMER FROM CUSTOMER ENTITY" + ex.getMessage(), ex);
		}
		return customers;
	}

}
