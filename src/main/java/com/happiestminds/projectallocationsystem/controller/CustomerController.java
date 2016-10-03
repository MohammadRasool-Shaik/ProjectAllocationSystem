package com.happiestminds.projectallocationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiestminds.projectallocationsystem.entity.CustomerEntity;
import com.happiestminds.projectallocationsystem.service.CustomerService;

/**
 * @author rasool.shaik
 * 
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 
	 */
	public CustomerController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "custm", method = RequestMethod.GET)
	public String customerManagement(Model model) {
		List<CustomerEntity> customers = customerService.fetchCustomers();
		if (customers.isEmpty()) {
			model.addAttribute("customersEmptyMsg", "Customers are not available, Contact Administrator");
		} else {
			model.addAttribute("customerList", customers);
		}
		return "customers";
	}

	@RequestMapping(value = "customeractions", method = RequestMethod.POST)
	public String customerActions(@ModelAttribute CustomerEntity customer, @RequestParam(required = true) String action, BindingResult bindingResult, Model model) {
		CustomerEntity customerResult = new CustomerEntity();
		switch (action.toLowerCase()) {
		case "add":
			boolean isaddSkillSet = customerService.addCustomer(customer);
			if (!isaddSkillSet) {
				model.addAttribute("message", "Customer already Exist, With Same Id " + customer.getCustomerID());
			}
			customerResult = customer;
			break;
		case "edit":
			customerService.updateCustomer(customer);
			customerResult = customer;
			break;
		case "delete":
			customerService.deleteCustomer(customer);
			customerResult = new CustomerEntity();
			break;
		}
		List<CustomerEntity> customers = customerService.fetchCustomers();
		if (customers.isEmpty()) {
			model.addAttribute("customersEmptyMsg", "Customers are not available, Contact Administrator");
		} else {
			model.addAttribute("customerList", customers);
		}
		model.addAttribute("customer", customerResult);
		return "customers";
	}
}
