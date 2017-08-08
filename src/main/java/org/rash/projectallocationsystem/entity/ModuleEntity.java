package org.rash.projectallocationsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author rasool.shaik
 * 
 */

@Entity
@Table(name = "module")
public class ModuleEntity {

	@Id
	@Column(length = 10)
	private String moduleId;
	@Column(name = "name", length = 50)
	private String moduleName;
	@Column(length = 3)
	private Integer viewOrder;

	public ModuleEntity() {
		super();
	}

	public ModuleEntity(String moduleId, String moduleName, Integer viewOrder) {
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

}
