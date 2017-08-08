package org.rash.projectallocationsystem.Dto;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author rasool.shaik
 * 
 */
public class ProjectOptionDto {

	private String displayText;

	private String value;

	/**
	 * 
	 */
	public ProjectOptionDto() {
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("DisplayText")
	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	@JsonProperty("Value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
