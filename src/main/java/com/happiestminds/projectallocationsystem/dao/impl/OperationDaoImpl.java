package com.happiestminds.projectallocationsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.OperationDAO;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;

/**
 * @author rasool.shaik
 *
 */
@Repository
public class OperationDaoImpl extends AbstractDaoImpl<OperationEntity, String> implements OperationDAO {

	protected OperationDaoImpl() {
		super(OperationEntity.class);
	}

}
