package com.happiestminds.projectallocationsystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiestminds.projectallocationsystem.entity.EmailEntity;
import com.happiestminds.projectallocationsystem.service.MailService;

@Controller
public class LoginController {

	
	@Autowired
	private MailService mailService;

	public LoginController() {
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String doLogin() {
		return "login";
	}

	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public String  forgotPassword(@RequestParam("mailId") String mailId) {
		
		EmailEntity emailEntity=new EmailEntity();
		emailEntity.setMessage("Hello ");
		emailEntity.setSubject("Test");
		List<String> emailIds=new ArrayList<String>();
		emailIds.add(mailId);
		emailEntity.setEmailIds(emailIds);
		try {
			mailService.sendInviteEmails(emailEntity);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "includes";
	}
}
