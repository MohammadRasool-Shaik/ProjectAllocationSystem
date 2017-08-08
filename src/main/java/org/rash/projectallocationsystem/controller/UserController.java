package org.rash.projectallocationsystem.controller;

import javax.validation.Valid;

import org.rash.projectallocationsystem.Dto.UserDto;
import org.rash.projectallocationsystem.Dto.batch.UserGroupListDto;
import org.rash.projectallocationsystem.Dto.batch.UserListDto;
import org.rash.projectallocationsystem.enumerator.StatusCodeEnum;
import org.rash.projectallocationsystem.response.UserListResponse;
import org.rash.projectallocationsystem.response.UserResponse;
import org.rash.projectallocationsystem.service.UserGroupService;
import org.rash.projectallocationsystem.service.UserService;
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
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserGroupService userGroupService;

	/**
	 * 
	 */
	public UserController() {
	}

	@RequestMapping(value = { "/usraccm" }, method = RequestMethod.GET)
	public String userManagement(Model model) {
		fetchOperationsByUserGroupForMenu(model);
		return "userMang";
	}

	@RequestMapping(value = { "/usraccm/getAllUsers" }, method = RequestMethod.POST)
	public @ResponseBody
	UserListResponse fetchAllUsers(@RequestParam int jtStartIndex, @RequestParam int jtPageSize, @RequestParam(required = false) String jtSorting) {

		UserListResponse uListResponse = new UserListResponse();
		UserGroupListDto groups = new UserGroupListDto();

		UserListDto usersWithGroups = userService.getAllUsersWithGroups(groups, jtStartIndex, jtPageSize, jtSorting);
		uListResponse.setUsers(usersWithGroups.getUsers());
		uListResponse.setTotalRecordCount(userService.getAllUserCount());
		uListResponse.setResult(usersWithGroups.getStatusDto().getStatusCode().getMsg());
		uListResponse.setMessage(usersWithGroups.getStatusDto().getStatusMessage());

		return uListResponse;
	}

	@RequestMapping(value = "/usraccm/useractions", method = RequestMethod.POST)
	public @ResponseBody
	UserResponse userActions(@RequestParam String action, @ModelAttribute UserDto user) {

		UserResponse uResponse = new UserResponse();
		UserDto userResponse = new UserDto();
		switch (action.toLowerCase()) {
		case "update":
			userService.updateUser(user);
			userResponse = user;
			break;
		case "delete":
			UserDetails currentUser = getCurrentUser();
			userService.deleteUser(user, currentUser.getUsername());
			userResponse = user;
			break;
		}
		uResponse.setUser(userResponse);
		uResponse.setResult(userResponse.getStatusDto().getStatusCode().getMsg());
		uResponse.setMessage(userResponse.getStatusDto().getStatusMessage());
		return uResponse;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegister(Model model) {
		model.addAttribute("user", new UserDto());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Valid UserDto user, BindingResult bindingResult, Model model) {
		String viewName = null;
		if (!bindingResult.hasErrors()) {
			UserDto userResponse = userService.addUser(user);
			model.addAttribute("user", userResponse);
			if (userResponse.getStatusDto().getStatusCode().equals(StatusCodeEnum.SUCCESS)) {
				viewName = "login";
			} else {
				viewName = "register";
			}
		} else {
			viewName = "register";
		}
		return viewName;
	}

	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public @ResponseBody
	UserDto findByUserName(@RequestParam String userName) {
		UserDto user = userService.getUser(userName);
		return user;
	}
}
