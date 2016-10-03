package com.happiestminds.projectallocationsystem.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;
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
		model.addAttribute("userGroupList", userGroupService.getAllUserGroups());
		return "usergroup";
	}

	@RequestMapping(value = "/usergroupactions", method = RequestMethod.POST)
	public String userGroupActions(@RequestParam(required = true) String action, @ModelAttribute UserGroupEntity userGroup, BindingResult bindingResult, Model model) {
		UserGroupEntity userGroupResult = new UserGroupEntity();
		switch (action.toLowerCase()) {
		case "add":
			boolean isUserGroupAdded = userGroupService.addUserGroup(userGroup);
			if (!isUserGroupAdded) {
				model.addAttribute("message", "User Group Already Exist, With Same GroupId " + userGroup.getGroupId());
			}
			userGroupResult = userGroup;
			break;
		case "edit":
			userGroupService.updateUserGroup(userGroup);
			userGroupResult = userGroup;
			break;
		case "delete":
			userGroupService.deleteUserGroup(userGroup);
			userGroupResult = new UserGroupEntity();
			break;
		}
		model.addAttribute("userGroup", userGroupResult);
		model.addAttribute("userGroupList", userGroupService.getAllUserGroups());
		return "usergroup";
	}

	@RequestMapping(value = "/applyRightsToGroup", method = RequestMethod.GET)
	public String applyRightsToGroup(@RequestParam String groupId, @RequestParam String operations, Model model) {
		boolean isActionPerformed = false;
		isActionPerformed = userGroupService.applyRightsToGroup(groupId, operations);
		Map<String, List<OperationEntity>> operationsByGroup = userGroupService.fetchOperationsByGroup(groupId);

		model.addAttribute("operationsByGroup", operationsByGroup);
		model.addAttribute("groupId", groupId);
		model.addAttribute("isActionPerformed", isActionPerformed);
		return "grouprights";
	}

	@RequestMapping(value = "/fetchgrouprights", method = RequestMethod.GET)
	public String fetchOperationsByUserGroup(@RequestParam String groupId, Model model) {
		Map<String, List<OperationEntity>> operationsByGroup = null;
		try {
			operationsByGroup = userGroupService.fetchOperationsByGroup(groupId);
			model.addAttribute("operationsByGroup", operationsByGroup);
			model.addAttribute("groupId", groupId);
		} catch (Exception e) {
			logger.error("EXCPTION OCCURED WHILE PROCESSING REQUEST" + e.toString());
		}
		return "grouprights";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String fetchOperationsByUserGroupForHome(Model model) {
		try {
			UserDetails currentUser = getCurrentUser();
			Collection<GrantedAuthority> authorities = currentUser.getAuthorities();
			Set<ModuleEntity> modules = userGroupService.fetchModulesByRights(authorities);
			model.addAttribute("modules", modules);
		} catch (Exception e) {
			logger.error("EXCPTION OCCURED WHILE PROCESSING REQUEST" + e.toString());
		}
		return "home";
	}

}
