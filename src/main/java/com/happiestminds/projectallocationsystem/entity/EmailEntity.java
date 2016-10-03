package com.happiestminds.projectallocationsystem.entity;

import java.util.List;

import javax.persistence.Embeddable;

/**
 * @author rasool.shaik
 * 
 */
@Embeddable
public class EmailEntity {

	private String subject;

	private String message;

	private List<String> emailIds;

	/**
	 * 
	 */
	public EmailEntity() {
		// TODO Auto-generated constructor stub
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

}
