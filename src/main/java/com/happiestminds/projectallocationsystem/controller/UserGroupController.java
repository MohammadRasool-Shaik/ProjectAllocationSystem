package com.happiestminds.projectallocationsystem.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happiestminds.projectallocationsystem.Dto.OperationDto;
import com.happiestminds.projectallocationsystem.Dto.UserGroupDto;
import com.happiestminds.projectallocationsystem.Dto.batch.UserGroupListDto;
import com.happiestminds.projectallocationsystem.response.UserGroupListResponse;
import com.happiestminds.projectallocationsystem.response.UserGroupOptionsResponse;
import com.happiestminds.projectallocationsystem.response.UserGroupResponse;
import com.happiestminds.projectallocationsystem.service.UserGroupService;

/**
 * @author rasool.shaik
 * 
 */
@Controller
public class UserGroupController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserGroupController.class.getSimpleName());

	@Autowired
	private UserGroupService userGroupService;

	/**
	 * 
	 */
	public UserGroupController() {

	}

	@RequestMapping(value = { "/usrgrpm" }, method = RequestMethod.GET)
	public String userGroupManagement(Model model) {
		fetchOperationsByUserGroupForMenu(model);
		return "userGroupMang";
	}

	@RequestMapping(value = { "/usrgrpm/getAllUserGroups" }, method = RequestMethod.POST)
	public @ResponseBody
	UserGroupListResponse getAllUserGroups(@RequestParam int jtStartIndex, @RequestParam int jtPageSize, @RequestParam(required = false) String jtSorting) {
		UserGroupListResponse userGroupListResponse = new UserGroupListResponse();

		UserGroupListDto userGroupListDto = userGroupService.getAllUserGroups(jtStartIndex, jtPageSize, jtSorting);

		userGroupListResponse.setUserGroups(userGroupListDto.getUserGroups());
		userGroupListResponse.setResult(userGroupListDto.getStatusDto().getStatusCode().getMsg());
		userGroupListResponse.setTotalRecordCount(userGroupService.getCountAllUserGroups());
		userGroupListResponse.setMessage(userGroupListDto.getStatusDto().getStatusMessage());

		return userGroupListResponse;
	}

	@RequestMapping(value = "/usrgrpm/usergroupactions", method = RequestMethod.POST)
	public @ResponseBody
	UserGroupResponse userGroupActions(@RequestParam String action, @ModelAttribute UserGroupDto userGroup) {
		UserGroupDto userGroupResult = new UserGroupDto();
		UserGroupResponse userGroupResponse = new UserGroupResponse();
		switch (action.toLowerCase()) {
		case "save":
			userGroupService.addUserGroup(userGroup);
			userGroupResult = userGroup;
			break;
		case "update":
			userGroupService.updateUserGroup(userGroup);
			userGroupResult = userGroup;
			break;
		case "delete":
			userGroupService.deleteUserGroup(userGroup);
			userGroupResult = userGroup;
			break;
		}
		userGroupResponse.setUserGroup(userGroupResult);
		userGroupResponse.setResult(userGroupResult.getStatusDto().getStatusCode().getMsg());
		userGroupResponse.setMessage(userGroupResult.getStatusDto().getStatusMessage());
		return userGroupResponse;
	}

	// TODO : Different approach compare to other requests
	@RequestMapping(value = "/applyRightsToGroup", method = RequestMethod.POST)
	public @ResponseBody
	String applyRightsToGroup(@ModelAttribute UserGroupDto userGroup, @RequestParam(required = false) List<String> operations, Model model) {
		boolean isActionPerformed = false;
		String groupId = userGroup.getGroupId();
		// JSONObject obj = new JSONObject();
		// obj.put("groupId", groupId);
		try {
			userGroupService.updateUserGroup(userGroup);
			isActionPerformed = userGroupService.applyRightsToGroup(groupId, operations);
		} catch (Exception ex) {
			logger.error("While updating rights got some exception", ex);
		}
		if (isActionPerformed) {
			return groupId + ",Success";
		} else {
			return groupId + ",Failure";
		}
	}

	@RequestMapping(value = "/fetchgrouprights", method = RequestMethod.GET)
	public String fetchOperationsByUserGroup(@ModelAttribute UserGroupDto userGroup, Model model) {
		Map<String, List<OperationDto>> operationsByGroup = null;
		try {
			String groupId = userGroup.getGroupId();
			operationsByGroup = userGroupService.fetchOperationsByGroup(userGroup.getGroupId());
			model.addAttribute("operationsByGroup", operationsByGroup);
			model.addAttribute("groupId", groupId);
			model.addAttribute("groupName", userGroup.getGroupName());
		} catch (Exception e) {
			logger.error("EXCPTION OCCURED WHILE PROCESSING REQUEST" + e.toString());
		}
		return "grouprights";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		fetchOperationsByUserGroupForMenu(model);
		return "home";
	}

	@RequestMapping(value = "/usrgrpm/getAllUserGroupOptions", method = RequestMethod.POST)
	public @ResponseBody
	UserGroupOptionsResponse getAllUserGroupsOptions() {
		return userGroupService.getAllUserGroupsOptions();
	}

}
