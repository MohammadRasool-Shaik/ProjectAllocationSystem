package com.happiestminds.projectallocationsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "allocation_requests")
public class AllocationRequestsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int requestID;

	private Date requestDate;

	private String requestor;

	@Column(length = 1)
	private char typeOfRequest;

	private String employeeID;

	@Column(length = 20)
	private String requestedSkillSet;

	private Date dateHandled;

	@Column(length = 10)
	private String handledBy;

	private String projectID;

	private Date startDate;

	private Date endDate;

	private Date smilesUpdated;

	private String comments;

	@Column(length = 1)
	private int billableStatus;

	/**
	 * 
	 */
	public AllocationRequestsEntity() {
	}


	/**
	 * @return the requestID
	 */
	public int getRequestID() {
		return requestID;
	}


	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}


	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate
	 *            the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
	public char getTypeOfRequest() {
		return typeOfRequest;
	}

	/**
	 * @param typeOfRequest
	 *            the typeOfRequest to set
	 */
	public void setTypeOfRequest(char typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	/**
	 * @return the employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID
	 *            the employeeID to set
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the requestedSkillSet
	 */
	public String getRequestedSkillSet() {
		return requestedSkillSet;
	}

	/**
	 * @param requestedSkillSet
	 *            the requestedSkillSet to set
	 */
	public void setRequestedSkillSet(String requestedSkillSet) {
		this.requestedSkillSet = requestedSkillSet;
	}

	/**
	 * @return the dateHandled
	 */
	public Date getDateHandled() {
		return dateHandled;
	}

	/**
	 * @param dateHandled
	 *            the dateHandled to set
	 */
	public void setDateHandled(Date dateHandled) {
		this.dateHandled = dateHandled;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the smilesUpdated
	 */
	public Date getSmilesUpdated() {
		return smilesUpdated;
	}

	/**
	 * @param smilesUpdated
	 *            the smilesUpdated to set
	 */
	public void setSmilesUpdated(Date smilesUpdated) {
		this.smilesUpdated = smilesUpdated;
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
	public int getBillableStatus() {
		return billableStatus;
	}

	/**
	 * @param billableStatus
	 *            the billableStatus to set
	 */
	public void setBillableStatus(int billableStatus) {
		this.billableStatus = billableStatus;
	}

}
