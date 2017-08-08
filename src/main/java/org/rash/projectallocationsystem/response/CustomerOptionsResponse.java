package org.rash.projectallocationsystem.response;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.CustomerOptionDto;

/**
 * @author rasool.shaik
 * 
 */
public class CustomerOptionsResponse {
	private String result;

	private List<CustomerOptionDto> options = new ArrayList<CustomerOptionDto>();

	private String message;

	/**
	 * 
	 */
	public CustomerOptionsResponse() {
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("Result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	@JsonProperty("Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("Options")
	public List<CustomerOptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<CustomerOptionDto> options) {
		this.options = options;
	}

}
