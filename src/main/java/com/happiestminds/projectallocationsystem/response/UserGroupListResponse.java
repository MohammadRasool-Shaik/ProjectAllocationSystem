package com.happiestminds.projectallocationsystem.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.UserGroupDto;

/**
 * @author rasool.shaik
 * 
 */
public class UserGroupListResponse {

	@JsonProperty("Records")
	private List<UserGroupDto> userGroups;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;

	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public UserGroupListResponse() {
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

	public List<UserGroupDto> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroupDto> userGroups) {
		this.userGroups = userGroups;
	}

}
