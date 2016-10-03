package com.happiestminds.projectallocationsystem.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "Projects")
public class ProjectEntity{
	@Id
	@Column(length = 10)
	private String projectID;

	@Column(length = 50)
	private String projectName;

	@Column(length = 100)
	private String adminSPOC;

	@Column(length = 100)
	private String techSPOC;

	private Date startDate;

	private Date endDate;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "customerId", nullable = false)
	private CustomerEntity customer;

	/**
	 * 
	 */
	public ProjectEntity() {
		super();
	}

	/**
	 * @param projectID
	 * @param projectName
	 */
	public ProjectEntity(String projectID, String projectName) {
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
	 * @return the customer
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

}
