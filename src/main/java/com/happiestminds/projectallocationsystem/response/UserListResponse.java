package com.happiestminds.projectallocationsystem.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.UserDto;

/**
 * @author rasool.shaik
 * 
 */
public class UserListResponse {

	@JsonProperty("Records")
	private List<UserDto> users;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;

	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public UserListResponse() {
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

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}


}
