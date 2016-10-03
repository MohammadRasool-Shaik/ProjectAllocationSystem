package com.happiestminds.projectallocationsystem.controller;

import javax.mail.MessagingException;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happiestminds.projectallocationsystem.Dto.EmailDto;
import com.happiestminds.projectallocationsystem.service.MailService;

@Controller
public class LoginController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class.getSimpleName());

	@Autowired
	private MailService mailService;

	public LoginController() {

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String doLogin() {
		return "login";
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

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPassword(String emailId) {

		try {
			mailService.forgotPassword(emailId);
		} catch (MessagingException e) {
			logger.error("EXCEPTION OCCURED WHILE MAILING/MESSAGEING REQUEST");
		} catch (HibernateException exception) {
			logger.error("EXCEPTION OCCURED WHILE UPDATING PWD IN DB");
		} catch (Exception e) {
			logger.error("EXCEPTION OCCURED WHILE PROCESSING THE REQUEST");
		}
		return "login";
	}

	@RequestMapping(value = "/chagpwd", method = RequestMethod.GET)
	public String changePassword(Model model) {
		UserDetails currentUser = getCurrentUser();
		String username = currentUser.getUsername();
		
		model.addAttribute("currentUser",username);
		return "chagpwd";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(String userName, String password, String newPassword) {
		try {
			mailService.changePassword(userName, password, newPassword);
		} catch (HibernateException exception) {
			logger.error("EXCEPTION OCCURED WHILE UPDATING PWD IN DB");
		} catch (Exception e) {
			logger.error("EXCEPTION OCCURED WHILE PROCESSING THE REQUEST");
		}
		return "login";
	}
}
