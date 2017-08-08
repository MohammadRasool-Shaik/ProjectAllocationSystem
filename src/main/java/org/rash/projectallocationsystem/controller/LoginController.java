package org.rash.projectallocationsystem.controller;

import javax.mail.MessagingException;

import org.rash.projectallocationsystem.Dto.StatusDto;
import org.rash.projectallocationsystem.Dto.UserDto;
import org.rash.projectallocationsystem.enumerator.StatusCodeEnum;
import org.rash.projectallocationsystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {


	@Autowired
	private MailService mailService;

	public LoginController() {
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView doLogin() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public @ResponseBody
	StatusDto forgotPassword(String emailId) throws MessagingException {
		UserDto userResponse = mailService.forgotPassword(emailId);
		return userResponse.getStatusDto();
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		UserDto userResponse = new UserDto();
		UserDetails currentUser = getCurrentUser();
		userResponse.setUserName(currentUser.getUsername());
		model.addAttribute("user", userResponse);
		fetchOperationsByUserGroupForMenu(model);
		return "changpwd";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(String userName, String password, String newPassword, Model model) {
		UserDto userResponse = mailService.changePassword(userName, password, newPassword);
		fetchOperationsByUserGroupForMenu(model);
		if (userResponse.getStatusDto().getStatusCode().equals(StatusCodeEnum.SUCCESS)) {
			model.addAttribute("user", userResponse);
			return "login";
		} else {
			model.addAttribute("user", userResponse);
			return "changpwd";
		}
	}
}
