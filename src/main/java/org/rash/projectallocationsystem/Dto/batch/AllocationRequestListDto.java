/**
 * 
 */
package org.rash.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rash.projectallocationsystem.Dto.AllocationRequestDto;
import org.rash.projectallocationsystem.Dto.StatusDto;

/**
 * @author rasool.shaik
 * 
 */
public class AllocationRequestListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<AllocationRequestDto> requests = new ArrayList<AllocationRequestDto>();

	private StatusDto statusDto;

	/**
	 * 
	 */
	public AllocationRequestListDto() {
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
	 * @return the requests
	 */
	public List<AllocationRequestDto> getRequests() {
		return requests;
	}

	/**
	 * @param requests
	 *            the requests to set
	 */
	public void setRequests(List<AllocationRequestDto> requests) {
		this.requests = requests;
	}

}
