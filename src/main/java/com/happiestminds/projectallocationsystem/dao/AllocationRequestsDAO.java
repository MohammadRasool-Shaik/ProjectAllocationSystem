package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import com.happiestminds.projectallocationsystem.entity.AllocationRequestEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface AllocationRequestsDAO extends AbstractDAO<AllocationRequestEntity, Integer> {

	List<AllocationRequestEntity> getAllRequests(String userDetails, boolean isPracticeHead, int startIndex, int pageSize, String sortVar);

	int getAllocationRequestCount();

	AllocationRequestEntity saveAllocationRequest(AllocationRequestEntity request);

}
