package com.happiestminds.projectallocationsystem.Dto;

import java.io.Serializable;

/**
 * 
 * @author rasool.shaik
 * 
 */

public class ModuleDto implements Serializable, Comparable<ModuleDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moduleId;
	private String moduleName;
	private Integer viewOrder;

	public ModuleDto() {
		super();
	}

	public ModuleDto(String moduleId, String moduleName, Integer viewOrder) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.viewOrder = viewOrder;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the viewOrder
	 */
	public Integer getViewOrder() {
		return viewOrder;
	}

	/**
	 * @param viewOrder
	 *            the viewOrder to set
	 */
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId
	 *            the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public int compareTo(ModuleDto o) {
		return this.viewOrder < o.viewOrder ? -1 : this.viewOrder > o.viewOrder ? 1 : 0;
	}
}
