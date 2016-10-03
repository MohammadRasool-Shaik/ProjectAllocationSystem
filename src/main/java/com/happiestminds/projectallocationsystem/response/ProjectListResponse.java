package com.happiestminds.projectallocationsystem.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.ProjectDto;

/**
 * @author rasool.shaik
 *
 */
public class ProjectListResponse {
	

	@JsonProperty("Records")
	private List<ProjectDto> projects;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;
	
	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public ProjectListResponse() {
		// TODO Auto-generated constructor stub
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}


	public List<ProjectDto> getProjects() {
		return projects;
	}


	public void setProjects(List<ProjectDto> projects) {
		this.projects = projects;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
