package com.happiestminds.projectallocationsystem.service;

import java.text.ParseException;

import net.sf.json.JSONArray;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.EmployeeDto;
import com.happiestminds.projectallocationsystem.Dto.batch.EmployeeListDto;

/**
 * @author rasool.shaik
 *
 */
public interface EmployeeService {
	@Transactional(readOnly = false)
	void addEmployee(EmployeeDto employee)throws ParseException;

	@Transactional(readOnly = false)
	void updateEmployee(EmployeeDto employee)throws ParseException;

	@Transactional
	void deleteEmployee(EmployeeDto customer);

	@Transactional(readOnly = true)
	EmployeeListDto fetchEmployees(int startIndex, int pageSize, String sortVar)throws ParseException;

	@Transactional(readOnly = true)
	int getAllEmployeeCount();
	
	@Transactional(readOnly = true)
	EmployeeListDto fetchEmployees();

	@Transactional(readOnly = true)
	EmployeeDto findEmployeeByName(String name);

	@Transactional(readOnly = true)
	JSONArray fetchEmployeesByKeyWords(String keyword);
}
