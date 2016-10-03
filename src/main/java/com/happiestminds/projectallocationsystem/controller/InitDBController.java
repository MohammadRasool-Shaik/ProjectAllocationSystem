/**
 * 
 */
package com.happiestminds.projectallocationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happiestminds.projectallocationsystem.service.InitDBService;

/**
 * @author rasool.shaik
 *
 */
@Controller
public class InitDBController extends BaseController{
	
	@Autowired
	private InitDBService initDBService;

	/**
	 * 
	 */
	public InitDBController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/loadinitialData",method=RequestMethod.GET)
	public String loadInitialData(){
		initDBService.loadInitialData();
		return "redirect:home";
	}

}
