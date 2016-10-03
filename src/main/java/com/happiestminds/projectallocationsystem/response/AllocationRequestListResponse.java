package com.happiestminds.projectallocationsystem.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.Dto.AllocationRequestDto;

/**
 * @author rasool.shaik
 * 
 */
public class AllocationRequestListResponse {

	@JsonProperty("Records")
	private List<AllocationRequestDto> requests;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;

	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public AllocationRequestListResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
