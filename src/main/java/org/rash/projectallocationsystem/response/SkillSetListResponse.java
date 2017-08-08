package org.rash.projectallocationsystem.response;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.SkillSetDto;

public class SkillSetListResponse {

	@JsonProperty("Records")
	private List<SkillSetDto> skills;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;

	@JsonProperty
	private Map<String, String> skillGroups;

	@JsonProperty("Message")
	private String message;

	public SkillSetListResponse() {
	}

	/**
	 * @param result
	 */
	public SkillSetListResponse(String result) {
		super();
		this.result = result;
	}

	/**
	 * @param result
	 * @param message
	 */
	public SkillSetListResponse(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	/**
	 * @param skills
	 * @param result
	 * @param message
	 */
	public SkillSetListResponse(List<SkillSetDto> skills, String result, String message) {
		super();
		this.skills = skills;
		this.result = result;
		this.message = message;
	}

	public List<SkillSetDto> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillSetDto> skills) {
		this.skills = skills;
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

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public Map<String, String> getSkillGroups() {
		return skillGroups;
	}

	public void setSkillGroups(Map<String, String> skillGroups) {
		this.skillGroups = skillGroups;
	}

}
