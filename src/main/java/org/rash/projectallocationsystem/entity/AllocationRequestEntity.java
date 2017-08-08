package org.rash.projectallocationsystem.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.rash.projectallocationsystem.enumerator.AllocationStatus;
import org.rash.projectallocationsystem.enumerator.EmployeeStatus;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "allocation_Requests")
public class AllocationRequestEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer requestID;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar requestDate;
	
	@ManyToOne
	@JoinColumn(name = "requestorId")
	private EmployeeEntity requestor;

	// (allocation/de-allocation/change billable to non-billable or vice versa)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private AllocationStatus typeOfRequest;

	@ManyToOne(fetch = FetchType.EAGER)
	// Default fetch type for ManyToOne Relationship
	@JoinColumn(name = "employeeId")
	private EmployeeEntity employee;

	@ManyToOne
	@JoinColumn(name = "skillId")
	private SkillSetEntity requestedSkillSet;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar dateHandled; // Resource Allocated / Deallocated date By
									// Practice Head

	@ManyToOne
	@JoinColumn(name = "handledBy")
	private EmployeeEntity handledBy; // Allocation / De-Allocation done by

	@ManyToOne
	@JoinColumn(name = "projectId")
	private ProjectEntity project;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar startDate; // Employee Work Starts Date on Project

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar endDate; // Employee Work End Date on Project

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar smilesUpdated; // PM/Practice Head, Date Updated on SMILES

	private String comments;

	@Enumerated
	private EmployeeStatus billableStatus;

	/**
	 * 
	 */
	public AllocationRequestEntity() {
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
	 * @return the employee
	 */
	public EmployeeEntity getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	/**
	 * @return the requestedSkillSet
	 */
	public SkillSetEntity getRequestedSkillSet() {
		return requestedSkillSet;
	}

	/**
	 * @param requestedSkillSet
	 *            the requestedSkillSet to set
	 */
	public void setRequestedSkillSet(SkillSetEntity requestedSkillSet) {
		this.requestedSkillSet = requestedSkillSet;
	}

	/**
	 * @return the handledBy
	 */
	public EmployeeEntity getHandledBy() {
		return handledBy;
	}

	/**
	 * @param handledBy
	 *            the handledBy to set
	 */
	public void setHandledBy(EmployeeEntity handledBy) {
		this.handledBy = handledBy;
	}

	/**
	 * @return the project
	 */
	public ProjectEntity getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(ProjectEntity project) {
		this.project = project;
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
	public EmployeeStatus getBillableStatus() {
		return billableStatus;
	}

	/**
	 * @param billableStatus
	 *            the billableStatus to set
	 */
	public void setBillableStatus(EmployeeStatus billableStatus) {
		this.billableStatus = billableStatus;
	}

	/**
	 * @return the typeOfRequest
	 */
	public AllocationStatus getTypeOfRequest() {
		return typeOfRequest;
	}

	/**
	 * @param typeOfRequest
	 *            the typeOfRequest to set
	 */
	public void setTypeOfRequest(AllocationStatus typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	/**
	 * @return the requestDate
	 */
	public Calendar getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate
	 *            the requestDate to set
	 */
	public void setRequestDate(Calendar requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the dateHandled
	 */
	public Calendar getDateHandled() {
		return dateHandled;
	}

	/**
	 * @param dateHandled
	 *            the dateHandled to set
	 */
	public void setDateHandled(Calendar dateHandled) {
		this.dateHandled = dateHandled;
	}

	/**
	 * @return the smilesUpdated
	 */
	public Calendar getSmilesUpdated() {
		return smilesUpdated;
	}

	/**
	 * @param smilesUpdated
	 *            the smilesUpdated to set
	 */
	public void setSmilesUpdated(Calendar smilesUpdated) {
		this.smilesUpdated = smilesUpdated;
	}

	/**
	 * @return the requestor
	 */
	public EmployeeEntity getRequestor() {
		return requestor;
	}

	/**
	 * @param requestor the requestor to set
	 */
	public void setRequestor(EmployeeEntity requestor) {
		this.requestor = requestor;
	}

}
