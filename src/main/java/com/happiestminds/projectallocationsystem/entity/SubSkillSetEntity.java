package com.happiestminds.projectallocationsystem.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "subskill")
public class SubSkillSetEntity {

	@Id
	@Column(length = 20, nullable = false)
	private String subSkillID;

	@Column(length = 50, nullable = false)
	private String description;

	@Column(length = 2)
	private int experience;

	@Column
	private Date lastUsed;

	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "skillId")
	private SkillSetEntity skillSet;

	/**
	 * 
	 */
	public SubSkillSetEntity() {

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
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * @return the lastUsed
	 */
	public Date getLastUsed() {
		return lastUsed;
	}

	/**
	 * @param lastUsed
	 *            the lastUsed to set
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	/**
	 * @return the skillSet
	 */
	public SkillSetEntity getSkillSet() {
		return skillSet;
	}

	/**
	 * @param skillSet
	 *            the skillSet to set
	 */
	public void setSkillSet(SkillSetEntity skillSet) {
		this.skillSet = skillSet;
	}

}
