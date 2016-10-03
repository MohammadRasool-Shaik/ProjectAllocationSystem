package com.happiestminds.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.happiestminds.projectallocationsystem.Dto.EmployeeDto;
import com.happiestminds.projectallocationsystem.Dto.StatusDto;

/**
 * @author rasool.shaik
 * 
 */
public class EmployeeListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EmployeeDto> employees = new ArrayList<EmployeeDto>();

	private StatusDto statusDto;

	/**
	 * 
	 */
	public EmployeeListDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the employees
	 */
	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	/**
	 * @param employees
	 *            the employees to set
	 */
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	/**
	 * @return the statusDto
	 */
	public StatusDto getStatusDto() {
		return statusDto;
	}

	/**
	 * @param statusDto
	 *            the statusDto to set
	 */
	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}

}
