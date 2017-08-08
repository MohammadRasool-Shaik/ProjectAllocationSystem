package org.rash.projectallocationsystem.dao.impl;

import org.rash.projectallocationsystem.dao.AllocationHistoryDAO;
import org.rash.projectallocationsystem.entity.AllocationHistoryEntity;

public class AllocationHistoryDaoImpl extends AbstractDaoImpl<AllocationHistoryEntity, String> implements AllocationHistoryDAO {

	protected AllocationHistoryDaoImpl() {
		super(AllocationHistoryEntity.class);
	}
}
