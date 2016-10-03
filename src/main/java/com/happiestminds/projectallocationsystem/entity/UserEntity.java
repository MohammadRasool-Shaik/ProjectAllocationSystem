package com.happiestminds.projectallocationsystem.entity;

import java.util.Date;

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
@Table(name = "user")
public class UserEntity {

	@Id
	@Column(length = 10, updatable = false)
	private String userId;
	@Column(length = 50, nullable = false, unique = true, updatable = false)
	private String userName;

	@Column(length = 100, nullable = false)
	private String password;

	@Transient
	private String cpassword;

	@Column(length = 100, nullable = false, unique = true)
	private String emailId;

	@Column(length = 50)
	private String lastLoginIP;

	private Date lastLoginDate;

	private int failedAttempts;

	@Column(length = 1, nullable = false)
	private Integer accountStatus = 0;

	private Date resetValidity;

	@Column(length = 1, nullable = false)
	private Integer currentlyBilled = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId", nullable = false)
	private UserGroupEntity userGroup;

	public UserEntity() {
	}

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param email
	 */
	public UserEntity(String userId, String userName, String password, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.emailId = email;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the email to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the lastLoginIP
	 */
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	/**
	 * @param lastLoginIP
	 *            the lastLoginIP to set
	 */
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	/**
	 * @return the lastLoginDate
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate
	 *            the lastLoginDate to set
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * @return the failedAttempts
	 */
	public int getFailedAttempts() {
		return failedAttempts;
	}

	/**
	 * @param failedAttempts
	 *            the failedAttempts to set
	 */
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	/**
	 * @return the resetValidity
	 */
	public Date getResetValidity() {
		return resetValidity;
	}

	/**
	 * @param resetValidity
	 *            the resetValidity to set
	 */
	public void setResetValidity(Date resetValidity) {
		this.resetValidity = resetValidity;
	}

	/**
	 * @return the userGroup
	 */
	public UserGroupEntity getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(UserGroupEntity userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @return the accountStatus
	 */
	public Integer getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus
	 *            the accountStatus to set
	 */
	public void setAccountStatus(Integer accountStatus) {

		if (accountStatus != null) {
			this.accountStatus = accountStatus;
		}
	}

	/**
	 * @return the currentlyBilled
	 */
	public Integer getCurrentlyBilled() {
		return currentlyBilled;
	}

	/**
	 * @param currentlyBilled
	 *            the currentlyBilled to set
	 */
	public void setCurrentlyBilled(Integer currentlyBilled) {
		if (currentlyBilled != null) {
			this.currentlyBilled = currentlyBilled;
		}

	}

	/**
	 * @return the cpassword
	 */
	public String getCpassword() {
		return cpassword;
	}

	/**
	 * @param cpassword
	 *            the cpassword to set
	 */
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
}
