package org.rash.projectallocationsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rasool.shaik
 *
 */
@MappedSuperclass
public class BaseEntity {

	@Version
	@Column(name = "version")
	private Integer version;
	
	@Column(name = "createddate")
	private Date createdDate;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "updateddate")
	private Date updatedDate;
	
	@Column(name = "updatedby")
	private String updatedBy;
	/**
	 * 
	 */
	public BaseEntity() {
	}
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}


	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}


	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}


	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	} 

}
