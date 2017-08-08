package org.rash.projectallocationsystem.controller;

import org.rash.projectallocationsystem.Dto.CustomerDto;
import org.rash.projectallocationsystem.Dto.batch.CustomerListDto;
import org.rash.projectallocationsystem.response.CustomerListResponse;
import org.rash.projectallocationsystem.response.CustomerOptionsResponse;
import org.rash.projectallocationsystem.response.CustomerResponse;
import org.rash.projectallocationsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CustomerController extends BaseController {

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
		fetchOperationsByUserGroupForMenu(model);
		return "customersMang";
	}

	@RequestMapping(value = "/custm/fetchcustomers", method = RequestMethod.POST)
	public @ResponseBody
	CustomerListResponse getAllCustomers(@RequestParam int jtStartIndex, @RequestParam int jtPageSize, @RequestParam(required = false) String jtSorting) {
		CustomerListResponse cuListResponse = new CustomerListResponse();
		CustomerListDto customers = customerService.fetchCustomers(jtStartIndex, jtPageSize, jtSorting);

		cuListResponse.setCustomers(customers.getCustomers());
		cuListResponse.setTotalRecordCount(customerService.getCountAllCustomers());
		cuListResponse.setResult(customers.getStatusDto().getStatusCode().getMsg());
		cuListResponse.setMessage(customers.getStatusDto().getStatusMessage());
		return cuListResponse;
	}

	@RequestMapping(value = "/custm/customeractions", method = RequestMethod.POST)
	public @ResponseBody
	CustomerResponse customerActions(@RequestParam String action, @ModelAttribute CustomerDto customer) {
		CustomerDto customerResult = new CustomerDto();
		CustomerResponse cuResponse = new CustomerResponse();
		switch (action.toLowerCase()) {
		case "save":
			customerService.addCustomer(customer);
			customerResult = customer;
			break;
		case "update":
			customerService.updateCustomer(customer);
			customerResult = customer;
			break;
		case "delete":
			customerService.deleteCustomer(customer);
			customerResult = customer;
			break;
		}
		cuResponse.setCustomerResponse(customerResult);
		cuResponse.setResult(customerResult.getStatusDto().getStatusCode().getMsg());
		cuResponse.setMessage(customerResult.getStatusDto().getStatusMessage());
		return cuResponse;
	}

	@RequestMapping(value = "/custm/getAllCustomers", method = RequestMethod.POST)
	public @ResponseBody CustomerOptionsResponse getAllCustomers() {
		return customerService.getAllCustomers();
	}
}
