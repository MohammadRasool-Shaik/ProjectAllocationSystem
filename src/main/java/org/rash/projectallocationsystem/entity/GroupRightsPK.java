package org.rash.projectallocationsystem.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author rasool.shaik
 * 
 */
@Embeddable
public class GroupRightsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String groupId;

	private String operationId;

	/**
	 * 
	 */
	public GroupRightsPK() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the operationId
	 */
	public String getOperationId() {
		return operationId;
	}

	/**
	 * @param operationId the operationId to set
	 */
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}


}
