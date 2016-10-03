package com.happiestminds.projectallocationsystem.dao.impl;

import com.happiestminds.projectallocationsystem.dao.AllocationRequestsDAO;
import com.happiestminds.projectallocationsystem.entity.AllocationRequestsEntity;

/**
 * @author rasool.shaik
 * 
 */
public class AllocationRequestsDaoImpl extends AbstractDaoImpl<AllocationRequestsEntity, Integer> implements AllocationRequestsDAO {

	protected AllocationRequestsDaoImpl() {
		super(AllocationRequestsEntity.class);
	}

}
