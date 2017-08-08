package org.rash.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rash.projectallocationsystem.Dto.SkillSetDto;
import org.rash.projectallocationsystem.Dto.StatusDto;

/**
 * @author rasool.shaik
 * 
 */
public class SkillSetListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SkillSetDto> skills = new ArrayList<SkillSetDto>();

	private StatusDto statusDto;

	/**
	 * 
	 */
	public SkillSetListDto() {
		super();
	}

	/**
	 * @return the skills
	 */
	public List<SkillSetDto> getSkills() {
		return skills;
	}

	/**
	 * @param skills
	 *            the skills to set
	 */
	public void setSkills(List<SkillSetDto> skills) {
		this.skills = skills;
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
