package org.rash.projectallocationsystem.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.rash.projectallocationsystem.Dto.EmployeeDto;

/**
 * @author rasool.shaik
 * 
 */
public class EmployeeListResponse {

	@JsonProperty("Records")
	private List<EmployeeDto> employes;

	@JsonProperty("Result")
	private String result;

	@JsonProperty("TotalRecordCount")
	private int totalRecordCount;

	@JsonProperty("Message")
	private String message;

	/**
	 * 
	 */
	public EmployeeListResponse() {
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

	public List<EmployeeDto> getEmployes() {
		return employes;
	}

	public void setEmployes(List<EmployeeDto> employes) {
		this.employes = employes;
	}

}
