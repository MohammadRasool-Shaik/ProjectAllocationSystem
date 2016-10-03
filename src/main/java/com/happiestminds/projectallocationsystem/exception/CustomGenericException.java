package com.happiestminds.projectallocationsystem.exception;

import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;

/**
 * @author rasool.shaik
 * 
 */

public class CustomGenericException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private StatusCodeEnum statusCode;
	private String statusMsg;

	/**
	 * 
	 */
	public CustomGenericException() {
		super();
	}

	/**
	 * @param statusCode
	 * @param statusMsg
	 */
	public CustomGenericException(StatusCodeEnum statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomGenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CustomGenericException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CustomGenericException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CustomGenericException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param statusCode
	 */
	public CustomGenericException(StatusCodeEnum statusCode) {
		super();
		this.statusCode = statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */

	public void setStatusCode(StatusCodeEnum code) {
		this.statusMsg = code.getMsg();
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

	/**
	 * @return the statusCode
	 */
	public StatusCodeEnum getStatusCode() {
		return statusCode;
	}

}
