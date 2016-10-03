package com.happiestminds.projectallocationsystem.Dto;

import java.util.List;

/**
 * @author rasool.shaik
 * 
 */
public class EmailDto {

	private String subject;

	private String message;

	private List<String> emailIds;

	private String fromMailId;

	/**
	 * 
	 */
	public EmailDto() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getEmailIds() {
		return emailIds;
	}

	public void setEmailIds(List<String> emailIds) {
		this.emailIds = emailIds;
	}

	/**
	 * @return the fromMailId
	 */
	public String getFromMailId() {
		return fromMailId;
	}

	/**
	 * @param fromMailId
	 *            the fromMailId to set
	 */
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}

}
