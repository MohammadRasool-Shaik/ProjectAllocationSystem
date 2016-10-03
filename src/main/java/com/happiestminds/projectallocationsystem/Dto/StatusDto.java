package com.happiestminds.projectallocationsystem.Dto;

import java.io.Serializable;

import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;

/**
 * @author rasool.shaik
 * 
 */

public class StatusDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusMsg;

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */

	public void setStatusCode(StatusCodeEnum code) {
		this.statusMsg= code.getMsg();
	}
	

	/**
	 * @return the statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * @param statusMsg
	 *            the statusMsg to set
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

}
