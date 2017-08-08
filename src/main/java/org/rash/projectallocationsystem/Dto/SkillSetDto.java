package org.rash.projectallocationsystem.Dto;

import java.io.Serializable;


/**
 * @author rasool.shaik
 * 
 */
public class SkillSetDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String skillID;

	private String description;

	private String groupInfo;
	
	private StatusDto statusDto;

	/**
	 * 
	 */
	public SkillSetDto() {
	}

	/**
	 * @param skillID
	 * @param description
	 */
	public SkillSetDto(String skillID, String description) {
		super();
		this.skillID = skillID;
		this.description = description;
	}

	/**
	 * @return the skillID
	 */
	public String getSkillID() {
		return skillID;
	}

	/**
	 * @param skillID
	 *            the skillID to set
	 */
	public void setSkillID(String skillID) {
		this.skillID = skillID;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the groupInfo
	 */
	public String getGroupInfo() {
		return groupInfo;
	}

	/**
	 * @param groupInfo
	 *            the groupInfo to set
	 */
	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}

	/**
	 * @return the statusDto
	 */
	public StatusDto getStatusDto() {
		return statusDto;
	}

	/**
	 * @param statusDto the statusDto to set
	 */
	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}
}
