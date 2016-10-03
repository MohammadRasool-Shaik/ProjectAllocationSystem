package com.happiestminds.projectallocationsystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "allocation_history")
public class AllocationHistoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(length = 10)
	private String requestID;
	
	@Column(length = 10)
	private String employeeID;

	@Column(length = 10)
	private String requestor;

	private Date requestDate;

	@Column(length = 10)
	private String handledBy;

	private Date handledDate;

	@Column(length = 1)
	private Character requestType;

	@Column(length = 10)
	private String projectId;

	private Date startDateinSID;

	private Date endDateinSID;

	@Column(length = 1)
	private String status;
	//(allocated and de-allocated)

	/**
	 * 
	 */
	public AllocationHistoryEntity() {
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
	 * @return the requestID
	 */
	public String getRequestID() {
		return requestID;
	}

	/**
	 * @param requestID
	 *            the requestID to set
	 */
	public void setRequestID(String requestID) {
		this.requestID = requestID;
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
	 * @return the handledDate
	 */
	public Date getHandledDate() {
		return handledDate;
	}

	/**
	 * @param handledDate
	 *            the handledDate to set
	 */
	public void setHandledDate(Date handledDate) {
		this.handledDate = handledDate;
	}

	/**
	 * @return the requestType
	 */
	public Character getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType
	 *            the requestType to set
	 */
	public void setRequestType(Character requestType) {
		this.requestType = requestType;
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
	 * @return the startDateinSID
	 */
	public Date getStartDateinSID() {
		return startDateinSID;
	}

	/**
	 * @param startDateinSID
	 *            the startDateinSID to set
	 */
	public void setStartDateinSID(Date startDateinSID) {
		this.startDateinSID = startDateinSID;
	}

	/**
	 * @return the endDateinSID
	 */
	public Date getEndDateinSID() {
		return endDateinSID;
	}

	/**
	 * @param endDateinSID
	 *            the endDateinSID to set
	 */
	public void setEndDateinSID(Date endDateinSID) {
		this.endDateinSID = endDateinSID;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
