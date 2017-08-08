package org.rash.projectallocationsystem.Dto;


/**
 * @author rasool.shaik
 * 
 */
public class SubSkillSetDto {

	private String subSkillID;

	private String description;

	private SkillSetDto skillSet;

	/**
	 * 
	 */
	public SubSkillSetDto() {

	}

	/**
	 * @return the subSkillID
	 */
	public String getSubSkillID() {
		return subSkillID;
	}

	/**
	 * @param subSkillID
	 *            the subSkillID to set
	 */
	public void setSubSkillID(String subSkillID) {
		this.subSkillID = subSkillID;
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
	 * @return the skillSet
	 */
	public SkillSetDto getSkillSet() {
		return skillSet;
	}

	/**
	 * @param skillSet
	 *            the skillSet to set
	 */
	public void setSkillSet(SkillSetDto skillSet) {
		this.skillSet = skillSet;
	}

}
