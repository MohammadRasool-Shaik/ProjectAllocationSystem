package org.rash.projectallocationsystem.Dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author rasool.shaik
 * 
 */

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	@NotNull(message = "Please enter a username")
	@Size(min = 2, max = 50, message = "User Name size must be between 2 and 50 characters")
	private String userName;

	@NotNull(message = "Please provide a password")
	@Size(min = 2, max = 50, message = "Password size must be between 2 and 50 characters")
	private String password;

	@Size(min = 2, max = 50, message = "Confirm password size must be between 2 and 50 characters")
	private String cpassword;

	@Size(max = 100, min = 2, message = "EmailId size must be between 2 and 100 characters")
	private String emailId;

	private String lastLoginIP;

	private Date lastLoginDate;

	private Integer failedAttempts;

	private Integer accountStatus = 0;

	private Date resetValidity;

	private String userGroupId;

	private StatusDto statusDto;

	public UserDto() {
	}

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param email
	 */
	public UserDto(Integer userId, String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.emailId = email;
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
	public Integer getFailedAttempts() {
		return failedAttempts;
	}

	/**
	 * @param failedAttempts
	 *            the failedAttempts to set
	 */
	public void setFailedAttempts(Integer failedAttempts) {
		if (failedAttempts != null) {
			this.failedAttempts = failedAttempts;
		}
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

	/**
	 * @return the statusDto
	 */
	public StatusDto getStatusDto() {
		return statusDto;
	}

	/**
	 * @param statusDto
	 *            the statusDto to set
	 */
	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
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
}
