package org.rash.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.UserGroupDto;

/**
 * @author rasool.shaik
 * 
 */
public class UserGroupResponse {

	@JsonProperty("Record")
	private UserGroupDto userGroup;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public UserGroupResponse() {
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

	public UserGroupDto getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroupDto userGroup) {
		this.userGroup = userGroup;
	}

}
