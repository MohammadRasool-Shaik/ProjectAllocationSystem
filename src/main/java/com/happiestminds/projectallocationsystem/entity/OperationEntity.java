/**
 * 
 */
package com.happiestminds.projectallocationsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "operations")
public class OperationEntity{

	@Id
	@Column(length = 10)
	private String operationId;

	private String description;
	@Column(length = 3)
	private Integer viewOrder;

	@ManyToOne(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "moduleId", nullable = false)
	private ModuleEntity module;
	
	@Transient
	private String isChecked;

	/**
	 * 
	 */
	public OperationEntity() {
		super();
	}

	public OperationEntity(String operationId, String description, Integer viewOrder) {
		super();
		this.operationId = operationId;
		this.description = description;
		this.viewOrder = viewOrder;
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

	public Integer getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
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
	 * @return the isChecked
	 */
	public String getIsChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked the isChecked to set
	 */
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}


}
