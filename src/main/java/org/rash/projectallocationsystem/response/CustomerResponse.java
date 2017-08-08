package org.rash.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.CustomerDto;

public class CustomerResponse {
	
	@JsonProperty("Record")
	private CustomerDto customerResponse;
	
	@JsonProperty("Result")
	private String result;
	
	@JsonProperty("Message")
	private String message;

	public CustomerResponse() {
		// TODO Auto-generated constructor stub
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


	public CustomerDto getCustomerResponse() {
		return customerResponse;
	}


	public void setCustomerResponse(CustomerDto customerResponse) {
		this.customerResponse = customerResponse;
	}

}
