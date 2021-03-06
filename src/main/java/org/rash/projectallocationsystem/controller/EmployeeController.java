package org.rash.projectallocationsystem.controller;

import java.text.ParseException;

import org.rash.projectallocationsystem.Dto.EmployeeDto;
import org.rash.projectallocationsystem.Dto.batch.EmployeeListDto;
import org.rash.projectallocationsystem.response.EmployeeListResponse;
import org.rash.projectallocationsystem.response.EmployeeResponse;
import org.rash.projectallocationsystem.service.EmployeeService;
import org.rash.projectallocationsystem.service.ProjectService;
import org.rash.projectallocationsystem.service.SkillSetService;
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
public class EmployeeController extends BaseController{

	@Autowired
	private EmployeeService empService;

	@Autowired
	private SkillSetService skillSetService;

	@Autowired
	private ProjectService projectService;

	/**
	 * 
	 */
	public EmployeeController() {
	}

	@RequestMapping(value = "empm", method = RequestMethod.GET)
	public String employeeManagement(Model model) {
		fetchOperationsByUserGroupForMenu(model);
		return "employeeMang";

	}

	@RequestMapping(value = "/empm/fetchAllEmployees", method = RequestMethod.POST)
	public @ResponseBody
	EmployeeListResponse getAllEmployees(@RequestParam int jtStartIndex, @RequestParam int jtPageSize, @RequestParam(required = false) String jtSorting) throws ParseException {
		EmployeeListResponse eListResponse = new EmployeeListResponse();
		EmployeeListDto employees = empService.fetchEmployees(jtStartIndex, jtPageSize, jtSorting);
		eListResponse.setEmployes(employees.getEmployees());
		eListResponse.setTotalRecordCount(empService.getAllEmployeeCount());
		eListResponse.setResult(employees.getStatusDto().getStatusCode().getMsg());
		eListResponse.setMessage(employees.getStatusDto().getStatusMessage());
		return eListResponse;
	}

	@RequestMapping(value = "/empm/employeeactions", method = RequestMethod.POST)
	public @ResponseBody
	EmployeeResponse employeeActions(@RequestParam String action, @ModelAttribute EmployeeDto employee)throws ParseException {
		EmployeeResponse eResponse = new EmployeeResponse();
		EmployeeDto employeeResult = new EmployeeDto();
		switch (action.toLowerCase()) {
		case "save":
			empService.addEmployee(employee);
			employeeResult = employee;
			break;
		case "update":
			empService.updateEmployee(employee);
			employeeResult = employee;
			break;
		case "delete":
			empService.deleteEmployee(employee);
			employeeResult = employee;
			break;
		}
		eResponse.setEmployee(employeeResult);
		eResponse.setResult(employeeResult.getStatusDto().getStatusCode().getMsg());
		eResponse.setMessage(employeeResult.getStatusDto().getStatusMessage());
		return eResponse;
	}

}
