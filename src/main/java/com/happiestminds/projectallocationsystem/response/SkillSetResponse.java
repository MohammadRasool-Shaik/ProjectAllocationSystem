package com.happiestminds.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.SkillSetDto;

public class SkillSetResponse {

	@JsonProperty("Record")
	private SkillSetDto skillSet;
	
	@JsonProperty("Result")
	private String result;
	
	@JsonProperty("Message")
	private String message;

	public SkillSetResponse() {
	}

	/**
	 * @param result
	 */
	public SkillSetResponse(String result) {
		super();
		this.result = result;
	}

	/**
	 * @param result
	 * @param message
	 */
	public SkillSetResponse(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	/**
	 * @param skillSet
	 * @param result
	 * @param message
	 */
	public SkillSetResponse(SkillSetDto skillSet, String result, String message) {
		super();
		this.skillSet = skillSet;
		this.result = result;
		this.message = message;
	}

	public SkillSetDto getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(SkillSetDto skillSet) {
		this.skillSet = skillSet;
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

}
