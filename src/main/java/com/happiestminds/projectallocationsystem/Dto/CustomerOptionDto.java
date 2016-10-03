package com.happiestminds.projectallocationsystem.Dto;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author rasool.shaik
 * 
 */
public class CustomerOptionDto {

	private String displayText;

	private String value;

	/**
	 * 
	 */
	public CustomerOptionDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param displayText
	 * @param value
	 */
	public CustomerOptionDto(String displayText, String value) {
		super();
		this.displayText = displayText;
		this.value = value;
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
