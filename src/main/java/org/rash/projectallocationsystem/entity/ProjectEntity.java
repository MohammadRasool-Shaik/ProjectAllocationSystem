package org.rash.projectallocationsystem.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "Projects")
public class ProjectEntity {
	@Id
	@Column(length = 10)
	private String projectID;

	@Column(length = 50, nullable = false)
	private String projectName;

	@Column(length = 100)
	private String adminSPOC;

	@Column(length = 100)
	private String techSPOC;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar startDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar expectedEndDate;

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

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the expectedEndDate
	 */
	public Calendar getExpectedEndDate() {
		return expectedEndDate;
	}

	/**
	 * @param expectedEndDate the expectedEndDate to set
	 */
	public void setExpectedEndDate(Calendar expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}


}
