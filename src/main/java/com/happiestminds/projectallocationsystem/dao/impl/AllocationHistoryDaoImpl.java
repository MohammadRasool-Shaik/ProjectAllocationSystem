package com.happiestminds.projectallocationsystem.dao.impl;

import com.happiestminds.projectallocationsystem.dao.AllocationHistoryDAO;
import com.happiestminds.projectallocationsystem.entity.AllocationHistoryEntity;

public class AllocationHistoryDaoImpl extends AbstractDaoImpl<AllocationHistoryEntity, String> implements AllocationHistoryDAO {

	protected AllocationHistoryDaoImpl() {
		super(AllocationHistoryEntity.class);
	}
}
