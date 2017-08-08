package org.rash.projectallocationsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "group_rights")
public class GroupRightsEntity {

	@AttributeOverrides({ @AttributeOverride(name = "groupId", column = @Column(name = "groupId", length = 10)),
			@AttributeOverride(name = "operationId", column = @Column(name = "operationId", length = 10)) })
	@EmbeddedId
	private GroupRightsPK groupRightPk;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "operationId", insertable = false, updatable = false)
	private OperationEntity operation;

	/**
	 * 
	 */
	public GroupRightsEntity() {
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
	public OperationEntity getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(OperationEntity operation) {
		this.operation = operation;
	}

}
