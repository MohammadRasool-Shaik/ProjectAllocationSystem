package org.rash.projectallocationsystem.Dto;

import java.io.Serializable;


/**
 * @author rasool.shaik
 * 
 */

public class GroupRightsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupRightsPK groupRightPk;

	private OperationDto operation;

	/**
	 * 
	 */
	public GroupRightsDto() {
		super();
	}

	/**
	 * @return the groupRightPk
	 */
	public GroupRightsPK getGroupRightPk() {
		return groupRightPk;
	}

	/**
	 * @param groupRightPk
	 *            the groupRightPk to set
	 */
	public void setGroupRightPk(GroupRightsPK groupRightPk) {
		this.groupRightPk = groupRightPk;
	}

	/**
	 * @return the operation
	 */
	public OperationDto getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(OperationDto operation) {
		this.operation = operation;
	}

}
