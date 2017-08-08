package org.rash.projectallocationsystem.Dto;

import java.io.Serializable;

/**
 * @author rasool.shaik
 * 
 */
public class UserGroupDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String groupId;

	private String groupName;

	private StatusDto statusDto;

	/**
	 * 
	 */
	public UserGroupDto() {
		super();
	}

	/**
	 * @param groupId
	 * @param groupName
	 */
	public UserGroupDto(String groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

}
