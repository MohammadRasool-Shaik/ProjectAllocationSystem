package com.happiestminds.projectallocationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.service.UserGroupService;
import com.happiestminds.projectallocationsystem.service.UserService;

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
		UserEntity user = new UserEntity();
		model.addAttribute("user", user);
		model.addAttribute("usersList", userService.getAllUsersWithGroups());
		// TODO : two separate calls to get users and to get usergroups, instead
		// we can use right outer join 2 get both information in one db call,
		// but we are getting null left i.e child side bcz we are maintaining
		// manytoone relationships
		model.addAttribute("userGroupList", userGroupService.getAllUserGroups());
		return "users";
	}

	@RequestMapping(value = "/useractions", method = RequestMethod.POST)
	public String userActions(@ModelAttribute UserEntity user, @RequestParam String groupId, @RequestParam String action, BindingResult bindingResult, Model model) {
		UserEntity userResult = new UserEntity();
		switch (action.toLowerCase()) {
		case "edit":
			userService.updateUser(user, groupId);
			userResult = user;
			break;
		case "delete":
			userService.deleteUser(user);
			userResult = new UserEntity();
			break;
		}
		model.addAttribute("user", userResult);
		model.addAttribute("usersList", userService.getAllUsersWithGroups());
		model.addAttribute("userGroupList", userGroupService.getAllUserGroups());
		return "users";
	}

	@RequestMapping(value = "/getregister", method = RequestMethod.GET)
	public String getRegister(Model model) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute UserEntity user, Model model) {
		boolean isUserAdded = userService.addUser(user);
		String message = user.getUserId() + "userId already exit, Try with other user";
		if (isUserAdded) {
			message = "You are successfully Register";
			model.addAttribute("user", user);
		}
		model.addAttribute("registermsg", message);
		return "login";
	}

}
