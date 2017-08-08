package org.rash.projectallocationsystem.Dto;

import java.io.Serializable;

/**
 * @author rasool.shaik
 * 
 */

public class ProjectDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String projectID;

	private String projectName;

	private String adminSPOC;

	private String techSPOC;

	private String startDate;

	private String expectedEndDate;

	private String customerId;

	private StatusDto statusDto;

	/**
	 * 
	 */
	public ProjectDto() {
		super();
	}

	/**
	 * @param projectID
	 * @param projectName
	 */
	public ProjectDto(String projectID, String projectName) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID
	 *            the projectID to set
	 */
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the adminSPOC
	 */
	public String getAdminSPOC() {
		return adminSPOC;
	}

	/**
	 * @param adminSPOC
	 *            the adminSPOC to set
	 */
	public void setAdminSPOC(String adminSPOC) {
		this.adminSPOC = adminSPOC;
	}

	/**
	 * @return the techSPOC
	 */
	public String getTechSPOC() {
		return techSPOC;
	}

	/**
	 * @param techSPOC
	 *            the techSPOC to set
	 */
	public void setTechSPOC(String techSPOC) {
		this.techSPOC = techSPOC;
	}

	/**
	 * @return the statusDto
	 */
	public StatusDto getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the expectedEndDate
	 */
	public String getExpectedEndDate() {
		return expectedEndDate;
	}

	/**
	 * @param expectedEndDate
	 *            the expectedEndDate to set
	 */
	public void setExpectedEndDate(String expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

}
