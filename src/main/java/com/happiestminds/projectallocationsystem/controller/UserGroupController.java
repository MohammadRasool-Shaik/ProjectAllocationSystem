package com.happiestminds.projectallocationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;
import com.happiestminds.projectallocationsystem.service.InitDBService;
import com.happiestminds.projectallocationsystem.service.UserGroupService;
/**
 * @author rasool.shaik
 * 
 */
@Controller
public class UserGroupController {

	@Autowired
	private UserGroupService userGroupService;
	
	@Autowired
	private InitDBService initDBService;

	/**
	 * 
	 */
	public UserGroupController() {

	}

	@RequestMapping(value = { "/usergroupmgt"}, method = RequestMethod.GET)
	public String userGroupManagement(Model model) {
		UserGroupEntity userGroup = new UserGroupEntity();
		model.addAttribute("userGroup", userGroup);
		model.addAttribute("userGroupList", userGroupService.getAllUserGroups());
		return "usergroup";
	}

	@RequestMapping(value = "/usergroupactions", method = RequestMethod.POST)
	public String userGroupActions(@ModelAttribute UserGroupEntity userGroup, @RequestParam String action, BindingResult bindingResult, Model model) {
		UserGroupEntity userGroupResult = new UserGroupEntity();
		switch (action.toLowerCase()) {
		case "add":
			userGroupService.addUserGroup(userGroup);
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
		case "search":
			UserGroupEntity searchedUserGroup = userGroupService.getUserGroup(userGroup.getGroupId());
			userGroupResult = searchedUserGroup != null ? searchedUserGroup : new UserGroupEntity();
			break;
		}
		model.addAttribute("userGroup", userGroupResult);
		model.addAttribute("userGroupList", userGroupService.getAllUserGroups());
		return "usergroup";
	}
}
