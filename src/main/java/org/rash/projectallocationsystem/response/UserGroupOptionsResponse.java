package org.rash.projectallocationsystem.response;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.UserGroupOptionDto;

/**
 * @author rasool.shaik
 * 
 */
public class UserGroupOptionsResponse {
	private String result;

	private List<UserGroupOptionDto> options = new ArrayList<UserGroupOptionDto>();

	private String message;

	/**
	 * 
	 */
	public UserGroupOptionsResponse() {
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
	public List<UserGroupOptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<UserGroupOptionDto> options) {
		this.options = options;
	}

}
