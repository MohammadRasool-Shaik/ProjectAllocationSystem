package com.happiestminds.projectallocationsystem.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.CustomerDto;

/**
 * @author rasool.shaik
 * 
 */
public class CustomerListResponse {

	@JsonProperty("Records")
	private List<CustomerDto> customers;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;

	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public CustomerListResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CustomerDto> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDto> customers) {
		this.customers = customers;
	}

}
