package com.happiestminds.projectallocationsystem.Dto;

import java.io.Serializable;

/**
 * @author rasool.shaik
 * 
 */
public class EmployeeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeId;

	private String employeeName;

	private String skillSetId;

	private Integer empStatus;

	private String competency;

	private String projectId;

	private String assignedDate;

	private String endDate;

	private StatusDto statusDto;

	/**
	 * 
	 */
	public EmployeeDto() {
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public StatusDto getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}

	public String getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(String skillSetId) {
		this.skillSetId = skillSetId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	/**
	 * @return the assignedDate
	 */
	public String getAssignedDate() {
		return assignedDate;
	}

	/**
	 * @param assignedDate
	 *            the assignedDate to set
	 */
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
