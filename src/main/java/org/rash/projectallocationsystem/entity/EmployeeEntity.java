package org.rash.projectallocationsystem.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.rash.projectallocationsystem.enumerator.EmployeeStatus;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity {

	@Id
	@Column(length = 10, updatable = false)
	private String employeeId;

	@Column(name = "ename", length = 100)
	private String employeeName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skillId", nullable = false)
	private SkillSetEntity skillSetEntity;

	@Enumerated
	private EmployeeStatus empStatus;

	private String competency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private ProjectEntity projectEntity;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar assignedDate; // Currently Working Project Assigned Date

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar endDate; // Currently Working Project End Date

	/**
	 * 
	 */
	public EmployeeEntity() {
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the skillSetEntity
	 */
	public SkillSetEntity getSkillSetEntity() {
		return skillSetEntity;
	}

	/**
	 * @param skillSetEntity
	 *            the skillSetEntity to set
	 */
	public void setSkillSetEntity(SkillSetEntity skillSetEntity) {
		this.skillSetEntity = skillSetEntity;
	}

	/**
	 * @return the empStatus
	 */
	public EmployeeStatus getEmpStatus() {
		return empStatus;
	}

	/**
	 * @param empStatus
	 *            the empStatus to set
	 */
	public void setEmpStatus(EmployeeStatus empStatus) {
		this.empStatus = empStatus;
	}

	/**
	 * @return the competency
	 */
	public String getCompetency() {
		return competency;
	}

	/**
	 * @param competency
	 *            the competency to set
	 */
	public void setCompetency(String competency) {
		this.competency = competency;
	}

	/**
	 * @return the projectEntity
	 */
	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	/**
	 * @param projectEntity
	 *            the projectEntity to set
	 */
	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	/**
	 * @return the eName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the eName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the assignedDate
	 */
	public Calendar getAssignedDate() {
		return assignedDate;
	}

	/**
	 * @param assignedDate
	 *            the assignedDate to set
	 */
	public void setAssignedDate(Calendar assignedDate) {
		this.assignedDate = assignedDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

}
