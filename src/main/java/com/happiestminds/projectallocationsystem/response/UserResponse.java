package com.happiestminds.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.UserDto;

/**
 * @author rasool.shaik
 *
 */
public class UserResponse {
	
	@JsonProperty("Record")
	private UserDto user;
	
	@JsonProperty("Result")
	private String result;
	
	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
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

}
