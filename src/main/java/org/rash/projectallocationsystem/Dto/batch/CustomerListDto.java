package org.rash.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rash.projectallocationsystem.Dto.CustomerDto;
import org.rash.projectallocationsystem.Dto.StatusDto;

/**
 * @author rasool.shaik
 * 
 */
public class CustomerListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CustomerDto> customers = new ArrayList<CustomerDto>();

	private StatusDto statusDto;

	/**
	 * 
	 */
	public CustomerListDto() {
	}

	/**
	 * @return the customers
	 */
	public List<CustomerDto> getCustomers() {
		return customers;
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(List<CustomerDto> customers) {
		this.customers = customers;
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
