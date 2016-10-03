package com.happiestminds.projectallocationsystem.Dto;

import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;

public class StatusDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private StatusCodeEnum statusCode;

	@JsonProperty
	private String statusMessage;

	private Map<String, String> errorsMap;

	/**
	 * 
	 */
	public StatusDto() {
		super();
	}

	/**
	 * @param statusCode
	 */
	public StatusDto(StatusCodeEnum statusCode) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusCode.getMsg();
	}

	/**
	 * @param statusCode
	 * @param statusMessage
	 */
	public StatusDto(StatusCodeEnum statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public void setStatusCode(StatusCodeEnum status) {
		this.statusCode = status;
		this.statusMessage = status.getMsg();
	}

	/**
	 * @return the statusCode
	 */
	public StatusCodeEnum getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the errorsMap
	 */
	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}

	/**
	 * @param errorsMap
	 *            the errorsMap to set
	 */
	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage
	 *            the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
