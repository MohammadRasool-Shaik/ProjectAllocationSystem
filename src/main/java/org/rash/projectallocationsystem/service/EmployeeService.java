package org.rash.projectallocationsystem.service;

import java.text.ParseException;

import org.rash.projectallocationsystem.Dto.EmployeeDto;
import org.rash.projectallocationsystem.Dto.batch.EmployeeListDto;
import org.springframework.transaction.annotation.Transactional;

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
}
