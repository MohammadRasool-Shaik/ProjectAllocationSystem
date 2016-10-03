/**
 * 
 */
package com.happiestminds.projectallocationsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "operations")
public class OperationEntity {

	@Id
	@Column(length = 10)
	private String operationId;

	private String description;
	@Column(length = 3)
	private Integer viewOrder;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "moduleId", nullable = false)
	private ModuleEntity module;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "group_rights", joinColumns = @JoinColumn(name = "operationId"), inverseJoinColumns = @JoinColumn(name = "groupId"))
	private Set<UserGroupEntity> userGroups = new HashSet<UserGroupEntity>();

	/**
	 * 
	 */
	public OperationEntity() {
		super();
	}

	public OperationEntity(String operationId, String description, Integer viewOrder, ModuleEntity module) {
		super();
		this.operationId = operationId;
		this.description = description;
		this.viewOrder = viewOrder;
		this.module = module;
	}

	/**
	 * @return the operationId
	 */
	public String getOperationId() {
		return operationId;
	}

	/**
	 * @param operationId
	 *            the operationId to set
	 */
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the module
	 */
	public ModuleEntity getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(ModuleEntity module) {
		this.module = module;
	}

	/**
	 * @return the userGroups
	 */
	public Set<UserGroupEntity> getUserGroups() {
		return userGroups;
	}

	/**
	 * @param userGroups
	 *            the userGroups to set
	 */
	public void setUserGroups(Set<UserGroupEntity> userGroups) {
		this.userGroups = userGroups;
	}

	public Integer getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

}
