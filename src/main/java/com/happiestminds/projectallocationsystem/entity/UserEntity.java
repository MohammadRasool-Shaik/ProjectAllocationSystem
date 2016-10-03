package com.happiestminds.projectallocationsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rasool.shaik
 * 
 */

@Entity
@Table(name = "user")
public class UserEntity{

	@Id
	@Column(length = 10, updatable = false)
	private String userId;
	@Column(length = 50, nullable = false)
	private String userName;

	@Column(length = 100, nullable = false)
	private String password;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 50, nullable = false)
	private String lastLoginIP;

	private Date lastLoginDate;

	private int failedAttempts;

	@Column(length = 1, nullable = false)
	private String accountStatus;

	private Date resetValidity;

	@Column(length = 1, nullable = false)
	private String currentlyBilled;

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
		this.email = email;
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
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus
	 *            the accountStatus to set
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
	 * @return the currentlyBilled
	 */
	public String getCurrentlyBilled() {
		return currentlyBilled;
	}

	/**
	 * @param currentlyBilled
	 *            the currentlyBilled to set
	 */
	public void setCurrentlyBilled(String currentlyBilled) {
		this.currentlyBilled = currentlyBilled;
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
}
