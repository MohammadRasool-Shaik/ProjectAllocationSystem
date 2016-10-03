package com.happiestminds.projectallocationsystem.service;

import java.text.ParseException;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.AllocationRequestDto;
import com.happiestminds.projectallocationsystem.Dto.batch.AllocationRequestListDto;
import com.happiestminds.projectallocationsystem.controller.UserDetails;

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
	void raiseAllocationRequest(UserDetails userDetails, AllocationRequestDto allocationRequestDto) throws ParseException, MessagingException;

	@Transactional(readOnly = false)
	void editAllocatedRequest(UserDetails currentUser, AllocationRequestDto allocationRequestDto) throws ParseException;

}
