/**
 * 
 */
package com.happiestminds.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.Dto.UserGroupDto;

/**
 * @author Ammi
 * 
 */
public class UserGroupListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<UserGroupDto> userGroups = new ArrayList<UserGroupDto>();

	private StatusDto statusDto;

	/**
	 * 
	 */
	public UserGroupListDto() {
		// TODO Auto-generated constructor stub
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


	/**
	 * @return the userGroups
	 */
	public List<UserGroupDto> getUserGroups() {
		return userGroups;
	}


	/**
	 * @param userGroups the userGroups to set
	 */
	public void setUserGroups(List<UserGroupDto> userGroups) {
		this.userGroups = userGroups;
	}



}
