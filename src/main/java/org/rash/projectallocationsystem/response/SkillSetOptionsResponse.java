package org.rash.projectallocationsystem.response;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.SkillSetOptionDto;

/**
 * @author rasool.shaik
 * 
 */
public class SkillSetOptionsResponse {
	private String result;

	private List<SkillSetOptionDto> options = new ArrayList<SkillSetOptionDto>();

	private String message;

	/**
	 * 
	 */
	public SkillSetOptionsResponse() {
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("Result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@JsonProperty("Options")
	public List<SkillSetOptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<SkillSetOptionDto> options) {
		this.options = options;
	}

	@JsonProperty("Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
