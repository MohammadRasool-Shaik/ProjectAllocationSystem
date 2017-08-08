package org.rash.projectallocationsystem.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.AllocationRequestDto;

public class AllocationRequestResponse {

	@JsonProperty("Record")
	private AllocationRequestDto allocationRequestResponse;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("Message")
	private String message;

	public AllocationRequestResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the allocationRequestResponse
	 */
	public AllocationRequestDto getAllocationRequestResponse() {
		return allocationRequestResponse;
	}

	/**
	 * @param allocationRequestResponse
	 *            the allocationRequestResponse to set
	 */
	public void setAllocationRequestResponse(AllocationRequestDto allocationRequestResponse) {
		this.allocationRequestResponse = allocationRequestResponse;
	}

}
