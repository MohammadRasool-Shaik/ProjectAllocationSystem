package com.happiestminds.projectallocationsystem.Dto;

import java.io.Serializable;

/**
 * @author rasool.shaik
 * 
 */

public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerID;

	private String customerName;

	private StatusDto statusDto;

	/**
	 * 
	 */
	public CustomerDto() {
	}

	/**
	 * @param customerID
	 * @param customerName
	 */
	public CustomerDto(String customerID, String customerName) {
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
