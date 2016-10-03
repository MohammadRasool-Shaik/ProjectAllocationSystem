package com.happiestminds.projectallocationsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name="skillset")
public class SkillSetEntity{

	@Id
	@Column(length = 20)
	private String skillID;

	@Column(length = 50)
	private String description;

	/**
	 * 
	 */
	public SkillSetEntity() {
	}

	/**
	 * @param skillID
	 * @param description
	 */
	public SkillSetEntity(String skillID, String description) {
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

}
