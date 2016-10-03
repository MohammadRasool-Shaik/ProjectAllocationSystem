package com.happiestminds.projectallocationsystem.dao.impl;

import com.happiestminds.projectallocationsystem.dao.CustomerDAO;
import com.happiestminds.projectallocationsystem.entity.CustomerEntity;

/**
 * @author rasool.shaik
 * 
 */
public class CustomerDaoImpl extends AbstractDaoImpl<CustomerEntity, String> implements CustomerDAO {

	protected CustomerDaoImpl() {
		super(CustomerEntity.class);
	}
}
