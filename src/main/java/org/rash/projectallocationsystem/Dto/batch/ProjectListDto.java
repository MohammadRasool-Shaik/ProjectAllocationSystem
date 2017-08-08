package org.rash.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rash.projectallocationsystem.Dto.ProjectDto;
import org.rash.projectallocationsystem.Dto.StatusDto;

/**
 * @author rasool.shaik
 * 
 */
public class ProjectListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ProjectDto> projects = new ArrayList<ProjectDto>();

	StatusDto statusDto;

	/**
	 * 
	 */
	public ProjectListDto() {
		super();
	}

	/**
	 * @return the projects
	 */
	public List<ProjectDto> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(List<ProjectDto> projects) {
		this.projects = projects;
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
