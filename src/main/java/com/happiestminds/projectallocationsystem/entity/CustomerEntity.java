package com.happiestminds.projectallocationsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@Column(length = 50)
	private String customerID;

	@Column(length = 50)
	private String customerName;

	/**
	 * 
	 */
	public CustomerEntity() {
	}

	/**
	 * @param customerID
	 * @param customerName
	 */
	public CustomerEntity(String customerID, String customerName) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
