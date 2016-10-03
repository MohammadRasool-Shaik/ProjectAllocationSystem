package com.happiestminds.projectallocationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.service.UserService;

/**
 * @author rasool.shaik
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 
	 */
	public UserController() {
	}
	
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public String addUser(UserEntity user){
		userService.add(user);
		return "home";
	}
	

}
