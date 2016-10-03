package com.happiestminds.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.ProjectDto;

/**
 * @author rasool.shaik
 *
 */
public class ProjectResponse {
	
	@JsonProperty("Record")
	private ProjectDto project;
	
	@JsonProperty("Result")
	private String result;
	
	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public ProjectResponse() {
		// TODO Auto-generated constructor stub
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


	public ProjectDto getProject() {
		return project;
	}


	public void setProject(ProjectDto project) {
		this.project = project;
	}

}
