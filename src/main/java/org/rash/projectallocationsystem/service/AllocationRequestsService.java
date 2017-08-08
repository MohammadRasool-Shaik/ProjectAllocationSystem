package org.rash.projectallocationsystem.service;

import java.text.ParseException;

import org.rash.projectallocationsystem.Dto.AllocationRequestDto;
import org.rash.projectallocationsystem.Dto.batch.AllocationRequestListDto;
import org.rash.projectallocationsystem.controller.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rasool.shaik
 * 
 */
public interface AllocationRequestsService {

	@Transactional(readOnly = true)
	AllocationRequestListDto fetchAllocationRequests(UserDetails userDetails, boolean isPracticeHead, int startIndex, int pageSize, String sortVar) throws ParseException;

	@Transactional(readOnly = true)
	int getAllocationRequestsCount();

	@Transactional(readOnly = false)
	void raiseAllocationRequest(UserDetails userDetails, AllocationRequestDto allocationRequestDto) throws ParseException;

	@Transactional(readOnly = false)
	void editAllocatedRequest(UserDetails currentUser, AllocationRequestDto allocationRequestDto) throws ParseException;

}
