/**
 * 
 */
package com.happiestminds.projectallocationsystem.Dto;

import java.io.Serializable;


/**
 * @author rasool.shaik
 * 
 */

public class OperationDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operationId;

	private String description;
	private Integer viewOrder;

	private ModuleDto module;
	
	private String isChecked;

	/**
	 * 
	 */
	public OperationDto() {
		super();
	}

	public OperationDto(String operationId, String description, Integer viewOrder) {
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
	public ModuleDto getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(ModuleDto module) {
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
