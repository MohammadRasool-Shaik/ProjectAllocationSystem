package org.rash.projectallocationsystem.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.rash.projectallocationsystem.Dto.AllocationRequestDto;
import org.rash.projectallocationsystem.Dto.StatusDto;
import org.rash.projectallocationsystem.Dto.batch.AllocationRequestListDto;
import org.rash.projectallocationsystem.controller.UserDetails;
import org.rash.projectallocationsystem.dao.AllocationRequestsDAO;
import org.rash.projectallocationsystem.dao.EmployeeDAO;
import org.rash.projectallocationsystem.entity.AllocationRequestEntity;
import org.rash.projectallocationsystem.entity.EmployeeEntity;
import org.rash.projectallocationsystem.entity.ProjectEntity;
import org.rash.projectallocationsystem.entity.SkillSetEntity;
import org.rash.projectallocationsystem.enumerator.AllocationStatus;
import org.rash.projectallocationsystem.enumerator.EmployeeStatus;
import org.rash.projectallocationsystem.enumerator.StatusCodeEnum;
import org.rash.projectallocationsystem.service.AllocationRequestsService;
import org.rash.projectallocationsystem.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rasool.shaik
 * 
 */
@Service
public class AllocationRequestsServiceImpl extends AbstractServiceImpl implements AllocationRequestsService {

	@Autowired
	private AllocationRequestsDAO allocationRequestsDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	/**
	 * 
	 */
	public AllocationRequestsServiceImpl() {
	}

	@Override
	public AllocationRequestListDto fetchAllocationRequests(UserDetails userDetails, boolean isPracticeHead, int startIndex, int pageSize, String sortVar) throws ParseException {
		AllocationRequestListDto allocRequests = new AllocationRequestListDto();
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		EmployeeEntity requestor = employeeDAO.findById(userDetails.getUserId());
		String requestorId = requestor.getEmployeeId();
		List<AllocationRequestEntity> requests = allocationRequestsDAO.getAllRequests(requestorId, isPracticeHead, startIndex, pageSize, sortVar);
		for (AllocationRequestEntity allocReqEntity : requests) {
			AllocationRequestDto request = new AllocationRequestDto();

			request.setRequestID(allocReqEntity.getRequestID());
			request.setComments(allocReqEntity.getComments());

			request.setDateHandled(DateUtil.parseDate(allocReqEntity.getDateHandled()));
			request.setStartDate(DateUtil.parseDate(allocReqEntity.getStartDate()));
			request.setEndDate(DateUtil.parseDate(allocReqEntity.getEndDate()));
			request.setSmilesUpdated(DateUtil.parseDate(allocReqEntity.getSmilesUpdated()));
			request.setRequestDate(DateUtil.parseDate(allocReqEntity.getRequestDate()));

			request.setEmployeeName(allocReqEntity.getEmployee().getEmployeeName());
			request.setRequestor(allocReqEntity.getRequestor().getEmployeeName());
			request.setHandledBy(allocReqEntity.getHandledBy().getEmployeeName());
			request.setSkillSetId(allocReqEntity.getRequestedSkillSet().getSkillID());
			request.setProjectId(allocReqEntity.getProject().getProjectID());
			request.setTypeOfRequest(allocReqEntity.getTypeOfRequest().getKey());
			request.setBillableStatus(allocReqEntity.getBillableStatus().getValue());
			allocRequests.getRequests().add(request);
		}
		statusDto.setStatusCode(StatusCodeEnum.OK);
		allocRequests.setStatusDto(statusDto);
		return allocRequests;
	}

	@Override
	public int getAllocationRequestsCount() {
		return allocationRequestsDAO.getAllocationRequestCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rash.projectallocationsystem.service.AllocationRequestsService
	 * #raiseAllocationRequest(org.rash.projectallocationsystem.Dto.
	 * AllocationRequestDto)
	 */
	@Override
	public void raiseAllocationRequest(UserDetails userDetails, AllocationRequestDto allocationRequestDto) throws ParseException {
		Calendar cal = Calendar.getInstance();

		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		AllocationRequestEntity allocationRequest = new AllocationRequestEntity();

		EmployeeEntity requestor = employeeDAO.findById(userDetails.getUserId());
		allocationRequest.setRequestor(requestor);

		allocationRequest.setDateHandled(DateUtil.getDate(allocationRequestDto.getDateHandled()));
		allocationRequest.setStartDate(DateUtil.getDate(allocationRequestDto.getStartDate()));
		allocationRequest.setEndDate(DateUtil.getDate(allocationRequestDto.getEndDate()));
		allocationRequest.setSmilesUpdated(DateUtil.getDate(allocationRequestDto.getSmilesUpdated()));

		cal.setTime(new Date());
		allocationRequest.setRequestDate(cal);

		EmployeeEntity ee = new EmployeeEntity();
		ee.setEmployeeId(allocationRequestDto.getEmployeeName());
		allocationRequest.setEmployee(ee);

		EmployeeEntity ee1 = new EmployeeEntity();
		ee1.setEmployeeId(allocationRequestDto.getHandledBy());
		allocationRequest.setHandledBy(ee1);

		SkillSetEntity skill = new SkillSetEntity();
		skill.setSkillID(allocationRequestDto.getSkillSetId());
		allocationRequest.setRequestedSkillSet(skill);

		ProjectEntity project = new ProjectEntity();
		project.setProjectID(allocationRequestDto.getProjectId());
		allocationRequest.setProject(project);

		allocationRequest.setTypeOfRequest(AllocationStatus.value(allocationRequestDto.getTypeOfRequest()));
		allocationRequest.setBillableStatus(EmployeeStatus.value(Integer.valueOf(allocationRequestDto.getBillableStatus())));
		BeanUtils.copyProperties(allocationRequestDto, allocationRequest);

		allocationRequestsDAO.save(allocationRequest);

		statusDto.setStatusCode(StatusCodeEnum.OK);
		statusDto.setStatusMessage("Request Raised Successfully");
		allocationRequestDto.setStatusDto(statusDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rash.projectallocationsystem.service.AllocationRequestsService
	 * #
	 * editAllocatedRequest(org.rash.projectallocationsystem.controller
	 * .UserDetails,
	 * org.rash.projectallocationsystem.Dto.AllocationRequestDto)
	 */
	@Override
	public void editAllocatedRequest(UserDetails userDetails, AllocationRequestDto allocationRequestDto) throws ParseException {

		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		AllocationRequestEntity allocationRequest = new AllocationRequestEntity();

		AllocationRequestEntity existRequest = allocationRequestsDAO.findById(allocationRequestDto.getRequestID()); // here we need requestId
		allocationRequest.setRequestor(existRequest.getEmployee());
		allocationRequestDto.setRequestor(existRequest.getEmployee().getEmployeeId());

		allocationRequest.setDateHandled(DateUtil.getDate(allocationRequestDto.getDateHandled()));
		allocationRequest.setStartDate(DateUtil.getDate(allocationRequestDto.getStartDate()));
		allocationRequest.setEndDate(DateUtil.getDate(allocationRequestDto.getEndDate()));
		allocationRequest.setSmilesUpdated(DateUtil.getDate(allocationRequestDto.getSmilesUpdated()));
		
		allocationRequestDto.setRequestDate(DateUtil.parseDate(existRequest.getRequestDate()));

		allocationRequest.setRequestDate(existRequest.getRequestDate());

		EmployeeEntity ee = new EmployeeEntity();
		ee.setEmployeeId(allocationRequestDto.getEmployeeName());
		allocationRequest.setEmployee(ee);

		EmployeeEntity ee1 = new EmployeeEntity();
		ee1.setEmployeeId(allocationRequestDto.getHandledBy());
		allocationRequest.setHandledBy(ee1);

		SkillSetEntity skill = new SkillSetEntity();
		skill.setSkillID(allocationRequestDto.getSkillSetId());
		allocationRequest.setRequestedSkillSet(skill);

		ProjectEntity project = new ProjectEntity();
		project.setProjectID(allocationRequestDto.getProjectId());
		allocationRequest.setProject(project);

		allocationRequest.setTypeOfRequest(AllocationStatus.value(allocationRequestDto.getTypeOfRequest()));
		allocationRequest.setBillableStatus(EmployeeStatus.value(Integer.valueOf(allocationRequestDto.getBillableStatus())));
		BeanUtils.copyProperties(allocationRequestDto, allocationRequest);

		allocationRequestsDAO.update(allocationRequest);

		statusDto.setStatusCode(StatusCodeEnum.OK);
		statusDto.setStatusMessage("Request Updated Successfully");
		allocationRequestDto.setStatusDto(statusDto);

	}
}
