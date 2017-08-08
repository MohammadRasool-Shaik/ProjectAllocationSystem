package org.rash.projectallocationsystem.response;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.ProjectOptionDto;

/**
 * @author rasool.shaik
 * 
 */
public class ProjectOptionsResponse {

	private String result;

	private List<ProjectOptionDto> options = new ArrayList<ProjectOptionDto>();

	private String message;

	/**
	 * 
	 */
	public ProjectOptionsResponse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param result
	 * @param options
	 */
	public ProjectOptionsResponse(String result, List<ProjectOptionDto> options) {
		super();
		this.result = result;
		this.options = options;
	}

	/**
	 * @param result
	 * @param message
	 */
	public ProjectOptionsResponse(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	@JsonProperty("Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("Options")
	public List<ProjectOptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<ProjectOptionDto> options) {
		this.options = options;
	}

	@JsonProperty("Result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
