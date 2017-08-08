package org.rash.projectallocationsystem.Dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author rasool.shaik
 * 
 */

public class AllocationRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer requestID;

	@NotNull(message = "Please Select Requestor")
	private String requestor;

	// (allocation/de-allocation/change billable to non-billable or vice versa)
	private String typeOfRequest;
	
	private String requestDate;

	private String employeeName;

	private String skillSetId;

	private String dateHandled; // Allocated / Deallocated date

	private String handledBy; // Allocation / De Allocation done by

	private String projectId;

	private String startDate;

	private String endDate;

	private String smilesUpdated;

	private String comments;

	private Integer billableStatus;
	private StatusDto statusDto;

	/**
	 * 
	 */
	public AllocationRequestDto() {
	}

	/**
	 * @return the requestor
	 */
	public String getRequestor() {
		return requestor;
	}

	/**
	 * @param requestor
	 *            the requestor to set
	 */
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	/**
	 * @return the typeOfRequest
	 */
	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	/**
	 * @param typeOfRequest
	 *            the typeOfRequest to set
	 */
	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	/**
	 * @return the skillSetId
	 */
	public String getSkillSetId() {
		return skillSetId;
	}

	/**
	 * @param skillSetId
	 *            the skillSetId to set
	 */
	public void setSkillSetId(String skillSetId) {
		this.skillSetId = skillSetId;
	}

	/**
	 * @return the handledBy
	 */
	public String getHandledBy() {
		return handledBy;
	}

	/**
	 * @param handledBy
	 *            the handledBy to set
	 */
	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the billableStatus
	 */
	public Integer getBillableStatus() {
		return billableStatus;
	}

	/**
	 * @param billableStatus
	 *            the billableStatus to set
	 */
	public void setBillableStatus(Integer billableStatus) {
		this.billableStatus = billableStatus;
	}

	/**
	 * @return the requestID
	 */
	public Integer getRequestID() {
		return requestID;
	}

	/**
	 * @param requestID
	 *            the requestID to set
	 */
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
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

	/**
	 * @return the dateHandled
	 */
	public String getDateHandled() {
		return dateHandled;
	}

	/**
	 * @param dateHandled
	 *            the dateHandled to set
	 */
	public void setDateHandled(String dateHandled) {
		this.dateHandled = dateHandled;
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
	 * @return the smilesUpdated
	 */
	public String getSmilesUpdated() {
		return smilesUpdated;
	}

	/**
	 * @param smilesUpdated
	 *            the smilesUpdated to set
	 */
	public void setSmilesUpdated(String smilesUpdated) {
		this.smilesUpdated = smilesUpdated;
	}

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

}
