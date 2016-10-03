package com.happiestminds.projectallocationsystem.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.Dto.AllocationRequestDto;
import com.happiestminds.projectallocationsystem.Dto.EmailDto;
import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.Dto.batch.AllocationRequestListDto;
import com.happiestminds.projectallocationsystem.controller.UserDetails;
import com.happiestminds.projectallocationsystem.dao.AllocationRequestsDAO;
import com.happiestminds.projectallocationsystem.dao.EmployeeDAO;
import com.happiestminds.projectallocationsystem.entity.AllocationRequestEntity;
import com.happiestminds.projectallocationsystem.entity.EmployeeEntity;
import com.happiestminds.projectallocationsystem.entity.ProjectEntity;
import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.enumerator.AllocationStatus;
import com.happiestminds.projectallocationsystem.enumerator.EmployeeStatus;
import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;
import com.happiestminds.projectallocationsystem.service.AllocationRequestsService;
import com.happiestminds.projectallocationsystem.service.MailService;
import com.happiestminds.projectallocationsystem.util.DateUtil;

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

	@Autowired
	private MailService mailService;

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
			if (allocReqEntity.getHandledBy() != null) {
				request.setHandledBy(allocReqEntity.getHandledBy().getEmployeeName());
			} else {
				request.setHandledBy(null);
			}
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
	 * com.happiestminds.projectallocationsystem.service.AllocationRequestsService
	 * #raiseAllocationRequest(com.happiestminds.projectallocationsystem.Dto.
	 * AllocationRequestDto)
	 */
	@Override
	public void raiseAllocationRequest(UserDetails userDetails, AllocationRequestDto allocationRequestDto) throws ParseException, MessagingException {

		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		String startDate = allocationRequestDto.getStartDate();
		String endDate = allocationRequestDto.getEndDate();
		String dateHandled = allocationRequestDto.getDateHandled();
		String smilesUpdated = allocationRequestDto.getSmilesUpdated();
		boolean isProjectDatesValid = DateUtil.isEndDateAfterStartDate(startDate, endDate);
		boolean isRequestDatesValid = DateUtil.isEndDateAfterStartDate(dateHandled, smilesUpdated);
		EmployeeEntity requestor = employeeDAO.findById(userDetails.getUserId());
		if ((requestor == null && isProjectDatesValid) || isRequestDatesValid) {
			Calendar cal = Calendar.getInstance();

			AllocationRequestEntity allocationRequest = new AllocationRequestEntity();

			allocationRequest.setRequestor(requestor);

			allocationRequest.setDateHandled(DateUtil.getDate(dateHandled));
			allocationRequest.setStartDate(DateUtil.getDate(startDate));
			allocationRequest.setEndDate(DateUtil.getDate(endDate));
			allocationRequest.setSmilesUpdated(DateUtil.getDate(smilesUpdated));

			cal.setTime(new Date());
			allocationRequest.setRequestDate(cal);

			EmployeeEntity ee = new EmployeeEntity();
			ee.setEmployeeId(allocationRequestDto.getEmployeeName());
			allocationRequest.setEmployee(ee);

			EmployeeEntity ee1 = null;
			if (allocationRequestDto.getHandledBy() != null && allocationRequestDto.getHandledBy().length() > 0) {
				ee1 = new EmployeeEntity();
				ee1.setEmployeeId(allocationRequestDto.getHandledBy());
			}
			allocationRequest.setHandledBy(ee1);

			SkillSetEntity skill = new SkillSetEntity();
			String skillSetId = allocationRequestDto.getSkillSetId();
			skill.setSkillID(skillSetId);
			allocationRequest.setRequestedSkillSet(skill);

			ProjectEntity project = new ProjectEntity();
			project.setProjectID(allocationRequestDto.getProjectId());
			allocationRequest.setProject(project);

			allocationRequest.setTypeOfRequest(AllocationStatus.value(allocationRequestDto.getTypeOfRequest()));
			allocationRequest.setBillableStatus(EmployeeStatus.value(Integer.valueOf(allocationRequestDto.getBillableStatus())));
			BeanUtils.copyProperties(allocationRequestDto, allocationRequest);

			allocationRequestsDAO.save(allocationRequest);

			String fromMailId = userDetails.getUsername() + "@happiestminds.com";

			sendMailstoPracticeHeads(skillSetId, allocationRequest, fromMailId);

			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Request Raised Successfully");
		} else {
			statusDto.setStatusMessage("Requestor/Employee is not an User to use this application, Try Another?");
			if (!isRequestDatesValid) {
				statusDto.setStatusMessage("Please enter an Handle-Date after the Smiles-Updated-Date");
			}
			if (!isProjectDatesValid) {
				statusDto.setStatusMessage("Please enter an End date after the Start date");
			}
			// throwAlredyExistValidationError(SkillSetDto.class.getSimpleName(),
			// skillSetDto.getSkillID());
		}
		allocationRequestDto.setStatusDto(statusDto);

	}

	public void sendMailstoPracticeHeads(String skillId, AllocationRequestEntity allocationRequest, String fromMailId) throws MessagingException {
		List<UserEntity> practiceHeads = employeeDAO.fetchPracticeHeadsBySkill(skillId);
		EmailDto emailDto = new EmailDto();
		List<String> emailIds = new ArrayList<String>();
		for (UserEntity practiceHead : practiceHeads) {
			emailIds.add(practiceHead.getEmailId());
		}
		emailDto.setEmailIds(emailIds);
		emailDto.setSubject("Allocation Request for Project " + allocationRequest.getProject().getProjectID());
		emailDto.setMessage("Allocation Request for Project " + allocationRequest.getProject().getProjectID());

		emailDto.setFromMailId(fromMailId);
		mailService.raiseAllocationRequestEmail(emailDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.AllocationRequestsService
	 * #
	 * editAllocatedRequest(com.happiestminds.projectallocationsystem.controller
	 * .UserDetails,
	 * com.happiestminds.projectallocationsystem.Dto.AllocationRequestDto)
	 */
	@Override
	public void editAllocatedRequest(UserDetails userDetails, AllocationRequestDto allocationRequestDto) throws ParseException {

		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		String startDate = allocationRequestDto.getStartDate();
		String endDate = allocationRequestDto.getEndDate();
		String dateHandled = allocationRequestDto.getDateHandled();
		String smilesUpdated = allocationRequestDto.getSmilesUpdated();
		boolean isProjectDatesValid = DateUtil.isEndDateAfterStartDate(startDate, endDate);
		boolean isRequestDatesValid = DateUtil.isEndDateAfterStartDate(dateHandled, smilesUpdated);
		AllocationRequestEntity existRequest = allocationRequestsDAO.findById(allocationRequestDto.getRequestID()); // here
		// we
		if ((existRequest == null && isProjectDatesValid) || isRequestDatesValid) {
			AllocationRequestEntity allocationRequest = new AllocationRequestEntity();

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

			EmployeeEntity ee1 = null;
			if (allocationRequestDto.getHandledBy() != null && allocationRequestDto.getHandledBy().length() > 0) {
				ee1 = new EmployeeEntity();
				ee1.setEmployeeId(allocationRequestDto.getHandledBy());
			}
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
		} else {
			statusDto.setStatusMessage("Requestor/Employee is not an User to use this application, Try Another?");
			if (!isRequestDatesValid) {
				statusDto.setStatusMessage("Please enter an Handle-Date after the Smiles-Updated-Date");
			}
			if (!isProjectDatesValid) {
				statusDto.setStatusMessage("Please enter an End date after the Start date");
			}
			// throwAlredyExistValidationError(SkillSetDto.class.getSimpleName(),
			// skillSetDto.getSkillID());
		}
		allocationRequestDto.setStatusDto(statusDto);
	}
}
