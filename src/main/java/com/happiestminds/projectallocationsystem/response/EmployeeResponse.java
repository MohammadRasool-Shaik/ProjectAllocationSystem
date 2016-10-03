package com.happiestminds.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.EmployeeDto;

public class EmployeeResponse {

	@JsonProperty("Record")
	private EmployeeDto employee;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("Message")
	private String message;

	public EmployeeResponse() {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

}
