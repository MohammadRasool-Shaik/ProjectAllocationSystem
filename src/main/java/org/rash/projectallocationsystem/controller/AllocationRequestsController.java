package org.rash.projectallocationsystem.controller;

import java.text.ParseException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.rash.projectallocationsystem.Dto.AllocationRequestDto;
import org.rash.projectallocationsystem.Dto.EmailDto;
import org.rash.projectallocationsystem.Dto.batch.AllocationRequestListDto;
import org.rash.projectallocationsystem.response.AllocationRequestListResponse;
import org.rash.projectallocationsystem.response.AllocationRequestResponse;
import org.rash.projectallocationsystem.service.AllocationRequestsService;
import org.rash.projectallocationsystem.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rasool.shaik
 * 
 */
@Controller
public class AllocationRequestsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AllocationRequestsController.class.getSimpleName());

	@Autowired
	private MailService mailService;

	@Autowired
	private AllocationRequestsService allocationRequestsService;

	/**
	 * 
	 */
	public AllocationRequestsController() {
	}

	@RequestMapping(value = "/allocam", method = RequestMethod.GET)
	public String allocateRequestManagement(Model model) {
		// model.addAttribute("employees", empService.fetchEmployees());
		// model.addAttribute("projects",
		// projectService.getProjectsAsOptions());
		// model.addAttribute("skills", skillSetService.getSkillSetOptions());
		return "allocateRequestMangement";
	}

	@RequestMapping(value = "/allocam/fetchAllocationRequests", method = RequestMethod.POST)
	public @ResponseBody
	AllocationRequestListResponse fetchAllocationRequests(HttpServletRequest request, @RequestParam int jtStartIndex, @RequestParam int jtPageSize,
			@RequestParam(required = false) String jtSorting) throws ParseException {
		boolean isPracticeHead = false;
		AllocationRequestListResponse allocationListResponse = new AllocationRequestListResponse();
		if (request.isUserInRole("SRCHALL")) {
			isPracticeHead = true;
		}
		AllocationRequestListDto allocationRequests = allocationRequestsService.fetchAllocationRequests(getCurrentUser(), isPracticeHead, jtStartIndex, jtPageSize, jtSorting);
		allocationListResponse.setRequests(allocationRequests.getRequests());
		allocationListResponse.setTotalRecordCount(allocationRequestsService.getAllocationRequestsCount());
		allocationListResponse.setResult(allocationRequests.getStatusDto().getStatusCode().getMsg());
		allocationListResponse.setMessage(allocationRequests.getStatusDto().getStatusMessage());

		return allocationListResponse;
	}

	@RequestMapping(value = "/allocam/raiseAllocationRequest", method = RequestMethod.POST)
	public @ResponseBody
	AllocationRequestResponse raiseAllocationRequest(@ModelAttribute AllocationRequestDto allocationRequestDto, BindingResult bindingResult, Model model) throws ParseException {

		AllocationRequestResponse allocRequestResponse = new AllocationRequestResponse();
		allocationRequestsService.raiseAllocationRequest(getCurrentUser(), allocationRequestDto);
		allocRequestResponse.setAllocationRequestResponse(allocationRequestDto);
		allocRequestResponse.setResult(allocationRequestDto.getStatusDto().getStatusCode().getMsg());
		allocRequestResponse.setMessage(allocationRequestDto.getStatusDto().getStatusMessage());
		return allocRequestResponse;
	}

	@RequestMapping(value = "/allocam/editAllocatedRequest", method = RequestMethod.POST)
	public @ResponseBody
	AllocationRequestResponse editAllocatedRequest(@ModelAttribute AllocationRequestDto allocationRequestDto, BindingResult bindingResult, Model model) throws ParseException {
		AllocationRequestResponse allocRequestResponse = new AllocationRequestResponse();
		allocationRequestsService.editAllocatedRequest(getCurrentUser(), allocationRequestDto);
		allocRequestResponse.setAllocationRequestResponse(allocationRequestDto);
		allocRequestResponse.setResult(allocationRequestDto.getStatusDto().getStatusCode().getMsg());
		allocRequestResponse.setMessage(allocationRequestDto.getStatusDto().getStatusMessage());
		return allocRequestResponse;
	}

	@RequestMapping(value = "/raiseRequest", method = RequestMethod.POST)
	public String raiseRequest(@ModelAttribute EmailDto emailDto) {
		try {
			mailService.sendInviteEmails(emailDto);
		} catch (MessagingException e) {
			logger.error("EXCEPTION OCCURED WHILE MAILING/MESSAGEING REQUEST");
		} catch (Exception e) {
			logger.error("EXCEPTION OCCURED WHILE RAISING REQUEST");
		}
		return "redirect:home";
	}

	@RequestMapping(value = "/getAllocationRequests", method = RequestMethod.GET)
	public String getAllocateRequests() {
		return null;
	}
}
